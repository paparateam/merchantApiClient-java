package com.papara.api.validation.service;

import com.papara.api.common.model.ServiceResult;
import com.papara.api.validation.model.*;
import com.papara.base.PaparaModel;
import com.papara.base.PaparaRESTException;

/**
 * IValidation interface that has the methods for Validation Service.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see IValidation
 * @since 0.0.1
 */
public interface IValidation {

    /**
     * Validation by account number service result.
     *
     * @param validationByAccountNumberOptions the validation by account number options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<Validation> validationByAccountNumber(ValidationByAccountNumberOptions validationByAccountNumberOptions) throws PaparaRESTException;

    /**
     * Validation by tckn service result.
     *
     * @param validationByTcknOptions the validation by tckn options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<Validation> validationByTckn(ValidationByTcknOptions validationByTcknOptions) throws PaparaRESTException;

    /**
     * Validation by phone number service result.
     *
     * @param validationByPhoneNumberOptions the validation by phone number options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<Validation> validationByPhoneNumber(ValidationByPhoneNumberOptions validationByPhoneNumberOptions) throws PaparaRESTException;

    /**
     * Validation by email service result.
     *
     * @param ValidationByEmailOptions the validation by email options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<Validation> validationByEmail(ValidationByEmailOptions ValidationByEmailOptions) throws PaparaRESTException;

    /**
     * Validation by id service result.
     *
     * @param ValidationByIdOptions the validation by id options
     * @return the service result
     * @throws PaparaRESTException the papara rest exception
     */
    ServiceResult<Validation> validationById(ValidationByIdOptions ValidationByIdOptions) throws PaparaRESTException;

}
