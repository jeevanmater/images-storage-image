package com.nisum.image_storage_service.repo;

import com.nisum.image_storage_service.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<ImageData,Long> {
  Optional<ImageData> findByName(String name);
}
