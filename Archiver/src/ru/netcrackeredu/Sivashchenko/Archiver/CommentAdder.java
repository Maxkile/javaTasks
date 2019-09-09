package ru.netcrackeredu.Sivashchenko.Archiver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class CommentAdder {

    public static void addComments(String archive_name,String comment){
        try{
            System.out.println(comment);
            String temp_name = ZipUtils.generateZipName();
            ZipOutputStream zip_stream = new ZipOutputStream(new FileOutputStream(temp_name), StandardCharsets.UTF_8);//temp archive
            ZipFile src_zipfile = new ZipFile(new File(archive_name),ZipFile.OPEN_READ);//source archive
            ArrayList<? extends ZipEntry> src_entries = Collections.list(src_zipfile.entries());//to make it implement comparable interface
            for (ZipEntry src_entry : src_entries) {
                ZipEntry entry = new ZipEntry(src_entry.getName());
                zip_stream.putNextEntry(entry);
                ZipUtils.writeDatatoZip(src_zipfile.getInputStream(entry),zip_stream);
            }
            zip_stream.setComment(comment);
            src_zipfile.close();
            zip_stream.close();
            if (!ZipUtils.deleteFile(archive_name) || !ZipUtils.renameFile(temp_name,archive_name)){
                System.out.println("Error with " + "\"" + archive_name + "\"" +" .Process can't get access to file because it is being used by another process.");
                ZipUtils.deleteFile(temp_name);
                System.exit(ArchiveExitConstants.RESOURCE_BUSY);
            }
        } catch (FileNotFoundException fnf){
            System.out.println("Can't find file " + "\"" + fnf.getLocalizedMessage() + "\"" + "!");
            System.out.println("FNF");
            System.exit(ArchiveExitConstants.FILE_OR_PATH_NOT_FOUND);
        } catch (IOException io){
            System.out.println(io.getLocalizedMessage());
            System.exit(ArchiveExitConstants.IO_EXC);
        }
    }
}
