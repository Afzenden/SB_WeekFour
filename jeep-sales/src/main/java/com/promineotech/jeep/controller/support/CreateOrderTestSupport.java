package com.promineotech.jeep.controller.support;

public class CreateOrderTestSupport extends BaseTest {
    protected String createOrderBody() {

        return "{\"customer\":\"STERN_TORO\"," +
                "\"model\":\"WRANGLER_4XE\"," +
                "\"trim\":\"High Altitude 4xe\"," +
                "\"doors\":4," +
                "\"color\":\"EXT_DIAMOND_BLACK\"," +
                "\"engine\":\"2_0_TURBO\"," +
                "\"tire\":\"35_NITTO_MT\"," +
                "\"options\":[   \"EXT_MOPAR_STEP_BLACK\"," +
                "\"INT_MOPAR_COLR\"," +
                "\"INT_MOPAR_SAFETY\"," +
                "\"INT_MOPAR_RADIO\"]}";

    }
}
