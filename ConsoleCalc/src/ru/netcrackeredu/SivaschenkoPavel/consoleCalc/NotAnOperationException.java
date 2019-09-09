package ru.netcrackeredu.SivaschenkoPavel.consoleCalc;

class NotAnOperationException extends RuntimeException {

    private String name;

    public NotAnOperationException(String name) {
        this.name = name;
    }

    public NotAnOperationException() {
    }

    public String toString() {
        return this.name;
    }

    public String getLocalizedMessage() {
        return "\"" + this.name + "\"";
    }
}
