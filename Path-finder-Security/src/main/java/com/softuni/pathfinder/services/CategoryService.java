package com.softuni.pathfinder.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.softuni.pathfinder.domain.dtos.model.CategoryModel;
import com.softuni.pathfinder.domain.enums.CategoryName;
import com.softuni.pathfinder.repositories.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;

	public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	public CategoryModel findByName(CategoryName categoryName) {
		return this.modelMapper.map(this.categoryRepository.findByName(categoryName), CategoryModel.class);
	}

}
