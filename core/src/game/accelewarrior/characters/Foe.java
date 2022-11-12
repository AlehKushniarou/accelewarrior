package game.accelewarrior.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import game.accelewarrior.Accelewarrior;

public class Foe {
    private Accelewarrior game;
    private Texture texture;
    private String texturePath = "square foe.png";
    private Rectangle squareFoe;
    private int speed = 350;
    private int dir = 1;
    private float time = 1.5f;

    public Foe(Accelewarrior game) {
        this.game = game;
        texture = new Texture(texturePath);
        squareFoe = new Rectangle(700, 70, 50, 50);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, squareFoe.getX(), squareFoe.getY(),
                squareFoe.getHeight(), squareFoe.getWidth());
    }

    public void update() {
        float dt = Gdx.graphics.getDeltaTime();

        time += Gdx.graphics.getDeltaTime();
        if (time >= 1.5f) {
            dir = MathUtils.random(1, 4);
            time = 0f;
        }

        switch (dir) {
            case 1:
                squareFoe.x += speed * dt;
                break;
            case 2:
                squareFoe.x -= speed * dt;
                break;
            case 3:
                squareFoe.y += speed * dt;
                break;
            case 4:
                squareFoe.y -= speed * dt;
                break;
        }

        if (squareFoe.x < 0) {
            squareFoe.x = 0;
            dir = MathUtils.random(1, 4);
        }
        if (squareFoe.x > game.getScreenWidth() - squareFoe.getWidth()) {
            squareFoe.x = game.getScreenWidth() - squareFoe.getWidth();
            dir = MathUtils.random(1, 4);
        }
        if (squareFoe.y < 0) {
            squareFoe.y = 0;
            dir = MathUtils.random(1, 4);
        }
        if (squareFoe.y > game.getScreenHeight() - squareFoe.getHeight()) {
            squareFoe.y = game.getScreenHeight() - squareFoe.getHeight();
            dir = MathUtils.random(1, 4);
        }
    }

    public void dispose() {
        texture.dispose();
    }

    public Rectangle getSquareFoe() {
        return squareFoe;
    }
}
