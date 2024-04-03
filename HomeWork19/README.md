# Домашнее задание №19 Фреймворк Spring  - ORM, Spring Hibernate

## Задание
Домашнее задание из предыдущей лекции перевести на использование EntityManager для DAO слоя и реализовать пару сервисов с использованием CrudRepository

## Решение
Я переписал предыдущее домашнее задание, используя EntityManager для управления доступом к данным в слое доступа к данным (DAO).
Также я создал два сервиса, используя CrudRepository для выполнения операций поиска, добавления и удаления рецептов.

Первый сервис называется IngredientService и предоставляет методы для выполнения операций над ингредиентами. Эта сущность связана с рецептом через связь многие к одному, поэтому я использовал аннотацию `@ManyToOne` для создания связи между ними.

Второй сервис называется CategoryService и управляет категориями рецептов, позволяя создавать, читать, обновлять и удалять категории, а также связывать их с рецептами, используя связь один ко многим `@OneToMany`.

Я также поручил Hibernate создать(обновить) таблицы в базе данных при запуске приложения.

В результате мои классы стали более гибкими и управляемыми, а использование EntityManager позволило мне получить больше контроля над операциями базы данных в моем приложении.