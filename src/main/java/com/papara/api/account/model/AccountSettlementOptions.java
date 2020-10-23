package com.papara.api.account.model;

import com.papara.base.PaparaModel;

/**
 * AccountSettlementOptions is used by account service for providing settlement request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
// SettlementRequestModel
public class AccountSettlementOptions extends PaparaModel {

    private String startDate;
    private String endDate;
    private Integer entryType;

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets entry type.
     * BankTransfer = 1
     * CorporateCardTransaction = 2,
     * LoadingMoneyFromPhysicalPoint = 6,
     * MerchantPayment = 8,
     * PaymentDistribution = 9,
     * EduPos = 11.
     *
     * @return the entry type
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
