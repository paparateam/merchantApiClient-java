package com.papara.api.account.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Account class is used by account service to match returning account value from API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaService
 * @since 0.0.1
 */
public class Account extends PaparaModel {

    @SerializedName(value = "merchantBalanceModelList")
    private List<AccountBalance> merchantBalanceModelList = new ArrayList<AccountBalance>();
    @SerializedName(value = "legalName")
    private String legalName;
    @SerializedName(value = "brandName")
    private String brandName;
    @SerializedName(value = "allowedPaymentTypes")
    private List<AccountPaymentType> allowedPaymentTypes = new ArrayList<AccountPaymentType>();

    /**
     * Gets merchant balance model list.
     *
     * @return the merchant balance model list
     */
    public List<AccountBalance> getMerchantBalanceModelList() {
        return merchantBalanceModelList;
    }

    /**
     * Sets merchant balance model list.
     *
     * @param merchantBalanceModelList the merchant balance model list
     */
    public void setMerchantBalanceModelList(List<AccountBalance> merchantBalanceModelList) {
        this.merchantBalanceModelList = merchantBalanceModelList;
    }

    /**
     * Gets legal name.
     *
     * @return the legal name
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * Sets legal name.
     *
     * @param legalName the legal name
     */
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    /**
     * Gets brand name.
     *
     * @return the brand name
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * Sets brand name.
     *
     * @param brandName the brand name
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * Gets allowed payment types.
     *
     * @return the allowed payment types
     */
    public List<AccountPaymentType> getAllowedPaymentTypes() {
        return allowedPaymentTypes;
    }

    /**
     * Sets allowed payment types.
     *
     * @param allowedPaymentTypes the allowed payment types
     */
    public void setAllowedPaymentTypes(List<AccountPaymentType> allowedPaymentTypes) {
        this.allowedPaymentTypes = allowedPaymentTypes;
    }
}
