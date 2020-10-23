package com.papara.api.validation.model;

import com.papara.base.PaparaModel;

/**
 * ValidationByPhoneNumberOptions is used by validation service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class ValidationByPhoneNumberOptions extends PaparaModel {

    private String phoneNumber;

    /**
     * Gets phone number registered to Papara.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number registered to Papara.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
