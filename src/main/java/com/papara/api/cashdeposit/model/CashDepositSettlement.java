package com.papara.api.cashdeposit.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/**
 * Settlement class is used by account service to match returning settlement values API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @see PaparaModel
 * @version 0.0.1
 * @since 0.0.1
 */

public class CashDepositSettlement extends PaparaModel {

    @SerializedName(value = "count")
    private Integer count;
    @SerializedName(value = "volume")
    private BigDecimal volume;

    /**
     * Gets transaction count.
     *
     * @return the transaction  count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Sets transaction  count.
     *
     * @param count the transaction  count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Gets transaction  volume.
     *
     * @return the transaction  volume
     */
    public BigDecimal getVolume() {
        return volume;
    }

    /**
     * Sets transaction  volume.
     *
     * @param volume the transaction  volume
     */
    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }
}

