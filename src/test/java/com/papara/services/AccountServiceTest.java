package com.papara.services;

import java.util.ArrayList;

import com.papara.api.account.model.*;
import com.papara.api.account.service.AccountService;
import com.papara.api.common.model.PagingResult;
import com.papara.api.common.model.ServiceResult;
import com.papara.base.APIContext;
import com.papara.base.PaparaRESTException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * AccountService Unit Tests
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public class AccountServiceTest {

    private AccountService accountService;
    private APIContext context;


    /**
     * Setup context for tests
     */
    @Before
    public void setup() {
        this.context = new APIContext(AppSettings.apiKey, null, AppSettings.env);
        this.accountService = new AccountService(context);
    }

    /**
     *
     * Test Case:
     *     Getting account information from account service
     *
     * Result
     *     should be succeeded
     *     should not have error
     *     @see Account should be the instance
     *     @see Account data should be the instance
     *
     *
     *     @throws PaparaRESTException the Papara rest exception
     */
    @Test
    public void testGetAccount() throws PaparaRESTException {
        ServiceResult<Account> result = accountService.getAccount();

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(Account.class));
    }

    /**
     *  Test Case:
     *    Listing ledgers from account service
     *
     * Result
     *    should be succeeded
     *    should not have error
     *    @see AccountLedger should be the instance
     *    @see AccountLedger data should be the instance
     *
     *    @throws PaparaRESTException the Papara rest exception
     */
    @Test
    public void testGetAccountLedgers() throws PaparaRESTException {
        AccountLedgerOptions accountLedgerOptions = new AccountLedgerOptions();
        accountLedgerOptions.setStartDate("2020-01-01T06:42:47.049Z");
        accountLedgerOptions.setEndDate("2020-08-22T06:42:47.049Z");
        accountLedgerOptions.setPage(1);
        accountLedgerOptions.setPageSize(50);

        ServiceResult<PagingResult<AccountLedger>> result = accountService.getAccountLedgers(accountLedgerOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getItems().getClass().isAssignableFrom((new ArrayList<AccountLedger>()).getClass()));
    }

    /**
     * Test Case:
     *     Getting settlement from account service
     *
     * Result
     *     should be succeeded
     *     should not have error
     *     @see AccountSettlement should be the instance
     *     @see AccountSettlement data should be the instance
     *
     *     @throws PaparaRESTException the papara rest exception
     */
    @Test
    public void testGetAccountSettlement() throws PaparaRESTException {

        AccountSettlementOptions accountSettlementOptions = new AccountSettlementOptions();
        accountSettlementOptions.setStartDate("2020-08-22T06:42:47.056Z");
        accountSettlementOptions.setEndDate("2020-08-22T06:42:47.056Z");

        ServiceResult<AccountSettlement> result = accountService.getAccountSettlement(accountSettlementOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData().getClass().isAssignableFrom(AccountSettlement.class));

    }

}
