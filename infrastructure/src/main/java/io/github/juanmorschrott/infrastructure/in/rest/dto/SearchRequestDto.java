package io.github.juanmorschrott.infrastructure.in.rest.dto;

import io.github.juanmorschrott.infrastructure.in.rest.validator.CheckInBeforeCheckOut;
import io.github.juanmorschrott.infrastructure.in.rest.validator.NoPastDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@NoPastDate
@CheckInBeforeCheckOut
@Schema(description = "DTO for search request containing hotelId, checkIn date, checkOut date, and ages of guests.")
public final class SearchRequestDto {

    @Schema(description = "ID of the hotel", example = "1234abc")
    private final String hotelId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Schema(description = "Check-in date example 29/12/2023", format = "date", pattern = "dd/MM/yyyy", example = "29/12/2023")
    private final LocalDate checkIn;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Schema(description = "Check-out date example 31/12/2023", format = "date", pattern = "dd/MM/yyyy", example = "31/12/2023")
    private final LocalDate checkOut;

    @NotNull
    @Size(min = 1)
    @Schema(description = "List of ages of guests", example = "[30, 29, 1, 3]")
    private final List<@Min(0) @Max(120) Integer> ages;

    public SearchRequestDto(@JsonProperty("hotelId") String hotelId,
                            @JsonProperty("checkIn") LocalDate checkIn,
                            @JsonProperty("checkOut") LocalDate checkOut,
                            @JsonProperty("ages") List<Integer> ages) {
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ages = Collections.unmodifiableList(ages);
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

        public SearchRequestDto build() {
            return new SearchRequestDto(hotelId, checkIn, checkOut, ages);
        }
    }
}
