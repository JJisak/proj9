import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final List<Recipe> recipes;

    @Autowired
    public RecipeController(RecipeParser recipeParser) {
        try {
            recipes = recipeParser.parseRecipes("recipes.txt");
        } catch (IOException e) {
            // Handle the exception appropriately
            throw new RuntimeException("recipes.txt not found", e);
        }
    }

    @GetMapping("/gluten-free")
    public List<Recipe> getGlutenFreeRecipes() {
        return recipes.stream()
                .filter(Recipe::getGlutenFree)
                .collect(Collectors.toList());
    }

    @GetMapping("/vegan")
    public List<Recipe> getVeganRecipes() {
        return recipes.stream()
                .filter(Recipe::getVegan)
                .collect(Collectors.toList());
    }

    @GetMapping("/vegan-and-gluten-free")
    public List<Recipe> getVeganAndGlutenFreeRecipes() {
        return recipes.stream()
                .filter(recipe -> recipe.getVegan() && recipe.getGlutenFree())
                .collect(Collectors.toList());
    }

    @GetMapping("/vegetarian")
    public List<Recipe> getVegetarianRecipes() {
        return recipes.stream()
                .filter(Recipe::getVegetarian)
                .collect(Collectors.toList());
    }

    @GetMapping("/all-recipes")
    public List<Recipe> getAllRecipes() {
        return recipes;
    }
}
