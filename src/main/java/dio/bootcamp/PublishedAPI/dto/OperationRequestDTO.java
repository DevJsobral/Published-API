package dio.bootcamp.PublishedAPI.dto;

import dio.bootcamp.PublishedAPI.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Schema(description = "Request body showing the fields to make the operations")
public record OperationRequestDTO(
        @Schema(description = "Username of the user performing the operation", example = "john_doe")
        String userName,
        @Schema(description = "Password of the user", example = "password123")
        String password,
        @Schema(description = "Value to be deposited, withdrawn, or transferred", example = "100.00")
        BigDecimal value
) {

    public OperationRequestDTO(User model) {
        this(model.getUsername(), model.getPassword(), model.getAccount().getBalance());
    }
}
