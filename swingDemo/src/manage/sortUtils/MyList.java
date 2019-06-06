package manage.sortUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.ListIterator;

/**
 * @author kuangjunlin
 */
public interface MyList<E> extends MyCollection<E> {

    Object[] toArray();

    ListIterator<E> listIterator();

    default void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<E> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((E) e);
        }
    }
}
