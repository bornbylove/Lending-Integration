package com.dev.LendingIntegration_.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Authorization_Token")
public class User implements UserDetails {
    @Id
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "Password", nullable = false)
    private String password;
    @Column(name = "response_Code", nullable = false)
    private String responseCode;
    @Column(name = "Response_Message", nullable = false)
    private String responseMessage;
    @Column(name = "Authentication_Token")
    private String authToken;
    @Column(name = "Expiration_Time", nullable = false)
    private String expirationInMinutes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
