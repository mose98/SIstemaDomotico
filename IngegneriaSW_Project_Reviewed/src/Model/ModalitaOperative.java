package Model;

import java.util.ArrayList;
import java.util.List;

public class ModalitaOperative extends GenericInfo{
    private List<String> listaParametri=new ArrayList<>();

    public ModalitaOperative(String nome, List<String> listaParametri){
        this.nome=nome;
        this.listaParametri=listaParametri;
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    public List<String> getListaParametri() {
        return listaParametri;
    }


}
