package com.papara.api.account.service;

import com.google.gson.reflect.TypeToken;
import com.papara.api.account.model.*;
import com.papara.api.common.model.PagingResult;
import com.papara.api.common.model.ServiceResult;
import com.papara.base.*;

import java.lang.reflect.Type;

/**
 * Account service will be used for obtaining account information, settlements and ledgers.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaService
 * @see IAccount
 * @since 0.0.1
 */
public class AccountService extends PaparaService implements IAccount {

    private APIContext context;

    /**
     * Initialize API context
     *
     * @param context APIContext
     */
    public AccountService(APIContext context) {
        this.context = context;
    }

    /**
     * Returns account information and current balance for authorized merchant.
     *
     * @return ServiceResult with the generic type of Account
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Account> getAccount() throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<Account>>() {
        }.getType();
        ServiceResult<Account> result = configureAndExecute(context, HttpMethod.GET, Endpoint.ACCOUNT, "", ServiceResult.class, type);
        return result;
    }

    /**
     * Returns list of ledgers for authorized merchant.
     *
     * @param accountLedgerOptions Ledger Options
     * @return ServiceResult with the generic type of PagingResult of AccountLedger
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<PagingResult<AccountLedger>> getAccountLedgers(AccountLedgerOptions accountLedgerOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<PagingResult<AccountLedger>>>() {
        }.getType();
        if (accountLedgerOptions != null && accountLedgerOptions.getEntryType() != null) {
            EnumUtil.extractEntryTypesValuesAndMatchWithParam(accountLedgerOptions.getEntryType());
        }

        String payLoad = JSONFormatter.toJSON(accountLedgerOptions);
        ServiceResult<PagingResult<AccountLedger>> result = configureAndExecute(context, HttpMethod.POST, Endpoint.ACCOUNT_LEDGERS, payLoad, ServiceResult.class, type);
        return result;
    }

    /**
     * Returns settlement for authorized merchant.
     *
     * @param accountSettlementOptions Settlement Options
     * @return ServiceResult with the generic type of AccountSettlement.
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<AccountSettlement> getAccountSettlement(AccountSettlementOptions accountSettlementOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<AccountSettlement>>() {
        }.getType();
        if (accountSettlementOptions != null && accountSettlementOptions.getEntryType() != null) {
            EnumUtil.extractEntryTypesValuesAndMatchWithParam(accountSettlementOptions.getEntryType());
        }
        String payLoad = JSONFormatter.toJSON(accountSettlementOptions);
        ServiceResult<AccountSettlement> result = configureAndExecute(context, HttpMethod.POST, Endpoint.ACCOUNT_SETTLEMENT, payLoad, ServiceResult.class, type);
        return result;
    }


}
