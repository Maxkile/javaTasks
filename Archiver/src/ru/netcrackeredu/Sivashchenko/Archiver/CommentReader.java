package ru.netcrackeredu.Sivashchenko.Archiver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class CommentReader {

    public static String readComments(String archive_name){
        String comments = null;
        try{
            ZipFile zipout = new ZipFile(new File(archive_name),ZipFile.OPEN_READ);
            comments = zipout.getComment();
            zipout.close();
        } catch (IOException io){
            System.out.println(io.getLocalizedMessage());
            System.exit(ArchiveExitConstants.IO_EXC);
        }
        return comments;
    }
}
