package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.entities.Article;
import br.com.lacostech.pegasusbackend.model.entities.Post;
import br.com.lacostech.pegasusbackend.model.entities.Theme;
import br.com.lacostech.pegasusbackend.model.entities.User;
import br.com.lacostech.pegasusbackend.model.requests.ArticleRequest;
import br.com.lacostech.pegasusbackend.model.requests.PostRequest;
import br.com.lacostech.pegasusbackend.model.responses.PostDetailedResponse;
import br.com.lacostech.pegasusbackend.model.responses.PostResponse;
import br.com.lacostech.pegasusbackend.repositories.ArticleRepository;
import br.com.lacostech.pegasusbackend.repositories.PostRepository;
import br.com.lacostech.pegasusbackend.repositories.ThemeRepository;
import br.com.lacostech.pegasusbackend.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ThemeRepository themeRepository;
    private final ArticleRepository articleRepository;
    private final AuthService authService;

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

    @Transactional
    public PostDetailedResponse insert(final PostRequest request) {
        Post post = new Post();
        copyDataFromRequest(request, post);

        post = postRepository.save(post);
        insertPostArticlesFromRequest(post, request.getArticles());

        return new PostDetailedResponse(post);
    }

    private void copyDataFromRequest(final PostRequest request, final Post entity) {
        if (Objects.nonNull(request)) {
            BeanUtils.copyProperties(request, entity);

            User user = authService.getAuthenticatedUser();
            entity.setAuthor(user);

            Theme theme = getThemeById(request.getThemeId());
            entity.setTheme(theme);

            entity.getImages().clear();
            entity.getImages().addAll(request.getImages());
        }
    }

    private void insertPostArticlesFromRequest(final Post post, final List<ArticleRequest> articles) {
        post.getArticles().clear();
        articles.forEach(art -> {
            Article article = new Article();
            BeanUtils.copyProperties(art, article);

            article.setPost(post);
            article.getParagraphs().addAll(art.getParagraphs());

            article = articleRepository.save(article);
            post.getArticles().add(article);
        });
    }

    private Theme getThemeById(Long themeId) {
        return themeRepository.findById(themeId)
                .orElseThrow(() -> new NotFoundException("Theme id " + themeId + " does not exists"));
    }

}
