package org.javasoft;

import java.util.Locale;
import java.util.logging.Logger;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * Dynamic compilation API class.
 */
public class Compiler {

	private final Logger logger = Logger.getLogger(Compiler.class.getName());

    private JavaCompiler compiler;


    public Compiler() {
		this.compiler = ToolProvider.getSystemJavaCompiler();
		/**
		 * The same file manager can be reopened for another compiler task. 
		 * Thus we reduce the overhead of scanning through file system and jar files each time 
		 */
    }

	public static void main(String args[]) {
		Compiler compiler = new Compiler();
	    compiler.compile(new CompilerParams(args));
    }
	
	public String compile(CompilerParams params) {
        long begin = System.currentTimeMillis();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, Locale.getDefault(), null);
        Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjectsFromStrings(params.getFiles());
		
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		
		CompilationTask compilerTask = compiler.getTask(null, fileManager, diagnostics, params.getOptions(), null, fileObjects);
		boolean status = compilerTask.call();
        String output = "";
		if (!status) {
			for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
				//System.out.format("Error on line %d in %s", diagnostic.getLineNumber(), diagnostic);
                output += diagnostic.toString() + "\n";
			}
		} else {
            output = "Compilation successful";
        } 
        System.out.println(output);
//		try {
//			fileManager.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        long end = System.currentTimeMillis();
        System.out.println("Compilation time: " + (end - begin) + " millis.");
        return output;
	}
}
