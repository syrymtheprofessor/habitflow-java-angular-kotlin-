package com.dailic.main.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDto {

    @Min(0)
    private int page = 0; // default значения

    @Min(1) @Max(100)
    private int size = 10;

    public Pageable toPageable() {
        return PageRequest.of(page, size);
    }
}