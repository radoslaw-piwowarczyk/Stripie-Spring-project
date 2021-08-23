package spring.boot.stripe.demo.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import spring.boot.stripe.demo.model.InvoiceEntity;
import spring.boot.stripe.demo.service.InvoiceService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;


@RestController
@RequestMapping("/v1")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Value("${stripe.apikey}")
    private String stripeKey;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeKey;
    }

    @GetMapping("/invoices/{invoiceId}")
    public String getInvoice(@PathVariable String invoiceId) throws StripeException {
        return invoiceService.get(invoiceId);
    }

    @PostMapping("/{customerId}/{productName}/{currency}/{unitAmount}")
    public String createInvoice(@Valid @RequestBody InvoiceEntity invoiceEntity, @PathVariable String customerId,
                                @PathVariable String productName, @PathVariable String currency,@PathVariable Integer unitAmount) throws StripeException {
        return invoiceService.create(invoiceEntity, customerId , productName, currency, unitAmount);
    }

}
