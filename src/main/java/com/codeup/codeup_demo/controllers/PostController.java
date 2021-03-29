package com.codeup.codeup_demo.controllers;

import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.repo.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.codeup.codeup_demo.repo.PostRepository;

import java.util.List;

@Controller
class PostController {

    private PostRepository postDao;
    private UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao){

        this.postDao = postDao;
        this.userDao = userDao;
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

    @GetMapping("/posts/{id}/show")
    public String postsId(@PathVariable Long id, Model model) {
        model.addAttribute("post", postDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewCreate() {
        return "posts/create";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(@RequestParam("post_title") String title,@RequestParam("post_description")String description) {
        User user = userDao.getOne(1L);
        Post newPost = new Post(title, description);
        newPost.setOwner(user);
        postDao.save(newPost);
        return "return a new post";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEdit(@PathVariable Long id, Model model) {

        Post postFromDb = postDao.getOne(id);
        model.addAttribute("oldPost",postFromDb);

        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    @ResponseBody
    public String editPost(@RequestParam("post_title") String title,@RequestParam("post_description")String description) {
        Post newPost = new Post(title, description);
        postDao.save(newPost);
        return "return a new post";
    }

    @PostMapping("/posts/{id}/delete")
    @ResponseBody
    public String deleteAd(@PathVariable long id){
        postDao.deleteById(id);
        return "You deleted an ad";
    }
}