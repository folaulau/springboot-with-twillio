package com.folautech.twillio.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Builder
@Data
public class TwillioSmsMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String toPhoneNumber;
    private String messageBody;
}
