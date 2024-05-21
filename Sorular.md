# SORULAR
## 1. GÜN
1. password ve repassword aynı mı kontrolü yaparak register olmasını sağlayalım. Eşit değilse hata fırlatalım.
   Şifreler uyuşmamaktadır gibi bir hata dönelim.
2. Entity -> Dto arasındaki dönüşümleri Mapper kullanarak gerçekleştirelim.
3. Register olma zamanını ve state'ini de belirleyelim. Register olurken state true olmalı.
3. ResponseDto'lar hazırlayalım. Register ve Login işlemleri için ayrı ayrı ResponseDto dönelim.

Yapanlar-> Can, İsmet, Kenan, Aslıhan, Serkan, Berk

## 2.GÜN
1. Bir BaseEntity sınıfı açalım. Bu sınıf içinde diğer alt entitylerin çoğunda kullanılması planlanan fieldları tutacağız.
   (Long createat, Long updateat, Boolean state)
   Auth entity'si BaseEntityden miras alacak.
   Auth uygun şekilde çalışmaya devam edecek.
   Auth classına @SuperBuilder, BaseEntity classına @SuperBuilder, @MappedSuperclass anotasyonu eklemek gereklidir.
2. Login olma işlemi sonucunda neden Entity(Auth) dönülmez?

3. clienta bir token döneceğiz. Ama öncesinde sadece bir string değer ile bunu temsil edelim.

4. // authtoken:2453  buradan geriye 2453 değerini dönmeli:
   public Long getIdFromToken(String token){
   //todo: metod içinde tokendaki id bilgisini geriye Long olarak dönecek kodlar yazılacak.
   }
5. Tüm authları listeleyecek bir endpoint açalım. (Salih)
* Ancak buraya sadece Login olup token alabilenler istek atabilsin.
* Geçerli tokenı olmayanlar istek atamasın.
* Token geçerli değilse hata fırlatalım.

6. secretKey kodumuzun içinde olmasını istemiyorsak, yml dosyasında tutabiliriz. Yml içinde tutmak sadece bu tür kodları merkezileştirmeye yardımcı olur. Gizlemeye yardımcı olmaz.
7. Bu tür gizlenmesi gereken değerleri ortam değişkenlerine key value şeklinde değerleri girerek saklayabiliriz.
8. Windowsta Sistem Özellikleri > Gelişmiş Sekmesi > ortam değişkenleri kısmına key value şeklinde değer girilebilir.
9. IntelliJ IDEA ile de bu değerler girilebilir. Run> Edit Configurations > Modify Options > Environments Variables aktif hale getirilir. Ve o kısma key value şeklinde değerler girilebilir.
10. Ortam değişkenlerindeki değerlere yml'dan erişim sağlanabilir.
11. secret-key: ${JAVA14_AUTH_SECRET_SYSTEM}
12. Ymldaki değerlere diğer sınıflardan erişim sağlanabilir.
    @Value("${authservice.secret.secret-key}")
    String secretKey;
## 4.GÜN
1. UserProfileMicroService adlı yeni bir microservice(modüle) ekleyelim.  (15dk.)
* İçerisine Authtaki gibi tüm packageları oluşturalım.
* Main class adını değiştirelim. (UserProfileMicroServiceApplication) Gerekli anotasyonları ekleyelim.
* application.yml dosyasını ayarlayalım. port:9091 yapalım.
* build.gradle içi boş kalsın.
* Exceptionda gerekli düzenlemeyi yapınız.
* Endpointste gerekli düzenlemeyi yapınız.
2. MongoDb Community Server'ı kendi işletim sisteminize uygun versiyonunu indiriniz.
   Windows için: https://fastdl.mongodb.org/windows/mongodb-windows-x86_64-7.0.9-signed.msi
