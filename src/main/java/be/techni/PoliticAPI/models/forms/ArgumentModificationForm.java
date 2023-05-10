package be.techni.PoliticAPI.models.forms;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ArgumentModificationForm {
    @NotBlank
    private String description;

    @NotBlank
    private String title;
}
