package com.papara.api.payment.model;

import com.google.gson.annotations.SerializedName;
import com.papara.api.account.model.Account;
import com.papara.api.masspayment.service.IMassPayment;
import com.papara.base.PaparaModel;
import com.papara.base.PaparaService;

import java.math.BigDecimal;

/**
 * Payment class is used by payment service to match returning payment values from API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class Payment extends PaparaModel {

    @SerializedName(value = "merchant")
    private Account merchant;
    @SerializedName(value = "id")
    private String id;
    @SerializedName(value = "createdAt")
    private String createdAt;
    @SerializedName(value = "merchantId")
    private String merchantId;
    @SerializedName(value = "userId")
    private String userId;
    @SerializedName(value = "paymentMethod")
    private int paymentMethod;
    @SerializedName(value = "paymentMethodDescription")
    private String paymentMethodDescription;
    @SerializedName(value = "referenceId")
    private String referenceId;
    @SerializedName(value = "orderDescription")
    private String orderDescription;
    @SerializedName(value = "status")
    private Integer status;
    @SerializedName(value = "statusDescription")
    private String statusDescription;
    @SerializedName(value = "amount")
    private BigDecimal amount;
    @SerializedName(value = "fee")
    private BigDecimal fee;
    @SerializedName(value = "currency")
    private Integer currency;
    @SerializedName(value = "notificationUrl")
    private String notificationUrl;
    @SerializedName(value = "notificationDone")
    private Boolean notificationDone;
    @SerializedName(value = "redirectUrl")
    private String redirectUrl;
    @SerializedName(value = "paymentUrl")
    private String paymentUrl;
    @SerializedName(value = "merchantSecretKey")
    private String merchantSecretKey;
    @SerializedName(value = "returningRedirectUrl")
    private String returningRedirectUrl;
    @SerializedName(value = "turkishNationalId")
    private Long turkishNationalId;

    /**
     * Gets merchant.
     *
     * @return the merchant
     */
    public Account getMerchant() {
        return merchant;
    }

    /**
     * Sets merchant.
     *
     * @param merchant the merchant
     */
    public void setMerchant(Account merchant) {
        this.merchant = merchant;
    }

    /**
     * Gets payment id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets payment id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets payment created date.
     *
     * @return the created at
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets payment created date.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

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
     * @param userId the user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets payment method.
     * 0 - User completed transaction with existing Papara balance
     * 1 - User completed the transaction with a debit / credit card that was previously defined.
     * 2 - User completed transaction via mobile payment.
     *
     * @return the payment method
     */
    public int getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets payment method.
     *
     * @param paymentMethod the payment method
     */
    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Gets payment method description.
     *
     * @return the payment method description
     */
    public String getPaymentMethodDescription() {
        return paymentMethodDescription;
    }

    /**
     * Sets payment method description.
     *
     * @param paymentMethodDescription the payment method description
     */
    public void setPaymentMethodDescription(String paymentMethodDescription) {
        this.paymentMethodDescription = paymentMethodDescription;
    }

    /**
     * Gets payment reference id.
     *
     * @return the reference id
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * Sets payment reference id.
     *
     * @param referenceId the reference id
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    /**
     * Gets order description.
     *
     * @return the order description
     */
    public String getOrderDescription() {
        return orderDescription;
    }

    /**
     * Sets order description.
     *
     * @param orderDescription the order description
     */
    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    /**
     * Gets payment status.
     * 0 - Awaiting, payment is not done yet.
     * 1 - Payment is done, transaction is completed.
     * 2 - Transactions is refunded by merchant.
     *
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets payment status.
     *
     * @param status the status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Gets payment status description.
     *
     * @return the status description
     */
    public String getStatusDescription() {
        return statusDescription;
    }

    /**
     * Sets payment status description.
     *
     * @param statusDescription the status description
     */
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    /**
     * Gets payment amount.
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets payment amount.
     *
     * @param amount the amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets payment fee.
     *
     * @return the fee
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * Sets payment fee.
     *
     * @param fee the fee
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * Gets payment currency.
     * Values are “0”, “1”, “2”, “3”.
     *
     * @return the currency
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * Sets payment currency.
     *
     * @param currency the currency
     */
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    /**
     * Gets notification url.
     *
     * @return the notification url
     */
    public String getNotificationUrl() {
        return notificationUrl;
    }

    /**
     * Sets notification url.
     *
     * @param notificationUrl the notification url
     */
    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    /**
     * Gets if notification was made.
     *
     * @return the notification done
     */
    public Boolean getNotificationDone() {
        return notificationDone;
    }

    /**
     * Sets if notification was made.
     *
     * @param notificationDone the notification done
     */
    public void setNotificationDone(Boolean notificationDone) {
        this.notificationDone = notificationDone;
    }

    /**
     * Gets redirect url.
     *
     * @return the redirect url
     */
    public String getRedirectUrl() {
        return redirectUrl;
    }

    /**
     * Sets redirect url.
     *
     * @param redirectUrl the redirect url
     */
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    /**
     * Gets payment url.
     *
     * @return the payment url
     */
    public String getPaymentUrl() {
        return paymentUrl;
    }

    /**
     * Sets payment url.
     *
     * @param paymentUrl the payment url
     */
    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    /**
     * Gets merchant secret key.
     *
     * @return the merchant secret key
     */
    public String getMerchantSecretKey() {
        return merchantSecretKey;
    }

    /**
     * Sets merchant secret key.
     *
     * @param merchantSecretKey the merchant secret key
     */
    public void setMerchantSecretKey(String merchantSecretKey) {
        this.merchantSecretKey = merchantSecretKey;
    }

    /**
     * Gets returning redirect url.
     *
     * @return the returning redirect url
     */
    public String getReturningRedirectUrl() {
        return returningRedirectUrl;
    }

    /**
     * Sets returning redirect url.
     *
     * @param returningRedirectUrl the returning redirect url
     */
    public void setReturningRedirectUrl(String returningRedirectUrl) {
        this.returningRedirectUrl = returningRedirectUrl;
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
     * Sets turkish national id.
     *
     * @param turkishNationalId the turkish national id
     */
    public void setTurkishNationalId(Long turkishNationalId) {
        this.turkishNationalId = turkishNationalId;
    }
}
