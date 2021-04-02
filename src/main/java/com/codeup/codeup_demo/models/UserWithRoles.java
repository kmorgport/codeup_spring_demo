package com.codeup.codeup_demo.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UserWithRoles extends User implements UserDetails {

    private String username;

    public UserWithRoles(User user) {
        super(user);  // Call the copy constructor defined in User
    }

    public UserWithRoles(String username){
        this.username = username;
    }
    public UserWithRoles() {

    }

    @Override
    public String getPassword(){
        return "pass";
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        String roles = ""; // Since we're not using the authorization part of the component
//        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
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
