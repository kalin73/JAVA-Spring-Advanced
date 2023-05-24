package com.softuni.dbimages.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.softuni.dbimages.service.FileService;

@Controller
public class DownloadController {
	private final FileService fileService;

	public DownloadController(FileService fileService) {
		this.fileService = fileService;
	}

	@GetMapping("/show/{fileId}")
	public String show(@PathVariable(name = "fileId") Long fileId, Model model) {
		model.addAttribute("fileId", fileId);
		
		return "show";
	}

	@GetMapping("/download/{fileId}")
	public HttpEntity<byte[]> download(@PathVariable(name = "fileId") Long fileId) {
		var fileDownloadModel = fileService.getFileById(fileId);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(MimeTypeUtils.parseMimeType(fileDownloadModel.getContentType())));
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileDownloadModel.getFileName());
		headers.setContentLength(fileDownloadModel.getFileData().length);

		return new HttpEntity<>(fileDownloadModel.getFileData(), headers);
	}
}
