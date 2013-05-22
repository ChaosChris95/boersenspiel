package boersenspiel.shell;

import boersenspiel.manager.AccountManagerImpl;

import java.lang.reflect.Method;

/**
 * User: Peach
 * Date: 20.05.13
 * Time: 14:03
 */
public class InvocationHandler implements java.lang.reflect.InvocationHandler{

    //AccountManagerImpl accountManager = AccountManagerImpl.getInstance();
    private Object AccountManagerImpl;

    public InvocationHandler(Object impl) {
        this.AccountManagerImpl = impl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class == method.getDeclaringClass()) {
            String name = method.getName();
            if ("equals".equals(name)) {
                return proxy == args[0];
            } else if ("hashCode".equals(name)) {
                return System.identityHashCode(proxy);
            } else if ("toString".equals(name)) {
                return proxy.getClass().getName() + "@" +
                        Integer.toHexString(System.identityHashCode(proxy)) +
                        ", with InvocationHandler " + this;
            } else {
                throw new IllegalStateException(String.valueOf(method));
            }
        }
        return method.invoke(AccountManagerImpl, args);
    }
}
