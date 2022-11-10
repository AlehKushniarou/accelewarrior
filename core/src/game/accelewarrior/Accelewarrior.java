package game.accelewarrior;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class Accelewarrior extends ApplicationAdapter {
	private SpriteBatch batch;

	private Warrior warrior;

	private Texture circleImg;
	private Texture squareFoeImg;

	private Circle circle;

	private Rectangle squareFoe;

	private float radius = 50f;
	private float diameter = radius * 2;
	private float halfRadius = radius / 2;

	private OrthographicCamera camera;
	private int screenWidth = 800;
	private int screenHeight = 480;

	private int dir = 1;
	private float time = 1.5f;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		warrior = new Warrior(this);

		circleImg = new Texture("red circle.png");
		squareFoeImg = new Texture("square foe.png");

		circle = new Circle(356, 187, radius);
		squareFoe = new Rectangle(700, 70, 50, 50);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, screenWidth, screenHeight);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.58f, 0.9f, 1f, 0);

		update();

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		warrior.render(batch);
		batch.draw(squareFoeImg, squareFoe.getX(), squareFoe.getY(), squareFoe.getHeight(), squareFoe.getWidth());

		if (Gdx.input.isTouched()) {
			circle.x = warrior.getSquare().x - halfRadius;
			circle.y = warrior.getSquare().y - halfRadius;
			batch.draw(circleImg, warrior.getSquare().x - halfRadius,
					warrior.getSquare().y - halfRadius,
					diameter, diameter);
		}

		batch.end();

		float dt = Gdx.graphics.getDeltaTime();

		time += Gdx.graphics.getDeltaTime();
		if (time >= 1.5f) {
			dir = MathUtils.random(1, 4);
			time = 0f;
		}

		switch (dir) {
			case 1 : squareFoe.x += 200 * Gdx.graphics.getDeltaTime();
				break;
			case 2 : squareFoe.x -= 200 * Gdx.graphics.getDeltaTime();
				break;
			case 3 : squareFoe.y += 200 * Gdx.graphics.getDeltaTime();
				break;
			case 4 : squareFoe.y -= 200 * Gdx.graphics.getDeltaTime();
				break;
		}

		// make sure the square foe stays within the screen bounds
		if (squareFoe.x < 0) {
			squareFoe.x = 0;
			dir = MathUtils.random(1, 4);
		}
		if (squareFoe.x > screenWidth - squareFoe.getWidth()) {
			squareFoe.x = screenWidth - squareFoe.getWidth();
			dir = MathUtils.random(1, 4);
		}
		if (squareFoe.y < 0) {
			squareFoe.y = 0;
			dir = MathUtils.random(1, 4);
		}
		if (squareFoe.y > screenHeight - squareFoe.getHeight()) {
			squareFoe.y = screenHeight - squareFoe.getHeight();
			dir = MathUtils.random(1, 4);
		}
	}

	private void update() {
		warrior.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		warrior.dispose();
		circleImg.dispose();
		squareFoeImg.dispose();
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public Rectangle getSquareFoe() {
		return squareFoe;
	}
}
