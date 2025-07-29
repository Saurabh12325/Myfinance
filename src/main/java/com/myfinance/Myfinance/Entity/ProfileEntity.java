package com.myfinance.Myfinance.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
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
    private String email;
    private  String password;
    private String profileImageUrl;
    private LocalDateTime createdAt;
    private Boolean isActive;
    private String activationToken;

    @PrePersist
    public void prePersist() {
        if(this.isActive == null) {
            isActive = false;
        }
    }


}
