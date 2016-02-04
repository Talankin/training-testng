/**************************************
 * Created by dtalankin on 31.01.2016.
 */

package ru.dtalankin.trainings;


import org.apache.commons.io.FileUtils;
import org.testng.Assert;
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

    @BeforeClass(groups = {"positive","negative"})
    public void createTmpDirTest(){
        try {
            System.out.println("Set up fixture");
            tmpDir = Files.createTempDirectory("tmpDir");

            subDir = new File(tmpDir.toFile(), "subdir");
            subDir.mkdir();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass(groups = {"positive","negative"})
    public void deleteTmpDirTest(){
        System.out.println("Tear down fixture");
        FileUtils.deleteQuietly(tmpDir.toFile());
    }

    @Test(groups = "positive")
    public void filePositiveTest1() {
        File file = new File(tmpDir.toFile(), "file1.txt");
        try {
            Assert.assertTrue(file.createNewFile(), "File1 is not created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = "positive")
    public void filePositiveTest2() {
        File file = new File(subDir, "file2.txt");
        try {
            Assert.assertTrue(file.createNewFile(), "File2 is not created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = "negative")
    public void fileNegativeTest3() {
        File fileForNegativeTest = new File(tmpDir.toFile(), "file3.txt");
        File file = new File(tmpDir.toFile(), "file3.txt");

        try {
            fileForNegativeTest.createNewFile();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = "negative")
    public void fileNegativeTest4() {
        File fileForNegativeTest = new File(subDir, "file4.txt");
        File file = new File(subDir, "file4.txt");

        try {
            fileForNegativeTest.createNewFile();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
