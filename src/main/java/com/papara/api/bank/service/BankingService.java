package com.papara.api.bank.service;

import com.google.gson.reflect.TypeToken;
import com.papara.api.bank.model.BankAccount;
import com.papara.api.bank.model.BankingWithdrawalOptions;
import com.papara.api.common.model.ServiceResult;
import com.papara.base.*;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Banking service will be used for listing merchant's bank accounts and making withdrawal operations.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaService
 * @see IBank
 * @since 0.0.1
 */
public class BankingService extends PaparaService implements IBank {

    /**
     * Initialize API context
     *
     * @param context APIContext
     */
    private APIContext context;

    /**
     * Instantiates a new banking service.
     *
     * @param context the context
     */
    public BankingService(APIContext context) {
        this.context = context;
    }

    /**
     * Returns account information and current balance for authorized merchant.
     *
     * @return ServiceResult with the generic type of List of BankAccount
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<List<BankAccount>> getBankAccounts() throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<List<BankAccount>>>() {
        }.getType();
        ServiceResult result = configureAndExecute(context, HttpMethod.GET, Endpoint.BANK_ACCOUNTS, "", ServiceResult.class, type);
        return result;
    }

    /**
     * Creates a withdrawal request from given bank account for authorized merchant.
     * @param BankingWithdrawalOptions Withdrawal Options
     * @return ServiceResult
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult withdrawal(BankingWithdrawalOptions bankAccountWithdrawalRequestModel) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(bankAccountWithdrawalRequestModel);
        ServiceResult result = configureAndExecute(context, HttpMethod.POST, Endpoint.BANK_WITHDRAWAL, payLoad, ServiceResult.class, type);
        return result;
    }
}
