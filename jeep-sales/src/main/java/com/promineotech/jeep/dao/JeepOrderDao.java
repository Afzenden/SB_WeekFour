package com.promineotech.jeep.dao;

import com.promineotech.jeep.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface JeepOrderDao {

    Optional<Customer> fetchCustomer(String customer);
    Optional<Jeep> fetchModel(JeepModel model, String trim, int doors);
    Optional<Color> fetchColor(String color);
    Optional<Engine> fetchEngine(String engine);
    Optional<Tire> fetchTire(String tire);
    List<Option> fetchOptions(List<String> optionIds);
    Order saveOrder(Customer customer, Jeep jeep, Color color, Engine engine, Tire tire, BigDecimal price, List<Option> options);
}
