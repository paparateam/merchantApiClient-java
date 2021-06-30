# Table of Contents

<a href="#intro">Intro</a>

<a href="#enums">Enums</a>

<a href="#account">Account</a>

<a href="#banking">Banking</a>

<a href="#cash-deposit">Cash Deposit</a>

<a href="#mass-payment">Mass Payment</a>

<a href="#recurring-mass-payment">Recurring Mass Payment</a>

<a href="#payments">Payments</a>

<a href="#validation">Validation</a>

<a href="#response-types">Response Types</a>

# <a name="intro">Intro</a> 

Integrating Papara into your software requires following;

1. Obtain your API Key. So Papara can authenticate integration’s API requests. To obtain your API Key, follow https://merchant.test.papara.com/ URL. After sucessfully logged in, API Key can be viewed on https://merchant.test.papara.com/APIInfo
2. Install client library. So your integration can interact with the Papara API. Install operations are like following.

# Dependencies

#### Maven

```xml
<dependency>
	<groupId>com.papara</groupId>
	<artifactId>java-sdk</artifactId>
	<version>1.0.0</version>
</dependency>
```

#### Gradle

In `build.gradle` file you should define `jcenter` as your repositories beside others.

```groovy

repositories {
    jcenter()
}

dependencies {
    implementation 'com.papara:java-sdk:1.0.0'
}

```

# Configurations

### Java Setup

Before connecting to API, java developers should configure client settings. You could define environment variable for your **API Key** and **Environment** you want to request. Beside that also any alternative solution could be used. In Spring Framework we could define variables in `applaction.properties` and read it in the code via`@Value` annotation. 

In the properties file;

```properties
papara-api-key = YOUR_API_KEY_HERE        // Papara Registered API KEY
papara-env = sandbox                      // Target environment. sandbox or live
```

In the code;

```java
@Value("${papara-api-key}")
private String apiKey;

@Value("${papara-env}")
private String env;
```

After these we can create a client;

```java
APIContext context = new APIContext(apiKey, null, env);  // env also could be Constants.SANDBOX or Constants.LIVE
PaparaClient client = new PaparaClient(context);
```

### Java Test Request

After everything is set, use code block below to test everything works perfectly.

```java
@RestController
public class PaparaController {

    @Value("${papara-api-key}")
    private String apiKey;

    @Value("${papara-api-key}")
    private String env;

    @GetMapping
    public ServiceResult<Account> getAccount() throws PaparaRESTException {
        APIContext context = new APIContext(apiKey, null, env);
        PaparaClient client = new PaparaClient(context);
        return client.getAccountService().getAccount();
    }
}
```

# <a name="enums">Enums</a>

# CashDepositProvisionStatus

When a cash deposit request was made, following statuses will return and display the status of provision.

| **Key**         | **Value** | **Description**                      |
| --------------- | --------- | ------------------------------------ |
| Pending         | 0         | Cash deposit is pending provision.   |
| Complete        | 1         | Cash Deposit is completed            |
| Cancel          | 2         | Cash Deposit is cancelled            |
| ReadyToComplete | 3         | Cash Deposit is ready for completion |

 

# Currency

All currencies on the API are listed below.

| **Key** | **Value** | **Description** |
| ------- | --------- | --------------- |
| TRY     | 0         | Turkish Lira    |
| USD     | 1         | U.S Dollar      |
| EUR     | 2         | Euro            |

 

# EntryType

Entry types are used in ledgers and cash deposits in order to track the money in the operation. Possible entry types are listed below.

| **Key**                       | **Value** | **Description**                                              |
| ----------------------------- | --------- | ------------------------------------------------------------ |
| BankTransfer                  | 1         | Bank Transfer: Cash deposit or withdrawal                    |
| CorporateCardTransaction      | 2         | Papara Corporate Card Transaction:  Transaction which was operated by corporation card assigned to merchant |
| LoadingMoneyFromPhysicalPoint | 6         | Loading Money From Physical Point: Cash  deposit operation from contracted location |
| MerchantPayment               | 8         | Merchant Payment: Checkout via Papara                        |
| PaymentDistribution           | 9         | Payment Distribution: Masspayment via  papara                |
| EduPos                        | 11        | Offline payment. EDU POS via Papara                          |

 

# PaymentMethod

Three types of payment is accepted in the system. Possible payment methods are listed below. 

| **Key**       | **Value** | **Description**        |
| ------------- | --------- | ---------------------- |
| PaparaAccount | 0         | Papara Account Balance |
| Card          | 1         | Registered Credit Card |
| Mobile        | 2         | Mobile Payment         |

 

# PaymentStatus

After a payment was done, API returns the payment status which are shown below.

| **Key**   | **Value** | **Description**            |
| --------- | --------- | -------------------------- |
| Pending   | 0         | Payment waiting            |
| Completed | 1         | User completed the payment |
| Refunded  | 2         | Order refunded             |

# <a name="account">Account</a>

This part contains the technical integration information prepared for the use of the account and balance information of the merchant. Account and balance information on Papara account can be retrieved by Account service. Developers can also retrieve the balance history, which contains a list of transactions that contributed to the balance.

## Get Account Information

Returns the merchant account and balance information. Balance information contains current balance, available funds and unavailable funds, whilst account information contains brand name and full title of the merchant. To perform this operation use `getAccount` method on `Account` service. 

### Account Model

`Account` class is used by account service to match returning account value from API and contains account information.

| **Variable Name**        | **Type**                 | **Description**                        |
| ------------------------ | ------------------------ | -------------------------------------- |
| legalName                | string                   | Gets or sets merchant’s company title. |
| brandName                | string                   | Gets or sets brand name.               |
| allowedPaymentTypes      | List<AccountPaymentType> | Gets or sets allowed payment types.    |
| merchantBalanceModelList | List<AccountBalance>     | Gets or sets account balances          |

### AccountPaymentType Model

`AccountPaymentType` class is used by account service to match returning account value from API. `allowedPaymentType` displays allowed payment types.

| **Variable Name** | **Type** | **Description**                                              |
| ----------------- | -------- | ------------------------------------------------------------ |
| paymentMethod     | Integer  | Gets or sets payment method.<br />0 - Papara Account Balance  <br />1 – Credit/Debit Card<br/>2- Mobile - Mobile Payment. |

### AccountBalance Model

`AccountBalance` class is used by account service to match returning account balance value from API. Account balance shows current balance figures and lists three types of balances and general currency.

| **Variable Name** | **Type**   | **Description**                |
| ----------------- | ---------- | ------------------------------ |
| currency          | Integer    | Gets or sets currency          |
| totalBalance      | BigDecimal | Gets or sets total balance     |
| lockedBalance     | BigDecimal | Gets or sets locked balance    |
| availableBalance  | BigDecimal | Gets or sets available balance |

### Service Method

#### Purpose

Return account information and current balance for authorized merchant.

| **Method** | **Params** | **Return Type**        |
| ---------- | ---------- | ---------------------- |
| getAccount | None       | ServiceResult<Account> |

#### Usage

``` java
 /**
     * Returns account information and current balance for authorized merchant.
     *
     * @return ServiceResult with the generic type of Account
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Account> getAccount() throws PaparaRESTException {
        ServiceResult<Account> result = accountService.getAccount();
        return result;
    }
```



## List Ledgers

Returns the merchant account history (list of transactions) in paged format. This method is used for listing all transactions made for a merchant including resulting balance for each transaction.  To perform this operation use `getAccountLedgerss` method on `Account` service. `startDate` and `endDate` should be provided.

### AccountLedger Model

`AccountLedger` class is used by account service to match returning ledger values from API. Represents a transaction itself.

| **Variable Name**          | **Type**     | **Description**                                              |
| -------------------------- | ------------ | ------------------------------------------------------------ |
| id                         | Integer      | Gets or sets merchant ID                                     |
| createdAt                  | DateTime     | Gets or sets created date of a ledger                        |
| entryType                  | Integer      | Gets or sets entry type                                      |
| entryTypeName              | String       | Gets or sets entry type name                                 |
| amount                     | BigDecimal   | Gets or sets amount                                          |
| fee                        | BigDecimal   | Gets or sets fee                                             |
| currency                   | Integer      | Gets or sets currency                                        |
| currencyInfo               | CurrencyInfo | Gets or sets currency information                            |
| resultingBalance           | BigDecimal   | Gets or sets resulting balance                               |
| description                | String       | Gets or sets description                                     |
| massPaymentId              | String       | Gets or sets mass payment Id. It is the  unique value sent by the merchant to prevent duplicate repetition in payment  transactions. It is displayed in transaction records of masspayment type in  account transactions to ensure control of the transaction. It will be null in  other payment types. |
| checkoutPaymentId          | String       | Gets or sets checkout payment ID. It is  the ID field in the data object in the payment record transaction. It is the  unique identifier of the payment transaction. It is displayed in transaction  records of checkout type in account transactions. It will be null in other  payment types. |
| checkoutPaymentReferenceId | String       | Gets or sets checkout reference ID. This  is the referenceId field sent when creating the payment transaction record.  It is the reference information of the payment transaction in the merchant  system. It is displayed in transaction records of checkout type in account  transactions. It will be null in other payment types |

### CurrencyInfo Model

`CurrencyInfo` class is used by account ledger model to get or set returning currency values from API. Represents the currency information available in a ledger.

| **Variable Name**    | **Type** | **Description**                                |
| -------------------- | -------- | ---------------------------------------------- |
| currencyEnum         | Integer  | Gets or sets currency type.                    |
| symbol               | String   | Gets or sets currency symbol                   |
| code                 | String   | Gets or sets currency code                     |
| preferredDisplayCode | String   | Gets or sets currency's preferred display code |
| name                 | String   | Gets or sets currency name                     |
| isCryptocurrency     | boolean  | Gets or sets if it is a cryptocurrency or not  |
| precision            | Integer  | Gets or sets currency precision                |
| iconUrl              | String   | Gets or sets currency icon URL                 |

### AccountLedgerOptions Model

`AccountLedgerOptions`  is used by account service for providing request parameters for ledger listing operation. 

| **Variable Name** | **Type** | **Description**                                              |
| ----------------- | -------- | ------------------------------------------------------------ |
| startDate         | String   | Gets or sets start date for transactions                     |
| endDate           | String   | Gets or sets end date for transactions                       |
| entryType         | Integer  | Gets or sets entry types                                     |
| accountNumber     | Integer  | Gets or sets merchant account number                         |
| page              | Integer  | Gets or sets the requested page number. If  the requested date has more than 1 page of results for the requested  PageSize, use this to iterate through pages |
| pageSize          | Integer  | Gets or sets number of elements you want  to receive per request page. Min=1, Max=50 |

### Service Method

#### Purpose

Returns list of ledgers for authorized merchant.

| **Method**        | **Params**           | **Return Type**                            |
| ----------------- | -------------------- | ------------------------------------------ |
| getAccountLedgers | accountLedgerOptions | ServiceResult<PagingResult<AccountLedger>> |

#### Usage

``` java
/**
     * Returns list of ledgers for authorized merchant.
     *
     * @param accountLedgerOptions Ledger Options
     * @return ServiceResult with the generic type of PagingResult of AccountLedger
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<PagingResult<AccountLedger>> getAccountLedgers() throws PaparaRESTException {
        AccountLedgerOptions accountLedgerOptions = new AccountLedgerOptions();
        accountLedgerOptions.setStartDate("2020-01-01T06:42:47.049Z");
        accountLedgerOptions.setEndDate("2020-08-22T06:42:47.049Z");
        accountLedgerOptions.setPage(1);
        accountLedgerOptions.setPageSize(50);

        ServiceResult<PagingResult<AccountLedger>> result = accountService.getAccountLedgers(accountLedgerOptions);
        return result;
    }
```

