package com.avoris.application.port.out;

import com.avoris.domain.Search;

public interface SearchEventPort {

    void sendMessage(String searchId, Search search);
}
