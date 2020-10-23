package com.papara.api.masspayment.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/** MassPayment class is used by mass payment service to match returning mass payment values from API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class MassPayment extends PaparaModel {

    @SerializedName(value = "massPaymentId")
    private String massPaymentId;
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
     * Gets mass payment id.
     *
     * @return the mass payment id
     */
    public String getMassPaymentId() {
        return massPaymentId;
    }

    /**
     * Sets mass payment id.
     *
     * @param massPaymentId the mass payment id
     */
    public void setMassPaymentId(String massPaymentId) {
        this.massPaymentId = massPaymentId;
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
     * Gets mass payment created at.
     *
     * @return the created at
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets mass payment created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets mass payment amount.
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets mass payment amount.
     *
     * @param amount the amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets mass payment currency.
     *
     * @return the currency
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * Sets mass payment currency.
     *
     * @param currency the currency
     */
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    /**
     * Gets mass payment fee.
     *
     * @return the fee
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * Sets mass payment fee.
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
     * Gets mass payment description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets mass payment description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