## Get Settlement

Calculates the count and volume of transactions within the given time period. To perform this operation use `getAccountSettlement` method on `Account` service. `startDate` and `endDate` should be provided.

### AccountSettlement Model

`AccountSettlement` class is used by account service to match returning settlement values API.

| **Variable Name** | **Type**   | **Description**                 |
| ----------------- | ---------- | ------------------------------- |
| count             | Integer    | Gets or sets transaction count  |
| volume            | BigDecimal | Gets or sets transaction volume |

### SettlementGetOptions Model

`SettlementGetOptions` is used by account service for providing settlement request parameters.

| **Variable Name** | **Type** | **Description**                          |
| ----------------- | -------- | ---------------------------------------- |
| startDate         | String   | Gets or sets start date for transactions |
| endDate           | String   | Gets or sets end date for transactions   |
| entryType         | Integer  | Gets or sets entry types                 |

### Service Method

#### Purpose

Returns settlement for authorized merchant.

| **Method**           | **Params**               | **Return Type**                |
| -------------------- | ------------------------ | ------------------------------ |
| getAccountSettlement | accountSettlementOptions | PaparaSingleResult<Settlement> |

#### Usage

``` java
/**
     * Returns settlement for authorized merchant.
     *
     * @param accountSettlementOptions Settlement Options
     * @return ServiceResult with the generic type of AccountSettlement.
     * @throws PaparaRESTException
     */
    public ServiceResult<AccountSettlement> getAccountSettlement() throws PaparaRESTException {
        AccountSettlementOptions accountSettlementOptions = new AccountSettlementOptions();
        accountSettlementOptions.setStartDate("2020-08-22T06:42:47.056Z");
        accountSettlementOptions.setEndDate("2020-08-22T06:42:47.056Z");

        ServiceResult<AccountSettlement> result = accountService.getAccountSettlement(accountSettlementOptions);
        return result;
    }
```



# <a name="banking">Banking</a> 

This part contains technical integration information prepared for merchants those who want to quickly and securely list their bank accounts with Papara and/or create a withdrawal request to their bank accounts.

## Get Bank Accounts

Retrieves registered bank accounts of the merchant. To perform this operation use `getBankAccounts` method on `Banking` service.

### BankAccount Model

`BankAccount` class is used by banking service to match returning bank accounts from API

| **Variable Name** | **Type** | **Description**                         |
| ----------------- | -------- | --------------------------------------- |
| bankAccountId     | Integer  | Gets or sets merchant's bank account ID |
| bankName          | string   | Gets or sets merchant bank name         |
| branchCode        | string   | Gets or sets merchant branch code       |
| iban              | string   | Gets or sets IBAN Number                |
| accountCode       | string   | Gets or sets merchant account code      |
| description       | string   | Gets or sets description                |
| currency          | string   | Gets or sets currency                   |

### Service Method

#### Purpose

Returns bank accounts for authorized merchant.

| **Method**      | **Params** | **Return Type**                  |
| --------------- | ---------- | -------------------------------- |
| getBankAccounts |            | ServiceResult<List<BankAccount>> |

#### Usage

``` java 
/**
     * Returns account information and current balance for authorized merchant.
     *
     * @return ServiceResult with the generic type of List of BankAccount
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<List<BankAccount>> getBankAccounts() throws PaparaRESTException {
        ServiceResult<List<BankAccount>> result = bankService.getBankAccounts();
        return result;
    }
```



## Withdrawal

Generates withdrawal requests for merchants. To perform this operation use `withdrawal` method on `Banking` service.

### BankingWithdrawalOptions 

`BankingWithdrawalOptions` is used by banking service for providing request parameters.

| **Variable Name** | **Type**   | **Description**                                              |
| ----------------- | ---------- | ------------------------------------------------------------ |
| bankAccountId     | Integer    | Gets or sets target bank account id which  money will be transferred to when withdrawal is completed.It will be obtained  as a result of the request to list bank accounts. |
| amount            | BigDecimal | Gets or sets withdrawal amount                               |

### Service Method

#### Purpose

Creates a withdrawal request from given bank account for authorized merchant.

| **Method** | **Params**               | **Return Type**     |
| ---------- | ------------------------ | ------------------- |
| withdrawal | BankingWithdrawalOptions | PaparaServiceResult |

#### Usage

``` java
/**
     * Creates a withdrawal request from given bank account for authorized merchant.
     * @param BankingWithdrawalOptions Withdrawal Options
     * @return ServiceResult
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult withdrawal() throws PaparaRESTException {
        ServiceResult<List<BankAccount>> bankAccounts = bankService.getBankAccounts();
        BankAccount bankAccount = bankAccounts.getData().get(0);

        BankingWithdrawalOptions bankingWithdrawalOptions = new BankingWithdrawalOptions();
        bankingWithdrawalOptions.setAmount(new BigDecimal(10));
        bankingWithdrawalOptions.setBankAccountId(bankAccount.getBankAccountId());

        ServiceResult result = bankService.withdrawal(bankingWithdrawalOptions);
        return result;
    }
```

## Possible Errors and Error Codes

| **Error Code** | **Error Description**                          |
| -------------- | ---------------------------------------------- |
| 105            | Insufficient funds.                            |
| 115            | Requested amount is lower then  minimum limit. |
| 120            | Bank account not found.                        |
| 247            | Merchant's account is not active.              |



# <a name="cash-deposit">Cash Deposit</a> 

With the integration of Papara physical point, you can become a money loading point and earn money from which end users can load balance to their Papara accounts. Physical point integration methods should only be used in scenarios where users load cash to Papara accounts.

## Get Cash Deposit Information

Returns cash deposit information. To perform this operation use `getCashDeposit` method on `Cash Deposit` service. `id` should be provided.

### CashDeposit Model

`CashDeposit` class is used by cash deposit service to match returning cash deposit values from API

| **Variable Name** | **Type**   | **Description**                                       |
| ----------------- | ---------- | ----------------------------------------------------- |
| merchantReference | String     | Gets or sets merchant reference code                  |
| id                | Integer    | Gets or sets cash deposit ID                          |
| createdAt         | String     | Gets or sets created date of cash deposit             |
| amount            | BigDecimal | Gets or sets amount of cash deposit                   |
| currency          | Integer    | Gets or sets currency of cash deposit                 |
| Fee               | BigDecimal | Gets or sets fee of cash deposit                      |
| resultingBalance  | BigDecimal | Gets or sets resulting balance in  merchant's account |
| description       | String     | Gets or sets description                              |

### CashDepositGetOptions

`CashDepositGetOptions` is used by cash deposit service for providing request parameters

| **Variable Name** | **Type** | **Description**              |
| ----------------- | -------- | ---------------------------- |
| id                | string   | Gets or sets cash deposit ID |

### Service Method

#### Purpose

Returns a cash deposit information

| **Method**     | **Params**            | **Return Type**            |
| -------------- | --------------------- | -------------------------- |
| getCashDeposit | CashDepositGetOptions | ServiceResult<CashDeposit> |

####   Usage

``` java
	/**
     * Returns a cash deposit information.
     *
     * @param cashDepositGetOptions Cash Deposit Options
     * @return ServiceResult with the generic type of CashDeposit
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDeposit> getCashDeposit() throws PaparaRESTException {
        CashDepositGetOptions cashDepositGetOptions = new CashDepositGetOptions();
        cashDepositGetOptions.setId("CASH_DEPOSIT_ID");
        ServiceResult<CashDeposit> result = cashDepositService.getCashDeposit(cashDepositGetOptions);
        return result;
    }
```

## Get Cash Deposit By Reference

Returns the information of the money loading process from the physical point with the merchant reference information. To perform this operation use `getCashDepositByReference` method on `Cash Deposit` service. `Reference` should be provided.

### CashDepositByReferenceOptions

`CashDepositByReferenceOptions` is used by cash deposit service for providing request parameters.

| **Variable Name** | **Type** | **Description**                                              |
| ----------------- | -------- | ------------------------------------------------------------ |
| reference         | String   | Gets or sets cash deposit reference no.  Reference no is required. |

### Service Method

#### Purpose

Returns a cash deposit object using merchant's unique reference number.

| **Method**                | **Params**                    | **Return Type**                 |
| ------------------------- | ----------------------------- | ------------------------------- |
| getCashDepositByReference | CashDepositByReferenceOptions | PaparaSingleResult<CashDeposit> |

#### Usage

``` java
	/**
     * Returns a cash deposit object using merchant's unique reference number.
     *
     * @param cashDepositByReferenceOptions Cash Deposit By Merchant Reference Options
     * @return ServiceResult with the generic type of CashDeposit
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDeposit> getCashDepositByReference() throws PaparaRESTException {
        CashDepositByReferenceOptions cashDepositByReferenceOptions = new CashDepositByReferenceOptions();
        cashDepositByReferenceOptions.setReference("CASH_DEPOSIT_REFERENCE_NUMBER");
        
        ServiceResult<CashDeposit> result = cashDepositService.getCashDepositByReference(cashDepositByReferenceOptions);
        return result;
    }
```



## Create Cash Deposit With Phone Number

It deposits money to the user from the physical point. using user’s phone number. To perform this operation use `createWithPhoneNumber` method on `Cash Deposit` service. `phoneNumber`, `amount` and `merchantReference` should be provided.

### CashDepositToPhoneOptions

`CashDepositToPhoneOptions` is used by cash deposit service for providing request parameters.

| **Variable Name** | **Type**   | **Description**                                              |
| ----------------- | ---------- | ------------------------------------------------------------ |
| phoneNumber       | String     | Gets or sets phone number. The mobile  phone number registered in the Papara account of the user to be loaded with  cash. |
| amount            | BigDecimal | Gets or sets amount. The amount of the  cash deposit. This amount will be transferred to the account of the user who  received the payment. The amount to be deducted from the merchant account  will be exactly this number. |
| merchantReference | String     | Gets or sets merchant reference. The  unique value sent by the merchant to prevent false repetitions in cash  loading transactions. If a previously submitted and successful  merchantReference is resubmitted with a new request, the request will fail.  merchantReference sent with failed requests can be resubmitted. |

### Service Method

#### Purpose

Creates a cash deposit request using end users' phone number.

| **Method**            | **Params**                | **Return Type**            |
| --------------------- | ------------------------- | -------------------------- |
| CreateWithPhoneNumber | CashDepositToPhoneOptions | ServiceResult<CashDeposit> |

#### Usage

``` java
	/**
     * Creates a cash deposit request using end users's phone number.
     *
     * @param cashDepositToPhoneOptions Cash Deposit To Phone Number Options
     * @return ServiceResult with the generic type of CashDeposit
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDeposit> createWithPhoneNumber() throws PaparaRESTException {
        CashDepositToPhoneOptions cashDepositToPhoneOptions = new CashDepositToPhoneOptions();
        cashDepositToPhoneOptions.setAmount(new BigDecimal(10));
        cashDepositToPhoneOptions.setPhoneNumber(AppSettings.personalPhoneNumber);
        cashDepositToPhoneOptions.setMerchantReference("MERCHANT_REFERENCE_NUMBER");

        ServiceResult<CashDeposit> result = cashDepositService.createWithPhoneNumber(cashDepositToPhoneOptions);
        return result;
    }
```

## Create Cash Deposit With Account Number

Deposits money to the user with Papara number from the physical point. To perform this operation use `createWithAccountNumber` on `Cash Deposit` service. `accountNumber`, `amount` and `merchantReference` should be provided.

