package com.papara.api.payment.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

/**
 * PaymentListOptions is used by payment service for providing request parameters.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class PaymentListOptions extends PaparaModel {

    @SerializedName("pageIndex")
    private Integer pageIndex;
    @SerializedName("pageItemCount")
    private Integer pageItemCount;

    /**
     * Gets page index. It is the index number of the page that is wanted to display from the pages calculated on the basis of the number of records (pageItemCount) desired to be displayed on a page.
     * Note: the first page is always 1.
     *
     * @return the page index
     */
    public Integer getPageIndex() {
        return pageIndex;
    }

    /**
     * Sets page index.
     *
     * @param pageIndex the page index
     */
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * Gets page item count. The number of records that are desired to be displayed on a page.
     *
     * @return the page item count
     */
    public Integer getPageItemCount() {
        return pageItemCount;
    }

    /**
     * Sets page item count.
     *
     * @param pageItemCount the page item count
     */
    public void setPageItemCount(Integer pageItemCount) {
        this.pageItemCount = pageItemCount;
    }
}
