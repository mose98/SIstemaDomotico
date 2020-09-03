package Control;

import java.util.List;

public class Regole {
    private Antecedente antecedente;
    private Conseguente conseguente;
    private String nome;
    private String time;
    private boolean attivo=true;

    public Regole(String nome, Antecedente antecedente, Conseguente conseguente){
        this.nome=nome;
        this.antecedente=antecedente;
        this.conseguente=conseguente;
    }

    public Regole(String nome, Conseguente conseguente){
        this.nome=nome;
        this.conseguente=conseguente;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    public boolean getAttivo(){return attivo;}

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getNome() {
        return nome;
    }

    public Antecedente getAntecedente() {
        return antecedente;
    }

    public Conseguente getConseguente() {
        return conseguente;
    }
}
