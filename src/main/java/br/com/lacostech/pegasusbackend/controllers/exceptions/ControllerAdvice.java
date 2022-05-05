package br.com.lacostech.pegasusbackend.controllers.exceptions;

import br.com.lacostech.pegasusbackend.services.exceptions.ForbiddenException;
import br.com.lacostech.pegasusbackend.services.exceptions.NotFoundException;
import br.com.lacostech.pegasusbackend.services.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public StandardError notFoundError(NotFoundException exception) {
        return new StandardError(exception.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public OAuthCustomError forbiddenError(ForbiddenException exception) {
        return new OAuthCustomError("Forbidden", exception.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public OAuthCustomError unauthorizedError(UnauthorizedException exception) {
        return new OAuthCustomError("Unauthorized", exception.getMessage());
    }

}
