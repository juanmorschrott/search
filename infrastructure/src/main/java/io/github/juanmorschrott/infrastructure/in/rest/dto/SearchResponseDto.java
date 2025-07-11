package io.github.juanmorschrott.infrastructure.in.rest.dto;

public class SearchResponseDto {

    private final String searchId;

    public SearchResponseDto(String searchId) {
        this.searchId = searchId;
    }

    public static class Builder {
        private String searchId;

        public Builder searchId(String searchId) {
            this.searchId = searchId;
            return this;
        }

        public SearchResponseDto build() {
            return new SearchResponseDto(searchId);
        }
    }

    public String getSearchId() {
        return searchId;
    }
}
