package com.avoris.infrastructure.in.rest;

import com.avoris.application.port.in.PublishSearchUseCase;
import com.avoris.application.port.in.SearchCountQuery;
import com.avoris.domain.Search;
import com.avoris.domain.SearchCount;
import com.avoris.infrastructure.in.rest.dto.SearchCountResponseDto;
import com.avoris.infrastructure.in.rest.dto.SearchRequestDto;
import com.avoris.infrastructure.in.rest.dto.SearchResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Search", description = "Search Controller")
public class SearchController {

    private static final Logger log = LoggerFactory.getLogger(SearchController.class);

    private final PublishSearchUseCase publishSearchUseCase;
    private final ConversionService conversionService;
    private final SearchCountQuery searchCountQuery;

    public SearchController(PublishSearchUseCase publishSearchUseCase,
            ConversionService conversionService,
            SearchCountQuery searchCountQuery) {
        this.publishSearchUseCase = publishSearchUseCase;
        this.conversionService = conversionService;
        this.searchCountQuery = searchCountQuery;
    }

    @Operation(summary = "Create a new search", description = "Creates a new search entry and returns the search ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/search")
    public SearchResponseDto search(@Valid @RequestBody SearchRequestDto searchRequestDto) {
        log.info("Received search request: {}", searchRequestDto);
        Search search = this.conversionService.convert(searchRequestDto, Search.class);
        String searchId = this.publishSearchUseCase.publish(search);

        return new SearchResponseDto.Builder()
                .searchId(searchId)
                .build();
    }

    @Operation(summary = "Get search count", description = "Returns the count of searches for a given search ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Count retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid search ID"),
            @ApiResponse(responseCode = "404", description = "Search ID not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/count")
    public SearchCountResponseDto count(
            @Parameter(description = "ID of the search to count", required = true) @RequestParam String searchId) {
        log.info("Received search count request: {}", searchId);
        SearchCount searchCount = this.searchCountQuery.search(searchId);

        return this.conversionService.convert(searchCount, SearchCountResponseDto.class);
    }
}
