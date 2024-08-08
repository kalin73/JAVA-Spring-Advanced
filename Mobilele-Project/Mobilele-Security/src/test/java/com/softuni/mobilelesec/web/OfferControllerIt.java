package com.softuni.mobilelesec.web;

import com.softuni.mobilelesec.repositories.OfferRepository;
import com.softuni.mobilelesec.services.EmailService;
import com.softuni.mobilelesec.services.OfferService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "pesho@abv.bg", roles = {"USER", "ADMIN"})
public class OfferControllerIt {
    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @MockBean
    private OfferService offerService;

    @BeforeEach
    public void setup() {
        doNothing().when(emailService).sendRegistrationEmail(null, null);
        doNothing().when(offerService).addOffer(any());
    }

    @AfterEach
    public void clean() {
        offerRepository.deleteAll();
    }

    @Test
    public void addOfferTest() throws Exception {
        mockMvc.perform(post("/offers/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("model", "VW")
                        .param("price", "10000")
                        .param("engine", "GASOLINE")
                        .param("transmission", "MANUAL")
                        .param("year", "2008")
                        .param("mileage", "200000")
                        .param("description", "Fast!")
                        .param("imageUrl", "url")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

}
