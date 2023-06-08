package pl.madzierski.daniel.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class AppRuntimeException extends RuntimeException {
   private final HttpStatusCode httpStatusCode;

   public AppRuntimeException(HttpStatus httpStatusCode, String message) {
      super(message);
      this.httpStatusCode = httpStatusCode;
   }
}
