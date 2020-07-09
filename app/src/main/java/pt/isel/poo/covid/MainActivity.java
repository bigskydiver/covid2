package pt.isel.poo.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import pt.isel.poo.covid.model.Level;
import pt.isel.poo.covid.model.Loader;
import pt.isel.poo.covid.tile.TilePanel;

public class MainActivity extends AppCompatActivity {

    private Button leftButton;
    private Button rightButton;
    Scanner in;
    private static final String FILE_NAME = "covid_levels.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int currentLvl = 1;
        final int HEIGHT  = 9;
        final int WIDTH = 9;
        in = new Scanner(FILE_NAME);
        Level nivel = new Level(0,0,0);

        try {
            nivel.loadslvl(in);
        } catch (Loader.LevelFormatException e) {
            e.printStackTrace();
        }
        int levelnbr = 1 ;
        final TilePanel panel = findViewById(R.id.tilePanel) ;
        leftButton = findViewById(R.id.button_left);
        leftButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

              }
        }
        );
        rightButton = findViewById(R.id.button_right);
        rightButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

              }
          }
        );



    }

    public void init(){



    }

}
