package View;

import Model.*;

import static Control.Util.chooseFromList;
import static View.StaticVariables.listaUnitaImmobiliari;

public class VistaParametri{
    public static void ValoriSensori(){
        int index=0;
        SistemaDomotico.printListUnita(listaUnitaImmobiliari);
        UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("In che unita' immobiliare si desidera inserire il sensore? ", listaUnitaImmobiliari) - 1);

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
}
