/**************************************
 * Created by dtalankin on 09.02.2016.
 */

package ru.dtalankin.trainings;

import org.testng.asserts.Assertion;

import java.io.File;
import java.io.IOException;


public class AssertionForExceptions extends Assertion {

    public void assertExpectedException(final File file) {
        doAssert(new ExceptionsAssert("File " + file + " is not created") {
            @Override
            public void doAssert() {
                try {
                    assertTrue(file.createNewFile(), getMessage());
                } catch (IOException e) {
                    fail(getMessage());
                }
            }
        });
    }

}
