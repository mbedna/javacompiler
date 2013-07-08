package org.javasoft;

import java.util.List;
import java.util.ArrayList;

public class CompilerParams {
    
    private List<String> files = new ArrayList<String>();  
    private List<String> options = new ArrayList<String>();

    public CompilerParams(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String param = args[i];
            switch (param.charAt(0)) {
                case '-':
                    String param2 = args[++i];
                    System.out.println("Found dash with command " + param + " and value " + param2);   
                    options.add(param);
                    options.add(param2);
                break;         
                default:            
                    System.out.println("Add to files: " + param);
                    files.add(param);
                break;         
            }     
        } 
    }
    
    public List<String> getFiles() {
        return files;
    }   

    public List<String> getOptions() {
        return options;
    }   
}
