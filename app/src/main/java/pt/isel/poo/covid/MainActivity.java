package pt.isel.poo.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.TimeAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import pt.isel.poo.covid.model.Direction;
import pt.isel.poo.covid.model.Hero;
import pt.isel.poo.covid.model.Level;
import pt.isel.poo.covid.model.Loader;
import pt.isel.poo.covid.model.Virus;
import pt.isel.poo.covid.tile.TilePanel;
import pt.isel.poo.covid.view.LevelView;

public class MainActivity extends AppCompatActivity {
    private Hero hero;
    private Virus virus;
    private Button leftButton;
    private Button rightButton;
    private Button endbutton;
    private LevelView levelView;
    private Level nivel;
    private TextView out;
    private Scanner in;
    private final String FILE_NAME = "covid_levels.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int currentLvl = 1;
        final int HEIGHT  = 9;
        final int WIDTH = 9;
        final Direction right = new Direction(0,-1);
        final Direction left = new Direction(0,1);
        final Direction down = new Direction(-1,0);
        nivel = new Level(1,9,9);
        System.out.println(" aqui");

        try {
            in = new Scanner(getAssets().open(FILE_NAME));
            nivel = nivel.loadslvl(in,1);
            nivel.Levelprint();

        } catch (Loader.LevelFormatException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final TilePanel panel = findViewById(R.id.tilePanel) ;
        levelView=new LevelView(panel,nivel);

        hero = (Hero) nivel.getHero();
        out = findViewById(R.id.textView);
        final TimeAnimator animator = new TimeAnimator();
        animator.setTimeListener(new TimeAnimator.TimeListener() {
            int elapsedTime = 0;
            int interval = 250;
            @Override
            public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
                //Handles gravity
                if(! nivel.CheckifDead( nivel.getHero().pos,down) && !(nivel.getVirusLength()==0)){
                    if (elapsedTime >= interval) {
                        elapsedTime = 0;

                        nivel.moveHero(down);

                        for (int i = 0; i < nivel.getVirusLength(); ++i) {
                        virus = nivel.getVirus(i);
                         nivel.moveVirus(down, i);

                        }

                    levelView.init();
                    } else {
                        elapsedTime += deltaTime;
                    }

                }else {
                    endbutton.setVisibility( View.VISIBLE);
                    if(nivel.getVirusLength()==0){
                        out.setText( "WIN");

                    }else out.setText( "Lost");

                }
            }

        });
        animator.start();
        endbutton = findViewById(R.id.endbutton);
        endbutton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  System.exit(0);
              }
          }
        );



        leftButton = findViewById(R.id.button_left);
        leftButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  hero = (Hero) nivel.getHero();

                      nivel.moveHero(left);
                      levelView.init();

              }
        }
        );
        rightButton = findViewById(R.id.button_right);
        rightButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
               hero = (Hero) nivel.getHero();

                      nivel.moveHero(right);
                      levelView.init();

              }
          }
        );

    }



}
