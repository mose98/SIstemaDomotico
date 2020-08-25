package Model;

import java.util.List;

public class Sensori extends GenericInfo{
    private CategoriaSensori categoria;
    private boolean attivo=true;

    public Sensori(String nome, CategoriaSensori categoria){
        this.nome=nome;
        this.categoria=categoria;
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public String getDescrizione() {
        return super.getDescrizione();
    }

    @Override
    public void setDescrizione(String descrizione) {
        super.setDescrizione(descrizione);
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    public CategoriaSensori getCategoria() {
        return categoria;
    }

    public boolean getAttivo(){return attivo;}
}
