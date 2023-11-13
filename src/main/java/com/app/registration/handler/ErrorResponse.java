package com.app.registration.handler;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ErrorResponse {

    private String errorCode;
    private Instant timestamp;
    private String message;
}
