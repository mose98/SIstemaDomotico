package View;

import Control.Regole;
import Model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface StaticVariables {
    List<String> tipologieUnitaImmobiliari = new ArrayList<>();
    List<CategoriaSensori> listaCategoriaSensori = new ArrayList<>();
    List<String> listaNomiCategoriaSensori = new ArrayList<>();
    List<CategoriaAttuatori> listaCategoriaAttuatori = new ArrayList<CategoriaAttuatori>();
    List<String> listaNomiCategoriaAttuatori = new ArrayList<>();
    List<Stanze> listaStanze = new ArrayList<>();
    List<String> listaNomiStanze = new ArrayList<>();
    List<Artefatti> listaArtefatti = new ArrayList<>();
    List<String> listaNomiArtefatti = new ArrayList<>();
    List<UnitaImmobiliari> listaUnitaImmobiliari = new ArrayList<>();
    List<String> listaNomiUnitaImmobiliari = new ArrayList<>();
    List<Sensori> listaSensori = new ArrayList<>();
    List<String> listaNomiSensori = new ArrayList<>();
    List<Attuatori> listaAttuatori = new ArrayList<>();
    List<String> listaNomiAttuatori = new ArrayList<>();
    List<CategoriaSensori> categorieSensoriRimanenti = new ArrayList<>();
    List<CategoriaAttuatori> categorieAttuatoriRimanenti = new ArrayList<>();
    List<Regole> listaRegole = new ArrayList<>();
    List<String> listaNomiRegole = new ArrayList<>();
    List<String> listaRegoleApplicate = new ArrayList<>();
    List<String> listaRegoleFormat = new ArrayList<>();
    String[] operatori = new String[]{">", ">=", "<", "<=", "=="};
    int maxlength = 80;
    String SYS_PROPERTY_TMPDIR = "java.io.tmpdir";
    String wPath = System.getProperty(SYS_PROPERTY_TMPDIR);
    String blankSPaceErrorMsg = "ATTENZIONE: LA STRINGA NON PUO' ESSERE VUOTA E NON PUO' CONTENERE SPAZI";
    String duplicatedErrorMsg = "ATTENZIONE: IL NOME INSERITO E' GIA' PRESENTE NELLA LISTA";
    String[] vociMenuI = new String[]{"MODALITA' MANUTENTORE", "MODALITA' FRUITORE"};
    //voci del menu del manutentore
    String[] vociMenuManut = new String[]{"CREA UNITA' IMMOBILIARE", "CREA CATEGORIA SENSORE", "CREA CATEGORIA ATTUATORE", "CREA NUOVA STANZA",
            "CREA NUOVO ARTEFATTO", "CREA NUOVO SENSORE", "CREA NUOVO ATTUATORE", "RIMUOVI STANZA", "RIMUOVI ARTEFATTO", "RIMUOVI SENSORE",
            "RIMUOVI ATTUATORE", "RIMUOVI CATEGORIA SENSORE", "RIMUOVI CATEGORIA ATTUATORE",
            "VISUALIZZA CATEGORIE SENSORI", "VISUALIZZA CATEGORIE ATTUATORI", "VISUALIZZA UNITA' IMMOBILIARE",
            "VISUALIZZA LISTA SENSORI", "VISUALIZZA LISTA ATTUATORI", "VISUALIZZA LISTA ARTEFATTI", "VISUALIZZA LISTA STANZE",
            "IMPORTA CATEGORIE SENSORI", "IMPORTA CATEGORIE ATTUATORI", "IMPORTA UNITA' IMMOBILIARI", "IMPORTA REGOLE"};
    //voci del menu del fruitore
    String[] vociMenuFruit = new String[]{"VISUALIZZA CATEGORIE SENSORI", "VISUALIZZA CATEGORIE ATTUATORI", "VISUALIZZA UNITA' IMMOBILIARE",
            "VISUALIZZA LISTA SENSORI", "VISUALIZZA LISTA ATTUATORI", "VISUALIZZA LISTA ARTEFATTI", "VISUALIZZA LISTA STANZE", "VISUALIZZA VALORI SENSORI",
            "ASSEGNA MODALITA' OPERATIVE PARAMETRICHE", "CREA REGOLE", "VISUALIZZA REGOLE APPLICATE", "VISUALIZZA REGOLE CREATE", "CREA REGOLE TEMPO-DIPENDENTI",
            "MODIFICA STATO SENSORI", "MODIFICA STATO ATTUATORI", "MODIFICA STATO REGOLE"};

    String titolo = "BENVENUTO NEL MENU INIZIALE DELLA PRIMA VERSIONE \n Scegli la modalita' con la quale vuoi proseguire";
    String titoloManut = "ATTIVO COME MODALITA' MANUTENTORE";
    String titoloFruit = "ATTIVO COME MODALITA' FRUITORE";

    String titNewUnita = "CREAZIONE NUOVA UNITA' IMMOBILIARE \n------------------------------------------------------";
    String titNewCatSens = "CREAZIONE NUOVA CATEGORIA SENSORI \n------------------------------------------------------";
    String titNewCatAtt = "CREAZIONE NUOVA CATEGORIA  ATTUATORI\n------------------------------------------------------";
    String titNewStanza = "CREAZIONE NUOVA STANZA \n------------------------------------------------------";
    String titNewArtefatto = "CREAZIONE NUOVO ARTEFATTO \n------------------------------------------------------";
    String titNewSensore = "CREAZIONE NUOVO SENSORE \n------------------------------------------------------";
    String titNewAttuatore = "CREAZIONE NUOVO ATTUATORE \n------------------------------------------------------";
    String titDelStanza = "RIMOZIONE STANZA \n------------------------------------------------------";
    String titDelArtefatto = "RIMOZIONE ARTEFATTO \n------------------------------------------------------";
    String titDelSensore = "RIMOZIONE SENSORE \n------------------------------------------------------";
    String titDelAttuatore = "RIMOZIONE ATTUATORE \n------------------------------------------------------";
    String titDelCatSens = "RIMOZIONE CATEGORIA SENSORE \n------------------------------------------------------";
    String titDelCatAtt = "RIMOZIONE CATEGORIA ATTUATORE \n------------------------------------------------------";
    String titViewAllUnita = "VISUALIZZAZIONE UNITA' IMMOBILIARE \n------------------------------------------------------";
    String titViewSens = "VISUALIZZAZIONE LISTA SENSORI \n------------------------------------------------------";
    String titViewCatSens = "VISUALIZZAZIONE LISTA CATEGORIE SENSORI \n------------------------------------------------------";
    String titViewCatAtt = "VISUALIZZAZIONE LISTA CATEGORIE ATTUATORI \n------------------------------------------------------";
    String titViewAtt = "VISUALIZZAZIONE LISTA ATTUATORI \n------------------------------------------------------";
    String titViewSensValue = "VISUALIZZAZIONE VALORI SENSORI \n------------------------------------------------------";
    String titModAttValue = "ASSEGNAMENTO PARAMETRO ALLA MODALITA' OPERATIVA \n------------------------------------------------------";
    String titViewListStanze = "VISUALIZZAZIONE LISTA STANZE \n------------------------------------------------------";
    String titViewListArt = "VISUALIZZAZIONE LISTA ARTEFATTI \n------------------------------------------------------";
    String titNewRule = "CREAZIONE REGOLA \n------------------------------------------------------";
    String titNewRuleT = "CREAZIONE REGOLA TEMPO-DIPENDENTE \n------------------------------------------------------";
    String titModStSens = "MODIFICA STATO SENSORI \n------------------------------------------------------";
    String titModStAtt = "MODIFICA STATO ATTUATORI \n------------------------------------------------------";
    String titModStRule = "MODIFICA STATO REGOLE \n------------------------------------------------------";

    String titEndNewUnita = "unita'  immobiliare creata correttamente \n------------------------------------------------------";
    String titEndNewCatSens = "categoria sensore correttamente \n------------------------------------------------------";
    String titEndNewCatAtt = "categoria attuatore creata correttamente \n------------------------------------------------------";
    String titEndNewStanza = "stanza creata correttamente \n------------------------------------------------------";
    String titEndNewArtefatto = "artefatto creato correttamente \n------------------------------------------------------";
    String titEndNewSensore = "sensore creato correttamente \n------------------------------------------------------";
    String titEndNewAttuatore = "attuatore creato correttamente \n------------------------------------------------------";
    String titEndDelStanza = "stanza eliminata correttamente \n------------------------------------------------------";
    String titEndDelArtefatto = "artefatto eliminato correttamente \n------------------------------------------------------";
    String titEndDelSensore = "sensore eliminato correttamente \n------------------------------------------------------";
    String titEndDelAttuatore = " attuatore eliminato correttamente \n------------------------------------------------------";
    String titEndDelCatSens = "categoria sensore eliminato correttamente \n------------------------------------------------------";
    String titEndDelCatAtt = "categoria attuatore eliminato correttamente \n------------------------------------------------------";
    String titEndNewRule = "regola creata correttamente \n------------------------------------------------------";
    String titEndNewRuleT = "regola creata correttamente \n------------------------------------------------------";
    String titEndModStato = "stato modificato correttamente \n------------------------------------------------------";
    String descNewRule = "la struttura della regola è composta nella seguente maniera   " +
            "\n     if 'antecedente' then 'conseguente' \nin base al risultato dell'antecedente, si avrà un conseguente " +
            "\ncostruisci il tuo antecedente e conseguente per impostare parametri agli attuatori sulla base dele rilevazioni dei sensori";
    String descNewRuleT = "la struttura della regola è composta nella seguente maniera   " +
            "\n     if 'time' then 'start' \nin base al risultato dell'antecedente, si avrà un conseguente " +
            "\ncostruisci il tuo antecedente e conseguente per impostare parametri agli attuatori in modo automatico ad una certa ora";
}
