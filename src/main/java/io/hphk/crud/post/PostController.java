package io.hphk.crud.post;

import io.hphk.crud.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public String postList(Authentication authentication, Model model) {
        Iterable<Post> posts = postService.all();
        model.addAttribute("posts", posts);
        if (authentication != null) {
            User user = (User) authentication.getPrincipal();
            String username = user.getUsername();
            model.addAttribute("username", username);
        }
        return "post/list.html";
    }

    @GetMapping("/new")
    public String postNew () {
        return "post/new.html";
    }

    @PostMapping("/create")
    public RedirectView postCreate (@RequestParam String title, @RequestParam String content) {
        Post post = postService.create(title, content);
        return new RedirectView(String.format("/posts/%s", post.getId()));
    }

    @GetMapping("/{postId}")
    public String postDetail (@PathVariable("postId") Long postId, Model model) {
        Post post = postService.get(postId);
        model.addAttribute("post", post);
        return "post/detail.html";
    }

    @DeleteMapping("/{postId}")
    public RedirectView postDelete (@PathVariable("postId") Long postId) {
        postService.delete(postId);
        return new RedirectView("/posts");
    }

    @GetMapping("/{postId}/edit")
    public String postEdit (@PathVariable("postId") Long postId, Model model) {
        Post post = postService.get(postId);
        model.addAttribute("post", post);
        return "post/edit.html";
    }

    @PutMapping("/{postId}")
    public RedirectView postUpdate (@PathVariable("postId") Long postId, @RequestParam String title, @RequestParam String content) {
        postService.update(postId, title, content);
        return new RedirectView(String.format("/posts/%s", postId));
    }

}
