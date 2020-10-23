package com.papara.services;

import com.papara.api.common.model.PagingResult;
import com.papara.api.common.model.ServiceResult;
import com.papara.api.payment.model.*;
import com.papara.api.payment.service.PaymentService;
import com.papara.base.APIContext;
import com.papara.base.PaparaRESTException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

/**
 * PaymentService Unit Tests
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public class PaymentServiceTest {

    private PaymentService paymentService;
    private APIContext context;


    /**
     * Setup context for tests .
     */
    @Before
    public void setup() {
        this.context = new APIContext(AppSettings.apiKey, null, AppSettings.env);
        this.paymentService = new PaymentService(context);
    }

    /**
     * Test Case:
     * Getting payment by id from payment service
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see Payment should be the instance
     * @see Payment data should be the instance
     */
    @Test
    public void testPaymentById() throws PaparaRESTException {

        PaymentListOptions paymentListOptions = new PaymentListOptions();
        paymentListOptions.setPageIndex(1);
        paymentListOptions.setPageItemCount(20);
        ServiceResult<PagingResult<PaymentListItem>> listResult = paymentService.paymentList(paymentListOptions);

        if (listResult.getData().getItems().size() > 0) {

            PaymentGetOptions paymentGetOptions = new PaymentGetOptions();
            paymentGetOptions.setId(listResult.getData().getItems().get(0).getId());

            ServiceResult<Payment> result = paymentService.getPayment(paymentGetOptions);

            Assert.assertTrue(result.isSucceeded());
            Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
            Assert.assertTrue(result.getData().getClass().isAssignableFrom(Payment.class));

        }

    }
    /**
     * Test Case:
     * Getting payment by reference number from payment service
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see Payment should be the instance
     * @see Payment data should be the instance
     */
    @Test
    public void testPaymentByReferenceId() throws PaparaRESTException {

        PaymentListOptions paymentListOptions = new PaymentListOptions();
        paymentListOptions.setPageIndex(1);
        paymentListOptions.setPageItemCount(20);
        ServiceResult<PagingResult<PaymentListItem>> listResult = paymentService.paymentList(paymentListOptions);

        if (listResult.getData().getItems().size() > 0) {

            PaymentReferenceGetOptions paymentReferenceGetOptions = new PaymentReferenceGetOptions();
            paymentReferenceGetOptions.setReferenceId(listResult.getData().getItems().get(0).getId());

            ServiceResult<Payment> result = paymentService.getPaymentByReference(paymentReferenceGetOptions);

            Assert.assertTrue(result.isSucceeded());
            Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
            Assert.assertTrue(result.getData().getClass().isAssignableFrom(Payment.class));

        }

    }

    /**
     * Test Case:
     * Getting payments from payment service
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see PagingResult<PaymentListItem> should be the instance
     * @see PagingResult<PaymentListItem> data should be the instance
     */
    @Test
    public void testPaymentList() throws PaparaRESTException {

        PaymentListOptions paymentListOptions = new PaymentListOptions();
        paymentListOptions.setPageIndex(1);
        paymentListOptions.setPageItemCount(20);
        ServiceResult<PagingResult<PaymentListItem>> result = paymentService.paymentList(paymentListOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getItems().getClass().isAssignableFrom((new ArrayList<PaymentListItem>()).getClass()));

    }


    /**
     * Test Case:
     * Creating payment by payment service
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see Payment should be the instance
     * @see Payment data should be the instance
     */
    @Test
    public void testCreatePayment() throws PaparaRESTException {

        PaymentCreateOptions paymentCreateOptions = new PaymentCreateOptions();
        paymentCreateOptions.setAmount(new BigDecimal(1));
        paymentCreateOptions.setNotificationUrl("https://testmerchant.com/notification");
        paymentCreateOptions.setOrderDescription("Payment Unit Test");
        paymentCreateOptions.setReferenceId(UUID.randomUUID().toString());
        paymentCreateOptions.setTurkishNationalId(AppSettings.tckn);
        paymentCreateOptions.setRedirectUrl("https://testmerchant.com/userredirect");
        ServiceResult<Payment> result = paymentService.createPayment(paymentCreateOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(Payment.class));
        Assert.assertEquals(paymentCreateOptions.getReferenceId(), result.getData().getReferenceId());

    }


    /**
     * Test Case:
     * Refunding a payment by payment service
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see PagingResult<PaymentListItem> should be the instance
     * @see PagingResult<PaymentListItem> data should be the instance
     */
    @Test
    public void testRefundPayment() throws PaparaRESTException {

        PaymentListOptions paymentListOptions = new PaymentListOptions();
        paymentListOptions.setPageIndex(1);
        paymentListOptions.setPageItemCount(20);
        ServiceResult<PagingResult<PaymentListItem>> paymentList = paymentService.paymentList(paymentListOptions);

        if (paymentList.getData().getItems().size() > 0) {
            PaymentRefundOptions paymentRefundOptions = new PaymentRefundOptions();
            paymentRefundOptions.setId(paymentList.getData().getItems().get(0).getId());

            ServiceResult result = paymentService.refundPayment(paymentRefundOptions);

            Assert.assertTrue(result.isSucceeded());
            Assert.assertNull(result.getError());
            Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        }

    }

}
