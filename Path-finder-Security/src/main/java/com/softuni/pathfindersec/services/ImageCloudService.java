package com.softuni.pathfindersec.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.UUID;

@Service
public class ImageCloudService {
	private Cloudinary cloudinary;

	public ImageCloudService() {

		cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "dr59v2kfk", "api_key", "334577986788471",
				"api_secret", "X4I3OVg91omCLqdNmVv005YItd4"));
	}

	public String saveImage(MultipartFile multipartFile) {
		String imageId = UUID.randomUUID().toString();

		@SuppressWarnings("rawtypes")
		Map params = ObjectUtils.asMap("public_id", imageId, "overwrite", true, "resource_type", "image");

		File tmpFile = new File(imageId);
		try {
			Files.write(tmpFile.toPath(), multipartFile.getBytes());
			cloudinary.uploader().upload(tmpFile, params);
			Files.delete(tmpFile.toPath());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return String.format("https://res.cloudinary.com/dr59v2kfk/image/upload/v1678739665/" + imageId + "."
				+ getFileExtension(multipartFile.getOriginalFilename()));
	}

	private String getFileExtension(String filename) {
		return filename.substring(filename.lastIndexOf('.') + 1);
	}
}