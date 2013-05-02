package boersenspiel.shell;

import boersenspiel.exceptions.CommandScannerException;
import boersenspiel.interfaces.CommandTypeInfo;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Peach
 * Date: 02.05.13
 * Time: 16:24
 */

public class CommandScanner {

    private CommandTypeInfo commandTypeInfos[];
    private BufferedReader shellReader;

    public CommandScanner(CommandTypeInfo[] commandTypeInfos, BufferedReader shellReader){
        this.commandTypeInfos = commandTypeInfos;
        this.shellReader = shellReader;
    }

    public void fillInCommandDesc(CommandDescriptor command) throws CommandScannerException {    //?
        // Sucht auf der Konsoleneingabe nach gültigen Kommandos und speichert sie in command
        String line = null;
        try {
            line = this.shellReader.readLine();
            if(line == null) {
                throw new CommandScannerException("Nichts eingegeben");
            }
        } catch (IOException e) {
            throw new CommandScannerException(e.getMessage());
        }
        String[] parts = line.split(" ");
        String cmd = parts[0].toLowerCase();

        for(int i = 0; i < this.commandTypeInfos.length; i++) {
            if(this.commandTypeInfos[i].getCmdName().equals(cmd)) {
                command.commandType = this.commandTypeInfos[i];
                // Kommando gefunden, Parameter prüfen
                Class<?>[] classes = command.commandType.getParamTypes();
                if(classes.length != parts.length - 1) {
                    // Fehler. Nicht genug Parameter (exception)
                    throw new CommandScannerException("Nicht genug Parameter");
                }
                // Platz für die Parameter schaffen
                command.params = new Object[classes.length];

                for(int j = 0; j < classes.length; j++) {
                    Class<?> clas = classes[j];
                    if(clas == Integer.class) {
                        command.params[j] = Integer.parseInt(parts[j + 1]);
                    } else if(clas == Long.class) {
                        command.params[j] = Long.parseLong(parts[j + 1]);
                    } else {
                        command.params[j] = parts[j + 1];
                    }
                }
                return;
            }
        }
        throw new CommandScannerException("Kommando nicht gefunden");
    }

}