### CashDepositToAccountNumberOptions

`CashDepositToAccountNumberOptions` is used by cash deposit service for providing request parameters.

| **Variable Name** | **Type**   | **Description**                                              |
| ----------------- | ---------- | ------------------------------------------------------------ |
| accountNumber     | Integer    | Gets or sets account number. Papara  account number of the user who will be loaded with cash. |
| amount            | BigDecimal | Gets or sets amount. The amount of the  cash deposit. This amount will be transferred to the account of the user who  received the payment. The amount to be deducted from the merchant account  will be exactly this number. |
| merchantReference | String     | Gets or sets merchant reference. The  unique value sent by the merchant to prevent false repetitions in cash  loading transactions. If a previously submitted and successful  merchantReference is resubmitted with a new request, the request will fail.  MerchantReference sent with failed requests can be resubmitted. |

### Service Method

#### Purpose

Creates a cash deposit request using end user's account number.

| **Method**              | **Params**                        | **Return Type**            |
| ----------------------- | --------------------------------- | -------------------------- |
| createWithAccountNumber | CashDepositToAccountNumberOptions | ServiceResult<CashDeposit> |

#### Usage


```java
	/**
     * Creates a cash deposit request using end user's account number.
     *
     * @param cashDepositToAccountNumberOptions Cash Deposit To Account Number Options
     * @return ServiceResult with the generic type of CashDeposit
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDeposit> createWithAccountNumber() throws PaparaRESTException {
        CashDepositToAccountNumberOptions cashDepositToAccountNumberOptions = new CashDepositToAccountNumberOptions();
        cashDepositToAccountNumberOptions.setAmount(new BigDecimal(10));
        cashDepositToAccountNumberOptions.setAccountNumber(AppSettings.personalAccountNumber);
        cashDepositToAccountNumberOptions.setMerchantReference("MERCHANT_REFERENCE_NUMBER");

        ServiceResult<CashDeposit> result = cashDepositService.createWithAccountNumber(cashDepositToAccountNumberOptions);
        return result;
    }
```

## Create Cash Deposit With National Identity Number

Deposits money to the user with national identity number registered in Papara from the physical point. To perform this operation use `createWithTckn` on `Cash Deposit` service. `phoneNumber`, `tckn`, `amount` and `merchantReference` should be provided.

### CashDepositToTcknOptions

`CashDepositToTcknOptions` is used by cash deposit service for providing request parameters.

| **Variable Name** | **Type**   | **Description**                                              |
| ----------------- | ---------- | ------------------------------------------------------------ |
| tckn              | String     | Gets or sets national identity number  which is linked to user's Papara account |
| amount            | BigDecimal | Gets or sets amount. The amount of the  cash deposit. This amount will be transferred to the account of the user who  received the payment. The amount to be deducted from the merchant account  will be exactly this number |
| merchantReference | String     | Gets or sets merchant reference. The  unique value sent by the merchant to prevent false repetitions in cash  loading transactions. If a previously submitted and successful  merchantReference is resubmitted with a new request, the request will fail.  merchantReference sent with failed requests can be resubmitted |

### Service Method

#### Purpose

Creates a cash deposit request using end users's national identity number.

| **Method**     | **Params**               | **Return Type**            |
| -------------- | ------------------------ | -------------------------- |
| createWithTckn | CashDepositToTcknOptions | ServiceResult<CashDeposit> |

#### Usage

```java
	 /**
     * Creates a cash deposit request using end users's national identity number.
     *
     * @param cashDepositToTcknOptions Cash Deposit To National Identity Number Options
     * @return ServiceResult with the generic type of CashDeposit
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDeposit> createWithTckn() throws PaparaRESTException {
        CashDepositToTcknOptions cashDepositToTcknOptions = new CashDepositToTcknOptions();
        cashDepositToTcknOptions.setAmount(new BigDecimal(10));
        cashDepositToTcknOptions.setTckn(AppSettings.tckn.toString());
        cashDepositToTcknOptions.setMerchantReference("MERCHANT_REFERENCE_NUMBER");

        ServiceResult<CashDeposit> result = cashDepositService.createWithTckn(cashDepositToTcknOptions);
        return result;
    }
```



## Create Cash Deposit Provision With National Identity Number

Creates a request to deposit money from the physical point using national identity number registered in Papara without provision. To perform this operation use `createProvisionWithTckn` on `Cash Deposit` service. `phoneNumber`, `tckn`, `amount` and `merchantReference` should be provided.

### CashDepositToTcknOptions Model

`CashDepositProvision` class is used by cash deposit service to match returning cash deposit provision values from API.

| **Variable Name** | **Type**   | **Description**                           |
| ----------------- | ---------- | ----------------------------------------- |
| tckn            | String | Get national identity number which is linked to user's Papara account                                |
| amount            | BigDecimal | Gets or sets amount                                    |
| merchantReference | String     | Gets or sets merchant reference code      |

### Service Method

#### Purpose

Creates a cash deposit request without upfront payment using end user's national identity number.

| **Method**              | **Params**               | **Return Type**                     |
| ----------------------- | ------------------------ | ----------------------------------- |
| createProvisionWithTckn | CashDepositToTcknOptions | ServiceResult<CashDepositProvision> |

#### Usage

```java
	 /**
     * Creates a cash deposit request without upfront payment using end user's national identity number.
     *
     * @param cashDepositToTcknOptions Cash Deposit To National Identity Number Options
     * @return ServiceResult with the generic type of CashDepositProvision
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDepositProvision> createProvisionWithTckn() throws PaparaRESTException {
        CashDepositToTcknOptions cashDepositToTcknOptions = new CashDepositToTcknOptions();
        cashDepositToTcknOptions.setAmount(new BigDecimal(10));
        cashDepositToTcknOptions.setTckn(AppSettings.tckn.toString());
        cashDepositToTcknOptions.setMerchantReference(UUID.randomUUID().toString());

        ServiceResult<CashDepositProvision> result = cashDepositService.createProvisionWithTckn(cashDepositToTcknOptions);
        return result;
    }
```

## Create Cash Deposit Provision Control With National Identity Number

Deposits money to the user with national identity number registered in Papara from the physical point. To perform this operation use `createProvisionWithTcknControl` on `Cash Deposit` service. `phoneNumber`, `tckn`, `amount` and `merchantReference` should be provided.

### CashDepositTcknControlOptions

`CashDepositTcknControlOptions` is used by cash deposit service for providing request parameters.

| **Variable Name** | **Type**   | **Description**                                              |
| ----------------- | ---------- | ------------------------------------------------------------ |
| phoneNumber              | String     | Gets or sets phone number which is linked to user's Papara account |
| tckn              | String     | Gets or sets national identity number which is linked to user's Papara account |
| amount            | BigDecimal | Gets or sets amount. The amount of the cash deposit. This amount will be transferred to the account of the user who  received the payment. The amount to be deducted from the merchant account will be exactly this number |
| merchantReference | String     | Gets or sets merchant reference. The unique value sent by the merchant to prevent false repetitions in cash  loading transactions. If a previously submitted and successful  merchantReference is resubmitted with a new request, the request will fail. merchantReference sent with failed requests can be resubmitted |

### Service Method

#### Purpose

Creates a cash deposit request using end users's national identity number.

| **Method**     | **Params**               | **Return Type**            |
| -------------- | ------------------------ | -------------------------- |
| createProvisionWithTcknControl | CashDepositTcknControlOptions | ServiceResult<CashDepositProvision> |

#### Usage

```java
/**
     * Creates a cash deposit request without upfront payment using end user's national identity number.
     *
     * @param CashDepositControlOptions Cash Deposit Control Options
     * @return ServiceResult  with the generic type of CashDepositProvision
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDepositProvision> createProvisionWithTcknControl(CashDepositTcknControlOptions cashDepositTcknControlOptions) throws PaparaRESTException {
        CashDepositTcknControlOptions cashDepositTcknControlOptions = new CashDepositTcknControlOptions();
        cashDepositTcknControlOptions.setAmount(new BigDecimal(10));
        cashDepositTcknControlOptions.setMerchantReference("MERCHANT_REFERENCE_NUMBER");
        cashDepositTcknControlOptions.setPhoneNumber(AppSettings.personalPhoneNumber);
        cashDepositTcknControlOptions.setTckn(AppSettings.tckn);

        ServiceResult<CashDepositProvision> result = cashDepositService.createProvisionWithTcknControl(cashDepositTcknControlOptions);
        return result;
    }
```

## Create Cash Deposit Provision With Phone Number

Creates a request to deposit money from the physical point using phone number registered in Papara without provision. To perform this operation use `createProvisionWithPhoneNumber` on `Cash Deposit` service. `phoneNumber`, `amount` and `merchantReference` should be provided.

### Service Method

#### Purpose

Creates a cash deposit request without upfront payment using end user's phone number.

| **Method**                     | **Params**                | **Return Type**                     |
| ------------------------------ | ------------------------- | ----------------------------------- |
| createProvisionWithPhoneNumber | CashDepositToPhoneOptions | ServiceResult<CashDepositProvision> |

#### Usage

```java
	 /**
     * Creates a cash deposit request without upfront payment using end users's phone number.
     *
     * @param cashDepositToPhoneModel Cash Deposit To Phone Number Options
     * @return ServiceResult with the generic type of CashDepositProvision
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDepositProvision> createProvisionWithPhoneNumber() throws PaparaRESTException {
        CashDepositToPhoneOptions cashDepositToPhoneOptions = new CashDepositToPhoneOptions();
        cashDepositToPhoneOptions.setAmount(new BigDecimal(10));
        cashDepositToPhoneOptions.setPhoneNumber(AppSettings.personalPhoneNumber);
        cashDepositToPhoneOptions.setMerchantReference(UUID.randomUUID().toString());

        ServiceResult<CashDepositProvision> result = cashDepositService.createProvisionWithPhoneNumber(cashDepositToPhoneOptions);
        return result;
    }
```

## Create Cash Deposit Provision With Account Number

Creates a request to deposit money from the physical point using Papara number without provision. To perform this operation use `createProvisionWithAccountNumber` on `Cash Deposit` service. `accountNumber`, `amount` and `merchantReference` should be provided.

### Service Method

#### Purpose

Creates a cash deposit request without upfront payment using end user's phone number.

| **Method**                       | **Params**                        | **Return Type**                     |
| -------------------------------- | --------------------------------- | ----------------------------------- |
| createProvisionWithAccountNumber | CashDepositToAccountNumberOptions | ServiceResult<CashDepositProvision> |

#### Usage

```java
	 /**
     * Creates a cash deposit request without upfront payment using merchant's account number.
     *
     * @param cashDepositToAccountNumberOptions Cash Deposit To Account Number Options
     * @return ServiceResult with the generic type of CashDepositProvision
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDepositProvision> createProvisionWithAccountNumber() throws PaparaRESTException {
        CashDepositToAccountNumberOptions cashDepositToAccountNumberOptions = new CashDepositToAccountNumberOptions();
        cashDepositToAccountNumberOptions.setAmount(new BigDecimal(10));
        cashDepositToAccountNumberOptions.setAccountNumber(AppSettings.personalAccountNumber);
        cashDepositToAccountNumberOptions.setMerchantReference(UUID.randomUUID().toString());

        ServiceResult<CashDepositProvision> result = cashDepositService.createProvisionWithAccountNumber(cashDepositToAccountNumberOptions);
        return result;
    }
```

## Cash Deposit Provision Control By Reference Code

