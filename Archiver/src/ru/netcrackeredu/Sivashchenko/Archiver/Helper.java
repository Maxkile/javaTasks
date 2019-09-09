package ru.netcrackeredu.Sivashchenko.Archiver;

import java.io.BufferedInputStream;
import java.io.IOException;

class Helper implements Printer {

    private BufferedInputStream help_inp;

    Helper(){
        help_inp = new BufferedInputStream(this.getClass().getResourceAsStream("/resources/Helper.txt"),200);
    }

    private void closePrinter() throws IOException{
        help_inp.close();
    }

    @Override
    public void print() {
        try {
            int i;
            while ((i = help_inp.read()) != -1) {
                System.out.print((char) i);
            }
            System.out.println("\n");
            closePrinter();
        } catch (IOException io){
            System.out.println(io.getLocalizedMessage());
        }
    }
}
