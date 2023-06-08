package pl.madzierski.daniel.github.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CommitDTO {
   private final String sha;

   @JsonCreator
   public CommitDTO(@JsonProperty("sha") String sha) {
      this.sha = sha;
   }
}
