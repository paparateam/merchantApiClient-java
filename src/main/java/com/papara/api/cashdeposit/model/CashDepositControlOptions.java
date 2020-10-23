package com.papara.api.cashdeposit.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.math.BigDecimal;

/**
 * CashDepositControlOptions is used by cash deposit service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public class CashDepositControlOptions extends PaparaModel {

    @SerializedName(value = "referenceCode")
    private String referenceCode;
    @SerializedName(value = "amount")
    private BigDecimal amount;

    /**
     * Gets reference number of cash deposit request.
     * @return the reference number of cash deposit request.
     */
    public String getReferenceCode() {
        return referenceCode;
    }

    /**
     * Sets reference number of cash deposit request.
     *
     * @param referenceCode the reference number of cash deposit request.
     */
    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    /**
     * Gets cash deposit amount..
     *
     * @return the cash deposit amount.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets cash deposit amount..
     *
     * @param amount the cash deposit amount.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
