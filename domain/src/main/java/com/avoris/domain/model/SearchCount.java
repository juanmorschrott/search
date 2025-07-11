package com.avoris.domain.model;

public class SearchCount {

    private String searchId;
    private Search search;
    private Long count;

    public SearchCount() {}

    public SearchCount(String searchId, Search search, Long count) {
        this.searchId = searchId;
        this.search = search;
        this.count = count;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public static class Builder {
        private String searchId;
        private Search search;
        private Long count;

        public Builder() {
        }

        public Builder searchId(String searchId) {
            this.searchId = searchId;
            return this;
        }

        public Builder search(Search search) {
            this.search = search;
            return this;
        }

        public Builder count(Long count) {
            this.count = count;
            return this;
        }

        public SearchCount build() {
            return new SearchCount(this);
        }
    }

    private SearchCount(Builder builder) {
        this.searchId = builder.searchId;
        this.search = builder.search;
        this.count = builder.count;
    }
}
