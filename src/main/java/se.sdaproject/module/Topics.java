package se.sdaproject;

import javax.persistence.*;
import java.util.List;

@Entity
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @ManyToMany
    private List<Article> articleTopic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticleTopic() {
        return articleTopic;
    }

    public void setArticleTopic(List<Article> articleTopic) {
        this.articleTopic = articleTopic;
    }
}
