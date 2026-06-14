# **Backend-сервіс управління системою музичних фестивалів**

---

# **Предметна область**

Система управління музичними фестивалями для організації та планування масових подій. Забезпечує управління фестивалями, артистами, сценами, виступами та реєстрацією відвідувачів.

Розвиток індустрії розваг та масових заходів вимагає впровадження сучасних інформаційних технологій для ефективного управління ресурсами.

Організація музичних фестивалів – це складний процес, який включає координацію виступів артистів, розподіл сцен, управління розкладом та реєстрацію відвідувачів.

---

# **Сутності системи**

| **Сутність** | **Поля** |
| --- | --- |
| **Festival** | id: Long, name: String, city: String, startDate: LocalDate, endDate: LocalDate |
| **Artist** | id: Long, name: String, genre: String, country: String |
| **Stage** | id: Long, name: String, capacity: Integer |
| **Performance** | id: Long, performanceTime: LocalDateTime, durationMinutes: Integer |
| **Visitor** | id: Long, firstName: String, lastName: String, email: String, registrationDate: LocalDate |

---

# **Зв'язки між сутностями**

| **Зв'язок** | **Тип** |
| --- | --- |
| Festival — Stage | 1 : N |
| Festival — Artist | M : N |
| Artist — Performance | 1 : N |
| Stage — Performance | 1 : N |
| Visitor — Festival | M : N |

---

# **REST API**

## **Фестивалі**

| **Метод** | **Endpoint** | **Опис** |
| --- | --- | --- |
| POST | /festivals | Створити новий фестиваль |
| GET | /festivals | Отримати список фестивалів |
| GET | /festivals/{id} | Отримати фестиваль за ID |
| PUT | /festivals/{id} | Оновити фестиваль |
| DELETE | /festivals/{id} | Видалити фестиваль |
| GET | /festivals/{id}/artists | Отримати артистів фестивалю |
| GET | /festivals/{id}/stages | Отримати сцени фестивалю |

## **Артисти**

| **Метод** | **Endpoint** | **Опис** |
| --- | --- | --- |
| POST | /artists | Створити нового артиста |
| GET | /artists | Отримати список артистів |
| GET | /artists/{id} | Отримати артиста за ID |
| PUT | /artists/{id} | Оновити артиста |
| DELETE | /artists/{id} | Видалити артиста |
| GET | /artists/{id}/performances | Отримати виступи артиста |

## **Відвідувачі**

| **Метод** | **Endpoint** | **Опис** |
| --- | --- | --- |
| POST | /visitors | Зареєструвати нового відвідувача |
| GET | /visitors | Отримати список відвідувачів |
| GET | /visitors/{id} | Отримати відвідувача за ID |
| PUT | /visitors/{id} | Оновити дані відвідувача |
| DELETE | /visitors/{id} | Видалити відвідувача |
| GET | /visitors/{id}/festivals | Отримати фестивалі відвідувача |

## **Сцени**

| **Метод** | **Endpoint** | **Опис** |
| --- | --- | --- |
| POST | /stages | Створити нову сцену |
| GET | /stages | Отримати список сцен |
| GET | /stages/{id} | Отримати сцену за ID |
| PUT | /stages/{id} | Оновити сцену |
| DELETE | /stages/{id} | Видалити сцену |
| GET | /stages/{id}/performances | Отримати виступи сцени |
| GET | /stages/{id}/festival | Отримати фестиваль сцени |

## **Виступи**

| **Метод** | **Endpoint** | **Опис** |
| --- | --- | --- |
| POST | /performances | Створити новий виступ |
| GET | /performances | Отримати список виступів |
| GET | /performances/{id} | Отримати виступ за ID |
| PUT | /performances/{id} | Оновити виступ |
| DELETE | /performances/{id} | Видалити виступ |

## **Пошук**

| **Метод** | **Endpoint** | **Опис** |
| --- | --- | --- |
| GET | /search/festivals?query= | Пошук фестивалів |
| GET | /search/artists?query= | Пошук артистів |
| GET | /search/visitors?query= | Пошук відвідувачів |

## **Аналітика**

| **Метод** | **Endpoint** | **Опис** |
| --- | --- | --- |
| GET | /analytics/festivals/count | Загальна кількість фестивалів |
| GET | /analytics/artists/count | Кількість артистів |
| GET | /analytics/visitors/count | Кількість відвідувачів |
| GET | /analytics/performances/by-stage | Кількість виступів за сценами |

## **Метрики сервісу**

| **Метод** | **Endpoint** | **Опис** |
| --- | --- | --- |
| GET | /actuator/health | Перевірка стану сервісу |
| GET | /actuator/metrics | Список доступних метрик |
| GET | /actuator/prometheus | Метрики для систем моніторингу |

---

# **Проєктування архітектури**

## **Трирівнева архітектура**

Застосунок побудований за класичною трирівневою архітектурою з використанням патерну MVC:

- **Рівень доступу до даних (Repository)** – використовує Spring Data JPA для генерації SQL-запитів до PostgreSQL
- **Рівень бізнес-логіки (Service)** – сервісні класи для обробки даних та перевірки правил
- **Рівень представлення (Controller)** – перехоплює HTTP-запити та повертає JSON-відповіді

## **Нормалізація бази даних (3НФ)**

База приведена до 3НФ: атомарні атрибути, залежність від первинного ключа, відсутність транзитивних залежностей.

---

# **Технологічний стек**

- Java 17 | Spring Boot 3.2.5 | Spring Data JPA
- PostgreSQL | Lombok | SpringDoc OpenAPI (Swagger UI)
- Jakarta Validation

---

# **Встановлення та запуск**

**PostgreSQL:** localhost:5432, база `festival_db`

```bash
./mvnw spring-boot:run
```

**Сервіс:** http://localhost:8080  
**Swagger:** http://localhost:8080/swagger-ui.html

---

# **Структура проекту**

```
src/main/java/ua/opnu/labwork2/
├── Entity: Festival, Artist, Stage, Performance, Visitor
├── Repository: *Repository інтерфейси
├── Service: *Service класи
├── Controller: *Controller + Search + Analytics
├── dto/: Request/Response DTO класи
├── exception/: GlobalExceptionHandler + custom exceptions
├── config/: OpenApiConfig
└── Labwork2Application.java
```

---

# **Приклади запитів**

```bash
# Criar фестиваль
curl -X POST http://localhost:8080/festivals \
  -H "Content-Type: application/json" \
  -d '{"name": "Atlas", "city": "Kyiv", "startDate": "2026-07-10", "endDate": "2026-07-15"}'

# Пошук
curl -X GET "http://localhost:8080/search/festivals?query=Atlas"

# Аналітика
curl -X GET http://localhost:8080/analytics/festivals/count
```

---

# **Автор**

**Русакова Я. В.** (AI-244, ОПНУ, Одеса – 2026)

