package game.accelewarrior;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.accelewarrior.screens.MainMenuScreen;

public class Accelewarrior extends Game {
    private SpriteBatch batch;
    private BitmapFont font;

    private final int screenWidth = 2232; //2232	800
    private final int screenHeight = 1080; //1080	480

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        screen.dispose();
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }
}
