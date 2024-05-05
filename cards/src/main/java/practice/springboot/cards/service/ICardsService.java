package practice.springboot.cards.service;

import practice.springboot.cards.dto.CardsDto;

public interface ICardsService {

    void createCard(String mobileNumber);

    CardsDto fetchCardDetails(String mobileNumber);

    boolean updateCard(CardsDto cardsDto);

    boolean deleteCards(String mobileNumber);

}
