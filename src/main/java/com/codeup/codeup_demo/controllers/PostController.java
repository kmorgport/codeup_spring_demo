package com.codeup.codeup_demo.controllers;

import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.repo.UserRepository;
import com.codeup.codeup_demo.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.codeup.codeup_demo.repo.PostRepository;

import java.util.List;

@Controller
class PostController {

    private PostRepository postDao;
    private UserRepository userDao;
    private EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService){
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
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

    @PostMapping("/posts/{id}/show")
    @ResponseBody
    public String deleteAdOnShowPage(@PathVariable long id){
        postDao.deleteById(id);
        return "You deleted an ad";
    }

//    @GetMapping("/posts/create")
//    public String viewCreate() {
//        return "posts/create";
//    }

    @GetMapping("/posts/create")
    public String viewCreate(Model model) {
        model.addAttribute("post",new Post());

        return "posts/create";
    }

//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String createPost(@RequestParam("post_title") String title,@RequestParam("post_description")String description) {
//        User user = userDao.getOne(1L);
//        Post newPost = new Post(title, description);
//        newPost.setOwner(user);
//        postDao.save(newPost);
//        return "return a new post";
//    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setOwner(user);
        Post savePost = postDao.save(post);
        emailService.prepareAndSend(savePost,"New Ad!", "A new post was created!");
        return "posts/index";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEdit(Model model, @PathVariable Long id) {
        model.addAttribute("post",postDao.getOne(id));
        return "/posts/index";
    }

    @PostMapping("/posts/{id}/edit")
    @ResponseBody
    public String editPost(@ModelAttribute Post postToUpdate, @PathVariable Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postToUpdate.setId(id);
        postToUpdate.setOwner(user);
        postDao.save(postToUpdate);

        return "posts/edit";
    }

    @PostMapping("/posts/{id}/delete")
    @ResponseBody
    public String deleteAd(@PathVariable long id){
        postDao.deleteById(id);
        return "You deleted an ad";
    }
}