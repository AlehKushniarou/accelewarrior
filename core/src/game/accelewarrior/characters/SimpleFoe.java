package game.accelewarrior.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.accelewarrior.Accelewarrior;

public class SimpleFoe extends Foe{
    private Texture texture;
    private String texturePath = "square foe.png";

    public SimpleFoe(Accelewarrior game) {
        super(game);
        texture = new Texture(texturePath);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, squareFoe.getX(), squareFoe.getY(),
                squareFoe.getHeight(), squareFoe.getWidth());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
