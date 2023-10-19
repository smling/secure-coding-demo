package io.github.smling.securecodingdemo.controllers;

import io.github.smling.securecodingdemo.SecureCodingDemoApplicationTestInitializer;
import io.github.smling.securecodingdemo.fixtures.EntryCollectionFixture;
import io.github.smling.securecodingdemo.services.PublicApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Execution(ExecutionMode.CONCURRENT)
@ContextConfiguration(initializers = SecureCodingDemoApplicationTestInitializer.class)
class PublicApiControllerTest {
    @Mock
    PublicApiService publicApiService;

    @Autowired
    PublicApiController publicApiController;

    @ParameterizedTest
    @MethodSource("io.github.smling.securecodingdemo.fixtures.PublicApiFixture#mockGeEntriesHappyCases")
    void getEntries(String api, String description, String auth, boolean https, String cors, String category) {
        Mockito.when(publicApiService.getEntries(
                Mockito.any(),
                Mockito.any(),
                Mockito.any(),
                Mockito.anyBoolean(),
                Mockito.any(),
                Mockito.any()
        )).thenAnswer(invocationOnMock -> EntryCollectionFixture.mockEntryCollection()
        );
        assertDoesNotThrow(()->{
            publicApiController.getEntries(api, description, auth, https, cors, category).doOnNext(response-> {
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