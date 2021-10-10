package com.promineotech.jeep.controller;

import com.promineotech.jeep.JeepSales;
import com.promineotech.jeep.controller.support.CreateOrderTestSupport;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {JeepSales.class})
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
        "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, config = @SqlConfig(encoding = "utf-8"))
class CreateOrderTest extends CreateOrderTestSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testCreatedOrderReturnsSuccess201() {
        //Given: an order is sent
        String body = createOrderBody();
        String uri = getBaseURIforOrders();
        int numRowsOrders = JdbcTestUtils.countRowsInTable(jdbcTemplate, "orders");
        int numRowsOptions = JdbcTestUtils.countRowsInTable(jdbcTemplate, "order_options");
        System.out.println(uri);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
        System.out.println("bodyEntity: [from createOrderBody() in CreateOrderTestSupport] " + bodyEntity);

        //When: the order is sent
        ResponseEntity<Order> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity, Order.class);
        System.out.println("response: ======> " + response.getBody());

        //Then: a 201 status is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        //And: the returned order is correct
        assertThat(response.getBody()).isNotNull();

        Order order = response.getBody();
        assertThat(order.getCustomer().getCustomerId()).isEqualTo("STERN_TORO");
        assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.WRANGLER_4XE);
        assertThat(order.getModel().getTrimLevel()).isEqualTo("High Altitude 4xe");
        assertThat(order.getModel().getNumDoors()).isEqualTo(4);
        assertThat(order.getColor().getColorId()).isEqualTo("EXT_DIAMOND_BLACK");
        assertThat(order.getEngine().getEngineId()).isEqualTo("2_0_TURBO");
        assertThat(order.getTire().getTireId()).isEqualTo("35_NITTO_MT");
        assertThat(order.getOptions()).hasSize(4);

        assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "orders")).isEqualTo(numRowsOrders + 1);
        assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "order_options")).isEqualTo(numRowsOptions + 4);

    }
}



