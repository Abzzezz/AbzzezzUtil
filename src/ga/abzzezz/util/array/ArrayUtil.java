/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.array;

import ga.abzzezz.util.animations.MathUtil;
import ga.abzzezz.util.stringing.StringUtil;

import java.util.ArrayList;

public class ArrayUtil {

    public static ArrayList<String> sortWithNumberInName(ArrayList<String> in) {
        ArrayList<String> sorted = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Integer> numberCopy = new ArrayList<>();
        ArrayList<Integer> numbers2 = new ArrayList<>();

        for (String s : in) {
            String numString = s;
            int num = Integer.valueOf(StringUtil.extractNumber(numString));
            numbers.add(num);
            numberCopy.add(num);
        }

        while (numbers.iterator().hasNext()) {
            int lowest = MathUtil.getLowest(numbers);
            numbers2.add(lowest);
            numbers.remove(numbers.indexOf(lowest));
        }

        for (Integer integer : numbers2) {
            sorted.add(numbers2.indexOf(integer), in.get(numberCopy.indexOf(integer)));
        }

        numbers.clear();
        numberCopy.clear();
        numbers2.clear();

        return sorted;
    }

    /*
    Used when number is not directly accessible
    trows index out of bounds when arrayIndex is out of bounds!
     */

    public static ArrayList<String> sortWithNumberInName(ArrayList<String> in, String split, int arrayIndex) {
        ArrayList<String> sorted = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Integer> numberCopy = new ArrayList<>();
        ArrayList<Integer> numbers2 = new ArrayList<>();

        for (String s : in) {
            String numString = s.split(split)[arrayIndex];
            int num = Integer.valueOf(StringUtil.extractNumber(numString));
            numbers.add(num);
            numberCopy.add(num);
        }

        while (numbers.iterator().hasNext()) {
            int lowest = MathUtil.getLowest(numbers);
            numbers2.add(lowest);
            numbers.remove(numbers.indexOf(lowest));
        }

        for (Integer integer : numbers2) {
            sorted.add(numbers2.indexOf(integer), in.get(numberCopy.indexOf(integer)));
        }

        numbers.clear();
        numberCopy.clear();
        numbers2.clear();

        return sorted;
    }

    public static void removeElement(Object[] arr, int removedIdx) {
        System.arraycopy(arr, removedIdx + 1, arr, removedIdx, arr.length - 1 - removedIdx);
    }

}
