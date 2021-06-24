package com.papara.api.common.enumeration;

/**
 * Currency enum is used for defining currencies on transactions.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public enum Currency {

    /**
     * Try currency.
     */
    TRY(0),
    /**
     * Usd currency.
     */
    USD(1),
    /**
     * Eur currency.
     */
    EUR(2),
    /**
     * Gbp currency.
     */
    GBP(3);

    private Integer value;

    private Currency(int value) {
        this.value = value;
    }

    /**
     * Gets currency value.
     *
     * @return the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Sets currency value.
     *
     * @param value the value
     */
    public void setValue(int value) {
        this.value = value;
    }
}
