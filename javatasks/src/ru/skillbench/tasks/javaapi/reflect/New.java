package ru.skillbench.tasks.javaapi.reflect;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.stream.Stream;

public class New {
    public static void main(String[] args) throws Throwable {
        Reflector.SampleNumber sample = new Reflector.SampleNumber(2);
        Reflector reflector = new ReflectorImpl();
        reflector.setClass(Reflector.SampleNumber.class);
//        Stream<Field> stream =  reflector.getAllDeclaredFields();
//        Iterator<Field> it = stream.iterator();
//        while(it.hasNext()){
//            System.out.println(it.next().getType().getName());
//        }
        System.out.println(reflector.getMethodResult("qwe","byteValue",null));
    }
}
