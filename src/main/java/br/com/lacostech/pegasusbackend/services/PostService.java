package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.entities.Post;
import br.com.lacostech.pegasusbackend.model.entities.Theme;
import br.com.lacostech.pegasusbackend.model.responses.PostDetailedResponse;
import br.com.lacostech.pegasusbackend.model.responses.PostResponse;
import br.com.lacostech.pegasusbackend.repositories.PostRepository;
import br.com.lacostech.pegasusbackend.repositories.ThemeRepository;
import br.com.lacostech.pegasusbackend.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ThemeRepository themeRepository;

    @Transactional(readOnly = true)
    public Page<PostResponse> findAllPosts(final Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        return posts.map(PostResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<PostResponse> findAllPostsByTheme(final Long themeId, final Pageable pageable) {
        Theme theme = getThemeById(themeId);
        Page<Post> posts = postRepository.findAllByTheme(theme, pageable);
        return posts.map(PostResponse::new);
    }

    @Transactional(readOnly = true)
    public PostDetailedResponse findDetailedPost(final Long id) {
        return postRepository.findById(id)
                .map(PostDetailedResponse::new)
                .orElseThrow(() -> new NotFoundException("Post id " + id + " not found"));
    }

    private Theme getThemeById(Long themeId) {
        return themeRepository.findById(themeId)
                .orElseThrow(() -> new NotFoundException("Theme id " + themeId + " does not exists"));
    }

}
