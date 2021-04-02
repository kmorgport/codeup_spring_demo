package com.codeup.codeup_demo;

import com.codeup.codeup_demo.repo.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class CodeupDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeupDemoApplication.class, args);
    }

}
