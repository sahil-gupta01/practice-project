package ecommerce.ecommerce.controllerAdvice;

import ecommerce.ecommerce.dtos.ErrorResponseDto;
import ecommerce.ecommerce.exceptions.InvalidProductIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidProduct(){
        return new ResponseEntity<>(new ErrorResponseDto("Invalid product id"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorResponseDto> handleArithmeticException(){
        return new ResponseEntity<>(new ErrorResponseDto("Arithmetically wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
