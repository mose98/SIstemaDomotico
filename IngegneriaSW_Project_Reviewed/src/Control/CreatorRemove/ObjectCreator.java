package Control.CreatorRemove;

import Control.StaticVariables;
import Model.*;

import java.util.ArrayList;
import java.util.List;

public class ObjectCreator extends GenericInfo implements StaticVariables {

    public static UnitaImmobiliari createUnitaImmobiliare(){
        String nomeUnita = readStringUnique("Dai un nome all'unità immobiliare > ", listaNomiUnitaImmobiliari);

        printTitleAndList("Tipologie di unita' immobiliari", tipologieUnitaImmobiliari);
        int tipoUnita = chooseFromList("Scegliere il tipo di unita' immobiliare tra quelle proposte di sopra > ", tipologieUnitaImmobiliari) - 1;
        UnitaImmobiliari unita = new UnitaImmobiliari(nomeUnita, tipologieUnitaImmobiliari.get(tipoUnita));

        return unita;
    }

    public static CategoriaSensori createCategoriaSensore(){
        char confirm;
        List<String> listaNomiInfoRilevabili = new ArrayList<>();
        List<Rilevazioni> listaInfoRilevabili = new ArrayList<>();

        String nomeCatSens = readStringUnique("Dai un nome alla categoria di sensori (senza spazii) > ", listaNomiCategoriaSensori);
        String descrizioneCatSens = readStringLength("Inserisci la descrizione > ", maxlength);

        do {
            String nomeRilevazione=readStringUnique("Inserisci l'informazione rilevabile dal sensore > ", listaNomiInfoRilevabili);
            listaNomiInfoRilevabili.add(nomeRilevazione);

            char chooseType=chooseInsert("L'informazione rilevata e' numerica o non  numerica?\npremere '1' per la numerica\npremere '2' per non numerica", '1', '2');

            listaInfoRilevabili.add(Rilevazioni.newRilevazione(chooseType, nomeRilevazione));

            confirm = chooseInsert("Vuoi inserire un'altra informazione rilevabile dal sensore?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
        } while (confirm == 'y');

        CategoriaSensori categoriaSensore = new CategoriaSensori(nomeCatSens, descrizioneCatSens, listaInfoRilevabili);

        return categoriaSensore;
    }

    public static CategoriaAttuatori createCategoriaAttuatore(){
        List<String> listNomiModalita = new ArrayList<>();
        List<ModalitaOperative> listModalita = new ArrayList<>();
        char confirm;

        String nomeCatAtt = readStringUnique("Dai un nome alla categoria di attuatori (senza spazi) > ", listaNomiCategoriaAttuatori);
        String descrizioneCatAtt = readStringLength("Inserisci la descrizione > ", maxlength);

        do {
            List<String> listParam = new ArrayList<>();

            String nomeModOperativa=readStringUnique("Aggiungere una modalita' operativa all'attuatore > ", listNomiModalita);

            do {
                String parametroModOperativa = readStringUnique("Aggiungere un parametro alla modalita' operativa dell'attuatore > ", listParam);

                listParam.add(parametroModOperativa);

                confirm = chooseInsert("Vuoi inserire un'altra modalità operativa?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
            } while (confirm == 'y');

            ModalitaOperative modalita = new ModalitaOperative(nomeModOperativa, listParam);
            listModalita.add(modalita);
            listNomiModalita.add(modalita.getNome());

            CategoriaAttuatori.printListModalitaOperative(listModalita);

            confirm = chooseInsert("Vuoi inserire un'altra modalita' operativa?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
        } while (confirm == 'y');

        CategoriaAttuatori categoriaAttuatore = new CategoriaAttuatori(descrizioneCatAtt, nomeCatAtt, listModalita);

        return categoriaAttuatore;
    }

    public static Stanze createStanza(){
        SistemaDomotico.printListUnita(listaUnitaImmobiliari);
        UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("In che unita' immobiliare si desidera inserire la stanza?", listaUnitaImmobiliari)-1);

        String nomeStanza = readString("Inserisci un nome alla stanza (senza spazi) > ");

        Stanze stanza = new Stanze(nomeStanza);
        stanza.setDescrizione(unitaSel.getNome());
        unitaSel.aggiungiStanza(stanza);

        return stanza;
    }

    public static Artefatti createArtefatto(){
        int sceltaPosArtefatto;
        Artefatti artefatto = null;

        SistemaDomotico.printListUnita(listaUnitaImmobiliari);
        UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("In che unita' immobiliare si desidera inserire l'artefatto?", listaUnitaImmobiliari)-1);

        String nomeArtefatto = readString("Inserisci un nome dell'artefatto (senza spazi) > ");

        sceltaPosArtefatto = chooseInsertString("Scegliere dove posizionare l'artefatto?\n1) STANZA\n2) ESTERNO", 2);

        //inserimento artefatto in una stanza
        if(sceltaPosArtefatto == 1){
            if(unitaSel.checkStanze()){
                unitaSel.printStanze();

                Stanze stanzaSel = unitaSel.getListastanza().get(chooseFromList("In che stanza si desidera inserire il sensore?", unitaSel.getListastanza())-1);

                artefatto = new Artefatti(nomeArtefatto);

                stanzaSel.aggiungiArtefatti(artefatto);
                unitaSel.aggiungiArtefattoUnita(artefatto);
                artefatto.setDescrizione(stanzaSel.getNome() + "(stanza)");
            }
        }
        //inserimento sensore all'esterno
        else{
            artefatto = new Artefatti(nomeArtefatto);

            unitaSel.aggiungiArtefattoUnita(artefatto);
            artefatto.setDescrizione(unitaSel.getNome() + "(esterno)");
            unitaSel.aggiungiArtefattiEsterni(artefatto);
        }

        return artefatto;
    }

    public static Sensori createSensore(){
        int sceltaPosSensore;
        Sensori sensore= null;
        SistemaDomotico.printListUnita(listaUnitaImmobiliari);
        UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("In che unita' immobiliare si desidera inserire il sensore?", listaUnitaImmobiliari)-1);

        String nomeSensore = readString("Inserisci un nome del sensore (senza spazi) > ");

        CategoriaSensori.printListCategoriaSensoriRimanenti(categorieSensoriRimanenti);
        CategoriaSensori categoria = categorieSensoriRimanenti.get(chooseFromList("Assegnare il sensore ad una delle categorie dell'elenco precedente > ", categorieSensoriRimanenti)-1);

        sceltaPosSensore = chooseInsertString("Scegliere dove posizionare il sensore?\n1) STANZA\n2) ARTEFATTO\n3)ESTERNO", 3);


        //inserimento sensore in una stanza
        if(sceltaPosSensore==1){
            if(unitaSel.checkStanze()){
                unitaSel.printStanze();

                Stanze stanzaSel = unitaSel.getListastanza().get(chooseFromList("In che stanza si desidera inserire il sensore?", unitaSel.getListastanza())-1);

                sensore = new Sensori(nomeSensore, categoria);

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
        else if (sceltaPosSensore==2){
            if(unitaSel.getListaArtefattiUnita().size()>0){
                Artefatti.printArtefatti(unitaSel.getListaArtefattiUnita());

                Artefatti artefattoSel = unitaSel.getListaArtefattiUnita().get(chooseFromList("In che artefatto si vuole applicare il sensore?", unitaSel.getListaArtefattiUnita()) - 1);

                sensore = new Sensori(nomeSensore, categoria);

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
        else{
            sensore = new Sensori(nomeSensore, categoria);

            categoria.aggiungiNomeSensore(nomeSensore);
            categoria.aggiungiSensore(sensore);
            unitaSel.aggiungiSensoriUnita(sensore);
            unitaSel.aggiungiSensoriEsterni(sensore);
            unitaSel.aggiungiListaSensoriUnitaUbicazione(unitaSel.getNome());
            sensore.setDescrizione(unitaSel.getNome() + " (esterno)");
        }

        UnitaImmobiliari.printSensori(unitaSel.getListaSensoriUnita());

        return sensore;
    }

    public static Attuatori createAttuatore(){
        int sceltaPosAttuatore;
        Attuatori attuatore = null;
        SistemaDomotico.printListUnita(listaUnitaImmobiliari);
        UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("In che unita' immobiliare si desidera inserire il attuatore?", listaUnitaImmobiliari)-1);

        String nomeAttuatore = readString("Inserisci un nome del attuatore (senza spazi) > ");

        CategoriaAttuatori.printListCategoriaAttuatoriRimanenti(categorieAttuatoriRimanenti);
        CategoriaAttuatori categoria = categorieAttuatoriRimanenti.get(chooseFromList("Assegnare l'attuatore ad una delle categorie dell'elenco precedente > ", categorieAttuatoriRimanenti)-1);

        sceltaPosAttuatore = chooseInsertString("Scegliere dove posizionare l'attuatore?\n1) STANZA\n2) ARTEFATTO\n3)ESTERNO", 3);


        //inserimento attuatore in una stanza
        if(sceltaPosAttuatore ==1){
            if(unitaSel.checkStanze()){
                unitaSel.printStanze();

                Stanze stanzaSel = unitaSel.getListastanza().get(chooseFromList("In che stanza si desidera inserire l'attuatore?", unitaSel.getListastanza())-1);

                attuatore = new Attuatori(nomeAttuatore, categoria);

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
        else if (sceltaPosAttuatore ==2){
            if(unitaSel.getListaArtefattiUnita().size()>0){
                Artefatti.printArtefatti(unitaSel.getListaArtefattiUnita());

                Artefatti artefattoSel = unitaSel.getListaArtefattiUnita().get(chooseFromList("In che artefatto si vuole applicare l'attuatore?", unitaSel.getListaArtefattiUnita()) - 1);

                attuatore = new Attuatori(nomeAttuatore, categoria);

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
        else{
            attuatore = new Attuatori(nomeAttuatore, categoria);

            categoria.aggiungiNomeAttuatore(nomeAttuatore);
            categoria.aggiungiAttuatore(attuatore);
            unitaSel.aggiungiAttuatoriUnita(attuatore);
            unitaSel.aggiungiAttuatoriEsterni(attuatore);
            unitaSel.aggiungiListaAttuatoriUnitaUbicazione(unitaSel.getNome());
            attuatore.setDescrizione(unitaSel.getNome() + " (esterno)");
        }

        UnitaImmobiliari.printAttuatori(unitaSel.getListaAttuatoriUnita());

        return attuatore;
    }

}
