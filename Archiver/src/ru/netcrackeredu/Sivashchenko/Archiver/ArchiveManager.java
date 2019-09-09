package ru.netcrackeredu.Sivashchenko.Archiver;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * ru.netcrackeredu.Sivashchenk.Archiver.Main zip-processor of the program. Checks and stores input paths,archive names and file names. It also checks command line flags such as:
 * "-h" - display help information;
 * "-v" - displays program version;
 * "-c" - compressing mode(existing files in archive would be overwritten)
 * "-d" - decompressing mode.
 * "-a" - adding files to existing archive mode(existing files in archive wouldn't be overwritten)
 */

public class ArchiveManager {

    private List<String> files;
    private String archive_name;
    private String comment;
    private String unzip_path;
    private ArchiveMode mode;

    ArchiveManager(String[] attributes){
        try{
            parser(attributes);
        } catch(WrongModeException wrong_op){
            System.out.print(wrong_op.getLocalizedMessage());
            new Helper().print();
            System.exit(ArchiveExitConstants.WRONG_MODE_FLAG);
        }
    }


    private void parser(String[] attributes) throws WrongModeException{
        if (attributes.length >= 1){//for flag
            mode = ArchiveMode.getMode(attributes[0]);
        }
        else {
            System.out.println("Wrong mode flag!\n");
            new Helper().print();
            System.exit(ArchiveExitConstants.WRONG_MODE_FLAG);
        }
        if (attributes.length >= 2){//for archive name
            archive_name = attributes[1];
        }
        else{
            System.out.println("Wrong archive name!\n");
            new Helper().print();
            System.exit(ArchiveExitConstants.WRONG_ARCHIVE_NAME);
        }
        if (mode.equals(ArchiveMode.ARCHIVE) || mode.equals(ArchiveMode.ADD)){//for compression
            if (attributes.length >= 3){
                files = Arrays.asList(Arrays.copyOfRange(attributes,2,attributes.length));
            }
            else{
                System.out.println("No input files!\n");
                new Helper().print();
                System.exit(ArchiveExitConstants.NO_INPUT_FILES);
            }
        }
        else if (mode.equals(ArchiveMode.UNARCHIVE)){//for decompression
            if (attributes.length == 3){
                if (isPath(attributes[2])) unzip_path = attributes[2];
                else{
                    System.out.println("Wrong path!\n");
                    new Helper().print();
                    System.exit(ArchiveExitConstants.FILE_OR_PATH_NOT_FOUND);
                }
            }
            else if (attributes.length > 3){
                System.out.println("Too many arguments! See help.\n");
                new Helper().print();
                System.exit(ArchiveExitConstants.WRONG_NUM_ARG);
            }
        }
        else if (mode.equals(ArchiveMode.ADD_COMMENTS)){//for decompression
            if (attributes.length == 3){
                unzip_path = attributes[2];
            }
            else if (attributes.length > 3){
                System.out.println("Too many arguments! See help.\n");
                new Helper().print();
                System.exit(ArchiveExitConstants.WRONG_NUM_ARG);
            }
        }
        else if (mode.equals((ArchiveMode.CREATE_WITH_COMMENTS))){
            if (attributes.length >= 4){
                comment = attributes[2];
                files = Arrays.asList(Arrays.copyOfRange(attributes,3,attributes.length));
            }
            else{
                System.out.println("No input files!\n");
                new Helper().print();
                System.exit(ArchiveExitConstants.NO_INPUT_FILES);
            }
        }
    }


    public static boolean isPath(String name){
        File tmp = new File(name);
        return tmp.isDirectory();
    }

    public void archive(){
        switch (mode){
            case SHOW_HELP:
                new Helper().print();
                break;
            case SHOW_VERSION:
                new Versioner().print();
                break;
            case ARCHIVE:
                Zipper.createZip(archive_name,files);
                System.out.println("Successfully zipped!");
                break;
            case UNARCHIVE:
                Unzipper.unzipTo(archive_name,unzip_path);
                System.out.println("Successfully unziped!");
                break;
            case ADD:
                Adder.addFilesTo(archive_name,files);
                System.out.println("Successfully added!");
                break;
            case CREATE_WITH_COMMENTS:
                Zipper.createZip(archive_name,files,comment);
                System.out.println("Successfully zipped, comments added!");
                break;
            case ADD_COMMENTS:
                String comments = unzip_path;
                CommentAdder.addComments(archive_name,comments);//unzip_path is comments itself here
                System.out.println("Comments added!");
                break;
            case READ_COMMENTS:
                comments = CommentReader.readComments(archive_name);
                if (comments == null) System.out.println("No comments found.");
                System.out.println(comments);
                break;
        }
    }

    public String getArchiveName(){
        return this.archive_name;
    }

    public String getUnzipPath(){
        return this.unzip_path;
    }

    public List<String> getFiles(){
        return this.files;
    }

}
