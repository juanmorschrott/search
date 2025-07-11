package io.github.juanmorschrott.infrastructure.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class SearchCountResponseDto {

    private String searchId;
    private Search search;
    private Long count;

    public SearchCountResponseDto() {}

    public SearchCountResponseDto(@JsonProperty("searchId") String searchId,
                                  @JsonProperty("search") Search search,
                                  @JsonProperty("count") Long count) {
        this.searchId = searchId;
        this.search = search;
        this.count = count;
    }

    public String getSearchId() {
        return searchId;
    }

    public Search getSearch() {
        return search;
    }

    public Long getCount() {
        return count;
    }

    public static class Builder {
        private String searchId;
        private Search search;
        private Long count;

        public Builder() {}

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

        public SearchCountResponseDto build() {
            return new SearchCountResponseDto(this);
        }
    }

    private SearchCountResponseDto(Builder builder) {
        this.searchId = builder.searchId;
        this.search = builder.search;
        this.count = builder.count;
    }

    public static class Search {

        private final String hotelId;
        private final LocalDate checkIn;
        private final LocalDate checkOut;
        private final List<Integer> ages;

        public Search(String hotelId, LocalDate checkIn, LocalDate checkOut, List<Integer> ages) {
            this.hotelId = hotelId;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.ages = ages;
        }

        public String getHotelId() {
            return hotelId;
        }

        public LocalDate getCheckIn() {
            return checkIn;
        }

        public LocalDate getCheckOut() {
            return checkOut;
        }

        public List<Integer> getAges() {
            return ages;
        }

        public static class Builder {
            private String hotelId;
            private LocalDate checkIn;
            private LocalDate checkOut;
            private List<Integer> ages;

            public Builder() {}

            public Builder hotelId(String hotelId) {
                this.hotelId = hotelId;
                return this;
            }

            public Builder checkIn(LocalDate checkIn) {
                this.checkIn = checkIn;
                return this;
            }

            public Builder checkOut(LocalDate checkOut) {
                this.checkOut = checkOut;
                return this;
            }

            public Builder ages(List<Integer> ages) {
                this.ages = ages;
                return this;
            }

            public Search build() {
                return new Search(this);
            }
        }

        private Search(Builder builder) {
            this.hotelId = builder.hotelId;
            this.checkIn = builder.checkIn;
            this.checkOut = builder.checkOut;
            this.ages = builder.ages;
        }
    }
}