3. Kurulum aşamasında Compass'ı da seçiyoruz. Compass ile MongoDB yönetimsel işlemlerini GUI arayüz ile halledebileceğiz.
4. Compass açıldığında MongoDB'nin portunu bize gösteriyor: mongodb://localhost:27017
# MongoDB İşlemleri:
* Mongo ile işlem yaparken admin kullanıcısı ve şifresi kullanılmamalıdır.
* Bu nedenle önce bir db oluşturulur. Ardından bu db için bir user oluşturmak gereklidir.
* Açtığımız db adı: UserProfileDB
* Veritabanı açıldıktan sonra sol alt tarafta MONGOSH ile terminale geçiş yapmalıyız.
* MongoSH test> durumunda açılır. Burada use databaseadi diyerek kendi DB'mize geçiş yapmalıyız.
*  use UserProfileDB
   switched to db UserProfileDB
   UserProfileDB>
* Bu moda geçiş yaptıktan sonra bu db için bir user tanımlayacağız.
  db.createUser(
  {
  user:"bilge",
  pwd:"bilge!123",
  roles: ["readWrite","dbAdmin"]
  }
  )
* Aşağıdaki komutu user oluşturmak için kullanıyoruz.
  db.createUser({user:"bilge",pwd:"bilge!123",roles: ["readWrite","dbAdmin"] } )
* Bu adımdan sonra projemizde spring data mongo bağımlılığını eklememiz gerekli.
  https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-mongodb/3.2.5

# Docker üzerinde MongoDb kurulumu ve Çalıştırma:
```bash
docker run --name java14MongoDB -d -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=root -p 27027:27017 mongo:jammy 
```

## 5. Gün OpenFeign ile Servisler arası Senkron İletişim.

## 6. Gün
Common Application Properties
https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.docker-compose

### Logging İşlemleri
@Slf4j
application.yml dosyası içine aşağıdaki gibi ayarlar yazılarak loglama seviyesi belirlenebilir.
```
logging:
level:
root: trace

file:
name: userprofileservice.log

logback:
rollingpolicy:
max-file-size: 1MB
```

## ConfigServer işlemleri:
Default Port: 8888 (Mongo Default Port: 27017, PostgreSQL Default Port: 5432)

## Github token oluşturma:
Sağ üstteki profil resmine tıklayıp > Settings > Açılan pencerede Developer Settingse tıklanır.
Personel Access Tokens kısmında > Tokens Classic tıklanır.
Generate new token butonuna tıklanır > Classic olan seçilir.

# Gelen token: bir yere not alınabilir.

## Dockerda Zipkin Container ayağa kaldırma
```bash
docker run -d -p 9411:9411 --name zipkin openzipkin/zipkin
```
# RABBITMQ

## AMQP - Advanced Message Queuing Protocol -
* Spring AMQP ile rabbitmq gibi mesajlaşma sistemlerine entegre proje hazırlanabilir.
* İletişimin gerçekleşmesi için birisi mesaj üretmeli, diğeri de mesajı almalı.
* Projemizde sadece AMQP yeterli değil. Yanında rabbitmq da kullanacağız.
* Bağımlılığını projemize eklemeliyiz.
* implementation 'org.springframework.boot:spring-boot-starter-amqp:3.2.5'


## Dockerda RabbitMQ kurulumu:
* default port: amqp: 5672 (arka planda microservislerin iletişimi için)
* management port : http: 15672 (Yönetim işlemleri için yayın yaptığı port)
```bash
docker run -d --hostname my-rabbit --name some-rabbit -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password -p 15672:15672 -p 5672:5672 --memory=128m rabbitmq:3-management
```

## RabbitMQ Config Dosyası Hazırlama
* Exchange, Kuyruk ve Binding Hazırlamamız gerekli.
* Her bir için ayrı metod tanımlayıp  @Bean ile işaretledik.

## rabbitMQ package içi:
* Consumer, Producer ve Model packageları açtık.

## rabbitMQ'ya gönderilecek Model:
* Aynen dtoda olduğu gibi Gönderilecek fieldları barındıran bir model oluştururuz.
* Bu model sınıfları mutlaka implements Serializable  olmalıdır.

## ProducerService yazma:
* RabbitTemplate'ın convertAndSend metodu içine exchange adını ve routingKey(bindingKey) ile modeli yolladık.
  enviroment variable olarak eklenmeli:
* SPRING_AMQP_DESERIALIZATION_TRUST_ALL true bunu ekledikten sonra IDE kapatıp açalım.