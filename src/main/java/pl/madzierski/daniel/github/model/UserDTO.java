package pl.madzierski.daniel.github.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserDTO {
   private final int numberOfPublicRepositories;

   @JsonCreator
   public UserDTO(@JsonProperty("public_repos") int numberOfPublicRepositories) {
      this.numberOfPublicRepositories = numberOfPublicRepositories;
   }
}
