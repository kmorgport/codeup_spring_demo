package controllers;

import models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import repo.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao){
        this.postDao = postDao;
    }
//    private final PostRepository postDao;
//
//    public PostController(PostRepository postDao){
//        this.postDao = postDao;
//    }

//    List<Post> posts = new ArrayList<>();

    @GetMapping("/posts")
    public String posts(Model model) {
        List<Post> postsFromDB = postDao.findAll();
//        posts.add(new Post("PS5", "new"));
        model.addAttribute("posts",postsFromDB);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postsId(@PathVariable int id, Model model) {
        model.addAttribute("post", new Post("ipad","new"));
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