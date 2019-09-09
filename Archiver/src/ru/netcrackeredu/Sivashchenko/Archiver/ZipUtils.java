package ru.netcrackeredu.Sivashchenko.Archiver;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    private final static int MIN_TEMP_NAME_LEN = 4;
    private final static int MAX_TEMP_NAME_LEN = 10;

    public static String generateZipName(){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        alph = alph + alph.toUpperCase();
        Random random = new Random();
        int len = (int)(Math.random()*MIN_TEMP_NAME_LEN + (MAX_TEMP_NAME_LEN - MIN_TEMP_NAME_LEN));
        StringBuilder res = new StringBuilder(len);
        for(int i = 0;i<len;i++){
            res.append(alph.charAt(random.nextInt(alph.length())));
        }
        res.append(".zip");
        return res.toString();
    }

    public static void writeDatatoZip(String file, ZipOutputStream zip_out) throws IOException {
        zip_out.write(new BufferedInputStream(new FileInputStream(file)).readAllBytes());//writes all data from "file" to entry of ZipOutputStream
        zip_out.closeEntry();
    }

    public static void writeDatatoZip(InputStream from, ZipOutputStream zip_out) throws IOException {
        zip_out.write(new BufferedInputStream(from).readAllBytes());//writes all data from "file" to entry of ZipOutputStream
        zip_out.closeEntry();
    }

    //it's cheaper not to add entries that will be replaced by others than then to check and replace entries
    public static boolean entryExists(ZipEntry entry, List<String> files){
        for(int i = 0;i < files.size();++i){
            File adding_file = new File(files.get(i));
            //if List<String> files element is directory then seek for entry in this element's files recursively
            if (adding_file.isDirectory()){
                String[] dir_filenames = adding_file.list();
                for(int j = 0; j < dir_filenames.length;++j){
                    dir_filenames[j] = files.get(i) + File.separator + dir_filenames[j];
                }
                boolean found_in_dir =  ZipUtils.entryExists(entry, Arrays.asList(dir_filenames));
                //if not found in dir then continue searching
                if (found_in_dir) return true;
                else continue;
            }
            else if (files.get(i).equals(entry.getName())){
                System.out.println(entry.getName() + " already exists!");
                return true;
            }
        }
        return false;
    }

    public static boolean fileExists(String arch_name){
        File arch_file = new File(arch_name);
        return arch_file.exists();
    }

    public static void deleteFileIfExists(String arch_name){
        if (fileExists(arch_name)){
            File arch_file = new File(arch_name);
            arch_file.delete();
        }
    }

    public static boolean deleteFile(String arch_name){
        File arch_file = new File(arch_name);
        return arch_file.delete();
    }

    public static boolean renameFile(String from,String to){
        File arch_file = new File(from);
        return arch_file.renameTo(new File(to));
    }

}
