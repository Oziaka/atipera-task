package pl.madzierski.daniel.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BranchResponseDTO {

   private final String name;
   private final String lastCommitSha;


}
