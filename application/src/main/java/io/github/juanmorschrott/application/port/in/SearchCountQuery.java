package io.github.juanmorschrott.application.port.in;

import io.github.juanmorschrott.domain.model.SearchCount;

/**
 * Example of input for simple query which does not modify the state of the system.
 */
public interface SearchCountQuery {

    SearchCount search(String searchId);
}
