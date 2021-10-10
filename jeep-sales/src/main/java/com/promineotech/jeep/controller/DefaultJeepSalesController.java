package com.promineotech.jeep.controller;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.service.JeepSalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RestController
@Slf4j
public class DefaultJeepSalesController implements JeepSalesController {

  @Autowired
  private JeepSalesService jeepSalesService;
	
  @Override		
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    log.debug("model={}", "trim={}", model, trim);
    return jeepSalesService.fetchJeeps(model, trim);
  }

  protected void assertErrorMessageValid(Map<String, Object> error, HttpStatus status) {
    assertThat(error)
            .containsKey("message")
            .containsEntry("status code", status.value())
            .containsEntry("uri", "/jeeps")
            .containsKey("timestamp")
            .containsEntry("reason", status.getReasonPhrase());
  }

}
