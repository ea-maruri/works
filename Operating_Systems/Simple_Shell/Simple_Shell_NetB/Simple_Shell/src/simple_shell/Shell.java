/*
 *  Copyright 2019.
 *  Alejandro Maruri.
 *  ale_maruri14@hotmail.com
 *  eamaruri@estud.usfq.edu.ec
 */

package simple_shell;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EAMT
 */
public class Shell {
    public static final char PROMPT = '>'; // The prompt for user
    public static final int SEPARATOR = 38; // Separator of commands (&)
    public static final int WIN_SEPARATOR = 92; 

    private Deque<String> shellHistory; // To store history
    private ArrayList<String> listOfCommands; // List of allowed commands
    
    private String currentDirectory;
    
    
    /**
     * Constructor
     * Initializes the list of commands, the deque for histroy and current directory
     * */
    public Shell(){
        this.listOfCommands = new ArrayList<>();
        addInitialCommandsToList(); // Add to list some important and basic commands

        shellHistory = new ArrayDeque<>(20); // A dequeue to store the history
        
        if(System.getProperty("os.name").contains("Windows")){
            String[] dirs = System.getProperty("user.dir").replace("\\", "/").split("/");
            currentDirectory = dirs[0];
            currentDirectory += "\\";
            currentDirectory += dirs[1];
        }
    }

    
    /**
     * Adds a command to the command list
     *
     * @param command    a String with the command to adding to list*/
    public void addACommandToList(String command){
        this.listOfCommands.add(command);
    }

    
    /**
     * Adds a command to the shell history
     *
     * @param command               String to store a command in  history*/
    public void addToHistory(String command){
        if(this.shellHistory.size() == 20) this.shellHistory.poll();

        this.shellHistory.add(command);
    }

    
    /**
     * Adds basic commands to the command list.
     * If recognize Windows, adds ipconfig, else adds ifconfig
     * */
    private void addInitialCommandsToList(){
        this.listOfCommands.add("ls");
        this.listOfCommands.add("cd");
        this.listOfCommands.add("echo");
        this.listOfCommands.add("ping");

        if(System.getProperty("os.name").contains("Windows"))  this.listOfCommands.add("ipconfig");
        else this.listOfCommands.add("ifconfig");

        this.listOfCommands.add("history");
        this.listOfCommands.add("help");
        this.listOfCommands.add("exit");
    }

    
    /**
     * Shows the prompt to user
     *
     * @return   True wheter prompt must continue*/
    public boolean showPrompt(){
        Scanner input = new Scanner(System.in);

        System.out.print("\n" + currentDirectory + PROMPT);
        String command_sequence = input.nextLine();

        return executeCommandSequence(command_sequence);
    }

    
    /**
     * Checks if command is correct
     * */
    private boolean checkCommand(String command){
        return this.listOfCommands.contains(command);
    }

    
    /**
     * 
     */
    public void processACommand(String command) throws IOException{
        /*TESTING*/
        String[] command_and_args = command.split(" ");
        List<String> command_parts = new ArrayList<>();
        
        for(String s : command_and_args){
            command_parts.add(s);
        }
        
        SystemCommandExecutor commandExecutor = new SystemCommandExecutor(command_parts);
        try {
            int result = commandExecutor.executeCommand(currentDirectory);
        } catch (InterruptedException ex) {
            Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // get the output from the command
        StringBuilder stdout = commandExecutor.getStandardOutputFromCommand();
        
        // print the output from the command
        System.out.println("executed: " + stdout);
    }
    
    
    /**
     * Executes the command
     * */
    private void executeCommand(String command){
        String command_header = command.split(" ")[0]; // The beginning of the command (without options)

        addToHistory(command);

        try {
            // If command is in the commands list
            if(checkCommand(command_header)){
                Runtime runtime = Runtime.getRuntime(); // To run as terminal
                Process process = runtime.exec(command); // Executing as terminal

                
                // Getting the result of executing the command
                System.out.println(command + ":");

                // Printing result
                try (BufferedReader in = new BufferedReader(new InputStreamReader((process.getInputStream())))) {
                    // Printing result
                    String inputLine;
                    while((inputLine = in.readLine()) != null){
                        System.out.println("\t" + inputLine);
                    }
                 
                    System.out.println();
                }
            }
            else{
                System.err.printf("\"%s\" is not recognized as an internal or external command, program or executable " +
                                "batch file.\n\n", command);
            }

        } catch (IOException ex) {
            System.out.println("Error: can't execute the command: " + ex.toString());
         }
    }

    
    /**
     * Separates all the string of commands by the given separator (attribute SEPARATOR). Then executes each command.
     * Checks if the current command is exit.
     * */
    private boolean executeCommandSequence(String command_sequence){
        // Separates each command by SEPARATOR (&)
        command_sequence = command_sequence.replace("^", "&");
        String[] SeparatedCommands = command_sequence.split(Character.toString((char)SEPARATOR));
        
        // Executes each command separated
        for(String command : SeparatedCommands) {
            System.out.println("Command: " + command);
            if(command.trim().contains("cd")){
                String[] cd_command = command.split(" ");
                if(cd_command[1].equals("..")){
                    String dir = this.currentDirectory.replace("\\", "/");
                    String[] dirs = dir.split("/");
                    this.currentDirectory = "";
                    for(int i = 0; i < dirs.length - 1; i++){
                        this.currentDirectory += dirs[i];
                        this.currentDirectory += "\\";
                    }
                    this.currentDirectory = this.currentDirectory.substring(0, this.currentDirectory.length()-1);
                    return true;
                }
                
                System.out.println("ENTRE1!!");
                String second_part = "";
                if(cd_command.length >= 2){
                    for(int i = 1; i < cd_command.length; i++){
                        second_part+=cd_command[i];
                        second_part+=" ";
                    }
                    second_part = second_part.substring(0, second_part.length()-1); // Quita el espacio final
                }
                
                System.out.println("Second; " + second_part);
                System.out.println("LO QUE MNADO: " + this.currentDirectory + "\\" + second_part);
                        
                File dir = new File(this.currentDirectory + "\\" + second_part);
                if(dir.exists()){
                    if(dir.isDirectory()){
                        if(System.getProperty("os.name").contains("Windows"))
                            this.currentDirectory+="\\";
                        this.currentDirectory+=dir.getName();
                    }
                }
                else{
                    System.out.println("No existe: " + this.currentDirectory + second_part);
                }
                addToHistory(command);
                return true;
            }
            
            if(command.trim().equals("exit"))
                return false;
            else if(command.trim().equals("history")){
                showHistory();
                return true;
            }
            else if(command.trim().equals("help")){
                showCommands();
                return true;
            }
            else if(command.trim().equals("!1")){
                String firstCommand = this.shellHistory.peek();
                if(firstCommand != null) executeCommand(firstCommand);
                else System.out.println("Error: There is no history");
                
                return true;
            }
            else if(command.trim().equals("!#")){
                String lastCommand = this.shellHistory.getLast();
                if(lastCommand != null) executeCommand(lastCommand);
                
                return true;
            }

            try {
                processACommand(command);
            } catch (IOException ex) {
                Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
            }
            executeCommand(command.trim());
        }
        return true;
    }

    
    /**
     * Shows history of commands
     * */
    public void showHistory() {
        System.out.println("History:");

        int counter = 0;
        for (String command : this.shellHistory) {
            if (!command.equals(""))
                System.out.printf("\t%d %s\n", ++counter, command);
        }
    }

    
    /**
     * Shows the list of commands
     * */
    public void showCommands(){
        System.out.println("List of commands:");
        for(String command : listOfCommands)
            System.out.printf("\t-> \"%s\"\n", command);
    }
}
