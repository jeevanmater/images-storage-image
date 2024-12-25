package com.nisum.image_storage_service.servic;

import com.nisum.image_storage_service.entity.ImageData;
import com.nisum.image_storage_service.repo.StorageRepository;
import com.nisum.image_storage_service.utils.ImageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageRepository storageRepository;

    @Transactional
    public String uploadImage(MultipartFile file) throws IOException {
        System.out.println("This is line....!");
        ImageData imageData = storageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build());
        return "File uploaded successfully with name: " + file.getOriginalFilename();
    }

    @Transactional
    public byte[] downloadImage(String fileName) {
        Optional<ImageData> imageData = storageRepository.findByName(fileName);
        return ImageUtils.decompressImage(imageData.get().getImageData());


    }
}