With the reference code created by the user, it checks the deposit request without prepayment from the physical point and makes it ready to be approved. To perform this operation, use `provisionByReferenceControl` on `Cash Deposit` service. `referenceCode` and `amount` should be provided.

### Service Method

#### Purpose

Makes  a cash deposit request ready to be completed without upfront payment.

| **Method**                  | **Params**                | **Return Type** |
| --------------------------- | ------------------------- | --------------- |
| provisionByReferenceControl | CashDepositControlOptions | ServiceResult   |

#### Usage

```java
/**
     * Makes a cash deposit provision ready to be completed by cash deposit reference number
     *
     * @param CashDepositControlOptions Cash Deposit Control Options
     * @return ServiceResult
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult provisionByReferenceControl() throws PaparaRESTException {
        CashDepositControlOptions cashDepositControlOptions = new CashDepositControlOptions();
        cashDepositControlOptions.setAmount(new BigDecimal(10));
        cashDepositControlOptions.setReferenceCode("MERCHANT_REFERENCE_NUMBER");

        ServiceResult result = cashDepositService.provisionByReferenceControl(cashDepositControlOptions);
        return result;
    }
```

## Complete Cash Deposit Provision By Reference Code

With the reference code created by the user, it approves the deposit request without prepayment from the physical point and transfers the balance to the user. To perform this operation, use `completeProvisionByReference` on `Cash Deposit` service. `referenceCode` and `amount` should be provided.

### Service Method

#### Purpose

Completes a cash deposit request without upfront payment.

| **Method**                   | **Params**                | **Return Type** |
| ---------------------------- | ------------------------- | --------------- |
| completeProvisionByReference | CashDepositControlOptions | ServiceResult   |

#### Usage

```java
	 /**
     * Completes a cash deposit provision by cash deposit reference number
     *
     * @param CashDepositControlOptions Cash Deposit Control Options
     * @return ServiceResult
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult completeProvisionByReference() throws PaparaRESTException {
        CashDepositControlOptions cashDepositControlOptions = new CashDepositControlOptions();
        cashDepositControlOptions.setAmount(new BigDecimal(10));
        cashDepositControlOptions.setReferenceCode("MERCHANT_REFERENCE_NUMBER");

        ServiceResult result = cashDepositService.completeProvisionByReference(cashDepositControlOptions);
        return result;
    }
```



## Cash Deposit Completion

Confirms the deposit request created from the physical point to the user without prepayment. To perform this operation, use `completeCashDepositProvision` on `Cash Deposit` service. `id` and `transactionDate` should be provided.

### CashDepositCompleteOptions

`CashDepositCompleteOptions` is used by cash deposit service for providing request parameters.

| **Variable Name** | **Type** | **Description**                                |
| ----------------- | -------- | ---------------------------------------------- |
| id                | Integer  | Gets or sets ID of cash deposit request        |
| transactionDate   | String   | Gets or sets date of cash deposit  transaction |

### Service Method

#### Purpose

Completes a cash deposit request without upfront payment.

| **Method**                   | **Params**                 | **Return Type**                     |
| ---------------------------- | -------------------------- | ----------------------------------- |
| completeCashDepositProvision | CashDepositCompleteOptions | ServiceResult<CashDepositProvision> |

#### Usage

```java
	 /**
     * Completes a cash deposit request without upfront payment.
     *
     * @param cashDepositCompleteOptions Cash Deposit Complete Options
     * @return ServiceResult with the generic type of CashDeposit
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDepositProvision> completeCashDepositProvision() throws PaparaRESTException {     
        CashDepositCompleteOptions cashDepositCompleteOptions = new CashDepositCompleteOptions();
        cashDepositCompleteOptions.setId("CASH_DEPOSIT_ID");
        cashDepositCompleteOptions.setTransactionDate("CASH_DEPOSIT_DATE");

        ServiceResult<CashDepositProvision> cashDepositProvisionServiceResult = cashDepositService.completeCashDepositProvision(cashDepositCompleteOptions);
        return result;
    }
```

## Get Cash Deposit By Date

Retrieves information of money deposits from the physical point. To perform this operation, use `getCashDepositByDate` on `Cash Deposit` service. `startDate`, `endDate`, `pageIndex` and `pageItemCount` should be provided.

### CashDepositByDateOptions

`CashDepositByDateOptions` is used by cash deposit service for providing request parameters.

| **Variable Name** | **Type** | **Description**                                              |
| ----------------- | -------- | ------------------------------------------------------------ |
| startDate         | String   | Gets or sets start date of cash deposit                      |
| endDate           | String   | Gets or sets end date of cash deposit                        |
| pageIndex         | Integer  | Gets or sets page index. It is the index  number of the page that is wanted to display from the pages calculated on the  basis of the number of records (pageItemCount) desired to be displayed on a  page. Note: the first page is always 1 |
| pageItemCount     | Integer  | Gets or sets page item count. The number  of records that are desired to be displayed on a page |

### Service Method

#### Purpose

Returns a cash deposit information by given date.

| **Method**           | **Params**               | **Return Type**                       |
| -------------------- | ------------------------ | ------------------------------------- |
| getCashDepositByDate | CashDepositByDateOptions | ServiceResult<ArrayList<CashDeposit>> |

#### Usage

```java
	 /**
     * Returns a cash deposit information by given date.
     *
     * @param cashDepositByDateOptions Cash Deposit By Date Options
     * @return ServiceResult with the generic type of CashDeposit
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<ArrayList<CashDeposit>> getCashDepositByDate() throws PaparaRESTException {
        cashDepositByDateOptions.setStartDate("2020-08-12T11:00:00");
        cashDepositByDateOptions.setEndDate("2020-08-22T11:00:00");
        cashDepositByDateOptions.setPageIndex(1);
        cashDepositByDateOptions.setPageItemCount(20);
        ServiceResult<ArrayList<CashDeposit>> result = cashDepositService.getCashDepositByDate(cashDepositByDateOptions);
        return result;
    }
```

## Provision Settlements

Returns the total number and volume of transactions performed within the given dates. Both start and end dates are included in the calculation. To perform this operation, use `provisionSettlement` on `Cash Deposit` service. `startDate` and `endDate` should be provided.

### CashDepositSettlementOptions

`CashDepositSettlementOptions` is used by cash deposit service for providing request parameters.

| **Variable Name** | **Type** | **Description**                        |
| ----------------- | -------- | -------------------------------------- |
| startDate         | String   | Gets or sets start date for settlement |
| endDate           | String   | Gets or sets end date for settlement   |
| entryType         | Integer  | Gets or sets entry type for settlement |

### Service Method

#### Purpose

Returns total transaction volume and count between given dates. Start and end dates are included.

| **Method**          | **Params**                   | **Return Type**                      |
| ------------------- | ---------------------------- | ------------------------------------ |
| provisionSettlement | CashDepositSettlementOptions | ServiceResult<CashDepositSettlement> |

#### Usage

```java
	 /**
     * Returns total transaction volume and count between given dates. Start and end dates are included.
     *
     * @param cashDepositSettlementOptions Cash Deposit Settlement Options
     * @return ServiceResult with the generic type of CashDepositSettlement
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDepositSettlement> provisionSettlement() throws PaparaRESTException {
        CashDepositSettlementOptions cashDepositSettlementOptions = new CashDepositSettlementOptions();
        cashDepositSettlementOptions.setStartDate("2020-08-12T11:00:00");
        cashDepositSettlementOptions.setEndDate("2020-08-12T11:00:00");
        cashDepositSettlementOptions.setEntryType(3);

        ServiceResult<CashDepositSettlement> result = cashDepositService.provisionSettlement(cashDepositSettlementOptions);
        return result;
    }
```

## Settlements

Returns the total number and volume of transactions performed within the given dates. Both start and end dates are included in the calculation. To perform this operation, use `settlement` on `Cash Deposit` service. `startDate` and `endDate` should be provided.

### CashDepositSettlementOptions

`CashDepositSettlementOptions` is used by cash deposit service for providing request parameters.

| **Variable Name** | **Type** | **Description**                        |
| ----------------- | -------- | -------------------------------------- |
| startDate         | String   | Gets or sets start date for settlement |
| endDate           | String   | Gets or sets end date for settlement   |
| entryType         | Integer  | Gets or sets entry type for settlement |

### Service Method

#### Purpose

Returns total transaction volume and count between given dates. Start and end dates are included.

| **Method**           | **Params**                   | **Return Type**                      |
| -------------------- | ---------------------------- | ------------------------------------ |
| settlement | CashDepositSettlementOptions | ServiceResult<CashDepositSettlement> |

#### Usage

```java
	 /**
     * Returns total transaction volume and count between given dates. Start and end dates are included.
     *
     * @param cashDepositSettlementOptions Cash Deposit Settlement Options
     * @return ServiceResult with the generic type of CashDepositSettlement
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<CashDepositSettlement> settlement() throws PaparaRESTException {
        CashDepositSettlementOptions cashDepositSettlementOptions = new CashDepositSettlementOptions();
        cashDepositSettlementOptions.setStartDate("2020-08-12T11:00:00");
        cashDepositSettlementOptions.setEndDate("2020-08-12T11:00:00");
        cashDepositSettlementOptions.setEntryType(3);

        ServiceResult<CashDepositSettlement> result = cashDepositService.settlement(cashDepositSettlementOptions);
        return result;
    }
```

## Possible Errors and Error Codes

| **Error Code** | **Error Description**                                        |
| -------------- | ------------------------------------------------------------ |
| 100            | User  not found.                                             |
| 101            | Merchant  information could not found.                       |
| 105            | Insufficient  funds.                                         |
| 107            | The  user exceeds the balance limit with this transaction.   |
| 111            | The  user exceeds the monthly transaction limit with this transaction |
| 112            | An  amount has been sent below the minimum deposit limit.    |
| 203            | The  user account is blocked.                                |
| 997            | The  authorization to make a cash deposit is not defined in your account. You  should contact your customer representative. |
| 998            | The  parameters you submitted are not in the expected format. Example: one of the  mandatory fields is not provided. |
| 999            | An  error occurred in the Papara system.                     |



# <a name="mass-payment">Mass Payment</a> 

This part is the technical integration statement prepared for merchants those want to distribute payments to their users quickly, safely and widely through Papara.

## Get Mass Payment

Returns information about the payment distribution process. To perform this operation use `getMassPaymentById` method on `MassPayment` service. `id` should be provided.

### Mass Payment Model

`MassPayment` class is used by mass payment service to match returning mass payment values from API.

| **Variable Name** | **Type**   | **Description**                                         |
| ----------------- | ---------- | ------------------------------------------------------- |
| massPaymentId     | string     | Gets or sets mass payment ID                            |
| id                | Integer    | Gets or sets ID which is created after  payment is done |
| createdAt         | String     | Gets or sets created date                               |
| amount            | BigDecimal | Gets or sets amount of payment                          |
| currency          | Integer    | Gets or sets currency. Values are “0”,  “1”, “2”, “3”   |
| fee               | BigDecimal | Gets or sets fee                                        |
| resultingBalance  | BigDecimal | Gets or sets resulting balance                          |
| description       | String     | Gets or sets description                                |

### MassPaymentByIdOptions

`MassPaymentByIdOptions` is used by mass payment service for providing request parameters.

| **Variable Name** | **Type** | **Description**              |
| ----------------- | -------- | ---------------------------- |
| id                | String   | Gets or sets mass payment ID |

### Service Method

#### Purpose

Returns mass payment information for authorized merchant.

