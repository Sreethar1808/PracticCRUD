package com.JavaTech.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JavaTech.Entity.FileData;


@Repository
public interface FileRepository extends JpaRepository<FileData, Long>{
    
	Optional<FileData> findByName(String fileName);
}
