package recipes;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @PostMapping("/new")
    AddResponse add(@Valid @RequestBody Recipe recipe) {
        return new AddResponse(recipeService.add(recipe));
    }

    @GetMapping("/{id}")
    Recipe get(@PathVariable int id) {
        return recipeService.get(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping
    List<Recipe> list() {
        var logger = LoggerFactory.getLogger(RecipeController.class);
        return recipeService.list();
    }

    @GetMapping("/search")
    List<Recipe> listByIngredientAndDescription(@RequestParam(required = false) String category, @RequestParam(required = false) String name) {
        System.out.println("SEARCH");
        if (category != null && name != null)
            throw new BadRequest();
        if (category == null && name == null)
            throw new BadRequest();
        if (category != null)
            return recipeService.findByCategory(category);
        return recipeService.findByName(name);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable int id) {
        if (!recipeService.delete(id)) {
            throw new NotFoundException();
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@Valid @RequestBody Recipe recipe, @PathVariable long id) {
        recipe.setId(id);
        if (!recipeService.update(recipe))
            throw new NotFoundException();
    }

    record AddResponse(long id) {
        AddResponse(Recipe recipe) {
            this(recipe.getId());
        }
    }

    record RecipeRequest(
            @NotBlank String name,
            @NotBlank String description,
            @NotEmpty List<String> ingredients,
            @NotEmpty List<String> directions) {
    }
}
