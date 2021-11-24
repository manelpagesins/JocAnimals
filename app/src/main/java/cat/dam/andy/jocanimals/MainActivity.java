package cat.dam.andy.jocanimals;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static ArrayList<Integer> imatgesAnimals = new ArrayList<Integer>();
    static ArrayList<ImageView> animals_esquerra = new ArrayList<ImageView>();
    static ArrayList<ImageView> animals_dreta = new ArrayList<ImageView>();
    static int numeroTaulaEsquerra = 0;
    static int numeroTaulaDreta = 0;

    RelativeLayout Taula_Esquerra;
    RelativeLayout Taula_Dreta;

    /**
     * Funcio que ens permetra afegir imatges dins del arraylist que guarda les imatges dels animals que apareixen
     */
    public static void InserirImatges(){

        imatgesAnimals.add(R.drawable.castor);
        imatgesAnimals.add(R.drawable.dofi);
        imatgesAnimals.add(R.drawable.eriso);
        imatgesAnimals.add(R.drawable.lleo);

    }

    /**
     * Funcio per inserir un numero totalment random de animals a la taula de la esquerra
     * @param Taula_Esquerra  RelativeLayout en el que es situaran les imatges
     * @param numRand Numero random per aconseguir una foto dins del arraylist de imatges
     * @param numeroGenerats Numero random de animals que apareixaran al RelativeLayout
     */
    public static void InserirAnimal_Taula1(RelativeLayout Taula_Esquerra, int numRand,int numeroGenerats, ImageView animal1){

        for(int i = 0; i < numeroGenerats; i++){
            animals_esquerra.add(animal1);
            animals_esquerra.get(i).setId(i);
            animals_esquerra.get(i).setImageResource(imatgesAnimals.get(numRand));
            animals_esquerra.get(i).setVisibility(View.VISIBLE);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);

            if(i == 0){

                params.addRule(RelativeLayout.CENTER_IN_PARENT);

            }else if( i >= 1){


                switch (animals_esquerra.get(i).getId()){

                    case 1:
                        params.addRule(RelativeLayout.RIGHT_OF, animals_esquerra.get(0).getId());
                        break;

                    case 2:
                        params.addRule(RelativeLayout.LEFT_OF, animals_esquerra.get(0).getId());

                        break;

                    case 3:
                        params.addRule(RelativeLayout.ABOVE, animals_esquerra.get(0).getId());
                        break;

                    case 4:
                        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, animals_esquerra.get(0).getId());

                        break;

                    case 5:
                        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, animals_esquerra.get(0).getId());
                        params.addRule(RelativeLayout.LEFT_OF, animals_esquerra.get(0).getId());

                        break;
                }

            }else{

                if(animals_esquerra.get(i).getParent() != null){
                    ((ViewGroup)animals_esquerra.get(i).getParent()).removeView(animals_esquerra.get(i));
                }
                Taula_Esquerra.addView(animals_esquerra.get(i));
            }

        }
    }

    /**
     * Funcio per inserir un numero totalment random de animals a la taula de la dreta
     * @param Taula_Dreta  RelativeLayout en el que es situaran les imatges
     * @param numRand Numero random per aconseguir una foto dins del arraylist de imatges
     * @param numeroGenerats Numero random de animals que apareixaran al RelativeLayout
     */
    public static void InserirAnimal_Taula2(RelativeLayout Taula_Dreta, int numRand,int numeroGenerats, ImageView animal2){

        for(int i = 0; i < numeroGenerats; i++){
            animals_dreta.add(animal2);
            animals_dreta.get(i).setId(i);
            animals_dreta.get(i).setImageResource(imatgesAnimals.get(numRand));
            animals_dreta.get(i).setVisibility(View.VISIBLE);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);

            if( i == 0){

                params.addRule(RelativeLayout.CENTER_IN_PARENT);

            }else if( i >= 1){

                        switch (animals_dreta.get(i).getId()){

                            case 1:
                                params.addRule(RelativeLayout.CENTER_IN_PARENT);
                                    break;

                            case 2:
                                params.addRule(RelativeLayout.LEFT_OF, R.id.animal2);

                                    break;

                            case 3:
                                params.addRule(RelativeLayout.ABOVE, R.id.animal2);
                                    break;

                            case 4:
                                params.addRule(RelativeLayout.ALIGN_PARENT_TOP, R.id.animal2);

                                    break;

                            case 5:
                                params.addRule(RelativeLayout.ALIGN_PARENT_TOP, animals_dreta.get(0).getId());
                                params.addRule(RelativeLayout.LEFT_OF, R.id.animal2);

                                    break;
                        }

            }else{

                if(animals_dreta.get(i).getParent() != null){
                    ((ViewGroup)animals_dreta.get(i).getParent()).removeView(animals_dreta.get(i));
                }
                Taula_Dreta.addView(animals_dreta.get(i));
            }

        }
    }

    /**
     * Funcio en la que mantindrem una sessio de joc
     * @param Taula_Esquerra
     * @param Taula_Dreta
     * @param animal1
     * @param animal2
     */
    public static void SessioDeJoc(RelativeLayout Taula_Esquerra, RelativeLayout Taula_Dreta, ImageView animal1, ImageView animal2){

        Random rand = new Random();

        int numeroAnimalTaula1;
        int numeroAnimalTaula2;

        do {
            numeroTaulaEsquerra = rand.nextInt(5);
            numeroTaulaDreta = rand.nextInt(5);
            numeroAnimalTaula1 = rand.nextInt(4);
            numeroAnimalTaula2 = rand.nextInt(4);
        }while(numeroAnimalTaula1 != numeroAnimalTaula2 && numeroTaulaDreta != 0 && numeroTaulaEsquerra != 0);

        InserirAnimal_Taula1(Taula_Esquerra, numeroAnimalTaula1, numeroTaulaEsquerra, animal1);

        InserirAnimal_Taula2(Taula_Dreta, numeroAnimalTaula2, numeroTaulaDreta, animal2);

    }

    /**
     * Funcio en la que podrem saber la resposta al joc amb la interaccio del usuari
     * @param numAccio - numero integer generat depenguent del buto que ha fet click el usuari
     */
    public static void DecissioUsuari(int numAccio,TextView Resultat){

        int numGenerat = 0;

        if(numeroTaulaEsquerra > numeroTaulaDreta){
            numGenerat = 1;
        }
        else if(numeroTaulaEsquerra == numeroTaulaDreta){
            numGenerat = 2;
        }else{
            numGenerat = 3;
        }

        if(numGenerat == numAccio){
            Resultat.setText("CORRECTE");
        }else{
            Resultat.setText("FALLAT");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Taula_Esquerra = (RelativeLayout) findViewById(R.id.Taula_Esquerra);
        Taula_Dreta = (RelativeLayout) findViewById(R.id.Taula_Dreta);

        TextView Resultat = (TextView) findViewById(R.id.Resultat);

        ImageView animal1 = (ImageView) findViewById(R.id.animal1);
        ImageView animal2 = (ImageView) findViewById(R.id.animal2);

        InserirImatges();

        SessioDeJoc(Taula_Esquerra, Taula_Dreta, animal1, animal2);

            ImageButton Major = (ImageButton) findViewById(R.id.Buto_MajorQue);
            Major.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int numero = 1;
                    DecissioUsuari(numero,Resultat);
                }
            });

            ImageButton Menor = (ImageButton) findViewById(R.id.Buto_MenorQue);
            Menor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int numero = 2;
                    DecissioUsuari(numero, Resultat);

                }
            });

            ImageButton Igual = (ImageButton) findViewById(R.id.Buto_Igual);
            Igual.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int numero = 3;
                    DecissioUsuari(numero, Resultat);

                }
            });

    }
}