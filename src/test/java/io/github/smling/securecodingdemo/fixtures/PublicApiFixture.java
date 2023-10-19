package io.github.smling.securecodingdemo.fixtures;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class PublicApiFixture {
    private PublicApiFixture() {}
    public static Stream<Arguments> mockGeEntriesHappyCases() {
        return Stream.of(
                Arguments.of("cat", null, null, true, null, null),
                Arguments.of(null, "description", null, true, null, null),
                Arguments.of(null, null, "auth", true, null, null),
                Arguments.of(null, null, null, true, null, null),
                Arguments.of(null, null, null, true, "cors", null),
                Arguments.of(null, null, null, true, null, "category")
        );
    }
}
