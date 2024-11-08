# Бот для VK

Пример простого бота для ВКонтакте на Java с использованием Spring Boot. Обработка событий с использованием Callback API, без использования готовых библиотек для VK API.

Бот отправляет в ответ на любое сообщение то же самое сообщение пользователя.

## Пример работы
![Привет](https://github.com/user-attachments/assets/1cd08c7d-fb1d-4721-8549-554d0fd4e1fa)

## Требования
Java 17 или выше
Maven 3.3.2 или выше

## Подготовка к использованию
Укажите свои данные в [application.properties](https://github.com/Starkvell/VkRepeatBot/blob/main/src/main/resources/application.properties).
Ключ доступа сообщества API и код подтверждения для Callback API Вы можете получить в настройках сообщества. Подробнее о получении ключа доступа для  можно прочитать [здесь](https://dev.vk.com/ru/api/access-token/community-token/in-community-settings).

## Установка
Вы можете склонировать репозиторий, и запустить бота с помощью команд:
```
mvn clean install
java -jar ./target/vk-repeat-bot-0.0.1-SNAPSHOT.jar --server.port=80
```
Или запустив контейнер в докере:
```
docker build -t repeatBot .
docker run -d -p 80:80
```

Подробную информацию о типах событий и формате уведомлений Вы найдёте [в документации ВК API](https://vk.com/dev/callback_api). 
