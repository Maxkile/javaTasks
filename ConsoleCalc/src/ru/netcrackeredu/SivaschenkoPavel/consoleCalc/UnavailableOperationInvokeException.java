package ru.netcrackeredu.SivaschenkoPavel.consoleCalc;

public class UnavailableOperationInvokeException extends RuntimeException {

    private String name;

    public UnavailableOperationInvokeException(String name) {
        this.name = name;
    }

    public UnavailableOperationInvokeException() {
    }

    public String toString() {
        return this.name;
    }

    public String getLocalizedMessage() {
        return "Operation" + " \"" + this.name + "\" " + "is declared but unavailable! Check config file!";
    }
}
