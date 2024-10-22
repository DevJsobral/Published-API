package dio.bootcamp.PublishedAPI.dto;

import dio.bootcamp.PublishedAPI.models.Feature;
import dio.bootcamp.PublishedAPI.models.News;
import dio.bootcamp.PublishedAPI.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Getter
@Setter
public record UserUpdateDTO(
        Long id,
        String name,
        List<FeatureDTO> features,
        List<NewsDTO> news)
{
    public UserUpdateDTO(User model) {
        this(
                model.getId(),
                model.getName(),
                ofNullable(model.getFeatures()).orElse(emptyList()).stream().map(FeatureDTO::new).collect(toList()),
                ofNullable(model.getNews()).orElse(emptyList()).stream().map(NewsDTO::new).collect(toList())
        );
    }
    public User toModel() {
        User model = new User();
        model.setId(this.id);
        model.setName(this.name);
        model.setFeatures(ofNullable(this.features).orElse(emptyList()).stream().map(FeatureDTO::toModel).collect(toList()));
        model.setNews(ofNullable(this.news).orElse(emptyList()).stream().map(NewsDTO::toModel).collect(toList()));
        return model;
    }
}

