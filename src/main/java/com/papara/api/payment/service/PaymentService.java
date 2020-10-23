package com.papara.api.payment.service;

import com.google.gson.reflect.TypeToken;
import com.papara.api.common.model.PagingResult;
import com.papara.api.common.model.ServiceResult;
import com.papara.api.payment.model.*;
import com.papara.base.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Payment service will be used for getting, creating or listing payments and refunding.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaService
 * @see IPayment
 * @since 0.0.1
 */
public class PaymentService extends PaparaService implements IPayment {

    private APIContext context;

    /**
     * Instantiates a new Payment service.
     *
     * @param context the context
     */
    public PaymentService(APIContext context) {
        this.context = context;
    }

    /**
     * Returns payment and balance information for authorized merchant.
     *
     * @param PaymentGetOptions Payment Get Options
     * @return ServiceResult with the generic type of Payment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Payment> getPayment(PaymentGetOptions paymentGetOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<Payment>>() {
        }.getType();
        Map<String, String> pathParameters = new HashMap<String, String>();
        pathParameters.put("0", paymentGetOptions.getId());
        Map<String, String> queryParameters = new HashMap<String, String>();
        String uriPath = RESTUtil.formatURIPath(Endpoint.PAYMENTS_ID, pathParameters,
                queryParameters);
        ServiceResult<Payment> result = configureAndExecute(context, HttpMethod.GET, uriPath, "", ServiceResult.class, type);
        return result;

    }

    @Override
    public ServiceResult<Payment> getPaymentByReference(PaymentReferenceGetOptions paymentReferenceGetOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<Payment>>() {
        }.getType();
        Map<String, String> pathParameters = new HashMap<String, String>();
        pathParameters.put("0", paymentReferenceGetOptions.getReferenceId());
        Map<String, String> queryParameters = new HashMap<String, String>();
        String uriPath = RESTUtil.formatURIPath(Endpoint.PAYMENTS_REFERENCE, pathParameters,
                queryParameters);
        ServiceResult<Payment> result = configureAndExecute(context, HttpMethod.GET, uriPath, "", ServiceResult.class, type);
        return result;
    }

    /**
     * Creates a refund for a completed payment for authorized merchant.
     *
     * @param PaymentRefundOptions Payment refund Options
     * @return ServiceResult with the generic type of Payment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult refundPayment(PaymentRefundOptions paymentRefundOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult>() {
        }.getType();
        Map<String, String> pathParameters = new HashMap<String, String>();
        pathParameters.put("0", paymentRefundOptions.getId());
        Map<String, String> queryParameters = new HashMap<String, String>();
        String uriPath = RESTUtil.formatURIPath(Endpoint.PAYMENT_REFUND, pathParameters,
                queryParameters);
        ServiceResult result = configureAndExecute(context, HttpMethod.PUT, uriPath, "", ServiceResult.class, type);
        return result;

    }

    /**
     * Creates a payment for authorized merchant.
     *
     * @param PaymentCreateOptions Payment Create Options
     * @return ServiceResult with the generic type of Payment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Payment> createPayment(PaymentCreateOptions paymentRequestModel) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<Payment>>() {
        }.getType();
        String payload = paymentRequestModel.toJSON();
        ServiceResult<Payment> result = configureAndExecute(context, HttpMethod.POST, Endpoint.PAYMENTS, payload, ServiceResult.class, type);
        return result;
    }

    /**
     * Returns a list of completed payments sorted by newest to oldest for authorized merchant.
     *
     * @param PaymentListOptions Payment List Options
     * @return ServiceResult with the generic type of Paging Result of Payment List Item
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<PagingResult<PaymentListItem>> paymentList(PaymentListOptions paymentListOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<PagingResult<PaymentListItem>>>() {
        }.getType();
        Map<String, String> pathParameters = new HashMap<String, String>();
        pathParameters.put("0", String.valueOf(paymentListOptions.getPageIndex()));
        pathParameters.put("1", String.valueOf(paymentListOptions.getPageItemCount()));
        Map<String, String> queryParameters = new HashMap<String, String>();
        String uriPath = RESTUtil.formatURIPath(Endpoint.PAYMENT_LIST, pathParameters,
                queryParameters);
        ServiceResult<PagingResult<PaymentListItem>> result = configureAndExecute(context, HttpMethod.GET, uriPath, "", ServiceResult.class, type);
        return result;
    }

}
