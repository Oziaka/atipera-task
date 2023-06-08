package pl.madzierski.daniel.user;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.madzierski.daniel.user.model.RepositoryResponseDTO;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
class UserController {

   private final UserService userService;

   @GetMapping(value = "/{userName}/repositories", produces = MediaType.APPLICATION_JSON_VALUE)
   ResponseEntity<Flux<RepositoryResponseDTO>> getRepositories(@PathVariable String userName) {
      return ResponseEntity.ok(this.userService.getUserRepositoriesWithLastCommit(userName));
   }
}
