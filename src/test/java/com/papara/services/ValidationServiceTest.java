package com.papara.services;

import com.papara.api.common.model.ServiceResult;
import com.papara.api.validation.model.*;
import com.papara.api.validation.service.ValidationService;
import com.papara.base.APIContext;
import com.papara.base.PaparaRESTException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * ValidationService Unit Tests
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public class ValidationServiceTest {

    private ValidationService validationService;
    private APIContext context;


    /**
     * Setup context for tests .
     */
    @Before
    public void setup() {
        this.context = new APIContext(AppSettings.apiKey, null, AppSettings.env);
        this.validationService = new ValidationService(context);
    }

    /**
     * Test Case:
     *      Valdating user by account number from validation service
     *
     *  Result
     *      should be succeeded
     *      should not have error
     *      @see Validation should be the instance
     *      @see Validation data should be the instance
     *
     *      @throws PaparaRESTException the Papara rest exception
     */
    @Test
    public void tesValidationByAccountNumber() throws PaparaRESTException {

        ValidationByAccountNumberOptions validationByAccountNumberOptions = new ValidationByAccountNumberOptions();
        validationByAccountNumberOptions.setAccountNumber(Long.valueOf(AppSettings.personalAccountNumber));
        ServiceResult<Validation> result = validationService.validationByAccountNumber(validationByAccountNumberOptions);

        Assert.assertNull(result.getError());
        Assert.assertTrue(result.isSucceeded());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(Validation.class));

    }

    /**
     * Test Case:
     *      Valdating user by national identity number from validation service
     *
     *  Result
     *      should be succeeded
     *      should not have error
     *      @see Validation should be the instance
     *      @see Validation data should be the instance
     *
     *      @throws PaparaRESTException the Papara rest exception
     */
    @Test
    public void testValidationByTckn() throws PaparaRESTException {

        ValidationByTcknOptions validationByTcknOptions = new ValidationByTcknOptions();
        validationByTcknOptions.setTckn(AppSettings.tckn);
        ServiceResult<Validation> result = validationService.validationByTckn(validationByTcknOptions);

        Assert.assertNull(result.getError());
        Assert.assertTrue(result.isSucceeded());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(Validation.class));

    }

    /**
     * Test Case:
     *      Valdating user by phone number from validation service
     *
     *  Result
     *      should be succeeded
     *      should not have error
     *      @see Validation should be the instance
     *      @see Validation data should be the instance
     *
     *      @throws PaparaRESTException the Papara rest exception
     */
    @Test
    public void testValidationByPhoneNumber() throws PaparaRESTException {

        ValidationByPhoneNumberOptions validationByPhoneNumberOptions = new ValidationByPhoneNumberOptions();
        validationByPhoneNumberOptions.setPhoneNumber(AppSettings.personalPhoneNumber);
        ServiceResult<Validation> result = validationService.validationByPhoneNumber(validationByPhoneNumberOptions);

        Assert.assertNull(result.getError());
        Assert.assertTrue(result.isSucceeded());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(Validation.class));

    }


    /**
     * Test Case:
     *      Valdating user by e-mail from validation service
     *
     *  Result
     *      should be succeeded
     *      should not have error
     *      @see Validation should be the instance
     *      @see Validation data should be the instance
     *
     *      @throws PaparaRESTException the Papara rest exception
     */
    @Test
    public void testValidationByEmail() throws PaparaRESTException {

        ValidationByEmailOptions validationByEmailOptions = new ValidationByEmailOptions();
        validationByEmailOptions.setEmail(AppSettings.personalEmail);
        ServiceResult<Validation> result = validationService.validationByEmail(validationByEmailOptions);

        Assert.assertNull(result.getError());
        Assert.assertTrue(result.isSucceeded());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(Validation.class));

    }


    /**
     * Test Case:
     *      Valdating user by id from validation service
     *
     *  Result
     *      should be succeeded
     *      should not have error
     *      @see Validation should be the instance
     *      @see Validation data should be the instance
     *
     *      @throws PaparaRESTException the Papara rest exception
     */
    @Test
    public void testValidationById() throws PaparaRESTException {

        ValidationByIdOptions validationByIdOptions = new ValidationByIdOptions();
        validationByIdOptions.setUserId(AppSettings.personalAccountId);
        ServiceResult<Validation> result = validationService.validationById(validationByIdOptions);

        Assert.assertNull(result.getError());
        Assert.assertTrue(result.isSucceeded());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(Validation.class));

    }
}
