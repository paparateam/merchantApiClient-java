package com.papara.api.common.enumeration;

import com.papara.api.cashdeposit.service.ICashDeposit;
import com.papara.base.PaparaService;

/**
 * EntryType enum is used for defining entry types on transactions.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public enum EntryType {

    /**
     * Banka Transferi: Banka üzerinden Papara'ya para yükleme ya da çekme işlemi.
     * Bank Transfer: Cash deposit or withdrawal
     */
    BANK_TRANSFER(1),

    /**
     * Kurumsal Papara Card İşlemi: Üye işyeri hesabına tanımlı kurumsal kartla yapılmış işlem.
     * Papara Corporate Card Transaction: Transaction which was operated by corporation card assigned to merchant
     */
    CORPORATE_CARD_TRANSACTION(2),

    /**
     * Fiziksel Noktadan Para Yükleme: Anlaşmalı nokta ile Papara'ya para yükleme işlemi.
     * Loading Money From Physical Point: Cash deposit operation from contracted location
     */
    LOADING_MONEY_FROM_PHYSICAL_POINT(6),

    /**
     * Üye İşyeri Ödeme: Papara ile checkout (ödeme al/kabul et) işlemi.
     * Merchant Payment: Checkout via Papara
     */
    MERCHANT_PAYMENT(8),

    /**
     * Ödeme Dağıtım: Papara ile masspayment (ödeme dağıt/gönder) işlemi.
     * Payment Distribution: Masspayment via papara
     */
    PAYMENT_DISTRIBUTION(9),

    /**
     * Kapalı devre ödeme kabul. Papara EDU POS işlemleri.
     * Offline payment. EDU POS via Papara
     */
    EDU_POS(11);

    private int value;

    private EntryType(int value) {
        this.value = value;
    }

    /**
     * Gets EntryType value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets EntryType value.
     *
     * @param value the value
     */
    public void setValue(int value) {
        this.value = value;
    }
}
