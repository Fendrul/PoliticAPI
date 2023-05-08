package be.techni.PoliticAPI.models.forms;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginForm {
    @NotNull
    private String username;
    @NotNull
    private String password;

}
