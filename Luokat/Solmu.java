package Tira2020.Luokat;

/**
 * Konkreettinen Solmu luokka, sisältää solmuille tyypilliset piirteet Perii
 * GraafinOsa luokan
 * <p>
 * @author Markus Linjamäki (markus.linjamaki@tuni.fi)
 */

public class Solmu extends GraafinOsa{
    // viittaus alkioon
    private Piste piste;
    // sijainti sekvenssissä
    private int sijainti;

    public Solmu(Piste p, int s){
        piste(p);
        sijainti(s);
    }
    public Piste piste(){
        return piste;
    }
    public void piste(Piste p){
        if(p != null){
            piste = p;
        }
    }
    public void sijainti(int i){
        if(0 <= i){
            sijainti = i;
        }
    }
    public int sijainti(){
        return sijainti;
    }
    
    @Override
    // solmut ovat samat, jos niiden arvo eli pisteen koordinaatit ovat samat
    public boolean equals(Object obj){
        try{
            Solmu toinen = (Solmu)obj;
            return (toinen.piste).equals(piste);
        }
        catch(Exception e){
            return false;
        }
    }
}
