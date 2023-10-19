package io.github.smling.securecodingdemo.clients;

import io.github.smling.securecodingdemo.SecureCodingDemoApplicationTestInitializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Execution(ExecutionMode.CONCURRENT)
@ContextConfiguration(initializers = SecureCodingDemoApplicationTestInitializer.class)
class PublicApiClientTest {
    @Autowired
    PublicApiClient publicApiClient;

    @Test
    void getEntries() {
        assertDoesNotThrow(() -> {
            publicApiClient.getEntries("cat", null, null, true, null, null)
                    .subscribe(actual -> {
                        assertAll(() -> {
                                    assertNotNull(actual);
                                },
                                () -> {
                                    assertTrue(actual.count() > 0);
                                });
                    });
        });
    }
}