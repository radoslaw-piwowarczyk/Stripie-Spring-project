package spring.boot.stripe.demo.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceItem;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.net.StripeResponse;
import com.stripe.param.InvoiceCreateParams;
import com.stripe.param.InvoiceItemCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.boot.stripe.demo.model.InvoiceEntity;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Value("${stripe.apikey}")
    private String stripeKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeKey;
    }

    @Override
    public String get(String cId) throws StripeException {
        Invoice invoice;
        StripeResponse response = null;
        invoice = Invoice.retrieve(cId);
        response = invoice.getLastResponse();
        if (response != null) {
            return response.body();
        } else return null;
    }

    @Override
    public String create(InvoiceEntity invoiceEntity, String customerId, String productName, String currency, Integer unitAmount) throws StripeException {
        var product = getProduct(productName);
        var price = getPrice(product, unitAmount, currency);
        createInvoiceItem(customerId, price);
        var invoiceParams = createInvoiceCreateParams(customerId);
        var invoice = Invoice.create(invoiceParams);

        StripeResponse response = null;
        response = invoice.getLastResponse();
        if (response != null) {
            return response.body();
        } else
            return null;
    }

    private InvoiceCreateParams createInvoiceCreateParams(String customerId) {
        return InvoiceCreateParams.builder()
                .setCustomer(customerId)
                .build();
    }

    private void createInvoiceItem(String customerId, Price price) throws StripeException {
        InvoiceItemCreateParams invoiceItemCreateParams =
                InvoiceItemCreateParams.builder()
                        .setCustomer(customerId)
                        .setPrice(price.getId())
                        .build();
        InvoiceItem invoiceItem = InvoiceItem.create(invoiceItemCreateParams);
    }

    private Price getPrice(Product product, int unitAmount, String currency) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("unit_amount", unitAmount);
        params.put("currency", currency);
        params.put("product", product.getId());
        return Price.create(params);
    }

    private Product getProduct(String name) throws StripeException {
        Map<String, Object> productParams = new HashMap<>();
        productParams.put("name", name);
        return Product.create(productParams);
    }


}
