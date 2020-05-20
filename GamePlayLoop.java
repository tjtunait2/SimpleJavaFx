package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;

public class GamePlayLoop extends AnimationTimer {
    protected InvinciBagel invinciBagel;
    Pos location;
    @Override
    public void handle(long now) {
        invinciBagel.iBagel.update();
        invinciBagel.iBeagle.update();
    }
    public GamePlayLoop(InvinciBagel iBagel){
        super();
        this.invinciBagel = iBagel;
    }
}
