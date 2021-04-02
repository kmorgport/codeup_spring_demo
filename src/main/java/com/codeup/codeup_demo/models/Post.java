package com.codeup.codeup_demo.models;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 225, nullable=false)
    private String title;

    @Column(columnDefinition = "TEXT", length = 3000, nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Post(){}

    public Post(String title, String body){
        this.title = title;
        this.body = body;
    }

    public Post(long id, String title, String body){
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getBody(){
        return this.body;
    }
    public void setBody(String body){
        this.body = body;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
    }

    public void setOwner(User owner){
        this.owner = owner;
    }

    public User getOwner(){
        return this.owner;
    }
}
