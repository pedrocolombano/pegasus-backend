package br.com.lacostech.pegasusbackend.services.exceptions;

public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String msg) {
        super(msg);
    }

}
