package ru.netcrackeredu.Sivashchenko.Archiver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

class Adder {

    private Adder(){}

    private static void addDir(File dir,ZipOutputStream where) throws IOException{
        File[] dir_files = dir.listFiles();
        for (int i = 0; i < dir_files.length; ++i) {
            if (dir_files[i].isDirectory()) {
                addDir(dir_files[i], where);
            }
            //not dir
            else {
                ZipEntry entry = new ZipEntry(dir_files[i].getPath());
                entry.setMethod(ZipEntry.DEFLATED);//choose compressing method
                where.putNextEntry(entry);
                ZipUtils.writeDatatoZip(dir_files[i].getPath(), where);
            }
        }
    }

    public static void addFilesTo(String archive_name,List<String> files){
        System.out.println("Adding...");
        String temp_name = ZipUtils.generateZipName();

        try{
            //checking if files and archive exist
            for(String file:files){
                if (!ZipUtils.fileExists(file)) throw new FileNotFoundException(file);
            }
            //create archive if it doesn't exist
            if (!ZipUtils.fileExists(archive_name)){
                Zipper.createZip(archive_name,files);
            }
            else{
                //copying files from old archive
                ZipOutputStream zip_stream = new ZipOutputStream(new FileOutputStream(temp_name), StandardCharsets.UTF_8);//temp archive
                ZipFile src_zipfile = new ZipFile(new File(archive_name),ZipFile.OPEN_READ);//source archive
                ArrayList<? extends ZipEntry> src_entries = Collections.list(src_zipfile.entries());//to make it implement comparable interface
                for (ZipEntry src_entry : src_entries) {
                    ZipEntry entry = new ZipEntry(src_entry.getName());
                    if (ZipUtils.entryExists(entry,files)) continue;
                    zip_stream.putNextEntry(entry);
                    ZipUtils.writeDatatoZip(src_zipfile.getInputStream(entry),zip_stream);
                }
                src_zipfile.close();
                //we have put all archive entries to temp archive by there, now have to delete old archive and add new files

                //putting new files to archive
                for (String file : files) {
                    File tmp = new File(file);
                    if (tmp.isDirectory()) addDir(tmp,zip_stream);
                    else{
                        ZipEntry entry = new ZipEntry(tmp.getPath());
                        entry.setMethod(ZipEntry.DEFLATED);//choose compressing method
                        zip_stream.putNextEntry(entry);
                        ZipUtils.writeDatatoZip(tmp.getPath(), zip_stream);
                    }
                }
                zip_stream.close();
                //it is only left to rename temp archive
                if (!ZipUtils.deleteFile(archive_name) || !ZipUtils.renameFile(temp_name,archive_name)){
                    System.out.println("Error with " + "\"" + archive_name + "\"" +" .Process can't get access to file because it is being used by another process.");
                    ZipUtils.deleteFile(temp_name);
                    System.exit(ArchiveExitConstants.RESOURCE_BUSY);
                }
            }
        } catch (FileNotFoundException fnf){
            System.out.println("Can't find file " + "\"" + fnf.getLocalizedMessage() + "\"" + "!");
            System.exit(ArchiveExitConstants.FILE_OR_PATH_NOT_FOUND);
        } catch (IOException io){
            System.out.println(io.getLocalizedMessage());
            System.exit(ArchiveExitConstants.IO_EXC);
        }
    }


}
