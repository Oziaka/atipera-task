package pl.madzierski.daniel.github.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class OwnerDTO {
   private final String login;

   @JsonCreator
   public OwnerDTO(@JsonProperty("login") String login) {
      this.login = login;
   }
}
