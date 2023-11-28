package recipes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class RecipeService {
    private final RecipeRepository repository;

    RecipeService(RecipeRepository repository) {

        this.repository = repository;
    }

    synchronized int add(Recipe recipe) {
        Recipe result = repository.save(recipe);
        return Math.toIntExact(result.getId());
    }

    List<Recipe> list() {
        return repository.findBy();
    }

    List<RecipeRepository.NaamEnDesc> listSome(String desc, String ingredient) {
        return repository.findNaamEnDescBy();
    }

    Optional<Recipe> get(int id) {
        return repository.findById((long) id);
    }

    boolean delete(int id) {
        if (repository.existsById((long) id)) {
            repository.deleteById((long) id);
            return true;
        }
        return false;
    }

    public List<Recipe> findByCategory(String category) {
        return repository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> findByName(String name) {
        return repository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

    public boolean update(Recipe recipe) {
        if (repository.existsById(recipe.getId())) {
            recipe.setDate(LocalDateTime.now());
            repository.save(recipe);
            return true;
        }
        return false;
    }
}
