package ru.netcrackeredu.Sivashchenko.Archiver;

import java.io.BufferedInputStream;
import java.io.IOException;

class Versioner implements Printer{

    private BufferedInputStream ver_inp;

    Versioner(){
        ver_inp = new BufferedInputStream(this.getClass().getResourceAsStream("/resources/Versioner.txt"));
    }

    private void closePrinter() throws IOException{
        ver_inp.close();
    }

    @Override
    public void print() {
        try {
            int i;
            while ((i = ver_inp.read()) != -1) {
                System.out.print((char) i);
            }
            System.out.println("\n");
            closePrinter();
        } catch (IOException io){
            System.out.println(io.getLocalizedMessage());
        }
    }

}
