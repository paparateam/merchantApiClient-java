package com.papara.api.validation.model;

import com.google.gson.annotations.SerializedName;
import com.papara.api.payment.service.IPayment;
import com.papara.base.PaparaModel;
import com.papara.base.PaparaService;

/**
 * Validation class is used by validation service to match returning user value from API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class Validation extends PaparaModel {

    @SerializedName(value = "userId")
    private String userId;
    @SerializedName(value = "firstName")
    private String firstName;
    @SerializedName(value = "lastName")
    private String lastName;
    @SerializedName(value = "email")
    private String email;
    @SerializedName(value = "phoneNumber")
    private String phoneNumber;
    @SerializedName(value = "tckn")
    private String tckn;
    @SerializedName(value = "accountNumber")
    private Integer accountNumber;

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets tckn.
     *
     * @return the tckn
     */
    public String getTckn() {
        return tckn;
    }

    /**
     * Sets tckn.
     *
     * @param tckn the tckn
     */
    public void setTckn(String tckn) {
        this.tckn = tckn;
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
}
