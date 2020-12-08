# İçindekiler

<a href="#intro">Giriş</a>

<a href="#enums">Enumlar</a>

<a href="#account">Hesap Bilgileri</a>

<a href="#banking">Bankacılık</a>

<a href="#cash-deposit">Fiziksel Nokta Entegrasyonu</a>

<a href="#mass-payment">Ödeme Dağıtma</a>

<a href="#payments">Ödeme Alma</a>

<a href="#validation">Doğrulama</a>

<a href="#response-types">Geri Dönüş Tipleri</a>

# <a name="intro">Intro</a> 

Papara ile entegre olmak için aşağıdaki adımları takip edebilirsiniz;

1. API Anahtarınızı edinin. Böylece Papara doğrulama sistemi API isteklerinin kimliğini doğrulayabilir. API Anahtarınızı almak için https://merchant.test.papara.com/ URL adresine gidin. Başarıyla oturum açtıktan sonra, API Anahtarı https://merchant.test.papara.com/APIInfo adresinde görüntülenebilir.

2. Kütüphaneyi kurun. Böylece yazılımınız Papara API ile entegre olabilir. Kurulum işlemleri aşağıdaki gibidir.

# Bağımlılıklar

#### Maven

```xml
<dependency>
	<groupId>com.papara</groupId>
	<artifactId>java-sdk</artifactId>
	<version>1.0.0</version>
</dependency>
```

#### Gradle

 `build.gradle` dosyanızda `jcenter`'ı depolama hedefi olarak eklemelisiniz

```groovy

repositories {
    jcenter()
}

dependencies {
    implementation 'com.papara:java-sdk:1.0.0'
}

```

# Konfigürasyonlar

### Java Kurulumu



API'ye bağlanmadan önce, java geliştiricileri istemci ayarlarını yapılandırmalıdır. Kullanmak istediğiniz **API Anahtarınız** ve **Ortamınız** için ortam değişkenini tanımlayabilirsiniz. Bunun yanında herhangi bir alternatif çözüm de kullanılabilir. Spring Framework'te değişkenleri `appliction.properties` içinde tanımlayabilir ve `@ Value` ek açıklaması aracılığıyla kodun içinde kullanabilirsiniz.

`properties` dosyasında;

```properties
papara-api-key = YOUR_API_KEY_HERE        // Papara API KEY
papara-env = sandbox                      // Hedef ortam. test veya canlı
```

Kodun içinde;

```java
@Value("${papara-api-key}")
private String apiKey;

@Value("${papara-env}")
private String env;
```

Bunlardan sonra istemciyi oluşturabilirsiniz.

```java
APIContext context = new APIContext(apiKey, null, env);  // ortam Constants.SANDBOX veya Constants.LIVE olabilir
PaparaClient client = new PaparaClient(context);
```

### Java Test İsteği

Her şey ayarlandıktan sonra, her şeyin çalıştığını test etmek için aşağıdaki kod bloğunu kullanın.

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

# <a name="enums">Enumlar</a>

# CashDepositProvisionStatus

Bir para yatırma talebi yapıldığında, aşağıdaki durumlar geri dönecek ve provizyon durumunu gösterecektir.

| **Anahtar**     | **Değer** | **Açıklama**         |
| --------------- | --------- | -------------------- |
| Pending         | 0         | Provizyon bekleniyor |
| Complete        | 1         | Tamamlandı           |
| Cancel          | 2         | İptal edildi         |
| ReadyToComplete | 3         | Tamamlanmaya hazır   |

 

# Currency

API'da bulunan bütün para birimleri aşağıdaki gibidir.

| **Anahtar** | **Değer** | **Açıklama**    |
| ----------- | --------- | --------------- |
| TRY         | 0         | Türk Lirası     |
| USD         | 1         | Amerikan Doları |
| EUR         | 2         | Euro            |

 

# EntryType

Giriş Türleri hesap defterlerinde ve para yatırma işlemlerinde parayı takip etmek için kullanılır. Olası giriş türleri aşağıdaki gibidir.

| **Anahtar**                   | **Değer** | **Açıklama**                                                 |
| ----------------------------- | --------- | ------------------------------------------------------------ |
| BankTransfer                  | 1         | Banka Transferi: Para Yatırma veya Çekme                     |
| CorporateCardTransaction      | 2         | Papara Kurumsal Kart İşlemi: Üye iş yerine tahsis edilen kurum kartı ile gerçekleştirilen işlemdir. |
| LoadingMoneyFromPhysicalPoint | 6         | Fiziki Noktadan Para Yükleme: Anlaşmalı yerden nakit para yatırma işlemi |
| MerchantPayment               | 8         | Üye iş yeri Ödemesi: Papara ile Ödeme                        |
| PaymentDistribution           | 9         | Ödeme Dağıtımı: Papara ile toplu ödeme                       |
| EduPos                        | 11        | Çevrimdışı ödeme. Papara üzerinden EDU POS                   |

 

# PaymentMethod

Kabul edilen üç ödeme yöntemi aşağıdaki gibidir.

| **Anahtar**   | **Değer** | **Açıklama**          |
| ------------- | --------- | --------------------- |
| PaparaAccount | 0         | Papara Hesap Bakiyesi |
| Card          | 1         | Tanımlı Kredi Kartı   |
| Mobile        | 2         | Mobil Ödeme           |

 

# PaymentStatus

Ödeme tamamlandıktan sonra API'dan aşağıdaki ödeme durumları dönecektir.

| **Anahtar** | **Değer** | **Açıklama**                |
| ----------- | --------- | --------------------------- |
| Pending     | 0         | Ödeme Bekliyor              |
| Completed   | 1         | Kullanıcı ödemeyi tamamladı |
| Refunded    | 2         | İade edildi                 |

# <a name="account">Hesap Bilgileri</a>

Bu bölüm üye işyerine ait hesap ve bakiye bilgilerinin kullanımı için hazırlanan teknik entegrasyon bilgilerini içerir. Papara hesabındaki hesap ve bakiye bilgileri `Account` servisi ile alınabilir. Geliştiriciler ayrıca bakiyede değişiklik işlemlerin bir listesini içeren bakiye geçmişini de alabilirler.

## Hesap Bilgilerine Erişim

Üye iş yeri hesabı ve bakiye bilgilerini döndürür. Bakiye bilgileri cari bakiyeyi, kullanılabilir ve blokeli bakiyeyi içerirken, hesap bilgileri üye iş yerinin marka adını ve tam unvanını içerir. Bu işlemi gerçekleştirmek için `Account` servisinde bulunan `getAccount` methodunu kullanın.

### Account Model

`Account` sınıfı, `Account` servisi tarafından API'den dönen hesap bilgileri eşleştirmek için kullanılır ve hesap bilgilerini içerir.

| **Değişken Adı**         | **Tip**                  | **Açıklama**                                                 |
| ------------------------ | ------------------------ | ------------------------------------------------------------ |
| legalName                | string                   | Üye iş yerinin şirket unvanını alır veya belirler.           |
| brandName                | string                   | Üye iş yerinin şirket marka adını alır veya belirler.        |
| allowedPaymentTypes      | List<AccountPaymentType> | Üye iş yerinin şirket için kabul edilen ödeme tiplerini alır veya belirler. |
| merchantBalanceModelList | List<AccountBalance>     | Üye iş yerinin şirketin hesap bakiyesini alır veya belirler. |

### AccountPaymentType Model

`AllowedPaymentType` sınıfı, `Account` servisi tarafından API'den dönen hesap bilgilerini eşleştirmek için kullanılır. `allowPaymentType`, izin verilen ödeme türlerini gösterir.

| **Değişken Adı** | **Tip** | **Açıklama**                                                 |
| ---------------- | ------- | ------------------------------------------------------------ |
| paymentMethod    | Integer | Ödeme tipini alır veya belirler.<br />0 – Papara Hesap Bakiyesi  <br />1 – Kredi/Banka kartı <br />2 – Mobil Ödeme. |

### AccountBalance Model

`AccountBalance` sınıfı, `Account` servisi tarafından API'den dönen hesap bakiyesi değeriyle eşleştirmek için kullanılır. Hesap bakiyesi, cari bakiye rakamlarını gösterir ve üç tür bakiye ve genel para birimini listeler.

| **Değişken Adı** | **Tip**    | **Açıklama**                                |
| ---------------- | ---------- | ------------------------------------------- |
| currency         | Integer    | Para birimini alır veya belirler.           |
| totalBalance     | BigDecimal | Toplam bakiyeyi alır veya belirler.         |
| lockedBalance    | BigDecimal | Blokeli bakiyeyi alır veya belirler.        |
| availableBalance | BigDecimal | Kullanılabilir bakiyeyi alır veya belirler. |

### Servis Methodu

#### Kullanım Amacı

Üye iş yeri için hesap bilgisi ve güncel bakiye bilgisini döner.

| **Method** | **Parametreler** | **Dönüş Tipi**         |
| ---------- | ---------------- | ---------------------- |
| getAccount |                  | ServiceResult<Account> |

#### Kullanım Şekli

``` java
    @Override
    public ServiceResult<Account> getAccount() throws PaparaRESTException {
        ServiceResult<Account> result = accountService.getAccount();
        return result;
    }
```



## Hesap Hareketlerini Listeleme

Üye iş yeri hesap hareketlerini(işlem listesi) sayfalı biçimde döndürür. Bu method, her işlem için ortaya çıkan bakiye dahil olmak üzere bir üye iş yeri için yapılan tüm işlemleri listelemek için kullanılır. Bu işlemi gerçekleştirmek için `Account` hizmetinde `getAccountLedgerss` methodunu kullanın. `startDate` ve `endDate` bilgileri gönderilmelidir.

### AccountLedger Model

`AccountLedger` sınıfı, `Account` servisi tarafından API'den dönen değerleri eşleştirmek için kullanılır. Bir işlemin kendisini temsil eder.

