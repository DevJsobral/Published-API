package dio.bootcamp.PublishedAPI.dto;

import dio.bootcamp.PublishedAPI.models.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Request body showing the fields to make the operations")
public record TransferOperationRequestDTO(
        @Schema(description = "Username of the user performing the operation", example = "john_doe")
        String userName,
        @Schema(description = "Password of the user", example = "password123")
        String password,
        @Schema(description = "Number of the destiny account", example = "123456")
        String destinyAccountNumber,
        @Schema(description = "Value to be transferred", example = "100.00")
        BigDecimal balance
) { }
