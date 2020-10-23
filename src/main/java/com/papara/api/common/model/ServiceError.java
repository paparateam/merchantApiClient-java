package com.papara.api.common.model;

import com.papara.api.bank.service.IBank;
import com.papara.base.PaparaModel;
import com.papara.base.PaparaService;

/**
 * Papara Service Error Result type. Handles error responses returning from API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class ServiceError extends PaparaModel {

    private String message;
    private Integer code;

    /**
     * Gets error message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets error message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets error code.
     *
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Sets error code.
     *
     * @param code the code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

}
