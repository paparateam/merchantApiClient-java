package com.papara.api.validation.model;

import com.papara.base.PaparaModel;

/**
 * ValidationByEmailOptions is used by validation service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class ValidationByEmailOptions extends PaparaModel {

    private String email;

    /**
     * Gets e-mail address registered to Papara.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets e-mail address registered to Papara.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
