package com.codeup.codeup_demo.models;

import javax.persistence.*;

@Entity
@Table(name= "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "role")
    private String role;

    public UserRole() {
    }

    public UserRole(long id, String role) {
        this.id = id;

        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
