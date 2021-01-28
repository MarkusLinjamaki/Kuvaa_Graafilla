package Tira2020.Kauttoliittyma;
import Tira2020.Graafi.Graafi;
import Tira2020.Luokat.Piste;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.image.Image;  
import javafx.scene.image.PixelReader; 
import javafx.scene.image.PixelWriter; 
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color; 
import javafx.scene.image.ImageView; 
import javafx.stage.Stage;  
import javafx.scene.control.Button;
import javafx.scene.control.TextField; 
import javafx.scene.text.Text; 
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent; 
import javafx.event.ActionEvent;
import java.util.Random;
import java.io.FileWriter; 
import java.io.IOException;
//Tira 2020 harjoitustyön pohja. 
//Muokattu https://www.tutorialspoint.com/javafx/index.htm esimerkeistä.

public class Kayttoliittyma extends Application {  
    private static Color MUSTA = Color.BLACK;
    private static Color VALKOINEN = Color.WHITE;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Image image;
    WritableImage wImage;
    double width;
    double height;
    ImageView imageView1;
    ImageView imageView2;
    
    int greyList[][];
    Piste aloitusPiste;
    int kokonaisEro;
    int intensiteettiEro;
    
    @Override 
    public void start(Stage stage) throws FileNotFoundException {         
        //Creating an image 	  	  
        image= new Image(new FileInputStream("Testikuva.jpg"));  
        width=image.getWidth();
	height=image.getHeight();	   
        System.out.println(height  + " " +  width);
        // update greylist
        greyList = new int[(int)height][(int)(width)];
        
        PixelReader pixelReader = image.getPixelReader();       
        //Reading through the image. 
        for(int y = 0; y < height; y++) { 
            for(int x = 0; x < width; x++) { 
                //Retrieving the color of the pixel of the loaded image   
                Color color = pixelReader.getColor(x, y); 
 
                int r = (int) Math.round(color.getRed() * 255);
                int g = (int) Math.round(color.getGreen() * 255);
                int b = (int) Math.round(color.getBlue() * 255);
                int grayscale = (int) Math.round(0.3*r+0.59*g+0.11*b);
                greyList[y][x] = grayscale;
            }
        }
        
        //Setting the image view 1 
        imageView1 = new ImageView(image); 
      
        //Setting the position of the image 
	//HUOM! Tämä vaikuttaa hiiren koordinaatteihin kuvassa.
        imageView1.setX(50); 
        imageView1.setY(25); 
      
        //setting the fit height and width of the image view 
        imageView1.setFitHeight(height/2); 
        imageView1.setFitWidth(width/2);         
      
        //Setting the preserve ratio of the image view 
        imageView1.setPreserveRatio(true); 
         
        //Setting the image view 2 
        imageView2 = new ImageView(image);
      
        //Setting the position of the image 
        imageView2.setX(width/2+60); 
        imageView2.setY(25); 
      
        //setting the fit height and width of the image view 
        imageView2.setFitHeight(height/2); 
        imageView2.setFitWidth(width/2);          
      
        //Setting the preserve ratio of the image view 
        imageView2.setPreserveRatio(true);          
        int delta=50;
	Text text1 = new Text("Anna sallittu intensiteettiero");  
	text1.setLayoutX(50);
	text1.setLayoutY(height/2+delta);
	TextField textField1 = new TextField(); 
	textField1.setText("5");
	textField1.setLayoutX(50);
	textField1.setLayoutY(height/2+1.3*delta);
	  
	Text text2 = new Text("Anna sallittu kokonaisero");  
	text2.setLayoutX(50);
	text2.setLayoutY(height/2+2.8*delta);
	TextField textField2 = new TextField();
	textField2.setText("5");
	textField2.setLayoutX(50);
	textField2.setLayoutY(height/2+3.1*delta);
        
        button1 = new Button("Hae yksi komponentti syvyyshaulla");
	button1.setLayoutX(50);
	button1.setLayoutY(height/2+4*delta);
	  
	button2 = new Button("Hae yksi komponentti leveyshaulla");
	button2.setLayoutX(50);
	button2.setLayoutY(height/2+4.8*delta);
	  
	button3 = new Button("Hae yhden komponentin minimiVPuu");
	button3.setLayoutX(50);
	button3.setLayoutY(height/2+5.5*delta);
	  
	button4 = new Button("Hae kaikki komponentit");
	button4.setLayoutX(50);
	button4.setLayoutY(height/2+6.2*delta);
	  
	EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) {
                //Luetaan tekstikenttien tiedot.
                String txt1=textField1.getText();
                String txt2=textField2.getText();
                // koitetaan muuntaa luvuiksi
                try{
                    intensiteettiEro = Integer.parseInt(txt1);
                    kokonaisEro = Integer.parseInt(txt2);
                }
                catch(Exception err){
                    System.out.println("Syötä oikeat arvot!");
                }
		//Valitaan suoritettava tehtävä.
		if(e.getSource()==button1)
                    Syvyyshaku(); 
		if(e.getSource()==button2)
                    Leveyshaku();
		if(e.getSource()==button3)
                    MinVPuu();			  
		if(e.getSource()==button4)
                    Kaikki();
                    System.out.println("Eka teksti " + txt1);
                    System.out.println("Toka teksti "+ txt2);
            } 
        }; 
        
	button1.setOnAction(event);
	button2.setOnAction(event);
	button3.setOnAction(event);
	button4.setOnAction(event);
        
	EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) {             
                double x = e.getX();
                double y = e.getY();
                aloitusPiste = new Piste((int) ((x - 50) * 2 ), (int) ((y - 25) * 2));   // onkohan oikeinpäin 
                System.out.println("Hiiren klikkaus rivilla "+ y+ " ja sarakkeella "+ x);
                System.out.println("Aloituspiste = " + aloitusPiste.y() + " " + aloitusPiste.x());
                //HUOM! Näkyvä kuvan korkeus ja leveys on puolet varsinaisesta kuvasta.
                //Lisäksi näkyvän kuvan vasen yläreuna on kohdassa(50,25).
                //Kuvassa tarvitaan kokonaislukuja.
            } 
        };  
	  
	imageView1.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        //Creating a Group object  
        Group root = new Group(imageView1, imageView2, text1, textField1, 
	text2, textField2,button1,button2,button3,button4);  
      
        //Creating a scene object 
        Scene scene = new Scene(root, width+100, height*0.85);  
      
        //Setting title to the Stage 
        stage.setTitle("Harkka 2020");  
      
        //Adding scene to the stage 
        stage.setScene(scene);  
      
        //Displaying the contents of the stage
        stage.show(); 
    }  
   //Kuvan piirtämistä varten.
    public void ManipulateImage(Graafi graafi){

        wImage = new WritableImage((int)width, (int)height);	   
        PixelReader pixelReader = image.getPixelReader();      
        PixelWriter writer = wImage.getPixelWriter();  
        //Reading through the image. 
        for(int y = 0; y < height; y++) { 
            for(int x = 0; x < width; x++) { 
                if(graafi.solmuTaulu(x,y)){
                    writer.setColor(x, y, VALKOINEN);
                }
                else{
                    writer.setColor(x, y, MUSTA);
                }
            }
        }	
        imageView2.setImage(wImage);
    }
   
    public void Syvyyshaku(){
        Graafi g = new Graafi(greyList,aloitusPiste,intensiteettiEro, kokonaisEro,1);
        ManipulateImage(g);
        
    }
   
    public void Leveyshaku(){
        Graafi g = new Graafi(greyList,aloitusPiste,intensiteettiEro, kokonaisEro,2);
        ManipulateImage(g);
    }
   
    public void MinVPuu(){
        Graafi g = new Graafi(greyList,aloitusPiste,intensiteettiEro, kokonaisEro,3);
        ManipulateImage(g);
    }
   
    public void Kaikki(){
        try {
            FileWriter myWriter = new FileWriter("Graafi.txt");
            for(int i=0; i<10; i++)
                myWriter.write("Rivi"+String.valueOf(i)+"\n");
                myWriter.close();
      
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]) {
        launch(args);
   } 
}        
