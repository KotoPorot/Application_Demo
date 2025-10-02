# Документация API для фронтенд-разработчика

Ветка: `backAndFront`  
Контроллер: `BoardController`

---

## Аутентификация

Все эндпоинты требуют передачи токена авторизации (JWT или аналогичный) через стандартный механизм Spring Security.  
Передавайте токен в заголовке `Authorization: Bearer <token>`.

---

## Эндпоинты

### 1. Создать доску

**POST** `/createBoard`

**RequestBody:**
```json
{
  "boardName": "Название доски" // обязательно
}
```
**Response:**
- 200 OK:  
  ```json
  {
    // объект UserRolesDTO с ролями пользователя на доске
  }
  ```
- 406 NOT_ACCEPTABLE: доска с таким именем уже существует

---

### 2. Добавить участника на доску

**POST** `/addBoardMember`

**RequestBody:**
```json
{
  "boardId": 123,           // обязательно, ID доски
  "userName": "username",   // обязательно, логин пользователя
  "boardRole": "MEMBER"     // опционально, роль (MEMBER по умолчанию; варианты: MEMBER, MANAGER)
}
```
**Response:**
- 200 OK: пустой ответ
- 418 I_AM_A_TEAPOT: нет прав на изменение доски
- 406 NOT_ACCEPTABLE: пользователь уже участник или не найден
- 403 FORBIDDEN: ошибка безопасности

---

### 3. Удалить участника с доски

**POST** `/deleteBoardMember`

**RequestBody:**
```json
{
  "boardId": 123,   // обязательно, ID доски
  "userId": 456     // обязательно, ID пользователя
}
```
**Response:**
- 200 OK: пустой ответ
- 418 I_AM_A_TEAPOT: нет прав на изменение доски
- 406 NOT_ACCEPTABLE: пользователь или доска не найдены
- 400 BAD_REQUEST: ошибка сервера

---

### 4. Создать департамент

**POST** `/createDepartment`

**RequestBody:**
```json
{
  "boardId": 123,      // обязательно, ID доски
  "name": "Dev Team"   // обязательно, название департамента
}
```
**Response:**
- 200 OK:
  ```json
  {
    // объект Department
  }
  ```
- 406 NOT_ACCEPTABLE: доска не найдена или нет прав
- 409 CONFLICT: департамент не создан (конфликт)

---

### 5. Добавить участника в департамент

**POST** `/addDepartmentMember`

**RequestBody:**
```json
{
  "boardId": 123,         // обязательно, ID доски
  "departmentId": 456,    // обязательно, ID департамента
  "userId": 789           // обязательно, ID пользователя (должен быть участник доски)
}
```
**Response:**
- 200 OK: пустой ответ
- 404 NOT_FOUND: один из объектов не найден
- 403 FORBIDDEN: нет прав или пользователь не участник доски
- 409 CONFLICT: пользователь уже в департаменте

---

### 6. Удалить участника из департамента

**POST** `/deleteDepMember`

**RequestBody:**
```json
{
  "departmentId": 456,   // обязательно, ID департамента
  "userId": 789          // обязательно, ID пользователя
}
```
**Response:**
- 200 OK: пустой ответ
- 404 NOT_FOUND: один из объектов не найден
- 403 FORBIDDEN: нет прав или пользователь не участник департамента

---

### 7. Создать задачу

**POST** `/createTask`

**RequestBody:**
```json
{
  "title": "Задача",         // обязательно, название задачи
  "description": "Описание", // опционально
  "executorId": 789,         // опционально, ID исполнителя
  "departmentId": 456,       // опционально, ID департамента
  "boardId": 123             // обязательно, ID доски
}
```
**Response:**
- 200 OK:
  ```json
  {
    // объект Task
  }
  ```
- 404 NOT_FOUND: доска не найдена или не задан title
- 403 FORBIDDEN: пользователь не участник доски
- 400 BAD_REQUEST: некорректные данные

---

### 8. Получить информацию о пользователе

**GET** `/getUserInfo`

**Response:**
```json
{
  "userId": 123,
  "username": "user",
  "userBoards": [
    {
      "boardId": 1,
      "boardName": "Main",
      "boardRole": "OWNER"
    }
  ],
  "userDepartments": [
    {
      "depId": 10,
      "depName": "Dev",
      "boardId": 1,
      "boardName": "Main"
    }
  ],
  "userTasksId": [100, 101, 102],
  "respDep": {/* объект департамента, если есть */},
  "defaultBoardId": 1
}
```

---

### 9. Получить информацию о доске

**GET** `/getBoardInfo`

**Response:**
```json
{
  "boardId": 1,
  "boardName": "Main",
  "members": [
    {
      // BoardMemberDTO: информация о пользователе и его роли на доске
    }
  ]
}
```

---

## Примечания

- Все поля, помеченные как "обязательно", должны быть заполнены, иначе сервер вернет ошибку.
- Для всех POST-запросов требуется авторизация.
- Все ID — числовые значения.
- Формат ошибок соответствует стандартным кодам HTTP.

---

## DTO-структуры

- Состав возвращаемых объектов можно уточнить по внутренним классам DTO в репозитории:
  - [UserDTO.java](https://github.com/KotoPorot/Application_Demo/blob/backAndFront/src/main/java/com/KotoPorot/Application_Demo/ResponseDTO/UserDTO.java)
  - [BoardDTO.java](https://github.com/KotoPorot/Application_Demo/blob/backAndFront/src/main/java/com/KotoPorot/Application_Demo/ResponseDTO/BoardDTO.java)
  - [Department.java](https://github.com/KotoPorot/Application_Demo/blob/backAndFront/src/main/java/com/KotoPorot/Application_Demo/Entities/Department.java)
  - [Task.java](https://github.com/KotoPorot/Application_Demo/blob/backAndFront/src/main/java/com/KotoPorot/Application_Demo/Entities/Task.java)

---

Если требуется документация по дополнительным контроллерам или вопросам, обратись к разработчику backend или открой соответствующий issue!