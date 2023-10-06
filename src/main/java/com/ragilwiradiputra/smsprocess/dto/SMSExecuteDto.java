package com.ragilwiradiputra.smsprocess.dto;

import lombok.Builder;
import lombok.Data;
import org.thymeleaf.context.Context;

@Data
@Builder
public class SMSExecuteDto {

    private String fromNumber;
    private String toNumber;
    private Context context;
    private String content;

}
