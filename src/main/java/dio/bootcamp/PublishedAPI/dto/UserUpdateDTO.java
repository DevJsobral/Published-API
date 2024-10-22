package dio.bootcamp.PublishedAPI.dto;

import dio.bootcamp.PublishedAPI.models.Feature;
import dio.bootcamp.PublishedAPI.models.News;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserUpdateDTO {

    private Long id;
    private String name;
    private List<Feature> features;
    private List<News> news;
}
