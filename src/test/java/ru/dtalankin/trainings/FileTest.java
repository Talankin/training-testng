/**************************************
 * Created by dtalankin on 31.01.2016.
 */

package ru.dtalankin.trainings;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Test
public class FileTest {
    private Path tmpDir = null;
    private File subDir = null;
    private String notExistingDir = "qwerty";

    @BeforeClass(groups = {"positive","negative"}, alwaysRun = true)
    public void createTmpDirTest() throws IOException {
        tmpDir = Files.createTempDirectory("tmpDir");
        subDir = new File(tmpDir.toFile(), "subdir");
        subDir.mkdir();
    }

    @AfterClass(groups = {"positive","negative"}, alwaysRun = true)
    public void deleteTmpDirTest(){
        FileUtils.deleteQuietly(tmpDir.toFile());
    }

    @Test(groups = "positive", alwaysRun = true)
    @Parameters({"subPath","fileName"})
    public void filePositiveTest1(String subPath, String fileName) throws IOException {
        File fullPath = new File(tmpDir.toFile(), subPath);
        fullPath.mkdir();
        File file = new File(fullPath, fileName);
        file.createNewFile();
        Assert.assertTrue(file.exists(), "File1 is not created");
    }

    @Test(groups = "positive", alwaysRun = true,
            dataProviderClass = DataProviders.class, dataProvider = "loadFileNameFromFile")
    public void filePositiveTest2(String fileName) throws IOException {
        File file = new File(subDir, fileName);
        file.createNewFile();
        Assert.assertTrue(file.exists(), "File2 is not created");
    }

    @Test(groups = "positive", alwaysRun = true, dataProvider = "fileNameGenerator")
    public void filePositiveTest3(String fileName) throws IOException {
        File file = new File(subDir, fileName);
        file.createNewFile();
        AssertJUnit.assertTrue("File4 does not exist", file.exists());
    }

    @Test(groups = "negative", alwaysRun = true)
    public void fileNegativeTest4() throws IOException {
        File file = new File(tmpDir.toFile(), "file3.txt");
        file.createNewFile();
        AssertJUnit.assertFalse("File3 created", file.createNewFile());
    }

    @Test(groups = "negative", alwaysRun = true)
    public void fileNegativeTest5() throws IOException {
        File notExistingPath = new File(subDir, notExistingDir);
        File file = new File(notExistingPath, "file4.txt");
        AssertionForExceptions verify = new AssertionForExceptions();
        verify.assertExpectedException(file);
    }

    @DataProvider
    public Iterator<Object[]> fileNameGenerator() {
        List<Object[]> filesList = new ArrayList<Object[]>();
        for(int i=0; i<5; i++) {
            filesList.add(new Object[]{
                    generateRandomFileName()
            });
        }
        return filesList.iterator();
    }

    private Object generateRandomFileName() {
        return "file" + (int)(Math.random()*100000) + ".txt";
    }

}
