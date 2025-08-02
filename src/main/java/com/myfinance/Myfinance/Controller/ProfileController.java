package com.myfinance.Myfinance.Controller;

import com.myfinance.Myfinance.Service.ProfileService;
import com.myfinance.Myfinance.dto.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import com.myfinance.Myfinance.dto.ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("/activate")
    public ResponseEntity<String> activateProfile(@RequestParam String token) {
        boolean isActivated = profileService.activateProfile(token);
        if (isActivated) {
            return ResponseEntity.ok("Profile activated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid activation token.");
        }
    }

    public ResponseEntity<Map<String,Object>> login(@RequestBody LoginDto loginDto) {
        try {
            if (!profileService.isAccountActive(loginDto.getEmail())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "Account is not active,Please active your account first."));
            }
            Map<String, Object> response = profileService.authenticateAndGenerateToken(loginDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }




}
