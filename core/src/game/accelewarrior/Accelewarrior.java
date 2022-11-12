package game.accelewarrior;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import game.accelewarrior.characters.Foe;
import game.accelewarrior.characters.Warrior;
import game.accelewarrior.screens.LossScreen;
import game.accelewarrior.screens.MainMenuScreen;
import game.accelewarrior.screens.VictoryScreen;

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
