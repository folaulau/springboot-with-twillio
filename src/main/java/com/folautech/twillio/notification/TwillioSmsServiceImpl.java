package com.folautech.twillio.notification;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TwillioSmsServiceImpl implements TwillioSmsService {

    @Value("${twilio.account.id}")
    private String accountId;

    @Value("${twilio.auth.token}")
    private String accountAuthToken;

    @Value("${twilio.phone.number.from}")
    private String fromPhoneNumber;

    @Value("${twilio.phone.number.to}")
    private String toPhoneNumber;

    @Override
    public boolean sendSms(TwillioSmsMessage twillioSmsMessage) {
        Twilio.init(accountId, accountAuthToken);

        Message message = null;

        String toNumber = twillioSmsMessage.getToPhoneNumber();
        if (toNumber == null || toNumber.isEmpty()) {
            toNumber = toPhoneNumber;
        }

        try{
            message = Message
                    .creator(new PhoneNumber("+1"+toNumber),
                            new PhoneNumber("+1"+fromPhoneNumber),
                            twillioSmsMessage.getMessageBody())
                    .create();
        } catch (Exception e) {
            log.warn("Failed to send SMS to {}: {}", twillioSmsMessage.getToPhoneNumber(), e.getMessage());
            return false;
        }
        log.info("sms message sent id: {}",message.getSid());
        return true;
    }
}
