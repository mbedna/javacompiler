package org.javasoft;

import org.junit.Test;

public class CompilerClientTest {

private String[] args = new String[] {"-source", "1.6", "-d", System.getProperty("java.io.tmpdir"), "-cp", "C:\\m2\\repository\\junit\\junit\\4.10\\junit-4.10.jar;C:\\m2\\repository\\org\\hamcrest\\hamcrest-core\\1.1\\hamcrest-core-1.1.jar;target\\classes;target\\test-classes", "C:/workspacescala/javacompiler/src/test/java/org/javasoft/CompilerClientTest.java"}; 

    @Test
    public void test() {
        CompilerServer.main(null);
        CompilerClient client = new CompilerClient();
        client.main(args);
        client.sendQuit();
    }
}
