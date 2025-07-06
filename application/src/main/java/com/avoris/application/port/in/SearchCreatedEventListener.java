package com.avoris.application.port.in;

import com.avoris.domain.Search;

/**
 * Example of input for an event listener that reacts to the creation of a search.
 */
public interface SearchCreatedEventListener {

    void listen(String searchId, Search search);
}
