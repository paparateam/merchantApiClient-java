package com.papara.api.cashdeposit.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/**
 * CashDepositToPhoneOptions is used by cash deposit service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @see PaparaModel
 * @version 0.0.1
 * @since 0.0.1
 */

public class CashDepositToPhoneOptions extends PaparaModel {

    @SerializedName(value = "phoneNumber")
    private String phoneNumber;
    @SerializedName(value = "amount")
    private BigDecimal amount;
    @SerializedName(value = "merchantReference")
    private String merchantReference;

    /**
     * Gets phone number. The mobile phone number registered in the Papara account of the user to be loaded with cash.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets amount. The amount of the cash deposit.
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
