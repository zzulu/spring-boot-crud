package io.hphk.crud.post;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Iterable<Post> all() {
        return postRepository.findAll();
    }

    public Post create(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        return postRepository.save(post);
    }

    public Post get(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(() -> new EntityNotFoundException());
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    public Post update(Long id, String title, String content) {
        Post post = this.get(id);
        post.setTitle(title);
        post.setContent(content);
        return postRepository.save(post);
    }

}
