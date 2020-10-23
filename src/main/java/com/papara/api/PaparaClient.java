package com.papara.api;

import com.papara.api.account.service.AccountService;
import com.papara.api.bank.service.BankingService;
import com.papara.api.cashdeposit.service.CashDepositService;
import com.papara.api.masspayment.service.MassPaymentService;
import com.papara.api.payment.service.PaymentService;
import com.papara.api.validation.service.ValidationService;
import com.papara.base.APIContext;

/**
 * Papara merchant service facade..
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @see IPaparaService
 * @version 0.0.1
 * @since 0.0.1
 */
public class PaparaClient implements IPaparaService {

    private AccountService accountService;
    private BankingService bankingService;
    private CashDepositService cashDepositService;
    private MassPaymentService massPaymentService;
    private PaymentService paymentService;
    private ValidationService validationService;

    private APIContext context;

    /**
     * Instantiates a new Papara client.
     *
     * @param context the context
     */
    public PaparaClient(APIContext context) {
        this.context = context;
    }

    /**
     * Gets Account Service
     * @see AccountService
     */
    @Override
    public AccountService getAccountService() {
        if (this.accountService == null) {
            return new AccountService(context);
        }
        return this.accountService;
    }

    /**
     * Gets Banking Service
     * @see BankingService
     */
    @Override
    public BankingService getBankingService() {
        if (this.bankingService == null) {
            return new BankingService(context);
        }
        return this.bankingService;
    }

    /**
     * Gets Cash Deposit Service
     * @see CashDepositService
     */
    @Override
    public CashDepositService getCashDepositService() {
        if (this.cashDepositService == null) {
            return new CashDepositService(context);
        }
        return this.cashDepositService;
    }

    /**
     * Gets Mass Payment Service
     * @see MassPaymentService
     */
    @Override
    public MassPaymentService getMassPaymentService() {
        if (this.massPaymentService == null) {
            return new MassPaymentService(context);
        }
        return this.massPaymentService;
    }
    /**
     * Gets Payment Service
     * @see PaymentService
     */
    @Override
    public PaymentService getPaymentService() {
        if (this.paymentService == null) {
            return new PaymentService(context);
        }
        return this.paymentService;
    }

    /**
     * Gets Validation Service
     * @see ValidationService
     */
    @Override
    public ValidationService getValidationService() {
        if (this.validationService == null) {
            return new ValidationService(context);
        }
        return this.validationService;
    }

}