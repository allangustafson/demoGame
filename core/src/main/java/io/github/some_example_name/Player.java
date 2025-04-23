package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Player  extends Sprite {
    private float speed = 1.5f;  // could also have filename, width, height
    private float velocityY = 0f; // velocity is speed + direction
    private final float gravity = -9.8f; // Adjust as needed
    private final float jumpVelocity = 5f; //  Adjust as needed
    private boolean isGrounded = true;

    public Player()  {    // related code from create()
        super(new Texture("dog.png"));
        setSize(1,1);
    }

    public void input(Viewport viewport) {
        float delta = Gdx.graphics.getDeltaTime(); // for all hw frame rate
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.translateX(this.speed*delta); // Move right
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.translateX(-this.speed*delta); // Move right
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && isGrounded) {
            velocityY = jumpVelocity;
            isGrounded = false;
        }
        // Come down with gravity
        velocityY += gravity * delta;
        this.translateY(velocityY * delta);
// Check if you reached the ground at 0
        if (this.getY() <= 0) {
            this.setY(0);
            velocityY = 0;
            isGrounded = true;
        }



        this.setX(MathUtils.clamp(this.getX(),
            0, viewport.getWorldWidth() - this.getWidth()));
        this.setY(MathUtils.clamp(this.getY(),
            0, viewport.getWorldHeight() - this.getHeight()));
    }


}
