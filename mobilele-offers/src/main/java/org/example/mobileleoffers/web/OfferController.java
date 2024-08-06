package org.example.mobileleoffers.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.mobileleoffers.model.dto.AddOfferDTO;
import org.example.mobileleoffers.model.dto.OfferDTO;
import org.example.mobileleoffers.service.OfferService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/offers")
@Tag(name = "Offers", description = "The controller responsible for offer management.")
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public ResponseEntity<PagedModel<OfferDTO>> getAllOffers(Pageable pageable) {
        return ResponseEntity.ok(this.offerService.getAllOffers(pageable));
    }

    @ApiResponses(value =
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The offer details",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = OfferDTO.class)
                                    )
                            }),
                    @ApiResponse(responseCode = "404", description = "If the offer was not found.")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<OfferDTO> getOfferById(@PathVariable Long id) {
        return ResponseEntity.ok(this.offerService.getOfferById(id));
    }

    @Operation(
            security = @SecurityRequirement(name = "bearer-token")
    )
    @PostMapping
    public ResponseEntity<OfferDTO> createOffer(@RequestBody AddOfferDTO addOfferDTO, Principal principal) {
        var createdOffer = this.offerService.addOffer(addOfferDTO, principal.getName());

        return ResponseEntity.created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdOffer.id())
                        .toUri())
                .body(createdOffer);
    }

    @Operation(
            security = @SecurityRequirement(name = "bearer-token")
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<OfferDTO> deleteOffer(@PathVariable("id") UUID id) {
        this.offerService.deleteOfferById(id);

        return ResponseEntity.noContent().build();
    }
}
