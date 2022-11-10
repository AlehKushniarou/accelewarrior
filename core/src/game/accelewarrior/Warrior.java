package game.accelewarrior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Warrior {
    private Accelewarrior game;
    private Texture warriorTexture;
    private Texture circleTexture;
    private String textureWarriorPath = "square.png";
    private String textureCirclePath = "red circle.png";
    private Rectangle square;
    private Circle circle;
    private int speed = 180;
    private float radius = 50f;
    private float diameter = radius * 2;
    private float halfRadius = radius / 2;

    public Warrior(Accelewarrior game) {
        this.game = game;
        warriorTexture = new Texture(textureWarriorPath);
        circleTexture = new Texture(textureCirclePath);
        square = new Rectangle(356, 187, 50, 50);
        circle = new Circle();
        circle.radius = radius;
    }

    public void render(SpriteBatch batch) {
        batch.draw(warriorTexture, square.getX(), square.getY(), square.getHeight(), square.getWidth());
        if (Gdx.input.isTouched()) {
            circle.x = square.x + square.getWidth() / 2;
            circle.y = square.y + square.getHeight() / 2;
            batch.draw(circleTexture, square.x - halfRadius,
                    square.y - halfRadius,
                    diameter, diameter);
        }
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

        if (square.overlaps(game.getFoe().getSquareFoe())) {
            square.setX(356);
            square.setY(187);
            game.getFoe().getSquareFoe().setX(700);
            game.getFoe().getSquareFoe().setY(70);
        }

        if (Intersector.overlaps(circle, game.getFoe().getSquareFoe())) {
            square.setX(356);
            square.setY(187);
            game.getFoe().getSquareFoe().setX(700);
            game.getFoe().getSquareFoe().setY(70);
        }
    }

    public void dispose() {
        warriorTexture.dispose();
        circleTexture.dispose();
    }

    public Rectangle getSquare() {
        return square;
    }
}
