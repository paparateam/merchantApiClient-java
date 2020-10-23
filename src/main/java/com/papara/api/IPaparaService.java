package com.papara.api;

import com.papara.api.account.service.AccountService;
import com.papara.api.bank.service.BankingService;
import com.papara.api.cashdeposit.service.CashDepositService;
import com.papara.api.masspayment.service.MassPaymentService;
import com.papara.api.payment.service.PaymentService;
import com.papara.api.validation.service.ValidationService;

/**
 * IPaparaService interface that has the methods for Papara Client.
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public interface IPaparaService {

    /**
     * Gets account service.
     *
     * @return the account service
     */
    AccountService getAccountService();

    /**
     * Gets bank service.
     *
     * @return the bank service
     */
    BankingService getBankingService();

    /**
     * Gets cash deposit service.
     *
     * @return the cash deposit service
     */
    CashDepositService getCashDepositService();

    /**
     * Gets mass payment service.
     *
     * @return the mass payment service
     */
    MassPaymentService getMassPaymentService();

    /**
     * Gets payment service.
     *
     * @return the payment service
     */
    PaymentService getPaymentService();

    /**
     * Gets validation service.
     *
     * @return the validation service
     */
    ValidationService getValidationService();

}
