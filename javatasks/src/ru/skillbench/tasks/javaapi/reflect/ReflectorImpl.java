package ru.skillbench.tasks.javaapi.reflect;

import java.lang.reflect.*;
import java.nio.channels.CancelledKeyException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReflectorImpl implements Reflector{

    private Class<?> aim_class;

    @Override
    public void setClass(Class<?> clazz) {
        aim_class = clazz;
    }

    @Override
    public Stream<String> getMethodNames(Class<?>... paramTypes) {
        List<String> method_names = new LinkedList<String>();
        Method[] methods =  aim_class.getMethods();
        for(Method meth:methods){
            Class<?>[] pars = meth.getParameterTypes();
            if (Arrays.equals(paramTypes,pars))
                method_names.add(meth.getName());
        }
        return method_names.stream();
    }

    private <T> LinkedList<T> asList(T[] array){
        return new LinkedList<T>(Arrays.asList(array));
    }

    @Override
    public Stream<Field> getAllDeclaredFields() {
        LinkedList<Field> fields = new LinkedList<Field>();
        fields =  asList(aim_class.getDeclaredFields());
        Class<?> cur_class = aim_class.getSuperclass();
        Class<?> prev_class = aim_class;
        while((cur_class != null) && (!cur_class.equals(prev_class))){//adding fields from superclasses
            fields.addAll(asList(cur_class.getDeclaredFields()));
            prev_class = cur_class;
            cur_class = cur_class.getSuperclass();
        }
        Stream<Field> out = fields.stream();
        return out.filter(x -> !Modifier.isStatic(x.getModifiers())).collect(Collectors.toList()).stream();
    }

    @Override
    public Object getFieldValue(Object target, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        if (aim_class == null){
            Class<?> clazz = target.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(target);
        }
        else{
            Field field = aim_class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(target);
        }
    }

    @Override
    public Object getMethodResult(Object constructorParam, String methodName, Object... methodParams) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Constructor<?> constr;
        Object instance;
        Method method;
        Object res = null;
       try{//see method in main class
           if (constructorParam == null){
               constr = aim_class.getConstructor();
               instance = constr.newInstance();
           }
           else{
               constr = aim_class.getConstructor(constructorParam.getClass());
               instance = constr.newInstance(constructorParam);
           }
           if (methodParams == null){
               method = aim_class.getDeclaredMethod(methodName);
               method.setAccessible(true);
               int count = method.getParameterCount();
               res = method.invoke(instance);
           }
           else {
               Class<?>[] param_class = new Class<?>[methodParams.length];//Class<?> type array of methodParams
               for (int i = 0; i < methodParams.length; ++i) {
                   param_class[i] = methodParams[i].getClass();
               }
               method = aim_class.getDeclaredMethod(methodName, param_class);
               method.setAccessible(true);
               int count = method.getParameterCount();
               res = method.invoke(instance, methodParams);
           }
       //see method in parent class
       } catch (NoSuchMethodException nsm){
           Class<?> super_class = aim_class.getSuperclass();
           try{
               if (constructorParam == null){
                   constr = aim_class.getConstructor();
                   instance = constr.newInstance();
               }
               else{
                   constr = aim_class.getConstructor(constructorParam.getClass());
                   instance = constr.newInstance(constructorParam);
               }
               if (methodParams == null){
                   method = super_class.getDeclaredMethod(methodName);
                   method.setAccessible(true);
                   int count = method.getParameterCount();
                   res = method.invoke(instance);
               }
               else {
                   Class<?>[] param_class = new Class<?>[methodParams.length];//Class<?> type array of methodParams
                   for (int i = 0; i < methodParams.length; ++i) {
                       param_class[i] = methodParams[i].getClass();
                   }
                   method = super_class.getDeclaredMethod(methodName, param_class);
                   method.setAccessible(true);
                   int count = method.getParameterCount();
                   res = method.invoke(instance, methodParams);
               }
               return res;
           } catch (InvocationTargetException ite) {
               System.out.println(ite.getMessage());
           }
       } catch (RuntimeException ite) {
           throw new InvocationTargetException(ite);
       }
       return res;
    }

}
