package Test;

import Control.CreatorRemove.ObjectRemoval;
import Model.Stanze;
import Model.UnitaImmobiliari;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectRemovalTest {

    //test dell'eliminazione di una stanza
    @Test
    void deleteObject() {
        UnitaImmobiliari u = new UnitaImmobiliari("Sole", "Privato");
        Stanze camera = new Stanze("Camera");
        Stanze bagno = new Stanze("Bagno");
        Stanze salotto = new Stanze("Salotto");

        u.aggiungiStanza(camera);
        u.aggiungiStanza(bagno);
        u.aggiungiStanza(salotto);

        //test per la presenza di almeno una stanza all'interno dell'unità
        assertTrue(u.checkStanze());
        //test di presenza stanza nell'unità
        assertTrue(u.getListastanza().contains(bagno));

        ObjectRemoval remove = new ObjectRemoval();

        remove.deleteObject(u, camera);
        //test di verifica corretta rimozione stanza
        assertTrue(!u.getListastanza().contains(camera));
    }
}