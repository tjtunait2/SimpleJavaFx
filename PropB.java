package sample;

import javafx.scene.image.Image;

public class PropB extends Actor {
    @Override
    public void update() {

    }
    public PropB(String SVGData, double xLocation, double yLocation, Image... spriteCels){
        super(SVGData, xLocation, yLocation, spriteCels);
        spriteFrame.setScaleX(-1);
        spriteFrame.setScaleY(-1);
        this.setIsFlipH(true);
        this.setIsFlipV(true);
        spriteFrame.setTranslateY(yLocation);
        spriteFrame.setTranslateX(xLocation);
    }
}
