/**************************************
 * Created by dtalankin on 31.01.2016.
 */

package ru.dtalankin.trainings;


import org.testng.annotations.Test;

public class FirstTest {
    @Test(groups = "positive")
    public void test1 ()
    {
        System.out.println("FirstTest.test1 done");
    }

    @Test(groups = "positive")
    public void test2 ()
    {
        System.out.println("FirstTest.test2 done");
    }

}
