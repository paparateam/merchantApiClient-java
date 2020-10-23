package com.papara.api.bank.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

/**
 * BankAccount class is used by banking service to match returning bank accounts from API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @see PaparaModel
 * @version 0.0.1
 * @since 0.0.1
 */
public class BankAccount extends PaparaModel {

    @SerializedName(value = "bankAccountId")
    private Integer bankAccountId;
    @SerializedName(value = "bankName")
    private String bankName;
    @SerializedName(value = "branchCode")
    private String branchCode;
    @SerializedName(value = "iban")
    private String iban;
    @SerializedName(value = "accountCode")
    private String accountCode;
    @SerializedName(value = "description")
    private String description;
    @SerializedName(value = "currency")
    private String currency;

    /**
     * Gets bank account id.
     *
     * @return the bank account id
     */
    public Integer getBankAccountId() {
        return bankAccountId;
    }

    /**
     * Sets bank account id.
     *
     * @param bankAccountId the bank account id
     */
    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    /**
     * Gets bank name.
     *
     * @return the bank name
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets bank name.
     *
     * @param bankName the bank name
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * Gets branch code.
     *
     * @return the branch code
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * Sets branch code.
     *
     * @param branchCode the branch code
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * Gets iban.
     *
     * @return the iban
     */
    public String getIban() {
        return iban;
    }

    /**
     * Sets iban.
     *
     * @param iban the iban
     */
    public void setIban(String iban) {
        this.iban = iban;
    }

    /**
     * Gets account code.
     *
     * @return the account code
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * Sets account code.
     *
     * @param accountCode the account code
     */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets currency.
     *
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets currency.
     *
     * @param currency the currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
