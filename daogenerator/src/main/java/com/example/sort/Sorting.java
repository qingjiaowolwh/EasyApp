package com.example.sort;

import java.util.Comparator;

/**
 * Created by lwh on 2018/8/13.
 */
public class Sorting {

    //冒泡
    public static <T extends Dog> void bubbling(T[] sort, Comparator<? super T> c) {
        boolean swapped = true;
        for (int k = 0; k < sort.length; k++) {
            System.out.print(sort[k].getAge());
        }
        System.out.println("");
        for (int i = 0; i < sort.length - 1; i++) {//外层循环控制排序趟数
            swapped = false;
            for (int j = 0; j < sort.length - i - 1; j++) {//内层循环控制每一趟排序多少次
                if (c.compare(sort[j], sort[j + 1]) > 0) {
                    T temp = sort[j];
                    sort[j] = sort[j + 1];
                    sort[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static int binarySearch(int[] list, int key) {
        int low = 0;
        int high = list.length - 1;

        while (high >= low) {
            int mid = (low + high) / 2;
            if (key < list[mid]) {
                high = mid - 1;
            } else if (key == list[mid]) {
                return mid;
            } else {
                low = mid + 1;
            }
        }

        return -low - 1; // Now high < low
    }
}
