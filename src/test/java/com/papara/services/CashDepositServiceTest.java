package com.papara.services;

import com.papara.api.cashdeposit.model.*;
import com.papara.api.cashdeposit.service.CashDepositService;
import com.papara.api.common.model.ServiceResult;
import com.papara.base.APIContext;
import com.papara.base.PaparaRESTException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

/**
 * CashDepositService Unit Tests
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public class CashDepositServiceTest {

    private CashDepositService cashDepositService;
    private APIContext context;


    /**
     * Setup context for tests.
     */
    @Before
    public void setup() {
        this.context = new APIContext(AppSettings.apiKey, null, AppSettings.env);
        this.cashDepositService = new CashDepositService(context);
    }

    /**
     * Test Case:
     * Getting cash deposit from cash deposit service
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see CashDeposit should be the instance
     * @see CashDeposit data should be the instance
     */
     @Test
    public void testGetCashDeposit() throws PaparaRESTException {

        CashDepositGetOptions cashDepositGetOptions = new CashDepositGetOptions();
        cashDepositGetOptions.setId("1");
        ServiceResult<CashDeposit> result = cashDepositService.getCashDeposit(cashDepositGetOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(CashDeposit.class));

    }

    /**
     * Test Case:
     * Getting cash deposit by merchant reference.
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see CashDeposit should be the instance
     * @see CashDeposit data should be the instance
     */
    @Test
    public void testGetCashDepositByReference() throws PaparaRESTException {

        CashDepositByReferenceOptions cashDepositByReferenceOptions = new CashDepositByReferenceOptions();
        cashDepositByReferenceOptions.setReference("78cadfb9-71d1-42dd-9793-84e90af53b07");
        ServiceResult<CashDeposit> result = cashDepositService.getCashDepositByReference(cashDepositByReferenceOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(CashDeposit.class));

    }

    /**
     * Test Case:
     * Getting cash deposit by date.
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see CashDeposit should be the instance
     * @see CashDeposit data should be the instance
     */
    @Test
    public void testGetCashDepositByDate() throws PaparaRESTException {
        CashDepositByDateOptions cashDepositByDateOptions = new CashDepositByDateOptions();

        cashDepositByDateOptions.setStartDate("2020-08-12T11:00:00");
        cashDepositByDateOptions.setEndDate("2020-08-22T11:00:00");
        cashDepositByDateOptions.setPageIndex(1);
        cashDepositByDateOptions.setPageItemCount(20);
        ServiceResult<ArrayList<CashDeposit>> result = cashDepositService.getCashDepositByDate(cashDepositByDateOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom((new ArrayList<CashDeposit>()).getClass()));
    }

    /**
     * Test Case:
     * Creating cash deposit to phone number
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see CashDeposit should be the instance
     * @see CashDeposit data should be the instance
     */
    @Test
    public void testCreateWithPhoneNumber() throws PaparaRESTException {

        CashDepositToPhoneOptions cashDepositToPhoneOptions = new CashDepositToPhoneOptions();
        cashDepositToPhoneOptions.setAmount(new BigDecimal(10));
        cashDepositToPhoneOptions.setPhoneNumber(AppSettings.personalPhoneNumber);
        cashDepositToPhoneOptions.setMerchantReference(UUID.randomUUID().toString());

        ServiceResult<CashDeposit> result = cashDepositService.createWithPhoneNumber(cashDepositToPhoneOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(CashDeposit.class));

    }

    /**
     * Test Case:
     * Creating cash deposit to account number
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see CashDeposit should be the instance
     * @see CashDeposit data should be the instance
     */
    @Test
    public void testCreateWithAccountNumber() throws PaparaRESTException {

        CashDepositToAccountNumberOptions cashDepositToAccountNumberOptions = new CashDepositToAccountNumberOptions();
        cashDepositToAccountNumberOptions.setAmount(new BigDecimal(10));
        cashDepositToAccountNumberOptions.setAccountNumber(AppSettings.personalAccountNumber);
        cashDepositToAccountNumberOptions.setMerchantReference(UUID.randomUUID().toString());

        ServiceResult<CashDeposit> result = cashDepositService.createWithAccountNumber(cashDepositToAccountNumberOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(CashDeposit.class));

    }


    /**
     * Test Case:
     * Creating cash deposit to national identity number
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see CashDeposit should be the instance
     * @see CashDeposit data should be the instance
     */
    @Test
    public void testCreateWithTckn() throws PaparaRESTException {

        CashDepositToTcknOptions cashDepositToTcknOptions = new CashDepositToTcknOptions();
        cashDepositToTcknOptions.setAmount(new BigDecimal(10));
        cashDepositToTcknOptions.setTckn(AppSettings.tckn.toString());
        cashDepositToTcknOptions.setMerchantReference(UUID.randomUUID().toString());

        ServiceResult<CashDeposit> result = cashDepositService.createWithTckn(cashDepositToTcknOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(CashDeposit.class));

    }

    /**
     * Test Case:
     * Creating cash deposit provision with phone number
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see CashDepositProvision should be the instance
     * @see CashDepositProvision data should be the instance
     */
    @Test
    public void testCreateProvisionWithPhoneNumber() throws PaparaRESTException {

        CashDepositToPhoneOptions cashDepositToPhoneOptions = new CashDepositToPhoneOptions();
        cashDepositToPhoneOptions.setAmount(new BigDecimal(10));
        cashDepositToPhoneOptions.setPhoneNumber(AppSettings.personalPhoneNumber);
        cashDepositToPhoneOptions.setMerchantReference(UUID.randomUUID().toString());

        ServiceResult<CashDepositProvision> result = cashDepositService.createProvisionWithPhoneNumber(cashDepositToPhoneOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(CashDepositProvision.class));

        CashDepositCompleteOptions cashDepositCompleteOptions = new CashDepositCompleteOptions();
        cashDepositCompleteOptions.setId(result.getData().getId());
        cashDepositCompleteOptions.setTransactionDate(result.getData().getCreatedAt());

        ServiceResult<CashDepositProvision> completeResult = cashDepositService
                .completeCashDepositProvision(cashDepositCompleteOptions);

        Assert.assertTrue(completeResult.isSucceeded());
        Assert.assertNull(completeResult.getError());
        Assert.assertTrue(completeResult.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(completeResult.getData().getClass().isAssignableFrom(CashDepositProvision.class));

    }

    /**
     * Test Case:
     * Creating cash deposit provision with Papara account number
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see CashDepositProvision should be the instance
     * @see CashDepositProvision data should be the instance
     */
    @Test
    public void testCreateProvisionWithAccountNumber() throws PaparaRESTException {

        CashDepositToAccountNumberOptions cashDepositToAccountNumberOptions = new CashDepositToAccountNumberOptions();
        cashDepositToAccountNumberOptions.setAmount(new BigDecimal(10));
        cashDepositToAccountNumberOptions.setAccountNumber(AppSettings.personalAccountNumber);
        cashDepositToAccountNumberOptions.setMerchantReference(UUID.randomUUID().toString());

        ServiceResult<CashDepositProvision> result = cashDepositService.createProvisionWithAccountNumber(cashDepositToAccountNumberOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(CashDepositProvision.class));
        CashDepositCompleteOptions cashDepositCompleteOptions = new CashDepositCompleteOptions();
        cashDepositCompleteOptions.setId(result.getData().getId());
        cashDepositCompleteOptions.setTransactionDate(result.getData().getCreatedAt());

        ServiceResult<CashDepositProvision> completeResult = cashDepositService
                .completeCashDepositProvision(cashDepositCompleteOptions);

        Assert.assertTrue(completeResult.isSucceeded());
        Assert.assertNull(completeResult.getError());
        Assert.assertTrue(completeResult.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(completeResult.getData().getClass().isAssignableFrom(CashDepositProvision.class));

    }

    /**
     * Test Case:
     * Creating cash deposit provision with national identity number
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see CashDepositProvision should be the instance
     * @see CashDepositProvision data should be the instance
     */
    @Test
    public void testCreateProvisionWithTckn() throws PaparaRESTException {

        CashDepositToTcknOptions cashDepositToTcknOptions = new CashDepositToTcknOptions();
        cashDepositToTcknOptions.setAmount(new BigDecimal(10));
        cashDepositToTcknOptions.setTckn(AppSettings.tckn.toString());
        cashDepositToTcknOptions.setMerchantReference(UUID.randomUUID().toString());

        ServiceResult<CashDepositProvision> result = cashDepositService.createProvisionWithTckn(cashDepositToTcknOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(CashDepositProvision.class));

        CashDepositCompleteOptions cashDepositCompleteOptions = new CashDepositCompleteOptions();
        cashDepositCompleteOptions.setId(result.getData().getId());
        cashDepositCompleteOptions.setTransactionDate(result.getData().getCreatedAt());

        ServiceResult<CashDepositProvision> completeResult = cashDepositService
                .completeCashDepositProvision(cashDepositCompleteOptions);

        Assert.assertTrue(completeResult.isSucceeded());
        Assert.assertNull(completeResult.getError());
        Assert.assertTrue(completeResult.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(completeResult.getData().getClass().isAssignableFrom(CashDepositProvision.class));

    }

    /**
     * Test Case:
     * Completing cash deposit provision
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see CashDepositProvision should be the instance
     * @see CashDepositProvision data should be the instance
     */
     @Test
    public void testCompleteCashDepositProvision() throws PaparaRESTException {

        CashDepositCompleteOptions cashDepositCompleteOptions = new CashDepositCompleteOptions();
        cashDepositCompleteOptions.setId(1);
        cashDepositCompleteOptions.setTransactionDate("2020-08-12T11:00:00");

        ServiceResult<CashDepositProvision> result = cashDepositService.completeCashDepositProvision(cashDepositCompleteOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(CashDepositProvision.class));

    }

    /**
     * Test Case:
     * Getting provision settlements from cash deposit service
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see CashDepositSettlement should be the instance
     * @see CashDepositSettlement data should be the instance
     */
    @Test
    public void testProvisionSettlement() throws PaparaRESTException {

        CashDepositSettlementOptions cashDepositSettlementOptions = new CashDepositSettlementOptions();
        cashDepositSettlementOptions.setStartDate("2020-08-12T11:00:00");
        cashDepositSettlementOptions.setEndDate("2020-08-12T11:00:00");
        cashDepositSettlementOptions.setEntryType(3);

        ServiceResult<CashDepositSettlement> result = cashDepositService.provisionSettlement(cashDepositSettlementOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(CashDepositSettlement.class));

    }

    /**
     * Test Case:
     * Getting settlements from cash deposit service
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see CashDepositSettlement should be the instance
     * @see CashDepositSettlement data should be the instance
     */
    @Test
    public void testSettlement() throws PaparaRESTException {

        CashDepositSettlementOptions cashDepositSettlementOptions = new CashDepositSettlementOptions();
        cashDepositSettlementOptions.setStartDate("2020-08-12T11:00:00");
        cashDepositSettlementOptions.setEndDate("2020-08-12T11:00:00");
        cashDepositSettlementOptions.setEntryType(3);

        ServiceResult<CashDepositSettlement> result = cashDepositService.settlement(cashDepositSettlementOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(CashDepositSettlement.class));

    }

    /**
     * Test Case:
     * Complete provision by cash deposit reference number from cash deposit service
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see cashDepositControlOptions should be the instance
     * @see CashDepositProvision data should be the instance
     */
    @Test
    public void testCompleteProvisionByReference() throws PaparaRESTException {

        CashDepositToAccountNumberOptions cashDepositToAccountNumberOptions = new CashDepositToAccountNumberOptions();
        cashDepositToAccountNumberOptions.setAmount(new BigDecimal(10));
        cashDepositToAccountNumberOptions.setAccountNumber(AppSettings.personalAccountNumber);
        cashDepositToAccountNumberOptions.setMerchantReference(UUID.randomUUID().toString());

        ServiceResult<CashDepositProvision> provisionWithAccountNumber = cashDepositService.createProvisionWithAccountNumber(cashDepositToAccountNumberOptions);


        CashDepositControlOptions cashDepositControlOptions = new CashDepositControlOptions();
        cashDepositControlOptions.setAmount(new BigDecimal(10));
        cashDepositControlOptions.setReferenceCode(provisionWithAccountNumber.getData().getMerchantReference());

        ServiceResult result = cashDepositService.completeProvisionByReference(cashDepositControlOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));

    }

    /**
     * Test Case:
     * Make a provision ready to be completed using cash deposit reference number from cash deposit service
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see cashDepositControlOptions should be the instance
     * @see CashDepositProvision data should be the instance
     */
    @Test
    public void testProvisionByReferenceControl() throws PaparaRESTException {

        CashDepositToAccountNumberOptions cashDepositToAccountNumberOptions = new CashDepositToAccountNumberOptions();
        cashDepositToAccountNumberOptions.setAmount(new BigDecimal(10));
        cashDepositToAccountNumberOptions.setAccountNumber(AppSettings.personalAccountNumber);
        cashDepositToAccountNumberOptions.setMerchantReference(UUID.randomUUID().toString());

        ServiceResult<CashDepositProvision> provisionWithAccountNumber = cashDepositService.createProvisionWithAccountNumber(cashDepositToAccountNumberOptions);

        CashDepositControlOptions cashDepositControlOptions = new CashDepositControlOptions();
        cashDepositControlOptions.setAmount(new BigDecimal(10));
        cashDepositControlOptions.setReferenceCode(provisionWithAccountNumber.getData().getMerchantReference());

        ServiceResult result = cashDepositService.provisionByReferenceControl(cashDepositControlOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));

    }


    /**
     * Test Case:
     * Create provision using national identity number from cash deposit service
     * <p>
     * Result
     * should be succeeded
     * should not have error
     *
     * @throws PaparaRESTException the Papara rest exception
     * @see cashDepositControlOptions should be the instance
     * @see CashDepositProvision data should be the instance
     */
    @Test
    public void testCreateProvisionWithTcknControl() throws PaparaRESTException {

        CashDepositTcknControlOptions cashDepositTcknControlOptions = new CashDepositTcknControlOptions();
        cashDepositTcknControlOptions.setAmount(new BigDecimal(10));
        cashDepositTcknControlOptions.setMerchantReference(UUID.randomUUID().toString());
        cashDepositTcknControlOptions.setPhoneNumber(AppSettings.personalPhoneNumber);
        cashDepositTcknControlOptions.setTckn(AppSettings.tckn);

        ServiceResult<CashDepositProvision> result = cashDepositService.createProvisionWithTcknControl(cashDepositTcknControlOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(CashDeposit.class));

        CashDepositCompleteOptions cashDepositCompleteOptions = new CashDepositCompleteOptions();
        cashDepositCompleteOptions.setId(result.getData().getId());
        cashDepositCompleteOptions.setTransactionDate(result.getData().getCreatedAt());

        ServiceResult<CashDepositProvision> cashDepositProvisionServiceResult = cashDepositService.completeCashDepositProvision(cashDepositCompleteOptions);

        Assert.assertTrue(cashDepositProvisionServiceResult.isSucceeded());
        Assert.assertNull(cashDepositProvisionServiceResult.getError());
        Assert.assertTrue(cashDepositProvisionServiceResult.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(cashDepositProvisionServiceResult.getData().getClass().isAssignableFrom(CashDeposit.class));

    }
}
