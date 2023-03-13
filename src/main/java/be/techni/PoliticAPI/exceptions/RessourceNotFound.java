package be.techni.PoliticAPI.exceptions;

public class RessourceNotFound extends RuntimeException {
    public RessourceNotFound(String message) {
        super(message);
    }
}
