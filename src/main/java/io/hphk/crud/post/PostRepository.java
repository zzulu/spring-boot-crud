package io.hphk.crud.post;
import org.springframework.data.jpa.repository.JpaRepository;
import io.hphk.crud.post.Post;


public interface PostRepository extends JpaRepository<Post, Long> {

}
