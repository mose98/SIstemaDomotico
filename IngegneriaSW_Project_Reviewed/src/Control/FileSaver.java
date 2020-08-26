package Control;

import java.io.File;

public class FileSaver {
    public static final String SYS_PROPERTY_TMPDIR = "java.io.tmpdir";

    public static boolean createFolder(String folderName){
        String wPath = System.getProperty(SYS_PROPERTY_TMPDIR);
        wPath=wPath+folderName;
        File folder = new File(wPath);

        if(folder.mkdirs()) return true;
        return false;
    }
}
