package com.myfinance.Myfinance.Mapper;

import com.myfinance.Myfinance.Entity.CategoryEntity;
import com.myfinance.Myfinance.Entity.ProfileEntity;
import com.myfinance.Myfinance.dto.CategoryDto;
import com.myfinance.Myfinance.dto.ProfileDTO;



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

    public static CategoryEntity mapToCategoryEntity(CategoryDto categoryDto,ProfileEntity profile) {
        return CategoryEntity.builder()
                .name(categoryDto.getName())
                .type(categoryDto.getType())
                .icon(categoryDto.getIcon())
                .profile(profile)
                .build();
    }

    public static  CategoryDto mapToCategoryDto(CategoryEntity categoryEntity) {
        return CategoryDto.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .type(categoryEntity.getType())
                .icon(categoryEntity.getIcon())
                .profileId(categoryEntity.getProfile() != null ? categoryEntity.getProfile().getId() : null)
                .createdAt(categoryEntity.getCreatedAt())
                .updatedAt(categoryEntity.getUpdatedAt())
                .build();
    }
}
