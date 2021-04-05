package com.codeup.codeup_demo.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserWithRoles extends User implements UserDetails {

    private String username;
    private String password;
    private String email;
    private List<GrantedAuthority> authorities;
    private List<String> roles;

//    public UserWithRoles(User user){
//        this.username = user.getUsername();
//        this.password = user.getPassword();
//        this.email = user.getEmail();
//        this.authorities = Arrays.stream(user.getRoles().split(","))
//                .map(SimpleGrantedAuthority:: new)
//                .collect(Collectors.toList());
//    }

    public UserWithRoles(User user, List<String> roles){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.roles = roles;

    }

    public UserWithRoles(User user){
        super(user);
    }

//    public UserWithRoles(User user) {
//        super(user);  // Call the copy constructor defined in User
//    }

//    public UserWithRoles(String username){
//        this.username = username;
//    }
    public UserWithRoles() {

    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = ""; // Since we're not using the authorization part of the component
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }

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
