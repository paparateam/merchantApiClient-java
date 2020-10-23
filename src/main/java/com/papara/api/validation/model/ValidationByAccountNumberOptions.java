package com.papara.api.validation.model;

import com.papara.base.PaparaModel;

/**
 * ValidationByAccountNumberOptions is used by validation service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class ValidationByAccountNumberOptions extends PaparaModel {

    private Long accountNumber;

    /**
     * Gets Papara account number.
     *
     * @return the account number
     */
    public Long getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets Papara account number.
     *
     * @param accountNumber the account number
     */
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
