package org.example.mobileleoffers.web;

import org.example.mobileleoffers.model.dto.OfferDTO;
import org.example.mobileleoffers.service.OfferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/offers")
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public ResponseEntity<PagedModel<OfferDTO>> getAllOffers(@PageableDefault(
            size = 3,
            sort = "id",
            direction = Sort.Direction.DESC
    ) Pageable pageable) {
        return ResponseEntity.ok(this.offerService.getAllOffers(pageable));
    }
}
