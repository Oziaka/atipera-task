package pl.madzierski.daniel.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import pl.madzierski.daniel.exception.model.ExceptionDTO;

@ControllerAdvice
class CustomExceptionHandler {

   @ExceptionHandler(AppRuntimeException.class)
   ResponseEntity<ExceptionDTO> handleAppRuntimeException(AppRuntimeException exception, WebRequest request) {
      return ResponseEntity
         .status(exception.getHttpStatusCode())
         .body(new ExceptionDTO(exception.getHttpStatusCode().value(), exception.getMessage()));
   }

   @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
   @ResponseBody
   @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
   String handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException exception, WebRequest request) throws JsonProcessingException {
      return """
            {
               "status":%s,
               "message":"%s"
            }
            """.formatted(exception.getStatusCode().value(), exception.getMessage());
   }

}
