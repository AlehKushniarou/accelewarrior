package game.accelewarrior.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import game.accelewarrior.Accelewarrior;
import game.accelewarrior.screens.GameScreen;

public class Foe {
    private Accelewarrior game;
    private Texture texture;
    private String texturePath = "square foe.png";
    private Rectangle squareFoe;
    private int speed = 350;
    private float timer = 1.5f;
    private Vector2 position;
    private Vector2 direction;
    private float size = 50.0f;

    public Foe(Accelewarrior game) {
        this.game = game;
        texture = new Texture(texturePath);
        float x = MathUtils.random(0, game.getScreenWidth());
        float y = MathUtils.random(0, game.getScreenHeight());
        position = new Vector2(x, y);
        direction = new Vector2(x, y).nor();
        squareFoe = new Rectangle(position.x, position.y, size, size);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, squareFoe.getX(), squareFoe.getY(),
                squareFoe.getHeight(), squareFoe.getWidth());
    }

    public void update() {
        float dt = Gdx.graphics.getDeltaTime();

        position.mulAdd(direction, speed * dt);

        timer -= dt;
        if (timer < 0.0f) {
            timer = MathUtils.random(1.0f, 4.0f);
            direction.set(MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 1.0f));
            direction.nor();
        }

        squareFoe.x = position.x;
        squareFoe.y = position.y;

        //Check screen bounds
        if (squareFoe.x < 0) {
            squareFoe.x = 0;
            direction.set(MathUtils.random(0.0f, 1.0f), MathUtils.random(-1.0f, 1.0f));
        }
        if (squareFoe.x > game.getScreenWidth() - squareFoe.getWidth()) {
            squareFoe.x = game.getScreenWidth() - squareFoe.getWidth();
            direction.set(MathUtils.random(-1.0f, 0.0f), MathUtils.random(-1.0f, 1.0f));
        }
        if (squareFoe.y < 0) {
            squareFoe.y = 0;
            direction.set(MathUtils.random(-1.0f, 1.0f), MathUtils.random(0.0f, 1.0f));
        }
        if (squareFoe.y > game.getScreenHeight() - squareFoe.getHeight()) {
            squareFoe.y = game.getScreenHeight() - squareFoe.getHeight();
            direction.set(MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 0.0f));
        }
    }

    public void dispose() {
        texture.dispose();
    }

    public Rectangle getSquareFoe() {
        return squareFoe;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(float x, float y) {
        direction.set(x, y);
    }
}
