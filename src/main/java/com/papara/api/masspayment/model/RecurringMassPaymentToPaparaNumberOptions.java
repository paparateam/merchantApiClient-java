package com.papara.api.masspayment.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/**
 * RecurringMassPaymentToPaparaNumberOptions is used by mass payment service for
 * providing request parameters.
 *
 * @author Mehmet Canhoroz <m.canhoroz@papara.com>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class RecurringMassPaymentToPaparaNumberOptions extends PaparaModel {

    @SerializedName(value = "accountNumber")
    private String accountNumber;
    @SerializedName(value = "parseAccountNumber")
    private Integer parseAccountNumber;
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
     * Gets account number. The 10-digit Papara number of the user who will receive
     * the payment. It can be in the format 1234567890 or PL1234567890. Before the
     * Papara version transition, the Papara number was called the wallet number.
     * Old wallet numbers have been changed to Papara number. Payment can be
     * distributed to old wallet numbers.
     *
     * @return the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets account number.
     *
     * @param accountNumber the account number
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets parse account number. Parses the account number to long type. In old
     * papara integrations, account / wallet number was made by starting with PL.
     * The service was written in such a way that it accepts numbers starting with
     * PL, in order not to cause problems to the member merchants that receive the
     * papara number from their users.
     *
     * @return the parse account number
     */
    public Integer getParseAccountNumber() {
        return parseAccountNumber;
    }

    /**
     * Sets parse account number.
     *
     * @param parseAccountNumber the parse account number
     */
    public void setParseAccountNumber(Integer parseAccountNumber) {
        this.parseAccountNumber = parseAccountNumber;
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
     * Gets mass payment id. Unique value sent by merchant to prevent erroneous
     * repetition in payment transactions. If a massPaymentId that was sent
     * previously and succeeded is sent again with a new request, the request will
     * fail.
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
     * Sets mass payment description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
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