| **Değişken Adı**           | **Tip**      | **Açıklama**                                                 |
| -------------------------- | ------------ | ------------------------------------------------------------ |
| id                         | Integer      | Merchant ID alır veya belirler.                              |
| createdAt                  | DateTime     | Hesap hareketlerinin oluşma tarihinialır veya belirler.      |
| entryType                  | Integer      | Giriş türnü alır veya belirler.                              |
| entryTypeName              | String       | Giriş tür adını alır veya belirler.                          |
| amount                     | BigDecimal   | Tutarı alır veya belirler.                                   |
| fee                        | BigDecimal   | Hizmet bedelini alır veya belirler.                          |
| currency                   | Integer      | Para birimini alır veya belirler.                            |
| currencyInfo               | CurrencyInfo | Para birimi bilgisini alır veya belirler.                    |
| resultingBalance           | BigDecimal   | Kalan bakiyeyi alır veya belirler.                           |
| description                | String       | Açıklamayı alır veya belirler.                               |
| massPaymentId              | String       | Toplu ödeme ID'sini alır veya belirler. Ödeme işlemlerinde mükerrer tekrarı önlemek için üye işyeri tarafından gönderilen benzersiz değerdir. Hesap hareketlerinde toplu ödeme türü işlem kayıtlarında işlemin kontrolünü sağlamak için görüntülenir. Diğer ödeme türlerinde boş olacaktır. |
| checkoutPaymentId          | String       | Ödeme ID'sini alır veya belirler. Ödeme kaydı işleminde veri nesnesinde bulunan kimlik alanıdır. Ödeme işleminin benzersiz tanımlayıcısıdır. Hesap hareketlerinde kasa tipi işlem kayıtlarında görüntülenir. Diğer ödeme türlerinde boş olacaktır. |
| checkoutPaymentReferenceId | String       | Checkout referans ID'ini alır veya belirler. Bu, ödeme işlemi kaydı oluşturulurken gönderilen referans kimliği alanıdır. Üye işyeri sisteminde ödeme işleminin referans bilgisidir. Hesap hareketlerinde kasa tipi işlem kayıtlarında görüntülenir. Diğer ödeme türlerinde boş olacaktır |

### CurrencyInfo Model

`CurrencyInfo` sınıfı, `AccountLedger` modeli tarafından API'den dönen para birimi değerlerini almak veya ayarlamak için kullanılır. Hesap hareketlerinde bulunan para birimi bilgilerini temsil eder.

| **Değişken Adı**     | **Tip** | **Açıklama**                                                 |
| -------------------- | ------- | ------------------------------------------------------------ |
| currencyEnum         | Integer | Para birimi tipini alır veya belirler                        |
| symbol               | String  | Para birimi sembolünü alır veya belirler                     |
| code                 | String  | Para birimi kodunu alır veya belirler                        |
| preferredDisplayCode | String  | Para biriminin tercih edilen gösterim kodunu alır veya belirler |
| name                 | String  | Para biriminin adını alır veya belirler                      |
| isCryptocurrency     | boolean | Para biriminin kripto para olup olmadığını alır veya belirler |
| precision            | Integer | Para biriminin virgülden sonra kaç hane gösterileceğini alır veya belirler |
| iconUrl              | String  | Para birimi ikonu URL'ini alır veya belirler                 |

### AccountLedgerOptions Model

`LedgerListOptions` `Account` servisi tarafından hesap hareketleri listeleme işlemine istek parametreleri sağlamak için kullanılır

| **Değişken Adı** | **Tip** | **Açıklama**                                                 |
| ---------------- | ------- | ------------------------------------------------------------ |
| startDate        | String  | İşlemlerin başlangıç tarihini alır veya belirler             |
| endDate          | String  | İşlemlerin bitiş tarihlerini alır veya belirler              |
| entryType        | Integer | İşlemlerin hareket tiplerini alır veya belirler              |
| accountNumber    | Integer | Üye iş yeri hesap numarasını alır veya belirler              |
| page             | Integer | İstenen sayfa numarasını alır veya belirler. İstenen tarihte, istenen PageSize için 1'den fazla sonuç sayfası varsa, bunu sayfalar arasında dönmek için kullanın |
| pageSize         | Integer | Bir sayfada getirilmesi istenen kalem sayısını alır veya belirler. Min=1, Max=50 |

### Servis Methodu

#### Kullanım Amacı

Üye iş yeri için hesap hareketleri listesini döndürür.

| **Method**        | **Parametreler**     | **Dönüş Tipi**                             |
| ----------------- | -------------------- | ------------------------------------------ |
| getAccountLedgers | accountLedgerOptions | ServiceResult<PagingResult<AccountLedger>> |

#### Kullanım Şekli

``` java
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

## Mutabakat Bilgilerine Erişim

Verilen süre içindeki işlemlerin sayısını ve hacmini hesaplar. Bu işlemi gerçekleştirmek için ` Account`  servisinde bulunan ` getAccountSettlement` methodunu kullanın. ` startDate` ve ` endDate` gönderilmelidir.

### AccountSettlement Model

`AccountSettlement` sınıfı, ` Account` servisi tarafından API'dan dönen mutabakat değerlerini eşleştirmek için kullanılır.

| **Değişken Adı** | **Tip**    | **Açıklama**                       |
| ---------------- | ---------- | ---------------------------------- |
| count            | Integer    | İşlem sayısını alır veya belirler. |
| volume           | BigDecimal | İşlem hacmini alır veya belirler   |

### SettlementGetOptions Model

`SettlementGetOptions` sınıfı, ` Account` servisi tarafından API'dan dönen mutabakat değerlerini eşleştirmek için kullanılır.

| **Değişken Adı** | **Tip** | **Açıklama**                                      |
| ---------------- | ------- | ------------------------------------------------- |
| startDate        | String  | İşlemlerin başlangıç tarihini alır veya belirler. |
| endDate          | String  | İşlemlerin bitiş tarihini alır veya belirler.     |
| entryType        | Integer | İşlemlerin giriş tipini alır veya belirler.       |

### Servis Methodu

#### Kullanım Amacı

Üye iş yeri için mutabakat bilgilerini getirir.

| **Method**           | **Parametreler**         | **Dönüş Tipi**                 |
| -------------------- | ------------------------ | ------------------------------ |
| getAccountSettlement | accountSettlementOptions | PaparaSingleResult<Settlement> |

#### Kullanım Şekli

``` java
    public ServiceResult<AccountSettlement> getAccountSettlement() throws PaparaRESTException {
        AccountSettlementOptions accountSettlementOptions = new AccountSettlementOptions();
        accountSettlementOptions.setStartDate("2020-08-22T06:42:47.056Z");
        accountSettlementOptions.setEndDate("2020-08-22T06:42:47.056Z");

        ServiceResult<AccountSettlement> result = accountService.getAccountSettlement(accountSettlementOptions);
        return result;
    }
```



# <a name="banking">Bankacılık</a> 

Bu bölümde, banka hesaplarını Papara'da hızlı ve güvenli bir şekilde listelemek ve / veya banka hesaplarına para çekme talebi oluşturmak isteyen işyerleri için hazırlanmış teknik entegrasyon bilgileri yer almaktadır.

## Banka Hesap Bilgilerine Erişim

Üye iş yeri kurumun kayıtlı banka hesaplarını getirir. Bu işlemi gerçekleştirmek için `Banking` servisinde bulunan `getBankAccounts` methodunu kullanın.

### BankAccount Model

`BankAccount` sınıfı, `Banking` servisi tarafından API'den dönen banka hesaplarını eşleştirmek için kullanılır

| **Değişken Adı** | **Tip** | **Açıklama**                                          |
| ---------------- | ------- | ----------------------------------------------------- |
| bankAccountId    | Integer | Üye iş yerinin banka hesap ID'sini alır veya belirler |
| bankName         | string  | Üye iş yerinin banka adını alır veya belirler         |
| branchCode       | string  | Üye iş yerinin şube kodunu alır veya belirler         |
| iban             | string  | IBAN numarasını alır veya belirler                    |
| accountCode      | string  | Üye iş yerinin hesap kodunu alır veya belirler        |
| description      | string  | Açıklamayı alır veya belirler                         |
| currency         | string  | Para birimini alır veya belirler                      |

### Servis Methodu

#### Kullanım Amacı

Üye iş yeri için banka hesaplarını döndürür.

| **Method**      | **Parametreler** | **Dönüş Tipi**                   |
| --------------- | ---------------- | -------------------------------- |
| getBankAccounts |                  | ServiceResult<List<BankAccount>> |

#### Kullanım Şekli

``` java 
    @Override
    public ServiceResult<List<BankAccount>> getBankAccounts() throws PaparaRESTException {
        ServiceResult<List<BankAccount>> result = bankService.getBankAccounts();
        return result;
    }
```

## Para Çekim İşlemi

Üye iş yerleri için para çekme talepleri oluşturur. Bu işlemi gerçekleştirmek için `Banking` hizmetinde `withdrawal` methodunu kullanın.

### BankingWithdrawalOptions 

`BankingWithdrawalOptions` `Banking` servisi tarafından istek parametrelerini sağlamak için kullanılır.

| **Değişken Adı** | **Tip**    | **Açıklama**                                                 |
| ---------------- | ---------- | ------------------------------------------------------------ |
| bankAccountId    | Integer    | Para çekme işlemi tamamlandığında hangi paranın aktarılacağı hedef banka hesap kimliğini alır veya belirler.Banka hesaplarını listeleme isteği sonucunda elde edilir. |
| amount           | BigDecimal | Çekilecek para tutarını alır veya belirler.                  |

### Servis Methodu

#### Kullanım Amacı

Üye iş yeri için belirli bir banka hesabından para çekme talebi oluşturur.

| **Method** | **Parametreler**         | **Dönüş Tipi**      |
| ---------- | ------------------------ | ------------------- |
| withdrawal | BankingWithdrawalOptions | PaparaServiceResult |

#### Kullanım Şekli

``` java
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

| **Error Code** | **Error Description**                       |
| -------------- | ------------------------------------------- |
| 105            | Yetersiz bakiye                             |
| 115            | Talep edilen miktar minimum limitin altında |
| 120            | Banka hesabı bulunamadı                     |
| 247            | Üye iş yeri hesabı aktif değil              |



# <a name="cash-deposit">Fiziksel Nokta Entegrasyonu</a> 

Papara fiziksel nokta entegrasyonu ile son kullanıcıların Papara hesaplarına bakiye yükleyebilecekleri para yükleme noktası olabilir ve kazanç sağlayabilirsiniz. Fiziksel nokta entegrasyon yöntemleri sadece kullanıcıların Papara hesaplarına nakit yükledikleri senaryolarda kullanılmalıdır.

## Para Yatırma Bilgilerine Erişim

Nakit para yükleme bilgilerini döndürür. Bu işlemi gerçekleştirmek için `CashDeposit`  servisinde bulunan `getCashDeposit `methodunu kullanın. `id` gönderilmelidir.

### CashDeposit Model

`CashDeposit` class is used by cash deposit service to match returning cash deposit values from API

