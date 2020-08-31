package Control;

import Model.CategoriaSensori;
import Model.Rilevazioni;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ImportClass extends Util {
    public static CategoriaSensori importCatSens(String folderUnita){
        List<String> listCatSens = FileSaver.readFile(wPath+ File.separator+folderUnita+"/CategorieSensori.txt");

        printTitleAndList("Categorie di sensori importabili", listCatSens);
        int choice = chooseFromList("Scegli la categoria che vuoi importare", listCatSens)-1;

        String nome=listCatSens.get(choice).split(",")[0];
        String descrizione=listCatSens.get(choice).split(",")[1];

        File f = new File(wPath+ File.separator+folderUnita+File.separator+"CategorieSensori");
        List<Path> xx =FileSaver.displayDirectoryContents(f);

        String folderPath=null;
        for (Path p:xx
             ) {
            if(p.getFileName().toString()==nome) folderPath=p.toString();
        }

        List<String> stringRil= FileSaver.readFile(folderPath);
        Rilevazioni r;
        List<Rilevazioni> listRil= new ArrayList<>();

        for (String s:stringRil
             ) {
            if(s.split(",")[1]=="true"){
                r = new Rilevazioni(s.split(",")[0], Boolean.parseBoolean(s.split(",")[1]));
                r.setUnitaMisura(s.split(",")[2]);
                r.setMassimo(Integer.parseInt(s.split(",")[3]));
                r.setMinimo(Integer.parseInt(s.split(",")[4]));

            }
            else{
                r = new Rilevazioni(s.split(",")[0], Boolean.parseBoolean(s.split(",")[1]));
                for(int i=2; i<s.split(",").length;i++){
                    r.aggiungiDominioValori(s.split(",")[i]);
                }
            }
            listRil.add(r);
        }

        CategoriaSensori c = new CategoriaSensori(nome, descrizione, listRil);

        return c;
    }

    public static CategoriaSensori importCatAtt(String folderUnita){
        List<String> listCatAtt = FileSaver.readFile(wPath+ File.separator+folderUnita+"/CategorieSAttuatori.txt");

        printTitleAndList("Categorie di attuatori importabili", listCatAtt);
        int choice = chooseFromList("Scegli la categoria che vuoi importare", listCatAtt)-1;

        String nome= listCatAtt.get(choice).split(",")[0];
        String descrizione= listCatAtt.get(choice).split(",")[1];

        File f = new File(wPath+ File.separator+folderUnita+File.separator+"CategorieAttuatori");
        List<Path> xx =FileSaver.displayDirectoryContents(f);

        String folderPath=null;
        for (Path p:xx
        ) {
            if(p.getFileName().toString()==nome) folderPath=p.toString();
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        List<String> stringRil= FileSaver.readFile(folderPath);
        Rilevazioni r;
        List<Rilevazioni> listRil= new ArrayList<>();

        for (String s:stringRil
        ) {
            if(s.split(",")[1]=="true"){
                r = new Rilevazioni(s.split(",")[0], Boolean.parseBoolean(s.split(",")[1]));
                r.setUnitaMisura(s.split(",")[2]);
                r.setMassimo(Integer.parseInt(s.split(",")[3]));
                r.setMinimo(Integer.parseInt(s.split(",")[4]));

            }
            else{
                r = new Rilevazioni(s.split(",")[0], Boolean.parseBoolean(s.split(",")[1]));
                for(int i=2; i<s.split(",").length;i++){
                    r.aggiungiDominioValori(s.split(",")[i]);
                }
            }
            listRil.add(r);
        }

        CategoriaSensori c = new CategoriaSensori(nome, descrizione, listRil);

        return c;
    }
}
