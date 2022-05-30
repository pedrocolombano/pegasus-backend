package br.com.lacostech.pegasusbackend.controllers;

import br.com.lacostech.pegasusbackend.model.requests.PostRequest;
import br.com.lacostech.pegasusbackend.model.responses.PostDetailedResponse;
import br.com.lacostech.pegasusbackend.model.responses.PostResponse;
import br.com.lacostech.pegasusbackend.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<Page<PostResponse>> findAllPaginated(@PageableDefault(size = 20) final Pageable pageable) {
        Page<PostResponse> posts = postService.findAllPosts(pageable);        return ResponseEntity.ok(posts);
    }

    @GetMapping("/themes/{themeId}")
    public ResponseEntity<Page<PostResponse>> findAllPaginated(
            @PathVariable final Long themeId,
            @PageableDefault(size = 20) final Pageable pageable) {
        Page<PostResponse> posts = postService.findAllPostsByTheme(themeId, pageable);
        return ResponseEntity.ok(posts);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDetailedResponse> findDetailedPost(@PathVariable final Long id) {
        PostDetailedResponse response = postService.findDetailedPost(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PostDetailedResponse> insert(@RequestBody final PostRequest request) {
        PostDetailedResponse response = postService.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDetailedResponse> update(
            @PathVariable final Long id,
            @RequestBody final PostRequest request) {
        PostDetailedResponse response = postService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long id) {
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
