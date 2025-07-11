package io.github.juanmorschrott.application.port.in;

import io.github.juanmorschrott.domain.model.Search;

/**
 * Example of input for an event listener that reacts to the creation of a search.
 */
public interface SearchCreatedEventListener {

    void listen(String searchId, Search search);
}
