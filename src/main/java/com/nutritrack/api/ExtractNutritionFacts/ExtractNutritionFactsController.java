package com.nutritrack.api.ExtractNutritionFacts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.nutritrack.api.imageUploader.ImageUploader;
import java.io.IOException;


@RestController
@RequestMapping("/nutritional-facts")
public class ExtractNutritionFactsController {

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("")
    public ResponseEntity<NutritionalInfo> uploadAndExtractText(@RequestParam("image") MultipartFile imageFile) {
        try {
            String imageUrl = ImageUploader.uploadImageToCloudinary(imageFile.getBytes());

            if (imageUrl == null || imageUrl.isEmpty()) {
                System.out.println("Failed to upload image.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            return ResponseEntity.ok(NutritionFactsOCR.getNutritionalFacts(imageUrl));

        } catch (IOException e) {
            System.err.println("Error in processing image or OCR: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