| **Değişken Adı**  | **Tip**    | **Açıklama**                                                 |
| ----------------- | ---------- | ------------------------------------------------------------ |
| merchantReference | String     | Üye iş yerinin referans numarasını alır veya belirler.       |
| id                | Integer    | Nakit para yükleme Id'sini alır veya belirler.               |
| createdAt         | String     | Nakit para yükleme işleminin yapıldığı alır veya belirler.   |
| amount            | BigDecimal | Nakit para yükleme işleminin tutarını alır veya belirler.    |
| currency          | Integer    | Nakit para yükleme işleminin para birimini alır veya belirler. |
| Fee               | BigDecimal | Nakit para yükleme işleminin hizmet bedelini alır veya belirler. |
| resultingBalance  | BigDecimal | Nakit para yükleme işleminden sonra kalan bakiyeyi alır veya belirler. |
| description       | String     | Nakit para yükleme işleminin açıklamasını alır veya belirler. |

### CashDepositGetOptions

`CashDepositGetOptions` `CashDeposit` servisine istek parametrelerini sağlamak için kullanılır

| **Değişken Adı** | **Tip** | **Açıklama**                                   |
| ---------------- | ------- | ---------------------------------------------- |
| id               | string  | Nakit para yükleme Id'sini alır veya belirler. |

### Servis Methodu

#### Kullanım Amacı

Nakit para yükleme işlemi bilgilerini döner

| **Method**     | **Parametreler**      | **Dönüş Tipi**             |
| -------------- | --------------------- | -------------------------- |
| getCashDeposit | CashDepositGetOptions | ServiceResult<CashDeposit> |

####   Kullanım Şekli

``` java
    @Override
    public ServiceResult<CashDeposit> getCashDeposit() throws PaparaRESTException {
        CashDepositGetOptions cashDepositGetOptions = new CashDepositGetOptions();
        cashDepositGetOptions.setId("CASH_DEPOSIT_ID");
        ServiceResult<CashDeposit> result = cashDepositService.getCashDeposit(cashDepositGetOptions);
        return result;
    }
```

## Referans Numarasına Göre Nakit Para Yükleme İşlemine Erişim

Üye iş yeri referans bilgileri ile birlikte fiziksel noktadan para yükleme işlemine ait bilgileri döndürür. Bu işlemi gerçekleştirmek için `CashDeposit` servisinde bulunan `getCashDepositByReference` methodunu kullanın. `reference` gönderillmelidir.

### CashDepositByReferenceOptions

`CashDepositByReferenceOptions` is used by cash deposit service for providing request parameters.

| **Değişken Adı** | **Tip** | **Açıklama**                                                 |
| ---------------- | ------- | ------------------------------------------------------------ |
| reference        | String  | Nakit para yükleme işleminin referans numarasını alır veya belirler. Zorunlu parametredir. |

### Servis Methodu

#### Kullanım Amacı

Üye iş yerinin benzersiz referans numarasını kullanarak bir nakit para yükleme nesnesi döndürür.

| **Method**                | **Parametreler**              | **Dönüş Tipi**                  |
| ------------------------- | ----------------------------- | ------------------------------- |
| getCashDepositByReference | CashDepositByReferenceOptions | PaparaSingleResult<CashDeposit> |

#### Kullanım Şekli

``` java
@Override
    public ServiceResult<CashDeposit> getCashDepositByReference() throws PaparaRESTException {
        CashDepositByReferenceOptions cashDepositByReferenceOptions = new CashDepositByReferenceOptions();
        cashDepositByReferenceOptions.setReference("CASH_DEPOSIT_REFERENCE_NUMBER");
        
        ServiceResult<CashDeposit> result = cashDepositService.getCashDepositByReference(cashDepositByReferenceOptions);
        return result;
    }
```



## Telefon Numarası ile Para Yükleme

Kullanıcının telefon numarasını kullanarak fiziksel noktadan kullanıcıya para yatırır. Bu işlemi gerçekleştirmek için `Cash Deposit` servisinde bulunan `createWithPhoneNumber` methodunu kullanın. `phoneNumber`, `amount` ve `merchantReference` gönderilmelidir.

### CashDepositToPhoneOptions

`CashDepositToPhoneOptions`  `CashDeposit` servisine istek parametrelerini sağlamak için kullanılır.

| **Değişken Adı**  | **Tip**    | **Açıklama**                                                 |
| ----------------- | ---------- | ------------------------------------------------------------ |
| phoneNumber       | String     | Papara hesabına kayıtlı cep telefonu numarasını alır veya belirler.. |
| amount            | BigDecimal | Yüklenecek para tutarını alır veya belirler. Bu tutar ödemeyi alan kullanıcının hesabına aktarılacaktır. Üye işyeri hesabından düşülecek tutar tam olarak bu sayı olacaktır. |
| merchantReference | String     | Üye iş yeri referans numarasını alır veya belirler. Nakit yükleme işlemlerinde yanlış tekrarları önlemek için üye işyeri tarafından gönderilen benzersiz değerdir. Kısasüre önce gönderilmiş ve başarılı bir merchantReference, yeni bir taleple yeniden gönderilirse, istek başarısız olur. Başarısız isteklerle gönderilen MerchantReference yeniden gönderilebilir. |

### Servis Methodu

#### Kullanım Amacı

Son kullanıcının telefon numarasını kullanarak nakit para yatırma isteği oluşturur.

| **Method**            | **Parametreler**          | **Dönüş Tipi**             |
| --------------------- | ------------------------- | -------------------------- |
| CreateWithPhoneNumber | CashDepositToPhoneOptions | ServiceResult<CashDeposit> |

#### Kullanım Şekli

``` java
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

## Papara Numarası ile Para Yükleme

Fiziksel noktadan Papara numarası ile kullanıcıya para yatırır. Bu işlemi yapmak için  `Cash Deposit` servisinde bulunan `createWithAccountNumber` methodunu kullanın. `accountNumber`, `amount` ve `merchantReference` gönderilmelidir.

### CashDepositToAccountNumberOptions

`CashDepositToAccountNumberOptions` is used by cash deposit service for providing request parameters.

| **Değişken Adı**  | **Tip**    | **Açıklama**                                                 |
| ----------------- | ---------- | ------------------------------------------------------------ |
| accountNumber     | Integer    | Hesap numarasını alır veya belirler. Nakit yükleme yapılacak kullanıcının Papara hesap numarasıdır. |
| amount            | BigDecimal | Yüklenecek para tutarını alır veya belirler. Bu tutar ödemeyi alan kullanıcının hesabına aktarılacaktır. Üye işyeri hesabından düşülecek tutar tam olarak bu sayı olacaktır. |
| merchantReference | String     | Üye iş yeri referans numarasını alır veya belirler. Nakit yükleme işlemlerinde yanlış tekrarları önlemek için üye işyeri tarafından gönderilen benzersiz değerdir. Kısasüre önce gönderilmiş ve başarılı bir merchantReference, yeni bir taleple yeniden gönderilirse, istek başarısız olur. Başarısız isteklerle gönderilen MerchantReference yeniden gönderilebilir. |

### Servis Methodu

#### Kullanım Amacı

Son kullanıcının hesap numarasını kullanarak nakit para yükleme talebi oluşturur.

| **Method**              | **Parametreler**                  | **Dönüş Tipi**             |
| ----------------------- | --------------------------------- | -------------------------- |
| createWithAccountNumber | CashDepositToAccountNumberOptions | ServiceResult<CashDeposit> |

#### Kullanım Şekli


```java
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

## TC Kimlik Numarası ile Para Yükleme

Fiziksel noktadan TCKN ile kullanıcıya para yatırır. Bu işlemi yapmak için  `Cash Deposit` servisinde bulunan `createWithTckn` methodunu kullanın. `tckn`, `amount` ve `merchantReference` gönderilmelidir.

### CashDepositToTcknOptions

`CashDepositToTcknOptions` `CashDeposit` servisine istek parametrelerini sağlamak için kullanılır.

| **Değişken Adı**  | **Tip**    | **Açıklama**                                                 |
| ----------------- | ---------- | ------------------------------------------------------------ |
| tckn              | String     | Nakit yükleme yapılacak kullanıcının TC kimlik numarasını alır veya belirler. |
| amount            | BigDecimal | Yüklenecek para tutarını alır veya belirler. Bu tutar ödemeyi alan kullanıcının hesabına aktarılacaktır. Üye işyeri hesabından düşülecek tutar tam olarak bu sayı olacaktır. |
| merchantReference | String     | Üye iş yeri referans numarasını alır veya belirler. Nakit yükleme işlemlerinde yanlış tekrarları önlemek için üye işyeri tarafından gönderilen benzersiz değerdir. Kısasüre önce gönderilmiş ve başarılı bir merchantReference, yeni bir taleple yeniden gönderilirse, istek başarısız olur. Başarısız isteklerle gönderilen MerchantReference yeniden gönderilebilir. |

### Servis Methodu

#### Kullanım Amacı

Son kullanıcının TC kimlik numarasını kullanarak nakit para yükleme talebi oluşturur.

| **Method**     | **Parametreler**         | **Dönüş Tipi**             |
| -------------- | ------------------------ | -------------------------- |
| createWithTckn | CashDepositToTcknOptions | ServiceResult<CashDeposit> |

#### Kullanım Şekli

```java
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



## TCKN ile Ön Ödemesiz Para Yükleme

Fiziksel noktadan TCKN ile kullanıcıya ön ödemesiz olarak para yatırır. Bu işlemi yapmak için  `Cash Deposit` servisinde bulunan `createProvisionWithTckn` methodunu kullanın. `tckn`, `amount` ve `merchantReference` gönderilmelidir.

### CashDepositProvision Model

`CashDepositProvision` class is used by cash deposit service to match returning cash deposit provision values from API.

| **Variable Name** | **Type** | **Description**                                              |
| ----------------- | -------- | ------------------------------------------------------------ |
| Id                | int      | Ön ödemesiz para yükleme işleminin ID'sini alır veya belirler. |
| CreatedAt         | DateTime | Ön ödemesiz para yükleme işleminin oluşturulma tarihini alır veya belirler. |
| Amount            | decimal? | Ön ödemesiz para yükleme işleminin tutarını alır veya belirler. |
| Currency          | int      | Ön ödemesiz para yükleme işleminin para birimini alır veya belirler. |
| MerchantReference | string   | Üye iş yeri referans numarasını alır veya belirler.          |
| UserFullName      | string   | Kullanıcının tam adını alır veya belirler                    |

### CashDepositToTcknOptions 

`CashDepositProvision` `CashDeposit` servisine istek parametrelerini sağlamak için kullanılır.

| **Değişken Adı** | **Tip**   | **Açıklama**                           |
| ----------------- | ---------- | ----------------------------------------- |
| tckn            | String | Nakit yükleme yapılacak kullanıcının TC kimlik numarasını alır veya belirler. |
| amount            | BigDecimal | Yüklenecek para tutarını alır veya belirler. Bu tutar ödemeyi alan kullanıcının hesabına aktarılacaktır. Üye işyeri hesabından düşülecek tutar tam olarak bu sayı olacaktır. |
| merchantReference | String     | Üye iş yeri referans numarasını alır veya belirler. Nakit yükleme işlemlerinde yanlış tekrarları önlemek için üye işyeri tarafından gönderilen benzersiz değerdir. Kısasüre önce gönderilmiş ve başarılı bir merchantReference, yeni bir taleple yeniden gönderilirse, istek başarısız olur. Başarısız isteklerle gönderilen MerchantReference yeniden gönderilebilir. |

### Servis Methodu

#### Kullanım Amacı

Creates a cash deposit request without upfront payment using end user's national identity number.

| **Method**              | **Parametreler**         | **Dönüş Tipi**                      |
| ----------------------- | ------------------------ | ----------------------------------- |
| createProvisionWithTckn | CashDepositToTcknOptions | ServiceResult<CashDepositProvision> |

#### Kullanım Şekli

```java
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

