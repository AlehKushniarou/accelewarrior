package game.accelewarrior.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import game.accelewarrior.Accelewarrior;

public class ChangingColorFoe extends Foe{
    private Texture texture;
    private Texture secondTexture;
    private String texturePath = "square foe.png";
    private String secondTexturePath = "square blue foe.png";
    private float colorTimer = 0.0f;
    private boolean changeColor = false;

    public ChangingColorFoe(Accelewarrior game) {
        super(game);
        texture = new Texture(texturePath);
        secondTexture = new Texture(secondTexturePath);
    }

    @Override
    public void render(SpriteBatch batch) {
        if (changeColor) {
            batch.draw(texture, squareFoe.getX(), squareFoe.getY(),
                    squareFoe.getHeight(), squareFoe.getWidth());
        } else {
            batch.draw(secondTexture, squareFoe.getX(), squareFoe.getY(),
                    squareFoe.getHeight(), squareFoe.getWidth());
        }
    }

    @Override
    public void update() {
        super.update();

        //Changing color
        if (colorTimer < 0.0f) {
            colorTimer = MathUtils.random(0.5f, 6.0f);
            changeColor = !changeColor;
        }
        colorTimer -= Gdx.graphics.getDeltaTime();
    }

    @Override
    public void dispose() {
        texture.dispose();
        secondTexture.dispose();
    }
}
