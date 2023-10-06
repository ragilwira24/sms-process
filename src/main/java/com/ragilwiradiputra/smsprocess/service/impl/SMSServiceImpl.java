package com.ragilwiradiputra.smsprocess.service.impl;

import com.ragilwiradiputra.smsprocess.dto.SMSContentDto;
import com.ragilwiradiputra.smsprocess.dto.SMSExecuteDto;
import com.ragilwiradiputra.smsprocess.service.SMSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;
import java.util.Map;

@RequiredArgsConstructor
public abstract class SMSServiceImpl implements SMSService {

    private final TemplateEngine smsTemplateEngine;
    private SMSContentDto smsContentDto;

    private Context parameterToContext(Map<String, Object> map){
        return new Context(Locale.getDefault(), map);
    }

    @Override
    public String printFromTemplate(Map<String, Object> map, String template) {
        return this.smsTemplateEngine.process(template, parameterToContext(map));
    }

    @Override
    public String templateToString(Context context, String template) {
        return this.smsTemplateEngine.process(template, context);
    }

    public HttpStatus sendSMS(SMSContentDto smsContentDto) {
        this.smsContentDto = smsContentDto;

        Context context = new Context(Locale.getDefault(), smsContentDto.getParameters());
        String content = this.templateToString(context, smsContentDto.getTemplate());
        return executeSMSProvider(SMSExecuteDto
                .builder()
                .toNumber(getToNumber())
                .fromNumber(getFromNumber())
                .context(context)
                .content(content)
                .build());
    }

    protected String getFromNumber(){
        if (ObjectUtils.isEmpty(smsContentDto.getFromNumber())){
            return getDefaultFromNumber();
        }
        return smsContentDto.getFromNumber();
    }

    protected abstract String getDefaultFromNumber();
    protected String getToNumber(){
        if (ObjectUtils.isEmpty(smsContentDto.getToNumber())){
            return getDefaultToNumber();
        }
        return smsContentDto.getToNumber();
    }

    protected abstract String getDefaultToNumber();

    protected abstract HttpStatus executeSMSProvider(SMSExecuteDto smsExecuteDto);

}
