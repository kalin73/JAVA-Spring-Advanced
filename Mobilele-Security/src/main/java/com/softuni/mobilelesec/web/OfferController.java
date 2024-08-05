package com.softuni.mobilelesec.web;

import com.softuni.mobilelesec.domain.dtos.binding.OfferCreationDto;
import com.softuni.mobilelesec.domain.enums.Engine;
import com.softuni.mobilelesec.domain.enums.ModelCategory;
import com.softuni.mobilelesec.domain.enums.Transmission;
import com.softuni.mobilelesec.domain.user.MobileleUserDetails;
import com.softuni.mobilelesec.services.OfferService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PreAuthorize("@offerService.isOwner(#mobileleUserDetails, #id)")
    @DeleteMapping("/{id}")
    public String deleteOffer(@AuthenticationPrincipal MobileleUserDetails mobileleUserDetails, @PathVariable("id") UUID id) {
        offerService.deleteOfferByUUID(id);

        return "redirect:/offers/all";
    }

    @GetMapping("/all")
    public String getAllOffers(Model model, @PageableDefault(sort = "offerId", size = 3) Pageable pageable) {
        var allOffersPage = offerService.getAllOffers(pageable);

        model.addAttribute("offers", allOffersPage);

        return "offers";
    }

    @GetMapping("/{id}")
    public String getOfferById(@PathVariable(name = "id") UUID offerUUID, Model model,
                               @AuthenticationPrincipal MobileleUserDetails mobileleUserDetails) {
        model.addAttribute("offer", offerService.getOfferById(offerUUID));
        model.addAttribute("canDelete", offerService.isOwner(mobileleUserDetails, offerUUID));

        return "details";
    }

    @GetMapping("/add")
    public ModelAndView getOfferAddPage(ModelAndView modelAndView) {
        modelAndView.addObject("engines", Engine.values());
        modelAndView.addObject("models", ModelCategory.values());
        modelAndView.addObject("transmissions", Transmission.values());
        modelAndView.setViewName("offer-add");

        return modelAndView;
    }

    @PostMapping("/add")
    public String addOffer(OfferCreationDto creationDto) {
        this.offerService.addOffer(creationDto);

        return "redirect:/offers/all";
    }
}