| **Method**      | **Params**             | **Return Type**            |
| --------------- | ---------------------- | -------------------------- |
| massPaymentById | MassPaymentByIdOptions | ServiceResult<MassPayment> |

#### Usage

```java
	 /**
     * Returns mass payment information for authorized merchant.
     *
     * @param MassPaymentByIdOptions Mass Payment By Id Options
     * @return ServiceResult with the generic type of MassPayment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<MassPayment> massPaymentById() throws PaparaRESTException {
        MassPaymentByIdOptions massPaymentByIdOptions = new MassPaymentByIdOptions();
        massPaymentByIdOptions.setId("MASS_PAYMENT_ID");
        ServiceResult<MassPayment> result = massPaymentService.massPaymentById(massPaymentByIdOptions);
        return result;
    }
```



## Get Mass Payment By Reference

Returns information about the payment distribution process. To perform this operation use `massPaymentByReference` method on `MassPayment` service. `reference` should be provided.

### MassPaymentByReferenceOptions

`MassPaymentByIdOptions` is used by mass payment service for providing request parameters.

| **Variable Name** | **Type** | **Description**                  |
| ----------------- | -------- | -------------------------------- |
| reference         | String   | Gets or sets mass payment number |

### Service Method

#### Purpose

Returns mass payment information for authorized merchant.

| **Method**             | **Params**                    | **Return Type**            |
| ---------------------- | ----------------------------- | -------------------------- |
| massPaymentByReference | massPaymentByReferenceOptions | ServiceResult<MassPayment> |

#### Usage

```java
	 /**
     * Returns mass payment information for authorized merchant.
     *
     * @param MassPaymentByIdOptions Mass Payment By Id Options
     * @return ServiceResult with the generic type of MassPayment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<MassPayment> massPaymentByReference() throws PaparaRESTException {
        MassPaymentByReferenceOptions massPaymentByReferenceOptions = new MassPaymentByReferenceOptions();
        massPaymentByReferenceOptions.setReference("1");
        ServiceResult<MassPayment> result = massPaymentService.massPaymentByReference(massPaymentByReferenceOptions);
        return result;
    }
```



## Create Mass Payment To Account Number

Send money to Papara number. To perform this operation use `massPaymentByAccount` method on `MassPayment` service. `accountNumber`, `amount` and `massPaymentId` should be provided.

### MassPaymentToPaparaNumberOptions

`MassPaymentToPaparaNumberOptions` is used by mass payment service for providing request parameters.

| **Variable Name**  | **Type**   | **Description**                                              |
| ------------------ | ---------- | ------------------------------------------------------------ |
| accountNumber      | String     | Gets or sets Papara account number. The  10-digit Papara number of the user who will receive the payment. It can be in  the format 1234567890 or PL1234567890. Before the Papara version transition,  the Papara number was called the wallet number.Old wallet numbers have been  changed to Papara number. Payment can be distributed to old wallet numbers. |
| parseAccountNumber | Integer    | Gets or sets parse account number. Parses  the account number to long type. In old papara integrations, account / wallet  number was made by starting with PL. The service was written in such a way  that it accepts numbers starting with PL, in order not to cause problems to  the member merchants that receive the papara number from their users. |
| amount             | BigDecimal | Gets or sets amount. The amount of the  payment transaction. This amount will be transferred to the account of the  user who received the payment. This figure plus transaction fee will be  charged to the merchant account. |
| massPaymentId      | String     | ets or sets mass payment ID. Unique value  sent by merchant to prevent erroneous repetition in payment transactions. If  a massPaymentId that was sent previously and succeeded is sent again with a new  request, the request will fail. |
| turkishNationalId  | Long       | Gets or sets national identity number.It  provides the control of the identity information sent by the user who will  receive the payment, in the Papara system. In case of a conflict of  credentials, the transaction will not take place. |
| description        | String     | Gets or sets description. Description of  the transaction provided by the merchant. It is not a required field. If  sent, the customer sees in the transaction descriptions. |
| currency                 | Integer    | Gets or sets currency. Values are “0”,  “1”, “2”, “3”        |

### Service Method

#### Purpose

Creates a mass payment to given account number for authorized merchant.

| **Method**           | **Params**                       | **Return Type**            |
| -------------------- | -------------------------------- | -------------------------- |
| massPaymentByAccount | MassPaymentToPaparaNumberOptions | ServiceResult<MassPayment> |

#### Usage

```java
	 /**
     * Creates a mass payment to given account number for authorized merchant.
     *
     * @param MassPaymentToPaparaNumberOptions Mass Payment To Papara Account Options
     * @return ServiceResult with the generic type of MassPayment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<MassPayment> massPaymentByAccount() throws PaparaRESTException {
        MassPaymentToPaparaNumberOptions massPaymentToPaparaNumberOptions = new MassPaymentToPaparaNumberOptions();
        massPaymentToPaparaNumberOptions.setAccountNumber(AppSettings.personalAccountNumber.toString());
        massPaymentToPaparaNumberOptions.setAmount(new BigDecimal(1));
        massPaymentToPaparaNumberOptions.setDescription("Unit Test: MassPaymentByAccount");
        massPaymentToPaparaNumberOptions.setMassPaymentId("MASS_MAYMENT_ID");
        massPaymentToPaparaNumberOptions.setParseAccountNumber(1);
        massPaymentToPaparaNumberOptions.setTurkishNationalId(AppSettings.tckn);
        ServiceResult<MassPayment> result = massPaymentService.massPaymentByAccount(massPaymentToPaparaNumberOptions);
        return result;
    }
```

## Create Mass Payment To E-Mail Address

Send money to e-mail address registered in Papara. To perform this operation use `massPaymentByEmail` method on `MassPayment` service. `email`, `amount` and `massPaymentId` should be provided.

### MassPaymentToEmailOptions

`MassPaymentToEmailOptions` is used by mass payment service for providing request parameters.

| **Variable Name** | **Type**   | **Description**                                              |
| ----------------- | ---------- | ------------------------------------------------------------ |
| email             | String     | Gets or sets e-mail address. Registered  email address of the user receiving the payment. |
| amount            | BigDecimal | Gets or sets amount. The amount of the  payment transaction. This amount will be transferred to the account of the  user who received the payment. This figure plus transaction fee will be  charged to the merchant account. |
| massPaymentId     | String     | Gets or sets mass payment ID. Unique value  sent by merchant to prevent erroneous repetition in payment transactions. If  a massPaymentId that was sent previously and succeeded is sent again with a  new request, the request will fail. |
| turkishNationalId | Long       | Gets or sets national identity number.It  provides the control of the identity information sent by the user who will  receive the payment, in the Papara system. In case of a conflict of  credentials, the transaction will not take place. |
| description       | String     | Gets or sets description. Description of  the transaction provided by the merchant. It is not a required field. If  sent, the customer sees in the transaction descriptions. |
| currency                 | Integer    | Gets or sets currency. Values are “0”,  “1”, “2”, “3”        |

### Service Method

#### Purpose

Creates a mass payment to given e-mail address for authorized merchant.

| **Method**         | **Params**                | **Return Type**            |
| ------------------ | ------------------------- | -------------------------- |
| massPaymentByEmail | MassPaymentToEmailOptions | ServiceResult<MassPayment> |

#### Usage

```java
/**
     * Creates a mass payment to given e-mail address for authorized merchant.
     *
     * @param MassPaymentToEmailOptions Mass Payment To E-Mail Options
     * @return ServiceResult with the generic type of MassPayment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<MassPayment> massPaymentByEmail() throws PaparaRESTException {
        MassPaymentToEmailOptions massPaymentToEmailOptions = new MassPaymentToEmailOptions();
        massPaymentToEmailOptions.setEmail(AppSettings.personalEmail);
        massPaymentToEmailOptions.setAmount(new BigDecimal(1));
        massPaymentToEmailOptions.setDescription("Unit Test: MassPaymentByEmail");
        massPaymentToEmailOptions.setMassPaymentId("MASS_PAYMENT_ID");
        massPaymentToEmailOptions.setTurkishNationalId(AppSettings.tckn);
        ServiceResult<MassPayment> result = massPaymentService.massPaymentByEmail(massPaymentToEmailOptions);
        return result;
    }
```

## Create Mass Payment To Phone Number

Send money to phone number registered in Papara. To perform this operation use `massPaymentByPhone` method on `MassPayment` service. `phoneNumber`, `amount` and `massPaymentId` should be provided.

### MassPaymentToPaparaNumberOptions

`MassPaymentToPhoneNumberOptions` is used by mass payment service for providing request parameters.

| **Variable Name** | **Type**   | **Description**                                              |
| ----------------- | ---------- | ------------------------------------------------------------ |
| phoneNumber       | string     | Gets or sets user's phone number. The  mobile number of the user who will receive the payment, registered in Papara.  It should contain a country code and start with + |
| amount            | BigDecimal | Gets or sets amount. The amount of the  payment transaction. This amount will be transferred to the account of the  user who received the payment. This figure plus transaction fee will be  charged to the merchant account |
| massPaymentId     | String     | Gets or sets mass payment ID. Unique value  sent by merchant to prevent erroneous repetition in payment transactions. If  a MassPaymentId that was sent previously and succeeded is sent again with a new  request, the request will fail |
| turkishNationalId | Long       | Gets or sets national identity number.It  provides the control of the identity information sent by the user who will  receive the payment, in the Papara system. In case of a conflict of  credentials, the transaction will not take place |
| description       | String     | Gets or sets description. Description of  the transaction provided by the merchant. It is not a required field. If  sent, the customer sees in the transaction descriptions |
| currency                 | Integer    | Gets or sets currency. Values are “0”,  “1”, “2”, “3”        |

### Service Method

#### Purpose

Creates a mass payment to given phone number for authorized merchant.

| **Method**         | **Params**                      | **Return Type**            |
| ------------------ | ------------------------------- | -------------------------- |
| massPaymentByPhone | MassPaymentToPhoneNumberOptions | ServiceResult<MassPayment> |

#### Usage

```java
/**
     * Creates a mass payment to given phone number for authorized merchant.
     *
     * @param MassPaymentToPhoneNumberOptions Mass Payment To Phone Number Options
     * @return ServiceResult with the generic type of MassPayment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<MassPayment> massPaymentByPhone() throws PaparaRESTException {
        MassPaymentToPhoneNumberOptions massPaymentToPhoneNumberOptions = new MassPaymentToPhoneNumberOptions();
        massPaymentToPhoneNumberOptions.setPhoneNumber(AppSettings.personalPhoneNumber);
        massPaymentToPhoneNumberOptions.setAmount(new BigDecimal(1));
        massPaymentToPhoneNumberOptions.setDescription("Unit Test: MassPaymentByPhone");
        massPaymentToPhoneNumberOptions.setMassPaymentId("MASS_PAYMENT_ID");
        massPaymentToPhoneNumberOptions.setTurkishNationalId(AppSettings.tckn);
        ServiceResult<MassPayment> result = massPaymentService.massPaymentByPhone(massPaymentToPhoneNumberOptions);
        return result;
    }
```



## Possible Errors and Error Codes

| **Error Code** | **Error Description**                                        |
| -------------- | ------------------------------------------------------------ |
| 100            | User  not found.                                             |
| 105            | Insufficient  funds                                          |
| 107            | Receiver  exceeds balance limit. The highest possible balance for simple accounts is  750 TL. |
| 111            | Receiver  exceeds monthly transaction limit. Simple accounts can receive payments from  a total of 2000 TL of defined resources per month. |
| 133            | MassPaymentID  was used recently.                            |
| 997            | You  are not authorized to distribute payments. You can contact your customer  representative and request a payment distribution definition to your merchant  account. |
| 998            | The  parameters you submitted are not in the expected format. Example: Customer  number less than 10 digits. In this case, the error message contains details  of the format error. |
| 999            | An error  occurred in the Papara system.                     |

