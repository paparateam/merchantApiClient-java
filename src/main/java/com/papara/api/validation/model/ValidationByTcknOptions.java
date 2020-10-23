package com.papara.api.validation.model;

import com.papara.base.PaparaModel;

/**
 * ValidationByTcknOptions is used by validation service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class ValidationByTcknOptions extends PaparaModel {

    private Long tckn;

    /**
     * Gets national identity number.
     *
     * @return the tckn
     */
    public Long getTckn() {
        return tckn;
    }

    /**
     * Sets national identity number.
     *
     * @param tckn the tckn
     */
    public void setTckn(Long tckn) {
        this.tckn = tckn;
    }
}
