import Control.StaticVariables;
import Control.Util;
import Model.UnitaImmobiliari;
import mylib.*;

import java.util.ArrayList;

public class MainVersion extends Util implements StaticVariables {
    public static void main(String[] args) {

        int scelta, sceltaManut, sceltaFruit, sceltaModOperativa;
        MyMenu menuIniziale;
        MyMenu menuManutentore;
        MyMenu menuFruitore;
        char confirm;

        menuIniziale = new MyMenu(titolo, vociMenuI);
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
                                    String nomeUnita = readString("Dai un nome all'unità immobiliare > ", listaNomiUnitaImmobiliari);

                                    printTitleAndList("Tipologie di unita' immobiliari", tipologieUnitaImmobiliari);

                                    int tipoUnita = chooseFromList("Scegliere il tipo di unita' immobiliare tra quelle proposte di sopra > ", tipologieUnitaImmobiliari) -1;

                                    UnitaImmobiliari unita = new UnitaImmobiliari(nomeUnita, tipologieUnitaImmobiliari.get(tipoUnita));

                                    listaNomiUnitaImmobiliari.add(unita.getNome());
                                    listaUnitaImmobiliari.add(unita);

                                    printNewLineAndTitle(titEndNewUnita);

                                    confirm = reinsert("Vuoi inserire un'altra unita' immobiliare?");
                                    do {
                                        System.out.println("Vuoi inserire un'altra unita'  immobiliare?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente");
                                        confirm = InputDati.leggiChar("Premere l'opzione desiderata > ");
                                        System.out.println();
                                    } while (confirm != 'y' && confirm != 'n');

                                } while (confirm == 'y');
                                break;
                            //crea nuova categoria sensori
                            case 2:
                                String nomeCatSens;
                                String descrizioneCatSens;
                                String nomeRilevazione;
                                String unitamisuraSens;
                                int minimo;
                                int massimo;
                                char chooseType;
                                boolean type;
                                String stringaDominio;
                                Rilevazioni rilevazioneSens;

                                System.out.println();
                                System.out.println(titNewCatSens);

