package com.myfinance.Myfinance.Controller;

import com.myfinance.Myfinance.Service.ProfileService;

import com.myfinance.Myfinance.dto.ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

@PostMapping("/register")
    public ResponseEntity<ProfileDTO> registerProfile( @RequestBody ProfileDTO profileDTO) {
        ProfileDTO profile = profileService.registerProfile(profileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(profile);
    }

}
