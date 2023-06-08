package pl.madzierski.daniel.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionDTO {
   private final int status;
   private final String message;

}
