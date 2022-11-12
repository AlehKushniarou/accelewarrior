package game.accelewarrior;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class Accelewarrior extends ApplicationAdapter {
    private SpriteBatch batch;

    private Warrior warrior;

    private Array<Foe> foes;

    private int negativeX = -100;
    private int negativeY = -100;

    private OrthographicCamera camera;
    private int screenWidth = 800; //2232	800
    private int screenHeight = 480; //1080	480

    @Override
    public void create() {
        batch = new SpriteBatch();

        warrior = new Warrior(this);

        foes = new Array<>();
        foes.add(new Foe(this));
        foes.add(new Foe(this));
        foes.add(new Foe(this));
        foes.add(new Foe(this));
        foes.add(new Foe(this));
        foes.add(new Foe(this));
        foes.add(new Foe(this));


        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.58f, 0.9f, 1f, 0);

        update();

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        warrior.render(batch);
        for (Foe foe : foes) {
            foe.render(batch);
        }

        batch.end();
    }

    private void update() {
        warrior.update();

        for (Foe foe : foes) {
            foe.update();

            if (warrior.getSquare().overlaps(foe.getSquareFoe())) {
                warrior.getSquare().x = 356;
                warrior.getSquare().y = 187;
            }

            if (Intersector.overlaps(warrior.getCircle(), foe.getSquareFoe())) {
                foes.removeValue(foe, true);
                foe.getSquareFoe().x = negativeX;
                foe.getSquareFoe().y = negativeY;
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        warrior.dispose();
        for (Foe foe : foes) {
            foe.dispose();
        }
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
