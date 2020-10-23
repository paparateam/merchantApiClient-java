package com.papara.services;

import com.papara.api.common.model.ServiceResult;
import com.papara.api.masspayment.model.*;
import com.papara.api.masspayment.service.MassPaymentService;
import com.papara.base.APIContext;
import com.papara.base.PaparaRESTException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * MassPaymentService Unit Tests
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public class MassPaymentServiceTest {

    private MassPaymentService massPaymentService;
    private APIContext context;


    /**
     * Setup context for tests.
     */
    @Before
    public void setup() {
        this.context = new APIContext(AppSettproings.apiKey, null, AppSettings.env);
        this.massPaymentService = new MassPaymentService(context);
    }

    /**
     * Test Case:
     *      Getting mass payment by id from mass payment service
     *
     *  Result
     *      should be succeeded
     *      should not have error
     *      @see MassPayment should be the instance
     *      @see MassPayment data should be the instance
     *
     *      @throws PaparaRESTException the Papara rest exception
     */
    //@Test
    public void testMassPaymentById() throws PaparaRESTException {

        MassPaymentByIdOptions massPaymentByIdOptions = new MassPaymentByIdOptions();
        massPaymentByIdOptions.setId("1");
        ServiceResult<MassPayment> result = massPaymentService.massPaymentById(massPaymentByIdOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(MassPayment.class));

    }

    /**
     * Test Case:
     *      Getting mass payment by Papara account number from mass payment service
     *
     *  Result
     *      should be succeeded
     *      should not have error
     *      @see MassPayment should be the instance
     *      @see MassPayment data should be the instance
     *
     *      @throws PaparaRESTException the Papara rest exception
     */
    @Test
    public void testMassPaymentByAccount() throws PaparaRESTException {

        MassPaymentToPaparaNumberOptions massPaymentToPaparaNumberOptions = new MassPaymentToPaparaNumberOptions();
        massPaymentToPaparaNumberOptions.setAccountNumber(AppSettings.personalAccountNumber.toString());
        massPaymentToPaparaNumberOptions.setAmount(new BigDecimal(1));
        massPaymentToPaparaNumberOptions.setDescription("Unit Test: MassPaymentByAccount");
        massPaymentToPaparaNumberOptions.setMassPaymentId(UUID.randomUUID().toString());
        massPaymentToPaparaNumberOptions.setParseAccountNumber(1);
        massPaymentToPaparaNumberOptions.setTurkishNationalId(AppSettings.tckn);
        ServiceResult<MassPayment> result = massPaymentService.massPaymentByAccount(massPaymentToPaparaNumberOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(MassPayment.class));

        MassPaymentByIdOptions massPaymentByIdOptions = new MassPaymentByIdOptions();
        massPaymentByIdOptions.setId(result.getData().getId().toString());
        ServiceResult<MassPayment> massPaymentResult = massPaymentService.massPaymentById(massPaymentByIdOptions);

        Assert.assertTrue(massPaymentResult.isSucceeded());
        Assert.assertTrue(massPaymentResult.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(massPaymentResult.getData().getClass().isAssignableFrom(MassPayment.class));

    }

    /**
     * Test Case:
     *      Getting mass payment by phone number from mass payment service
     *
     *  Result
     *      should be succeeded
     *      should not have error
     *      @see MassPayment should be the instance
     *      @see MassPayment data should be the instance
     *
     *      @throws PaparaRESTException the Papara rest exception
     */
    @Test
    public void testMassPaymentByPhone() throws PaparaRESTException {

        MassPaymentToPhoneNumberOptions massPaymentToPhoneNumberOptions = new MassPaymentToPhoneNumberOptions();
        massPaymentToPhoneNumberOptions.setPhoneNumber(AppSettings.personalPhoneNumber);
        massPaymentToPhoneNumberOptions.setAmount(new BigDecimal(1));
        massPaymentToPhoneNumberOptions.setDescription("Unit Test: MassPaymentByPhone");
        massPaymentToPhoneNumberOptions.setMassPaymentId(UUID.randomUUID().toString());
        massPaymentToPhoneNumberOptions.setTurkishNationalId(AppSettings.tckn);
        ServiceResult<MassPayment> result = massPaymentService.massPaymentByPhone(massPaymentToPhoneNumberOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(MassPayment.class));

        MassPaymentByIdOptions massPaymentByIdOptions = new MassPaymentByIdOptions();
        massPaymentByIdOptions.setId(result.getData().getId().toString());
        ServiceResult<MassPayment> massPaymentResult = massPaymentService.massPaymentById(massPaymentByIdOptions);

        Assert.assertTrue(massPaymentResult.isSucceeded());
        Assert.assertTrue(massPaymentResult.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(massPaymentResult.getData().getClass().isAssignableFrom(MassPayment.class));

    }


    /**
     * Test Case:
     *      Getting mass payment by e-mail from mass payment service
     *
     *  Result
     *      should be succeeded
     *      should not have error
     *      @see MassPayment should be the instance
     *      @see MassPayment data should be the instance
     *
     *      @throws PaparaRESTException the Papara rest exception
     */
    @Test
    public void testMassPaymentByEmail() throws PaparaRESTException {

        MassPaymentToEmailOptions massPaymentToEmailOptions = new MassPaymentToEmailOptions();
        massPaymentToEmailOptions.setEmail(AppSettings.personalEmail);
        massPaymentToEmailOptions.setAmount(new BigDecimal(1));
        massPaymentToEmailOptions.setDescription("Unit Test: MassPaymentByEmail");
        massPaymentToEmailOptions.setMassPaymentId(UUID.randomUUID().toString());
        massPaymentToEmailOptions.setTurkishNationalId(AppSettings.tckn);
        ServiceResult<MassPayment> result = massPaymentService.massPaymentByEmail(massPaymentToEmailOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(MassPayment.class));

        MassPaymentByIdOptions massPaymentByIdOptions = new MassPaymentByIdOptions();
        massPaymentByIdOptions.setId(result.getData().getId().toString());
        ServiceResult<MassPayment> massPaymentResult = massPaymentService.massPaymentById(massPaymentByIdOptions);

        Assert.assertTrue(massPaymentResult.isSucceeded());
        Assert.assertTrue(massPaymentResult.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(massPaymentResult.getData().getClass().isAssignableFrom(MassPayment.class));

    }

    /**
     * Test Case:
     *      Getting mass payment by payment reference number from mass payment service
     *
     *  Result
     *      should be succeeded
     *      should not have error
     *      @see MassPayment should be the instance
     *      @see MassPayment data should be the instance
     *
     *      @throws PaparaRESTException the Papara rest exception
     */
    // @Test
    public void testMassPaymentByReference() throws PaparaRESTException {

        MassPaymentByReferenceOptions massPaymentByReferenceOptions = new MassPaymentByReferenceOptions();
        massPaymentByReferenceOptions.setReference("1");
        ServiceResult<MassPayment> result = massPaymentService.massPaymentByReference(massPaymentByReferenceOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(MassPayment.class));

    }

}
