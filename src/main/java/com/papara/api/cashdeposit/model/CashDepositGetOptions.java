package com.papara.api.cashdeposit.model;

import com.google.gson.annotations.SerializedName;

/**
 * CashDepositGetOptions is used by cash deposit service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */

public class CashDepositGetOptions {

    @SerializedName(value = "id")
    private String id;

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
}
