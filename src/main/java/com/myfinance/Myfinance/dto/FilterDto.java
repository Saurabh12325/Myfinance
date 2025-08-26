package com.myfinance.Myfinance.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FilterDto {
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private String keyword;
    private String sortField;  // date , amount , name
    private String sortOrder;  // des or asc
}
