package practice.springboot.loans.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import practice.springboot.loans.constants.LoansConstants;
import practice.springboot.loans.dto.ResponseDto;
import practice.springboot.loans.service.ILoansService;

@RestController
@AllArgsConstructor
@RequestMapping("loans")
@Validated
public class LoansController {

    private ILoansService iLoansService;

    @PostMapping("create")
    public ResponseEntity<ResponseDto> createLoan(@Valid @RequestParam
                                                       @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                  String mobileNumber){

        iLoansService.createLoan(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201,LoansConstants.MESSAGE_201));
    }
}
