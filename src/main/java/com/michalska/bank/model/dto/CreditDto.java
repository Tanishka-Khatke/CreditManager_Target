package com.michalska.bank.model.dto;

import com.michalska.bank.entities.Credit;
import lombok.Data;

@Data
public class CreditDto {
    private int id;
    private String creditName;

    public static CreditDto from(Credit credit) {
        CreditDto creditDto = new CreditDto();
        creditDto.setId(credit.getId());
        creditDto.setCreditName(credit.getCreditName());
        return creditDto;
    }
}