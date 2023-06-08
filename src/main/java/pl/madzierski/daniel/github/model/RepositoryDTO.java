package pl.madzierski.daniel.github.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RepositoryDTO {

   private final String name;
   private final OwnerDTO owner;
   private final boolean fork;

   @JsonCreator
   public RepositoryDTO(@JsonProperty("name") String name, @JsonProperty("owner") OwnerDTO owner, @JsonProperty("fork") boolean fork) {
      this.name = name;
      this.owner = owner;
      this.fork = fork;
   }
}
