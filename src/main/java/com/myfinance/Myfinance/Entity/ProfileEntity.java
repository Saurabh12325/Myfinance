package com.myfinance.Myfinance.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileEntity {
    @Id
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private  String password;
    private String profileImageUrl;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;
    private String activationToken;

    @PrePersist
    public void prePersist() {
        if(this.isActive == null) {
            isActive = false;
        }
    }


}
