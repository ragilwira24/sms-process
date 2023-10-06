package com.ragilwiradiputra.smsprocess.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class SMSContentDto {

    private String fromNumber;
    private String toNumber;
    private String template;
    private Map<String, Object> parameters;

}