## TCKN ile Ön Ödemesiz Para Yükleme Kontrolü 

Fiziksel noktadan Papara'ya kayıtlı ulusal kimlik numarası ile kullanıcıya para yatırır. Bu işlemi gerçekleştirmek için `Cash Deposit` servisinde bulunan `createProvisionWithTcknControl` methodunu kullanın. `phoneNumber`, `tckn`, `amount` ve `merchantReference` gönderilmelidir.

### CashDepositTcknControlOptions

`CashDepositTcknControlOptions` sınıfı `CashDeposit` servisi tarafından API'den dönen ön ödemesiz para yükleme bilgilerini eşleştirmek için kullanılır

| **Değişken Adı** | **Tip**   | **Açıklama**                                              |
| ----------------- | ---------- | ------------------------------------------------------------ |
| phoneNumber              | String     | Papara hesabına kayıtlı cep telefonu numarasını alır veya belirler. |
| tckn              | String     | Nakit yükleme yapılacak kullanıcının TC kimlik numarasını alır veya belirler. |
| amount            | BigDecimal | Yüklenecek para tutarını alır veya belirler. Bu tutar ödemeyi alan kullanıcının hesabına aktarılacaktır. Üye işyeri hesabından düşülecek tutar tam olarak bu sayı olacaktır. |
| merchantReference | String     | Üye iş yeri referans numarasını alır veya belirler. Nakit yükleme işlemlerinde yanlış tekrarları önlemek için üye işyeri tarafından gönderilen benzersiz değerdir. Kısasüre önce gönderilmiş ve başarılı bir merchantReference, yeni bir taleple yeniden gönderilirse, istek başarısız olur. Başarısız isteklerle gönderilen MerchantReference yeniden gönderilebilir. |

### Servis Methodu

#### Kullanım Amacı

TC kimlik numarası ile ön yüklemesiz para yükleme kontrolü oluşturmak istendiğinde kullanılır.

| **Method**     | **Parametreler**         | **Dönüş Tipi**            |
| -------------- | ------------------------ | -------------------------- |
| createProvisionWithTcknControl | CashDepositTcknControlOptions | ServiceResult<CashDepositProvision> |

#### Kullanım Şekli

```java
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

## Telefon Numarası ile Ön Ödemesiz Para Yükleme

Kullanıcının telefon numarasını kullanarak fiziksel noktadan kullanıcıya ön ödemesiz olark para yatırır. Bu işlemi gerçekleştirmek için `Cash Deposit` servisinde bulunan `createProvisionWithPhoneNumber` methodunu kullanın. `phoneNumber`, `amount` ve `merchantReference` gönderilmelidir.

### Servis Methodu

#### Kullanım Amacı

Son kullanıcının telefon numarasını kullanarak ön ödemesiz nakit para yatırma isteği oluşturur.

| **Method**                     | **Parametreler**          | **Dönüş Tipi**                      |
| ------------------------------ | ------------------------- | ----------------------------------- |
| createProvisionWithPhoneNumber | CashDepositToPhoneOptions | ServiceResult<CashDepositProvision> |

#### Kullanım Şekli

```java
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

## Papara Numarası ile Ön Ödemesiz Para Yükleme

Fiziksel noktadan Papara numarası ile kullanıcıya ön ödemesiz olarak para yatırır. Bu işlemi yapmak için  `Cash Deposit` servisinde bulunan `createProvisionWithAccountNumber` methodunu kullanın. `accountNumber`, `amount` ve `merchantReference` gönderilmelidir.

### Servis Methodu

#### Kullanım Amacı

Son kullanıcının hesap numarasını kullanarak ön ödemesiz nakit para yatırma isteği oluşturur.

| **Method**                       | **Parametreler**                  | **Dönüş Tipi**                      |
| -------------------------------- | --------------------------------- | ----------------------------------- |
| createProvisionWithAccountNumber | CashDepositToAccountNumberOptions | ServiceResult<CashDepositProvision> |

#### Kullanım Şekli

```java
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

## Referans Numarasına Göre Nakit Yükleme Onaylama 

Kullanıcı tarafından oluşturulan referans kodu ile fiziki noktadan ön ödemesiz nakit para yükleme talebini kontrol ederek onaylanmaya hazır hale getirir. Bu işlemi gerçekleştirmek için,  `Cash Deposit` servisinde bulunan `provisionByReferenceControl` methodunu kullanın. `referenceCode` ve `amount` gönderilmelidir.

### Servis Methodu

#### Kullanım Amacı

Ön ödemesiz nakit para yükleme talebini tamamlanmaya hazır hale getirir.

| **Method**                  | **Parametreler**          | **Dönüş Tipi** |
| --------------------------- | ------------------------- | -------------- |
| provisionByReferenceControl | CashDepositControlOptions | ServiceResult  |

#### Kullanım Şekli

```java
@Override
    public ServiceResult provisionByReferenceControl() throws PaparaRESTException {
        CashDepositControlOptions cashDepositControlOptions = new CashDepositControlOptions();
        cashDepositControlOptions.setAmount(new BigDecimal(10));
        cashDepositControlOptions.setReferenceCode("MERCHANT_REFERENCE_NUMBER");

        ServiceResult result = cashDepositService.provisionByReferenceControl(cashDepositControlOptions);
        return result;
    }
```

## Referans Numarasına Göre Nakit Yükleme İşlemini Tamamlama

Kullanıcı tarafından oluşturulan referans kodu ile fiziki noktadan ön ödemesiz nakit para yükleme talebini onaylar ve bakiyeyi kullanıcıya aktarır. Bu işlemi gerçekleştirmek için `CashDeposit` servisinde bulunan `completeProvisionByReference` methodunu kullanın. `referenceCode` ve `amount` gönderilmelidir.

### Servis Methodu

#### Kullanım Amacı

Ön ödemesiz nakit yükleme işlemini tamamlar

| **Method**                   | **Parametreler**          | **Dönüş Tipi** |
| ---------------------------- | ------------------------- | -------------- |
| completeProvisionByReference | CashDepositControlOptions | ServiceResult  |

#### Kullanım Şekli

```java
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

Bekleyen para yükleme işlemlerini tamamlamak için kullanılır. Kullanıcının hesabına paranın geçmesi için işlemin tamamlanması gerekir. Bu işlemi gerçekleştirmek için `CashDeposit` servisinde bulunan `completeProvisionByReference` methodunu kullanın. `id` ve `transactionDate` gönderilmelidir.

### CashDepositCompleteOptions

`CashDepositCompleteOptions` `CashDeposit` servisine istek parametrelerini sağlamak için kullanılır.

| **Değişken Adı** | **Tip** | **Açıklama**                                   |
| ---------------- | ------- | ---------------------------------------------- |
| id               | Integer | Gets or sets ID of cash deposit request        |
| transactionDate  | String  | Gets or sets date of cash deposit  transaction |

### Servis Methodu

#### Kullanım Amacı

Bekleyen ön ödemesiz para yükleme işlemlerini tamamlamak için kullanılır.

| **Method**                   | **Parametreler**           | **Dönüş Tipi**                      |
| ---------------------------- | -------------------------- | ----------------------------------- |
| completeCashDepositProvision | CashDepositCompleteOptions | ServiceResult<CashDepositProvision> |

#### Kullanım Şekli

```java
@Override
    public ServiceResult<CashDepositProvision> completeCashDepositProvision() throws PaparaRESTException {     
        CashDepositCompleteOptions cashDepositCompleteOptions = new CashDepositCompleteOptions();
        cashDepositCompleteOptions.setId("CASH_DEPOSIT_ID");
        cashDepositCompleteOptions.setTransactionDate("CASH_DEPOSIT_DATE");

        ServiceResult<CashDepositProvision> cashDepositProvisionServiceResult = cashDepositService.completeCashDepositProvision(cashDepositCompleteOptions);
        return result;
    }
```

## Tarihe Göre Nakit Para Yükleme Bilgilerine Erişim

Para yatırma bilgilerini tarihe göre getirir. Bu işlemi gerçekleştirmek için, `Cash Deposit` bulunan`getCashDepositByDate` methodunu kullanın. `startDate`, `endDate`, `pageIndex` ve `pageItemCount` gönderilmelidir.

### CashDepositByDateOptions

`CashDepositByDateOptions` `CashDeposit` servisine istek parametrelerini sağlamak için kullanılır.

| **Değişken Adı** | **Tip** | **Açıklama**                                                 |
| ---------------- | ------- | ------------------------------------------------------------ |
| startDate        | String  | Nakit para yükleme işlemlerinin başlangıç tarihini alır veya belirler. |
| endDate          | String  | Nakit para yükleme işlemlerinin bitiş tarihini alır veya belirler. |
| pageIndex        | Integer | Sayfa dizinini alır veya belirler. Bir sayfada gösterilmek istenen kayıt sayısına (pageItemCount) göre hesaplanan sayfalardan gösterilmek istenen sayfanın indeks numarasıdır. Not: ilk sayfa her zaman 1'dir |
| pageItemCount    | Integer | Sayfa öğesi sayısını alır veya belirler. Bir sayfada gösterilmesi istenen kayıtların sayısıdir. |

### Servis Methodu

#### Kullanım Amacı

Verilen tarihler aralığındaki nakit para yükleme işlemlerine erişim için kullanılır

| **Method**           | **Parametreler**         | **Dönüş Tipi**                        |
| -------------------- | ------------------------ | ------------------------------------- |
| getCashDepositByDate | CashDepositByDateOptions | ServiceResult<ArrayList<CashDeposit>> |

#### Kullanım Şekli

