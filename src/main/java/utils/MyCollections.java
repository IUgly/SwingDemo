package utils;

import java.util.List;

/**
 * @author kuangjunlin
 */
public class MyCollections<E> {
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        list.sort(null);
    }

    public static void main(String[] args) {

    }
}
