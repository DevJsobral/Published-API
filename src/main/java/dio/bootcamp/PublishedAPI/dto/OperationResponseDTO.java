package dio.bootcamp.PublishedAPI.dto;

import dio.bootcamp.PublishedAPI.models.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Response body showing the result of the operation with updated balance")
public record OperationResponseDTO(
        @Schema(description = "Username of the user", example = "john_doe")
        String username,
        @Schema(description = "Account number of the user", example = "1234567890")
        String accountNumber,
        @Schema(description = "Updated balance after operation", example = "900.00")
        BigDecimal balance
) {
    public OperationResponseDTO(User model){
        this(model.getUsername(), model.getAccount().getNumber(), model.getAccount().getBalance());
    }

}
