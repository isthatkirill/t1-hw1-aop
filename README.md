## ДЗ №1 Т1 Холдинг

### Функциональность

Приложение представляет собой систему учета времени выполнения методов в приложении с использованием Spring AOP. Она позволяет асихронно логировать данные о времени выполнения методов, а также получать статистику.

### Аспекты

- Приложение содержит две аннотации: `@TrackTime` и `@TrackAsyncTime` для отслеживания синхронных и асинхронных методов соответственно.

- Аспект `ExecutionAspect` логирует и сохраняет в базу данных успешные вызовы методов, аспект `ErrorAspect` занимается обработкой методов, в процессе выполнения которых было выброшено исключение.

### Эндпоинты

В приложении реализованы CRUD-операции для демонстрационной сущности `Book`. Взаимодействие доступно по эндпоинтам:

- POST `/api/v1/books` - добавление книги
- GET `/api/v1/books` - получение книг с доступными параметрами фильтрации: `genre`, `publisher`, `name`, `isbn`, `author`, `yearOfPublication`, `pages` и параметрами пагинации: `from`, `max`.
- GET `/api/v1/books/{bookId}` - получение книги по идентификатору
- PATCH `/api/v1/books/{bookId}` - обновление книги по идентификатору
- DELETE `/api/v1/books/{bookId}` - удаление книги по идентификатору

Для получения статистики по времени выполнения методов доступны следующие эндпоинты:

- GET `/api/v1/executions/{executionId}` - получение сведений об выполнении метода по идентификатору
- GET `/api/v1/executions` - получение сведений об выполнение всех методов
- GET `/api/v1/executions/summary/` - получение статистики. Доступные параметры: `className` - получений статистики о выполнении методов класса, `methodName` - получение статистики о выполнении конкретного метода.

Пример получения сведений об одном вызове метода:

```json
GET /api/v1/executions/3

{
  "id": 3,
  "methodName": "getBooksWithFilters",
  "className": "isthatkirill.hwoneaop.service.impl.BookServiceImpl",
  "isSuccess": true,
  "millisTook": 2014,
  "executedAt": "2024-05-08T20:13:49.239132",
  "args": [
    {
      "genre": "Фэнтези",
      "publisher": "Питер"
    }
  ]
}
```

Пример получения статистики о выполнении методов класса и об одном конкретном методе, где:

- `executions` - количество вызовов;
- `min` - минимальное время выполнения;
- `avg` - среднее время выполнения;
- `max` - макисмальное время выполнения;
- `successRate` - процент методов, завершившихся успешно.

```json
GET /api/v1/executions/summary/?className=BookServiceImpl

{
    "name": "isthatkirill.hwoneaop.service.impl.BookServiceImpl",
    "executions": 13,
    "min": 143,
    "avg": 1496.4444444444443,
    "max": 3035,
    "successRate": 69.23076923076923
}

GET /api/v1/executions/summary/?methodName=getBookById&className=BookServiceImpl

{
    "name": "getBookById",
    "executions": 10,
    "min": 1015,
    "avg": 1050.25,
    "max": 1104,
    "successRate": 80.0
}
```

### Инструкция по запуску

1. Склонируйте репозиторий `git clone https://github.com/isthatkirill/t1-hw1-aop.git`

2. Перейдите в директорию с проектом `cd t1-hw1-aop`

3. Запустите PostgreSQL локально на компьютере, предварительно установив нужные параметры в `.env` файле или воспользуйтесь заранее подготовленным в `docker-compose.yaml` контейнером, запустив его с помощью команды `docker compose up`.

4. Запустите приложение посредством среды разработки или с помощью команд: `mvn clean package`, `mvn spring-boot:run`.

### Используемые технологии и библиотеки

- Spring Boot
- Spring Data JPA
- Lombok
- Mapstruct
- PostgreSQL
- Liquibase
