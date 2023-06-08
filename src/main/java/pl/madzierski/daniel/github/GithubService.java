package pl.madzierski.daniel.github;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.madzierski.daniel.exception.AppRuntimeException;
import pl.madzierski.daniel.github.model.BranchDTO;
import pl.madzierski.daniel.github.model.RepositoryDTO;
import pl.madzierski.daniel.user.model.BranchResponseDTO;
import pl.madzierski.daniel.user.model.RepositoryResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubService {

   private final WebClient webClient;
   private final int repositoriesPerPage;


   private GithubService(@Value("${github.token:#{null}}") String githubToken, @Value("${api.github.url:https://api.github.com}") String githubApiUrl, @Value("${api.github.repositories.per-page:100}") int repositoriesPerPage) {
      if (githubToken != null)
         this.webClient = WebClient.builder().baseUrl(githubApiUrl).defaultHeader("Authorization", "token " + githubToken).build();
      else
         this.webClient = WebClient.create(githubApiUrl);
      this.repositoriesPerPage = repositoriesPerPage;
   }

   public Flux<RepositoryResponseDTO> getAllUserRepositories(String userName) {
      return this.webClient.get().uri("/users/{name}", userName).retrieve().onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(new AppRuntimeException(HttpStatus.NOT_FOUND, "User not found"))).bodyToMono(pl.madzierski.daniel.github.model.UserDTO.class)
         .flatMapMany(u -> {
               List<Integer> pages = findNumbersOfPages(u);
               return Flux.fromArray(pages.toArray())
                  .flatMap(p -> getRepositoriesFromUser(userName, (Integer) p)
                     .flatMap(r -> getBranchesFromRepository(userName, r)
                        .flatMap(bl -> Mono.just(new RepositoryResponseDTO(r.getName(), r.getOwner().getLogin(), bl.stream().map(b -> new BranchResponseDTO(b.getName(), b.getCommit().getSha())).collect(Collectors.toSet())))
                        )
                     )
                  );
            }
         );
   }

   private Flux<RepositoryDTO> getRepositoriesFromUser(String userName, Integer page) {
      return this.webClient.get().uri("/users/{name}/repos?pre_page={perPage}&page={page}", userName, this.repositoriesPerPage, page)
         .retrieve()
         .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(new AppRuntimeException(HttpStatus.NOT_FOUND, "User not found")))
         .bodyToFlux(RepositoryDTO.class);
   }

   private Mono<List<BranchDTO>> getBranchesFromRepository(String userName, RepositoryDTO repository) {
      return this.webClient.get().uri("/repos/{name}/{repositoryName}/branches?per_page={perPage}", userName, repository.getName(), 100)
         .retrieve()
         .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(new AppRuntimeException(HttpStatus.NOT_FOUND, "User or repository not found")))
         .bodyToFlux(BranchDTO.class)
         .collectList();
   }


   private List<Integer> findNumbersOfPages(pl.madzierski.daniel.github.model.UserDTO user) {
      List<Integer> pages = new ArrayList<>();
      for (int i = 0, page = 1; i < user.getNumberOfPublicRepositories(); i += this.repositoriesPerPage)
         pages.add(page++);
      return pages;
   }
}