# <a name="recurring-mass-payment">Recurring Mass Payment</a>
This section is the technical integration document prepared for the merchants who want to distribute payments to their users in a fast, secure and widespread through Papara.
## Recurring Mass Payment Model
`RecurringMassPayment` class is used by recurring mass payment service to match returning recurring mass payment values from API.
| **Variable Name** | **Type** | **Description**                                                             |
| ----------------- | -------- | --------------------------------------------------------------------------- |
| MerchantId        | string   | Gets or sets merchant id.                                                   |
| UserId            | string   | Gets or sets user id.                                                       |
| Period            | int      | Gets or sets period. Values are "0" (Monthly), "1" (Weekly), "2" (Daily).   |
| ExecutionDay      | int      | Gets or sets ...th day of period. (Weeks start with Monday).                |
| AccountNumber     | int      | Gets or sets account number.                                                |
| Message           | string   | Gets or sets message.                                                       |
| Amount            | decimal  | Gets or sets amount.                                                        |
| Currency          | Currency | Gets or sets currency.Values are “0” (TRY), “1” (USD), “2” (EUR), “3” (GBP).|
## Create Recurring Mass Payment To Account Number
To perform this operation use `CreateRecurringMassPaymentWithAccountNumber` method on `MassPayment` service. `AccountNumber`, `Amount`, `ExecutionDay`, `Description`  and `Period` should be provided.
### RecurringMassPaymentToAccountNumberOptions
`RecurringMassPaymentToAccountNumberOptions` is used by mass payment service for providing request parameters.
| **Variable Name** | **Type** | **Description**                                                             |
| ----------------- | -------- | --------------------------------------------------------------------------- |
| AccountNumber     | string   | Gets or sets Papara account number. The 10-digit Papara number of the user who will receive the payment. It can be in the format 1234567890 or PL1234567890. Before the Papara version transition, the Papara number was called the wallet number.Old wallet numbers have been changed to Papara number. Payment can be distributed to old wallet numbers.                                                                                                     |
| Amount            | decimal  | Gets or sets amount. The amount of the payment transaction. This amount will be transferred to the account of the user who received the payment. This figure plus transaction fee will be charged to the merchant account.                                                |
| TurkishNationalId | long?    | Gets or sets national identity number.It provides the control of the identity information sent by the user who will receive the payment, in the Papara system. In case of a conflict of credentials, the transaction will not take place.                                   |
| Currency          | Currency? | Gets or sets currency.Values are “0” (TRY), “1” (USD), “2” (EUR), “3” (GBP).|
| Period            | int      | Gets or sets period. Values are "0" (Monthly), "1" (Weekly), "2" (Daily).   |
| ExecutionDay      | int      | Gets or sets ...th day of period. (Weeks start with Monday).                |
| Description       | string   | Gets or sets description. Description of the transaction provided by the merchant. It is not a required field. If sent, the customer sees in the transaction descriptions.                                                                                                |
### Service Method
#### Purpose
Creates a recurring mass payment to given account number for authorized merchant.
| **Method**      | **Params**                       | **Return Type**                 |
| --------------- | -------------------------------- | ------------------------------- |
| recurringMassPaymentByAccount | RecurringMassPaymentToPaparaNumberOptions | ServiceResult |
#### Usage
```java
    @Override
    public ServiceResult<RecurringMassPayment> RecurringMassPaymentByAccount() throws PaparaRESTException {
        RecurringMassPaymentToPaparaNumberOptions recurringMassPaymentToPaparaNumberOptions = new RecurringMassPaymentToPaparaNumberOptions();
        recurringMassPaymentToPaparaNumberOptions.setAccountNumber(AppSettings.personalAccountNumber.toString());
        recurringMassPaymentToPaparaNumberOptions.setAmount(new BigDecimal(1));
        recurringMassPaymentToPaparaNumberOptions.setDescription("Unit Test: RecurringMassPaymentByAccount");
        recurringMassPaymentToPaparaNumberOptions.setParseAccountNumber(1);
        recurringMassPaymentToPaparaNumberOptions.setTurkishNationalId(AppSettings.tckn);
        recurringMassPaymentToPaparaNumberOptions.setCurrency(0);
        recurringMassPaymentToPaparaNumberOptions.setPeriod(0);
        recurringMassPaymentToPaparaNumberOptions.setExecutionDay(1);
        ServiceResult<RecurringMassPayment> result = massPaymentService.recurringMassPaymentByAccount(recurringMassPaymentToPaparaNumberOptions);
        return result;
    }
```
## Create Recurring Mass Payment To Email
To perform this operation use `CreateRecurringMassPaymentWithEmail` method on `MassPayment` service. `Email`, `Amount`, `TurkishNationalId`, `Period`, `Currency`, `ExecutionDay` and `Description` should be provided.
### RecurringMassPaymentToEmailOptions
`RecurringMassPaymentToEmailOptions` is used by mass payment service for providing request parameters.
| **Variable Name** | **Type** | **Description**                                                             |
| ----------------- | -------- | --------------------------------------------------------------------------- |
| Email             | string   | Gets or sets e-mail address. Registered email address of the user receiving the payment.                                                                                                     |
| Amount            | decimal  | Gets or sets amount. The amount of the payment transaction. This amount will be transferred to the account of the user who received the payment. This figure plus transaction fee will be charged to the merchant account.                                                |
| TurkishNationalId | long?    | Gets or sets national identity number.It provides the control of the identity information sent by the user who will receive the payment, in the Papara system. In case of a conflict of credentials, the transaction will not take place.                                   |
| Currency          | Currency | Gets or sets currency.Values are “0” (TRY), “1” (USD), “2” (EUR), “3” (GBP).|
| Period            | int      | Gets or sets period. Values are "0" (Monthly), "1" (Weekly), "2" (Daily).   |
| ExecutionDay      | int      | Gets or sets ...th day of period. (Weeks start with Monday).                |
| Description       | string   | Gets or sets description. Description of the transaction provided by the merchant. It is not a required field. If sent, the customer sees in the transaction descriptions.    
### Service Method
#### Purpose
Creates a recurring mass payment to given email address for authorized merchant.
| **Method**      | **Params**                       | **Return Type**                 |
| --------------- | -------------------------------- | ------------------------------- |
| recurringMassPaymentByEmail | RecurringMassPaymentToEmailOptions | ServiceResult |
#### Usage
```java
    @Override
    public ServiceResult<RecurringMassPayment> recurringMassPaymentByEmail() throws PaparaRESTException {
        RecurringMassPaymentToEmailOptions recurringMassPaymentToEmailOptions = new RecurringMassPaymentToEmailOptions();
        recurringMassPaymentToEmailOptions.setEmail(AppSettings.personalEmail);
        recurringMassPaymentToEmailOptions.setAmount(new BigDecimal(1));
        recurringMassPaymentToEmailOptions.setDescription("Unit Test: RecurringMassPaymentByEmail");
        recurringMassPaymentToEmailOptions.setTurkishNationalId(AppSettings.tckn);
        recurringMassPaymentToEmailOptions.setCurrency(0);
        recurringMassPaymentToEmailOptions.setPeriod(0);
        recurringMassPaymentToEmailOptions.setExecutionDay(1);
        ServiceResult<RecurringMassPayment> result = massPaymentService.recurringMassPaymentByEmail(recurringMassPaymentToEmailOptions);
        return result;
    }
```
## Create Recurring Mass Payment To Phone Number
To perform this operation use `CreateRecurringMassPaymentWithPhoneNumber` method on `MassPayment` service. `PhoneNumber`, `Amount`, `TurkishNationalId`, `Period`, `Currency`, `ExecutionDay` and `Description` should be provided.
### RecurringMassPaymentToPhoneNumberOptions
`RecurringMassPaymentToPhoneNumberOptions` is used by mass payment service for providing request parameters.
| **Variable Name** | **Type** | **Description**                                                             |
| ----------------- | -------- | --------------------------------------------------------------------------- |
| PhoneNumber       | string   | Gets or sets user's phone number. The mobile number of the user who will receive the payment, registered in Papara. It should contain a country code and start with +                                                                                        |
| Amount            | decimal  | Gets or sets amount. The amount of the payment transaction. This amount will be transferred to the account of the user who received the payment. This figure plus transaction fee will be charged to the merchant account.                                                |
| TurkishNationalId | long?    | Gets or sets national identity number.It provides the control of the identity information sent by the user who will receive the payment, in the Papara system. In case of a conflict of credentials, the transaction will not take place.                                   |
| Currency          | Currency | Gets or sets currency.Values are “0” (TRY), “1” (USD), “2” (EUR), “3” (GBP).|
| Period            | int      | Gets or sets period. Values are "0" (Monthly), "1" (Weekly), "2" (Daily).   |
| ExecutionDay      | int      | Gets or sets ...th day of period. (Weeks start with Monday).                |
| Description       | string   | Gets or sets description. Description of the transaction provided by the merchant. It is not a required field. If sent, the customer sees in the transaction descriptions.    
### Service Method
#### Purpose
Creates a recurring mass payment to given phone number for authorized merchant.
| **Method**      | **Params**                       | **Return Type**                 |
| --------------- | -------------------------------- | ------------------------------- |
| recurringMassPaymentByPhone | RecurringMassPaymentToPhoneNumberOptions | ServiceResult |
#### Usage
```java
    @Override
    public ServiceResult<RecurringMassPayment> recurringMassPaymentByPhone() throws PaparaRESTException {
        RecurringMassPaymentToPhoneNumberOptions recurringMassPaymentToPhoneNumberOptions = new RecurringMassPaymentToPhoneNumberOptions();
        recurringMassPaymentToPhoneNumberOptions.setPhoneNumber(AppSettings.personalPhoneNumber);
        recurringMassPaymentToPhoneNumberOptions.setAmount(new BigDecimal(1));
        recurringMassPaymentToPhoneNumberOptions.setDescription("Unit Test: RecurringMassPaymentByPhone");
        recurringMassPaymentToPhoneNumberOptions.setTurkishNationalId(AppSettings.tckn);
        recurringMassPaymentToPhoneNumberOptions.setCurrency(0);
        recurringMassPaymentToPhoneNumberOptions.setPeriod(0);
        recurringMassPaymentToPhoneNumberOptions.setExecutionDay(1);
        ServiceResult<RecurringMassPayment> result = massPaymentService.recurringMassPaymentByPhone(recurringMassPaymentToPhoneNumberOptions);
        return result;
    }
```
## Possible Errors and Error Codes
| **Error Code** | **Error Description**                                        |
| -------------- | ------------------------------------------------------------ |
| 100            | User not found.                                              |
| 105            | Insufficient  funds                                          |
| 107            | Receiver exceeds balance limit. The highest possible balance for simple accounts is  750 TL. |
| 111            | Receiver exceeds monthly transaction limit. Simple accounts can receive payments from a total of 2000 TL of defined resources per month. |
| 133            | MassPaymentID was used recently.                             |
| 997            | You  are not authorized to distribute payments. You can contact your customer representative and request a payment distribution definition to your merchant  account. |
| 998            | The  parameters you submitted are not in the expected format. Example: Customer number less than 10 digits. In this case, the error message contains details of the format error. |
| 999            | An error  occurred in the Papara system. 

# <a name="payments">Payments</a> 

