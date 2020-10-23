package com.papara.api.bank.model;

import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/**
 * BankingWithdrawalOptions is used by banking service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @see PaparaModel
 * @version 0.0.1
 * @since 0.0.1
 */
public class BankingWithdrawalOptions extends PaparaModel {

    private Integer bankAccountId;
    private BigDecimal amount;

    /**
     * Gets bank account id which money will be transferred to when withdrawal is completed.It will be obtained as a result of the request to list bank accounts..
     *
     * @return the bank account id
     */
    public Integer getBankAccountId() {
        return bankAccountId;
    }

    /**
     * Sets bank account id.
     *
     * @param bankAccountId the bank account id
     */
    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
