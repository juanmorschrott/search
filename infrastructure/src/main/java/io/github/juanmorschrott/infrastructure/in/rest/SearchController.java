package io.github.juanmorschrott.infrastructure.in.rest;

import io.github.juanmorschrott.application.port.in.PublishSearchUseCase;
import io.github.juanmorschrott.application.port.in.SearchCountQuery;
import io.github.juanmorschrott.application.port.in.SearchQuery;
import io.github.juanmorschrott.domain.model.Search;
import io.github.juanmorschrott.infrastructure.in.rest.dto.SearchRequestDto;
import io.github.juanmorschrott.infrastructure.in.rest.dto.SearchResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Validated
@RestController
@RequestMapping("/api/v1/searches")
@Tag(name = "Search", description = "Search Controller")
public class SearchController {

    private static final Logger log = LoggerFactory.getLogger(SearchController.class);

    private final PublishSearchUseCase publishSearchUseCase;
    private final ConversionService conversionService;
    private final SearchQuery searchQuery;
    private final SearchCountQuery searchCountQuery;

    public SearchController(PublishSearchUseCase publishSearchUseCase,
                            ConversionService conversionService,
                            SearchQuery searchQuery,
                            SearchCountQuery searchCountQuery) {
        this.publishSearchUseCase = publishSearchUseCase;
        this.conversionService = conversionService;
        this.searchQuery = searchQuery;
        this.searchCountQuery = searchCountQuery;
    }

    @Operation(summary = "Create a new search", description = "Creates a new search entry and returns the search ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public SearchResponseDto search(@Valid @RequestBody SearchRequestDto searchRequestDto) {
        log.info("Received search request: {}", searchRequestDto);
        Search search = this.conversionService.convert(searchRequestDto, Search.class);
        String searchId = this.publishSearchUseCase.publish(search);

        return new SearchResponseDto.Builder()
                .searchId(searchId)
                .build();
    }

    @Operation(summary = "Search by id", description = "Searches for a search entry by its ID and returns the search details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{searchId}")
    public Search getSearchDetail(@PathVariable String searchId) {
        log.info("Received search detail request: {}", searchId);
        return this.searchQuery.getById(searchId);
    }

    @Operation(summary = "Get search count", description = "Returns the count of searches for given criteria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Count retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid search ID"),
            @ApiResponse(responseCode = "404", description = "Search ID not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/count")
    public long count(@RequestParam(required = false) String hotelId,
                      @Parameter(description = "Fecha de check-in (dd/MM/yyyy)", example = "31/12/2025")
                      @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate checkIn,
                      @Parameter(description = "Fecha de check-out (dd/MM/yyyy)", example = "01/01/2026")
                      @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate checkOut) {
        log.info("Received search count request: hotelId={}, checkIn={}, checkOut={}", hotelId, checkIn, checkOut);

        return this.searchCountQuery.search(hotelId, checkIn, checkOut);
    }
}
