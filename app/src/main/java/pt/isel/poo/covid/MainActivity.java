package pt.isel.poo.covid;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.TimeAnimator;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
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
    private Button leftButton,rightButton, endbutton,loadButton,saveButton;
    private LevelView levelView;
    private Level nivel;
    private TextView out;
    private TextView topPanel;
    private Scanner in;
    private  String FILE_NAME = "covid_levels.txt";
    private final String  SAVED_LEVEL = "saved_levels.txt";
    private final String  SAVED_TILES ="saved_tiles.txt";
    private int currentLvl = 1;
    private int savedLvl ;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int HEIGHT  = 9;
        final int WIDTH = 9;
        final Direction right = new Direction(0,-1);
        final Direction left = new Direction(0,1);
        final Direction down = new Direction(-1,0);
        nivel = new Level(1,9,9);

        if(savedInstanceState != null){
            SharedPreferences prefs = getSharedPreferences("PreferencesName", MODE_PRIVATE);
            currentLvl = prefs.getInt("currentLvl", 0);
            FILE_NAME = SAVED_TILES;
            try {
                in = new Scanner(openFileInput(FILE_NAME));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            if ( savedInstanceState == null)in = new Scanner(getAssets().open(FILE_NAME));
            nivel = nivel.loadslvl(in,currentLvl);
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
        topPanel= findViewById(R.id.textView2);

        final TimeAnimator animator = new TimeAnimator();
        topPanel.setText("Level :"+ currentLvl + ", nr de virus: "+nivel.getVirusLength());
        animator.setTimeListener(new TimeAnimator.TimeListener() {
            int elapsedTime = 0;
            int interval = 500;
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
                        out.setText( "Level complete");

                    }else{
                        levelView.init();
                        out.setText( "Game Over");
                    }

                }
            }

        });
        animator.start();
        endbutton = findViewById(R.id.endbutton);
        endbutton.setOnClickListener(new View.OnClickListener() {
              @SuppressLint("SetTextI18n")
              @Override

              public void onClick(View v) {
                  String gameState = out.getText().toString();
                  if( gameState.equals("Game Over"))System.exit(0);

                  if(gameState.equals("Level complete")) {
                      try {
                          nivel.reset();
                          endbutton.setVisibility(View.VISIBLE);
                          nivel = nivel.loadslvl(in,currentLvl+1);
                          if(nivel==null){

                              out.setText("No more Levels");

                          }else {

                              levelView=new LevelView(panel,nivel);
                              levelView.init();
                              out.setText("");
                              endbutton.setVisibility(View.INVISIBLE);
                          }
                      } catch (Loader.LevelFormatException e) {
                          e.printStackTrace();
                      }
                  }
                  if(gameState.equals("No more levels")) System.exit(0);

                  if(gameState.equals("Nothing to Load"))endbutton.setVisibility( View.INVISIBLE);
              }
          }
        );
        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
              @RequiresApi(api = Build.VERSION_CODES.KITKAT)
              @Override
              public void onClick(View v) {
                  try (PrintStream output = new PrintStream(openFileOutput(SAVED_LEVEL, MODE_PRIVATE))) {
                      SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
                      editor.putInt("savedLevel", currentLvl);
                      editor.apply();

                      if (nivel != null) nivel.save(output, currentLvl);
                      savedLvl= currentLvl;
                  } catch (IOException e) {
                      e.printStackTrace();
                  }

              }
          }
        );
        loadButton = findViewById(R.id.load_Button);
        loadButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  SharedPreferences prefs = getSharedPreferences("PreferencesName", MODE_PRIVATE);
                  savedLvl = prefs.getInt("savedLevel", 0);
                  try {
                      in = new Scanner( openFileInput(SAVED_LEVEL));
                      nivel = nivel.loadslvl(in,savedLvl);
                      currentLvl= savedLvl;
                      hero = (Hero) nivel.getHero();
                      levelView = new LevelView(panel,nivel);
                     // System.out.println("printing load");
                     // nivel.Levelprint();
                      levelView.init();
                  } catch (FileNotFoundException | Loader.LevelFormatException e) {
                      e.printStackTrace();
                  }
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        try (PrintStream output = new PrintStream(openFileOutput(SAVED_TILES, MODE_PRIVATE))) {
            SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
            editor.putInt("currentLvl", currentLvl);
            editor.apply();

            if (nivel != null) nivel.save(output, currentLvl);
            savedLvl= currentLvl;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


