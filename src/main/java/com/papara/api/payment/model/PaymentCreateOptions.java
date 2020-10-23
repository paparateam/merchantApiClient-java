package com.papara.api.payment.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/**
 * PaymentCreateOptions is used by payment service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class PaymentCreateOptions extends PaparaModel {

    @SerializedName("amount")
    private BigDecimal amount;
    @SerializedName("referenceId")
    private String referenceId;
    @SerializedName("orderDescription")
    private String orderDescription;
    @SerializedName("notificationUrl")
    private String notificationUrl;
    @SerializedName("redirectUrl")
    private String redirectUrl;
    @SerializedName("turkishNationalId")
    private Long turkishNationalId;

    /**
     * Gets payment amount. The amount of the payment transaction.
     * Exactly this amount will be taken from the account of the user who made the payment, and this amount will be displayed to the user on the payment screen.
     * Amount field can be minimum 1.00 and maximum 500000.00.
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
     * Gets reference id. Reference information of the payment transaction in the merchant system.
     * The transaction will be returned to the merchant without being changed in the result notifications as it was sent to Papara.
     * Must be no more than 100 characters. This area does not have to be unique and Papara does not make such a check.
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
     * Gets order description. Description of the payment transaction.
     * The sent value will be displayed to the user on the Papara checkout page.
     * Having a description that accurately identifies the transaction initiated by the user, will increase the chance of successful payment.
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
     * Gets notification url. The URL to which payment notification requests (IPN) will be sent.
     * With this field, the URL where the POST will be sent to the payment merchant must be sent.
     * To the URL sent with "notificationUrl", Papara will send a payment object containing all information of the payment with an HTTP POST request immediately after the payment is completed.
     * If the merchant returns 200 OK to this request, no notification will be made again.
     * If the merchant does not return 200 OK to this notification, Papara will continue to make payment notification (IPN) requests for 24 hours until the merchant returns to 200 OK.
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
     * Gets redirect url. URL to which the user will be redirected at the end of the process.
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
     * Gets turkish national id. It provides the control of the identity information sent by the user who will receive the payment, in the Papara system.
     * In case of a conflict of credentials, the transaction will not take place.
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
