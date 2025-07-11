package io.github.juanmorschrott.domain.model;

public record SearchCount(String searchId, Search search, Long count) {
}