package pt.isel.poo.covid.model;
import pt.isel.poo.covid.Position;
import pt.isel.poo.covid.model.Level;

public class Movemment {
    private final element[][] model;
    public Movemment(element[][] model){

      this.model = model;

    }

    public void moveHero (Direction dir){
       Position Heroposition = getHeroLocation();
        assert Heroposition != null;
        if(model[Heroposition.x  - dir.x ][Heroposition.y - dir.y].getElement() instanceof Space){

           model[Heroposition.x - dir.x ][Heroposition.y - dir.y ]= model[Heroposition.x][Heroposition.y];
           model[Heroposition.x][Heroposition.y] = new Space(new Position(Heroposition.x,Heroposition.y));
           model[Heroposition.x- dir.x ][Heroposition.y - dir.y].updatePos(new Position(Heroposition.x - dir.x,Heroposition.y -dir.y));

       }

    }




    private Position getHeroLocation(){
        for ( int i = 0 ; i< model.length;++i){
            for ( int z = 0 ; z< model.length;++z){
                    if(model[i][z].getElement() instanceof Hero) return new Position(i,z) ;


            }

        }
    return null;
    }
}
