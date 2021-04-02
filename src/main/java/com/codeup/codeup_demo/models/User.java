package com.codeup.codeup_demo.models;

import com.codeup.codeup_demo.util.Password;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(length = 225, nullable = false, unique = true)
    private String username;

    @Column(length = 225, nullable = false, unique = true)
    private String email;

    @Column(length = 225, nullable = false)
    private String password;

    @Column(length=225, nullable = false)
    private String roles;

    @OneToMany(cascade =CascadeType.ALL, mappedBy= "owner")
    private List<Post> posts;

    public User() {
    }

    public User(String username, String email, String password, String roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(User copy){
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
        roles = copy.roles;
    }

    public User(long id, String username, String email, String password, String roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.password = password;
    }

    public long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = Password.hash(password);
    }
}
