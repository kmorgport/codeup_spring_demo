package com.codeup.codeup_demo;

import models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
class PostController {

    List<Post> posts = new ArrayList<>();

    @GetMapping("/posts")
    public String posts(Model model) {
        posts.add(new Post("PS5", "new"));
        model.addAttribute("posts",posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postsId() {
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewCreate() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "return a new post";
    }

}