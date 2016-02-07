/**************************************
 * Created by dtalankin on 07.02.2016.
 */

package ru.dtalankin.trainings;


import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    private final static String DATA_FILE = "/files.data";

    @DataProvider
    public  static Iterator<Object[]> loadFileNameFromFile() throws IOException {
        BufferedReader dataFile = new BufferedReader(new InputStreamReader(
                        DataProviders.class.getResourceAsStream(DATA_FILE)));

        List<Object[]> filesList = new ArrayList<Object[]>();
        String line = dataFile.readLine();
        while (line!=null) {
            filesList.add(line.split(";"));
            line = dataFile.readLine();
        }
        dataFile.close();

        return filesList.iterator();
    }
}
