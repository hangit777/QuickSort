package ru.vsu.cs.moshkin;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] array = new int[] {10, 5, 1, 8, 3, 6, 2, 4, 9, 7};
        System.out.println("Тест 1:\n\n" + arrayToString(array) + "\n");
        print(array);

        int[] array1 = new int[] {99, 31, 85, 24, 32, 11, 56, 20, 91, 44, 77, 13, 36};
        System.out.println("Тест 2:\n\n" + arrayToString(array1) + "\n");
        print(array1);

        int[] array2 = new int[] {29, 305, 4, 723, 15, 218, 1036, 18, 81, 5471};
        System.out.println("Тест 3:\n\n" + arrayToString(array2) + "\n");
        print(array2);
    }

    static void print(int[] array) {
        for (int i = 1; i < 5; i++) {
            int[] arrayClone = array.clone();
            if (i == 1) {
                System.out.println("1)Первый элемент:\n");
            }
            if (i == 2) {
                System.out.println("2)Последний элемент:\n");
            }
            if (i == 3) {
                System.out.println("3)Медиана из 3-х:\n");
            }
            if (i == 4) {
                System.out.println("4)Рандомный элемент:\n");
            }
            quickSort(arrayClone, 0, array.length - 1, i);
        }
    }

    static void quickSort(int[] array, int low, int high, int i) {
        if (low < high) {
            int divideIndex = partition(array, low, high, i);
            printSortStep(array, low, high, divideIndex);
            quickSort(array, low, divideIndex - 1, i);
            quickSort(array, divideIndex + 1, high, i);
        }
    }

    static int partition(int[] array, int low, int high, int i) {
        int pivotIndex = conditionPivot(array, low, high, i);
        int pivot = array[pivotIndex];
        int leftIndex = low;
        int temp = array[pivotIndex];
        array[pivotIndex] = array[high];
        array[high] = temp;
        for (int j = low; j <= high; j++) {
            if (array[j] < pivot) {
                int x = array[leftIndex];
                array[leftIndex] = array[j];
                array[j] = x;
                leftIndex++;
            }
        }
        int k = array[high];
        array[high] = array[leftIndex];
        array[leftIndex] = k;
        return leftIndex;
    }

    static int conditionPivot(int[] array, int low, int high, int i) {
        if (i == 1) {
            return low;
        } else if (i == 2) {
            return high;
        } else if (i == 3) {
            int x = array[low];
            int y = array[(low + high) / 2];
            int z = array[high];
            if ((x >= y && y >= z) || (x <= y && y <= z))
                return (low + high) / 2;
            else if ((x >= z && z >= y) || (x <= z && z <= y))
                return high;
            else return low;
        } else if (i == 4) {
            return (int)(Math.random() * (high - low + 1)) + low;
        }
        return low;
    }

    static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    static void printSortStep(int[] arr, int from, int to, int partitionIndex) {
        System.out.println(arrayToString(arr));
        System.out.print("Индекс разбиения: " + partitionIndex);
        System.out.print(", левый подмассив: " + arrayToString(Arrays.copyOfRange(arr, from, partitionIndex)));
        System.out.println(", правый подмассив: " + arrayToString(Arrays.copyOfRange(arr, partitionIndex, to + 1)) + "\n");
    }
}