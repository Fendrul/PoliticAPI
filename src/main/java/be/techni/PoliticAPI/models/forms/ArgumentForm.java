package be.techni.PoliticAPI.models.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArgumentForm {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private List<String> sources;
    @NotNull
    private List<Long> categoriesId;
    private Long answerTo;
}
