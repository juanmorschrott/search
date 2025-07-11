package com.avoris.application.port.in;

import com.avoris.domain.model.SearchCount;

/**
 * Example of input for simple query which does not modify the state of the system.
 */
public interface SearchCountQuery {

    SearchCount search(String searchId);
}