```java
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

Verilen tarihlerde gerçekleştirilen ön ödemesiz para yükleme işlemlerin toplam sayısını ve hacmini döndürür. Hesaplamaya hem başlangıç hem de bitiş tarihleri dahil edilir. Bu işlemi gerçekleştirmek için, `Cash Deposit`  servisinde bulunan`provisionSettlements` methodunu kullanın. `startDate` ve `endDate` gönderilmelidir.

### CashDepositSettlementOptions

`CashDepositSettlementOptions` `CashDeposit` servisine istek parametrelerini sağlamak için kullanılır.

| **Değişken Adı** | **Tip** | **Açıklama**                                      |
| ---------------- | ------- | ------------------------------------------------- |
| startDate        | String  | Mutabakatın başlangıç tarihini alır veya belirler |
| endDate          | String  | Mutabakatın bitiş tarihini alır veya belirler     |
| entryType        | Integer | Mutabakatın giriş tipini alır veya belirler       |

### Servis Methodu

#### Kullanım Amacı

Verilen tarihler arasındaki toplam ön ödemesiz nakit para yükleme işlem hacmini ve sayımı döndürür. Başlangıç ve bitiş tarihleri dahildir.

| **Method**          | **Parametreler**             | **Dönüş Tipi**                       |
| ------------------- | ---------------------------- | ------------------------------------ |
| provisionSettlement | CashDepositSettlementOptions | ServiceResult<CashDepositSettlement> |

#### Kullanım Şekli

```java
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

Verilen tarihlerde gerçekleştirilen para yükleme işlemlerinin toplam sayısını ve hacmini döndürür. Hesaplamaya hem başlangıç hem de bitiş tarihleri dahil edilir. Bu işlemi gerçekleştirmek için, `Cash Deposit`  servisinde bulunan `settlement` methodunu kullanın. `startDate` ve `endDate` gönderilmelidir.

### CashDepositSettlementOptions

`CashDepositSettlementOptions` `CashDeposit` servisine istek parametrelerini sağlamak için kullanılır.

| **Değişken Adı** | **Tip** | **Açıklama**                                         |
| ---------------- | ------- | ---------------------------------------------------- |
| startDate        | String  | Mutabakatın başlangıç tarihini alır veya belirler    |
| endDate          | String  | Mutabakatın bitiş tarihini alır veya belirler        |
| entryType        | Integer | Mutabakatın giriş tipini tarihini alır veya belirler |

### Servis Methodu

#### Kullanım Amacı

Verilen tarihler arasındaki toplam nakit para yükleme işlem hacmini ve sayımı döndürür. Başlangıç ve bitiş tarihleri dahildir.

| **Method**           | **Parametreler**             | **Dönüş Tipi**                      |
| -------------------- | ---------------------------- | ------------------------------------ |
| settlement | CashDepositSettlementOptions | ServiceResult<CashDepositSettlement> |

#### Kullanım Şekli

```java
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

| **Hata Kodu** | **Hata Açıklaması**                                          |
| ------------- | ------------------------------------------------------------ |
| 100           | Kullanıcı bulunamadı.                                        |
| 101           | Üye iş yeri bilgisi bulunamadı.                              |
| 105           | Yetersiz bakiye.                                             |
| 107           | Kullanıcı bu işlem ile toplam işlem limitini aşıyor.         |
| 111           | Kullanıcı bu işlem ile aylık toplam işlem limitini aşıyor.   |
| 112           | Gönderilen tutar minimum gönderim tutarının altında.         |
| 203           | Kullanıcı hesabı blokeli.                                    |
| 997           | Nakit para yatırma yetkisi, hesabınızda tanımlanmamıştır. Müşteri temsilcinizle iletişime geçmelisiniz. |
| 998           | Gönderdiğiniz parametreler beklenen formatta değil. Örnek: zorunlu alanlardan biri sağlanmamıştır. |
| 999           | Papara sisteminde hata meydana geldi.                        |

# <a name="mass-payment">Ödeme Dağıtma</a> 

Bu bölüm, ödemelerini kullanıcılarına hızlı, güvenli ve yaygın bir şekilde Papara üzerinden dağıtmak isteyen işyerleri için hazırlanmış teknik entegrasyon bilgilerini içerir.

## Get Mass Payment

Ödeme dağıtım işlemi hakkında bilgileri döner. Bu işlemi yamak için `MassPayment` servisinde bulunan `getMassPayment` methodunu kullanın. `id` gönderilmelidir.

### Mass Payment Model

`MassPayment` sınıfı, `MassPayment` servisi tarafından API'den dönen ödeme dağıtım bilgilerini eşleştirmek için kullanılır.

| **Değişken Adı** | **Tip**    | **Açıklama**                                                 |
| ---------------- | ---------- | ------------------------------------------------------------ |
| massPaymentId    | string     | Ödeme ID'sini alır veya belirler.                            |
| id               | Integer    | Ödeme yapıldıktan sonra oluşan ID'yi alır veya belirler.     |
| createdAt        | String     | Ödeme tarihini alır veya belirler.                           |
| amount           | BigDecimal | Ödeme tutarını alır veya belirler.                           |
| currency         | Integer    | Ödeme yapılan para birmini alır veya belirler. Değerler "1","2" veya "3" olabilir. |
| fee              | BigDecimal | Hizmet bedelini alır veya belirler.                          |
| resultingBalance | BigDecimal | Kalan bakiyeyi alır veya belirler.                           |
| description      | String     | Açıklamayı alır veya belirler.                               |

### MassPaymentByIdOptions

`MassPaymentByIdOptions` is used by mass payment service for providing request parameters.

| **Değişken Adı** | **Tip** | **Açıklama**                 |
| ---------------- | ------- | ---------------------------- |
| id               | String  | Gets or sets mass payment ID |

### Servis Methodu

#### Kullanım Amacı

`MassPaymentGetOptions`  `MassPayment` servisine istek parametrelerini sağlamak için kullanılır.

| **Method**      | **Parametreler**       | **Dönüş Tipi**             |
| --------------- | ---------------------- | -------------------------- |
| massPaymentById | MassPaymentByIdOptions | ServiceResult<MassPayment> |

#### Kullanım Şekli

```java
@Override
    public ServiceResult<MassPayment> massPaymentById() throws PaparaRESTException {
        MassPaymentByIdOptions massPaymentByIdOptions = new MassPaymentByIdOptions();
        massPaymentByIdOptions.setId("MASS_PAYMENT_ID");
        ServiceResult<MassPayment> result = massPaymentService.massPaymentById(massPaymentByIdOptions);
        return result;
    }
```



## Referans Numarasına Göre Ödeme Dağıtım Bilgilerine Erişim

Referans numarası kullanarak ödeme dağıtım süreci hakkında bilgi verir. Bu işlemi gerçekleştirmek için `MassPayment` servisinde bulunan `massPaymentByReference` methodunu kullanın. `refernce` gönderilmelidir.

### MassPaymentByReferenceOptions

`MassPaymentByIdOptions` `MassPayment` servisine istek parametrelerini sağlamak için kullanılır.

| **Değişken Adı** | **Tip** | **Açıklama**                     |
| ---------------- | ------- | -------------------------------- |
| reference        | String  | Gets or sets mass payment number |

### Servis Methodu

#### Kullanım Amacı

Üye iş yeri için ödeme bilgisine erişmek için kullanılır

| **Method**             | **Parametreler**              | **Dönüş Tipi**             |
| ---------------------- | ----------------------------- | -------------------------- |
| massPaymentByReference | massPaymentByReferenceOptions | ServiceResult<MassPayment> |

#### Kullanım Şekli

```java
@Override
    public ServiceResult<MassPayment> massPaymentByReference() throws PaparaRESTException {
        MassPaymentByReferenceOptions massPaymentByReferenceOptions = new MassPaymentByReferenceOptions();
        massPaymentByReferenceOptions.setReference("1");
        ServiceResult<MassPayment> result = massPaymentService.massPaymentByReference(massPaymentByReferenceOptions);
        return result;
    }
```



## Hesap Numarasına Ödeme Gönderme

Send money to Papara number. To perform this operation use `massPaymentByAccount` method on `MassPayment` service. `accountNumber`, `amount` and `massPaymentId` should be provided.

Papara numarasına para gönderin. Bu işlemi gerçekleştirmek için `MassPayment` servisinde bulunan `PostMassPayment` methodunu kullanın. `AccountNumber`, `Amount` ve `MassPaymentId` gönderilmelidir.

### MassPaymentToPaparaNumberOptions

`MassPaymentToPaparaNumberOptions` `MassPayment` servisine istek parametrelerini sağlamak için kullanılır.

| **Değişken Adı**   | **Tip**    | **Açıklama**                                                 |
| ------------------ | ---------- | ------------------------------------------------------------ |
| accountNumber      | String     | Papara hesap numarasını alır veya belirler. Ödemeyi alacak kullanıcının 10 haneli Papara numarası. 1234567890 veya PL1234567890 biçiminde olabilir. Papara sürüm geçişinden önce Papara numarasına cüzdan numarası deniyordu, eski cüzdan numaraları Papara numarası olarak değiştirildi. Ödeme eski cüzdan numaralarına dağıtılabilir. |
| parseAccountNumber | Integer    | Ayrıştırma hesap numarasını alır veya belirler. Hesap numarasını long tip olarak ayrıştırır. Eski papara entegrasyonlarında PL ile başlanarak hesap / cüzdan numarası yapılıyordu. Hizmet, kullanıcılarından papara numarasını alan üye işyerlerine sorun yaşatmaması için PL ile başlayan numaraları kabul edecek şekilde yazılmıştır. |
| amount             | BigDecimal | Miktarı alır veya belirler. Ödeme işleminin tutarıdır. Bu tutar ödemeyi alan kullanıcının hesabına aktarılacaktır. Bu rakam artı işlem ücreti üye işyeri hesabından tahsil edilecektir. |
| massPaymentId      | String     | Ödeme ID'sini alır veya belirler. Ödeme işlemlerinde hatalı tekrarları önlemek için üye işyeri tarafından gönderilen benzersiz değerdir. Kısa süre önce gönderilmiş ve başarılı olan bir massPaymentId yeni bir taleple tekrar gönderilirse, istek başarısız olur. |
| turkishNationalId  | Long       | TC kimlik numarasını alır veya belirler. Ödemeyi alacak kullanıcının gönderdiği kimlik bilgilerinin Papara sisteminde kontrolünü sağlar. Kimlik bilgilerinde bir çelişki olması durumunda işlem gerçekleşmeyecektir. |
| description        | String     | Açıklamayı alır veya ayarlar. Üye iş yeri tarafından sağlanan işlemin açıklamasıdır. Zorunlu bir alan değildir. Gönderilirse işlem açıklamalarında alıcı tarafından görülür. |

### Servis Methodu

#### Kullanım Amacı

Üye iş yeri için verilen hesap numarasına ödeme göndermek için kullanılır

| **Method**           | **Parametreler**                 | **Dönüş Tipi**             |
| -------------------- | -------------------------------- | -------------------------- |
| massPaymentByAccount | MassPaymentToPaparaNumberOptions | ServiceResult<MassPayment> |

#### Kullanım Şekli

```java
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

