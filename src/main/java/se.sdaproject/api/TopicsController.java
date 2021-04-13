package se.sdaproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicsController {

    TopicsRepository topicsRepository;
    ArticleRepository articleRepository;

    public TopicsController(TopicsRepository topicsRepository, ArticleRepository articleRepository) {
        this.topicsRepository = topicsRepository;
        this.articleRepository = articleRepository;
    }

    @PostMapping("topics")
    public ResponseEntity<Topics> createTopics(@RequestBody Topics topics){
        topicsRepository.save(topics);
        return ResponseEntity.status(HttpStatus.CREATED).body(topics);

    }

    @PostMapping("topics/{topicsId}/article/{articleId}")
    public ResponseEntity<Topics> createArticletopic(@PathVariable Long topicsId, @PathVariable Long articleId){
        Topics topics = topicsRepository.findById(topicsId).orElseThrow(ResourceNotFoundException::new);
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        topics.getArticleTopic().add(article);
        topicsRepository.save(topics);
        return ResponseEntity.status(HttpStatus.CREATED).body(topics);
    }

    @GetMapping("topics/{topicsId}")
    public ResponseEntity<List<Article>> listArticletopic(@PathVariable Long topicsId){
        Topics topics = topicsRepository.findById(topicsId).orElseThrow(ResourceNotFoundException::new);
        List<Article> articleTopic = topics.getArticleTopic();
        return ResponseEntity.ok(articleTopic);
}

    @DeleteMapping("topics/{topicsId}/articleTopic/{articleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delectArticleTopic(@PathVariable Long topicsId, @PathVariable Long articleId){
        Topics topics = topicsRepository.findById(topicsId).orElseThrow(ResourceNotFoundException::new);
        Article article = articleRepository.findById(topicsId).orElseThrow(ResourceNotFoundException::new);
        if(topics.getArticleTopic().contains(article)) {
            topics.getArticleTopic().remove(article);
            topicsRepository.save(topics);
        } else {
            throw new ResourceNotFoundException();
        }
    }
    @DeleteMapping("topics/{topicsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTopics(@PathVariable Long topicsId) {
        Topics topics = topicsRepository.findById(topicsId).orElseThrow(ResourceNotFoundException::new);
        topicsRepository.delete(topics);
    }

}
