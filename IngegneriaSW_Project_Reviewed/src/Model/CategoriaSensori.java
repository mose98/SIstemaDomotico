package Model;

import Control.StaticVariables;

import java.util.ArrayList;
import java.util.List;

public class CategoriaSensori extends GenericInfo implements StaticVariables {
    private List<Rilevazioni> rilevazione= new ArrayList<>();
    private List<Sensori> listaSensori= new ArrayList<>();
    private List<String> listaNomiSensori= new ArrayList<>();

    public CategoriaSensori(String nome, String descrizione, List<Rilevazioni> rilevazione){
        this.descrizione=descrizione;
        this.nome=nome;
        this.rilevazione=rilevazione;
    }

    public static void printListCategoriaSensori(List<CategoriaSensori> listaCategoriaSensori){
        int index = 1;
        System.out.println("CATEGORIE SENSORI GIA' CREATE");
        for (CategoriaSensori sens : listaCategoriaSensori
        ) {
            System.out.println(index + ") " + sens.getNome() + ": " + sens.getDescrizione());
            index++;
        }
        System.out.println();
    }

    public static void printListCategoriaSensoriRimanenti(List<CategoriaSensori> listaCategoriaSensori){
        int index = 1;
        System.out.println("CATEGORIE SENSORI DISPONIBILI");
        for (CategoriaSensori sens : listaCategoriaSensori
        ) {
            System.out.println(index + ") " + sens.getNome() + ": " + sens.getDescrizione());
            index++;
        }
        System.out.println();
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public String getDescrizione() {
        return super.getDescrizione();
    }

    public void aggiungiSensore(Sensori sensore) {
        listaSensori.add(sensore);
    }

    public void aggiungiNomeSensore(String sensore) {
        listaNomiSensori.add(sensore);
    }

}
