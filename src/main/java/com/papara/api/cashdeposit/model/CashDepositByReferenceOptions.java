package com.papara.api.cashdeposit.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

/**
 * CashDepositByReferenceOptions is used by cash deposit service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public class CashDepositByReferenceOptions {

    @SerializedName(value = "reference")
    private String reference;

    /**
     * Gets cash deposit reference no. Reference no is required..
     *
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets cash deposit reference no. Reference no is required..
     *
     * @param reference the reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
}
