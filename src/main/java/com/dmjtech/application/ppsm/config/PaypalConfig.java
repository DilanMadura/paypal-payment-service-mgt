package com.dmjtech.application.ppsm.config;


import com.paypal.base.rest.APIContext;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Dilan Jayaneththi
 * @mailto : ddmdilan@mail.com
 * @created : 11/10/2024, Sunday, 10:07 PM,
 * @project : payment-service-mgt
 * @package : com.dmjtech.application.basic_project.config
 **/

@Configuration
public class PaypalConfig {
    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        OAuthTokenCredential authTokenCredential = new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
        APIContext context = new APIContext(authTokenCredential.getAccessToken());
        context.setConfigurationMap(paypalSdkConfig());
        return context;
    }

    private Map<String, String> paypalSdkConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", mode); // "sandbox" or "live"
        return configMap;
    }
}
