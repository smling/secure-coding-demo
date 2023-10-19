package io.github.smling.securecodingdemo.services;

import io.github.smling.securecodingdemo.SecureCodingDemoApplicationTestInitializer;
import io.github.smling.securecodingdemo.clients.PublicApiClient;
import io.github.smling.securecodingdemo.fixtures.EntryCollectionFixture;
import io.github.smling.securecodingdemo.models.Entry;
import io.github.smling.securecodingdemo.models.EntryCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Execution(ExecutionMode.CONCURRENT)
@ContextConfiguration(initializers = SecureCodingDemoApplicationTestInitializer.class)
class PublicApiServiceTest {
    @Mock
    PublicApiClient publicApiClient;

    @Autowired
    PublicApiService publicApiService;

    @ParameterizedTest
    @MethodSource("io.github.smling.securecodingdemo.fixtures.PublicApiFixture#mockGeEntriesHappyCases")
    void getEntries(String api, String description, String auth, boolean https, String cors, String category) {
        Mockito.when(publicApiClient.getEntries(
                Mockito.any(),
                Mockito.any(),
                Mockito.any(),
                Mockito.anyBoolean(),
                Mockito.any(),
                Mockito.any()
                )).thenAnswer(invocationOnMock -> EntryCollectionFixture.mockEntryCollection()
        );
        assertDoesNotThrow(()->{
            publicApiService.getEntries(api, description, auth, https, cors, category).doOnNext(response-> {
               assertNotNull(response);
               assertAll(
                       ()->assertNotNull(response),
                       ()->assertFalse(response.count() > 0),
                       ()->assertEquals(response.count(), response.entries().size())
            );
            });
        });
    }
}