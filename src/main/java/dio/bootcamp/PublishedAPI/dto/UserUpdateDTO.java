package dio.bootcamp.PublishedAPI.dto;

import dio.bootcamp.PublishedAPI.models.User;


public record UserUpdateDTO(Long id,String name, String userName, String password)
{
    public UserUpdateDTO(User model) {
        this(model.getId(), model.getName(), model.getUsername(), model.getPassword());
    }

    public User toModel() {
        User model = new User();
        model.setId(this.id);
        model.setName(this.name);
        model.setUsername(this.userName);
        model.setPassword(this.password);
        return model;
    }
}

