package Model;

public class Attuatori extends GenericInfo{
    private CategoriaAttuatori categoria;
    private ModalitaOperative parametroAssegnato;
    private boolean attivo=true;

    public Attuatori(String nome, CategoriaAttuatori categoria){
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

    public void setParametroAssegnato(ModalitaOperative parametroAssegnato) {
        this.parametroAssegnato = parametroAssegnato;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    public ModalitaOperative getParametroAssegnato() {
        return parametroAssegnato;
    }

    public CategoriaAttuatori getCategoria() {
        return categoria;
    }

    public boolean getAttivo(){return attivo;}
}