## E-Posta Adresine Ödeme Gönderme

Papara'da kayıtlı e-posta adresine para gönderin. Bu işlemi gerçekleştirmek için `MassPayment` servisinde bulunan `massPaymentByEmail` methodunu kullanın. `E-mail`, `amount` ve `massPaymentId` gönderilmelidir.

### MassPaymentToEmailOptions

`MassPaymentToEmailOptions` `MassPayment` servisine istek parametrelerini sağlamak için kullanılır.

| **Değişken Adı**  | **Tip**    | **Açıklama**                                                 |
| ----------------- | ---------- | ------------------------------------------------------------ |
| email             | String     | Hedef e-posta adresini alır veya belirler.                   |
| amount            | BigDecimal | Miktarı alır veya belirler. Ödeme işleminin tutarıdır. Bu tutar ödemeyi alan kullanıcının hesabına aktarılacaktır. Bu rakam artı işlem ücreti üye işyeri hesabından tahsil edilecektir |
| massPaymentId     | String     | Ödeme ID'sini alır veya belirler. Ödeme işlemlerinde hatalı tekrarları önlemek için üye işyeri tarafından gönderilen benzersiz değerdir. Kısa süre önce gönderilmiş ve başarılı olan bir massPaymentId yeni bir taleple tekrar gönderilirse, istek başarısız olur. |
| turkishNationalId | Long       | TC kimlik numarasını alır veya belirler. Ödemeyi alacak kullanıcının gönderdiği kimlik bilgilerinin Papara sisteminde kontrolünü sağlar. Kimlik bilgilerinde bir çelişki olması durumunda işlem gerçekleşmeyecektir. |
| description       | String     | Açıklamayı alır veya ayarlar. Üye iş yeri tarafından sağlanan işlemin açıklamasıdır. Zorunlu bir alan değildir. Gönderilirse işlem açıklamalarında alıcı tarafından görülür. |

### Servis Methodu

#### Kullanım Amacı

Üye iş yeri için verilen e-posta adresine ödeme göndermek için kullanılır

| **Method**         | **Parametreler**          | **Dönüş Tipi**             |
| ------------------ | ------------------------- | -------------------------- |
| massPaymentByEmail | MassPaymentToEmailOptions | ServiceResult<MassPayment> |

#### Kullanım Şekli

```java
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

## Telefon Numarasına Ödeme Gönderme

Papara'da kayıtlı telefon numarasına para gönderin. Bu işlemi gerçekleştirmek için `MassPayment` servisinde bulunan `massPaymentByPhone` methodunu kullanın. `phoneNumber`, `amount` ve `massPaymentId` gönderilmelidir.

### MassPaymentToPaparaNumberOptions

`MassPaymentToPhoneNumberOptions` `MassPayment` servisine istek parametrelerini sağlamak için kullanılır.

| **Değişken Adı**  | **Tip**    | **Açıklama**                                                 |
| ----------------- | ---------- | ------------------------------------------------------------ |
| phoneNumber       | string     | Kullanıcının telefon numarasını alır veya belirler. Ödemeyi alacak kullanıcının Papara'da kayıtlı cep telefonu numarasıdır. Bir ülke kodu içermeli ve + ile başlamalıdır. |
| amount            | BigDecimal | Miktarı alır veya belirler. Ödeme işleminin tutarıdır. Bu tutar ödemeyi alan kullanıcının hesabına aktarılacaktır. Bu rakam artı işlem ücreti üye işyeri hesabından tahsil edilecektir |
| massPaymentId     | String     | Ödeme ID'sini alır veya belirler. Ödeme işlemlerinde hatalı tekrarları önlemek için üye işyeri tarafından gönderilen benzersiz değerdir. Kısa süre önce gönderilmiş ve başarılı olan bir massPaymentId yeni bir taleple tekrar gönderilirse, istek başarısız olur. |
| turkishNationalId | Long       | TC kimlik numarasını alır veya belirler. Ödemeyi alacak kullanıcının gönderdiği kimlik bilgilerinin Papara sisteminde kontrolünü sağlar. Kimlik bilgilerinde bir çelişki olması durumunda işlem gerçekleşmeyecektir. |
| description       | String     | Açıklamayı alır veya ayarlar. Üye iş yeri tarafından sağlanan işlemin açıklamasıdır. Zorunlu bir alan değildir. Gönderilirse işlem açıklamalarında alıcı tarafından görülür. |

### Servis Methodu

#### Kullanım Amacı

Üye iş yeri için verilen telefon numarasına ödeme göndermek için kullanılır

| **Method**         | **Parametreler**                | **Dönüş Tipi**             |
| ------------------ | ------------------------------- | -------------------------- |
| massPaymentByPhone | MassPaymentToPhoneNumberOptions | ServiceResult<MassPayment> |

#### Kullanım Şekli

```java
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

| **Hata Kodu** | **Hata Açıklaması**                                          |
| ------------- | ------------------------------------------------------------ |
| 100           | Kullanıcı bulunamadı                                         |
| 105           | Yetersiz bakiye                                              |
| 107           | Alıcı bakiye limitini aşıyor. Basit hesaplar için mümkün olan en yüksek bakiye 750 TL'dir. |
| 111           | Alıcı aylık işlem limitini aşıyor. Basit hesaplar tanımlı kaynaktan aylık toplam 2000 TL ödeme alabilir. |
| 133           | MassPaymentID yakın zamanda kullanıldı.                      |
| 997           | Ödemeleri dağıtma yetkiniz yok. Müşteri temsilcinizle iletişime geçebilir ve üye iş yeri hesabınıza bir ödeme dağıtım tanımı talep edebilirsiniz. |
| 998           | Gönderdiğiniz parametreler beklenen formatta değil. Örnek: Müşteri numarası 10 haneden az. Bu durumda, hata mesajı format hatasının ayrıntılarını içerir. |
| 999           | Papara sisteminde bir hata oluştu.                           |

# <a name="payments">Ödeme Alma</a> 

Ödeme alma, oluşturma veya listeleme ve geri ödeme için ödeme hizmeti kullanılacaktır. Ödeme butonunu kullanıcılara göstermeden önce üye işyeri Papara'da bir ödeme işlemi oluşturmalıdır. Ödeme kayıtları zamana bağlıdır. Son kullanıcı tarafından tamamlanmayan ve ödenmeyen işlem kayıtları 1 saat sonra Papara sisteminden silinir. Tamamlanan ödeme kayıtları asla silinmez ve her zaman API ile sorgulanabilir.

## Ödeme Bilgilerine Erişim

Ödeme bilgilerini döndürür. Bu işlemi gerçekleştirmek için `Payment` servisinde bulunan`getPayment` methodunu kullanın. `id` gönderilmelidir.

### Payment Model

`Payment` sınıfı, `Payment` servisi tarafından API'den dönen ödeme değerlerini eşleştirmek için kullanılır.

| **Değişken Adı**         | **Tip**    | **Açıklama**                                                 |
| ------------------------ | ---------- | ------------------------------------------------------------ |
| merchant                 | Account    | Üye iş yerini alır veya belirler                             |
| id                       | String     | ID'yi alır veya belirler                                     |
| createdAt                | String     | Ödemenin oluşturulma tarihini alır veya belirler             |
| merchantId               | String     | Üye iş yeri ID'sini alır veya belirler                       |
| userId                   | string     | Kullanıcı ID'sini alır veya belirler                         |
| paymentMethod            | Integer    | Ödeme Yöntemini alır veya belirler. <br />0 - Kullanıcı, mevcut Papara bakiyesiyle işlemi tamamladı <br />1 - Kullanıcı, işlemi daha önce tanımlanmış bir banka / kredi kartı ile tamamladı. <br />2 - Kullanıcı, mobil ödeme yoluyla işlemi tamamladı. |
| paymentMethodDescription | String     | Ödeme yöntemi açıklamasını alır veya belirler.               |
| referenceId              | String     | Referans numarasını alır veya belirler.                      |
| orderDescription         | String     | Gets or sets order description                               |
| status                   | Integer    | Ödeme durumunu alır veya belirler.<br /> 0 - Bekleniyor, ödeme henüz yapılmadı. <br />1 - Ödeme yapıldı, işlem tamamlandı. 2 - İşlemler üye işyeri tarafından iade edildi. |
| statusDescription        | String     | Sipariş açıklamasını alır veya belirler.                     |
| amount                   | BigDecimal | Ödeme durumu açıklamasını alır veya belirler                 |
| fee                      | BigDecimal | Ödeme hizmet bedelini alır veya belirler                     |
| currency                 | Integer    | Ödeme yapılacak para birimini alır veya belirler. Değerler “0”,  “1”, “2” veya  “3” olabilir. |
| notificationUrl          | String     | Bildirim URL'ini alır veya belirler.                         |
| notificationDone         | Boolean    | Bildirimin yapılıp yapılmadığını alır veya belirler.         |
| redirectUrl              | String     | Yönlendirme URL'ini alır veya belirler.                      |
| paymentUrl               | String     | Ödeme URL'ini alır veya belirler.                            |
| merchantSecretKey        | String     | Üye iş yeri gizli anahtarını alır veya belirler.             |
| returningRedirectUrl     | String     | Geri dönen yönlendirme URL'ini alır veya belirler.           |
| turkishNationalId        | Long       | TC kimlik numarasını alır veya belirler.                     |

### PaymentGetOptions

`PaymentGetOptions` ödeme bilgilerine ulaşırken parametre olarak kullanılır

| **Değişken Adı** | **Tip** | **Açıklama**                   |
| ---------------- | ------- | ------------------------------ |
| id               | String  | Gets or sets unique payment ID |

### Servis Methodu

#### Kullanım Amacı

Ödeme ve bakiye bilgilerine erişmek için kullanılır

| **Method** | **Parametreler**  | **Dönüş Tipi**         |
| ---------- | ----------------- | ---------------------- |
| getPayment | PaymentGetOptions | ServiceResult<Payment> |

#### Kullanım Şekli

```java
@Override
    public ServiceResult<Payment> getPayment() throws PaparaRESTException {
        PaymentGetOptions paymentGetOptions = new PaymentGetOptions();
        paymentGetOptions.setId("PAYMENT_ID");
       
        ServiceResult<Payment> result = paymentService.getPayment(paymentGetOptions);
        return result;
    }
```

