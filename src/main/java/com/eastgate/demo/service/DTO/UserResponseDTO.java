package com.eastgate.demo.service.DTO;

import com.eastgate.demo.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserResponseDTO implements Serializable {
    private UUID id;

    private String username;

    private String secretKey;

    private LocalDateTime createdDate;

    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getSecretKey(),
                user.getCreatedDate()
        );
    }
}
