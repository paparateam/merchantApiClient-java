package com.papara.api.common.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

/**
 *  CurrencyInfo class is used by account ledger model to get or set returning currency values from API.
 *  @author Burak Serpici <burak.serpici@crosstech.com.tr>
 *  @version 0.0.1
 *  @since 0.0.1
 */
public class CurrencyInfo extends PaparaModel {

    @SerializedName(value = "currencyEnum")
    private Integer currencyEnum;
    @SerializedName(value = "symbol")
    private String symbol;
    @SerializedName(value = "code")
    private String code;
    @SerializedName(value = "preferredDisplayCode")
    private String preferredDisplayCode;
    @SerializedName(value = "name")
    private String name;
    @SerializedName(value = "isCryptocurrency")
    private boolean isCryptocurrency;
    @SerializedName(value = "precision")
    private Integer precision;
    @SerializedName(value = "iconUrl")
    private String iconUrl;

    /**
     * Gets currency type.
     *
     * @return the currency enum
     */
    public Integer getCurrencyEnum() {
        return currencyEnum;
    }

    /**
     * Sets currency type.
     *
     * @param currencyEnum the currency enum
     */
    public void setCurrencyEnum(int currencyEnum) {
        this.currencyEnum = currencyEnum;
    }

    /**
     * Gets currency symbol.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets currency symbol.
     *
     * @param symbol the symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets currency code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets currency code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets preferred currency display code.
     *
     * @return the preferred display code
     */
    public String getPreferredDisplayCode() {
        return preferredDisplayCode;
    }

    /**
     * Sets preferred currency display code.
     *
     * @param preferredDisplayCode the preferred display code
     */
    public void setPreferredDisplayCode(String preferredDisplayCode) {
        this.preferredDisplayCode = preferredDisplayCode;
    }

    /**
     * Gets currency name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets currency name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Is cryptocurrency boolean.
     *
     * @return the boolean
     */
    public boolean isCryptocurrency() {
        return isCryptocurrency;
    }

    /**
     * Sets cryptocurrency.
     *
     * @param cryptocurrency the cryptocurrency
     */
    public void setCryptocurrency(boolean cryptocurrency) {
        isCryptocurrency = cryptocurrency;
    }

    /**
     * Gets currency precision.
     *
     * @return the precision
     */
    public Integer getPrecision() {
        return precision;
    }

    /**
     * Sets currency precision.
     *
     * @param precision the precision
     */
    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    /**
     * Gets currency icon url.
     *
     * @return the icon url
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * Sets currency icon url.
     *
     * @param iconUrl the icon url
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
