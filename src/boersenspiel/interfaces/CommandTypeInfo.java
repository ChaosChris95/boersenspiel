package boersenspiel.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 26.04.13
 * Time: 10:39
 */
public interface CommandTypeInfo<T> {

    /**
     *
     * @return
     */

    String getCmdName();

    /**
     *
     * @return
     */

    String getHelpText();

    /**
     *
     * @return
     */

    Class<?>[] getParamTypes();



}
