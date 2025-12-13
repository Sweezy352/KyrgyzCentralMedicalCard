package com.example.kyrgyzstancentralmedicalcard.repository;

import com.example.kyrgyzstancentralmedicalcard.entity.NewsPictureFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsPictureFilesRepository extends JpaRepository<NewsPictureFiles, Long> {
    Optional<NewsPictureFiles> findByOriginalFileName(String originalFileName);
}