Payment service will be used for getting, creating or listing payments and refunding. Before showing the payment button to users, the merchant must create a payment transaction on Papara. Payment records are time dependent. Transaction records that are not completed and paid by the end user are deleted from Papara system after 1 hour. Completed payment records are never deleted and can always be queried with the API.

## Get Payment

Returns payment information. To perform this operation use `getPayment` method on `Payment` service. `id` should be provided.

### Payment Model

`Payment` class is used by payment service to match returning payment values from API.

| **Variable Name**        | **Type**   | **Description**                                              |
| ------------------------ | ---------- | ------------------------------------------------------------ |
| merchant                 | Account    | Gets or sets merhcant                                        |
| id                       | String     | Gets or sets ID                                              |
| createdAt                | String     | Gets or sets created date                                    |
| merchantId               | String     | Gets or sets merchant ID                                     |
| userId                   | string     | Gets or sets user ID                                         |
| paymentMethod            | Integer    | Gets or sets payment Method.  0 -  User completed transaction with existing Papara balance  1 -  User completed the transaction with a debit / credit card that was previously  defined.  2 -  User completed transaction via mobile payment. |
| paymentMethodDescription | String     | Gets or sets payment method description                      |
| referenceId              | String     | Gets or sets referance ID                                    |
| orderDescription         | String     | Gets or sets order description                               |
| status                   | Integer    | Gets or sets status.  0 -  Awaiting, payment is not done yet.  1 -  Payment is done, transaction is completed.  2 -  Transactions is refunded by merchant. |
| statusDescription        | String     | Gets or sets status description                              |
| amount                   | BigDecimal | Gets or sets amount                                          |
| fee                      | BigDecimal | Gets or sets fee                                             |
| currency                 | Integer    | Gets or sets currency. Values are “0”,  “1”, “2”, “3”        |
| notificationUrl          | String     | Gets or sets notification URL                                |
| notificationDone         | Boolean    | Gets or sets if notification was made                        |
| redirectUrl              | String     | Gets or sets redirect URL                                    |
| paymentUrl               | String     | Gets or sets payment URL                                     |
| merchantSecretKey        | String     | Gets or sets merchant secret key                             |
| returningRedirectUrl     | String     | Gets or sets returning Redirect URL                          |
| turkishNationalId        | Long       | Gets or sets national identity number                        |

### PaymentGetOptions

`PaymentGetOptions` will be used as parameter while acquiring payment information.

| **Variable Name** | **Type** | **Description**                |
| ----------------- | -------- | ------------------------------ |
| id                | String   | Gets or sets unique payment ID |

### Service Method

#### Purpose

Returns payment and balance information for authorized merchant.

| **Method** | **Params**        | **Return Type**        |
| ---------- | ----------------- | ---------------------- |
| getPayment | PaymentGetOptions | ServiceResult<Payment> |

#### Usage

```java
	 /**
     * Returns payment and balance information for authorized merchant.
     *
     * @param PaymentGetOptions Payment Get Options
     * @return ServiceResult with the generic type of Payment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Payment> getPayment() throws PaparaRESTException {
        PaymentGetOptions paymentGetOptions = new PaymentGetOptions();
        paymentGetOptions.setId("PAYMENT_ID");
       
        ServiceResult<Payment> result = paymentService.getPayment(paymentGetOptions);
        return result;
    }
```

## Get Payment By Payment Reference Number

Returns payment information. To perform this operation use `getPayment` method on `getPaymentByReference` service. `referenceId` should be provided.


### PaymentGetByReferenceOptions

`PaymentGetByReferenceOptions` will be used as parameter while acquiring payment information.

| **Variable Name** | **Type** | **Description**                              |
| ----------------- | -------- | -------------------------------------------- |
| referenceId       | string   | Gets or sets unique payment reference number |

### Service Method

#### Purpose

Returns payment and balance information for authorized merchant.

| **Method**            | **Params**                   | **Return Type**       |
| --------------------- | ---------------------------- | --------------------- |
| getPaymentByReference | PaymentGetByReferenceOptions | PaparaResult<Payment> |

#### Usage

```java
  /**
   * Returns payment and balance information for authorized merchant.
   *
   * @param PaymentGetOptions $options
   * @return PaparaResult
   */
  public ServiceResult<Payment> getPaymentByReference() throws PaparaRESTException 
  {
     PaymentReferenceGetOptions paymentReferenceGetOptions = new PaymentReferenceGetOptions();
     paymentReferenceGetOptions.setReferenceId("PAYMENT_REFERENCE_NUMBER");

     ServiceResult<Payment> result = paymentService.getPaymentByReference(paymentReferenceGetOptions);
     return result;
  }
```



## Create Payment

Creates a new payment record. To perform this operation use `createPayment` method on `Payment` service. `amount`, `referenceId`, `orderDescription`, `notificationUrl` and `redirectUrl` should be provided.

### PaymentCreateOptions

`PaymentCreateOptions` is used by payment service for providing request parameters.

| **Variable Name** | **Type**   | **Description**                                              |
| ----------------- | ---------- | ------------------------------------------------------------ |
| amount            | BigDecimal | Gets or sets amount. The amount of the  payment transaction. Exactly this amount will be taken from the account of  the user who made the payment, and this amount will be displayed to the user  on the payment screen. Amount field can be minimum 1.00 and maximum 500000.00 |
| referenceId       | String     | Gets or sets reference ID. Reference  information of the payment transaction in the merchant system. The  transaction will be returned to the merchant without being changed in the  result notifications as it was sent to Papara. Must be no more than 100  characters. This area does not have to be unique and Papara does not make  such a check |
| orderDescription  | String     | Gets or sets order description.  Description of the payment transaction. The sent value will be displayed to  the user on the Papara checkout page. Having a description that accurately  identifies the transaction initiated by the user, will increase the chance of  successful payment |
| notificationUrl   | String     | Gets or sets notification URL. The URL to  which payment notification requests (IPN) will be sent. With this field, the  URL where the POST will be sent to the payment merchant must be sent. To the  URL sent with "notificationUrl", Papara will send a payment object  containing all information of the payment with an HTTP POST request  immediately after the payment is completed. Make sure that the payment notification (IPN) coming to "NotificationURL" comes from Papara's IP addresses. You can check the payment by calling HTTP GET /payments API method with the "id" field in the submitted JSON. If the merchant returns 200 OK to  this request, no notification will be made again. If the merchant does not  return 200 OK to this notification, Papara will continue to make payment  notification (IPN) requests for 24 hours until the merchant returns to 200 OK |
| redirectUrl       | String     | Gets or sets redirect URL. URL to which  the user will be redirected at the end of the process |
| turkishNationalId | Long       | Gets or sets national identity number.It  provides the control of the identity information sent by the user who will  receive the payment, in the Papara system. In case of a conflict of  credentials, the transaction will not take place |
| currency                 | Integer    | Gets or sets currency. Values are “0”,  “1”, “2”, “3”        |


### Important Warning

Make sure that the payment notification (IPN) coming to "NotificationURL" comes from Papara's IP addresses. You can check the payment by calling HTTP GET /payments API method with the "id" field in the submitted JSON.

### Service Method

#### Purpose

Creates a payment for authorized merchant.

| **Method**    | **Params**           | **Return Type**        |
| ------------- | -------------------- | ---------------------- |
| createPayment | PaymentCreateOptions | ServiceResult<Payment> |

#### Usage

```java
	 /**
     * Creates a payment for authorized merchant.
     *
     * @param PaymentCreateOptions Payment Create Options
     * @return ServiceResult with the generic type of Payment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Payment> createPayment() throws PaparaRESTException {
        PaymentCreateOptions paymentCreateOptions = new PaymentCreateOptions();
        paymentCreateOptions.setAmount(new BigDecimal(1));
        paymentCreateOptions.setNotificationUrl("https://testmerchant.com/notification");
        paymentCreateOptions.setOrderDescription("Payment Unit Test");
        paymentCreateOptions.setReferenceId(UUID.randomUUID().toString());
        paymentCreateOptions.setTurkishNationalId(AppSettings.tckn);
        paymentCreateOptions.setRedirectUrl("https://testmerchant.com/userredirect");
        ServiceResult<Payment> result = paymentService.createPayment(paymentCreateOptions);
        return result;
    }
```

###  Validating Payment Result 

Following the user's successful completion of the transaction **before the user is directed to the merchant**   , Papara makes a  **HTTP POST**  request to the `notificationUrl` sent by the merchant with the payment request.

In the `body` part of the request, there will be a JSON object with the same structure as the `data` object of the return value creating a payment request. Sample:

```json
{
    "merchantId": "123-4564-8484",
    "userId": "123-987-654",
    "paymentMethod": 1,
    "paymentMethodDescription": "Credit/Debit Card",
    "referenceId": "Merchant Reference",
    "orderDescription": "Description that will be displayed to user on payment page",
    "status": 1,
    "statusDescription": "Completed",    
    "amount": 99.99,
    "fee": 1.98,
    "currency": "TRY",
    "notificationUrl": "https://www.papara.com/notification",
    "notificationDone": false,
    "redirectUrl": "https://www.papara.com/userredirect",
    "merchantSecretKey": "Secret key on the merchant panel",
    "paymentUrl": "www.papara.com/pid?6666-5555-ABCD",
    "returningRedirectUrl": "",
    "id": "6666-5555-ABCD",
    "createdAt": "2017-06-09T06:26:15.100Z",
    "turkishNationalId": 12345678901,
}
```

## Refund 

Refunds a completed payment of the merchant with the provided payment ID .To perform this operation use `refundPayment` method on `Payment` service. `id` should be provided.

### PaymentRefundOptions

`PaymentRefundOptions` is used by payment service for providing request parameters.

| **Variable Name** | **Type** | **Description**         |
| ----------------- | -------- | ----------------------- |
| id                | String   | Gets or sets payment ID |

### Service Method

#### Purpose

Creates a refund for a completed payment for authorized merchant.

| **Method**    | **Params**           | **Return Type** |
| ------------- | -------------------- | --------------- |
| refundPayment | PaymentRefundOptions | ServiceResult   |

#### Usage

```java
	 /**
     * Creates a refund for a completed payment for authorized merchant.
     *
     * @param PaymentRefundOptions Payment refund Options
     * @return ServiceResult with the generic type of Payment
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult refundPayment() throws PaparaRESTException {
        PaymentRefundOptions paymentRefundOptions = new PaymentRefundOptions();
        paymentRefundOptions.setId("PAYMENT_ID");

        ServiceResult result = paymentService.refundPayment(paymentRefundOptions);
        return result;

    }
```

## List Payments

Lists the completed payments of the merchant in a sequential order. To perform this operation use `paymentList` method on `Payment` service. `pageIndex` and `pageItemCount` should be provided.

### PaymentListItem

`PaymentListItem` class is used by payment service to match returning completed payment list values list API.

