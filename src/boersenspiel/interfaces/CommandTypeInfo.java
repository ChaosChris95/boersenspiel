package boersenspiel.interfaces;

/**
 * User: jan
 * Date: 26.04.13
 * Time: 10:39
 */

public interface CommandTypeInfo {

    /**
     * get name of command
     * @return String
     */

    public String getCmdName();

    /**
     * get help text
     * @return String
     */

    public String getHelpText();

    /**
     * get the types of the parameters of a command
     * @return Class\<\?\>[]
     */

    public Class<?>[] getParamTypes();

    /**
     * get the target which stands for the used Class
     * @return String
     */

    public String getTarget();

    /**
     * get the target which stands for the used Method
     * @return String
     */

    public String getFunc();






}
