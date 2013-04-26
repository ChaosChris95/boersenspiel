package boersenspiel.interfaces;

import boersenspiel.StockGameCommandType;

/**
 * User: Peach
 * Date: 26.04.13
 * Time: 11:36
 */
public interface CommandTypeInfo {


    void getName(String name);

    void getHelpText(String text);

    void getParamType(Class <StockGameCommandType> StockGameCommandType);

}


