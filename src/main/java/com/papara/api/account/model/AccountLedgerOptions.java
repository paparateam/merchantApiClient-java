package com.papara.api.account.model;

import com.papara.base.PaparaModel;

/**
 * LedgerListOptions is used by account service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */

public class AccountLedgerOptions extends PaparaModel {

    private String startDate;
    private String endDate;
    private Integer entryType;
    private Integer accountNumber;
    private Integer page;
    private Integer pageSize;

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

    /**
     * Gets account number.
     *
     * @return the account number
     */
    public Integer getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets account number.
     *
     * @param accountNumber the account number
     */
    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets page.
     * If the requested date has more than 1 page of results for the requested PageSize, use this to iterate through pages.
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * Gets number of elements you want to receive per request page. Min=1, Max=50.
     *
     * @return the page size
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Sets page size.
     *
     * @param pageSize the page size
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
