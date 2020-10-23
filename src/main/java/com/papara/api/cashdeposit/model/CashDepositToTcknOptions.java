package com.papara.api.cashdeposit.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/**
 * CashDepositToTcknOptions is used by cash deposit service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @see PaparaModel
 * @version 0.0.1
 * @since 0.0.1
 */

public class CashDepositToTcknOptions extends PaparaModel {

    @SerializedName(value = "tckn")
    private String tckn;
    @SerializedName(value = "amount")
    private BigDecimal amount;
    @SerializedName(value = "merchantReference")
    private String merchantReference;

    /**
     * Gets national identity number which is linked to user's Papara account.
     *
     * @return the tckn
     */
    public String getTckn() {
        return tckn;
    }

    /**
     * Sets tckn.
     *
     * @param tckn the tckn
     */
    public void setTckn(String tckn) {
        this.tckn = tckn;
    }

    /**
     * Gets the amount of the cash deposit.
     * This amount will be transferred to the account of the user who received the payment.
     * The amount to be deducted from the merchant account will be exactly this number..
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
