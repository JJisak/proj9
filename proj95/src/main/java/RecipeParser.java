import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeParser {
    public List<Recipe> parseRecipes(String filePath) throws IOException {
        List<Recipe> recipes = new ArrayList<>();

        FileReader fileReader = new FileReader(filePath);
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim());

        for (CSVRecord csvRecord : csvParser) {
            Recipe recipe = new Recipe();
            recipe.setCookingMinutes(Integer.valueOf(csvRecord.get("Cooking Minutes")));
            recipe.setDairyFree(Boolean.valueOf(csvRecord.get("Dairy Free")));
            recipe.setGlutenFree(Boolean.valueOf(csvRecord.get("Gluten Free")));
            recipe.setInstructions(csvRecord.get("Instructions"));
            recipe.setPreparationMinutes(Double.valueOf(csvRecord.get("Preparation Minutes")));
            recipe.setPricePerServing(Double.valueOf(csvRecord.get("Price Per Serving")));
            recipe.setReadyInMinutes(Integer.valueOf(csvRecord.get("Ready In Minutes")));
            recipe.setServings(Integer.valueOf(csvRecord.get("Servings")));
            recipe.setSpoonacularScore(Double.valueOf(csvRecord.get("Spoonacular Score")));
            recipe.setTitle(csvRecord.get("Title"));
            recipe.setVegan(Boolean.valueOf(csvRecord.get("Vegan")));
            recipe.setVegetarian(Boolean.valueOf(csvRecord.get("Vegetarian")));

            recipes.add(recipe);
        }

        csvParser.close();
        return recipes;
    }
}
