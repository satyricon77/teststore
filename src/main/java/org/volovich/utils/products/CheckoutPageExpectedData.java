package org.volovich.utils.products;

public class CheckoutPageExpectedData {
    public static final String PAYMENT_OPTION_NAME = "Pay by bank wire";
    public static final String ORDER_CONFIRMED_MESSAGE = "Your order is confirmed";

    public enum CheckoutStep {
        PERSONA_INFORMATION("PERSONAL INFORMATION"),
        ADDRESS("ADDRESSES"),
        SHIPPING_METHOD("SHIPPING METHOD"),
        PAYMENT("PAYMENT");

        private final String stepName;

        CheckoutStep(String stepName) {
            this.stepName = stepName;
        }

        public String getStepName() {
            return stepName;
        }
    }
}
