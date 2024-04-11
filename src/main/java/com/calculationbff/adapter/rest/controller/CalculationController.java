package com.calculationbff.adapter.rest.controller;


import com.calculationbff.application.adapter.CalculationRestClient;
import com.calculationbff.api.CalculationApiApi;
import com.calculationbff.api.model.CalculationRequest;
import com.calculationbff.api.model.CalculationResponseFull;
import com.calculationbff.api.model.PageCalculationResponseFull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CalculationController implements CalculationApiApi {

    @Autowired
    private CalculationRestClient calculationRestClient;


    /**
     * GET /calculation/pagination
     *
     * @param page (optional, default to 0)
     * @param size (optional, default to 10)
     * @return OK (status code 200)
     */
    @Operation(
            operationId = "getAllCalculations",
            tags = {"Calculation API"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = PageCalculationResponseFull.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/calculation/pagination",
            produces = {"application/json"}
    )

    public ResponseEntity<PageCalculationResponseFull> getAllCalculations(
            @Parameter(name = "page", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @Parameter(name = "size", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ) {
        var pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(calculationRestClient.getAllSumPaginated(pageable));
    }


    /**
     * POST /calculation
     *
     * @param calculationRequest (required)
     * @return OK (status code 200)
     */
    @Operation(
            operationId = "postCalculation",
            tags = {"Calculation API"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CalculationResponseFull.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/calculation",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<CalculationResponseFull> postCalculation(
            @Parameter(name = "CalculationRequest", description = "", required = true) @Valid @RequestBody List<@Valid CalculationRequest> calculationRequest
    ){
        return ResponseEntity.ok(calculationRestClient.calculation(calculationRequest));
    }
}
