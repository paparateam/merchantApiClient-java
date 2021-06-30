package com.papara.api.masspayment.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/**
 * RecurringMassPaymentToPhoneNumberOptions is used by mass payment service for
 * providing request parameters.
 *
 * @author Mehmet Canhoroz <m.canhoroz@papara.com>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */

public class RecurringMassPaymentToPhoneNumberOptions extends PaparaModel {

    @SerializedName(value = "phoneNumber")
    private String phoneNumber;
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
     * Gets phone number. The mobile number of the user who will receive the
     * payment, registered in Papara. It should contain a country code and start
     * with +.
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
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets turkish national id. It provides the control of the identity information
     * sent by the user who will receive the payment, in the Papara system. In case
     * of a conflict of credentials, the transaction will not take place.
     *
     * @return the turkish national id
     */
    public Long getTurkishNationalId() {
        return turkishNationalId;
    }

    /**
     * Sets turkish national id.
     *
     * @param turkishNationalId the turkish national id
     */
    public void setTurkishNationalId(Long turkishNationalId) {
        this.turkishNationalId = turkishNationalId;
    }

    /**
     * Gets mass payment description. Description of the transaction provided by the
     * merchant. It is not a required field. If sent, the customer sees in the
     * transaction descriptions.
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

    /**
     * Gets recurring mass payment period.
     *
     * @return the period
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * Sets recurring mass payment period.
     *
     * @param period the period
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * Gets recurring mass payment execution day.
     *
     * @return the execution day
     */
    public Integer getExecutionDay() {
        return executionDay;
    }

    /**
     * Sets recurring mass payment execution day.
     *
     * @param executionDay the execution day
     */
    public void setExecutionDay(Integer executionDay) {
        this.executionDay = executionDay;
    }

}
