package pl.madzierski.daniel.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.madzierski.daniel.github.GithubService;
import pl.madzierski.daniel.user.model.RepositoryResponseDTO;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
class UserService {

   private final GithubService githubService;

   Flux<RepositoryResponseDTO> getUserRepositoriesWithLastCommit(String userName) {
      return this.githubService.getAllUserRepositories(userName);
   }
}
