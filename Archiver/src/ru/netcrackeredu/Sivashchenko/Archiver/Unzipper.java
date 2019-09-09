package ru.netcrackeredu.Sivashchenko.Archiver;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

class Unzipper{

    private enum RewriteMode{
        DEFAULT(""),ALLOW("Y"),DISALLOW("N"),ALLOW_ALL("YA"),DISALLOW_ALL("NA");

        private String notation;

        RewriteMode(String notation){
            this.notation = notation;
        }

        public String getNotation(){
            return notation;
        }

        public static RewriteMode getMode(String not) throws WrongModeException{
            for(RewriteMode mode: RewriteMode.values()){
                if (mode.getNotation().equals(not)) return mode;
            }
            throw new WrongModeException(not);
        }
    };

    private static RewriteMode rewriteMode = RewriteMode.DEFAULT;

    private Unzipper(){}

    private static void createDir(String name){
        File dir = new File(name);
        dir.mkdirs();
    }

    /**
     * Unzips current archive entry.
     * @param from Input stream from ZipFile.
     * @param entry Current entry.
     * @param unzip_path Path where entry will be unziped.
     * @return Boolean result: true - success, false - unsuccess.
     * @return Boolean result: true - success, false - unsuccess.
     * @throws IOException
     */
    private static boolean unzipEntry(InputStream from,ZipEntry entry,String unzip_path) throws IOException{//from is where we're reading entry from
        File entry_file;
        boolean file_flag = true;
        if (unzip_path != null){
            if (entry.isDirectory()) {
                createDir(unzip_path  + entry.getName());
                return file_flag;
            }
            else entry_file = new File(unzip_path  + entry.getName());
        }
        else {
            if (entry.isDirectory()) {
                createDir(entry.getName());
                return file_flag;
            }
            else entry_file = new File(entry.getName());
        }

        if (rewriteMode.equals(RewriteMode.DISALLOW_ALL)) return file_flag;
        if (entry_file.exists()){
            if (!rewriteMode.equals(RewriteMode.ALLOW_ALL)){
                System.out.println("File " + "\"" +  entry.getName() + "\"" + " already exists. Do you wish to rewrite it?(\"Y\"|\"N\").\n" +
                        "If you want to rewrite\\not rewrite all next files press \"YA\"\\\"NA\") ");
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                try{
                    String answer = input.readLine();
                    rewriteMode = RewriteMode.getMode(answer);
                    switch (rewriteMode){
                        case ALLOW:
                        case ALLOW_ALL:
                            file_flag = entry_file.delete();
                            break;
                        case DISALLOW:
                        case DISALLOW_ALL:
                            System.out.println("File wasn't rewritten.");
                            return file_flag;//we just do nothing
                    }
                } catch (WrongModeException wrong_answer){
                    System.out.println("You should choose between \"Y\",\"N\",\"YA\" and \"NA\"!");
                    System.exit(ArchiveExitConstants.WRONG_CONSOLE_ANSWER);
                }
            }
            else{
                file_flag = entry_file.delete();
            }
        }
        file_flag = entry_file.createNewFile();
        file_flag = file_flag && entry_file.setWritable(true);
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(entry_file));
        out.write(from.readAllBytes());
        out.close();
        return file_flag;
    }

    public static void unzipTo(String archive_name,String where) {
       try{
           ZipFile zipfile = new ZipFile(new File(archive_name),ZipFile.OPEN_READ);
           Enumeration<? extends ZipEntry> zip_enum = zipfile.entries();
           while(zip_enum.hasMoreElements()){
               ZipEntry next = zip_enum.nextElement();
               if (!unzipEntry(zipfile.getInputStream(next),next,where)){
                   System.out.println("Can't create or write to file! Check your privileges\n");
                   System.exit(ArchiveExitConstants.CANT_CREATE_OR_WRITE_FILE);
               }
               if (!(rewriteMode.equals(RewriteMode.DISALLOW) || rewriteMode.equals(RewriteMode.DISALLOW_ALL))) System.out.println(next.getName());
           }
       } catch (IOException io){
           System.out.println("Can't find file: " + io.getLocalizedMessage());
           System.exit(ArchiveExitConstants.IO_EXC);
       }
    }

}
