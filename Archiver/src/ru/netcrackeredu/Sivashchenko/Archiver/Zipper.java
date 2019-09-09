package ru.netcrackeredu.Sivashchenko.Archiver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

class Zipper{

    private Zipper(){}

    private static void addDir(File dir,ZipOutputStream where) throws IOException{
        File[] dir_files = dir.listFiles();
        for (int i = 0; i < dir_files.length; ++i) {
            if (dir_files[i].isDirectory()) {
                addDir(dir_files[i], where);
            }
            //not dir
            else {
                ZipEntry entry = new ZipEntry(dir_files[i].getPath());
                System.out.println(entry.getName());
                entry.setMethod(ZipEntry.DEFLATED);//choose compressing method
                where.putNextEntry(entry);
                ZipUtils.writeDatatoZip(dir_files[i].getPath(), where);
            }
        }
    }

    //no comments
    public static void createZip(String archive_name,List<String> files){
        try{
            //checking if files exist
            for(String file:files){
                if (!ZipUtils.fileExists((file))) throw new FileNotFoundException(file);
            }
            ZipUtils.deleteFileIfExists(archive_name);
            ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(archive_name), StandardCharsets.UTF_8);
            for (String file : files) {
                File temp = new File(file);
                if (temp.isDirectory()) addDir(temp,zipout);
                else{
                    ZipEntry entry = new ZipEntry(temp.getPath());
                    System.out.println(entry.getName());
                    entry.setMethod(ZipEntry.DEFLATED);//choose compressing method
                    zipout.putNextEntry(entry);
                    ZipUtils.writeDatatoZip(temp.getPath(), zipout);
                }

            }
            zipout.close();
        } catch (FileNotFoundException fnf){
            System.out.println("Can't find file " + "\"" + fnf.getLocalizedMessage() + "\"" + "!");
            System.exit(ArchiveExitConstants.FILE_OR_PATH_NOT_FOUND);
        } catch (IOException io){
            System.out.println(io.getLocalizedMessage());
            System.exit(ArchiveExitConstants.IO_EXC);
        }
    }

    //with comments
    public static void createZip(String archive_name,List<String> files,String comment){
        try{
            //checking if files exist
            for(String file:files){
                if (!ZipUtils.fileExists((file))) throw new FileNotFoundException(file);
            }
            ZipUtils.deleteFileIfExists(archive_name);
            ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(archive_name), StandardCharsets.UTF_8);
            if (comment != null) zipout.setComment(comment);//adding comments
            for (String file : files) {
                File temp = new File(file);
                if (temp.isDirectory()) addDir(temp,zipout);
                else{
                    ZipEntry entry = new ZipEntry(temp.getPath());
                    System.out.println(entry.getName());
                    entry.setMethod(ZipEntry.DEFLATED);//choose compressing method
                    zipout.putNextEntry(entry);
                    ZipUtils.writeDatatoZip(temp.getPath(), zipout);
                }

            }
            zipout.close();
        } catch (FileNotFoundException fnf){
            System.out.println("Can't find file " + "\"" + fnf.getLocalizedMessage() + "\"" + "!");
            System.exit(ArchiveExitConstants.FILE_OR_PATH_NOT_FOUND);
        } catch (IOException io){
            System.out.println(io.getLocalizedMessage());
            System.exit(ArchiveExitConstants.IO_EXC);
        }
    }

}
