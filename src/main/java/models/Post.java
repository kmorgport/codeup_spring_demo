package models;

public class Post {
    private String title;
    private String body;

    public Post(){}

    public Post(String title, String body){
        this.title = title;
        this.body = body;
    }

    public String getTitle(){
        return this.title;
    }

    public String getBody(){
        return this.body;
    }
}
