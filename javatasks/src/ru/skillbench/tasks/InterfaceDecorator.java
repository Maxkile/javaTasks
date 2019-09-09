package ru.skillbench.tasks;

public interface InterfaceDecorator<T> {
    void doOperation(T erg);
}

class MainDecorator<T extends Number> implements  InterfaceDecorator<T>{

    @Override
    public void doOperation(T elem) {
        System.out.println(elem.byteValue());
    }
}

class BasicDecorator extends MainDecorator{
    InterfaceDecorator additional_dec;

    public BasicDecorator(InterfaceDecorator dec){
        additional_dec = dec;
    }

    public void doOtherOperation(String str){
        System.out.println(str.length());
    }
}

class Decorator extends BasicDecorator{

    public Decorator(InterfaceDecorator dec){
        super(dec);
    }


    @Override
    public void doOtherOperation(String str){
        System.out.println(str);
    }
}

class Main{
    public static void main(String[] args) {
        Decorator dec = new Decorator(new BasicDecorator(new MainDecorator()));
        dec.doOperation((byte)123);
        dec.doOtherOperation("Hello");
    }
}
