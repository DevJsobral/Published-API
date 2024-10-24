package dio.bootcamp.PublishedAPI.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import dio.bootcamp.PublishedAPI.util.CartaoUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "tab_card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String number;

    @Column(precision = 13, scale = 2, name = "card_limit")
    private BigDecimal limit;

    @PrePersist
    public void gerarNumeroCartao() {
        this.number = CartaoUtils.gerarNumeroCartao();
    }
}
