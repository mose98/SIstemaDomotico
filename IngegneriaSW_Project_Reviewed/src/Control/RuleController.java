package Control;

import Model.Attuatori;
import Model.ModalitaOperative;
import Model.Rilevazioni;
import Model.Sensori;
import View.StaticVariables;
import mylib.InputDati;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimerTask;

import static Control.Util.*;

public class RuleController implements StaticVariables {
    static String stringaRegola = null;

    public static Regole createRegola(){
        Regole rule=null;

        String nome = readString("Dai un nome alla regola > ");

        printNewLineAndTitle("COSTRUZIONE ANTECEDENTE\n==========================");
        Antecedente ant = createAntecedente();

        printNewLineAndTitle("COSTRUZIONE CONSEGUENTE\n==========================");
        Conseguente cons = createConseguente();

        if(ant!=null && cons!=null) {
            rule = new Regole(nome, ant, cons);

            listaRegole.add(rule);
            listaNomiRegole.add(rule.getNome());
            listaRegoleFormat.add(stringaRegola);
        }
        return rule;
    }

    public static Regole createRegolaTime(){
        Regole rule=null;

        String nome = readString("Dai un nome alla regola > ");

        printNewLineAndTitle("COSTRUZIONE ANTECEDENTE\n==========================");
        Antecedente ant = createAntecedenteTime();

        printNewLineAndTitle("COSTRUZIONE CONSEGUENTE\n==========================");
        Conseguente cons = createConseguente();

        if(ant!=null && cons!=null) {
            rule = new Regole(nome, ant, cons);

            listaRegole.add(rule);
            listaNomiRegole.add(rule.getNome());
            listaRegoleFormat.add(stringaRegola);
        }
        return rule;
    }

    public static Antecedente createAntecedente(){
        Antecedente antecedente = null;
        String operatoreSel = null;
        int rilevazioneNumSel2=0;
        Rilevazioni rilevazioneSel2=null;

        printTitleAndList("LISTA SENSORI", listaNomiSensori);
        Sensori sensoreSel = listaSensori.get(chooseFromList("Selezionare il sensore da verificare per l'antecedente > ", listaSensori)-1);

        printTitleAndList("LISTA RILEVAZIONI", sensoreSel.getCategoria().getRilevazione());
        Rilevazioni rilevazioneSel = sensoreSel.getCategoria().getRilevazione().get(chooseFromList("Selezionare l'informazione > ",sensoreSel.getCategoria().getRilevazione())-1);

        stringaRegola = "IF " + sensoreSel.getNome() + "." + rilevazioneSel.getNome();
        printNewLineAndTitle(stringaRegola);

        operatoreSel = operatori[chooseFromList("Selezionare l'operatore > ", Arrays.asList(operatori.clone()))-1];
        stringaRegola = stringaRegola + " " + operatoreSel;
        printNewLineAndTitle(stringaRegola);

        int sceltaSecondoOp = Character.getNumericValue(chooseInsert("Vuoi verificare l'uguaglianza con un' altra rilevazione o con una costante? \nselezionare '1' per costante \nselezionare '2' per rilevazione", '1', '2'));

        if(sceltaSecondoOp == 1){
            if(rilevazioneSel.getNumerica()){
                 rilevazioneNumSel2 = readInteger("Inserire una costante che stia nel dominio della rilevazione [" + rilevazioneSel.getMinimo() + ";" + rilevazioneSel.getMassimo() + "] > ", rilevazioneSel.getMinimo(), rilevazioneSel.getMassimo());
                 stringaRegola = stringaRegola + " "+ rilevazioneNumSel2;
                 printNewLineAndTitle(stringaRegola);
                 antecedente = new Antecedente(rilevazioneSel, operatoreSel, rilevazioneNumSel2);
            }
            else printNewLineAndTitle("Selezionare una rilevazione numerica");
        } else{
            printTitleAndList("LISTA SENSORI", listaNomiSensori);
            sensoreSel = listaSensori.get(chooseFromList("Selezionare il sensore > ", listaSensori)-1);

            printTitleAndList("LISTA RILEVAZIONI", sensoreSel.getCategoria().getRilevazione());
            rilevazioneSel2 = sensoreSel.getCategoria().getRilevazione().get(chooseFromList("Selezionare l'informazione > ",sensoreSel.getCategoria().getRilevazione())-1);

            antecedente = new Antecedente(rilevazioneSel, operatoreSel, rilevazioneSel2);
            stringaRegola = stringaRegola + " " + sensoreSel + "." + rilevazioneSel2;
            printNewLineAndTitle(stringaRegola);
        }

        return antecedente;
    }

    public static Antecedente createAntecedenteTime(){
        Antecedente antecedente = null;

        System.out.println("Ora bisognera' impostare l'orario 'time' nella forma HH:mm");
        System.out.println();

        int ora_time;
        int minuto_time;

        int operatoreSelint;
        String operatoreSel = null;

        System.out.println("Operatori booleani \n   1) >\n   2) <\n   3) ==");
        do {
            operatoreSelint = InputDati.leggiIntero("Selezionare l' operazione desiderata > ");
            if (operatoreSelint < 1 || operatoreSelint > 3)
                System.out.println("ATTENZIONE: l' operatore selezionato non e' presente nell'elenco!!!");
        } while (operatoreSelint < 1 || operatoreSelint > 3);
        if (operatoreSelint == 1) operatoreSel = ">";
        if (operatoreSelint == 2) operatoreSel = "<";
        if (operatoreSelint == 3) operatoreSel = "==";

        do {
            ora_time = InputDati.leggiIntero("Scegliere l'ora (compresa tra 00 e 23) > ");
            if (ora_time < 00 || ora_time > 23)
                System.out.println("ATTENZIONE: l'ora deve essere compresa tra 00 e 23!!!");
        } while (ora_time < 00 || ora_time > 23);

        System.out.println();

        do {
            minuto_time = InputDati.leggiIntero("Scegliere i minuti (compresi tra 00 e 59) > ");
            if (minuto_time < 00 || minuto_time > 59)
                System.out.println("ATTENZIONE: il minuto deve essere compresa tra 00 e 59!!!");
        } while (minuto_time < 00 || minuto_time > 59);

        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, ora_time);
        time.set(Calendar.MINUTE, minuto_time);

        stringaRegola = "IF 'time' " +operatoreSel+" "+ time.toString();

        antecedente = new Antecedente(time, operatoreSel);

        return antecedente;
    }

    public static Conseguente createConseguente(){
        Conseguente conseguente = null;
        Attuatori attuatoreSel = null;
        ModalitaOperative modalitaSel = null;

        printTitleAndList("LISTA ATTUATORI", listaNomiAttuatori);
        attuatoreSel = listaAttuatori.get(chooseFromList("Selezionare il sensore > ", listaAttuatori)-1);

        modalitaSel = attuatoreSel.getCategoria().getListModalitaOperative().get(chooseFromList("Selezionare la modalità operativa > ", attuatoreSel.getCategoria().getListModalitaOperative())-1);
        String parametro = modalitaSel.getListaParametri().get(chooseFromList("Seleziona il parametro > ", modalitaSel.getListaParametri())-1);

        stringaRegola = stringaRegola + " THEN " + attuatoreSel.getNome() + "." + parametro;
        printNewLineAndTitle(stringaRegola);

        conseguente = new Conseguente(attuatoreSel, parametro);
        return  conseguente;
    }
}


