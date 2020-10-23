package com.papara.api.cashdeposit.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;
/**
 * CashDepositTcknControlOptions is used by cash deposit service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public class CashDepositTcknControlOptions extends PaparaModel {

    @SerializedName(value = "phoneNumber")
    private String phoneNumber;
    @SerializedName(value = "tckn")
    private Long tckn;
    @SerializedName(value = "amount")
    private BigDecimal amount;
    @SerializedName(value = "merchantReference")
    private String merchantReference;

    /**
     * Gets phone number of cash deposit request.
     * @return the phone number of cash deposit request.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number of cash deposit request.
     *
     * @param phoneNumber the reference number of cash deposit request.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets tckn of cash deposit request.
     * @return the phone number of cash deposit request.
     */
    public Long getTckn() {
        return tckn;
    }

    /**
     * Sets phone number of cash deposit request.
     *
     * @param tckn the tckn of cash deposit request.
     */
    public void setTckn(Long tckn) {
        this.tckn = tckn;
    }

    /**
     * Gets amount of cash deposit request.
     * @return the phone number of cash deposit request.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets amount of cash deposit request.
     *
     * @param amount the amount of cash deposit request.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets merchant reference number of cash deposit request.
     * @return the phone number of cash deposit request.
     */
    public String getMerchantReference() {
        return merchantReference;
    }

    /**
     * Sets merchant reference number of cash deposit request.
     *
     * @param merchantReference the tckn of cash deposit request.
     */
    public void setMerchantReference(String merchantReference) {
        this.merchantReference = merchantReference;
    }
}
