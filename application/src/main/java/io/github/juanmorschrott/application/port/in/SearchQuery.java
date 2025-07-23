package io.github.juanmorschrott.application.port.in;

import io.github.juanmorschrott.domain.model.Search;

public interface SearchQuery {

    Search getById(String searchId);
}
