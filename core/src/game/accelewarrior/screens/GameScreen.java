package game.accelewarrior.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import game.accelewarrior.Accelewarrior;
import game.accelewarrior.characters.ChangingColorFoe;
import game.accelewarrior.characters.SimpleFoe;
import game.accelewarrior.characters.Warrior;

public class GameScreen implements Screen {
    private final Accelewarrior game;

    private Warrior warrior;

    private Array<SimpleFoe> foes;
    private Array<ChangingColorFoe> colorFoes;

    private int negativeX = -100;
    private int negativeY = -100;

    private OrthographicCamera camera;

    public GameScreen(Accelewarrior game) {
        this.game = game;

        warrior = new Warrior(game);

        foes = new Array<>();
        foes.add(new SimpleFoe(game));
        foes.add(new SimpleFoe(game));
        foes.add(new SimpleFoe(game));
        foes.add(new SimpleFoe(game));
        foes.add(new SimpleFoe(game));
        foes.add(new SimpleFoe(game));
        foes.add(new SimpleFoe(game));

        colorFoes = new Array<>();
        colorFoes.add(new ChangingColorFoe(game));
        colorFoes.add(new ChangingColorFoe(game));
        colorFoes.add(new ChangingColorFoe(game));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getScreenWidth(), game.getScreenHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.58f, 0.9f, 1f, 0);

        update();

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        warrior.render(game.getBatch());
        for (SimpleFoe foe : foes) {
            foe.render(game.getBatch());
        }
        for (ChangingColorFoe colorFoe : colorFoes) {
            colorFoe.render(game.getBatch());
        }
        game.getBatch().end();

        if (foes.size == 0) {
            game.setScreen(new VictoryScreen(game));
            dispose();
        }

        if (warrior.isDead()) {
            game.setScreen(new LossScreen(game));
            dispose();
        }
    }

    private void update() {
        warrior.update();

        for (SimpleFoe foe : foes) {
            foe.update();

            if (warrior.getSquare().overlaps(foe.getSquareFoe())) {
                warrior.setDead(true);
            }

            if (Intersector.overlaps(warrior.getCircle(), foe.getSquareFoe())) {
                foes.removeValue(foe, true);
                foe.getSquareFoe().x = negativeX;
                foe.getSquareFoe().y = negativeY;
                foe.dispose();
            }

            for (int i = 0; i < foes.size; i++) {
                SimpleFoe foe2 = foes.get(i);
                if (foe != foe2 && foe.getSquareFoe().overlaps(foe2.getSquareFoe())) {
                    foe.setDirection(-foe.getDirection().x, -foe.getDirection().y);
                }
            }
        }
        for (ChangingColorFoe colorFoe : colorFoes) {
            colorFoe.update();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        warrior.dispose();
        for (SimpleFoe foe : foes) {
            foe.dispose();
        }
    }
}
