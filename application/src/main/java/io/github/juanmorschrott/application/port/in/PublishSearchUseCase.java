package io.github.juanmorschrott.application.port.in;

import io.github.juanmorschrott.domain.model.Search;

/**
 * Complete use case for publishing a search.
 */
public interface PublishSearchUseCase {

    String publish(Search search);
}
