package Model;

import java.util.ArrayList;
import java.util.List;

public class SistemaDomotico {
    List<UnitaImmobiliari> listUnitaImmobiliari= new ArrayList<UnitaImmobiliari>();

    public SistemaDomotico(){}

    public List<UnitaImmobiliari> getListUnitaImmobiliari() {
        return listUnitaImmobiliari;
    }

    public boolean checkUnita(){
        if(getListUnitaImmobiliari().size()>0) return true;
        System.out.println();
        System.out.println("ATTENZIONE: non e' stata ancora creata nessuna unita'  immobiliare \ncreare almeno una unita'  immobiliare prima di creare una stanza");
        System.out.println();
        return false;
    }

    public static void printListUnita(List<UnitaImmobiliari> listaUnitaImmobiliari){
        int index = 1;
        System.out.println("LISTA UNITA' IMMOBILIARI");
        for (UnitaImmobiliari unita : listaUnitaImmobiliari
        ) {
            System.out.println(index + ") " + unita.getNome() + "    Tipologia > " + unita.getDescrizione());
            index++;
        }
        System.out.println();
    }
}
