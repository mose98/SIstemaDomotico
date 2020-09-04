import Control.*;
import Control.CreatorRemove.ObjectRemoval;
import View.StaticVariables;
import Model.*;
import View.VistaParametri;
import mylib.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static Control.CreatorRemove.ObjectCreator.*;
import static Control.RuleController.createRegola;
import static Control.RuleController.createRegolaTime;

public class MainVersion implements StaticVariables {
    public static void main(String[] args) throws IOException {
        tipologieUnitaImmobiliari.add("Privato");
        tipologieUnitaImmobiliari.add("Commerciale");

        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String folderName = "SistemaDomotico_" + sdf.format(date);
        String folderUnitaName = null;
        FileSaver.createFolder(folderName);
        FileSaver.createFolder(folderName + File.separator + "CategorieSensori");
        FileSaver.createFolder(folderName + File.separator + "CategorieAttuatori");
        FileSaver.createFile(folderName + "/CategorieSensori.txt");
        FileSaver.createFile(folderName + "/CategorieAttuatori.txt");

        ObjectRemoval removeController = new ObjectRemoval();
        SistemaDomotico sistemaDomotico = new SistemaDomotico();
        int scelta, sceltaManut, sceltaFruit, sceltaModOperativa;
        MyMenu menuIniziale;
        MyMenu menuManutentore;
        MyMenu menuFruitore;
        char confirm;

        menuIniziale = new MyMenu(titolo, vociMenuI);

        Timer timer = new Timer();
        Timer timerRule2 = new Timer();

        timer.schedule(new ControlRule(), 0, 5000);
        timerRule2.schedule(new ControlRuleTime(), 0, 500);

        menuIniziale.stampaMenu();
        scelta = menuIniziale.scegli();
        boolean finishedManut = false;
        boolean finished = false;
        boolean finishedFruit = false;
        MENU:
        do {
            switch (scelta) {
                case 0:
                    finished = true;
                    menuIniziale.stampaMenu();
                    scelta = menuIniziale.scegli();
                    continue MENU;
                case 1:
                    menuManutentore = new MyMenu(titoloManut, vociMenuManut);
                    menuManutentore.stampaMenu();
                    sceltaManut = menuManutentore.scegli();
                    do {
                        switch (sceltaManut) {
                            case 0:
                                finishedManut = true;
                                menuIniziale.stampaMenu();
                                scelta = menuIniziale.scegli();
                                continue MENU;

                                //creazione unita'  immobiliare
                            case 1:
                                printNewLineAndTitle(titNewUnita);

                                do {
                                    UnitaImmobiliari unita = createUnitaImmobiliare();

                                    sistemaDomotico.getListUnitaImmobiliari().add(unita);
                                    printNewLineAndTitle(titEndNewUnita);

                                    folderUnitaName = folderName + File.separator + unita.getNome();
                                    FileSaver.createFolder(folderUnitaName);
                                    FileSaver.createFile(folderUnitaName + "/Stanze.txt");
                                    FileSaver.createFile(folderUnitaName + "/ArtefattiEsterni.txt");
                                    FileSaver.createFile(folderUnitaName + "/AttuatoriEsterni.txt");
                                    FileSaver.createFile(folderUnitaName + "/SensoriEsterni.txt");

                                    confirm = chooseInsert("Vuoi inserire una nuova unita' immobiliare?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                } while (confirm == 'y');
                                break;
                            //crea nuova categoria sensori
                            case 2:
                                printNewLineAndTitle(titNewCatSens);

                                do {
                                    CategoriaSensori categoriaSensore = createCategoriaSensore(folderName + File.separator + "CategorieSensori");
                                    printNewLineAndTitle(titEndNewCatSens);

                                    CategoriaSensori.printListCategoriaSensori(listaCategoriaSensori);

                                    FileSaver.newCatSensFile(wPath + File.separator + folderName + File.separator + "CategorieSensori.txt", categoriaSensore.getNome(), categoriaSensore.getDescrizione());

                                    confirm = chooseInsert("Vuoi inserire una nuova categoria di sensori?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                } while (confirm == 'y');
                                break;
                            //crea nuova categoria attuatori
                            case 3:
                                printNewLineAndTitle(titNewCatAtt);

                                do {
                                    CategoriaAttuatori categoriaAttuatore = createCategoriaAttuatore(folderName + File.separator + "CategorieAttuatori");

                                    printNewLineAndTitle(titEndNewCatAtt);

                                    CategoriaAttuatori.printListCategoriaAttuatori(listaCategoriaAttuatori);

                                    FileSaver.newCatAttFile(wPath + File.separator + folderName + File.separator + "CategorieAttuatori.txt", categoriaAttuatore.getNome(), categoriaAttuatore.getDescrizione());

                                    confirm = chooseInsert("Vuoi inserire una nuova categoria di attuatori?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                } while (confirm == 'y');
                                break;
                            //crea nuova stanza
                            case 4:
                                if (sistemaDomotico.checkUnita()) {
                                    printNewLineAndTitle(titNewStanza);

                                    do {
                                        createStanza(folderName);
                                        printNewLineAndTitle(titEndNewStanza);

                                        confirm = chooseInsert("Vuoi creare una nuova stanza?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    } while (confirm == 'y');
                                }
                                break;
                            //crea nuovo artefatto
                            case 5:
                                if (sistemaDomotico.checkUnita()) {
                                    printNewLineAndTitle(titNewArtefatto);

                                    do {
                                        createArtefatto(folderName);
                                        printNewLineAndTitle(titEndNewArtefatto);

                                        confirm = chooseInsert("Vuoi creare un nuovo artefatto?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    } while (confirm == 'y');
                                }
                                break;
                            //crea nuovo sensore
                            case 6:
                                if (sistemaDomotico.checkUnita()) {
                                    printNewLineAndTitle(titNewSensore);

                                    do {
                                        createSensore(folderName);
                                        printNewLineAndTitle(titEndNewSensore);

                                        confirm = chooseInsert("Vuoi creare un nuovo sensore?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    } while (confirm == 'y');
                                }
                                break;
                            //crea nuovo attuatore
                            case 7:
                                if (sistemaDomotico.checkUnita()) {
                                    printNewLineAndTitle(titNewAttuatore);

                                    do {
                                        createAttuatore(folderName);
                                        printNewLineAndTitle(titEndNewAttuatore);

                                        confirm = chooseInsert("Vuoi creare un nuovo attuatore?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    } while (confirm == 'y');
                                }
                                break;
                            //rimuovi stanze
                            case 8:
                                if (sistemaDomotico.checkUnita() && listaStanze.size() > 0) {
                                    confirm = chooseInsert("Sei sicuro di voler proseguire con la rimozione?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    if (confirm == 'n') break;

                                    printNewLineAndTitle(titDelStanza);

                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Selezionare l'unita' immobiliare dalla quale si vuole eliminare la stanza?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printStanze();
                                    Stanze stanzaSel = unitaSel.getListastanza().get(chooseFromList("Selezionare la stanza da rimuovere > ", unitaSel.getListastanza()));
                                    removeController.deleteObject(unitaSel, stanzaSel);

                                    printNewLineAndTitle(titEndDelStanza);
                                }
                                break;
                            //rimuovi artefatto
                            case 9:
                                if (sistemaDomotico.checkUnita() && listaArtefatti.size() > 0) {
                                    confirm = chooseInsert("Sei sicuro di voler proseguire con la rimozione?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    if (confirm == 'n') break;

                                    printNewLineAndTitle(titDelArtefatto);

                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Selezionare l'unita' immobiliare dalla quale si vuole eliminare l'artefatto?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printArtefatti();
                                    Artefatti artefattoSel = unitaSel.getListaArtefattiUnita().get(chooseFromList("Selezionare l'artefatto da rimuovere > ", unitaSel.getListaArtefattiUnita()));
                                    removeController.deleteObject(unitaSel, artefattoSel);

                                    printNewLineAndTitle(titEndDelArtefatto);
                                }
                                break;
                            //rimuovi sensore
                            case 10:
                                if (sistemaDomotico.checkUnita() && listaSensori.size() > 0) {
                                    confirm = chooseInsert("Sei sicuro di voler proseguire con la rimozione?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    if (confirm == 'n') break;

                                    printNewLineAndTitle(titDelSensore);

                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Selezionare l'unita' immobiliare dalla quale si vuole eliminare il sensore?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printSensori(unitaSel.getListaSensoriUnita());
                                    Sensori sensoreSel = unitaSel.getListaSensoriUnita().get(chooseFromList("Selezionare il sensore da rimuovere > ", unitaSel.getListaSensoriUnita()));
                                    removeController.deleteObject(unitaSel, sensoreSel);

                                    printNewLineAndTitle(titEndDelSensore);
                                }
                                break;
                            //rimuovi attuatore
                            case 11:
                                if (sistemaDomotico.checkUnita() && listaAttuatori.size() > 0) {
                                    confirm = chooseInsert("Sei sicuro di voler proseguire con la rimozione?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    if (confirm == 'n') break;

                                    printNewLineAndTitle(titDelAttuatore);

                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Selezionare l'unita' immobiliare dalla quale si vuole eliminare l'attuatore?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printSensori(unitaSel.getListaSensoriUnita());
                                    Attuatori attuatoreSel = unitaSel.getListaAttuatoriUnita().get(chooseFromList("Selezionare l'attuatore da rimuovere > ", unitaSel.getListaAttuatoriUnita()));
                                    removeController.deleteObject(unitaSel, attuatoreSel);

                                    printNewLineAndTitle(titEndDelAttuatore);
                                }
                                break;
                            //rimuovi categoria sensore
                            case 12:
                                if (sistemaDomotico.checkUnita() && listaCategoriaSensori.size() > 0) {
                                    confirm = chooseInsert("Sei sicuro di voler proseguire con la rimozione?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    if (confirm == 'n') break;

                                    printNewLineAndTitle(titDelCatSens);

                                    CategoriaSensori.printListCategoriaSensori(listaCategoriaSensori);
                                    CategoriaSensori catSensoreSel = listaCategoriaSensori.get(chooseFromList("Selezionare la categoria da rimuovere > ", listaCategoriaSensori));
                                    removeController.deleteObject(catSensoreSel);

                                    printNewLineAndTitle(titEndDelCatSens);
                                }
                                break;
                            //rimuovi categoria attuatore
                            case 13:
                                if (sistemaDomotico.checkUnita() && listaCategoriaAttuatori.size() > 0) {
                                    confirm = chooseInsert("Sei sicuro di voler proseguire con la rimozione?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    if (confirm == 'n') break;

                                    printNewLineAndTitle(titDelCatAtt);

                                    CategoriaAttuatori.printListCategoriaAttuatori(listaCategoriaAttuatori);
                                    CategoriaAttuatori catAttuatoreSel = listaCategoriaAttuatori.get(chooseFromList("Selezionare la categoria da rimuovere > ", listaCategoriaAttuatori));
                                    removeController.deleteObject(catAttuatoreSel);

                                    printNewLineAndTitle(titEndDelCatAtt);
                                }
                                break;
                            //visualizza categorie sensori
                            case 14:
                                printNewLineAndTitle(titViewCatSens);
                                if (CategoriaSensori.checkCategorieSensori())
                                    CategoriaSensori.printListCategoriaSensori(listaCategoriaSensori);
                                break;
                            //visualizza categorie attuatori
                            case 15:
                                printNewLineAndTitle(titViewCatAtt);
                                if (CategoriaAttuatori.checkCategorieAttuatori())
                                    CategoriaAttuatori.printListCategoriaAttuatori(listaCategoriaAttuatori);
                                break;
                            //visualizza unita immobiliare
                            case 16:
                                int index = 1;
                                if (sistemaDomotico.checkUnita()) {
                                    printNewLineAndTitle(titViewAllUnita);
                                    for (UnitaImmobiliari unita : sistemaDomotico.getListUnitaImmobiliari()
                                    ) {
                                        System.out.println(index + ") " + unita.getNome().toUpperCase() + "     " + unita.getDescrizione());
                                        unita.printStanze();
                                        unita.printArtefatti();
                                        UnitaImmobiliari.printSensori(unita.getListaSensoriUnita());
                                        UnitaImmobiliari.printAttuatori(unita.getListaAttuatoriUnita());

                                        System.out.println("---------------------------------------------------------------------------");
                                    }
                                }
                                //visualizza sensori
                            case 17:
                                if (listaSensori.size() > 0) {
                                    printNewLineAndTitle(titViewSens);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare i sensori?", listaUnitaImmobiliari) - 1);

                                    UnitaImmobiliari.printSensori(unitaSel.getListaSensoriUnita());
                                }
                                break;
                            //visualizza attuatori
                            case 18:
                                if (listaAttuatori.size() > 0) {
                                    printNewLineAndTitle(titViewAtt);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare gli attuatori?", listaUnitaImmobiliari) - 1);

                                    UnitaImmobiliari.printAttuatori(unitaSel.getListaAttuatoriUnita());
                                }
                                break;
                            //visualizza artefatti
                            case 19:
                                if (listaArtefatti.size() > 0) {
                                    printNewLineAndTitle(titViewListArt);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare gli artefatti?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printArtefatti();
                                }
                                break;
                            //visualizza stanze
                            case 20:
                                if (listaStanze.size() > 0) {
                                    printNewLineAndTitle(titViewListStanze);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare le stanze?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printStanze();
                                }
                                break;
                            //importa categoria sensore
                            case 21:
                                listaCategoriaSensori.add(ImportClass.importCatSens(folderName));
                                break;
                            //importa categoria attuatore
                            case 22:
                                listaCategoriaAttuatori.add(ImportClass.importCatAtt(folderName));
                                break;
                            //importa unità immobiliare
                            case 23:
                                listaUnitaImmobiliari.add(ImportClass.importUnita(folderName));
                                break;
                            //importa regole
                            case 24:
                                break;
                        }
                        break;
                    } while (finishedManut);
                    break;

                case 2:
                    menuFruitore = new MyMenu(titoloFruit, vociMenuFruit);
                    menuFruitore.stampaMenu();
                    sceltaFruit = menuFruitore.scegli();
                    do {
                        switch (sceltaFruit) {
                            case 0:
                                int index = 0;
                                finishedFruit = true;
                                menuIniziale.stampaMenu();
                                scelta = menuIniziale.scegli();
                                continue MENU;
                                //visualizza categorie sensori
                            case 1:
                                if (CategoriaSensori.checkCategorieSensori()){
                                    printNewLineAndTitle(titViewCatSens);
                                    CategoriaSensori.printListCategoriaSensori(listaCategoriaSensori);
                                }
                                break;
                            //visualizza categorie attuatori
                            case 2:
                                if (CategoriaAttuatori.checkCategorieAttuatori()) {
                                    printNewLineAndTitle(titViewCatAtt);
                                    CategoriaAttuatori.printListCategoriaAttuatori(listaCategoriaAttuatori);
                                }
                                break;
                            //visualizza unita immobiliare
                            case 3:
                                index = 1;
                                if (sistemaDomotico.checkUnita()) {
                                    printNewLineAndTitle(titViewAllUnita);

                                    for (UnitaImmobiliari unita : sistemaDomotico.getListUnitaImmobiliari()
                                    ) {
                                        System.out.println(index + ") " + unita.getNome().toUpperCase() + "     " + unita.getDescrizione());
                                        unita.printStanze();
                                        unita.printArtefatti();
                                        UnitaImmobiliari.printSensori(unita.getListaSensoriUnita());
                                        UnitaImmobiliari.printAttuatori(unita.getListaAttuatoriUnita());

                                        System.out.println("---------------------------------------------------------------------------");
                                    }
                                }
                                //visualizza sensori
                            case 4:
                                if (listaSensori.size() > 0) {
                                    printNewLineAndTitle(titViewSens);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare i sensori?", listaUnitaImmobiliari) - 1);

                                    UnitaImmobiliari.printSensori(unitaSel.getListaSensoriUnita());
                                }
                                break;
                            //visualizza attuatori
                            case 5:
                                if (listaAttuatori.size() > 0) {
                                    printNewLineAndTitle(titViewAtt);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare gli attuatori?", listaUnitaImmobiliari) - 1);

                                    UnitaImmobiliari.printAttuatori(unitaSel.getListaAttuatoriUnita());
                                }
                                break;
                            //visualizza artefatti
                            case 6:
                                if (listaArtefatti.size() > 0) {
                                    printNewLineAndTitle(titViewListArt);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare gli artefatti?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printArtefatti();
                                }
                                break;
                            //visualizza stanze
                            case 7:
                                if (listaStanze.size() > 0) {
                                    printNewLineAndTitle(titViewListStanze);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare le stanze?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printStanze();
                                }
                                break;
                            //visualizza valori sensori
                            case 8:
                                if(listaSensori.size()>0){
                                    printNewLineAndTitle(titViewSensValue);
                                    VistaParametri.ValoriSensori();
                                }
                                break;
                            //assegna parametri modalità operative
                            case 9:
                                if(listaAttuatori.size()>0){
                                    printNewLineAndTitle(titModAttValue);
                                    VistaParametri.AssegnaParametri();
                                }
                                break;
                            //creazione regole
                            case 10:
                                if(listaUnitaImmobiliari.size()>0){
                                    printNewLineAndTitle(titNewRule);
                                    printNewLineAndTitle(descNewRule);
                                    createRegola();
                                    printNewLineAndTitle(titEndNewRule);
                                }
                                break;
                            //visualizza regole applicate
                            case 11:
                                if(listaRegoleApplicate.size()>0){
                                    printTitleAndList("Regole applicate", listaRegoleApplicate);
                                }
                                break;
                            //visualizza regole create
                            case 12:
                                if(listaRegole.size()>0){
                                    printTitleAndList("Lista regole create", listaNomiRegole);
                                }
                                break;
                            //creazione regole tempoDipendenti
                            case 13:
                                if(listaUnitaImmobiliari.size()>0){
                                    printNewLineAndTitle(titNewRuleT);
                                    printNewLineAndTitle(descNewRuleT);
                                    createRegolaTime();
                                    printNewLineAndTitle(titEndNewRuleT);
                                }
                                break;
                            //modifica stato sensore
                            case 14:
                                if(listaSensori.size()>0){
                                    printNewLineAndTitle(titModStSens);
                                    VistaParametri.AttivazioneSensori();
                                    printNewLineAndTitle(titEndModStato);
                                }
                                break;
                            //modifica stato attuatore
                            case 15:
                                if(listaAttuatori.size()>0){
                                    printNewLineAndTitle(titModStAtt);
                                    VistaParametri.AttivazioneAttuatori();
                                    printNewLineAndTitle(titEndModStato);
                                }
                                break;
                            //modifica stato regole
                            case 16:
                                if(listaRegole.size()>0){
                                    printNewLineAndTitle(titModStRule);
                                    VistaParametri.AttivazioneRegole();
                                    printNewLineAndTitle(titEndModStato);
                                }
                                break;
                            //importa categorie sensori
                            case 21:
                                listaCategoriaSensori.add(ImportClass.importCatSens(folderName));
                                break;
                            //importa categorie attuatori
                            case 22:
                                listaCategoriaAttuatori.add(ImportClass.importCatAtt(folderName));
                                break;
                            //importa unità immobiliari
                            case 23:
                                listaUnitaImmobiliari.add(ImportClass.importUnita(folderName));
                                break;
                        }
                        break;
                    } while (!finishedFruit);
            }
        } while (!finished);
    }
}

class ControlRule extends TimerTask {

    private boolean ifAntecedenteTrue(Object fattore1, String operatore, Object fattore2) {
        if (operatore == "<") {
            if ((int) fattore1 < (int) fattore2) return true;
        } else if (operatore == "<=") {
            if ((int) fattore1 <= (int) fattore2) return true;
        } else if (operatore == ">") {
            if ((int) fattore1 > (int) fattore2) return true;
        } else if (operatore == ">=") {
            if ((int) fattore1 >= (int) fattore2) return true;
        } else if (operatore == "==") {
            if ((int) fattore1 == (int) fattore2) return true;
            else if ((String) fattore1 == (String) fattore2) return true;
        }
        return false;
    }

    @Override
    public void run() {
        Rilevazioni fattore1;
        String operatore;
        Rilevazioni fattore2ril = null;
        int fattore2int = -99999;
        String fattore2str = null;
        Object value1 = null;
        Object value2 = null;

        if (listaRegole.size() > 0) {
            for (Regole rule : listaRegole
            ) {
                Antecedente antecedente = rule.getAntecedente();
                fattore1 = antecedente.getAnt_fattore1();
                operatore = antecedente.getAnt_operatore1();
                fattore2ril = antecedente.getAnt_fattore2();
                fattore2str = antecedente.getAnt_stfattore2();
                fattore2int = antecedente.getAnt_fattoreCost2();

                int max1 = 0;
                int min1 = 0;
                if (fattore1.getNumerica()) {
                    max1 = fattore1.getMassimo();
                    min1 = fattore1.getMinimo();
                    value1 = (int) (Math.random() * max1 + min1);
                } else {
                    int size = fattore1.getDominioValori().size();
                    int valore = (int) (Math.random() * size);
                    String valoreReg = fattore1.getDominioValori().get(valore);
                    value1 = valoreReg;
                }

                int max2 = 0;
                int min2 = 0;
                if (fattore2ril != null) {
                    max2 = fattore2ril.getMassimo();
                    min2 = fattore2ril.getMinimo();
                    value2 = (int) (Math.random() * max2 + min2);
                }
                if (fattore2int != -9999) value2 = fattore2int;
                if (fattore2str != null) value2 = fattore2str;
                if(value1!=null && value2!=null && operatore!=null) {
                    if (ifAntecedenteTrue(value1, operatore, value2) == true) {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                        rule.setTime(LocalTime.now().format(dtf));
                        listaRegoleApplicate.add("AGGIORNAMENTO: la regola '" + rule.getNome() + "' e' stata applicata alle " + rule.getTime());
                    }
                }
            }
        }
    }
}

class ControlRuleTime extends TimerTask {

    private boolean ifAntecedenteTrueT(Calendar time, String operatore) {

        if (operatore == "<") {
            if (time.get(Calendar.HOUR_OF_DAY) <= LocalTime.now().getHour() && time.get(Calendar.MINUTE) < LocalTime.now().getMinute())
                return true;
        } else if (operatore == ">") {
            if (time.get(Calendar.HOUR_OF_DAY) >= LocalTime.now().getHour() && time.get(Calendar.MINUTE) > LocalTime.now().getMinute())
                return true;
        } else if (operatore == "==") {
            if (time.get(Calendar.HOUR_OF_DAY) == LocalTime.now().getHour() && time.get(Calendar.MINUTE) == LocalTime.now().getMinute())
                return true;
        }
        return false;
    }

    @Override
    public void run() {
        Calendar time;
        String operatore;

        if (listaRegole.size() > 0) {
            for (Regole rule : listaRegole
            ) {
                Antecedente antecedente = rule.getAntecedente();
                time = antecedente.getTime();
                operatore=antecedente.getAnt_operatore1();
                if(time!=null) {
                    if (ifAntecedenteTrueT(time, operatore) == true) {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                        rule.setTime(LocalTime.now().format(dtf));
                        listaRegoleApplicate.add("AGGIORNAMENTO: la regola '" + rule.getNome() + "' e' stata applicata alle " + rule.getTime());
                    }
                }
            }
        }
    }
}