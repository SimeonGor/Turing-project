## Установка
1. Клонирование репозитория
2. Создание переменных окружения

```export GIGACHAT_CLIENT_ID=<client-id>```

```export GIGACHAT_CLIENT_SECRET=<client-secret>```

3. Запусить Spring
```mvnw spring-boot:run```

## Зависимости
Java17

## FAQ
### Ошибка неподтвержденного сертификата
1. Необходимо установить [Сертификаты НУЦ Минцифры](https://developers.sber.ru/docs/ru/gigachat/certificates)
2. Добавить в хранилище сертификатов jdk

```keytool -import -trustcacerts -file <файл_сертификата> -alias russian_trusted_sub_ca-cer -keystore <путь до хранилища сертификатов>```
