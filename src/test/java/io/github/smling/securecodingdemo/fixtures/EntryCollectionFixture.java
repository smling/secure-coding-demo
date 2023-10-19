package io.github.smling.securecodingdemo.fixtures;

import io.github.smling.securecodingdemo.models.Entry;
import io.github.smling.securecodingdemo.models.EntryCollection;
import reactor.core.publisher.Mono;

import java.util.List;

public class EntryCollectionFixture {
    private EntryCollectionFixture() {}
    public static Mono<EntryCollection> mockEntryCollection() {
        return Mono.just(new EntryCollection(
                        5,
                        List.of(
                                new Entry("api", "description", "auth", true, "cors", "https://www.google.com", "dummy"),
                                new Entry("api", "description", "auth", true, "cors", "https://www.google.com", "dummy"),
                                new Entry("api", "description", "auth", true, "cors", "https://www.google.com", "dummy"),
                                new Entry("api", "description", "auth", true, "cors", "https://www.google.com", "dummy"),
                                new Entry("api", "description", "auth", true, "cors", "https://www.google.com", "dummy")
                        )
                )
        );
    }
}
