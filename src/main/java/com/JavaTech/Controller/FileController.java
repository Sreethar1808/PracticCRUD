package com.JavaTech.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.JavaTech.Service.FileService;

@RestController
@RequestMapping("/image")
public class FileController {
	
	@Autowired
	FileService fileservice;
	
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file)
	{
		try
		{
		fileservice.uploadFile(file);
		return ResponseEntity.ok("File uploaded Successfully: "+file.getOriginalFilename());
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FIle upload failed");
			
		}
		
	}
	
	@GetMapping("/{name}")
	public byte[] getFile(@PathVariable String name)
	{
		byte[] file = fileservice.downloadFile(name);
		return file;
	}

}
