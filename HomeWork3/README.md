# Домашнее задание №3 Обобщенные классы – Generics

## Задача 1
Параметризовать CountMap и реализовать его.
    
    public interface CountMap {
      // добавляет элемент в этот контейнер.
      void add(Object o);
  
      //Возвращает количество добавлений данного элемента
      int getCount(Object o);
  
      //Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
      int remove(Object o);
  
      //количество разных элементов
      int size();
  
      //Добавить все элементы из source в текущий контейнер, 
      // при совпадении ключей,     суммировать значения
      void addAll(CountMap source);
  
      //Вернуть java.util.Map. ключ - добавленный элемент, 
      // значение - количество его добавлений
      Map toMap();
  
      //Тот же самый контракт как и toMap(), только всю информацию записать в destination
      void toMap(Map destination);
    }

## Задача 2
Параметризовать методы, используя правило PECS, и реализовать их.

    public class CollectionUtils {
      public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
          destination.addAll(source);
      }
  
      public static List newArrayList() { }
  
      public static int indexOf(List source, Object o) { }
  
      public static List limit(List source, int size) { }
  
      public static void add(List source, Object o) { }
  
      public static void removeAll(List removeFrom, List c2) { }
  
      public static boolean containsAll(List c1, List c2) { }
  
      public static boolean containsAny(List c1, List c2) { }
  
      public static List range(List list, Object min, Object max) { }
  
      public static List range(List list, Object min, Object max, Comparator comparator) { }
    }

## Решения и пояснения

### Задача 1
Параметризовал интерфейс CountMap. Далее создал класс CountMapImpl, который имплементирует наш интерфейс CountMap.
Для решения задачи логично выбрать коллекцию Map<T, Integer>. Ключом является объект, а значением кол-во добавлений этого объекта в коллекцию.
Реализовал все методи согласно заданному описанию.

### Задача 2
