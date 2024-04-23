package com.nutritrack.api.ExtractNutritionFacts;

import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NutritionFactsOCR {
  public static NutritionalInfo getNutritionalFacts(String imageUrl) {
    try {
        // Perform OCR to extract text from the image at the provided URL
        String ocrResult = performOCR(imageUrl);
        // Extract nutrient data from the OCR result

        System.out.println("OCR Result: " + ocrResult);
        return extractNutrients(ocrResult, imageUrl);
    } catch (Exception e) {
        System.err.println("Error processing image or OCR: " + e.getMessage());
        return null;
    }
}

private static String performOCR(String imageUrl) throws Exception {
    URL url = new URL("https://api.ocr.space/parse/imageurl?apikey=helloworld&url=" + imageUrl);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");

    try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        return content.toString();
    } catch (IOException e) {
        System.err.println("Error during OCR HTTP request: " + e.getMessage());
        throw e;  // Re-throw to handle it in the calling method
    }
}

private static NutritionalInfo extractNutrients(String ocrText, String imageUrl) {
    // Assuming NutritionExtractor and NutritionalInfo are properly implemented
    return NutritionExtractor.extractNutritionalInfo(ocrText, imageUrl);
}
}
