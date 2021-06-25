package com.papara.api.masspayment.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/**
 * RecurringMassPaymentToEmailOptions is used by mass payment service for
 * providing request parameters.
 *
 * @author Mehmet Canhoroz <m.canhoroz@papara.com>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class RecurringMassPaymentToEmailOptions extends PaparaModel {

    @SerializedName(value = "email")
    private String email;
    @SerializedName(value = "amount")
    private BigDecimal amount;
    @SerializedName(value = "turkishNationalId")
    private Long turkishNationalId;
    @SerializedName(value = "currency")
    private Integer currency;
    @SerializedName(value = "period")
    private Integer period;
    @SerializedName(value = "executionDay")
    private Integer executionDay;
    @SerializedName(value = "description")
    private String description;

    /**
     * Gets email. Registered email address of the user receiving the payment.
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets mass payment amount. The amount of the payment transaction. This amount
     * will be transferred to the account of the user who received the payment. This
     * figure plus transaction fee will be charged to the merchant account.
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
     * Gets mass payment id.
     *
     * @return the mass payment id
     */
    public String getMassPaymentId() {
        return massPaymentId;
    }

    /**
     * Sets mass payment id. Unique value sent by merchant to prevent erroneous
     * repetition in payment transactions. If a massPaymentId that was sent
     * previously and succeeded is sent again with a new request, the request will
     * fail.
     *
     * @param massPaymentId the mass payment id
     */
    public void setMassPaymentId(String massPaymentId) {
        this.massPaymentId = massPaymentId;
    }

    /**
     * Gets turkish national id.
     *
     * @return the turkish national id
     */
    public Long getTurkishNationalId() {
        return turkishNationalId;
    }

    /**
     * Sets turkish national id. It provides the control of the identity information
     * sent by the user who will receive the payment, in the Papara system. In case
     * of a conflict of credentials, the transaction will not take place.
     *
     * @param turkishNationalId the turkish national id
     */
    public void setTurkishNationalId(Long turkishNationalId) {
        this.turkishNationalId = turkishNationalId;
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
     * Sets description. Description of the transaction provided by the merchant. It
     * is not a required field. If sent, the customer sees in the transaction
     * descriptions.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets currency.
     *
     * @return currency.
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * Sets parse currency value.
     *
     * @param currency the parse currency value.
     */
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }
}
