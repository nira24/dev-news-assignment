package se.sdaproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentsController {

    CommentsRepository commentsRepository;
    ArticleRepository articleRepository;

    public CommentsController(CommentsRepository commentsRepository, ArticleRepository articleRepository) {
        this.commentsRepository = commentsRepository;
        this.articleRepository = articleRepository;
    }

    @PostMapping("/article/{articleId}/comments")
    public ResponseEntity<Comments> createComments(@PathVariable Long articleId, @RequestBody Comments comments) {
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        comments.setArticle(article);
        commentsRepository.save(comments);
        return ResponseEntity.status(HttpStatus.CREATED).body(comments);

    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Comments> deleteArticle(@PathVariable Long id){
        Comments comments = commentsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        commentsRepository.delete(comments);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comments> updateComments(@PathVariable Long id, @RequestBody Comments updatedComments) {
        Comments comments = commentsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedComments.setId(id);
        commentsRepository.save(updatedComments);
        return ResponseEntity.ok(updatedComments);
    }
}
