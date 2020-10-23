package com.papara.api.cashdeposit.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/**
 * CashDepositSettlementOptions is used by cash deposit service for providing request parameters
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @see PaparaModel
 * @version 0.0.1
 * @since 0.0.1
 */

public class CashDepositToAccountNumberOptions extends PaparaModel {

    @SerializedName(value = "accountNumber")
    private Integer accountNumber;
    @SerializedName(value = "amount")
    private BigDecimal amount;
    @SerializedName(value = "merchantReference")
    private String merchantReference;

    /**
     * Gets account number. Papara account number of the user who will be loaded with cash.
     *
     * @return the account number
     */
    public Integer getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets account number.
     *
     * @param accountNumber the account number
     */
    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets amount.
     * The amount of the cash deposit.
     * This amount will be transferred to the account of the user who received the payment.
     * The amount to be deducted from the merchant account will be exactly this number.
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

    /**
     * Gets merchant reference. The unique value sent by the merchant to prevent false repetitions in cash loading transactions.
     * If a previously submitted and successful merchantReference is resubmitted with a new request, the request will fail.
     * MerchantReference sent with failed requests can be resubmitted.
     *
     * @return the merchant reference
     */
    public String getMerchantReference() {
        return merchantReference;
    }

    /**
     * Sets merchant reference.
     *
     * @param merchantReference the merchant reference
     */
    public void setMerchantReference(String merchantReference) {
        this.merchantReference = merchantReference;
    }
}
