package com.papara.api.cashdeposit.model;

import com.google.gson.annotations.SerializedName;
import com.papara.api.account.service.IAccount;
import com.papara.base.PaparaModel;
import com.papara.base.PaparaService;

import java.math.BigDecimal;

/**
 * CashDeposit class is used by cash deposit service to match returning cash deposit values from API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class CashDeposit extends PaparaModel {

    @SerializedName(value = "merchantReference")
    private String merchantReference;
    @SerializedName(value = "id")
    private Integer id;
    @SerializedName(value = "createdAt")
    private String createdAt;
    @SerializedName(value = "amount")
    private BigDecimal amount;
    @SerializedName(value = "currency")
    private Integer currency;
    @SerializedName(value = "fee")
    private BigDecimal fee;
    @SerializedName(value = "resultingBalance")
    private BigDecimal resultingBalance;
    @SerializedName(value = "description")
    private String description;

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
     * Gets created date of cash deposit.
     *
     * @return the created date of cash deposit
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created date of cash deposit.
     *
     * @param createdAt the created date of cash deposit
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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
     * Gets fee.
     *
     * @return the fee
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * Sets fee.
     *
     * @param fee the fee
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * Gets resulting balance.
     *
     * @return the resulting balance
     */
    public BigDecimal getResultingBalance() {
        return resultingBalance;
    }

    /**
     * Sets resulting balance.
     *
     * @param resultingBalance the resulting balance
     */
    public void setResultingBalance(BigDecimal resultingBalance) {
        this.resultingBalance = resultingBalance;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
