package com.papara.api.common.model;

import com.papara.base.PaparaModel;

/**
 * Papara Service Success Result type. Handles success responses returning from API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see com.papara.base.PaparaModel
 * @since 0.0.1
 */
public class ServiceSuccessResult extends PaparaModel {

    private String message;
    private Integer code;

    /**
     * Gets success message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets success message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets success code.
     *
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Sets success code.
     *
     * @param code the code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

}
