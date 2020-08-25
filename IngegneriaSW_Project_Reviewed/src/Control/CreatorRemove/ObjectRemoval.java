package Control.CreatorRemove;

import View.StaticVariables;
import Model.*;

public class ObjectRemoval implements ObjectRemovalInterface, StaticVariables {

    //remove stanza
    @Override
    public void deleteObject(Object obj1, Stanze stanza) {
        UnitaImmobiliari unitaSel = (UnitaImmobiliari) obj1;
        Stanze stanzaSel = (Stanze) stanza;

        unitaSel.getListastanza().remove(stanzaSel);

        for (Artefatti art : stanzaSel.getListaArtefatti()
        ) {
            listaSensori.removeIf(x -> x.getDescrizione() == art.getNome());
            listaAttuatori.removeIf(x -> x.getDescrizione() == art.getNome());
            listaArtefatti.removeIf(x -> x.getDescrizione() == stanzaSel.getNome());
        }

        listaSensori.removeIf(x -> x.getDescrizione() == stanzaSel.getNome());
        listaAttuatori.removeIf(x -> x.getDescrizione() == stanzaSel.getNome());

        for (Artefatti art : stanzaSel.getListaArtefatti()
        ) {
            unitaSel.getListaSensoriUnita().removeIf(x -> x.getDescrizione() == art.getNome());
            unitaSel.getListaAttuatoriUnita().removeIf(x -> x.getDescrizione() == art.getNome());
            unitaSel.getListaArtefattiUnita().removeIf(x -> x.getDescrizione() == stanzaSel.getNome());
        }

        unitaSel.getListaSensoriUnita().removeIf(x -> x.getDescrizione() == stanzaSel.getNome());
        unitaSel.getListaAttuatoriUnita().removeIf(x -> x.getDescrizione() == stanzaSel.getNome());
        stanzaSel.getListaArtefatti().clear();
        stanzaSel.getListaAttuatori().clear();
        stanzaSel.getListaSensori().clear();
        listaStanze.remove(stanzaSel);
    }

    @Override
    public void deleteObject(Object obj1, Artefatti artefatto) {
        UnitaImmobiliari unitaSel = (UnitaImmobiliari) obj1;
        Artefatti artefattoSel = (Artefatti) artefatto;

        for (Stanze st : unitaSel.getListastanza()
        ) {
            st.getListaArtefatti().removeIf(x -> x.getDescrizione() == artefattoSel.getNome());
        }

        for (Stanze st : listaStanze
        ) {
            st.getListaArtefatti().removeIf(x -> x.getDescrizione() == artefattoSel.getNome());
        }

        unitaSel.getListaArtefattiUnita().remove(artefattoSel);
        listaArtefatti.remove(artefattoSel);
    }

    @Override
    public void deleteObject(Object obj1, Sensori sensore) {
        UnitaImmobiliari unitaSel = (UnitaImmobiliari) obj1;
        Sensori sensoreSel = (Sensori) sensore;
        for (Stanze st : listaStanze
        ) {
            st.getListaSensori().removeIf(x -> x.getDescrizione() == sensoreSel.getNome());
        }

        for (Artefatti art : listaArtefatti
        ) {
            listaSensori.removeIf(x -> x.getDescrizione() == art.getNome());
            art.getListaSensori().removeIf(x -> x.getDescrizione() == sensoreSel.getNome());
        }

        for (Stanze st : unitaSel.getListastanza()
        ) {
            st.getListaSensori().removeIf(x -> x.getDescrizione() == sensoreSel.getNome());
        }

        for (Artefatti art : unitaSel.getListaArtefattiUnita()
        ) {
            listaSensori.removeIf(x -> x.getDescrizione() == art.getNome());
            art.getListaSensori().removeIf(x -> x.getDescrizione() == sensoreSel.getNome());
        }

        unitaSel.getListaSensoriUnitaUbicazione().remove(sensoreSel);
        unitaSel.getListaSensoriUnita().remove(sensoreSel);
        listaSensori.remove(sensoreSel);
    }

    @Override
    public void deleteObject(Object obj1, Attuatori attuatore) {
        UnitaImmobiliari unitaSel = (UnitaImmobiliari) obj1;
        Attuatori attuatoriSel = (Attuatori) attuatore;
        for (Stanze st : listaStanze
        ) {
            st.getListaAttuatori().removeIf(x -> x.getDescrizione() == attuatoriSel.getNome());
        }

        for (Artefatti art : listaArtefatti
        ) {
            listaAttuatori.removeIf(x -> x.getDescrizione() == art.getNome());
            art.getListaAttuatori().removeIf(x -> x.getDescrizione() == attuatoriSel.getNome());
        }

        for (Stanze st : unitaSel.getListastanza()
        ) {
            st.getListaSensori().removeIf(x -> x.getDescrizione() == attuatoriSel.getNome());
        }

        for (Artefatti art : unitaSel.getListaArtefattiUnita()
        ) {
            listaSensori.removeIf(x -> x.getDescrizione() == art.getNome());
            art.getListaSensori().removeIf(x -> x.getDescrizione() == attuatoriSel.getNome());
        }

        unitaSel.getListaAttuatoriUnita().remove(attuatoriSel);
        listaAttuatori.remove(attuatoriSel);
    }

    @Override
    public void deleteObject(CategoriaSensori categoriaSensore) {
        listaNomiCategoriaSensori.remove(categoriaSensore.getNome());
        listaCategoriaSensori.remove(categoriaSensore);
    }

    @Override
    public void deleteObject(CategoriaAttuatori categoriaAttuatore) {
        listaNomiCategoriaAttuatori.remove(categoriaAttuatore.getNome());
        listaCategoriaAttuatori.remove(categoriaAttuatore);
    }
}
