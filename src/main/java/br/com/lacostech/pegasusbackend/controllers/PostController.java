package br.com.lacostech.pegasusbackend.controllers;

import br.com.lacostech.pegasusbackend.model.responses.PostDetailedResponse;
import br.com.lacostech.pegasusbackend.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailedResponse> findDetailedPost(@PathVariable final Long id) {
        PostDetailedResponse response = postService.findDetailedPost(id);
        return ResponseEntity.ok(response);
    }

}
