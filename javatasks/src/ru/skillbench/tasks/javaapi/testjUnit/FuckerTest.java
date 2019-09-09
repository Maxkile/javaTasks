package ru.skillbench.tasks.javaapi.testjUnit;

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class FuckerTest {
    private static Fucker fuck;

    @BeforeClass
    public static void creationReflect() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        Class<?> fuck = Class.forName("ru.skillbench.tasks.javaapi.testjUnit.Fucker");
        Constructor<?> constr = fuck.getConstructor(double.class,double.class);
        Fucker fu = (Fucker) constr.newInstance(1.2,2.3);
    }

    @Test
    public void testgetFuck(){
//        fuck.getFuck(null);

    }
}
