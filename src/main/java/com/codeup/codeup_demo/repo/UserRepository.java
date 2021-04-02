package com.codeup.codeup_demo.repo;

import com.codeup.codeup_demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

//    @Query("select ur.role from UserRole ur, User u where u.username=?1 and ur.userId = u.id")
//    List<String> ofUserWith(String username);
//    List<User> ofUserWith(String username);

    List<User> findByIdEquals(long id);
}
