package io.github.juanmorschrott.domain.exception;

import java.io.Serial;
import java.io.Serializable;

public class SearchServiceException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public SearchServiceException() {}

    public SearchServiceException(String message) {
        super(message);
    }
}
