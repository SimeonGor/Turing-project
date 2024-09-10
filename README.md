## Установка
1. Клонирование репозитория
2. Создание переменных окружения

```export DB_USER=<username>```

```export DB_PASSWORD=<password>```

```export GIGACHAT_CLIENT_ID=<client-id>``` (при использовании GigaChat)

```export GIGACHAT_CLIENT_SECRET=<client-secret>``` (при использовании GigaChat)

3. Изменить свойства в файле ```application.properties```

   ```spring.datasource.url=<db_url>```

   ```app.llama.url=<llama_url>```

4. Построить проект

    ```mvn package ```

    ```java -jar target/Turing_project-*.*.*-SNAPSHOT.jar```

## Зависимости
Java17

## FAQ
### Ошибка неподтвержденного сертификата
1. Необходимо установить [Сертификаты НУЦ Минцифры](https://developers.sber.ru/docs/ru/gigachat/certificates)
2. Добавить в хранилище сертификатов jdk

```keytool -import -trustcacerts -file <файл_сертификата> -alias russian_trusted_sub_ca-cer -keystore <путь до хранилища сертификатов>```
