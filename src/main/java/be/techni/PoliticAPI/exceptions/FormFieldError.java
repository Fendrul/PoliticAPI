package be.techni.PoliticAPI.exceptions;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class FormFieldError extends RuntimeException {
    BindingResult result;

    public FormFieldError(BindingResult result) {
        this.result = result;
    }
}