                                do {
                                    listaNomiInfoRilevabili = new ArrayList<>();
                                    listaInfoRilevabili = new ArrayList<>();

                                    do {
                                        nomeCatSens = InputDati.leggiStringa("Dai un nome alla categoria di sensori (senza spazi) > ");
                                        System.out.println();
                                    } while (isBlankorWhitespace(nomeCatSens) || !isUnivoque(listaNomiCategoriaSensori, nomeCatSens));

                                    do {
                                        descrizioneCatSens = InputDati.leggiStringa("Inserisci la descrizione > ");
                                        if (descrizioneCatSens.length() > maxlength || descrizioneCatSens.isEmpty() || descrizioneCatSens.isBlank())
                                            System.out.println("ATTENZIONE: la descrizione non può superare " + maxlength + " caratteri");
                                        System.out.println();
                                    } while (descrizioneCatSens.length() > maxlength || descrizioneCatSens.isEmpty() || descrizioneCatSens.isBlank());

                                    do {
                                        do {
                                            nomeRilevazione = InputDati.leggiStringa("Inserisci l'informazione rilevabile dal sensore > ");
                                            System.out.println();
                                        } while (isBlankorWhitespace(nomeRilevazione) || !isUnivoque(listaNomiInfoRilevabili, nomeRilevazione));

                                        listaNomiInfoRilevabili.add(nomeRilevazione);

                                        do {
                                            System.out.println("L'informazione rilevata e' numerica o non  numerica?\npremere '1' per la numerica\npremere '2' per non numerica");
                                            chooseType = InputDati.leggiChar("Premere l'opzione desiderata > ");
                                            System.out.println();
                                        } while (chooseType != '1' && chooseType != '2');

                                        if (chooseType == '1') {
                                            type = true;

                                            rilevazioneSens = new Rilevazioni(nomeRilevazione, type);

                                            do {
                                                unitamisuraSens = InputDati.leggiStringa("Inserisci l'unita'  di misura della rilevazione > ");
                                                System.out.println();
                                            } while (isBlankorWhitespace(unitamisuraSens));
                                            rilevazioneSens.setUnitaMisura(unitamisuraSens);

                                            minimo = InputDati.leggiIntero("Inserisci il minimo valore rilevabile dal sensore > ");
                                            rilevazioneSens.setMinimo(minimo);

                                            System.out.println();
                                            do {
                                                massimo = InputDati.leggiIntero("Inserisci il massimo valore rilevabile dal sensore > ");
                                                System.out.println();
                                            } while (massimo <= minimo);
                                            rilevazioneSens.setMassimo(massimo);

                                        } else {
                                            type = false;

                                            rilevazioneSens = new Rilevazioni(nomeRilevazione, type);

                                            do {
                                                do {
                                                    stringaDominio = InputDati.leggiStringa("Aggiungi un' informazione al dominio rilevabile > ");
                                                    System.out.println();
                                                } while (isBlankorWhitespace(stringaDominio) || !isUnivoque(rilevazioneSens.getDominioValori(), stringaDominio));

                                                rilevazioneSens.aggiungiDominioValori(stringaDominio);

                                                do {
                                                    System.out.println("Vuoi inserire un'altra variabile al dominio rilevabile?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente");
                                                    confirm = InputDati.leggiChar("Premere l'opzione desiderata > ");
                                                    System.out.println();
                                                } while (confirm != 'y' && confirm != 'n');
                                            } while (confirm == 'y');
                                        }

                                        listaInfoRilevabili.add(rilevazioneSens);

                                        do {
                                            System.out.println("Vuoi inserire un'altra informazione rilevabile?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente");
                                            confirm = InputDati.leggiChar("Premere l'opzione desiderata > ");
                                            System.out.println();
                                        } while (confirm != 'y' && confirm != 'n');
                                    } while (confirm == 'y');

                                    CategoriaSensori categoriaSensore = new CategoriaSensori(nomeCatSens, descrizioneCatSens, listaInfoRilevabili);

                                    System.out.println(titEndNewCatSens);
                                    System.out.println();

                                    listaNomiCategoriaSensori.add(categoriaSensore.getNome());
                                    listaCategoriaSensori.add(categoriaSensore);
                                    categorieSensoriRimanenti.add(categoriaSensore);

                                    System.out.println();
                                    System.out.println(titEndNewCatSens);
                                    System.out.println();

                                    index = 1;
                                    System.out.println("CATEGORIE SENSORI GIA' CREATE");
                                    for (CategoriaSensori sens : listaCategoriaSensori
                                    ) {
                                        System.out.println(index + ") " + sens.getNome() + ": " + sens.getDescrizione());
                                        index++;
                                    }
                                    System.out.println();

                                    do {
                                        System.out.println("Vuoi creare un' altra categoria?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente");
                                        confirm = InputDati.leggiChar("Premere l'opzione desiderata > ");
                                        System.out.println();
                                    } while (confirm != 'y' && confirm != 'n');
                                } while (confirm == 'y');
                                break;
                        }
                        break;
                    } while (!finishedManut);
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
                                if (listaCategoriaSensori.size() > 0) {
                                    index = 0;

                                    System.out.println(titViewSens);
                                    System.out.println();

                                    System.out.println("LISTA CATEGORIE SENSORI");
                                    for (CategoriaSensori catsens : listaCategoriaSensori
                                    ) {
                                        System.out.println(index + 1 + ") " + catsens.getNome() + "  -->  " + catsens.getDescrizione());
                                        index++;
                                    }

                                    System.out.println();
                                } else {
                                    System.out.println("ATTENZIONE: la lista delle categorie sensori e' vuota!!!");
                                    System.out.println();
                                }
                                break;
                        }
                        break;
                    } while (!finishedFruit);
            }
        } while (!finished);
    }
}