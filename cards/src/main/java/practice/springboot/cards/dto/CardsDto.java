package practice.springboot.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(name = "Cards", description = "schema to hold card Info")
@Data
public class CardsDto {

    @NotEmpty(message = "mobile number can't be null or empty")
    @Pattern(regexp = "(^$[0-9]{10})", message = "Mobile Number must have 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "mobile number can't be null or empty")
    @Pattern(regexp = "(^$[0-9]{12})", message = "Card Number must have 12 digits")
    private String cardNumber;

    @NotEmpty(message = "Card type can't be null or empty")
    private String cardType;

    @Positive(message = "Total limit should be greater than zero")
    private int totalLimit;

    @PositiveOrZero(message = "amount used should be equal to or greater than zero")
    private int amountUsed;

    @PositiveOrZero(message = "Available amount should be equal to or greater than zero")
    private int availableAmount;
}
