package com.calibri.dto;

import lombok.Data;

@Data
public class Choice {
    private String finishReason;
    private Integer index;
    private Message message;
}
