package pl.madzierski.daniel.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class RepositoryResponseDTO {

   private final String name;
   private final String ownerLogin;
   private final Set<BranchResponseDTO> branches;

}
