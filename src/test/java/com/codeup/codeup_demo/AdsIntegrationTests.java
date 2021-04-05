package com.codeup.codeup_demo;

import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.repo.PostRepository;
import com.codeup.codeup_demo.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CodeupDemoApplication.class)
@AutoConfigureMockMvc
public class AdsIntegrationTests {

    private User testUser;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userDao;

    @Autowired
    PostRepository postDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws Exception{
        testUser = userDao.findByUsername("testUser");

        if(testUser == null){
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail(("testUser@test.com"));
            testUser =  userDao.save(newUser);
        }

        httpSession = this.mvc.perform(post("/login").with(csrf())
                .param("username","testUser")
                .param("password", "pass"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/posts"))
                .andReturn()
                .getRequest()
                .getSession();

    }

    @Test
    public void contextLoads(){
        //Sanity test
        assertNotNull(mvc);
    }

    @Test
    public void testIfUserSessionIsActive(){
        assertNotNull(httpSession);
    }

    @Test
    public void testCreatePost() throws Exception{
        //Makes
        this.mvc.perform(
                post("/posts/create").with(csrf())
                .session((MockHttpSession) httpSession)
                .param("title","test title")
                .param("body", "testing body"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testShowPost() throws Exception{
        Post post = postDao.findAll().get(0);

        //Makes a Get request to /posts/{id}
        this.mvc.perform(get("/posts/"+post.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(post.getBody())));
    }

    @Test
    public void testPostIndex() throws Exception{
        Post post = postDao.findAll().get(0);

        this.mvc.perform(get("/posts"))
                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Latest Post")))
                .andExpect(content().string(containsString(post.getTitle())));

    }

    @Test
    public void testEditAd() throws Exception{
        Post post = postDao.findAll().get(0);

        //makes post request to edit page and expect a redirection to post show page
        this.mvc.perform(
                post("/posts"+post.getId()+"/edit").with(csrf())
                .session((MockHttpSession) httpSession)
                .param("title","edited title")
                .param("body","edited post body"))
                .andExpect(status().is3xxRedirection());

        this.mvc.perform(get("/posts/"+post.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("edited title")))
                .andExpect(content().string(containsString("edited post body")));
    }
}
