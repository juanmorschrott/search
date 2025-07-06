package com.avoris.infrastructure.out.event.dto;

import java.time.LocalDate;
import java.util.List;

public class SearchMessage {

    private String id;
    private String hotelId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private List<Integer> ages;

    private SearchMessage(Builder builder) {
        this.id = builder.id;
        this.hotelId = builder.hotelId;
        this.checkIn = builder.checkIn;
        this.checkOut = builder.checkOut;
        this.ages = builder.ages;
    }

    public String getId() {
        return id;
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
        private String id;
        private String hotelId;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private List<Integer> ages;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

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

        public SearchMessage build() {
            return new SearchMessage(this);
        }
    }
}

