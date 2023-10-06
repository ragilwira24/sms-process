package com.ragilwiradiputra.smsprocess.service;

import com.ragilwiradiputra.smsprocess.dto.SMSContentDto;
import org.springframework.http.HttpStatus;
import org.thymeleaf.context.Context;

import java.util.Map;

public interface SMSService {

    String printFromTemplate(Map<String, Object> map, String template);
    String templateToString(Context context, String template);
    HttpStatus sendSMS(SMSContentDto smsContentDto);


}
