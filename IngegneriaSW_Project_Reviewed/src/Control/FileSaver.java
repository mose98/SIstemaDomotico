package Control;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static View.StaticVariables.wPath;

public class FileSaver {

    public static List<Path> xx = new ArrayList<>();

    public static boolean createFolder(String folderName) {
        String path = wPath + folderName;
        File folder = new File(path);

        if (folder.mkdirs()) return true;
        return false;
    }

    public static void createFile(String fileName) throws IOException {
        String path = wPath + File.separator + fileName;
        File f = new File(path + File.separator);

        f.createNewFile();
    }

    public static void newCatSensFile(String fileName, String nome, String descrizione) {
        String line = nome + "," + descrizione;

        write(fileName, line);
    }

    public static void newRilevazioneFile(String fileName, String nome, boolean isNumerica, List<String> valori, String unitaDiMisura, Integer minimo, Integer massimo) {
        String line = nome + " " + isNumerica;

        if (valori != null) {
            line = line + ",";

            for (String val : valori
            ) {
                line = line + " " + val;
            }
        } else {
            line = line + "," + unitaDiMisura + "," + minimo + "," + massimo;
        }

        write(fileName, line);
    }

    public static void newCatAttFile(String fileName, String nome, String descrizione) {
        String line = nome + "," + descrizione;

        write(fileName, line);
    }

    public static void newModalitaOperativa(String fileName, String nome) {
        String line = nome;

        write(fileName, nome);
    }

    public static void newParametro(String fileName, String nome) {

    }

    public static void write(String fileName, String line) {
        try {
            final Path path = Paths.get(fileName);
            Files.write(path, Arrays.asList(line), StandardCharsets.UTF_8,
                    Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
        } catch (final IOException ioe) {
            // Add your own exception handling...
        }
    }

    public static void newStanza(String fileName, String name) {
        String line = name;

        write(fileName, name);
    }

    public static void newArtefatto(String fileName, String name) {
        String line = name;

        write(fileName, name);
    }

    public static void newSensore(String fileName, String nome, String categoria) {
        String line = nome + "," + categoria;

        write(fileName, line);
    }

    public static void newAttuatore(String fileName, String nome, String categoria) {
        String line = nome + "," + categoria;

        write(fileName, line);
    }

    public static List<Path> displayDirectoryContents(File dir) {

        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {

                    xx.add(Path.of(file.getCanonicalPath()));
                    displayDirectoryContents(file);
                } else {
                    xx.add(Path.of(file.getCanonicalPath()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return xx;
    }

    public static String findPath(List<Path> pathList, String name){

        for (Path p:pathList
             ) {
            if(p.getFileName().toString()==name) return p.toString();
        }
        return null;
    }

    public static List<String> readFile(String fileName) {
        List<String> list= new ArrayList<>();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                list.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return list;
    }
}

