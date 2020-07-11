package pt.isel.poo.covid.view;

import java.util.List;

import pt.isel.poo.covid.Position;
import pt.isel.poo.covid.model.Hero;
import pt.isel.poo.covid.model.Level;
import pt.isel.poo.covid.model.Space;
import pt.isel.poo.covid.model.TrashCan;
import pt.isel.poo.covid.model.Virus;
import pt.isel.poo.covid.model.Wall;
import pt.isel.poo.covid.model.element;
import pt.isel.poo.covid.tile.Tile;
import pt.isel.poo.covid.tile.TilePanel;
import pt.isel.poo.covid.view.HeroTile;

public class LevelView {
    private final TilePanel panel;
    private  final Level model;
    public LevelView(TilePanel panel , final Level model){
        this.panel = panel;
        this.model = model;
        init();

    }


    public void init() {
        for(int x = 0; x < model.height; ++x) {
            for(int y = 0; y < model.width; ++y) {
                updateTile(new Position(x,y));
            }
        }
    }

    public void updateTile (Position pos ){
        final Tile tile = createTile( model.getElement(pos));
        panel.setTile(pos.x, pos.y, tile);
    }


    public Tile createTile(element elem){


        if ( elem instanceof  Hero) return new HeroTile(panel.getContext(), (Hero) elem) ;
            else if ( elem instanceof Wall) return new WallTile(panel.getContext(),(Wall) elem) ;
            else if ( elem instanceof Virus)return  new VirusTile(panel.getContext(),(Virus) elem) ;
            else if ( elem instanceof TrashCan) return  new TrashTile(panel.getContext(),(TrashCan) elem);

        return null;
    }



}