| **Variable Name**        | **Type**   | **Description**                                              |
| ------------------------ | ---------- | ------------------------------------------------------------ |
| id                       | String     | Gets or sets payment ID                                      |
| createdAt                | String     | Gets or sets created date                                    |
| merchantId               | String     | Gets or sets merchant ID                                     |
| userId                   | String     | Gets or sets user ID                                         |
| paymentMethod            | Integer    | Gets or sets payment Method.  0 -  User completed transaction with existing Papara balance  1 -  User completed the transaction with a debit / credit card that was previously  defined.  2 -  User completed transaction via mobile payment. |
| paymentMethodDescription | String     | Gets or sets payment method description                      |
| referenceId              | String     | Gets or sets reference ID                                    |
| orderDescription         | String     | Gets or sets order description                               |
| Status                   | Integer    | Gets or sets status.  0 -  Awaiting, payment is not done yet.  1 -  Payment is done, transaction is completed.  2 -  Transactions is refunded by merchant. |
| statusDescription        | String     | Gets or sets status description                              |
| amount                   | BigDecimal | Gets or sets amount                                          |
| fee                      | BigDecimal | Gets or sets fee                                             |
| currency                 | Integer    | Gets or sets currency. Values are “0”,  “1”, “2”, “3”        |
| notificationUrl          | String     | Gets or sets notification URL                                |
| notificationDone         | Boolean    | Gets or sets if notification was made                        |
| redirectUrl              | String     | Gets or sets redirect URL                                    |
| paymentUrl               | String     | Gets or sets payment URL                                     |
| merchantSecretKey        | String     | Gets or sets merchant secret key                             |
| returningRedirectUrl     | String     | Gets or sets returning Redirect URL                          |
| turkishNationalId        | Long       | Gets or sets national identity number                        |

### PaymentListOptions

`PaymentListOptions` is used by payment service for providing request parameters

| **Variable Name** | **Type** | **Description**                                              |
| ----------------- | -------- | ------------------------------------------------------------ |
| pageIndex         | Integer  | Gets or sets page index. It is the index  number of the page that is wanted to display from the pages calculated on the  basis of the number of records (pageItemCount) desired to be displayed on a  page. Note: the first page is always 1 |
| pageItemCount     | Integer  | Gets or sets page item count. The number  of records that are desired to be displayed on a page |

### Service Method

#### Purpose

Returns a list of completed payments sorted by newest to oldest for authorized merchant.

| **Method**  | **Params**         | **Return Type**                              |
| ----------- | ------------------ | -------------------------------------------- |
| paymentList | PaymentListOptions | ServiceResult<PagingResult<PaymentListItem>> |

#### Usage

```java
	 /**
     * Returns a list of completed payments sorted by newest to oldest for authorized merchant.
     *
     * @param PaymentListOptions Payment List Options
     * @return ServiceResult with the generic type of Paging Result of Payment List Item
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<PagingResult<PaymentListItem>> paymentList() throws PaparaRESTException {
        PaymentListOptions paymentListOptions = new PaymentListOptions();
        paymentListOptions.setPageIndex(1);
        paymentListOptions.setPageItemCount(20);
        
        ServiceResult<PagingResult<PaymentListItem>> paymentList = paymentService.paymentList(paymentListOptions);
        return result;
    }
```

## Possible Errors and Error Codes

| **Error Code** | **Error Description**                                        |
| -------------- | ------------------------------------------------------------ |
| 997            | You  are not authorized to accept payments. You should contact your customer  representative. |
| 998            | The  parameters you submitted are not in the expected format. Example: one of the  mandatory fields is not provided. |
| 999            | An  error occurred in the Papara system.                     |



# <a name="validation">Validation</a> 

Validation service will be used for validating an end user. Validation can be performed by account number, e-mail address, phone number, national identity number.

## Validate By Id

It is used to validate users with Papara UserId. To perform this operation use `validationById` method on `Validation` service. `userId` should be provided.

### Validation Model           

`Validation` class is used by validation service to match returning user value from API

| **Variable Name** | **Type** | **Description**                            |
| ----------------- | -------- | ------------------------------------------ |
| userId            | String   | Gets or sets unique User ID                |
| firstName         | String   | Gets or sets user first name               |
| LastName          | String   | Gets or sets user last name                |
| email             | String   | Gets or sets user e-mail address           |
| phoneNumber       | String   | Gets or sets user phone number             |
| tckn              | String   | Gets or sets user national identity number |
| accountNumber     | Integer  | Gets or sets user account number           |

### ValidationByIdOptions 

`ValidationByIdOptions` is used by validation service for providing request parameters.

| **Variable Name** | **Type** | **Description**             |
| :---------------- | -------- | --------------------------- |
| userId            | string   | Gets or sets papara User ID |

### Service Method

#### Purpose

Returns end user information for validation by given user ID.

| **Method**     | **Params**            | **Return Type**           |
| -------------- | --------------------- | ------------------------- |
| validationById | ValidationByIdOptions | ServiceResult<Validation> |

#### Usage

```java
	 /**
     * Returns end user information for validation by given user ID.
     *
     * @param ValidationByIdOptions Validation By Id Options
     * @return ServiceResult with the generic type of Validation
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Validation> validationById() throws PaparaRESTException {
        ValidationByIdOptions validationByIdOptions = new ValidationByIdOptions();
        validationByIdOptions.setUserId(AppSettings.personalAccountId);
        
        ServiceResult<Validation> result = validationService.validationById(validationByIdOptions);
        return result;
    }
```

## Validate By Account Number

It is used to validate users with Papara account number. To perform this operation use `validationByAccountNumber` method on `Validation` service. `accountNumber` should be provided.

### ValidationByAccountNumberOptions

`ValidationByAccountNumberOptions` is used by validation service for providing request parameters

| **Variable Name** | **Type** | **Description**                    |
| ----------------- | -------- | ---------------------------------- |
| accountNumber     | Long     | Gets or sets Papara account number |

### Service Method

#### Purpose

Returns end user information for validation by given user account number.

| **Method**                | **Params**                       | **Return Type**           |
| ------------------------- | -------------------------------- | ------------------------- |
| validationByAccountNumber | ValidationByAccountNumberOptions | ServiceResult<Validation> |

#### Usage

```java
	 /**
     * Returns end user information for validation by given user account number.
     *
     * @param ValidationByAccountNumberOptions Validation By Account Number Options
     * @return ServiceResult with the generic type of Validation
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Validation> validationByAccountNumber() throws PaparaRESTException {
        ValidationByAccountNumberOptions validationByAccountNumberOptions = new ValidationByAccountNumberOptions();
        validationByAccountNumberOptions.setAccountNumber(Long.valueOf(AppSettings.personalAccountNumber));
        
        ServiceResult<Validation> result = validationService.validationByAccountNumber(validationByAccountNumberOptions);
        return result;
    }
```

## Validate By Phone Number

It is used to validate users with phone number registered in Papara. To perform this operation use `validationByPhoneNumber` method on `Validation` service. `phoneNumber` should be provided.

### ValidationByPhoneNumberOptions

`ValidationByPhoneNumberOptions` is used by validation service for providing request parameters

| **Variable Name** | **Type** | **Description**                                |
| ----------------- | -------- | ---------------------------------------------- |
| phoneNumber       | String   | Gets or sets phone number registered to Papara |

### Service Method

#### Purpose

Returns end user information for validation by given user phone number.

| **Method**            | **Params**                     | **Return Type**           |
| --------------------- | ------------------------------ | ------------------------- |
| validateByPhoneNumber | ValidationByPhoneNumberOptions | ServiceResult<Validation> |

#### Usage

```java
	 /**
     * Returns end user information for validation by given phone number.
     *
     * @param ValidationByPhoneNumberOptions Validation By Phone Number Options
     * @return ServiceResult with the generic type of Validation
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Validation> validationByPhoneNumber() throws PaparaRESTException {
        ValidationByPhoneNumberOptions validationByPhoneNumberOptions = new ValidationByPhoneNumberOptions();
        validationByPhoneNumberOptions.setPhoneNumber(AppSettings.personalPhoneNumber);
        
        ServiceResult<Validation> result = validationService.validationByPhoneNumber(validationByPhoneNumberOptions);
        return result;
    }
```

## Validate By E-Mail Address

It is used to validate users with e-mail address registered in Papara. To perform this operation use `validationByEmail` method on `Validation` service. `email` should be provided.

### ValidationByEmailOptions

`ValidationByEmailOptions` is used by validation service for providing request parameters

| **Variable Name** | **Type** | **Description**                                  |
| ----------------- | -------- | ------------------------------------------------ |
| email             | String   | Gets or sets e-mail address registered to Papara |

### Service Method

#### Purpose

Returns end user information for validation by given user e-mail address

| **Method**        | **Params**               | **Return Type**           |
| ----------------- | ------------------------ | ------------------------- |
| validationByEmail | ValidationByEmailOptions | ServiceResult<Validation> |

#### Usage

```java
	 /**
     * Returns end user information for validation by given user e-mail address.
     *
     * @param ValidationByEmailOptions Validation By E-Mail Options
     * @return ServiceResult with the generic type of Validation
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Validation> validationByEmail() throws PaparaRESTException {
        ValidationByEmailOptions validationByEmailOptions = new ValidationByEmailOptions();
        validationByEmailOptions.setEmail(AppSettings.personalEmail);
        
        ServiceResult<Validation> result = validationService.validationByEmail(validationByEmailOptions);
        return result;
    }
```

## Validate By National Identity Number

It is used to validate users with national identity number registered in Papara. To perform this operation use `validationByTckn` method on `Validation` service. `tckn` should be provided.

### ValidationByTcknOptions

`ValidationByPhoneNumberOptions` is used by validation service for providing request parameters.

| **Variable Name** | **Type** | **Description**                       |
| ----------------- | -------- | ------------------------------------- |
| tckn              | Long     | Gets or sets national identity number |

### Service Method

#### Purpose

Returns end user information for validation by given user national identity number

| **Method**       | **Params**              | **Return Type**           |
| ---------------- | ----------------------- | ------------------------- |
| validationByTckn | ValidationByTcknOptions | ServiceResult<Validation> |

#### Usage

```java
	 /**
     * Returns end user information for validation by given user national identity number.
     *
     * @param ValidationByTcknOptions Validation By National Identity Number Options
     * @return ServiceResult with the generic type of Validation
     * @throws PaparaRESTException
     */
    @Override
    public ServiceResult<Validation> validationByTckn(4) throws PaparaRESTException {
        ValidationByTcknOptions validationByTcknOptions = new ValidationByTcknOptions();
        validationByTcknOptions.setTckn(AppSettings.tckn);
        
        ServiceResult<Validation> result = validationService.validationByTckn(validationByTcknOptions);
        return result;

    }
```



# <a name="response-types">Response Types</a>

This part contains technical information about return values from API.

## ServiceResult

Papara Service Result type. Handles object data types sending to and returning from API.

| **Variable Name** | **Type**             | **Description**                                              |
| :---------------- | -------------------- | ------------------------------------------------------------ |
| data              | T                    | Generic object return type. Returns the value of the given object type |
| succeeded         | Boolean              | Returns success result                                       |
| error             | ServiceError         | Returns error result                                         |
| result            | ServiceSuccessResult | Returns service success result                               |

## ServiceError

Papara Service Error Result type. Handles error responses returning from API.

| **Variable Name** | **Type** | **Description**            |
| ----------------- | -------- | -------------------------- |
| message           | String   | Gets or sets error message |
| code              | Integer  | Gets or sets error code    |

## ServiceSuccessResult

Papara Service Success Result type. Handles success responses returning from API.


| **Variable Name** | **Type** | **Description**              |
| ----------------- | -------- | ---------------------------- |
| message           | String   | Gets or sets success message |
| code              | Integer  | Gets or sets success code    |

## PagingResult

Papara Paging type. Handles paging data types sending to and returning from API.


| **Variable Name** | **Type** | **Description**                           |
| ----------------- | -------- | ----------------------------------------- |
| items             | List<D>  | Gets or sets list items                   |
| page              | Integer  | Gets or sets page number                  |
| pageItemCount     | Integer  | Gets or sets page item count              |
| totalItemCount    | Integer  | Gets or sets total item count             |
| totalPageCount    | Integer  | Gets or sets total page count             |
| pageSkip          | Integer  | Gets or sets how many pages to be skipped |
