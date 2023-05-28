package com.softuni.pathfinder.domain.dtos.binding;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.softuni.pathfinder.domain.enums.Level;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RouteAddForm {
	@Size(min = 4, max = 20)
	private String name;

	@NotNull
	private MultipartFile image;

	@Size(min = 20)
	private String description;

	@NotNull
	private Level level;

	@NotNull
	private String video;

	@NotNull
	private Set<String> categories;

	public RouteAddForm() {

	}

	public String getName() {
		return name;
	}

	public RouteAddForm setName(String name) {
		this.name = name;
		return this;
	}

	public MultipartFile getImage() {
		return image;
	}

	public RouteAddForm setImage(MultipartFile image) {
		this.image = image;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public RouteAddForm setDescription(String description) {
		this.description = description;
		return this;
	}

	public Level getLevel() {
		return level;
	}

	public RouteAddForm setLevel(Level level) {
		this.level = level;
		return this;
	}

	public String getVideo() {
		return video;
	}

	public RouteAddForm setVideo(String video) {
		this.video = video;
		return this;
	}

	public Set<String> getCategories() {
		return categories;
	}

	public RouteAddForm setCategories(Set<String> categories) {
		this.categories = categories;
		return this;
	}
}
