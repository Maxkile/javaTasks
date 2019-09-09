package ru.netcrackeredu.Sivashchenko.Archiver;

public enum ArchiveMode {
    ARCHIVE("-a"),UNARCHIVE("-x"),SHOW_VERSION("-v"),SHOW_HELP("-h"),ADD("-i"),CREATE_WITH_COMMENTS("-ac"),ADD_COMMENTS("-ic"),READ_COMMENTS("-r");

    private String notation;

    ArchiveMode(String notation){
        this.notation = notation;
    }

    public String getNotation(){
        return notation;
    }

    public static ArchiveMode getMode(String flag) throws WrongModeException{
        for(ArchiveMode mode: ArchiveMode.values()){
            if (mode.getNotation().equals(flag)) return mode;
        }
        throw new WrongModeException(flag);
    }
}
