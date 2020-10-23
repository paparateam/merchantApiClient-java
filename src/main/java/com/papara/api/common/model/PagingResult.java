package com.papara.api.common.model;

import com.google.gson.annotations.SerializedName;
import com.papara.base.PaparaModel;

import java.util.List;

/**
 * Papara Paging type. Handles paging data types sending to and returning from API.
 *
 * @param <D> the type parameter
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see PaparaModel
 * @since 0.0.1
 */
public class PagingResult<D> extends PaparaModel {

    @SerializedName(value = "items")
    private List<D> items;
    @SerializedName(value = "page")
    private Integer page;
    @SerializedName(value = "pageItemCount")
    private Integer pageItemCount;
    @SerializedName(value = "totalItemCount")
    private Integer totalItemCount;
    @SerializedName(value = "totalPageCount")
    private Integer totalPageCount;
    @SerializedName(value = "pageSkip")
    private Integer pageSkip;

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<D> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(List<D> items) {
        this.items = items;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * Gets page item count.
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

    /**
     * Gets total item count.
     *
     * @return the total item count
     */
    public Integer getTotalItemCount() {
        return totalItemCount;
    }

    /**
     * Sets total item count.
     *
     * @param totalItemCount the total item count
     */
    public void setTotalItemCount(Integer totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    /**
     * Gets total page count.
     *
     * @return the total page count
     */
    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    /**
     * Sets total page count.
     *
     * @param totalPageCount the total page count
     */
    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    /**
     * Gets page skip.
     *
     * @return the page skip
     */
    public Integer getPageSkip() {
        return pageSkip;
    }

    /**
     * Sets page skip.
     *
     * @param pageSkip the page skip
     */
    public void setPageSkip(Integer pageSkip) {
        this.pageSkip = pageSkip;
    }
}
