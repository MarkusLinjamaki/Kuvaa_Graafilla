package Tira2020.Luokat;

/**
 * Konkreettinen Kaari luokka, sisältää kaarille tyypilliset piirteet
 * Perii GraafinOsa luokan
 * <p>
 * @author Markus Linjamäki (markus.linjamaki@tuni.fi)
 */

public class Kaari extends GraafinOsa implements Comparable<Kaari> {
    
    // alkusolmu
    private Solmu alkuSolmu;
    // loppusolmu
    private Solmu loppuSolmu;
    // arvo 
    private int arvo;
    //paikka
    private int paikka;

    public Kaari(Solmu a, Solmu e,int ar,int pa){
        alkuSolmu = a;
        loppuSolmu = e;
        arvo = ar;
        paikka = pa;
    }
    public Solmu alkuSolmu(){
        return alkuSolmu;
    }
    public Solmu loppuSolmu(){
        return loppuSolmu;
    }
    public int arvo(){
        return arvo;
    }
    @Override
    // Kaaret ovat samat, jos niiden alku ja loppu solmut ovat samat, kyseinen graafi on suuntaamaton
    public boolean equals(Object obj){
        try{
            Kaari toinen = (Kaari)obj;
            return ((toinen.alkuSolmu().equals(alkuSolmu) && toinen.loppuSolmu().equals(loppuSolmu)) || (toinen.alkuSolmu().equals(loppuSolmu) && toinen.loppuSolmu().equals(alkuSolmu)));
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public int compareTo(Kaari toinen) {
        if(arvo < toinen.arvo()){
            return -1;
        }
        else if(arvo == toinen.arvo()){
            return 0;
        }
        else{
            return 1;
        }
    }
}
