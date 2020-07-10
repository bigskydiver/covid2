package pt.isel.poo.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.TimeAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pt.isel.poo.covid.model.Direction;
import pt.isel.poo.covid.model.Level;
import pt.isel.poo.covid.model.Loader;
import pt.isel.poo.covid.model.Virus;
import pt.isel.poo.covid.tile.TilePanel;
import pt.isel.poo.covid.view.LevelView;

public class MainActivity extends AppCompatActivity {

    private Button leftButton;
    private Button rightButton;
    private LevelView levelView;
    private Level nivel;
    Scanner in;
    private static final String FILE_NAME = "covid_levels.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int currentLvl = 1;
        final int HEIGHT  = 9;
        final int WIDTH = 9;
        final Direction right = new Direction(-1,0);
        final Direction left = new Direction(1,0);
        final Direction down = new Direction(0,1);
        nivel = new Level(1,9,9);
        final TilePanel panel = findViewById(R.id.tilePanel) ;
        levelView = new LevelView(panel,nivel);
        File openedFile = new File(FILE_NAME);
        FileInputStream opendFile = null;
        try {
            opendFile= new FileInputStream(openedFile);
            in = new Scanner(opendFile);
            nivel = nivel.loadslvl(in);
            new LevelView(panel,nivel).init();
        } catch (Loader.LevelFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }




        final TimeAnimator animator = new TimeAnimator();
        animator.setTimeListener(new TimeAnimator.TimeListener() {
            int elapsedTime = 0;
            int interval = 350;
            @Override
            public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
                    //Handles gravity
                    if (elapsedTime >= 500) {
                        elapsedTime = 0;
                        nivel.moveElem(down,nivel.getHero());

                        for( int i = 0 ; i< nivel.getVirusLength(); ++i){

                            nivel.moveElem(down, nivel.getVirus(i));
                        }

                        levelView.init();
                    }
                    else {
                        elapsedTime += deltaTime;
                    }

            }
        });
        animator.start();

        leftButton = findViewById(R.id.button_left);
        leftButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  nivel.moveElem(left,nivel.getHero());
                  levelView.init();
              }
        }
        );
        rightButton = findViewById(R.id.button_right);
        rightButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  nivel.moveElem(right,nivel.getHero());
                  levelView.init();

              }
          }
        );



    }



}
