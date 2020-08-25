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

    public static String readString(String testo, List list){
        String nome;

        do {
            nome = InputDati.leggiStringa(testo);
            System.out.println();
        } while (!noSpace(nome) && !noBlank(nome) || !isOne(nome, list));

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

    public static char reinsert(String testo){
        char confirm;

        do {
            System.out.println("testo + \npremere 'y' per confermare\npremere 'n' per tornare al menu precedente");
            confirm = InputDati.leggiChar("Premere l'opzione desiderata > ");
            System.out.println();
        } while (confirm != 'y' && confirm != 'n');

        return confirm;
    }
}
