package boersenspiel.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 26.04.13
 * Time: 10:39
 */
public interface CommandTypeInfo {

    /**
     *
     * @return
     */

    public String getCmdName();

    /**
     *
     * @return
     */

    public String getHelpText();

    /**
     *
     * @return
     */

    public Class<?>[] getParamTypes();


    public Object getTarget();







}
