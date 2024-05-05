package practice.springboot.cards.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practice.springboot.cards.constants.CardsConstants;
import practice.springboot.cards.dto.CardsDto;
import practice.springboot.cards.dto.ResponseDto;
import practice.springboot.cards.service.ICardsService;

@RestController
@RequestMapping("/card")
@AllArgsConstructor
@Validated
public class CardsController {
    private ICardsService iCardsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                  String mobileNumber){

        iCardsService.createCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201,CardsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCard(@Valid @RequestParam
                                                  @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                  String mobileNumber){
        CardsDto cardsDto = iCardsService.fetchCardDetails(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardsDto cardsDto){

        boolean isUpdate = iCardsService.updateCard(cardsDto);

        if(isUpdate){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
        }

    }

    @DeleteMapping("/delete")
    public  ResponseEntity<ResponseDto> deleteCard(@Valid @RequestParam
                                                       @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                       String mobileNumber){
        boolean isDelete = iCardsService.deleteCards(mobileNumber);

        if(isDelete){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        }
    }


}
