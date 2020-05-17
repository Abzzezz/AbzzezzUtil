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
import java.util.LinkedList;
import java.util.List;

public class TestClass {


    static int integerout;
    static float floaout;
    static double doubleout;
    static long longout;
    static String testvalue;
    static short aShort;


    public static void main(String[] args) {
        DataObject dataObject = new DataObject();


        List<String> dataIn = new LinkedList<>();
        dataIn.add("data1");
        dataIn.add("data2");
        dataIn.add("data3");


        dataObject.addArray(new String[]{"[tes", "x", "y]"}, "Array");

        dataObject.addObject("TestKey", "Value");
        dataObject.addObject("IntegerKey", Integer.MAX_VALUE);
        dataObject.addObject("LongKey", Long.MAX_VALUE);
        dataObject.addObject("FloatKey", Float.MAX_VALUE);
        dataObject.addObject("DoubleKey", Double.MAX_VALUE);
        dataObject.addObject("ShortKey", Short.MAX_VALUE);
        File f = new File(System.getProperty("user.home"), "text.txt");

        DataFormat dataFormat = new DataFormat(f);


        testvalue = (String) dataFormat.decode("TestKey");
        integerout = (int) dataFormat.decode("IntegerKey");
        floaout = (float) dataFormat.decode("FloatKey");
        doubleout = (double) dataFormat.decode("DoubleKey");

        System.out.println(testvalue);
        System.out.println(integerout);
        System.out.println(floaout);
        System.out.println(doubleout);

    }
}
