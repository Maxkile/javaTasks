package ru.netcrackeredu.SivaschenkoPavel.consoleCalc;

class NotValidOperationException extends RuntimeException {

    private String name;

    public NotValidOperationException(String name) {
        this.name = name;
    }

    public NotValidOperationException() {
    }

    public String toString() {
        return this.name;
    }

    public String getLocalizedMessage() {
        return "No operation declaring found for:" + " \"" + this.name + "\"";
    }
}
