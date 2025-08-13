package org.co.WorkSearch.controller;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class EntityControllerAdvice {
    @ExceptionHandler(EntityNotFoundException.class)
    public void handleEntityNotFoundException(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @ExceptionHandler(EntityExistsException.class)
    public void handleEntityExistsException(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
