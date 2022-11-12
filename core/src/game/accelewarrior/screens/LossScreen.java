package game.accelewarrior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

import game.accelewarrior.Accelewarrior;

public class LossScreen implements Screen {
    private final Accelewarrior game;

    private OrthographicCamera camera;

    public LossScreen(Accelewarrior game) {
        this.game = game;

        game.getFont().getData().setScale(18);
        game.getFont().setColor(new Color(1.0f, 0.09f, 0.02f, 1));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getScreenWidth(), game.getScreenHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.58f, 0.9f, 1f, 0);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "You lost!\nTap to try again!",
                game.getScreenWidth() / 25.0f, game.getScreenHeight() / 1.3f);
        game.getBatch().end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
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

    }
}
