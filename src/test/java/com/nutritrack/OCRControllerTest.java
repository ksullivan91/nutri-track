package com.nutritrack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;

import com.nutritrack.api.ExtractNutritionFacts.NutritionFactsOCR;
import com.nutritrack.api.imageUploader.ImageUploader;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NutritionFactsOCR.class)
public class OCRControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUploadAndExtractText() throws Exception {
        when(NutritionFactsOCR.getNutritionalFacts("http://cloudinary.com/fakeImageUrl")).thenReturn(null);
    }

    @Test
    public void testUploadFailure() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile(
            "image", 
            "test.jpg", 
            MediaType.IMAGE_JPEG_VALUE, 
            "Test image content".getBytes()
        );

        // Mock the behavior to simulate a failure in uploading
        when(ImageUploader.uploadImageToCloudinary(mockFile.getBytes())).thenReturn(null);

        // Perform the post request
        mockMvc.perform(MockMvcRequestBuilders.multipart("/ocr/upload-extract")
                .file(mockFile)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().string("Failed to upload image."));
    }
}
