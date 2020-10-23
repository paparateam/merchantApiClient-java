package com.papara.api.cashdeposit.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

/**
 * CashDepositSettlementOptions is used by cash deposit service for providing request parameters
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @see PaparaModel
 * @version 0.0.1
 * @since 0.0.1
 */

public class CashDepositSettlementOptions extends PaparaModel {

    @SerializedName(value = "startDate")
    private String startDate;
    @SerializedName(value = "endDate")
    private String endDate;
    @SerializedName(value = "entryType")
    private Integer entryType;

    /**
     * Gets start date for settlement.
     *
     * @return the start date for settlement.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets start date for settlement.
     *
     * @param startDate the start date for settlement.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date for settlement.
     *
     * @return the end date for settlement
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets end date for settlement.
     *
     * @param endDate the end date for settlement
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets entry type.
     * Gets entry type.
     * Entry type.
     * 1: Bank Transfer(Deposits/Withdrawals)
     * 6: Cash Deposit
     * 8: Received Payment(Checkout)
     * 9: Sent Payment (MassPayment) = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17'].
     */
    public Integer getEntryType() {
        return entryType;
    }

    /**
     * Sets entry type.
     *
     * @param entryType the entry type
     */
    public void setEntryType(Integer entryType) {
        this.entryType = entryType;
    }
}
