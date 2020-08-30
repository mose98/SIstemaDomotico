package Model;

import Control.FileSaver;
import View.Util;
import mylib.InputDati;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Rilevazioni extends Util{
    String nome;
    String unitaDiMisura;
    int massimo;
    int minimo;
    boolean isNumerica;
    ArrayList<String> dominioValori = new ArrayList<>();

    public Rilevazioni(String nome, boolean isNumerica){
        this.nome=nome;
        this.isNumerica=isNumerica;
    }

    public static Rilevazioni newRilevazione(char chooseType, String nome){
        Rilevazioni rilevazioneSens;
        if (chooseType == '1') {
            rilevazioneSens = new Rilevazioni(nome, true);

            String unitamisuraSens = readString("Inserisci l'unita'  di misura della rilevazione > ");

            rilevazioneSens.setUnitaMisura(unitamisuraSens);

            int minimo = InputDati.leggiIntero("Inserisci il minimo valore rilevabile dal sensore > ");
            rilevazioneSens.setMinimo(minimo);
            printNewLine();
            int massimo;
            do {
                massimo = InputDati.leggiIntero("Inserisci il massimo valore rilevabile dal sensore > ");
                System.out.println();
            } while (massimo <= minimo);
            rilevazioneSens.setMassimo(massimo);
        } else {
            char confirm;
            rilevazioneSens = new Rilevazioni(nome, false);

            do {
                String stringaDominio = readStringUnique("Aggiungi un valore al dominio rilevabile > ", rilevazioneSens.getDominioValori());
                rilevazioneSens.aggiungiDominioValori(stringaDominio);

                confirm = chooseInsert("Vuoi inserire un altro valore al domminio?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
            } while (confirm == 'y');
        }

        return rilevazioneSens;
    }

    public String getNome() {
        return nome;
    }

    public boolean getNumerica(){
        return isNumerica;
    }

    public int getMassimo() {
        return massimo;
    }

    public int getMinimo() {
        return minimo;
    }

    public String getUnitaMisura() {
        return unitaDiMisura;
    }

    public void setMassimo(int massimo) {
        this.massimo = massimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public void setUnitaMisura(String unitaMisura) {
        this.unitaDiMisura = unitaMisura;
    }

    public List<String> getDominioValori() {
        return dominioValori;
    }

    public void aggiungiDominioValori(String valore) {
        dominioValori.add(valore);
    }

}
