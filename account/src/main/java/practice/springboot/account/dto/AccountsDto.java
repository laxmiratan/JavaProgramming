package practice.springboot.account.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account no. can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account no. must be 10 digit")
    private Long accountNumber;

    @NotEmpty(message = "Account type can't be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch address can't be null or empty")
    private String branchAddress;

}
