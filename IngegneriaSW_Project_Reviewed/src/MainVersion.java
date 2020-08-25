import Control.CreateObject;
import Control.StaticVariables;
import Model.*;
import mylib.*;

public class MainVersion extends CreateObject implements StaticVariables {
    public static void main(String[] args) {
        SistemaDomotico sistemaDomotico = new SistemaDomotico();
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
                                    UnitaImmobiliari unita = createUnitaImmobiliare();

                                    listaNomiUnitaImmobiliari.add(unita.getNome());
                                    listaUnitaImmobiliari.add(unita);
                                    sistemaDomotico.getListUnitaImmobiliari().add(unita);

                                    printNewLineAndTitle(titEndNewUnita);

                                    confirm = chooseInsert("Vuoi inserire una nuova unita' immobiliare?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                } while (confirm == 'y');
                                break;
                            //crea nuova categoria sensori
                            case 2:
                                printNewLineAndTitle(titNewCatSens);

                                do {
                                    CategoriaSensori categoriaSensore = createCategoriaSensore();

                                    listaNomiCategoriaSensori.add(categoriaSensore.getNome());
                                    listaCategoriaSensori.add(categoriaSensore);
                                    categorieSensoriRimanenti.add(categoriaSensore);

                                    printNewLineAndTitle(titEndNewCatSens);

                                    CategoriaSensori.printListCategoriaSensori(listaCategoriaSensori);

                                    confirm = chooseInsert("Vuoi inserire una nuova categoria di sensori?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                } while (confirm == 'y');
                                break;
                            //crea nuova categoria attuatori
                            case 3:
                                printNewLineAndTitle(titNewCatAtt);

                                do {
                                    CategoriaAttuatori categoriaAttuatore = createCategoriaAttuatore();

                                    listaCategoriaAttuatori.add(categoriaAttuatore);
                                    listaNomiCategoriaAttuatori.add(categoriaAttuatore.getNome());
                                    categorieAttuatoriRimanenti.add(categoriaAttuatore);

                                    printNewLineAndTitle(titEndNewCatAtt);

                                    CategoriaAttuatori.printListCategoriaAttuatori(listaCategoriaAttuatori);

                                    confirm = chooseInsert("Vuoi inserire una nuova categoria di attuatori?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                } while (confirm == 'y');
                                break;
                            //crea nuova stanza
                            case 4:
                                if (sistemaDomotico.checkUnita()) {
                                    printNewLineAndTitle(titNewStanza);

                                    do {
                                        Stanze stanza = createStanza();

                                        listaStanze.add(stanza);

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
                                        Artefatti artefatto = createArtefatto();

                                        listaArtefatti.add(artefatto);

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
                                        Sensori sensore = createSensore();

                                        listaSensori.add(sensore);

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
                                        Attuatori attuatore = createAttuatore();

                                        listaAttuatori.add(attuatore);

                                        printNewLineAndTitle(titEndNewAttuatore);

                                        confirm = chooseInsert("Vuoi creare un nuovo attuatore?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    } while (confirm == 'y');
                                }
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