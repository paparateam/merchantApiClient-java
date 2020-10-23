package com.papara.api.account.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

/**
 * AccountPaymentType class is used by account service to match returning payment types from API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
// MerchantPaymentTypeModel
public class AccountPaymentType extends PaparaModel {

    @SerializedName(value = "paymentMethod")
    private Integer paymentMethod;

    /**
     * Gets payment method.
     * 0- PaparaAccount - Papara Account Balance.
     * 1- Card - Registered Credit Card.
     * 2- Mobile - Mobile Payment.
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
}
