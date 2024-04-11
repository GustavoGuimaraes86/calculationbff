package com.calculationbff.application.adapter.impl;

import com.calculationbff.adapter.rest.restclient.CalculationFeign;
import com.calculationbff.application.adapter.CalculationRestClient;
import com.calculationbff.api.model.CalculationRequest;
import com.calculationbff.api.model.CalculationResponseFull;
import com.calculationbff.api.model.PageCalculationResponseFull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculationRestClientImpl implements CalculationRestClient {

    @Autowired
    private CalculationFeign calculationFeign;

    @Override
    public CalculationResponseFull calculation(List<CalculationRequest> bodyRequest) {
        // you can make more things here, I just create it to experiment Contract, service mesh and other stuff
        return calculationFeign.calculation(bodyRequest);
    }

    @Override
    public PageCalculationResponseFull getAllSumPaginated(Pageable pageable) {
        // you can make more things here, I just create it to experiment Contract, service mesh and other stuff
        return calculationFeign.getAllSumPaginated(pageable);
    }
}
