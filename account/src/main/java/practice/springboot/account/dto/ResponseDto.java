package practice.springboot.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data @AllArgsConstructor
public class ResponseDto {

    private String statusCode;
    private String statusMsg;

}
