package ru.netcrackeredu.SivaschenkoPavel.consoleCalc;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.BinaryOperator;

import static ru.netcrackeredu.SivaschenkoPavel.consoleCalc.ExitConstants.*;
import static ru.netcrackeredu.SivaschenkoPavel.consoleCalc.ScaleConstants.DIVISION_SCALE;

class CalcScanner {

    private Scanner scanner;
    private PropertiesReader reader;

    private boolean operationIsAvailable(Operations operation) {
        for (Object key : reader.getKeys()) {
            if (operation.getName().equals(key.toString())) {
                return reader.getProperty(key.toString()).equals("available");
            }
        }
        return false;
    }

    public CalcScanner(String filename) {
        this.scanner = new Scanner(System.in);
        this.reader = new PropertiesReader(filename);
    }

    public BigDecimal getArgument() throws NumberFormatException, NoSuchElementException {
        String arg = this.scanner.nextLine();
        if ((arg.equals(EXIT_1)) || arg.equals(EXIT_2) || arg.equals(EXIT_3) || arg.equals(EXIT_4)) exit(NORMAL_EXIT);
        return new BigDecimal(arg);
    }

    public Operations getOperation() throws ru.netcrackeredu.SivaschenkoPavel.consoleCalc.NotAnOperationException, NoSuchElementException {
        String arg = this.scanner.nextLine();
        for (Operations operation : Operations.values()) {
            if (arg.equals(operation.getSign()))
                if (operationIsAvailable(operation)) return operation;
                else throw new UnavailableOperationInvokeException(arg);
        }
        throw new ru.netcrackeredu.SivaschenkoPavel.consoleCalc.NotAnOperationException(arg);
    }

    /**
     * @param key Class name
     * @return Instance of class named "key", created with reflection API.
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     */
    private BinaryOperator<BigDecimal> getInstance(String key) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String abs_class_name = CalcScanner.class.getPackageName() + "." + key.toString();//Determining absolute path to our class(it is the same as absolute path to CalcScanner.class
        Class<?> cls = Class.forName(abs_class_name);
        return (BinaryOperator<BigDecimal>)cls.newInstance();
    }

    public BigDecimal getResult(BigDecimal arg1, BigDecimal arg2, Operations operation) throws NotValidOperationException {
        try {
            BinaryOperator<BigDecimal> operator;
            for (Object key : reader.getKeys()) {
                if (operation.getName().equals(key.toString())) {
                    operator = getInstance(key.toString());
                    return operator.apply(arg1, arg2);//using dynamic polymorphism
                }
            }
        } catch (ClassNotFoundException cnf_exception) {
            System.out.println("Class " + " \"" + cnf_exception.getLocalizedMessage() + "\" " + "not found!Please, check your configuration file!");
            exit(CLASS_NOT_FOUND);
        } catch (InstantiationException inst_exception) {
            System.out.println(inst_exception.getLocalizedMessage());
            exit(NO_CONSTRUCTOR_FOUND);
        } catch (IllegalAccessException access_exception) {
            System.out.println(access_exception.getLocalizedMessage());
            exit(ILLEGAL_ACCESS);
        } catch (NoSuchMethodException no_method) {
            System.out.println("No method " + no_method.getLocalizedMessage() + " found!");
            exit(NO_METHOD);
        } catch (InvocationTargetException invoke_exception) {
            System.out.println(invoke_exception.getLocalizedMessage());
            exit(INVOKE_ERROR);
        }
        throw new NotValidOperationException(operation.getName());
    }

    public void getInfo() {
        try {
            System.out.println("Input format:\n");
            System.out.println("-Operand");
            System.out.print("-Operator(function)");
            System.out.print("{");
            for (Object key : reader.getKeys()) {
                if (reader.getProperty(key.toString()).equals("available"))
                    System.out.print(" " + Operations.getSign(key.toString()) + " ");
            }
        } catch (NotValidOperationException noOperation_exception) {
            System.out.println("}");
            System.out.println("\n" + noOperation_exception.getLocalizedMessage());
            exit(NO_VALID_OPERATION);
        }
        System.out.println("}");
        System.out.println("-Operand\n");
        System.out.println("Division scale is set to " + ScaleConstants.DIVISION_SCALE + "\n");
        System.out.println("Enter any of commands(as first argument): " + "\"" + EXIT_1 + "\"" + "," + "\"" + EXIT_2 + "\"" + "," + "\"" + EXIT_3 + "\"" + "," + "\"" + EXIT_4 + "\"" + " to exit from calculator\n");
    }

    public void exit(int status) {
        this.scanner.close();
        this.reader.closeStream();
        System.out.println("Exiting...");
        System.exit(status);
    }

}
