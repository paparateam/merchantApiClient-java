package com.papara.api.cashdeposit.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/**
 * CashDepositProvision class is used by cash deposit service to match returning cash deposit provision values from
 * API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public class CashDepositProvision extends PaparaModel {

    @SerializedName(value = "id")
    private Integer id;
    @SerializedName(value = "amount")
    private BigDecimal amount;
    @SerializedName(value = "currency")
    private Integer currency;
    @SerializedName(value = "createdAt")
    private String createdAt;
    @SerializedName(value = "merchantReference")
    private String merchantReference;
    @SerializedName(value = "userFullName")
    private String userFullName;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
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

    /**
     * Gets currency.
     *
     * @return the currency
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * Sets currency.
     *
     * @param currency the currency
     */
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    /**
     * Gets created date of cash deposit.
     *
     * @return the created at
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created date of cash deposit.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets merchant reference.
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

    /**
     * Gets user full name.
     *
     * @return the user full name
     */
    public String getUserFullName() {
        return userFullName;
    }

    /**
     * Sets user full name.
     *
     * @param userFullName the user full name
     */
    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
}
