package com.myfinance.Myfinance.Service;

import com.myfinance.Myfinance.Entity.ProfileEntity;
import com.myfinance.Myfinance.Mapper.Mapper;
import com.myfinance.Myfinance.Repository.ProfileRepository;
import com.myfinance.Myfinance.dto.ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileDTO registerProfile(ProfileDTO profileDTO) {
        ProfileEntity newProfile =  Mapper.mapToEntity(profileDTO);
        newProfile.setActivationToken(UUID.randomUUID().toString());
        newProfile = profileRepository.save(newProfile);
        String activatationLink = "http://localhost:8080/api/v1.0/profile/activate?token=" + newProfile.getActivationToken();
        String Subject = "Activate your account";
        return Mapper.mapToDTO(newProfile);

    }


}
