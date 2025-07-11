package com.avoris.application.port.in;

import com.avoris.domain.model.Search;

/**
 * Complete use case for publishing a search.
 */
public interface PublishSearchUseCase {

    String publish(Search search);
}
