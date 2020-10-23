package com.papara.api.cashdeposit.service;

import com.google.gson.reflect.TypeToken;
import com.papara.api.cashdeposit.model.*;
import com.papara.api.common.model.ServiceResult;
import com.papara.base.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Cash deposit service will be used for deposit operations for an end user.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaService
 * @see ICashDeposit
 * @since 0.0.1
 */
public class CashDepositService extends PaparaService implements ICashDeposit {

    private APIContext context;

    /**
     * Instantiates a new Cash deposit service.
     *
     * @param context the context
     */
    public CashDepositService(APIContext context) {
        this.context = context;
    }

    /**
     * Returns a cash deposit information.
     *
     * @param cashDepositGetOptions Cash Deposit Options
     * @return ServiceResult with the generic type of CashDeposit
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDeposit> getCashDeposit(CashDepositGetOptions cashDepositGetOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<CashDeposit>>() {
        }.getType();

        if (cashDepositGetOptions.getId() == null) {
            throw new PaparaRESTException("Id is required.");
        }

        ServiceResult<CashDeposit> result = configureAndExecute(context, HttpMethod.GET, Endpoint.CASHDEPOSIT_ID.concat(cashDepositGetOptions.getId()), "", ServiceResult.class, type);
        return result;
    }

    /**
     * Returns a cash deposit object using merchant's unique reference number.
     *
     * @param cashDepositByReferenceOptions Cash Deposit By Merchant Reference Options
     * @return ServiceResult with the generic type of CashDeposit
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDeposit> getCashDepositByReference(CashDepositByReferenceOptions cashDepositByReferenceOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<CashDeposit>>() {
        }.getType();

        if (cashDepositByReferenceOptions.getReference() == null) {
            throw new PaparaRESTException("Reference is required.");
        }

        ServiceResult<CashDeposit> result = configureAndExecute(context, HttpMethod.GET, Endpoint.CASHDEPOSIT_REFERENCE.concat(cashDepositByReferenceOptions.getReference()), "", ServiceResult.class, type);
        return result;
    }

    /**
     * Returns a cash deposit information by given date.
     *
     * @param cashDepositByDateOptions Cash Deposit By Date Options
     * @return ServiceResult with the generic type of CashDeposit arraylist
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<ArrayList<CashDeposit>> getCashDepositByDate(CashDepositByDateOptions cashDepositByDateOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<ArrayList<CashDeposit>>>() {
        }.getType();

        Map<String, String> pathParameters = new HashMap<String, String>();
        pathParameters.put("0", cashDepositByDateOptions.getStartDate());
        pathParameters.put("1", cashDepositByDateOptions.getEndDate());
        pathParameters.put("2", String.valueOf(cashDepositByDateOptions.getPageIndex()));
        pathParameters.put("3", String.valueOf(cashDepositByDateOptions.getPageItemCount()));
        Map<String, String> queryParameters = new HashMap<String, String>();
        String uriPath = RESTUtil.formatURIPath(Endpoint.CASHDEPOSIT_DATE, pathParameters,
                queryParameters);

        ServiceResult<ArrayList<CashDeposit>> result = configureAndExecute(context, HttpMethod.GET, uriPath, "", ServiceResult.class, type);
        return result;
    }

    /**
     * Creates a cash deposit request using end users's phone number.
     *
     * @param cashDepositToPhoneOptions Cash Deposit To Phone Number Options
     * @return ServiceResult with the generic type of CashDeposit
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDeposit> createWithPhoneNumber(CashDepositToPhoneOptions cashDepositToPhoneOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<CashDeposit>>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(cashDepositToPhoneOptions);
        ServiceResult<CashDeposit> result = configureAndExecute(context, HttpMethod.POST, Endpoint.CASHDEPOSIT, payLoad, ServiceResult.class, type);
        return result;
    }

    /**
     * Creates a cash deposit request using end user's account number.
     *
     * @param cashDepositToAccountNumberOptions Cash Deposit To Account Number Options
     * @return ServiceResult with the generic type of CashDeposit
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDeposit> createWithAccountNumber(CashDepositToAccountNumberOptions cashDepositToAccountNumberOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<CashDeposit>>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(cashDepositToAccountNumberOptions);
        ServiceResult<CashDeposit> result = configureAndExecute(context, HttpMethod.POST, Endpoint.CASHDEPOSIT_ACCOUNT, payLoad, ServiceResult.class, type);
        return result;
    }

    /**
     * Creates a cash deposit request using end users's national identity number.
     *
     * @param cashDepositToTcknOptions Cash Deposit To National Identity Number Options
     * @return ServiceResult with the generic type of CashDeposit
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDeposit> createWithTckn(CashDepositToTcknOptions cashDepositToTcknOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<CashDeposit>>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(cashDepositToTcknOptions);
        ServiceResult<CashDeposit> result = configureAndExecute(context, HttpMethod.POST, Endpoint.CASHDEPOSIT_TCKN, payLoad, ServiceResult.class, type);
        return result;
    }

    /**
     * Creates a cash deposit request without upfront payment using end users's phone number.
     *
     * @param cashDepositToPhoneModel Cash Deposit To Phone Number Options
     * @return ServiceResult with the generic type of CashDepositProvision
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDepositProvision> createProvisionWithPhoneNumber(CashDepositToPhoneOptions cashDepositToPhoneModel) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<CashDepositProvision>>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(cashDepositToPhoneModel);
        ServiceResult<CashDepositProvision> result = configureAndExecute(context, HttpMethod.POST, Endpoint.CASHDEPOSIT_PROVISION_PHONE, payLoad, ServiceResult.class, type);
        return result;
    }

    /**
     * Creates a cash deposit request without upfront payment using user's account number.
     *
     * @param cashDepositToAccountNumberOptions Cash Deposit To Account Number Options
     * @return ServiceResult with the generic type of CashDepositProvision
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDepositProvision> createProvisionWithAccountNumber(CashDepositToAccountNumberOptions cashDepositToAccountNumberOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<CashDepositProvision>>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(cashDepositToAccountNumberOptions);
        ServiceResult<CashDepositProvision> result = configureAndExecute(context, HttpMethod.POST, Endpoint.CASHDEPOSIT_PROVISION_ACCOUNT, payLoad, ServiceResult.class, type);
        return result;
    }

    /**
     * Creates a cash deposit request without upfront payment using end user's national identity number.
     *
     * @param cashDepositToTcknOptions Cash Deposit To National Identity Number Options
     * @return ServiceResult with the generic type of CashDepositProvision
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDepositProvision> createProvisionWithTckn(CashDepositToTcknOptions cashDepositToTcknOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<CashDepositProvision>>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(cashDepositToTcknOptions);
        ServiceResult<CashDepositProvision> result = configureAndExecute(context, HttpMethod.POST, Endpoint.CASHDEPOSIT_PROVISION_TCKN, payLoad, ServiceResult.class, type);
        return result;
    }

    /**
     * Completes a cash deposit request without upfront payment.
     *
     * @param cashDepositCompleteOptions Cash Deposit Complete Options
     * @return ServiceResult with the generic type of CashDeposit
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDepositProvision> completeCashDepositProvision(CashDepositCompleteOptions cashDepositCompleteOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<CashDepositProvision>>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(cashDepositCompleteOptions);
        ServiceResult<CashDepositProvision> result = configureAndExecute(context, HttpMethod.POST, Endpoint.CASHDEPOSIT_PROVISION_COMPLETE, payLoad, ServiceResult.class, type);
        return result;
    }

    /**
     * Returns total transaction volume and count between given dates. Start and end dates are included.
     *
     * @param cashDepositSettlementOptions Cash Deposit Settlement Options
     * @return ServiceResult with the generic type of CashDepositSettlement
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDepositSettlement> provisionSettlement(CashDepositSettlementOptions cashDepositSettlementOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<CashDepositSettlement>>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(cashDepositSettlementOptions);
        ServiceResult<CashDepositSettlement> result = configureAndExecute(context, HttpMethod.POST, Endpoint.CASHDEPOSIT_PROVISION_SETTLEMENT, payLoad, ServiceResult.class, type);
        return result;
    }

    /**
     * Returns total transaction volume and count between given dates. Start and end dates are included.
     *
     * @param cashDepositSettlementOptions Cash Deposit Settlement Options
     * @return ServiceResult with the generic type of CashDepositSettlement
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDepositSettlement> settlement(CashDepositSettlementOptions cashDepositSettlementOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<CashDepositSettlement>>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(cashDepositSettlementOptions);
        ServiceResult<CashDepositSettlement> result = configureAndExecute(context, HttpMethod.POST, Endpoint.CASHDEPOSIT_SETTLEMENT, payLoad, ServiceResult.class, type);
        return result;
    }

    /**
     * Completes a cash deposit provision by cash deposit reference number
     *
     * @param CashDepositControlOptions Cash Deposit Control Options
     * @return ServiceResult
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult completeProvisionByReference(CashDepositControlOptions cashDepositControlOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(cashDepositControlOptions);
        ServiceResult result = configureAndExecute(context, HttpMethod.POST, Endpoint.CASHDEPOSIT_PROVISION_REFERENCE_COMPLETE, payLoad, ServiceResult.class, type);
        return result;
    }

    /**
     * Makes a cash deposit provision ready to be completed by cash deposit reference number
     *
     * @param CashDepositControlOptions Cash Deposit Control Options
     * @return ServiceResult
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult provisionByReferenceControl(CashDepositControlOptions cashDepositControlOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(cashDepositControlOptions);
        ServiceResult result = configureAndExecute(context, HttpMethod.POST, Endpoint.CASHDEPOSIT_PROVISION_REFERENCE_CONTROL, payLoad, ServiceResult.class, type);
        return result;
    }


    /**
     * Creates a cash deposit request without upfront payment using end user's national identity number.
     *
     * @param CashDepositControlOptions Cash Deposit Control Options
     * @return ServiceResult  with the generic type of CashDepositProvision
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDepositProvision> createProvisionWithTcknControl(CashDepositTcknControlOptions cashDepositTcknControlOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<CashDepositProvision>>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(cashDepositTcknControlOptions);
        ServiceResult<CashDepositProvision> result = configureAndExecute(context, HttpMethod.POST, Endpoint.CASHDEPOSIT_PROVISION_TCKN_CONTROL, payLoad, ServiceResult.class, type);
        return result;
    }
}
