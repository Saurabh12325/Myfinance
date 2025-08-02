package com.myfinance.Myfinance.Mapper;

import com.myfinance.Myfinance.Entity.ProfileEntity;
import com.myfinance.Myfinance.dto.ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


public class Mapper {


    public static ProfileEntity mapToEntity(ProfileDTO profileDTO) {
        return ProfileEntity.builder()
                .id(profileDTO.getId())
                            .fullName(profileDTO.getFullName())
                            .email(profileDTO.getEmail())
                        .password(profileDTO.getPassword())
                        .profileImageUrl(profileDTO.getProfileImageUrl())
                        .createdAt(profileDTO.getCreatedAt())
                        .updatedAt(profileDTO.getUpdatedAt())
                .build();

    }
    public static ProfileDTO mapToDTO(ProfileEntity profileEntity) {
        return ProfileDTO.builder()
                .id(profileEntity.getId())
                .fullName(profileEntity.getFullName())
                .email(profileEntity.getEmail())
                .profileImageUrl(profileEntity.getProfileImageUrl())
                .createdAt(profileEntity.getCreatedAt())
                .updatedAt(profileEntity.getUpdatedAt())
                .build();
    }
}
