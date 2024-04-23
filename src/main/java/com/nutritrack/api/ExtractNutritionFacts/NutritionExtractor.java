package com.nutritrack.api.ExtractNutritionFacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NutritionExtractor {
  /**
   * Extracts nutritional information from a JSON response string.
   * 
   * @param jsonResponse A string containing the JSON data with nutritional text.
   * @return A NutritionalInfo object populated with extracted data.
   */
  public static NutritionalInfo extractNutritionalInfo(String jsonResponse, String imageUrl) {
      // Call another method to parse the text and extract nutritional data.
        return parseNutritionalText(jsonResponse, imageUrl);
  }

  /**
   * Parses the text for nutritional data and returns a populated NutritionalInfo object.
   *
   * @param text The text extracted from the JSON response, assumed to contain nutritional information.
   * @return A NutritionalInfo object with details about calories, fat, carbohydrates, protein, and serving size.
   */
  private static NutritionalInfo parseNutritionalText(String text, String imageUrl) {
        NutritionalInfo info = new NutritionalInfo();
        info.setCalories(extractNutrient(text, "Calories"));
        info.setFat(extractNutrient(text, "Total Fat"));
        info.setCarbohydrates(extractNutrient(text, "Total Carbohydrate"));
        info.setDietaryFiber(extractNutrient(text, "Dietary Fiber"));
        info.setSugars(extractNutrient(text, "Sugars"));
        info.setProtein(extractNutrient(text, "Protein"));
        info.setVitaminA(extractNutrient(text, "Vitamin A"));
        info.setVitaminC(extractNutrient(text, "Vitamin C"));
        info.setVitaminC(extractNutrient(text, "Vitamin D"));
        info.setCalcium(extractNutrient(text, "Calcium"));
        info.setIron(extractNutrient(text, "Iron"));
        info.setSodium(extractNutrient(text, "Sodium"));
        info.setPotassium(extractNutrient(text, "Potassium"));
        info.setServingSize(extractNutrient(text, "Serving Size"));
        info.setImageUrl(imageUrl);

        return info;
    }

  /**
   * Searches the provided text for a specified nutrient and extracts its numeric value.
   *
   * @param text The text to search within.
   * @param nutrientName The name of the nutrient to find.
   * @return A string containing the numeric value of the nutrient, or an empty string if not found.
   */
  private static String extractNutrient(String text, String nutrientName) {
        Pattern pattern = Pattern.compile(nutrientName + ".*?([0-9]+(\\.[0-9]+)?(g|mg|mcg|oz|ml|cup|cups)?)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return "";
    }

  /**
   * The main method to execute extraction from a sample JSON response.
   */
  public static void main(String[] args) {
        String jsonResponse = "{...}"; // Placeholder for actual JSON response.
        String imageUrl = "{...}"; // Placeholder for actual image URL.
        NutritionalInfo info = NutritionExtractor.extractNutritionalInfo(jsonResponse, imageUrl);
        System.out.println(info.toString()); // Print the extracted information in a readable format.
  }

}
