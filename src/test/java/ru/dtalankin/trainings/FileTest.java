/**************************************
 * Created by dtalankin on 31.01.2016.
 */

package ru.dtalankin.trainings;


import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
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

    @BeforeClass(groups = {"positive","negative"}, alwaysRun = true)
    public void createTmpDirTest(){
        try {
            tmpDir = Files.createTempDirectory("tmpDir");
            subDir = new File(tmpDir.toFile(), "subdir");
            subDir.mkdir();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass(groups = {"positive","negative"}, alwaysRun = true)
    public void deleteTmpDirTest(){
        FileUtils.deleteQuietly(tmpDir.toFile());
    }

    @Test(groups = "positive", alwaysRun = true)
    public void filePositiveTest1() {
        File file = new File(tmpDir.toFile(), "file1.txt");
        try {
            Assert.assertTrue(file.createNewFile(), "File1 is not created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = "positive", alwaysRun = true)
    public void filePositiveTest2() {
        File file = new File(subDir, "file2.txt");
        try {
            Assert.assertTrue(file.createNewFile(), "File2 is not created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = "negative", alwaysRun = true)
    public void fileNegativeTest3() {
        File file = new File(tmpDir.toFile(), "file3.txt");

        try {
            file.createNewFile();
            AssertJUnit.assertTrue("File3 is not a derictory", file.isDirectory());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = "negative", alwaysRun = true)
    public void fileNegativeTest4() {
        File file = new File(subDir, "file4");
        AssertJUnit.assertTrue("File4 does not exist", file.exists());
    }
}
