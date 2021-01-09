package Tira2020.Tietorakenteet;

/**
 * Jono tietorakenteen toteutus, käsittelee int tyyppisiä arvoja
 * <p>
 * @author Markus Linjamäki (markus.linjamaki@tuni.fi)
 */
public class Jono{
    public static final int defaultN = 5000000;
    private int N;
    private int first;	// viite ensimmäiseen alkioon
    private int rear;	// viite seuraavaan vapaaseen alkioon
    private int items;
	
    private Object D[];
	
    public Jono(){
	this(defaultN);
    }
	
    public Jono(int size){
	first = 0;
	rear = 0;
	items = 0;
	N = size;
	D = new Object[N];
    }
	
    /*
    boolean IsEmpty ()
    palauttaa true, jos jono on tyhj�, muutoin false.
    returns true, if queue is empty, false otherwise 
    */
    public boolean IsEmpty (){
        return items == 0;
    }


    // palauttaa jonon ensimmäisen alkion
    public Object pop (){
	Object result = null;
	if (! IsEmpty ()){
            result = D[first];
            D[first] = null;
            first = (first + 1) % N;
            items--;
        }
        else{
            System.out.println("JONO TÄYNNÄ!");
        }
	return result;
    }

    // lisää alkion jonon loppuun
    public void push (Object o){
        if(items < N){
            D[rear] = o;
            rear = (rear + 1 + N) % N;
            items++;
        }
        else{
            System.out.println("JONO TÄYNNÄ!");
        }
    }
    // kertoo onko jono tyhjä
    public boolean isEmpty(){
        return items == 0;
    }
}
