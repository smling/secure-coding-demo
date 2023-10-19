package io.github.smling.securecodingdemo.clients;

import io.github.smling.securecodingdemo.models.EntryCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Optional;

@Component
public class PublicApiClient {
    @Value("${paths.public-api}")
    private String baseUrl;

    public Mono<EntryCollection> getEntries(String title, String description, String auth, boolean https, String cors, String category) {
        return createDefaultWebClient()
                .get()
                .uri(uriBuilder ->
                     uriBuilder.path("/entries")
                            .queryParamIfPresent("title", Optional.ofNullable(title))
                            .queryParamIfPresent("description", Optional.ofNullable(description))
                            .queryParamIfPresent("auth", Optional.ofNullable(auth))
                            .queryParamIfPresent("https", Optional.of(https))
                            .queryParamIfPresent("cors", Optional.ofNullable(cors))
                            .queryParamIfPresent("category", Optional.ofNullable(category))
                            .build()
                )
                .retrieve()
                .bodyToMono(EntryCollection.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(3)))
                ;
    }

    private WebClient createDefaultWebClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add(HttpHeaders.USER_AGENT, "secure-coding-demo");
                })
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(Integer.MAX_VALUE))
                        .build()
                )
                .build();
    }
}
