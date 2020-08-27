package Control;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static View.StaticVariables.wPath;

public class FileSaver {


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

    public static void newCatSensFile(String fileName, String nome, String descrizione){
        String line = nome+","+descrizione;

        write(fileName, line);
    }

    public static void newRilevazioneFile(String fileName, String nome, boolean isNumerica, List<String> valori, String unitaDiMisura, Integer minimo, Integer massimo){
        String line = nome+" "+ isNumerica;

        if(valori!=null) {
            line=line+",";

            for (String val : valori
            ) {
                line = line + " " + val;
            }
        }
        else {
            line = line + "," + unitaDiMisura + "," + minimo + "," + massimo;
        }

        write(fileName, line);
    }

    public static void newCatAttFile(String fileName, String nome, String descrizione){
        String line = nome+","+descrizione;

        write(fileName, line);
    }

    public static void newModalitaOperativa(String fileName, String nome){


    }

    public static void newParametro(String fileName, String nome){

    }

    public static void write(String fileName, String line){
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
            out.println(line);
            out.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
