package com.codeup.codeup_demo.repo;

import com.codeup.codeup_demo.models.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Roles extends CrudRepository<UserRole,Long> {
    @Query("select ur.role from UserRole ur, User u where u.username=?1 and ur.id = u.id")
    List<String> ofUserWith(String username);
}
