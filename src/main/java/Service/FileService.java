package Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Entity.FileData;
import Repository.FileRepository;

@Service
public class FileService {

	@Autowired
	private FileRepository fileRepo;
	
	
	public String uploadFile(MultipartFile file)
	{
		FileData savedData = fileRepo.save(FileData.builder()
				.name(file.getOriginalFilename())
				.fileType(file.getContentType())
				.fileData(file.getBytes())
				.build());
		
		if(savedData!=null)
		{
			return "fileUploaded Successfully";
		}
	}
	
	public byte[] downloadFile(String fileName)
	{
		Optional<FileData> data= fileRepo.findByName(fileName);
		return data.get().getfileData();
	}
}
