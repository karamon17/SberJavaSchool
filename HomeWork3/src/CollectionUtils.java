import java.util.*;

public class CollectionUtils {
    public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, Object o) {
        return source.indexOf(o);
    }

    public static <T> List<T> limit(List<? extends T> source, int size) {
        return new ArrayList<>(source.subList(0, Math.min(size, source.size())));
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return new HashSet<>(c1).containsAll(c2);
    }

    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (T element : c2) {
            if (c1.contains(element)) {
                return true;
            }
        }
        return false;
    }

    public static <T extends Comparable<? super T>> List<T> range(List<T> list, T min, T max) {
        List<T> result = newArrayList();
        if (!containsAll(list, Arrays.asList(min, max))) {
            return newArrayList();
        }
        for (T item : list) {
            if (item.compareTo(min) >= 0 && item.compareTo(max) <= 0) {
                result.add(item);
            }
        }
        result.sort(Comparator.naturalOrder());
        return result;
    }

    public static <T> List<T> range(List<T> list, T min, T max, Comparator<? super T> comparator) {
        List<T> result = newArrayList();
        for (T item : list) {
            if (comparator.compare(item, min) >=0 && comparator.compare(item, max) <= 0) {
                result.add(item);
            }
        }
        result.sort(comparator);
        return result;
    }
}
