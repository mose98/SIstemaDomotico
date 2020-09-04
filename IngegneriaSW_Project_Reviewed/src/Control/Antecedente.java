package Control;

import Model.Rilevazioni;

import java.util.Calendar;

public class Antecedente {
    private Rilevazioni ant_fattore1;
    private String ant_operatore1;
    private Rilevazioni ant_fattore2;
    private String ant_stfattore2;
    private int ant_fattoreCost2;
    private Calendar time;

    public Antecedente(Rilevazioni ant_fattore1, String ant_operatore1, Rilevazioni ant_fattore2){
        this.ant_fattore1=ant_fattore1;
        this.ant_operatore1=ant_operatore1;
        this.ant_fattore2=ant_fattore2;
    }

    public Antecedente(Calendar time, String ant_operatore1){
        this.time=time;
        this.ant_operatore1=ant_operatore1;
    }

    public Antecedente(Rilevazioni ant_fattore1, String ant_operatore1, String ant_stfattore2){
        this.ant_fattore1=ant_fattore1;
        this.ant_operatore1=ant_operatore1;
        this.ant_stfattore2=ant_stfattore2;
    }

    public Antecedente(Rilevazioni ant_fattore1, String ant_operatore1, int ant_fattoreCost2){
        this.ant_fattore1=ant_fattore1;
        this.ant_operatore1=ant_operatore1;
        this.ant_fattoreCost2=ant_fattoreCost2;
    }

    public Calendar getTime() {
        return time;
    }

    public int getAnt_fattoreCost2() {
        return ant_fattoreCost2;
    }

    public Rilevazioni getAnt_fattore1() {
        return ant_fattore1;
    }

    public Rilevazioni getAnt_fattore2() {
        return ant_fattore2;
    }

    public String getAnt_operatore1() {
        return ant_operatore1;
    }

    public String getAnt_stfattore2() {
        return ant_stfattore2;
    }
}
