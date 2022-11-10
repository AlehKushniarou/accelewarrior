package game.accelewarrior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Warrior {
    private Accelewarrior game;
    private Texture texture;
    private String texturePath = "square.png";
    private Rectangle square;
    private int speed = 180;

    public Warrior(Accelewarrior game) {
        this.game = game;
        texture = new Texture(texturePath);
        square = new Rectangle(356, 187, 50, 50);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, square.getX(), square.getY(), square.getHeight(), square.getWidth());
    }

    public void update() {
        float dt = Gdx.graphics.getDeltaTime();
        square.y -= Gdx.input.getAccelerometerX() * speed * dt;
        square.x += Gdx.input.getAccelerometerY() * speed * dt;

        if (square.x < 0) {
            square.x = 0;
        }
        if (square.x > game.getScreenWidth() - square.getWidth()) {
            square.x = game.getScreenWidth() - square.getWidth();
        }
        if (square.y < 0) {
            square.y = 0;
        }
        if (square.y > game.getScreenHeight() - square.getHeight()) {
            square.y = game.getScreenHeight() - square.getHeight();
        }

        if (square.overlaps(game.getSquareFoe())) {
            square.setX(356);
            square.setY(187);
            game.getSquareFoe().setX(700);
            game.getSquareFoe().setY(70);
        }
    }

    public void dispose() {
        texture.dispose();
    }

    public Rectangle getSquare() {
        return square;
    }
}
