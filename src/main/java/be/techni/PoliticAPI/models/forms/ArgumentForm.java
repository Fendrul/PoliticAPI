package be.techni.PoliticAPI.models.forms;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private Long clientId;
    private List<String> sources;
    private List<Long> categoriesId;
    private Long answerTo;
}
