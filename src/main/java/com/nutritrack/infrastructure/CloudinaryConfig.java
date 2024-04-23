package com.nutritrack.infrastructure;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;


public class CloudinaryConfig {
    private static Cloudinary cloudinary;

    static {
        Dotenv dotenv = Dotenv.load();
        Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        cloudinary.config.secure = true;
    }

    public static Cloudinary getCloudinary() {
        return cloudinary;
    }
}
