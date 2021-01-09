package Tira2020.Luokat;

/**
 * Konkreettinen Piste luokka, joka sisältää pisteelle omaavat (x,y)
 * koordinaatit. Toimii tietyn Solmun arvona
 * <p>
 * @author Markus Linjamäki (markus.linjamaki@tuni.fi)
 */

public class Piste {
    private int x;
    private int y;



    public Piste(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int x(){
        return x;
    }
    public int y(){
        return y;
    }
    //pisteet ovat samat jos niillä on samat koordinaatit
    @Override
    public boolean equals(Object obj){
        try{
            Piste toinen = (Piste)obj;
            return (toinen.x == x && toinen.y == y);
        }
        catch(Exception e){
            return false;
        }
    }
}

