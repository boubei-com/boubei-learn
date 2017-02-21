package jinhe.lt.junit;

import junit.framework.TestCase;

public class Test_HelloWorld extends TestCase {

    public Test_HelloWorld(String name) {
        super(name);
    }

    public void testSay() {

        HelloWorld hi = new HelloWorld();

        assertEquals("Hello World!", hi.say());

    }

    public static void main(String[] args) {

        junit.textui.TestRunner.run(

        Test_HelloWorld.class);

    }

}