## Get Payment By Payment Reference Number

Ödeme bilgilerini döndürür. Bu işlemi gerçekleştirmek için `Payment` servisinde bulunan `getPaymentByReference` methodunu kullanın. `referenceId` gönderilmelidir.


### PaymentGetByReferenceOptions

`PaymentGetByReferenceOptions` ödeme bilgilerine ulaşırken parametre olarak kullanılır

| **Değişken Adı** | **Tip** | **Açıklama**                                 |
| ---------------- | ------- | -------------------------------------------- |
| referenceId      | string  | Ödeme referans numarasını alır veya belirler |

### Servis Methodu

#### Kullanım Amacı

Üye iş yeri için ödeme ve bakiye bilgilerine erişmek istenildiğinde kullanılır.

| **Method**            | **Parametreler**             | **Dönüş Tipi**        |
| --------------------- | ---------------------------- | --------------------- |
| getPaymentByReference | PaymentGetByReferenceOptions | PaparaResult<Payment> |

#### Kullanım Şekli

```java
@Override
public ServiceResult<Payment> getPaymentByReference() throws PaparaRESTException 
  {
     PaymentReferenceGetOptions paymentReferenceGetOptions = new PaymentReferenceGetOptions();
     paymentReferenceGetOptions.setReferenceId("PAYMENT_REFERENCE_NUMBER");

     ServiceResult<Payment> result = paymentService.getPaymentByReference(paymentReferenceGetOptions);
     return result;
  }
```



## Create Payment

Yeni bir ödeme kaydı oluşturur. Bu işlemi gerçekleştirmek için `Payment` servisinde bulunan `createPayment`  methodunu kullanın. `Amount`, `ReferenceId`, `orderDescription`, `notificationUrl` ve `redirectUrl` sağlanmalıdır.

### PaymentCreateOptions

`PaymentCreateOptions` ödeme oluştururken parametre olarak kullanılır

| **Değişken Adı**  | **Tip**    | **Açıklama**                                                 |
| ----------------- | ---------- | ------------------------------------------------------------ |
| amount            | BigDecimal | Ödeme yapılacak miktarı alır veya belirler. Ödeme işleminin tutarı. Tam olarak bu tutar ödemeyi yapan kullanıcının hesabından alınacak ve bu tutar ödeme ekranında kullanıcıya gösterilecektir. Miktar alanı minimum 1.00, maksimum 500000.00 olabilir |
| referenceId       | String     | Referans ID'sini alır veya belirler. Üye işyeri sistemindeki ödeme işleminin referans bilgileridir. İşlem, Papara'ya gönderildiği gibi sonuç bildirimlerinde değiştirilmeden üye işyerine iade edilecektir. 100 karakterden fazla olmamalıdır. Bu alanın benzersiz olması gerekmez ve Papara böyle bir kontrol yapmaz |
| orderDescription  | String     | Sipariş açıklamasını alır veya belirler. Ödeme işleminin açıklamasıdır. Gönderilen bilgi, Papara ödeme sayfasında kullanıcıya gösterilecektir. Kullanıcı tarafından başlatılan işlemi doğru bir şekilde bildiren bir tanıma sahip olmak, başarılı ödeme şansını artıracaktır. |
| notificationUrl   | String     | Bildirim URL'sini alır veya belirler. Ödeme bildirim isteklerinin (IPN) gönderileceği URL'dir.  "NotificationUrl" ile gönderilen URL'ye Papara, ödeme tamamlandıktan hemen sonra bir HTTP POST isteği ile ödemenin tüm bilgilerini içeren bir ödeme nesnesi gönderecektir. Üye işyeri bu talebe 200 OK döndürürse tekrar bildirim yapılmayacaktır. Üye işyeri bu bildirime 200 OK dönmezse, Papara, üye işyeri 200 OK'e dönene kadar 24 saat boyunca ödeme bildirimi (IPN) talepleri yapmaya devam edecektir. |
| redirectUrl       | String     | Yönlendirme URL'sini alır veya belirler. İşlemin sonunda kullanıcının yönlendirileceği URL |
| turkishNationalId | Long       | TC kimlik numarasını alır veya belirler. Ödemeyi alacak kullanıcının gönderdiği kimlik bilgilerinin Papara sisteminde kontrolünü sağlar. Kimlik bilgilerinde bir çelişki olması durumunda işlem gerçekleşmeyecektir. |

### Servis Methodu

#### Kullanım Amacı

Ödeme oluşturmak için kullanılacaktır.

| **Method**    | **Parametreler**     | **Dönüş Tipi**         |
| ------------- | -------------------- | ---------------------- |
| createPayment | PaymentCreateOptions | ServiceResult<Payment> |

#### Kullanım Şekli

```java
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

## Refund 

Üye iş yerinin ödeme ID'siyle tamamlanmış bir ödemesini iade etmesini sağlar. Bu işlemi gerçekleştirmek için `Payment` servisinde bulunan `refundPayment` yöntemini kullanın. `id` gönderilmelidir.

### PaymentRefundOptions

`PaymentRefundOptions` iade oluştururken parametre olarak kullanılır.

| **Değişken Adı** | **Tip** | **Açıklama**                     |
| ---------------- | ------- | -------------------------------- |
| id               | String  | Ödeme ID'sini alır veya belirler |

### Servis Methodu

#### Kullanım Amacı

Üye iş yeri için bir ödemenin iade edileceği durumlarda kullanılır.

| **Method**    | **Parametreler**     | **Dönüş Tipi** |
| ------------- | -------------------- | -------------- |
| refundPayment | PaymentRefundOptions | ServiceResult  |

#### Kullanım Şekli

```java
@Override
    public ServiceResult refundPayment() throws PaparaRESTException {
        PaymentRefundOptions paymentRefundOptions = new PaymentRefundOptions();
        paymentRefundOptions.setId("PAYMENT_ID");

        ServiceResult result = paymentService.refundPayment(paymentRefundOptions);
        return result;

    }
```

## Ödemeleri Listeleme

Üye iş yerinin tamamlanan ödemelerini sıralı bir şekilde listeler. Bu işlemi gerçekleştirmek için `Payment` servisinde buluan `paymentList` methodunu kullanın. `pageIndex`ve `pageItemCount `gönderilmelidir.

### PaymentListItem

`PaymentListItem` sınıfı `Payment` servisi tarafından API'den dönen liste bilgilerini eşleştirmek için kullanılır

| **Değişken Adı**         | **Tip**    | **Açıklama**                                                 |
| ------------------------ | ---------- | ------------------------------------------------------------ |
| id                       | String     | Ödeme ID'sini alır veya belirler.                            |
| createdAt                | String     | Ödemenin yapıldığı tarihi alır veya belirler.                |
| merchantId               | String     | Üye iş yeri ID'sini alır veya belirler.                      |
| userId                   | String     | Kullanıcı ID'sini alır veya belirler.                        |
| paymentMethod            | Integer    | Ödeme Yöntemini alır veya belirler<br />0 - Kullanıcı, mevcut Papara bakiyesiyle işlemi tamamladı <br />1 - Kullanıcı, işlemi daha önce tanımlanmış bir banka / kredi kartı ile tamamladı. <br />2 - Kullanıcı, mobil ödeme yoluyla işlemi tamamladı. |
| paymentMethodDescription | String     | Ödeme açıklamasını alır veya belirler.                       |
| referenceId              | String     | Referans ID'yi alır veya belirler.                           |
| orderDescription         | String     | Sipariş açıklamasını alır veya belirler.                     |
| Status                   | Integer    | Ödeme durumunu alır veya belirler. <br />0 - Bekleniyor, ödeme henüz yapılmadı. <br />1 - Ödeme yapıldı, işlem tamamlandı. <br />2 - İşlemler üye işyeri tarafından iade edilir. |
| statusDescription        | String     | Ödeme durum açıklamasını alır veya belirler.                 |
| amount                   | BigDecimal | Ödeme tutarını alır veya belirler.                           |
| fee                      | BigDecimal | Hizmet bedelini alır veya belirler.                          |
| currency                 | Integer    | Ödemenin yapıldığı para birimini alır veya belirler. Olabilecek değerler “0”,  “1”, “2” veya “3” |
| notificationUrl          | String     | Yönlendirme URL'ini alır veya belirler                       |
| notificationDone         | Boolean    | Bildirimin yapılıp yapılmadığını alır veya belirler          |
| redirectUrl              | String     | Yönlendirme URL'ini alır veya belirler                       |
| paymentUrl               | String     | Ödeme URL'ini alır veya belirler                             |
| merchantSecretKey        | String     | Üye iş yeri gizli anahtarını alır veya belirler              |
| returningRedirectUrl     | String     | Geri dönüş URL'ini alır veya belirler                        |
| turkishNationalId        | Long       | TC Kimlik numarasını alır veya belirler                      |

### PaymentListOptions

`PaymentListOptions` `Payment`  servisi tarafından istek parametrelerini sağlamak için kullanılır

| **Değişken Adı** | **Tip** | **Açıklama**                                                 |
| ---------------- | ------- | ------------------------------------------------------------ |
| pageIndex        | Integer | Sayfa dizinini alır veya belirler. Bir sayfada gösterilmek istenen kayıt sayısına (pageItemCount) göre hesaplanan sayfalardan gösterilmek istenen sayfanın indeks numarasıdır. Not: ilk sayfa her zaman 1'dir |
| pageItemCount    | Integer | Sayfa öğesi sayısını alır veya belirler. Bir sayfada gösterilmesi istenen kayıtların sayısıdır. |

### Servis Methodu

#### Kullanım Amacı

Üye iş yerilar için tamamlanmış ödemeleri yeniden eskiye doğru sıralayacal bir şekilde görüntülemek için kullanılır

| **Method**  | **Parametreler**   | **Dönüş Tipi**                               |
| ----------- | ------------------ | -------------------------------------------- |
| paymentList | PaymentListOptions | ServiceResult<PagingResult<PaymentListItem>> |

#### Kullanım Şekli

```java
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

| **Hata Kodu** | **Hata Açıklaması**                                          |
| ------------- | ------------------------------------------------------------ |
| 997           | Ödemeleri kabul etme yetkiniz yok. Müşteri temsilcinizle iletişime geçmelisiniz. |
| 998           | Gönderdiğiniz parametreler beklenen formatta değil. Örnek: zorunlu alanlardan biri sağlanmamıştır. |
| 999           | Papara sisteminde bir hata oluştu.                           |

# <a name="validation">Doğrulama</a> 

