package Control;

import Model.Attuatori;

public class Conseguente {
    private Attuatori attuatore;
    private String parametro;

    public Conseguente(Attuatori attuatore, String parametro) {
        this.attuatore = attuatore;
        this.parametro = parametro;
    }
}
