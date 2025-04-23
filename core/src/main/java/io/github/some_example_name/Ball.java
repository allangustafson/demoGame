package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Ball extends Sprite {
    private float speedX = 1.5f;
    private float speedY = 1.5f;
    private float delta = Gdx.graphics.getDeltaTime();
    public Ball() {
        super(new Texture("ball.png"));
        setSize(1,1);
        setPosition(2,2);
    }

    public void randomPosn(Viewport viewport) {
        float randomX = MathUtils.random(0,
            viewport.getWorldWidth() - this.getWidth());
        float randomY = MathUtils.random(0,
            viewport.getWorldHeight() - this.getHeight());
        this.setPosition(randomX, randomY);

    }

    public void reverse() {
        speedY = -speedY;
        speedX = -speedX;
        this.translate(speedX*delta, speedY*delta);
    }


    public void update(Viewport v) {
        float delta = Gdx.graphics.getDeltaTime();
        if (this.getY() < 0 || this.getY()  > v.getWorldHeight() - this.getHeight()) {
            speedY = -speedY;  // reverse direction at 0 and worldHeight
        }
        if (this.getX() < 0 || this.getX()  > v.getWorldWidth() - this.getWidth()) {
            speedX = -speedX;  // reverse direction at 0 and worldHeight
        }
        this.translate(speedX*delta, speedY*delta); // or translate(speedX*delta,speedY*delta);
    }
}
