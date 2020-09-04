package Control.CreatorRemove;

import Control.Antecedente;
import Control.Conseguente;
import Control.FileSaver;
import Control.Regole;
import View.StaticVariables;
import Model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ObjectCreator extends GenericInfo implements StaticVariables {
    static int indexStanze = 1;
    static int indexArtefatti = 1;
    public static final String SYS_PROPERTY_TMPDIR = "java.io.tmpdir";
    public static String wPath = System.getProperty(SYS_PROPERTY_TMPDIR);

    public static UnitaImmobiliari createUnitaImmobiliare() {
        String nomeUnita = readStringUnique("Dai un nome all'unità immobiliare > ", listaNomiUnitaImmobiliari);

        printTitleAndList("Tipologie di unita' immobiliari", tipologieUnitaImmobiliari);
        int tipoUnita = chooseFromList("Scegliere il tipo di unita' immobiliare tra quelle proposte di sopra > ", tipologieUnitaImmobiliari) - 1;
        UnitaImmobiliari unita = new UnitaImmobiliari(nomeUnita, tipologieUnitaImmobiliari.get(tipoUnita));

        listaNomiUnitaImmobiliari.add(unita.getNome());
        listaUnitaImmobiliari.add(unita);

        return unita;
    }

    public static CategoriaSensori createCategoriaSensore(String folderName) throws IOException {
        char confirm;
        List<String> listaNomiInfoRilevabili = new ArrayList<>();
        List<Rilevazioni> listaInfoRilevabili = new ArrayList<>();

        String testo="Dai un nome alla categoria di sensori (senza spazii) > ";
        String nomeCatSens = readStringUnique(testo, listaNomiCategoriaSensori);
        String descrizioneCatSens = readStringLength("Inserisci la descrizione > ", maxlength);

        FileSaver.createFolder(folderName + File.separator + nomeCatSens);
        FileSaver.createFile(folderName + File.separator + nomeCatSens + File.separator + "Rilevazioni.txt");

        Rilevazioni rilevazione;
        do {
            String nomeRilevazione = readStringUnique("Inserisci l'informazione rilevabile dal sensore > ", listaNomiInfoRilevabili);
            listaNomiInfoRilevabili.add(nomeRilevazione);

            String richiesta="L'informazione rilevata e' numerica o non  numerica?\npremere '1' per la numerica\npremere '2' per non numerica";
            char chooseType = chooseInsert(richiesta, '1', '2');

            rilevazione = Rilevazioni.newRilevazione(chooseType, nomeRilevazione);
            listaInfoRilevabili.add(rilevazione);

            if (rilevazione.getNumerica()){
                String fileName=wPath + File.separator + folderName + File.separator + nomeCatSens + File.separator + "Rilevazioni.txt";
                FileSaver.newRilevazioneFile(fileName, rilevazione.getNome(), rilevazione.getNumerica(), null, rilevazione.getUnitaMisura(), rilevazione.getMinimo(), rilevazione.getMassimo());
            }
            else{
                String fileName=wPath + File.separator + folderName + File.separator + nomeCatSens + File.separator + "Rilevazioni.txt";
                FileSaver.newRilevazioneFile(fileName, rilevazione.getNome(), rilevazione.getNumerica(), rilevazione.getDominioValori(), null, null, null);
            }

            String richiesta1="Vuoi inserire un'altra informazione rilevabile dal sensore?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente";
            confirm = chooseInsert(richiesta1, 'y', 'n');
        } while (confirm == 'y');

        CategoriaSensori categoriaSensore = new CategoriaSensori(nomeCatSens, descrizioneCatSens, listaInfoRilevabili);

        listaNomiCategoriaSensori.add(categoriaSensore.getNome());
        listaCategoriaSensori.add(categoriaSensore);
        categorieSensoriRimanenti.add(categoriaSensore);

        return categoriaSensore;
    }

    public static CategoriaAttuatori createCategoriaAttuatore(String folderName) throws IOException {
        List<String> listNomiModalita = new ArrayList<>();
        List<ModalitaOperative> listModalita = new ArrayList<>();
        char confirm;

        String nomeCatAtt = readStringUnique("Dai un nome alla categoria di attuatori (senza spazi) > ", listaNomiCategoriaAttuatori);
        String descrizioneCatAtt = readStringLength("Inserisci la descrizione > ", maxlength);

        FileSaver.createFolder(folderName + File.separator + nomeCatAtt);
        FileSaver.createFile(folderName + File.separator + nomeCatAtt + File.separator + "ModalitaOperative.txt");

        do {
            List<String> listParam = new ArrayList<>();

            String nomeModOperativa = readStringUnique("Aggiungere una modalita' operativa all'attuatore > ", listNomiModalita);
            FileSaver.createFolder(folderName + File.separator + nomeCatAtt + File.separator + nomeModOperativa);
            FileSaver.newModalitaOperativa(wPath + File.separator + folderName + File.separator + nomeCatAtt + File.separator + "ModalitaOperative.txt", nomeModOperativa);

            do {
                String parametroModOperativa = readStringUnique("Aggiungere un parametro alla modalita' operativa dell'attuatore > ", listParam);

                listParam.add(parametroModOperativa);

                FileSaver.createFile(folderName + File.separator + nomeCatAtt + File.separator + nomeModOperativa + File.separator + parametroModOperativa);

                confirm = chooseInsert("Vuoi inserire un altro parametro?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
            } while (confirm == 'y');

            ModalitaOperative modalita = new ModalitaOperative(nomeModOperativa, listParam);
            listModalita.add(modalita);
            listNomiModalita.add(modalita.getNome());

            CategoriaAttuatori.printListModalitaOperative(listModalita);

            confirm = chooseInsert("Vuoi inserire un'altra modalita' operativa?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
        } while (confirm == 'y');

        CategoriaAttuatori categoriaAttuatore = new CategoriaAttuatori(nomeCatAtt, descrizioneCatAtt, listModalita);

        listaCategoriaAttuatori.add(categoriaAttuatore);
        listaNomiCategoriaAttuatori.add(categoriaAttuatore.getNome());
        categorieAttuatoriRimanenti.add(categoriaAttuatore);

        return categoriaAttuatore;
    }

    public static Stanze createStanza(String folderName) throws IOException {
        SistemaDomotico.printListUnita(listaUnitaImmobiliari);
        UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("In che unita' immobiliare si desidera inserire la stanza? ", listaUnitaImmobiliari) - 1);

        String nomeStanza = readString("Inserisci un nome alla stanza (senza spazi) > ");
        nomeStanza = nomeStanza + "_" + indexStanze++;

        Stanze stanza = new Stanze(nomeStanza);
        stanza.setDescrizione(unitaSel.getNome());
        unitaSel.aggiungiStanza(stanza);

        String folderUnitaName = folderName + File.separator + unitaSel.getNome();

        FileSaver.newStanza(wPath + File.separator + folderUnitaName + File.separator + "Stanze.txt", nomeStanza);

        FileSaver.createFolder(folderUnitaName + File.separator + nomeStanza);
        FileSaver.createFile(folderUnitaName + File.separator + nomeStanza + "/Sensori.txt");
        FileSaver.createFile(folderUnitaName + File.separator + nomeStanza + "/Attuatori.txt");
        FileSaver.createFile(folderUnitaName + File.separator + nomeStanza + "/Artefatti.txt");

        unitaSel.aggiungiStanza(stanza);
        listaStanze.add(stanza);
        listaNomiStanze.add(stanza.getNome());

        return stanza;
    }

    public static Artefatti createArtefatto(String folderName) throws IOException {
        int sceltaPosArtefatto;
        Artefatti artefatto = null;

        SistemaDomotico.printListUnita(listaUnitaImmobiliari);
        UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("In che unita' immobiliare si desidera inserire l'artefatto? ", listaUnitaImmobiliari) - 1);

        String nomeArtefatto = readString("Inserisci un nome dell'artefatto (senza spazi) > ");
        nomeArtefatto=nomeArtefatto + "_" + indexArtefatti++;
        sceltaPosArtefatto = chooseInsertString("Scegliere dove posizionare l'artefatto?\n1) STANZA\n2) ESTERNO", 2);

        //inserimento artefatto in una stanza
        if (sceltaPosArtefatto == 1) {
            if (unitaSel.checkStanze()) {
                unitaSel.printStanze();

                Stanze stanzaSel = unitaSel.getListastanza().get(chooseFromList("In che stanza si desidera inserire il sensore? ", unitaSel.getListastanza()) - 1);

                artefatto = new Artefatti(nomeArtefatto);

                stanzaSel.aggiungiArtefatti(artefatto);
                unitaSel.aggiungiArtefattoUnita(artefatto);
                artefatto.setDescrizione(stanzaSel.getNome() + "(stanza)");

                String folderUnitaName = folderName + File.separator + unitaSel.getNome() + File.separator + stanzaSel.getNome();

                FileSaver.createFolder(folderUnitaName + File.separator + nomeArtefatto);
                FileSaver.createFile(folderUnitaName +File.separator+nomeArtefatto+ "/Sensori.txt");
                FileSaver.createFile(folderUnitaName +File.separator+nomeArtefatto+ "/Attuatori.txt");

                FileSaver.newArtefatto(wPath+File.separator+folderUnitaName+"/Artefatti.txt", nomeArtefatto);

                unitaSel.aggiungiArtefattoUnita(artefatto);
            }
        }
        //inserimento sensore all'esterno
        else {
            artefatto = new Artefatti(nomeArtefatto);

            unitaSel.aggiungiArtefattoUnita(artefatto);
            artefatto.setDescrizione(unitaSel.getNome() + "(esterno)");
            unitaSel.aggiungiArtefattiEsterni(artefatto);

            String folderUnitaName = folderName + File.separator + unitaSel.getNome();

            FileSaver.createFolder(folderUnitaName + File.separator + nomeArtefatto);
            FileSaver.createFile(folderUnitaName+File.separator+nomeArtefatto + "/Sensori.txt");
            FileSaver.createFile(folderUnitaName +File.separator+nomeArtefatto+ "/Attuatori.txt");

            FileSaver.newArtefatto(wPath+File.separator+folderUnitaName+"/ArtefattiEsterni.txt", nomeArtefatto);
            unitaSel.aggiungiArtefattiEsterni(artefatto);
        }

        listaArtefatti.add(artefatto);
        listaNomiArtefatti.add(artefatto.getNome());
        return artefatto;
    }

    public static Sensori createSensore(String folderName) {
        int sceltaPosSensore;
        Sensori sensore = null;
        SistemaDomotico.printListUnita(listaUnitaImmobiliari);
        UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("In che unita' immobiliare si desidera inserire il sensore? ", listaUnitaImmobiliari) - 1);

        String nomeSensore = readString("Inserisci un nome del sensore (senza spazi) > ");

        if(categorieSensoriRimanenti.size()==0) return null;
        CategoriaSensori.printListCategoriaSensoriRimanenti(categorieSensoriRimanenti);
        CategoriaSensori categoria = categorieSensoriRimanenti.get(chooseFromList("Assegnare il sensore ad una delle categorie dell'elenco precedente > ", categorieSensoriRimanenti) - 1);

        sceltaPosSensore = chooseInsertString("Scegliere dove posizionare il sensore?\n1) STANZA\n2) ARTEFATTO\n3) ESTERNO", 3);


        //inserimento sensore in una stanza
        if (sceltaPosSensore == 1) {
            if (unitaSel.checkStanze()) {
                unitaSel.printStanze();

                Stanze stanzaSel = unitaSel.getListastanza().get(chooseFromList("In che stanza si desidera inserire il sensore? ", unitaSel.getListastanza()) - 1);

                sensore = new Sensori(nomeSensore, categoria);

                FileSaver.newSensore(wPath+File.separator+folderName+File.separator+unitaSel.getNome()+File.separator+stanzaSel.getNome()+"/Sensori.txt", nomeSensore, categoria.getNome());
                categoria.aggiungiNomeSensore(nomeSensore);
                categoria.aggiungiSensore(sensore);
                unitaSel.aggiungiSensoriUnita(sensore);
                stanzaSel.aggiungiSensori(sensore);
                stanzaSel.aggiungiCategorieSensoriApplicati(sensore.getCategoria());
                unitaSel.aggiungiListaSensoriUnitaUbicazione(stanzaSel.getNome());
                sensore.setDescrizione(stanzaSel.getNome());
                categorieSensoriRimanenti.remove(categoria);
            }
        }
        //inserimento sensore in un artefatto
        else if (sceltaPosSensore == 2) {
            if (unitaSel.getListaArtefattiUnita().size() > 0) {
                Artefatti.printArtefatti(unitaSel.getListaArtefattiUnita());

                Artefatti artefattoSel = unitaSel.getListaArtefattiUnita().get(chooseFromList("In che artefatto si vuole applicare il sensore? ", unitaSel.getListaArtefattiUnita()) - 1);

                sensore = new Sensori(nomeSensore, categoria);

                File f = new File(wPath+File.separator+folderName+File.separator+unitaSel.getNome());
                List<Path> pathList = FileSaver.displayDirectoryContents(f);
                String path= FileSaver.findPath(pathList, artefattoSel.getNome());

                FileSaver.xx.clear();
                FileSaver.newAttuatore(path+"/Sensori.txt", nomeSensore, categoria.getNome());
                categoria.aggiungiNomeSensore(nomeSensore);
                categoria.aggiungiSensore(sensore);
                unitaSel.aggiungiSensoriUnita(sensore);
                artefattoSel.aggiungiSensori(sensore);
                artefattoSel.aggiungiCategorieSensoriApplicati(sensore.getCategoria());
                unitaSel.aggiungiListaSensoriUnitaUbicazione(artefattoSel.getNome());
                sensore.setDescrizione(artefattoSel.getNome());
                categorieSensoriRimanenti.remove(categoria);
            }
        }
        //inserimento sensore all'esterno
        else {
            sensore = new Sensori(nomeSensore, categoria);

            FileSaver.newSensore(wPath+File.separator+folderName+File.separator+unitaSel.getNome()+"/SensoriEsterni.txt", nomeSensore, categoria.getNome());
            categoria.aggiungiNomeSensore(nomeSensore);
            categoria.aggiungiSensore(sensore);
            unitaSel.aggiungiSensoriUnita(sensore);
            unitaSel.aggiungiSensoriEsterni(sensore);
            unitaSel.aggiungiListaSensoriUnitaUbicazione(unitaSel.getNome());
            sensore.setDescrizione(unitaSel.getNome() + " (esterno)");
        }

        listaSensori.add(sensore);
        listaNomiSensori.add(sensore.getNome());
        UnitaImmobiliari.printSensori(unitaSel.getListaSensoriUnita());

        return sensore;
    }

    public static Attuatori createAttuatore(String folderName) {
        int sceltaPosAttuatore;
        Attuatori attuatore = null;
        SistemaDomotico.printListUnita(listaUnitaImmobiliari);
        UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("In che unita' immobiliare si desidera inserire il attuatore? ", listaUnitaImmobiliari) - 1);

        String nomeAttuatore = readString("Inserisci un nome del attuatore (senza spazi) > ");

        if(categorieAttuatoriRimanenti.size()==0) return null;
        CategoriaAttuatori.printListCategoriaAttuatoriRimanenti(categorieAttuatoriRimanenti);
        CategoriaAttuatori categoria = categorieAttuatoriRimanenti.get(chooseFromList("Assegnare l'attuatore ad una delle categorie dell'elenco precedente > ", categorieAttuatoriRimanenti) - 1);

        sceltaPosAttuatore = chooseInsertString("Scegliere dove posizionare l'attuatore?\n1) STANZA\n2) ARTEFATTO\n3) ESTERNO", 3);


        //inserimento attuatore in una stanza
        if (sceltaPosAttuatore == 1) {
            if (unitaSel.checkStanze()) {
                unitaSel.printStanze();

                Stanze stanzaSel = unitaSel.getListastanza().get(chooseFromList("In che stanza si desidera inserire l'attuatore? ", unitaSel.getListastanza()) - 1);

                attuatore = new Attuatori(nomeAttuatore, categoria);

                FileSaver.newAttuatore(wPath+File.separator+folderName+File.separator+unitaSel.getNome()+File.separator+stanzaSel.getNome()+"/Attuatori.txt", nomeAttuatore, categoria.getNome());
                categoria.aggiungiNomeAttuatore(nomeAttuatore);
                categoria.aggiungiAttuatore(attuatore);
                unitaSel.aggiungiAttuatoriUnita(attuatore);
                stanzaSel.aggiungiAttuatori(attuatore);
                stanzaSel.aggiungiCategorieAttuatoriApplicati(attuatore.getCategoria());
                unitaSel.aggiungiListaAttuatoriUnitaUbicazione(stanzaSel.getNome());
                attuatore.setDescrizione(stanzaSel.getNome());
                categorieAttuatoriRimanenti.remove(categoria);
            }
        }
        //inserimento attuatore in un artefatto
        else if (sceltaPosAttuatore == 2) {
            if (unitaSel.getListaArtefattiUnita().size() > 0) {
                Artefatti.printArtefatti(unitaSel.getListaArtefattiUnita());

                Artefatti artefattoSel = unitaSel.getListaArtefattiUnita().get(chooseFromList("In che artefatto si vuole applicare l'attuatore?", unitaSel.getListaArtefattiUnita()) - 1);

                attuatore = new Attuatori(nomeAttuatore, categoria);

                File f = new File(wPath+File.separator+folderName+File.separator+unitaSel.getNome());
                List<Path> pathList = FileSaver.displayDirectoryContents(f);
                String path= FileSaver.findPath(pathList, artefattoSel.getNome());

                FileSaver.xx.clear();
                FileSaver.newAttuatore(path+"/Attuatori.txt", nomeAttuatore, categoria.getNome());
                categoria.aggiungiNomeAttuatore(nomeAttuatore);
                categoria.aggiungiAttuatore(attuatore);
                unitaSel.aggiungiAttuatoriUnita(attuatore);
                artefattoSel.aggiungiAttuatori(attuatore);
                artefattoSel.aggiungiCategorieAttuatoriApplicati(attuatore.getCategoria());
                unitaSel.aggiungiListaAttuatoriUnitaUbicazione(artefattoSel.getNome());
                attuatore.setDescrizione(artefattoSel.getNome());
                categorieAttuatoriRimanenti.remove(categoria);
            }
        }
        //inserimento attuatore all'esterno
        else {
            attuatore = new Attuatori(nomeAttuatore, categoria);

            FileSaver.newAttuatore(wPath+File.separator+folderName+File.separator+unitaSel.getNome()+"/AttuatoriEsterni.txt", nomeAttuatore, categoria.getNome());
            categoria.aggiungiNomeAttuatore(nomeAttuatore);
            categoria.aggiungiAttuatore(attuatore);
            unitaSel.aggiungiAttuatoriUnita(attuatore);
            unitaSel.aggiungiAttuatoriEsterni(attuatore);
            unitaSel.aggiungiListaAttuatoriUnitaUbicazione(unitaSel.getNome());
            attuatore.setDescrizione(unitaSel.getNome() + " (esterno)");
        }

        listaAttuatori.add(attuatore);
        listaNomiAttuatori.add(attuatore.getNome());
        UnitaImmobiliari.printAttuatori(unitaSel.getListaAttuatoriUnita());

        return attuatore;
    }
}
