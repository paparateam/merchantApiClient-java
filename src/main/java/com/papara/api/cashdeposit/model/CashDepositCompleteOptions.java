package com.papara.api.cashdeposit.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

/**
 * CashDepositCompleteOptions is used by cash deposit service for providing request parameters.
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */

public class CashDepositCompleteOptions extends PaparaModel {

    @SerializedName(value = "id")
    private Integer id;
    @SerializedName(value = "transactionDate")
    private String transactionDate;

    /**
     * Gets ID of cash deposit request.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets ID of cash deposit request.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets date of cash deposit transaction.
     *
     * @return the date of cash deposit transaction.
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets transaction date.
     *
     * @param transactionDate the transaction date
     */
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

}
