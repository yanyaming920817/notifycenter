package com.urun.messageserver;

import java.sql.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    public void testDate()
    {
        Date date =new Date(System.currentTimeMillis());
        System.out.println(date.toLocaleString());
        System.out.println(date.toGMTString());
        System.out.println(date.toLocaleString());
        System.out.println(date.toString());
    }
}
