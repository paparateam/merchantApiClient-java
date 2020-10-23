package com.papara.api.payment.service;

import com.papara.api.common.model.PagingResult;
import com.papara.api.common.model.ServiceResult;
import com.papara.api.payment.model.*;
import com.papara.base.PaparaRESTException;

/**
 * IPayment interface that has the methods for Payment Service.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public interface IPayment {

    /**
     * Gets payment.
     *
     * @param paymentGetOptions the payment get options
     * @return the payment
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<Payment> getPayment(PaymentGetOptions paymentGetOptions) throws PaparaRESTException;


    ServiceResult<Payment> getPaymentByReference(PaymentReferenceGetOptions paymentReferenceGetOptions) throws PaparaRESTException;

    /**
     * Refund payment service result.
     *
     * @param paymentRefundOptions the payment refund options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult refundPayment(PaymentRefundOptions paymentRefundOptions) throws PaparaRESTException;

    /**
     * Create payment service result.
     *
     * @param paymentRequestModel the payment request model
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<Payment> createPayment(PaymentCreateOptions paymentRequestModel) throws PaparaRESTException;

    /**
     * Payment list service result.
     *
     * @param paymentListOptions the payment list options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<PagingResult<PaymentListItem>> paymentList(PaymentListOptions paymentListOptions) throws PaparaRESTException;

}
