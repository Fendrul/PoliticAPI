package be.techni.PoliticAPI.models.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ArgumentModificationForm {
    @NotBlank
    private String description;

    @NotBlank
    private Long argumentId;
}
