package com.papara.api.masspayment.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

/** MassPaymentByIdOptions is used by mass payment service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public class MassPaymentByIdOptions {

    @SerializedName(value = "id")
    private String id;

    /**
     * Gets mass payment id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets smass payment id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }
}
