package recipes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private final RecipeRepository repository;

    Config(RecipeRepository repository) {

        this.repository = repository;
    }

    @Bean
    RecipeService getData() {
        return new RecipeService(repository);
    }
}
