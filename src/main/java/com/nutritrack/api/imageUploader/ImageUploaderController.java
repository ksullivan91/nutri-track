package com.nutritrack.api.imageUploader;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/images")
public class ImageUploaderController {

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            return ResponseEntity.badRequest().body("No image file provided.");
        }
        try {
            String uploadedImageUrl = ImageUploader.uploadImageToCloudinary(imageFile.getBytes());
            if (uploadedImageUrl.isEmpty()) {
                return ResponseEntity.internalServerError().body("Failed to upload image.");
            }
            return ResponseEntity.ok("Image uploaded successfully! URL: " + uploadedImageUrl);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error uploading image: " + e.getMessage());
        }
    }
}
