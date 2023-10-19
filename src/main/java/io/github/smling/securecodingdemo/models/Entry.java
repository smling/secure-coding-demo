package io.github.smling.securecodingdemo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Entry(
        @JsonProperty("API")
        String api,
        @JsonProperty("description")
        String description,
        @JsonProperty("Auth")
        String auth,
        @JsonProperty("HTTPS")
        boolean https,
        @JsonProperty("Cors")
        String cors,
        @JsonProperty("Link")
        String link,
        @JsonProperty("Category")
        String category
) {
}
