package View;

import Control.Regole;
import Model.*;

import static Control.Util.chooseFromList;
import static Control.Util.printTitleAndList;
import static View.StaticVariables.*;

public class VistaParametri{
    public static void ValoriSensori(){
        int index=0;
        SistemaDomotico.printListUnita(listaUnitaImmobiliari);
        UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Scegli l'unità immobiliare ", listaUnitaImmobiliari) - 1);

        for (Sensori sens:unitaSel.getListaSensoriUnita()
             ) {
            for (Rilevazioni ril:sens.getCategoria().getRilevazione()
                 ) {
                if(ril.getNumerica()){
                    int valore = (int) (Math.random() * ril.getMassimo() + ril.getMinimo());
                    System.out.println(index++ + ") " + sens.getNome() + "      attivo = " + sens.getAttivo() + "    informazione > " + ril.getNome() + "   valore registrato > " + valore + " " + ril.getUnitaMisura());
                }
                else{
                    int size = ril.getDominioValori().size();
                    int valore = (int) (Math.random() * size);
                    String valoreReg = ril.getDominioValori().get(valore);
                    System.out.println(index++ + ") " + sens.getNome() + "      attivo = " + sens.getAttivo() + "    informazione > " + ril.getNome() + "   valore registrato > " + valoreReg);
                }
            }
        }
    }

    public static void AssegnaParametri(){
        SistemaDomotico.printListUnita(listaUnitaImmobiliari);
        UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Scegli l'unità immobiliare ", listaUnitaImmobiliari) - 1);

        printTitleAndList("LISTA ATTUATORI", unitaSel.getListaAttuatoriUnita());
        Attuatori attuatore = unitaSel.getListaAttuatoriUnita().get(chooseFromList("Scegli l'attuatore da impostare > ", unitaSel.getListaAttuatoriUnita())-1);

        printTitleAndList("MODALITA' OPERATIVE", attuatore.getCategoria().getListModalitaOperative());
        ModalitaOperative mod = attuatore.getCategoria().getListModalitaOperative().get(chooseFromList("Scegli la modalità operativa > ", attuatore.getCategoria().getListModalitaOperative())-1);

        attuatore.setParametroAssegnato(mod);
    }

    public static void AttivazioneSensori(){
        SistemaDomotico.printListUnita(listaUnitaImmobiliari);
        UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Scegli l'unità immobiliare ", listaUnitaImmobiliari) - 1);

        printTitleAndList("LISTA SENSORI", unitaSel.getListaSensoriUnita());
        Sensori sensore = unitaSel.getListaSensoriUnita().get(chooseFromList("Scegli il sensore da impostare > ", unitaSel.getListaSensoriUnita())-1);

        if(sensore.getAttivo()) sensore.setAttivo(false);
        else sensore.setAttivo(true);
    }

    public static void AttivazioneAttuatori(){
        SistemaDomotico.printListUnita(listaUnitaImmobiliari);
        UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Scegli l'unità immobiliare ", listaUnitaImmobiliari) - 1);

        printTitleAndList("LISTA ATTUATORI", unitaSel.getListaAttuatoriUnita());
        Attuatori attuatore = unitaSel.getListaAttuatoriUnita().get(chooseFromList("Scegli l'attuatore da impostare > ", unitaSel.getListaAttuatoriUnita())-1);

        if(attuatore.getAttivo()) attuatore.setAttivo(false);
        else attuatore.setAttivo(true);
    }

    public static void AttivazioneRegole(){
        printTitleAndList("Regole create", listaNomiRegole);
        Regole regola = listaRegole.get(chooseFromList("Scegli la regola ", listaNomiRegole)-1);

        if(regola.getAttivo()) {
            regola.setAttivo(false);
            listaRegoleApplicate.remove(regola.getNome());
        }
        else {
            regola.setAttivo(true);
            listaRegoleApplicate.add(regola.getNome());
        }
    }


}
