package accelerometer.warrior;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class Accelewarrior extends ApplicationAdapter {
	SpriteBatch batch;

	Texture squareImg;
	Texture circleImg;
	Texture squareFoeImg;

	Rectangle square;
	Circle circle;

	Rectangle squareFoe;

	float radius = 50f;
	float diameter = radius * 2;
	float halfRadius = radius / 2;

	OrthographicCamera camera;
	int screenWidth = 800;
	int screenHeight = 480;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		squareImg = new Texture("square.png");
		circleImg = new Texture("red circle.png");
		squareFoeImg = new Texture("square foe.png");

		square = new Rectangle(356, 187, 50, 50);
		circle = new Circle(356, 187, radius);
		squareFoe = new Rectangle(700, 70, 50, 50);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, screenWidth, screenHeight);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.58f, 0.9f, 1f, 0);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		batch.draw(squareImg, square.getX(), square.getY(), square.getHeight(), square.getWidth());
		batch.draw(squareFoeImg, squareFoe.getX(), squareFoe.getY(), squareFoe.getHeight(), squareFoe.getWidth());

		if (Gdx.input.isTouched()) {
			circle.x = square.x - halfRadius;
			circle.y = square.y - halfRadius;
			batch.draw(circleImg, square.x - halfRadius, square.y - halfRadius,
					diameter, diameter);
		}

		batch.end();

		if (square.overlaps(squareFoe)) {
			square.setX(356);
			square.setY(187);
			squareFoe.setX(700);
			squareFoe.setY(70);
		}

		float dt = Gdx.graphics.getDeltaTime();

		// accelerometer
		square.y -= Gdx.input.getAccelerometerX() * 180 * dt;
		square.x += Gdx.input.getAccelerometerY() * 180 * dt;

		// make sure the bucket stays within the screen bounds
		if (square.x < 0) {
			square.x = 0;
		}
		if (square.x > screenWidth - square.getWidth()) {
			square.x = screenWidth - square.getWidth();
		}
		if (square.y < 0) {
			square.y = 0;
		}
		if (square.y > screenHeight - square.getHeight()) {
			square.y = screenHeight - square.getHeight();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		squareImg.dispose();
		circleImg.dispose();
		squareFoeImg.dispose();
	}
}
