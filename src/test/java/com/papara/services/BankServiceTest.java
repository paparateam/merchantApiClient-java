package com.papara.services;

import com.papara.api.bank.model.BankAccount;
import com.papara.api.bank.model.BankingWithdrawalOptions;
import com.papara.api.bank.service.BankingService;
import com.papara.api.common.model.ServiceResult;
import com.papara.base.APIContext;
import com.papara.base.PaparaRESTException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * BankService Unit Tests
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */

public class BankServiceTest {

    private BankingService bankingService;
    private APIContext context;


    /**
     * Setup context for tests.
     */
    @Before
    public void setup() {
        this.context = new APIContext(AppSettings.apiKey, null, AppSettings.env);
        this.bankingService = new BankingService(context);
    }

    /**
     * Test Case:
     *      Getting bank account from banking service
     *
     *  Result
     *      should be succeeded
     *      should not have error
     *      @see BankAccount should be the instance
     *      @see BankAccount data should be the instance
     *
     *      @throws PaparaRESTException the Papara rest exception
     */
    @Test
    public void testGetBankAccounts() throws PaparaRESTException {

        ServiceResult<List<BankAccount>> result = bankingService.getBankAccounts();

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));
        Assert.assertTrue(result.getData() instanceof Collection<?>);
        Assert.assertTrue(result.getData().getClass().isAssignableFrom((new ArrayList<BankAccount>()).getClass()));

    }

    /**
     * Test Case:
     *       Withdrawal to bank account
     *
     *  Result
     *      should be succeeded
     *      should not have error
     *      @see BankAccount should be the instance
     *      @see BankAccount data should be the instance
     *
     *      @throws PaparaRESTException the Papara rest exception
     */
    @Test
    public void testWithdrawal() throws PaparaRESTException {

        ServiceResult<List<BankAccount>> bankAccounts = bankingService.getBankAccounts();

        Assert.assertTrue(bankAccounts.isSucceeded());
        Assert.assertTrue("Merchant must define at least 1 bank account from Papara portal.", bankAccounts.getData().size() > 0);
        Assert.assertTrue(bankAccounts.getData().get(0).getClass().isAssignableFrom(BankAccount.class));

        BankAccount bankAccount = bankAccounts.getData().get(0);

        BankingWithdrawalOptions bankingWithdrawalOptions = new BankingWithdrawalOptions();
        bankingWithdrawalOptions.setAmount(new BigDecimal(10));
        bankingWithdrawalOptions.setBankAccountId(bankAccount.getBankAccountId());

        ServiceResult result = bankingService.withdrawal(bankingWithdrawalOptions);

        Assert.assertTrue(result.isSucceeded());
        Assert.assertNull(result.getError());
        Assert.assertTrue(result.getClass().isAssignableFrom(ServiceResult.class));

    }

}
