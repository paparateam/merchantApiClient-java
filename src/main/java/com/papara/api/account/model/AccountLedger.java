package com.papara.api.account.model;

import com.google.gson.annotations.SerializedName;
import com.papara.api.common.model.CurrencyInfo;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;


/**
 * AccountLedger class is used by account service to match returning ledger values from API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class AccountLedger extends PaparaModel {

    @SerializedName(value = "id")
    private Integer id;
    @SerializedName(value = "createdAt")
    private String createdAt;
    @SerializedName(value = "entryType")
    private Integer entryType;
    @SerializedName(value = "entryTypeName")
    private String entryTypeName;
    @SerializedName(value = "amount")
    private BigDecimal amount;
    @SerializedName(value = "fee")
    private BigDecimal fee;
    @SerializedName(value = "currency")
    private Integer currency;
    @SerializedName(value = "currencyInfo")
    private CurrencyInfo currencyInfo;
    @SerializedName(value = "resultingBalance")
    private BigDecimal resultingBalance;
    @SerializedName(value = "description")
    private String description;
    @SerializedName(value = "massPaymentId")
    private String massPaymentId;
    @SerializedName(value = "checkoutPaymentId")
    private String checkoutPaymentId;
    @SerializedName(value = "checkoutReferenceId")
    private String checkoutPaymentReferenceId;

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
     * Gets created at.
     *
     * @return the created at
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets entry type.
     *
     * @return the entry type
     */
    public Integer getEntryType() {
        return entryType;
    }

    /**
     * Sets entry type.
     * BankTransfer = 1
     * CorporateCardTransaction = 2,
     * LoadingMoneyFromPhysicalPoint = 6,
     * MerchantPayment = 8,
     * PaymentDistribution = 9,
     * EduPos = 11
     *
     * @param entryType the entry type
     */
    public void setEntryType(Integer entryType) {
        this.entryType = entryType;
    }

    /**
     * Gets entry type name.
     *
     * @return the entry type name
     */
    public String getEntryTypeName() {
        return entryTypeName;
    }

    /**
     * Sets entry type name.
     *
     * @param entryTypeName the entry type name
     */
    public void setEntryTypeName(String entryTypeName) {
        this.entryTypeName = entryTypeName;
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
     * Gets currency info.
     *
     * @return the currency info
     */
    public CurrencyInfo getCurrencyInfo() {
        return currencyInfo;
    }

    /**
     * Sets currency info.
     *
     * @param currencyInfo the currency info
     */
    public void setCurrencyInfo(CurrencyInfo currencyInfo) {
        this.currencyInfo = currencyInfo;
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

    /**
     * Gets mass payment id.
     * It is the unique value sent by the merchant to prevent duplicate repetition in payment transactions.
     * It is displayed in transaction records of masspayment type in account transactions to ensure control of the transaction.
     * It will be null in other payment types.
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
     * Gets checkout payment id.
     * It is the ID field in the data object in the payment record transaction.
     * It is the unique identifier of the payment transaction. It is displayed in transaction records of checkout type in account transactions.
     * It will be null in other payment types.
     *
     * @return the checkout payment id
     */
    public String getCheckoutPaymentId() {
        return checkoutPaymentId;
    }

    /**
     * Sets checkout payment id.
     *
     * @param checkoutPaymentId the checkout payment id
     */
    public void setCheckoutPaymentId(String checkoutPaymentId) {
        this.checkoutPaymentId = checkoutPaymentId;
    }

    /**
     * Gets checkout payment reference id.
     *
     * @return the checkout payment reference id
     */
    public String getCheckoutPaymentReferenceId() {
        return checkoutPaymentReferenceId;
    }

    /**
     * Sets checkout payment reference id.
     * This is the referenceId field sent when creating the payment transaction record.
     * It is the reference information of the payment transaction in the merchant system.
     * It is displayed in transaction records of checkout type in account transactions.
     * It will be null in other payment types.
     *
     * @param checkoutPaymentReferenceId the checkout payment reference id
     */
    public void setCheckoutPaymentReferenceId(String checkoutPaymentReferenceId) {
        this.checkoutPaymentReferenceId = checkoutPaymentReferenceId;
    }
}
