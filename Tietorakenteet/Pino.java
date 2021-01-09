package Tira2020.Tietorakenteet;

/**
 * Pino tietorakenteen toteutus, käsittelee int tyyppisiä arvoja
 * <p>
 * @author Markus Linjamäki (markus.linjamaki@tuni.fi)
 */
public class Pino{

    private int N; /* The current size of the array. */
    private Object[] A; /* The array for holding the objects of the stack. */


    private int n;

    public Pino(){
        N = 1;
        n = 0;
        A = new Object[N];
    }

    // palauttaa true jos Pino on tyhjä
    public boolean isEmpty(){
        return n == 0;
    }

    // palauttaa pinon koon
    public int arraySize(){
        return N;
    }

    // lisää alkion pinon päälle
    public void push( Object x ){
        if(n < N){	// voidaan lisätä normaalisti
            A[n] = x;
        }
	    else{
            Object[] temp = new Object[N * 2];
            // luetaan kaikki alkiot väliaikaiseen tiedostoon
            for(int i = 0; i < n; i++){
                temp[i] = A[i];
            }
            A = temp;
            A[n] = x;
            temp = null;
            N = N * 2;
	    }
	    n = n + 1;
    }


    // palauttaa pinon päällimmäisen alkion
    public Object pop (){
        if(n > 0){
            Object item = A[n - 1];
            A[n - 1] = null;
            n = n - 1;
            if(n <= (N / 4) && N >= 2){
                Object[] temp = new Object[N / 2];
                for(int a = 0; a < n; a++){
                    temp[a] = A[a];
                }
                A = temp;
                temp = null;
                N = N / 2;
            }
            return item;
        }
        return null;
    }
}
