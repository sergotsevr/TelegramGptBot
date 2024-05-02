package com.calibri.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Message {

    @JsonIgnore
    private Long id;

    private String role;
    private String content;
}
