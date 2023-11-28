package recipes;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<NaamEnDesc> findNaamEnDescBy();
    List<Recipe> findByNameContainingIgnoreCaseOrderByDateDesc(String name);
    List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);
    List<Recipe> findBy();
    record NaamEnDesc(String name, String description) {
    }

}


