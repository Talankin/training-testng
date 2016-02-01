/**************************************
 * Created by dtalankin on 31.01.2016.
 */

package ru.dtalankin.trainings;


import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Test
public class FileTest {
    private Path tmpDir = null;
    private File subDir = null;

    @BeforeClass
    public void createTmpDirTest(){
        try {
            System.out.println("Set up fixture");
            tmpDir = Files.createTempDirectory("tmpDir");

            File fileForNegativeTest1 = new File(tmpDir.toFile(), "file3.txt");
            fileForNegativeTest1.createNewFile();

            subDir = new File(tmpDir.toFile(), "subdir");
            subDir.mkdir();

            File fileForNegativeTest2 = new File(subDir, "file4.txt");
            fileForNegativeTest2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void deleteTmpDirTest(){
        System.out.println("Tear down fixture");
        FileUtils.deleteQuietly(tmpDir.toFile());
    }

    public void filePositiveTest1() {
        File file = new File(tmpDir.toFile(), "file1.txt");
        try {
            System.out.println("Positive test. file1 is created : " + file.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filePositiveTest2() {
        File file = new File(subDir, "file2.txt");
        try {
            System.out.println("Positive test. file2 is created : " + file.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileNegativeTest3() {
        File file = new File(tmpDir.toFile(), "file3.txt");
        try {
            System.out.println("Negative test. file3 is created : " + file.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileNegativeTest4() {
        File file = new File(subDir, "file4.txt");
        try {
            System.out.println("Negative test. file4 is created : " + file.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
