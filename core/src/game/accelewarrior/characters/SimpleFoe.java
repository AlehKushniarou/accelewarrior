package game.accelewarrior.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

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

    public void dispose() {
        texture.dispose();
    }
}
