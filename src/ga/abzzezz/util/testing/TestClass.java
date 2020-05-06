/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.testing;


import ga.abzzezz.util.data.data.DataFormat;
import ga.abzzezz.util.data.data.DataObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TestClass {

    /*
    Class to test functionality
     */

    static int integerout;
    static float floaout;
    static double doubleout;
    static long longout;
    static String testvalue;
    static short aShort;

    public static void main(String[] args) {
        /*
        Create Data Object
         */
        DataObject dataObject = new DataObject();
        /*
        Create list
         */
        List<String> dataIn = new LinkedList<>();
        dataIn.add("data1");
        dataIn.add("data2");
        dataIn.add("data3");

        /*
        Add to data Object
         */
        dataObject.addList(dataIn, "ArrayData");
        dataObject.addArray(new String[] {"tes", "x", "y"}, "Array");

        /*
        Add more data with key and valuec
         */
        dataObject.addObject("TestKey", "Value");
        dataObject.addObject("IntegerKey", Integer.MAX_VALUE);
        dataObject.addObject("LongKey", Long.MAX_VALUE);
        dataObject.addObject("FloatKey", Float.MAX_VALUE);
        dataObject.addObject("DoubleKey", Double.MAX_VALUE);
        dataObject.addObject("ShortKey", Short.MAX_VALUE);

        /*
        File where Data should be stored
         */
        File f = new File(System.getProperty("user.home"), "text.txt");
        /*
        Write data to file
         */

        DataFormat.formatData(dataObject, f, false);
        /*
        Give print data as an array
         */
        testvalue = (String) DataFormat.decode(f, "TestKey");
        integerout = (int) DataFormat.decode(f, "IntegerKey");
        floaout = (float) DataFormat.decode(f, "FloatKey");
        doubleout = (double) DataFormat.decode(f, "DoubleKey");
        longout = (long) DataFormat.decode(f, "LongKey");

        System.out.println(Arrays.toString(DataFormat.decodeToArray(f, "ArrayData")));
        System.out.println(testvalue);
        System.out.println(integerout);
        System.out.println(floaout);
        System.out.println(doubleout);
        System.out.println(longout);

    }
}
