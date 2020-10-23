package com.papara.api.account.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/**
 * AccountSettlement class is used by account service to match returning settlement values from API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class AccountSettlement extends PaparaModel {

    @SerializedName(value = "count")
    private Integer count;
    @SerializedName(value = "volume")
    private BigDecimal volume;

    /**
     * Gets transaction count.
     *
     * @return the transaction count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Sets transaction count.
     *
     * @param count the transaction count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Gets transaction volume.
     *
     * @return the transaction volume
     */
    public BigDecimal getVolume() {
        return volume;
    }

    /**
     * Sets volume.
     *
     * @param volume the transaction volume
     */
    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }
}

