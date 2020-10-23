package com.papara.api.account.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/**
 * AccountBalance class is used by account service to match returning account balance value from API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */

public class AccountBalance extends PaparaModel {

    @SerializedName(value = "currency")
    private Integer currency;
    @SerializedName(value = "totalBalance")
    private BigDecimal totalBalance;
    @SerializedName(value = "lockedBalance")
    private BigDecimal lockedBalance;
    @SerializedName(value = "availableBalance")
    private BigDecimal availableBalance;

    /**
     * Gets currency.
     *
     * @return the currency
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * Sets currency.
     *
     * @param currency the currency
     */
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    /**
     * Gets total balance.
     *
     * @return the total balance
     */
    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    /**
     * Sets total balance.
     *
     * @param totalBalance the total balance
     */
    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    /**
     * Gets locked balance.
     *
     * @return the locked balance
     */
    public BigDecimal getLockedBalance() {
        return lockedBalance;
    }

    /**
     * Sets locked balance.
     *
     * @param lockedBalance the locked balance
     */
    public void setLockedBalance(BigDecimal lockedBalance) {
        this.lockedBalance = lockedBalance;
    }

    /**
     * Gets available balance.
     *
     * @return the available balance
     */
    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    /**
     * Sets available balance.
     *
     * @param availableBalance the available balance
     */
    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }
}
