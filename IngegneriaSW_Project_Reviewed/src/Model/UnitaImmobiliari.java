package Model;

import java.util.ArrayList;
import java.util.List;

public class UnitaImmobiliari extends GenericInfo{
    private List<Stanze> listastanza= new ArrayList<>();
    private List<Artefatti> artefattiEsterni= new ArrayList<>();
    private List<Artefatti> listaArtefattiUnita = new ArrayList<>();
    private List<Sensori> sensoriEsterni= new ArrayList<>();
    private List<Sensori> listaSensoriUnita = new ArrayList<>();
    private List<Attuatori> attuatoriEsterni= new ArrayList<>();
    private List<Attuatori> listaAttuatoriUnita = new ArrayList<>();
    private List<String> listaSensoriUnitaUbicazione = new ArrayList<>();
    private List<String> listaAttuatoriUnitaUbicazione = new ArrayList<>();

    public  UnitaImmobiliari(String nome, String descrizione){
        this.nome=nome;
        this.descrizione=descrizione;
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public String getDescrizione() {
        return super.getDescrizione();
    }

    public boolean checkStanze(){
        if(getListastanza().size()>0) return true;
        System.out.println();
        System.out.println("ATTENZIONE: non e' stata ancora creata nessuna stanza!!!");
        System.out.println();
        return false;
    }

    public boolean checkArtefatti(){
        if(getListaArtefattiUnita().size()>0) return true;
        System.out.println();
        System.out.println("ATTENZIONE: non e' stato ancora creato nessun artefatto!!!");
        System.out.println();
        return false;
    }

    public void printStanze(){
        int index = 1;
        System.out.println("LISTA STANZE");
        for (Stanze st : getListastanza()
        ) {
            System.out.println(index + ") " + st.getNome());
            index++;
        }
        System.out.println();
    }

    public static void printSensori(List<Sensori> list){
        int index = 1;
        System.out.println("LISTA SENSORI IN QUESTA UNITA'");
        for (Sensori sens : list
        ) {
            System.out.println(index + ") " + sens.getNome() + "     Posizione > " + sens.getDescrizione() + "        attivo = " + sens.getAttivo());
            index++;
        }
        System.out.println();
    }

    public static void printAttuatori(List<Attuatori> list){
        int index = 1;
        System.out.println("LISTA ATTUATORI IN QUESTA UNITA'");
        for (Attuatori att : list
        ) {
            System.out.println(index + ") " + att.getNome() + "     Posizione > " + att.getDescrizione() + "        attivo = " + att.getAttivo());
            index++;
        }
        System.out.println();
    }

    public void printArtefatti(){
        int index = 1;
        System.out.println("LISTA ARTEFATTI");
        for (Artefatti art : getListaArtefattiUnita()
        ) {
            System.out.println(index + ") " + art.getNome() + "      Posizione > " + art.getDescrizione());
            index++;
        }
    }

    public List<String> getListaAttuatoriUnitaUbicazione() {
        return listaAttuatoriUnitaUbicazione;
    }

    public List<String> getListaSensoriUnitaUbicazione() {
        return listaSensoriUnitaUbicazione;
    }

    public void aggiungiListaSensoriUnitaUbicazione(String stringa){
        listaSensoriUnitaUbicazione.add(stringa);
    }

    public void aggiungiListaAttuatoriUnitaUbicazione(String stringa){
        listaAttuatoriUnitaUbicazione.add(stringa);
    }

    public List<Stanze> getListastanza() {
        return listastanza;
    }

    public List<Artefatti> getArtefattiEsterni() {
        return artefattiEsterni;
    }

    public void aggiungiSensoriUnita(Sensori sensore){
        listaSensoriUnita.add(sensore);
    }

    public void aggiungiAttuatoriUnita(Attuatori attuatore){
        listaAttuatoriUnita.add(attuatore);
    }

    public void aggiungiArtefattoUnita(Artefatti artefatto){
        listaArtefattiUnita.add(artefatto);
    }

    public void aggiungiStanza(Stanze stanza) {
        listastanza.add(stanza);
    }

    public void aggiungiArtefattiEsterni(Artefatti artefattoEsterno) {
        artefattiEsterni.add(artefattoEsterno);
    }

    public void aggiungiAttuatoriEsterni(Attuatori attuatoreEsterno) {
        attuatoriEsterni.add(attuatoreEsterno);
    }

    public void aggiungiSensoriEsterni(Sensori sensoreEsterno) {
        sensoriEsterni.add(sensoreEsterno);
    }

    public List<Artefatti> getListaArtefattiUnita() {
        return listaArtefattiUnita;
    }

    public List<Attuatori> getAttuatoriEsterni() {
        return attuatoriEsterni;
    }

    public List<Attuatori> getListaAttuatoriUnita() {
        return listaAttuatoriUnita;
    }

    public List<Sensori> getListaSensoriUnita() {
        return listaSensoriUnita;
    }

    public List<Sensori> getSensoriEsterni() {
        return sensoriEsterni;
    }
}
