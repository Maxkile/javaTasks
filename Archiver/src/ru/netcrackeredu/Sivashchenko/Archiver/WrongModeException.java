package ru.netcrackeredu.Sivashchenko.Archiver;

public class WrongModeException extends Exception{

    private String log;

    WrongModeException(String log){
        this.log = "Error! Wrong flag: " + "\"" + log + "\"" +  "!\n\n";
    }

    public String getLocalizedMessage(){
        return log;
    }
}
