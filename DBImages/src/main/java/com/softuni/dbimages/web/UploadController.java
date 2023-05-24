package com.softuni.dbimages.web;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softuni.dbimages.model.FileUploadModel;
import com.softuni.dbimages.service.FileService;

@Controller
@RequestMapping
public class UploadController {
	private final FileService fileService;

	public UploadController(FileService fileService) {
		this.fileService = fileService;
	}

	@GetMapping("/upload")
	public String getUpload() {
		return "upload";
	}

	@PostMapping("/upload")
	public String upload(FileUploadModel uploadModel) throws IOException {

		return "redirect:/show" + fileService.saveFile(uploadModel.getImg());
	}
}
