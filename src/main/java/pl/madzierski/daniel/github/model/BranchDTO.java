package pl.madzierski.daniel.github.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BranchDTO {
   private final String name;
   private final CommitDTO commit;

   @JsonCreator
   public BranchDTO(@JsonProperty("name") String name, @JsonProperty("commit") CommitDTO commit) {
      this.name = name;
      this.commit = commit;
   }
}
