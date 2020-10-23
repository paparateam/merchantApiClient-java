package com.papara.api.masspayment.service;

import com.google.gson.reflect.TypeToken;
import com.papara.api.cashdeposit.service.ICashDeposit;
import com.papara.api.common.model.ServiceResult;
import com.papara.api.masspayment.model.*;
import com.papara.base.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Mass payment service will be used for getting mass payment info and sending payments to account number, mail address and phone number.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaService
 * @see IMassPayment
 * @since 0.0.1
 */
public class MassPaymentService extends PaparaService implements IMassPayment {

    private APIContext context;

    /**
     * Instantiates a new Mass payment service.
     *
     * @param context the context
     */
    public MassPaymentService(APIContext context) {
        this.context = context;
    }

    /**
     * Returns mass payment information for authorized merchant.
     *
     * @param MassPaymentByIdOptions Mass Payment By Id Options
     * @return ServiceResult with the generic type of MassPayment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<MassPayment> massPaymentById(MassPaymentByIdOptions massPaymentByIdOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<MassPayment>>() {
        }.getType();
        Map<String, String> pathParameters = new HashMap<String, String>();
        pathParameters.put("0", massPaymentByIdOptions.getId());
        Map<String, String> queryParameters = new HashMap<String, String>();
        String uriPath = RESTUtil.formatURIPath(Endpoint.MASSPAYMENT_ID, pathParameters,
                queryParameters);
        ServiceResult<MassPayment> result = configureAndExecute(context, HttpMethod.GET, uriPath, "", ServiceResult.class, type);
        return result;
    }

    /**
     * Creates a mass payment to given account number for authorized merchant.
     *
     * @param MassPaymentToPaparaNumberOptions Mass Payment To Papara Account Options
     * @return ServiceResult with the generic type of MassPayment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<MassPayment> massPaymentByAccount(MassPaymentToPaparaNumberOptions massPaymentToPaparaNumberOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<MassPayment>>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(massPaymentToPaparaNumberOptions);
        ServiceResult<MassPayment> result = configureAndExecute(context, HttpMethod.POST, Endpoint.MASSPAYMENT_ACCOUNT, payLoad, ServiceResult.class, type);
        return result;
    }

    /**
     * Creates a mass payment to given phone number for authorized merchant.
     *
     * @param MassPaymentToPhoneNumberOptions Mass Payment To Phone Number Options
     * @return ServiceResult with the generic type of MassPayment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<MassPayment> massPaymentByPhone(MassPaymentToPhoneNumberOptions massPaymentToPhoneNumberOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<MassPayment>>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(massPaymentToPhoneNumberOptions);
        ServiceResult<MassPayment> result = configureAndExecute(context, HttpMethod.POST, Endpoint.MASSPAYMENT_PHONE, payLoad, ServiceResult.class, type);
        return result;
    }

    /**
     * Creates a mass payment to given e-mail address for authorized merchant.
     *
     * @param MassPaymentToEmailOptions Mass Payment To E-Mail Options
     * @return ServiceResult with the generic type of MassPayment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<MassPayment> massPaymentByEmail(MassPaymentToEmailOptions massPaymentToEmailOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<MassPayment>>() {
        }.getType();
        String payLoad = JSONFormatter.toJSON(massPaymentToEmailOptions);
        ServiceResult<MassPayment> result = configureAndExecute(context, HttpMethod.POST, Endpoint.MASSPAYMENT_EMAIL, payLoad, ServiceResult.class, type);
        return result;
    }

    /**
     * Returns mass payment information for authorized merchant by mass payment reference Id.
     *
     * @param MassPaymenByReferenceOptions Mass Payment By Mass Payment Reference Number
     * @return ServiceResult with the generic type of MassPayment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<MassPayment> massPaymentByReference(MassPaymentByReferenceOptions massPaymentByReferenceOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<MassPayment>>() {
        }.getType();
        Map<String, String> pathParameters = new HashMap<String, String>();
        pathParameters.put("0", massPaymentByReferenceOptions.getReference());
        Map<String, String> queryParameters = new HashMap<String, String>();
        String uriPath = RESTUtil.formatURIPath(Endpoint.MASSPAYMENT_REFERENCE, pathParameters,
                queryParameters);
        ServiceResult<MassPayment> result = configureAndExecute(context, HttpMethod.GET, uriPath, "", ServiceResult.class, type);
        return result;
    }


}
