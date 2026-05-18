package com.example.moattravel.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.moattravel.entity.House;
import com.example.moattravel.form.HouseRegisterForm;
import com.example.moattravel.repository.HouseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HouseService {

	private final HouseRepository houseRepository;

	@Transactional
	public House create(HouseRegisterForm houseRegisterForm) {
	
		House house = new House();
		
		MultipartFile imageFile = houseRegisterForm.getImageFile();
		
		if(!imageFile.isEmpty()) {
			
			String imageName = imageFile.getOriginalFilename();
			
			String hashedimageName = generateNewName(imageName);
			
			Path filePath =
			Paths.get("src/main/resources/static/storage/" + hashedimageName);
			
			copyImageFile(imageFile, filePath);
			
			house.setImageName(hashedimageName);
			
			
		}

		house.setName(houseRegisterForm.getName());

		house.setDescription(houseRegisterForm.getDescription());

		house.setPrice(houseRegisterForm.getPrice());

		house.setCapacity(houseRegisterForm.getCapacity());

		house.setPostalCode(houseRegisterForm.getPostalCode());

		house.setAddress(houseRegisterForm.getAddress());

		house.setPhoneNumber(houseRegisterForm.getPhoneNumber());

		return houseRepository.save(house);

	}
	
	//UUIDをつかって生成したfile名を返す
	
	public String generateNewName(String fileName) {
				
		String[] fileNames = fileName.split("\\.");
		
		for(int i = 0; i<fileNames.length - 1; i++) {
			fileNames[i]= UUID.randomUUID().toString();
			
		}
		
		String hashedFileName =String.join(".", fileNames);
		
		return hashedFileName;
		
	}
	//画像ファイルを指定してファイルにコピーする
	public void copyImageFile(MultipartFile imageFile, Path filePath) {
		
		try {
			Files.copy(imageFile.getInputStream(), filePath);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
