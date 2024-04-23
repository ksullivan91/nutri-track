package com.nutritrack.api.imageUploader;

import java.util.Map;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

public class ImageUploader {

  @SuppressWarnings("unchecked")
  public static String uploadImageToCloudinary(byte[] imageBytes) {
      Dotenv dotenv = Dotenv.load();
      Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
      cloudinary.config.secure = true;

      Map<String, Object> params1 = ObjectUtils.asMap(
          "use_filename", true,
          "unique_filename", false,
          "overwrite", true
      );

      try {
          Map<String, Object> uploadResult = cloudinary.uploader().upload(imageBytes, params1);
          return (String) uploadResult.get("secure_url");
      } catch (Exception e) {
          System.err.println("Error uploading image to Cloudinary: " + e.getMessage());
          return "";
      }
  }
}

