package Model;

import Control.StaticVariables;

import java.util.ArrayList;
import java.util.List;

public class Artefatti extends GenericInfo implements StaticVariables {
    private List<Attuatori> listaAttuatori= new ArrayList<>();
    private List<Sensori> listaSensori= new ArrayList<>();
    private List<CategoriaSensori> categorieSensoriApplicati=new ArrayList<>();
    private List<CategoriaAttuatori> categorieAttuatoriApplicati=new ArrayList<>();

    public Artefatti(String nome){
        this.nome=nome;
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public String getDescrizione() {
        return super.getDescrizione();
    }

    @Override
    public void setDescrizione(String descrizione) {
        super.setDescrizione(descrizione);
    }

    public static void printArtefatti(List<Artefatti> list){
        int index = 1;
        System.out.println("LISTA ARTEFATTI");
        for (Artefatti art : list
        ) {
            System.out.println(index + ") " + art.getNome() + "     Posizione > " + art.getDescrizione());
            index++;
        }
        System.out.println();
    }

    public void aggiungiAttuatori(Attuatori attuatore) {
        listaAttuatori.add(attuatore);
    }

    public void aggiungiSensori(Sensori sensore) {
        listaSensori.add(sensore);
    }

    public List<Attuatori> getListaAttuatori() {
        return listaAttuatori;
    }

    public List<Sensori> getListaSensori() {
        return listaSensori;
    }

    public List<CategoriaSensori> getCategorieSensoriApplicati() {
        return categorieSensoriApplicati;
    }

    public void aggiungiCategorieSensoriApplicati(CategoriaSensori categoriaSensoreApplicato) {
        categorieSensoriApplicati.add(categoriaSensoreApplicato);
    }

    public List<CategoriaAttuatori> getCategorieAttuatoriApplicati() {
        return categorieAttuatoriApplicati;
    }

    public void aggiungiCategorieAttuatoriApplicati(CategoriaAttuatori categoriaArtefatteApplicato) {
        categorieAttuatoriApplicati.add(categoriaArtefatteApplicato);
    }
}
