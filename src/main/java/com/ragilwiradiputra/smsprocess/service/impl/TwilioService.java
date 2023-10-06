package com.ragilwiradiputra.smsprocess.service.impl;

import com.ragilwiradiputra.smsprocess.dto.SMSExecuteDto;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Service
@Slf4j
public class TwilioService extends SMSServiceImpl{

    @Value("${twilio.account.sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth.token}")
    private String twilioAuthToken;

    @Value("${sms.default.from-number}")
    private String defaultFromNumber;

    @Value("${sms.default.to-number}")
    private String defaultToNumber;

    public TwilioService(TemplateEngine smsTemplateEngine) {
        super(smsTemplateEngine);
    }

    @Override
    protected String getDefaultFromNumber() {
        return defaultFromNumber;
    }

    @Override
    protected String getDefaultToNumber() {
        return defaultToNumber;
    }

    @Override
    protected HttpStatus executeSMSProvider(SMSExecuteDto smsExecuteDto) {
        try {
            Twilio.init(twilioAccountSid, twilioAuthToken);
            Message.creator(new PhoneNumber(getToNumber()),
                    new PhoneNumber(getFromNumber()),
                    smsExecuteDto.getContent()).create();
            return HttpStatus.OK;
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return HttpStatus.BAD_REQUEST;
        }
    }
}
