package com.papara.api.payment.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;


public class PaymentReferenceGetOptions extends PaparaModel {

    @SerializedName("referenceId")
    private String referenceId;

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
}
