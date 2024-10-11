package dio.bootcamp.PublishedAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "tab_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne
    private Account account;

    @OneToOne
    private Card card;

    @OneToMany
    private List<Feature> features;

    @OneToMany
    private List<News> news;
}
