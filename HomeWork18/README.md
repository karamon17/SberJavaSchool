# Домашнее задание №18 Фреймворк Spring - JDBC, Transactions

## Задание
Разработать консольное приложение для хранения рецептов.

Функциональность:
* Поиск рецепта по имени или части имени блюда
* Добавление рецепта - рецепт состоит из множества ингредиентов и их количественного состава
* Удаление блюда

## Решение
Я разбил приложение на несколько слоев: уровень доступа к данным (DAO), сервисный слой (Service), уровень представления (UI) и основной класс Main для запуска приложения.

Я использовал аннотации Spring для конфигурации бинов, а внедрение зависимостей осуществлял через конструкторы. На уровне доступа к данным я создал интерфейс RecipeDAO и его реализацию RecipeDAOImpl, которая взаимодействует с базой данных PostgreSQL через JDBC Template для выполнения операций поиска, добавления и удаления рецептов.

Сервисный слой представлен классом RecipeService, который инкапсулирует логику бизнес-операций над рецептами. Также я создал класс ConsoleUI для взаимодействия с пользователем через консоль, реализовав методы для поиска, добавления и удаления рецептов.

Основной класс Main инициализирует контекст Spring, запускает приложение и предоставляет пользователю интерфейс для выполнения операций над рецептами. Я также добавил создание таблицы в базе данных при запуске приложения.