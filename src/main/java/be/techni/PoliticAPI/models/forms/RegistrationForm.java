package be.techni.PoliticAPI.models.forms;

import be.techni.PoliticAPI.models.entities.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistrationForm {

    @NotNull
    private String username;

    //TODO créer mon propre validator pour les mots de passe
    @NotNull
    private String password;

    public User toEntity() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
