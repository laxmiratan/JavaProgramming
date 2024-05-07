package practice.springboot.loans.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class LoansDto {

    @NotEmpty(message = "Mobile number can't be null pr empty")
    @Pattern(regexp = "(^$[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "Loan number can't be null pr empty")
    @Pattern(regexp = "(^$[0-9]{12})", message = "Loan number must be 12 digits")
    private String loanNumber;

    @NotEmpty(message = "Loan type can't be null pr empty")
    private String loanType;

    @Positive(message = "Total loan amount paid should be greater than zero")
    private int totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    private int amountPaid;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    private int outstandingAmount;
}
