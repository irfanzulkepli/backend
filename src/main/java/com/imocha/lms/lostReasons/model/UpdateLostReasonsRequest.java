package com.imocha.lms.lostReasons.model;

import lombok.Data;

@Data
public class UpdateLostReasonsRequest {
    private long id;
    private String lostReason;
}
