package ru.smn.fantasyteam.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.smn.fantasyteam.dto.ErrorResponse;
import ru.smn.fantasyteam.exceptions.user.LoginOrPasswordException;
import ru.smn.fantasyteam.util.Utils;

import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

@RestControllerAdvice
public class BasicControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleJsonExceptions(ConstraintViolationException exc) {
        Set<ConstraintViolation<?>> constraintViolations = exc.getConstraintViolations();

        List<String> collect = constraintViolations.stream()
                .map(violation -> String.format("%s %s - %s", StreamSupport.stream(violation.getPropertyPath().spliterator()
                                        , false)
                                .reduce((first, second) -> second).orElse(null),
                        violation.getInvalidValue(), violation.getMessage())).toList();
//        ConstraintViolation<?> next = exc.getConstraintViolations().iterator().next();
//        String errorMessage = null;
//        Path propertyPath = next.getPropertyPath();
//        for(Path.Node node : propertyPath){
//            errorMessage = node.getName();
//        }
        return new ErrorResponse(Utils.writeErrorMessage(collect), 400);
        // return new ResponseEntity<>(collect, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginOrPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleBadCredentialsException(LoginOrPasswordException exc) {
        return new ErrorResponse(exc.getMessage(), 401);
    }
}
