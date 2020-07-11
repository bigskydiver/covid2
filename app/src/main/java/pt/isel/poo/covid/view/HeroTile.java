package pt.isel.poo.covid.view;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import pt.isel.poo.covid.R;
import pt.isel.poo.covid.tile.Img;

import pt.isel.poo.covid.tile.Tile;
import pt.isel.poo.covid.model.Hero;

public class HeroTile implements Tile {
    private final Hero hero;
    private final Img HeroImage;
    private Img DeadHeroImage;
    private final Paint brush;

    public HeroTile(Context ctx, Hero hero){
        brush = new Paint();
        HeroImage= new Img(ctx, R.drawable.nurse);
        this.hero=hero;
    }

    @Override
    public void draw(Canvas canvas, int side) {
        HeroImage.draw(canvas, side, side,0,brush);
    }

    @Override
    public boolean setSelect(boolean selected) {
        return false;
    }
}
