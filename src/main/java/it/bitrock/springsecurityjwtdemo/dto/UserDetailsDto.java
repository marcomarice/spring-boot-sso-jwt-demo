package it.bitrock.springsecurityjwtdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto implements Serializable {
    private Long id;
    private String username;
    private String role;
    private String token;
    private String tokenExpirationDate;
}
