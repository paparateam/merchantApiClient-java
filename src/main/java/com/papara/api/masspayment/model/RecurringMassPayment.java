package com.papara.api.masspayment.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/**
 * RecurringMassPayment class is used by mass payment service to match returning
 * mass payment values from API.
 *
 * @author Mehmet Canhoroz <m.canhoroz@papara.com>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class RecurringMassPayment extends PaparaModel {

    @SerializedName(value = "merchantId")
    private String merchantId;
    @SerializedName(value = "userId")
    private String userId;
    @SerializedName(value = "period")
    private Integer period;
    @SerializedName(value = "executionDay")
    private Integer executionDay;
    @SerializedName(value = "accountNumber")
    private Integer accountNumber;
    @SerializedName(value = "message")
    private String message;
    @SerializedName(value = "amount")
    private BigDecimal amount;
    @SerializedName(value = "currency")
    private Integer currency;

    /**
     * Gets merchant id.
     *
     * @return the merchant id
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * Sets merchant id.
     *
     * @param merchantId the merchant id
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param id the user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
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

    /**
     * Gets rercurring mass payment execution day.
     *
     * @return the account number
     */
    public Integer getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets recurring mass payment account number.
     *
     * @param accountNumber the account number
     */
    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets recurring mass payment message.
     *
     * @return the description
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets recurring mass payment message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
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
     * Gets recurring mass payment currency.
     *
     * @return the currency
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * Sets mass payment currency. Values are “0” (TRY), “1” (USD), “2” (EUR), “3”
     * (GBP).
     *
     * @param currency the currency
     */
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }
}
