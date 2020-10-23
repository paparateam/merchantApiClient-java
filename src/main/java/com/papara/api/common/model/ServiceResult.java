package com.papara.api.common.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

/**
 * Papara Service Result type. Handles response data types returning from API.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @param <T> the type parameter
 * @version 0.0.1
 * @see com.papara.base.PaparaModel
 * @since 0.0.1
 */
public class ServiceResult<T> extends PaparaModel {

    @SerializedName("data")
    private T data;
    @SerializedName("succeeded")
    private Boolean succeeded;
    @SerializedName("error")
    private ServiceError error;
    @SerializedName("result")
    private ServiceSuccessResult result;

    /**
     * Gets result data.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets result data.
     *
     * @param data the data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Is result succeeded boolean.
     *
     * @return the boolean
     */
    public Boolean isSucceeded() {
        return succeeded;
    }

    /**
     * Sets result succeeded.
     *
     * @param succeeded the succeeded
     */
    public void setSucceeded(Boolean succeeded) {
        this.succeeded = succeeded;
    }

    /**
     * Gets result error.
     *
     * @return the error
     */
    public ServiceError getError() {
        return error;
    }

    /**
     * Sets result error.
     *
     * @param error the error
     */
    public void setError(ServiceError error) {
        this.error = error;
    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public ServiceSuccessResult getResult() {
        return result;
    }

    /**
     * Sets result.
     *
     * @param result the result
     */
    public void setResult(ServiceSuccessResult result) {
        this.result = result;
    }

}
