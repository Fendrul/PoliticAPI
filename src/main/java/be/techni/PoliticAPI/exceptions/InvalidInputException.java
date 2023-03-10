package be.techni.PoliticAPI.exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }

    //TODO implement the exception inside the repositories
}
