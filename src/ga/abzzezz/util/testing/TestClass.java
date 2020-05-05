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
import java.util.List;

public class TestClass {

    /*
    Class to test functionality
     */
    public static void main(String[] args) {
        /*
        Create Data Object
         */
        DataObject dataObject = new DataObject();
        /*
        Create list
         */
        List<String> dataIn = new ArrayList<>();
        dataIn.add("data1");
        dataIn.add("data2");
        dataIn.add("data3");

        /*
        Add to data Object
         */
        dataObject.addList(dataIn, "ArrayData");
        /*
        Add more data with key and value
         */
        dataObject.addObject("TestKey", "RandomValue");
        /*
        File where Data should be stored
         */
        File f = new File(System.getProperty("user.home"), "text.txt");
        /*
        Write data to file
         */

        /*
        Give print data as an array
         */
        System.out.println(Arrays.toString(DataFormat.decodeToArray(f, "ArrayData")));
        System.out.println(DataFormat.decode(f, "TestKey"));

    }
}
