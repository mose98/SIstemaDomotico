package Control.CreatorRemove;

import Model.*;

public interface ObjectRemovalInterface {
    //remove stanza
    void deleteObject(Object obj1, Stanze stanza);

    //remove artefatto
    void deleteObject(Object obj1, Artefatti artefatto);

    //remove attuatore
    void deleteObject(Object obj1, Sensori sensore);

    //remove attuatore
    void deleteObject(Object obj1, Attuatori attuatore);

    //remove categoriaSensore
    void deleteObject(CategoriaSensori categoriaSensore);

    //remove categoriaAttuatore
    void deleteObject(CategoriaAttuatori categoriaAttuatore);
}
