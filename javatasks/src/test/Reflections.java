package test;

import ru.skillbench.tasks.basics.entity.Employee;
import ru.skillbench.tasks.basics.entity.EmployeeImpl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflections {

    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Employee man = null;
        Class cls = Class.forName(EmployeeImpl.class.getName());
//        man = (EmployeeImpl)cls.newInstance();
        Constructor m = cls.getConstructor(String.class,String.class,int.class);
        man = (EmployeeImpl)m.newInstance("Dyadya","Vasya",12000);
        Field field = cls.getDeclaredField("salary");
        field.setAccessible(true);
        field.set(man,1200);
        Method salary = man.getClass().getMethod("getSalary");
        Object sal = salary.invoke(man);
        System.out.println(sal.getClass().getName());
        Class clss = Class.forName("test.Reflections");
    }
}
