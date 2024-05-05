package practice.springboot.account.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name can't be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 to 30")
    private String name;

    @NotEmpty(message = "Email can't be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile no. must be 10 digit")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
