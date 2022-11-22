package game.accelewarrior.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import game.accelewarrior.Accelewarrior;
import game.accelewarrior.characters.ChangingColorFoe;
import game.accelewarrior.characters.Foe;
import game.accelewarrior.characters.SimpleFoe;
import game.accelewarrior.characters.Warrior;

public class GameScreen implements Screen {
    private final Accelewarrior game;

    private Warrior warrior;

    private Array<Foe> foes;

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
        foes.add(new ChangingColorFoe(game));
        foes.add(new ChangingColorFoe(game));
        foes.add(new ChangingColorFoe(game));

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
        for (Foe foe : foes) {
            foe.render(game.getBatch());
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

        for (Foe foe : foes) {
            foe.update();

            if (warrior.getSquare().overlaps(foe.getSquareFoe())) {
                if (foe instanceof ChangingColorFoe && !((ChangingColorFoe) foe).isChangeColor()) {
                    killFoe(foe);
                } else {
                    warrior.setDead(true);
                }
            }

            if (Intersector.overlaps(warrior.getCircle(), foe.getSquareFoe())) {
                killFoe(foe);
            }

            for (int i = 0; i < foes.size; i++) {
                Foe foe2 = foes.get(i);
                if (foe != foe2 && foe.getSquareFoe().overlaps(foe2.getSquareFoe())) {
                    foe.setDirection(-foe.getDirection().x, -foe.getDirection().y);
                }
            }
        }
    }

    private void killFoe(Foe foe) {
        foes.removeValue(foe, true);
        foe.getSquareFoe().x = negativeX;
        foe.getSquareFoe().y = negativeY;
        foe.dispose();
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
        for (Foe foe : foes) {
            foe.dispose();
        }
    }
}
