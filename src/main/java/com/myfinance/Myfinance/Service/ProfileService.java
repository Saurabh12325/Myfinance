package com.myfinance.Myfinance.Service;

import com.myfinance.Myfinance.Entity.ProfileEntity;
import com.myfinance.Myfinance.Mapper.Mapper;
import com.myfinance.Myfinance.Repository.ProfileRepository;
import com.myfinance.Myfinance.dto.LoginDto;
import com.myfinance.Myfinance.dto.ProfileDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

                         
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
     private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public ProfileDTO registerProfile(ProfileDTO profileDTO) {
        ProfileEntity newProfile =  Mapper.mapToEntity(profileDTO);
        newProfile.setPassword(passwordEncoder.encode(newProfile.getPassword()));
        newProfile.setActivationToken(UUID.randomUUID().toString());
        newProfile = profileRepository.save(newProfile);
        String activatationLink = "http://localhost:8080/api/v1.0/profile/activate?token=" + newProfile.getActivationToken();
        String Subject = "Activate your account";
        String Body = "Click here to activate your account " + activatationLink;
        emailService.sendEmail(newProfile.getEmail(), Subject, Body);
        return Mapper.mapToDTO(newProfile);

    }

    //token validation api
    public boolean activateProfile(String activationToken) {
        return  profileRepository.findByActivationToken(activationToken)
                .map(profile -> {
                    profile.setIsActive(true);
                    profileRepository.save(profile);
                    return true;
                })
                .orElse(false);
    }

    public boolean isAccountActive(String email) {
        return profileRepository.findByEmail(email)
                .map(ProfileEntity::getIsActive)
                .orElse(false);
    }

    public  ProfileEntity getCurrentProfile(String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return profileRepository
                .findByEmail(authentication.getName()).orElseThrow(()-> new UsernameNotFoundException("Profile not found with this email" + email));
    }

    public ProfileDTO getPublicProfile(String email){
        ProfileEntity currentUser = null;
        if(email == null) {
            currentUser = getCurrentProfile(email);
        } else {
            currentUser = profileRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Profile not found with this email " + email));
        }
        return Mapper.mapToDTO(currentUser);
    }

    public Map<String, Object> authenticateAndGenerateToken(LoginDto loginDto) {

    }
}
