package com.papara.api.validation.service;

import com.google.gson.reflect.TypeToken;
import com.papara.api.common.model.ServiceResult;
import com.papara.api.payment.service.IPayment;
import com.papara.api.validation.model.*;
import com.papara.base.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Validation service will be used for validating an end user. Validation can be performed by account number, e-mail address, phone number, national identity number.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaService
 * @see IValidation
 * @since 0.0.1
 */
public class ValidationService extends PaparaService implements IValidation {

    private APIContext context;

    /**
     * Instantiates a new Validation service.
     *
     * @param context the context
     */
    public ValidationService(APIContext context) {
        this.context = context;
    }

    /**
     * Returns end user information for validation by given user account number.
     *
     * @param ValidationByAccountNumberOptions Validation By Account Number Options
     * @return ServiceResult with the generic type of Validation
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Validation> validationByAccountNumber(ValidationByAccountNumberOptions validationByAccountNumberOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<Validation>>() {
        }.getType();
        Map<String, String> pathParameters = new HashMap<String, String>();
        pathParameters.put("0", String.valueOf(validationByAccountNumberOptions.getAccountNumber()));
        Map<String, String> queryParameters = new HashMap<String, String>();
        String uriPath = RESTUtil.formatURIPath(Endpoint.VALIDATION_ACCOUNT, pathParameters,
                queryParameters);
        ServiceResult<Validation> result = configureAndExecute(context, HttpMethod.GET, uriPath, "", ServiceResult.class, type);
        return result;
    }

    /**
     * Returns end user information for validation by given user national identity number.
     *
     * @param ValidationByTcknOptions Validation By National Identity Number Options
     * @return ServiceResult with the generic type of Validation
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Validation> validationByTckn(ValidationByTcknOptions validationByTcknOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<Validation>>() {
        }.getType();
        Map<String, String> pathParameters = new HashMap<String, String>();
        pathParameters.put("0", String.valueOf(validationByTcknOptions.getTckn()));
        Map<String, String> queryParameters = new HashMap<String, String>();
        String uriPath = RESTUtil.formatURIPath(Endpoint.VALIDATION_TCKN, pathParameters,
                queryParameters);
        ServiceResult<Validation> result = configureAndExecute(context, HttpMethod.GET, uriPath, "", ServiceResult.class, type);
        return result;

    }

    /**
     * Returns end user information for validation by given phone number.
     *
     * @param ValidationByPhoneNumberOptions Validation By Phone Number Options
     * @return ServiceResult with the generic type of Validation
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Validation> validationByPhoneNumber(ValidationByPhoneNumberOptions validationByPhoneNumberOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<Validation>>() {
        }.getType();
        Map<String, String> pathParameters = new HashMap<String, String>();
        pathParameters.put("0", validationByPhoneNumberOptions.getPhoneNumber());
        Map<String, String> queryParameters = new HashMap<String, String>();
        String uriPath = RESTUtil.formatURIPath(Endpoint.VALIDATION_PHONE, pathParameters,
                queryParameters);
        ServiceResult<Validation> result = configureAndExecute(context, HttpMethod.GET, uriPath, "", ServiceResult.class, type);
        return result;
    }

    /**
     * Returns end user information for validation by given user e-mail address.
     *
     * @param ValidationByEmailOptions Validation By E-Mail Options
     * @return ServiceResult with the generic type of Validation
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Validation> validationByEmail(ValidationByEmailOptions validationByEmailOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<Validation>>() {
        }.getType();
        Map<String, String> pathParameters = new HashMap<String, String>();
        pathParameters.put("0", validationByEmailOptions.getEmail());
        Map<String, String> queryParameters = new HashMap<String, String>();
        String uriPath = RESTUtil.formatURIPath(Endpoint.VALIDATION_EMAIL, pathParameters,
                queryParameters);
        ServiceResult<Validation> result = configureAndExecute(context, HttpMethod.GET, uriPath, "", ServiceResult.class, type);
        return result;
    }

    /**
     * Returns end user information for validation by given user ID.
     *
     * @param ValidationByIdOptions Validation By Id Options
     * @return ServiceResult with the generic type of Validation
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Validation> validationById(ValidationByIdOptions ValidationByIdOptions) throws PaparaRESTException {
        Type type = new TypeToken<ServiceResult<Validation>>() {
        }.getType();
        Map<String, String> pathParameters = new HashMap<String, String>();
        pathParameters.put("0", ValidationByIdOptions.getUserId());
        Map<String, String> queryParameters = new HashMap<String, String>();
        String uriPath = RESTUtil.formatURIPath(Endpoint.VALIDATION_ID, pathParameters,
                queryParameters);
        ServiceResult<Validation> result = configureAndExecute(context, HttpMethod.GET, uriPath, "", ServiceResult.class, type);
        return result;
    }

}
