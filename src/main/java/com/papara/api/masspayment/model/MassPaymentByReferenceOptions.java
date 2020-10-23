package com.papara.api.masspayment.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

/**
 * MassPaymentByReferenceOptions is used by mass payment service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class MassPaymentByReferenceOptions extends PaparaModel {

    @SerializedName(value = "reference")
    private String reference;

    /**
     * Gets mass payment reference code.
     *
     * @return the mass payment code
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets mass payment reference code.
     *
     * @param reference the mass payment reference code
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
}
