package boersenspiel.shell;

import boersenspiel.exceptions.CommandScannerException;
import boersenspiel.interfaces.CommandTypeInfo;

import java.io.BufferedReader;
import java.io.IOException;

/**
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

    public void fillInCommandDesc(CommandDescriptor command) throws CommandScannerException {

        String line;
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
            if (this.commandTypeInfos[i].getCmdName().equals(cmd)) {
                command.commandType = this.commandTypeInfos[i];

                Class<?>[] classes = command.commandType.getParamTypes();
                if (classes.length != parts.length - 1) {
                    throw new CommandScannerException("Nicht genug Parameter");
                }

                command.params = new Object[classes.length];

                for (int j = 0; j < classes.length; j++) {
                    Class<?> clash = classes[j];
                    command.params[j] = clash.getClass().cast(parts[j + 1]);
                    throw new CommandScannerException("Fehlerhafte Eingabe");
                }
                return;
            }
        }
        throw new CommandScannerException("Kommando nicht gefunden");
    }

}
