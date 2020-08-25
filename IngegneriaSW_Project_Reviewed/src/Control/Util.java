package Control;

import mylib.InputDati;

import java.util.ArrayList;
import java.util.List;

public class Util implements StaticVariables {
    public static boolean noSpace(String name){
        if (name.equals(name.trim())) return true;
        return false;
    }

    public static boolean isOne(Object object, List list){
        if(!list.contains(object.toString())) {
            System.out.println(duplicatedErrorMsg);
            return true;
        }
        return false;
    }

    public static boolean noBlank(String name){
        if(!name.isBlank()) return true;
        return false;
    }

    public static boolean isLengthOK(String name, int length){
        if(name.length()<=length) return true;
        System.out.println("ATTENZIONE: la descrizione non può superare " + length + " caratteri!!!");
        return false;
    }

    public static void printNewLine(){
        System.out.println();
    }

    public static void printNewLineAndTitle(String title){
        System.out.println();
        System.out.print(title);
        System.out.println();
    }

    public static String readString(String testo){
        String nome;

        do {
            nome = InputDati.leggiStringa(testo);
            System.out.println();
        } while (!noSpace(nome) && !noBlank(nome));

        return nome;
    }

    public static String readStringUnique(String testo, List list){
        String nome;

        do {
            nome = InputDati.leggiStringa(testo);
            System.out.println();
        } while (!noSpace(nome) && !noBlank(nome) || !isOne(nome, list));

        return nome;
    }

    public static String readStringLength(String testo, int length){
        String nome;

        do {
            nome = InputDati.leggiStringa(testo);
            System.out.println();
        } while (!noSpace(nome) && !noBlank(nome) || !isLengthOK(nome, length));

        return nome;
    }

    public static void printTitleAndList(String title, List list){
        int index = 1;

        System.out.println(title);
        for (Object type : list
        ) {
            System.out.println(index + ") " + type.toString());
            index++;
        }

        System.out.println();
    }

    public static int chooseFromList(String testo, List list){
        int scelta = 0;

        do{
            scelta=InputDati.leggiIntero(testo);
            if(isInList(scelta, list)) System.out.println("ATTENZIONE: l'opzione selezionata non è presente nella lista!!!");
        }while(scelta < 1 || scelta > list.size());

        return scelta;
    }

    public static boolean isInList(int scelta, List list){
        if (scelta < 1 || scelta > list.size()) return false;

        return true;
    }

    public static char chooseInsert(String testo, char c1, char c2){
        char confirm;

        do {
            System.out.println(testo);
            confirm = InputDati.leggiChar("Premere l'opzione desiderata > ");
            System.out.println();
        } while (confirm != c1 && confirm != c2);

        return confirm;
    }

    public static int chooseInsertString(String testo, int max){
        int confirm;

        do {
            System.out.println(testo);
            confirm = InputDati.leggiIntero("Premere l'opzione desiderata > ");
            if (confirm < 1 || confirm > max)
                System.out.println("ATTENZIONE: l'opzione selezionata non è valida!!!");
            System.out.println();
        } while (confirm < 1 || confirm > max);

        return confirm;
    }


}
