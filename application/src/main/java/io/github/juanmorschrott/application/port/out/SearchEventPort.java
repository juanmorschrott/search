package io.github.juanmorschrott.application.port.out;

import io.github.juanmorschrott.domain.model.Search;

public interface SearchEventPort {

    void sendMessage(String searchId, Search search);
}
