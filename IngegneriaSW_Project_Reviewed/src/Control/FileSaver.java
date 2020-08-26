package Control;

import java.io.*;

public class FileSaver {
    public static final String SYS_PROPERTY_TMPDIR = "java.io.tmpdir";
    public static String wPath = System.getProperty(SYS_PROPERTY_TMPDIR);

    public static boolean createFolder(String folderName){
        String path=wPath+folderName;
        File folder = new File(path);

        if(folder.mkdirs()) return true;
        return false;
    }

    public static void createFile(String fileName) throws IOException {
        String path=wPath+File.separator+fileName;
        File f = new File(path+File.separator);

        f.createNewFile();
    }

    //public static void newCatSensFile(String )
}
