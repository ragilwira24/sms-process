package com.ragilwiradiputra.smsprocess.controller;

import com.ragilwiradiputra.smsprocess.dto.SMSContentDto;
import com.ragilwiradiputra.smsprocess.service.impl.TwilioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
public class SMSController {

    private static final String SMS_EXAMPLE_1_TEXT_TEMPLATE_NAME = "templates/sms_example_1";

    private final TwilioService twilioService;

    @PostMapping("/twilio")
    public ResponseEntity<String> sendSmsTwilio(@RequestBody SMSContentDto contentDto){
        Map<String, Object> map = contentDto.getParameters();
        map.put("subscriptionDate", new Date());
        contentDto.setParameters(map);
        contentDto.setTemplate(SMS_EXAMPLE_1_TEXT_TEMPLATE_NAME);

        HttpStatus sendSmHttpStatus = twilioService.sendSMS(contentDto);
        return ResponseEntity.status(sendSmHttpStatus).body(sendSmHttpStatus.getReasonPhrase());
    }

    @PostMapping("/twilio/print")
    public ResponseEntity<String> printSmsTemplate(@RequestBody Map<String, Object> parameter){
        parameter.put("subscriptionDate", new Date());
        return ResponseEntity.ok(twilioService.printFromTemplate(parameter, SMS_EXAMPLE_1_TEXT_TEMPLATE_NAME));
    }



}
