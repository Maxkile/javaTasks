package ru.netcrackeredu.SivaschenkoPavel.consoleCalc;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static ru.netcrackeredu.SivaschenkoPavel.consoleCalc.ExitConstants.NORMAL_EXIT;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        CalcScanner calcscanner = new CalcScanner("Calc.properties");
                calcscanner.getInfo();
                while (true) {
                    try {
                        BigDecimal arg1 = calcscanner.getArgument();
                        Operations op = calcscanner.getOperation();
                        BigDecimal arg2 = calcscanner.getArgument();
                        System.out.println(calcscanner.getResult(arg1, arg2, op));
                    } catch (ru.netcrackeredu.SivaschenkoPavel.consoleCalc.NotAnOperationException op_exception) {
                        System.out.println("Incorrect operation. " + op_exception.getLocalizedMessage());
                    } catch (ArithmeticException arithm_exception) {
                        System.out.println("Incorrect number. " + arithm_exception.getLocalizedMessage());
                    } catch (NumberFormatException format_exception) {
                        System.out.println("Incorrect number. " + format_exception.getLocalizedMessage());
                    } catch (UnavailableOperationInvokeException disabledOp_exception) {
                        System.out.println(disabledOp_exception.getLocalizedMessage());
                    } catch (NoSuchElementException no_element_exception) {
                        System.out.println("End of input. Exiting...");
                        calcscanner.exit(ExitConstants.NORMAL_EXIT);
            }
        }
    }
}
