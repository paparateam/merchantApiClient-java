package com.papara.api.masspayment.service;

import com.papara.api.common.model.ServiceResult;
import com.papara.api.masspayment.model.*;
import com.papara.base.PaparaRESTException;

/**
 * IMassPayment interface that has the methods for Mass Payment Service.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public interface IMassPayment {

    /**
     * Mass payment by account service result.
     *
     * @param massPaymentToPaparaNumberOptions the mass payment to papara number
     *                                         options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<MassPayment> massPaymentByAccount(MassPaymentToPaparaNumberOptions massPaymentToPaparaNumberOptions)
            throws PaparaRESTException;

    /**
     * Mass payment by phone service result.
     *
     * @param massPaymentToPhoneNumberOptions the mass payment to phone number
     *                                        options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<MassPayment> massPaymentByPhone(MassPaymentToPhoneNumberOptions massPaymentToPhoneNumberOptions)
            throws PaparaRESTException;

    /**
     * Mass payment by email service result.
     *
     * @param massPaymentToEmailOptions the mass payment to email options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<MassPayment> massPaymentByEmail(MassPaymentToEmailOptions massPaymentToEmailOptions)
            throws PaparaRESTException;

    /**
     * Mass payment by id service result.
     *
     * @param massPaymentByIdOptions the mass payment by id options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<MassPayment> massPaymentById(MassPaymentByIdOptions massPaymentByIdOptions)
            throws PaparaRESTException;

    /**
     * Mass payment by reference service result.
     *
     * @param massPaymenByReferenceOptions the mass payment by reference options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<MassPayment> massPaymentByReference(MassPaymentByReferenceOptions massPaymenByReferenceOptions)
            throws PaparaRESTException;

    /**
     * Recurring Mass payment by account service result.
     *
     * @param recurringMassPaymentToPaparaNumberOptions the recurring mass payment
     *                                                  to papara number options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<RecurringMassPayment> recurringMassPaymentByAccount(
            RecurringMassPaymentToPaparaNumberOptions recurringMassPaymentToPaparaNumberOptions)
            throws PaparaRESTException;

    /**
     * Recurring Mass payment by phone service result.
     *
     * @param recurringMassPaymentToPhoneNumberOptions the recurring mass payment to
     *                                                 phone number options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<RecurringMassPayment> recurringMassPaymentByPhone(
            RecurringMassPaymentToPhoneNumberOptions recurringMassPaymentToPhoneNumberOptions)
            throws PaparaRESTException;

    /**
     * Recurring Mass payment by email service result.
     *
     * @param recurringMassPaymentToEmailOptions the recurring mass payment to email
     *                                           options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<RecurringMassPayment> recurringMassPaymentByEmail(
            RecurringMassPaymentToEmailOptions recurringMassPaymentToEmailOptions) throws PaparaRESTException;
}
