package com.softuni.mobilelesec.repositories;

import org.springframework.data.jpa.domain.Specification;

import com.softuni.mobilelesec.domain.dtos.model.SearchOfferDTO;
import com.softuni.mobilelesec.domain.entities.OfferEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class OfferSpecification implements Specification<OfferEntity> {

	private static final long serialVersionUID = 1L;
	private final SearchOfferDTO searchOfferDTO;

	public OfferSpecification(SearchOfferDTO searchOfferDTO) {
		this.searchOfferDTO = searchOfferDTO;
	}

	@Override
	public Predicate toPredicate(Root<OfferEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		Specification<OfferEntity> where = Specification.where(null);

		if (searchOfferDTO.getModel() != null && !searchOfferDTO.getModel().isEmpty()) {
			where = where.and(modelNameEqualsTo(searchOfferDTO.getModel()));
		}

		if (searchOfferDTO.getMinPrice() != null) {
			where = where.and(priceGreaterOrEqualThan(searchOfferDTO.getMinPrice()));
		}

		if (searchOfferDTO.getMaxPrice() != null) {
			where = where.and(priceLessOrEqualTo(searchOfferDTO.getMaxPrice()));
		}

		return where.toPredicate(root, query, cb);
	}

	private static Specification<OfferEntity> modelNameEqualsTo(String model) {
		return (r, q, b) -> b.and(b.equal(r.join("model").get("name"), model));
	}

	private static Specification<OfferEntity> priceGreaterOrEqualThan(int minPrice) {
		return (r, q, b) -> b.and(b.greaterThanOrEqualTo(r.get("price"), minPrice));
	}

	private static Specification<OfferEntity> priceLessOrEqualTo(int maxPrice) {
		return (r, q, b) -> b.and(b.lessThanOrEqualTo(r.get("price"), maxPrice));
	}
}