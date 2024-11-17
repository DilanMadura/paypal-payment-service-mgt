package com.dmjtech.application.ppsm.util.component;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author : Dilan Jayaneththi
 * @mailto : ddmdilan@mail.com
 * @created : 6/29/2024, Saturday, 11:39 PM,
 * @project : basic-project
 * @package : com.dmjtech.application.basic_project.util.component
 **/

@Component
public class LogMaker {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    enum Operation {REQ, RES, INT, EXT}

    public enum Protocol {REST, SOAP}

    //Request-Log
    public void setLog(String file, String methodName, Protocol protocol, Object payload, HttpServletRequest request) {
        logger.info("FILE: {}, METHOD: {}, OPERATION-STEP: {}, PROTOCOL: {}, HttpClient: {}, URL: {}, PAYLOAD: {}"
                , file, methodName, Operation.REQ, protocol, request.getMethod(), request.getRequestURL(), payload);
    }

    //Response-Log
    public void setLog(String file, String methodName, Protocol protocol, Object payload, HttpStatus httpStatus) {
        if (httpStatus == HttpStatus.OK || httpStatus == HttpStatus.CREATED || httpStatus == HttpStatus.ACCEPTED) {
            logger.info("FILE: {}, METHOD: {}, OPERATION-STEP: {}, PROTOCOL: {},  PAYLOAD: {}, HTTP-STATUS :{}"
                    , file, methodName, Operation.RES, protocol, payload, httpStatus);
        } else {
            logger.error("FILE: {}, METHOD: {}, OPERATION-STEP: {}, PROTOCOL: {},  PAYLOAD: {}, HTTP-STATUS :{}"
                    , file, methodName, Operation.RES, protocol, payload, httpStatus);
        }
    }

}
