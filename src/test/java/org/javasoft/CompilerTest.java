package org.javasoft;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;

/**
 * A test class to test dynamic compilation API.
 */
public class CompilerTest {

    private String TEMP_DIR = System.getProperty("java.io.tmpdir");
    private String fileName =  TEMP_DIR + "/SampleJavaClass.java";

    private StringBuilder javaCode = new StringBuilder("package sample;\n")
                                                    .append("\n")
                                                    .append("public class SampleJavaClass {\n")
                                                    .append("\n")
                                                    .append("}");

    @Test
	public void shouldReturnSuccessForStringCompilation() throws IOException {
        givenJavaFile();
        whenCompilationOccurs();
//        thenClassFileShouldExistAndCanBeDeleted();
	}

    private void givenJavaFile() throws IOException  {
        writeStringToFile(createTempFile(), javaCode.toString());
    }

    private File createTempFile() throws IOException {
        File temp = new File(fileName); 
        temp.deleteOnExit();
        System.out.println("Temp file : " + temp.getAbsolutePath());
        return temp;
    }

    public static void writeStringToFile(File file, String data) throws IOException {
        OutputStream out = new FileOutputStream(file);
        try {
            out.write(data.getBytes());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    
    private void whenCompilationOccurs() {
        String[] args = new String[]{fileName, "-d", System.getProperty("java.io.tmpdir")};
        new Compiler().compile(new CompilerParams(args));
    }
}
