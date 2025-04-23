package io.github.some_example_name;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {

    private Texture dogImage;
    private Texture background;
    private FitViewport viewport;
    private Player dog;
    private Ball ball;
    private SpriteBatch spriteBatch;
    private BitmapFont font;
    private int score;
    private float wait;
    float delta;


    @Override
    public void create() {
        // Prepare your application here.
        dog = new Player();
        ball = new Ball();
        background = new Texture("background-clouds.jpg");
        viewport = new FitViewport(8,5);
        spriteBatch = new SpriteBatch();
        font = new BitmapFont(); // You can load a custom font if desired
        score = 0;
        wait = 0f;
        delta = Gdx.graphics.getDeltaTime();


    }

    @Override
    public void resize(int width, int height) {
        // Resize your application here. The parameters represent the new window size.
        viewport.update(width, height, true);
    }

    @Override
    public void render() {

        onCollision();


        // Draw your application here.
        ScreenUtils.clear(Color.BLACK);
        ball.update(viewport);
        dog.input(viewport);
        //move();
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0,
            viewport.getWorldWidth(), viewport.getWorldHeight());
        dog.draw(spriteBatch);
        ball.draw(spriteBatch);
        font.getData().setScale(0.05f); // smallest
        font.draw(spriteBatch, "" + score, 6, 4.5f); // for a 8x5 viewport

        spriteBatch.end();

    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        // Destroy application's resources here.
    }

    // a bug in collision. ball gets glitched out and gets infinite score.
    public void onCollision() {
        Rectangle dogHitbox = dog.getBoundingRectangle();
        Rectangle ballHitbox = ball.getBoundingRectangle();
        if (wait > 0f) {
            wait -= 1f;
        }
        if(dogHitbox.overlaps(ballHitbox) && wait <=0f) {
            System.out.println("Collision");
            ball.reverse();
            score++;
            wait += 1f;
        }
    }


}
