/*
 *  Copyright 2019.
 *  Alejandro Maruri.
 *  ale_maruri14@hotmail.com
 *  eamaruri@estud.usfq.edu.ec
 */

//Source: http://alvinalexander.com/java/java-exec-processbuilder-process-2
package simple_shell;


/**
 *
 * @author EAMT
 */
public class Simple_Shell {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Shell my_shell = new Shell();
        my_shell.showCommands();

	    boolean in_prompt = true;
	    while(in_prompt) {
            in_prompt = my_shell.showPrompt();
        }

        System.out.println();
        System.out.println();  
    }
}
