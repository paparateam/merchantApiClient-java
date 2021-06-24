package com.papara.base;

/**
 * Endpoint constans
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public class Endpoint {

    public static final String ACCOUNT = "/account";
    public static final String ACCOUNT_LEDGERS = "/account/ledgers";
    public static final String ACCOUNT_SETTLEMENT = "/account/settlement";

    public static final String BANK_ACCOUNTS = "/banking/bankaccounts";
    public static final String BANK_WITHDRAWAL = "/banking/withdrawal";

    public static final String CASHDEPOSIT_ID = "/cashdeposit?id=";
    public static final String CASHDEPOSIT_REFERENCE = "/cashdeposit/byreference?reference=";
    public static final String CASHDEPOSIT_DATE = "/cashdeposit/bydate?StartDate={0}&EndDate={1}&PageIndex={2}&PageItemCount={3}";
    public static final String CASHDEPOSIT = "/cashdeposit";
    public static final String CASHDEPOSIT_ACCOUNT = "/cashdeposit/accountnumber";
    public static final String CASHDEPOSIT_TCKN = "/cashdeposit/tckn";
    public static final String CASHDEPOSIT_SETTLEMENT = "/cashdeposit/settlement";
    public static final String CASHDEPOSIT_PROVISION_SETTLEMENT = "/cashdeposit/provision/settlement";
    public static final String CASHDEPOSIT_PROVISION_PHONE = "/cashdeposit/provision/phonenumber";
    public static final String CASHDEPOSIT_PROVISION_ACCOUNT = "/cashdeposit/provision/accountnumber";
    public static final String CASHDEPOSIT_PROVISION_TCKN = "/cashdeposit/provision/tckn";
    public static final String CASHDEPOSIT_PROVISION_TCKN_CONTROL = "/cashdeposit/provision/withtckncontrol";
    public static final String CASHDEPOSIT_PROVISION_COMPLETE = "/cashdeposit/provision/complete";
    public static final String CASHDEPOSIT_PROVISION_REFERENCE_CONTROL = "/cashdeposit/provisionbyreference/control";
    public static final String CASHDEPOSIT_PROVISION_REFERENCE_COMPLETE = "/cashdeposit/provisionbyreference/complete";

    public static final String VALIDATION_ACCOUNT = "/validation/accountNumber?accountNumber={0}";
    public static final String VALIDATION_TCKN = "/validation/tckn?tckn={0}";
    public static final String VALIDATION_PHONE = "/validation/phonenumber?phonenumber={0}";
    public static final String VALIDATION_EMAIL = "/validation/email?email={0}";
    public static final String VALIDATION_ID = "/validation/id?userId={0}";

    public static final String PAYMENTS = "/payments";
    public static final String PAYMENTS_REFERENCE = "/payments/reference?referenceId={0}";
    public static final String PAYMENTS_ID = "/payments?id={0}";
    public static final String PAYMENT_REFUND = "/payments?id={0}";
    public static final String PAYMENT_LIST = "/payments/list?pageIndex={0}&pageItemCount={1}";

    public static final String MASSPAYMENT_ACCOUNT = "/masspayment";
    public static final String MASSPAYMENT_EMAIL = "/masspayment/email";
    public static final String MASSPAYMENT_PHONE = "/masspayment/phone";
    public static final String MASSPAYMENT_ID = "/masspayment?id={0}";
    public static final String MASSPAYMENT_REFERENCE = "/masspayment/byreference?reference={0}";

    public static final String RECURRINGMASSPAYMENT_PHONE = "/recurringmasspayment/phone";
    public static final String RECURRINGMASSPAYMENT_EMAIL = "/recurringmasspayment/email";
    public static final String RECURRINGMASSPAYMENT_ACCOUNT = "/recurringmasspayment";

}
