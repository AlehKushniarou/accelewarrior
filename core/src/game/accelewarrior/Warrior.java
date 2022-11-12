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
    private int speed = 300;
    private float radius = 50f;
    private float diameter = radius * 2;
    private float halfRadius = radius / 2;
    private float timeOfAttack = 1.0f;
    private float timeBetweenAttacks = 1.1f;

    public Warrior(Accelewarrior game) {
        this.game = game;
        warriorTexture = new Texture(textureWarriorPath);
        circleTexture = new Texture(textureCirclePath);
        square = new Rectangle(356, 187, 50, 50);
        circle = new Circle(-100, -100, radius);
    }

    public void render(SpriteBatch batch) {
        batch.draw(warriorTexture, square.getX(), square.getY(),
                square.getHeight(), square.getWidth());
        timeBetweenAttacks += Gdx.graphics.getDeltaTime();
        if (Gdx.input.justTouched() && timeBetweenAttacks > 0.5f) {
            timeOfAttack = Gdx.graphics.getDeltaTime();
        }
        if (timeOfAttack < 0.2f){
            timeOfAttack += Gdx.graphics.getDeltaTime();
            circle.x = square.x + square.getWidth() / 2;
            circle.y = square.y + square.getHeight() / 2;
            batch.draw(circleTexture, square.x - halfRadius,
                    square.y - halfRadius,
                    diameter, diameter);
            timeBetweenAttacks = 0;
        } else {
            circle.x = -100;
            circle.y = -100;
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
    }

    public void dispose() {
        warriorTexture.dispose();
        circleTexture.dispose();
    }

    public Rectangle getSquare() {
        return square;
    }

    public Circle getCircle() {
        return circle;
    }
}
