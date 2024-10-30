package dio.bootcamp.PublishedAPI.controllers;

import dio.bootcamp.PublishedAPI.dto.OperationRequestDTO;
import dio.bootcamp.PublishedAPI.dto.OperationResponseDTO;
import dio.bootcamp.PublishedAPI.dto.TransferOperationRequestDTO;
import dio.bootcamp.PublishedAPI.models.User;
import dio.bootcamp.PublishedAPI.service.OperationsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operations")
public class OperationsController {

    private final OperationsService operationsService;

    public OperationsController(OperationsService operationsService){
        this.operationsService = operationsService;
    }

    @PutMapping("/withdraw")
    @Operation(summary = "Withdraw", description = "Withdraw from a user account based on the Username, password and value expected to withdrawn")
    public ResponseEntity<OperationResponseDTO> withdraw(@RequestBody OperationRequestDTO operationRequestDTO) {
        operationsService.withdraw(operationRequestDTO.userName(), operationRequestDTO.password(), operationRequestDTO.value());
        User userWithdrawn = operationsService.findByUsername(operationRequestDTO.userName());
        OperationResponseDTO operationResponse = new OperationResponseDTO(userWithdrawn);
        return ResponseEntity.ok(operationResponse);
    }

    @PutMapping("/deposit")
    @Operation(summary = "Deposit", description = "Deposit a certain value in a user account based on the Username, password and value expected to be deposited")
    public ResponseEntity<OperationResponseDTO> deposit(@RequestBody OperationRequestDTO operationRequestDTO) {
        operationsService.deposit(operationRequestDTO.userName(), operationRequestDTO.password(), operationRequestDTO.value());
        User userDeposited = operationsService.findByUsername(operationRequestDTO.userName());
        OperationResponseDTO operationResponse = new OperationResponseDTO(userDeposited);
        return ResponseEntity.ok(operationResponse);
    }

    @PutMapping("/transfer")
    @Operation(summary = "Transfer", description = "Transfer a certain value between two different accounts based on the Username, password and value expected to be deposited")
    public ResponseEntity<OperationResponseDTO> transfer(@RequestBody TransferOperationRequestDTO transferOperationRequestDTO) {
        operationsService.transfer(transferOperationRequestDTO.userName(),
                transferOperationRequestDTO.password(),
                transferOperationRequestDTO.balance(),
                transferOperationRequestDTO.destinyAccountNumber());
        User userTransfer = operationsService.findByUsername(transferOperationRequestDTO.userName());
        OperationResponseDTO operationResponse = new OperationResponseDTO(userTransfer);
        return ResponseEntity.ok(operationResponse);
    }


}
