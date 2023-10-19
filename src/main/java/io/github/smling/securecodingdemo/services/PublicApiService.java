package io.github.smling.securecodingdemo.services;

import io.github.smling.securecodingdemo.clients.PublicApiClient;
import io.github.smling.securecodingdemo.models.EntryCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PublicApiService {
    private final PublicApiClient publicApiClient;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PublicApiService(PublicApiClient publicApiClient) {
        this.publicApiClient = publicApiClient;
    }

    public Mono<EntryCollection> getEntries(String title, String description, String auth, boolean https, String cors, String category) {
        return publicApiClient
                .getEntries(title, description, auth, https, cors, category)
                .doOnSuccess(entryCollection -> {
                    logger.debug("record received; number of response: {}", entryCollection.count());
                    logger.trace("received data: {}", entryCollection);
                })
                .doOnError(exception -> logger.warn("Error occurred when calling public API.", exception));
    }
}
