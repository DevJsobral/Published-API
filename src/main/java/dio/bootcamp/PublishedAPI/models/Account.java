package dio.bootcamp.PublishedAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "tab_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String number;

    @Column(unique = true)
    private String agency;

    private BigDecimal balance;

    @Column(name = "acc_limit")
    private BigDecimal limit;
}
