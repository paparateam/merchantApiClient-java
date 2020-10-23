package com.papara.api.payment.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/**
 * PaymentListItem class is used by payment service to match returning payment values from API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class PaymentListItem {

    @SerializedName(value = "id")
    private String id;
    @SerializedName(value = "createdAt")
    private String createdAt;
    @SerializedName(value = "merchantId")
    private String merchantId;
    @SerializedName(value = "userId")
    private String userId;
    @SerializedName(value = "paymentMethod")
    private Integer paymentMethod;
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
    private String notificationDone;
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
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
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
    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets payment method.
     *
     * @param paymentMethod the payment method
     */
    public void setPaymentMethod(Integer paymentMethod) {
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
     * Gets reference id.
     *
     * @return the reference id
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * Sets reference id.
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
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Gets status description.
     *
     * @return the status description
     */
    public String getStatusDescription() {
        return statusDescription;
    }

    /**
     * Sets status description.
     *
     * @param statusDescription the status description
     */
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
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
     * Gets payment currency.
     * Values are “0”, “1”, “2”, “3”.
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
    public String getNotificationDone() {
        return notificationDone;
    }

    /**
     * Sets if notification was made.
     *
     * @param notificationDone the notification done
     */
    public void setNotificationDone(String notificationDone) {
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
