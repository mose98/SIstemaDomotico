package Test;

import Model.Rilevazioni;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RilevazioniTest {

    @Test
    void newRilevazione() {
        Rilevazioni ril = new Rilevazioni("Temperatura", true);
        Rilevazioni ril1 = new Rilevazioni("Movimento", false);
        ril1.aggiungiDominioValori("caldo");
        ril1.aggiungiDominioValori("freddo");
        //test di corretta creazione oggetto
        assertEquals("Temperatura", ril.getNome());
        //test di corretta creazione oggetto
        assertEquals(true, ril.getNumerica());
        //test di corretta aggiunta valore
        assertTrue(ril1.getDominioValori().contains("caldo"));
    }

    @Test
    void getMassimo(){
        Rilevazioni ril = new Rilevazioni("Calore", true);
        ril.setMinimo(-100);
        ril.setMassimo(100);
        //test di corretto settaggio valore massimo
        assertTrue(ril.getMassimo()==100);
    }
}