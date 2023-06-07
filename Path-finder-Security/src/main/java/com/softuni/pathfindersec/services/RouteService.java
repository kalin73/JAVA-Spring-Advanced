package com.softuni.pathfindersec.services;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.softuni.pathfindersec.domain.dtos.binding.RouteAddForm;
import com.softuni.pathfindersec.domain.dtos.model.CategoryModel;
import com.softuni.pathfindersec.domain.dtos.model.RouteModel;
import com.softuni.pathfindersec.domain.dtos.view.RoutePartialViewModel;
import com.softuni.pathfindersec.domain.entities.Category;
import com.softuni.pathfindersec.domain.entities.Picture;
import com.softuni.pathfindersec.domain.entities.Route;
import com.softuni.pathfindersec.domain.entities.UserEntity;
import com.softuni.pathfindersec.domain.enums.CategoryName;
import com.softuni.pathfindersec.repositories.RouteRepository;

@Service
public class RouteService {
	private final RouteRepository routeRepository;
	private final ModelMapper modelMapper;
	private final UserService userService;
	private final CategoryService categoryService;
	private final ImageCloudService imageCloudService;

	public RouteService(RouteRepository routeRepository, ModelMapper modelMapper, UserService userService,
			CategoryService categoryService, ImageCloudService imageCloudService) {
		this.routeRepository = routeRepository;
		this.modelMapper = modelMapper;
		this.userService = userService;
		this.categoryService = categoryService;
		this.imageCloudService = imageCloudService;
	}

	public RoutePartialViewModel getMostCommented() {
		final Route route = this.routeRepository.findMostCommented().orElseThrow(NoSuchElementException::new).get(0);

		return RoutePartialViewModel.fromRoute(route);

	}

	public List<RoutePartialViewModel> getAllRouts() {
		return this.routeRepository.findAll().stream().map(RoutePartialViewModel::fromRoute).toList();
	}

	public void addNewRoute(RouteAddForm routeAddForm, String authorName) throws IOException {
		Route route = this.modelMapper.map(routeAddForm, Route.class);

		Set<Category> categories = routeAddForm.getCategories().stream().map(
				cm -> this.modelMapper.map(this.categoryService.findByName(CategoryName.valueOf(cm)), Category.class))
				.collect(Collectors.toSet());

		route.setAuthor(this.modelMapper.map(this.userService.findByUsername(authorName), UserEntity.class))
				.setCategories(categories);

		addPicture(route, routeAddForm.getImage());

		this.routeRepository.saveAndFlush(route);

	}

	private void addPicture(Route route, MultipartFile file) {
		String pictureUrl = imageCloudService.saveImage(file);

		Picture picture = new Picture();
		picture.setAuthor(route.getAuthor()).setRoute(route).setTitle(file.getOriginalFilename()).setUrl(pictureUrl);

		route.setPictures(Collections.singleton(picture));
	}

	@Transactional
	public RouteModel findById(Long id) {
		return this.modelMapper.map(this.routeRepository.findById(id).orElseThrow(NoSuchElementException::new),
				RouteModel.class);
	}

	public List<RoutePartialViewModel> findAllByCategoryName(String categoryName) {
		CategoryModel categoryModel = this.categoryService.findByName(CategoryName.valueOf(categoryName));

		return this.routeRepository.findAllByCategoriesContains(this.modelMapper.map(categoryModel, Category.class))
				.orElseThrow(NoSuchElementException::new).stream().map(RoutePartialViewModel::fromRoute).toList();
	}
}
