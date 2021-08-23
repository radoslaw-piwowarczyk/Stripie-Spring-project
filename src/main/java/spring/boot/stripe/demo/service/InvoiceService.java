package spring.boot.stripe.demo.service;

import com.stripe.exception.StripeException;
import spring.boot.stripe.demo.logger.AbstractLogger;
import spring.boot.stripe.demo.model.InvoiceEntity;

public interface InvoiceService extends AbstractLogger {

    String get(String invoiceId) throws StripeException;

    String create(InvoiceEntity invoiceEntity, String customerId, String productName, String currency, Integer unitAmount) throws StripeException;

}
