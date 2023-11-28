package recipes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {

    @NotBlank String name;

    @NotBlank String description;
    @NotBlank String category;
    LocalDateTime date = LocalDateTime.now();

    @ElementCollection
    @NotEmpty List<String> ingredients;

    @ElementCollection
    @NotEmpty List<String> directions;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
