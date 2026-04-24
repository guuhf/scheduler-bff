package com.guuh.scheduler_bff.infrastructure.client.clientConfig;

import com.guuh.scheduler_bff.infrastructure.exceptions.ConflictException;
import com.guuh.scheduler_bff.infrastructure.exceptions.IntegrationException;
import com.guuh.scheduler_bff.infrastructure.exceptions.ResourceNotFoundException;
import com.guuh.scheduler_bff.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        switch (response.status()) {
            case 409:
                return new ConflictException("Attribute already exists");
            case 404:
                return new ResourceNotFoundException("Attribute not found");
            case 401:
                return new UnauthorizedException("User unauthorized");
            default:
                return new IntegrationException("External Error");
        }
    }
}
