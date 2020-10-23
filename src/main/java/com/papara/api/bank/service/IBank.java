package com.papara.api.bank.service;

import com.papara.api.bank.model.BankAccount;
import com.papara.api.bank.model.BankingWithdrawalOptions;
import com.papara.api.common.model.ServiceResult;
import com.papara.base.PaparaRESTException;

import java.util.List;

/** IBank interface that has the methods for Banking Service.
* @author Burak Serpici <burak.serpici@crosstech.com.tr>
* @version 0.0.1
* @since 0.0.1
*/
public interface IBank {

    /**
     * Gets bank accounts.
     *
     * @return the bank accounts
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<List<BankAccount>> getBankAccounts() throws PaparaRESTException;

    /**
     * Withdrawal service result.
     *
     * @param bankAccountWithdrawalRequestModel the bank account withdrawal request model
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult withdrawal(BankingWithdrawalOptions bankAccountWithdrawalRequestModel) throws PaparaRESTException;

}
