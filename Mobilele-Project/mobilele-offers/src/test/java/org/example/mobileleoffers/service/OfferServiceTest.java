package org.example.mobileleoffers.service;

import org.example.mobileleoffers.model.dto.AddOfferDTO;
import org.example.mobileleoffers.model.dto.OfferDTO;
import org.example.mobileleoffers.model.entity.OfferEntity;
import org.example.mobileleoffers.model.entity.UserEntity;
import org.example.mobileleoffers.model.enums.Engine;
import org.example.mobileleoffers.model.enums.Transmission;
import org.example.mobileleoffers.repository.OfferRepository;
import org.example.mobileleoffers.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {
    private OfferService offerService;

    @Mock
    private OfferRepository offerRepository;

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<OfferEntity> offerEntityArgumentCaptor;


    @BeforeEach
    public void setUp() {
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setOfferId(UUID.randomUUID())
                .setEngine(Engine.DIESEL)
                .setDescription("Very fast")
                .setTransmission(Transmission.AUTOMATIC)
                .setPrice(BigDecimal.valueOf(100000))
                .setYear(2008)
                .setMileage(200000)
                .setImageUrl("https://www.topgear.com/sites/default/files/2022/09/1-BMW-3-Series.jpg?w=892&h=502")
                .setSeller(new UserEntity());

        Mockito.when(offerRepository.save(any())).thenReturn(offerEntity);
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(new UserEntity()));
        offerService = new OfferService(offerRepository, userRepository);
    }

    @Test
    public void testAddOffer() {
        AddOfferDTO offerDTO = new AddOfferDTO("car",
                BigDecimal.valueOf(100000),
                Engine.DIESEL,
                Transmission.AUTOMATIC,
                2008,
                200000,
                "Very fast",
                "https://www.topgear.com/sites/default/files/2022/09/1-BMW-3-Series.jpg?w=892&h=502"
        );

        OfferDTO result = offerService.addOffer(offerDTO, "kalin@abv.bg");

        Mockito.verify(offerRepository).save(offerEntityArgumentCaptor.capture());

        OfferEntity offerEntity = offerEntityArgumentCaptor.getValue();

        assertEquals(Engine.DIESEL, offerEntity.getEngine());
        assertEquals(Transmission.AUTOMATIC, result.transmission());
        assertEquals(new BigDecimal("100000"), offerEntity.getPrice());
        assertEquals("100000", result.price());
    }
}
