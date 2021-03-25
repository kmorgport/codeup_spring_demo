package repo;

import models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    List<Post> findByTitleEquals(String title);

//    @Query("from POST post where post.body like %")
//    List<Post> searchByTitleLike(@Param("term")String term);
}
