package com.JavaTech.Service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.JavaTech.Entity.FileData;
import com.JavaTech.Repository.FileRepository;

@Service
public class FileService {

	@Autowired
	private FileRepository fileRepo;
	
	
	public String uploadFile(MultipartFile file) throws IOException {

		FileData fileData = new FileData();
		fileData.setName(file.getOriginalFilename());
		fileData.setFileType(file.getContentType());
		fileData.setFileData(file.getBytes());
		FileData savedData = fileRepo.save(fileData);
		
		if(savedData!=null)
		{
			return "fileUploaded Successfully";
		}
		return null;
	}
	
	public byte[] downloadFile(String fileName)
	{
		Optional<FileData> data= fileRepo.findByName(fileName);
		return data.get().getFileData();
	}
}
