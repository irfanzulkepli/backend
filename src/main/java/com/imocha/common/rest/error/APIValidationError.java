package com.imocha.common.rest.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class APIValidationError extends APISubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public APIValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
