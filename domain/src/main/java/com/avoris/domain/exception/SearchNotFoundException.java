package com.avoris.domain.exception;

import java.io.Serial;
import java.io.Serializable;

public class SearchNotFoundException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public SearchNotFoundException() {}

    public SearchNotFoundException(String message) {
        super(message);
    }
}
