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
	private Foe foe;

	private OrthographicCamera camera;
	private int screenWidth = 800;
	private int screenHeight = 480;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		warrior = new Warrior(this);
		foe = new Foe(this);

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
		foe.render(batch);
		batch.end();
	}

	private void update() {
		warrior.update();
		foe.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		warrior.dispose();
		foe.dispose();
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public Foe getFoe() {
		return foe;
	}
}
