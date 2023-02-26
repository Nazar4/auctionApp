package nulp.pz.auction.service.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public String handleException() {
        return "error";
    }

}
