package io.github.smling.securecodingdemo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record EntryCollection (
        @JsonProperty
        int count,
        @JsonProperty
        List<Entry> entries) {
}
