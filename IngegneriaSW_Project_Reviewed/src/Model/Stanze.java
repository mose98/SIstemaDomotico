package Model;

import java.util.ArrayList;
import java.util.List;

public class Stanze extends GenericInfo{
    private List<Artefatti> listaArtefatti= new ArrayList<>();
    private List<Sensori> listaSensori= new ArrayList<>();
    private List<Attuatori> listaAttuatori= new ArrayList<>();
    private List<CategoriaSensori> categorieSensoriApplicati=new ArrayList<>();
    private List<CategoriaAttuatori> categorieAttuatoriApplicati=new ArrayList<>();

    public Stanze(String nome){
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

    public List<Artefatti> getListaArtefatti() {
        return listaArtefatti;
    }

    public List<Attuatori> getListaAttuatori() {
        return listaAttuatori;
    }

    public void aggiungiSensori(Sensori sens) {
        listaSensori.add(sens);
    }

    public void aggiungiAttuatori(Attuatori att) {
        listaAttuatori.add(att);
    }

    public void aggiungiArtefatti(Artefatti art) {
        listaArtefatti.add(art);
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

    public void aggiungiCategorieAttuatoriApplicati(CategoriaAttuatori categoriaAttuatoreApplicato) {
        categorieAttuatoriApplicati.add(categoriaAttuatoreApplicato);
    }
}
