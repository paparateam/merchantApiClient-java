package com.papara.api.payment.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

/**
 * PaymentGetOptions will be used as parameter while acquiring payment information.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class PaymentGetOptions extends PaparaModel {

    @SerializedName("id")
    private String id;

    /**
     * Gets unique payment id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets unique payment id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }
}