Bir son kullanıcıyı doğrulamak için doğrulama servisi kullanılacaktır. Doğrulama, hesap numarası, e-posta adresi, telefon numarası, ulusal kimlik numarası ile yapılabilir.

## Kullanıcı ID'si ile Doğrulama

Papara kullanıcı ID'si ile kullanıcıları doğrulamak için kullanılır. Bu işlemi gerçekleştirmek için `Validation` servisinde bulunan `validationById`methodunu kullanın. `userId` gönderilmelidir.

### Validation Model           

`Validation` sınıf, `Validation` servisi tarafından API'den dönen kullanıcı değerini eşleştirmek için kullanılır

| **Değişken Adı** | **Tip** | **Açıklama**                                          |
| ---------------- | ------- | ----------------------------------------------------- |
| userId           | string  | Kullanıcı ID'sini alır veya belirler.                 |
| firstName        | string  | Kullanıcının ismini alır veya belirler.               |
| lastName         | string  | Kullanıcının soyismini alır veya belirler.            |
| email            | string  | Kullanıcının e-posta adresini alır veya belirler.     |
| phoneNumber      | string  | Kullanıcının telefon numarasını alır veya belirler.   |
| tckn             | Long    | Kullanıcının TC kimlik numarasını alır veya belirler. |
| accountNumber    | Integer | Kullanıcının hesap numarasını alır veya belirler.     |

### ValidationByIdOptions 

`ValidationByIdOptions` `Validation` servisi tarafından istek parametrelerini sağlamak için kullanılır.

| **Değişken Adı** | **Tip** | **Açıklama**                |
| :--------------- | ------- | --------------------------- |
| userId           | string  | Gets or sets papara User ID |

### Servis Methodu

#### Kullanım Amacı

Kullanıcı ID'si ile doğrulama yapılmak istenildiğinde kullanılır

| **Method**     | **Parametreler**      | **Dönüş Tipi**            |
| -------------- | --------------------- | ------------------------- |
| validationById | ValidationByIdOptions | ServiceResult<Validation> |

#### Kullanım Şekli

```java
@Override
    public ServiceResult<Validation> validationById() throws PaparaRESTException {
        ValidationByIdOptions validationByIdOptions = new ValidationByIdOptions();
        validationByIdOptions.setUserId(AppSettings.personalAccountId);
        
        ServiceResult<Validation> result = validationService.validationById(validationByIdOptions);
        return result;
    }
```

## Hesap Numarası ile Doğrulama

Papara hesap numarası ile kullanıcıları doğrulamak için kullanılır. Bu işlemi gerçekleştirmek için `Validation` servisinde bulunan `validationByAccountNumber` methodunu kullanın. `accountNumber` gönderilmelidir.

### ValidationByAccountNumberOptions

`ValidationByAccountNumberOptions` `Validation ` servisi tarafından istek parametrelerini sağlamak için kullanılır.

| **Değişken Adı** | **Tip** | **Açıklama**                                |
| ---------------- | ------- | ------------------------------------------- |
| accountNumber    | Long    | Papara hesap numarasını alır veya belirler. |

### Servis Methodu

#### Kullanım Amacı

Papara hesap numarası ile doğrulama yapılmak istenildiğinde kullanılır

| **Method**                | **Parametreler**                 | **Dönüş Tipi**            |
| ------------------------- | -------------------------------- | ------------------------- |
| validationByAccountNumber | ValidationByAccountNumberOptions | ServiceResult<Validation> |

#### Kullanım Şekli

```java
@Override
    public ServiceResult<Validation> validationByAccountNumber() throws PaparaRESTException {
        ValidationByAccountNumberOptions validationByAccountNumberOptions = new ValidationByAccountNumberOptions();
        validationByAccountNumberOptions.setAccountNumber(Long.valueOf(AppSettings.personalAccountNumber));
        
        ServiceResult<Validation> result = validationService.validationByAccountNumber(validationByAccountNumberOptions);
        return result;
    }
```

## Telefon Numarası ile Doğrulama

Paparaya kayıtlı telefon numarası ile kullanıcıları doğrulamak için kullanılır. Bu işlemi gerçekleştirmek için `Validation` servisinde bulunan `validationByPhoneNumber`methodunu kullanın. `phoneNumber` gönderilmelidir.

### ValidationByPhoneNumberOptions

`ValidationByPhoneNumberOptions` `Validation` servisi tarafından istek parametrelerini sağlamak için kullanılır.

| **Değişken Adı** | **Tip** | **Açıklama**                                                |
| ---------------- | ------- | ----------------------------------------------------------- |
| phoneNumber      | String  | Paparaya kayıtlı olan telefon numarasını alır veya belirler |

### Servis Methodu

#### Kullanım Amacı

Paparaya kayıtlı telefon numarası ile doğrulama yapılmak istenildiğinde kullanılır

| **Method**            | **Parametreler**               | **Dönüş Tipi**            |
| --------------------- | ------------------------------ | ------------------------- |
| validateByPhoneNumber | ValidationByPhoneNumberOptions | ServiceResult<Validation> |

#### Kullanım Şekli

```java
@Override
    public ServiceResult<Validation> validationByPhoneNumber() throws PaparaRESTException {
        ValidationByPhoneNumberOptions validationByPhoneNumberOptions = new ValidationByPhoneNumberOptions();
        validationByPhoneNumberOptions.setPhoneNumber(AppSettings.personalPhoneNumber);
        
        ServiceResult<Validation> result = validationService.validationByPhoneNumber(validationByPhoneNumberOptions);
        return result;
    }
```

## E-Posta Adresi ile Doğrulama

Paparaya kayıtlı e-posta adresi ile kullanıcıları doğrulamak için kullanılır. Bu işlemi gerçekleştirmek için `Validation` servisinde bulunan `validationByEmail`methodunu kullanın. `email` gönderilmelidir.

### ValidationByEmailOptions

`ValidationByEmailOptions` is used by validation service for providing request parameters

| **Değişken Adı** | **Tip** | **Açıklama**                                              |
| ---------------- | ------- | --------------------------------------------------------- |
| email            | String  | Paparaya kayıtlı olan e-posta adresini alır veya belirler |

### Servis Methodu

#### Kullanım Amacı

Paparaya kayıtlı e-posta adresi ile doğrulama yapılmak istenildiğinde kullanılır

| **Method**        | **Parametreler**         | **Dönüş Tipi**            |
| ----------------- | ------------------------ | ------------------------- |
| validationByEmail | ValidationByEmailOptions | ServiceResult<Validation> |

#### Kullanım Şekli

```java
@Override
    public ServiceResult<Validation> validationByEmail() throws PaparaRESTException {
        ValidationByEmailOptions validationByEmailOptions = new ValidationByEmailOptions();
        validationByEmailOptions.setEmail(AppSettings.personalEmail);
        
        ServiceResult<Validation> result = validationService.validationByEmail(validationByEmailOptions);
        return result;
    }
```

## TC Kimlik Numarası ile Doğrulama

Paparaya kayıtlı TC kimlik numarası ile kullanıcıları doğrulamak için kullanılır. Bu işlemi gerçekleştirmek için `Validation` servisinde bulunan `validationByTckn`methodunu kullanın. `tckn` gönderilmelidir.

### ValidationByTcknOptions

`ValidationByPhoneNumberOptions` is used by validation service for providing request parameters.

| **Değişken Adı** | **Tip** | **Açıklama**                            |
| ---------------- | ------- | --------------------------------------- |
| tckn             | Long    | TC Kimlik numarasını alır veya belirler |

### Servis Methodu

#### Kullanım Amacı

Paparaya kayıtlı TC kimlik numarası ile doğrulama yapılmak istenildiğinde kullanılır

| **Method**       | **Parametreler**        | **Dönüş Tipi**            |
| ---------------- | ----------------------- | ------------------------- |
| validationByTckn | ValidationByTcknOptions | ServiceResult<Validation> |

#### Kullanım Şekli

```java
@Override
    public ServiceResult<Validation> validationByTckn(4) throws PaparaRESTException {
        ValidationByTcknOptions validationByTcknOptions = new ValidationByTcknOptions();
        validationByTcknOptions.setTckn(AppSettings.tckn);
        
        ServiceResult<Validation> result = validationService.validationByTckn(validationByTcknOptions);
        return result;

    }
```



# <a name="response-types">Response Types</a>

Bu bölüm, API'den dönüş değerleri hakkında teknik bilgiler içerir.

## ServiceResult

Papara Service Result tipi. API'ye gönderilen ve API'den dönen nesne veri tiplerini işler.

| **Değişken Adı** | **Tip**              | **Açıklama**                                                 |
| :--------------- | -------------------- | ------------------------------------------------------------ |
| data             | T                    | Genel nesne dönüş tipi. Verilen nesne tipi değerini döndürür |
| succeeded        | Boolean              | İşlemin başarıyla sonuçlanıp sonuçlanmadığını gösteren bir değer alır veya belirler |
| error            | ServiceError         | İşlemin başarısız olup olmadığını gösteren bir değer alır veya belirler |
| result           | ServiceSuccessResult | Başarılı olan işlem sonucunu alır veya belirler.             |

## ServiceError

Papara Service Error Result tipi. API'den dönen hata yanıtlarını işler.

| **Değişken Adı** | **Tip** | **Açıklama**                     |
| ---------------- | ------- | -------------------------------- |
| message          | String  | Hata mesajını alır veya belirler |
| code             | Integer | Hata kodunu alır veya belirler   |

## ServiceSuccessResult

Papara Service Success Result tipi.  API'den dönen başarılı yanıtları işler.


| **Değişken Adı** | **Tip** | **Açıklama**                                      |
| ---------------- | ------- | ------------------------------------------------- |
| message          | String  | Başarılı işlem sonuç mesajını alır veya belirler  |
| code             | Integer | Başarılı işlem sonuç kodlarını alır veya belirler |

## PagingResult

Papara Paging tipi. API'ye gönderilen ve API'den dönen sayfalandırılmış veri tiplerini işler.


| **Değişken Adı** | **Tip** | **Açıklama**                                                 |
| ---------------- | ------- | ------------------------------------------------------------ |
| items            | List<D> | API'den dönen öğeleri alır veya ayarlar. Verilen nesne tipinin listesini döndürür |
| page             | Integer | Sayfa sayısını alır veya belirler                            |
| pageItemCount    | Integer | Sayfadaki öge sayısını alır veya belirler                    |
| totalItemCount   | Integer | Toplam öge sayısını alır veya belirler                       |
| totalPageCount   | Integer | Toplam sayfa sayısını alır ve belirler                       |
| pageSkip         | Integer | Kaç sayfanın atlanacağını alır veya belirler                 |