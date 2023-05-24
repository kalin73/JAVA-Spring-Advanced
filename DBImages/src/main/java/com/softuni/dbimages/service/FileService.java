package com.softuni.dbimages.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softuni.dbimages.model.FileDownloadModel;
import com.softuni.dbimages.model.FileEntity;
import com.softuni.dbimages.repository.FileRepository;

@Service
public class FileService {
	private final FileRepository fileRepository;

	public FileService(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

	public long saveFile(MultipartFile file) throws IOException {
		FileEntity fileEntity = new FileEntity().setName(file.getOriginalFilename())
				.setContentType(file.getContentType()).setData(file.getBytes());

		return this.fileRepository.save(fileEntity).getId();

	}

	public FileDownloadModel getFileById(long fileId) {
		var file = fileRepository.findById(fileId).orElseThrow();

		return new FileDownloadModel(file.getData(), file.getContentType(), file.getName());
	}
}
