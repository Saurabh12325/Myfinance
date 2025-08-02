package com.myfinance.Myfinance.Service;

import com.myfinance.Myfinance.Entity.ProfileEntity;
import com.myfinance.Myfinance.Mapper.Mapper;
import com.myfinance.Myfinance.Repository.ProfileRepository;
import com.myfinance.Myfinance.dto.ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

                         
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

    //token validation
    public boolean activateProfile(String activationToken) {
        return  profileRepository.findByActivationToken(activationToken)
                .map(profile -> {
                    profile.setIsActive(true);
                    profileRepository.save(profile);
                    return true;
                })
                .orElse(false);
    }


}
