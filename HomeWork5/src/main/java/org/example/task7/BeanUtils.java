package org.example.task7;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        if (to == null || from == null) {
            throw new IllegalArgumentException("Both 'to' and 'from' objects must be non-null.");
        }

        Method[] fromMethods = from.getClass().getMethods();

        for (Method fromMethod : fromMethods) {
            if (isGetter(fromMethod)) {
                try {
                    setValue(to, from, fromMethod);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void setValue(Object to, Object from, Method fromMethod) throws IllegalAccessException, InvocationTargetException {
        String propertyName = getPropertyName(fromMethod);
        Method toSetter = findSetter(to.getClass(), propertyName, fromMethod.getReturnType());
        if (toSetter != null) {
            Object value = fromMethod.invoke(from);
            toSetter.invoke(to, value);
        }
    }

    /**
     * Returns true if the given method is a getter.
     * @param method Method to process.
     */
    private static boolean isGetter(Method method) {
        String methodName = method.getName();
        return (methodName.startsWith("get") || methodName.startsWith("is"))
                && method.getParameterCount() == 0
                && !method.getReturnType().equals(void.class);
    }

    /**
     * Returns property name for the given getter method.
     * For example, for method "getSomeProperty" returns "someProperty".
     * Throws IllegalArgumentException if the given method is not a getter.
     * @param getterMethod Method to process.
     */
    private static String getPropertyName(Method getterMethod) {
        String methodName = getterMethod.getName();
        if (methodName.startsWith("get")) {
            return methodName.substring(3);
        }

        if (methodName.startsWith("is")) {
            return methodName.substring(2);
        }

        throw new IllegalArgumentException("Not a valid getter method: " + methodName);

    }

    /**
     * Returns setter for the property with the given name and type.
     * If no such setter exists, returns null.
     * @param clazz Class to process.
     * @param propertyName Name of the property.
     * @param propertyType Type of the property.
     */
    private static Method findSetter(Class<?> clazz, String propertyName, Class<?> propertyType) {
        try {
            String setterName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
            return clazz.getMethod(setterName, propertyType);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
