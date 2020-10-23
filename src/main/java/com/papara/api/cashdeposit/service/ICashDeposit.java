package com.papara.api.cashdeposit.service;

import com.papara.api.cashdeposit.model.*;
import com.papara.api.common.model.ServiceResult;
import com.papara.base.PaparaRESTException;

import java.util.ArrayList;

/**
 * ICashDeposit interface that has the methods for Cash Deposit Service.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public interface ICashDeposit {

    /**
     * Gets cash deposit.
     *
     * @param cashDepositGetOptions the cash deposit get options
     * @return the cash deposit
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<CashDeposit> getCashDeposit(CashDepositGetOptions cashDepositGetOptions) throws PaparaRESTException;

    /**
     * Get with reference service result.
     *
     * @param cashDepositByReferenceOptions the cash deposit by reference options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<CashDeposit> getCashDepositByReference(CashDepositByReferenceOptions cashDepositByReferenceOptions) throws PaparaRESTException;

    /**
     * Create with date service result.
     *
     * @param cashDepositToDateOptions the cash deposit to date options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<ArrayList<CashDeposit>> getCashDepositByDate(CashDepositByDateOptions cashDepositToDateOptions) throws PaparaRESTException;

    /**
     * Create with phone number service result.
     *
     * @param cashDepositToPhoneModel the cash deposit to phone model
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<CashDeposit> createWithPhoneNumber(CashDepositToPhoneOptions cashDepositToPhoneModel) throws PaparaRESTException;

    /**
     * Create with account number service result.
     *
     * @param cashDepositToAccountNumberOptions the cash deposit to account number options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<CashDeposit> createWithAccountNumber(CashDepositToAccountNumberOptions cashDepositToAccountNumberOptions) throws PaparaRESTException;

    /**
     * Create with tckn service result.
     *
     * @param cashDepositToTcknOptions the cash deposit to tckn options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<CashDeposit> createWithTckn(CashDepositToTcknOptions cashDepositToTcknOptions) throws PaparaRESTException;

    /**
     * Create provision with phone number service result.
     *
     * @param cashDepositToPhoneModel the cash deposit to phone model
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<CashDepositProvision> createProvisionWithPhoneNumber(CashDepositToPhoneOptions cashDepositToPhoneModel) throws PaparaRESTException;

    /**
     * Create provision with account number service result.
     *
     * @param cashDepositToAccountNumberOptions the cash deposit to account number options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<CashDepositProvision> createProvisionWithAccountNumber(CashDepositToAccountNumberOptions cashDepositToAccountNumberOptions) throws PaparaRESTException;

    /**
     * Create provision with tckn service result.
     *
     * @param cashDepositToTcknOptions the cash deposit to tckn options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<CashDepositProvision> createProvisionWithTckn(CashDepositToTcknOptions cashDepositToTcknOptions) throws PaparaRESTException;

    /**
     * Complete cash deposit provision service result.
     *
     * @param cashDepositCompleteOptions the cash deposit complete options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<CashDepositProvision> completeCashDepositProvision(CashDepositCompleteOptions cashDepositCompleteOptions) throws PaparaRESTException;

    /**
     * Provision settlement service result.
     *
     * @param cashDepositSettlementOptions the cash deposit settlement options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<CashDepositSettlement> provisionSettlement(CashDepositSettlementOptions cashDepositSettlementOptions) throws PaparaRESTException;

    ServiceResult<CashDepositSettlement> settlement(CashDepositSettlementOptions cashDepositSettlementOptions) throws PaparaRESTException;

    ServiceResult completeProvisionByReference(CashDepositControlOptions cashDepositControlOptions) throws PaparaRESTException;

    ServiceResult provisionByReferenceControl(CashDepositControlOptions cashDepositControlOptions) throws PaparaRESTException;

    ServiceResult<CashDepositProvision> createProvisionWithTcknControl(CashDepositTcknControlOptions cashDepositTcknControlOptions) throws PaparaRESTException;


}
