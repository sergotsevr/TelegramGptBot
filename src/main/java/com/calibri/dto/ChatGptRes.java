package com.calibri.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatGptRes {
    private String id;
    private Integer created;
    private String model;
    private String object;
    private List<Choice> choices;
    private Usage usage;
}
