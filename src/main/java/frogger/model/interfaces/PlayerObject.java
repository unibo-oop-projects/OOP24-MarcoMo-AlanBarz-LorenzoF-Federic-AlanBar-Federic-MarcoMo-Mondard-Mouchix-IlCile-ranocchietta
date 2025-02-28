package frogger.model.interfaces;

public interface PlayerObject {

    int getLives();

    void getHit();

    boolean isLookingRight();

    void setLookingRight(boolean bool);

    boolean isLookingLeft();

    void setLookingLeft(boolean bool);

    boolean isLookingDown();

    void setLookingDown(boolean bool);

    boolean isLookingUp();

    void setLookingUp(boolean bool);

    void addPoints(int points);

    int getScore();
}
