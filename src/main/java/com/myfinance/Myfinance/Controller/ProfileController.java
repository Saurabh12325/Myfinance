package com.myfinance.Myfinance.Controller;

import com.myfinance.Myfinance.Service.ProfileService;
import com.myfinance.Myfinance.dto.ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    public ResponseEntity<ProfileDTO> registerProfile(ProfileDTO profileDTO) {
        ProfileDTO profile = profileService.registerProfile(profileDTO);
        return ResponseEntity.ok(profile);
    }

}
