package com.papara.api.account.service;

import com.papara.api.account.model.*;
import com.papara.api.common.model.PagingResult;
import com.papara.api.common.model.ServiceResult;
import com.papara.base.PaparaRESTException;

/**
 * IAccount interface that has the methods for AccountService.
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public interface IAccount {

    /**
     * Gets account.
     *
     * @return the account
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<Account> getAccount() throws PaparaRESTException;

    /**
     * Gets account ledgers.
     *
     * @param accountLedgerOptions the account ledger options
     * @return the account ledgers
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<PagingResult<AccountLedger>> getAccountLedgers(AccountLedgerOptions accountLedgerOptions) throws PaparaRESTException;

    /**
     * Gets account settlement.
     *
     * @param accountSettlementOptions the account settlement options
     * @return the account settlement
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<AccountSettlement> getAccountSettlement(AccountSettlementOptions accountSettlementOptions) throws PaparaRESTException;

}
