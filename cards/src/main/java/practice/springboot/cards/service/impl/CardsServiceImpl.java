package practice.springboot.cards.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import practice.springboot.cards.constants.CardsConstants;
import practice.springboot.cards.dto.CardsDto;
import practice.springboot.cards.entity.Cards;
import practice.springboot.cards.exception.CardAlreadyExistsException;
import practice.springboot.cards.exception.ResourceNotFoundException;
import practice.springboot.cards.mapper.CardsMapper;
import practice.springboot.cards.repository.CardsRepository;
import practice.springboot.cards.service.ICardsService;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {

        Optional<Cards> optionalCard = cardsRepository.findByMobileNumber(mobileNumber);

        if(optionalCard.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }

        cardsRepository.save(createNewCard(mobileNumber));

    }

    private Cards createNewCard(String mobileNumber){
        Cards newCards = new Cards();

        long randomNumber = 100000000000L + new Random().nextInt(900000000);
        newCards.setCardNumber(Long.toString(randomNumber));
        newCards.setMobileNumber(mobileNumber);
        newCards.setCardType(CardsConstants.CREDIT_CARD);
        newCards.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCards.setAmountUsed(0);
        newCards.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);

        return newCards;
    }

    @Override
    public CardsDto fetchCardDetails(String mobileNumber) {

        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "MobileNumber", mobileNumber)
        );

        return CardsMapper.mapToCardsDto(cards, new CardsDto());

    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {

       Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
               () -> new ResourceNotFoundException("Card", "card number", cardsDto.getCardNumber())
       );

       CardsMapper.mapToCards(cardsDto, cards);
       cardsRepository.save(cards);

       return true;
    }

    @Override
    public boolean deleteCards(String mobileNumber) {

        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Mobile number", mobileNumber)
        );

        cardsRepository.delete(cards);
        return true;
    }


}
