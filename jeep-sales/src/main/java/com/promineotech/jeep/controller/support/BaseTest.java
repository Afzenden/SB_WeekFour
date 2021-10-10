package com.promineotech.jeep.controller.support;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

abstract class BaseTest {
    @LocalServerPort
    private int serverPort;

    @Autowired
    @Getter
    private TestRestTemplate restTemplate;

    protected String getBaseURIforJeeps() {
        return String.format("http://localhost:%d/jeeps", serverPort);
    }

    protected String getBaseURIforOrders() {
        return String.format("http://localhost:%d/orders", serverPort);
    }
}
