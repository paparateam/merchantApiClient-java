package com.papara.api.payment.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

/**
 * PaymentRefundOptions is used by payment service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class PaymentRefundOptions extends PaparaModel {

    @SerializedName("id")
    private String id;

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
}
