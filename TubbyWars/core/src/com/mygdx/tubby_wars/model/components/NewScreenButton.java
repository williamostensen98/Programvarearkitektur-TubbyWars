package com.mygdx.tubby_wars.model.components;

        import com.badlogic.ashley.core.Component;
        import com.badlogic.ashley.core.Engine;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Screen;
        import com.badlogic.gdx.graphics.g2d.TextureRegion;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.actions.Actions;
        import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
        import com.mygdx.tubby_wars.TubbyWars;
        import com.mygdx.tubby_wars.view.GameScreen;
        import com.mygdx.tubby_wars.model.Assets;
        import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
        import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.actions.Actions;
        import com.badlogic.gdx.graphics.g2d.TextureRegion;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.scenes.scene2d.ui.Button;
        import com.badlogic.gdx.scenes.scene2d.Stage;
        import com.mygdx.tubby_wars.view.LoadingScreen;

public class NewScreenButton extends Button {
    private TubbyWars game;
    private Texture texture;
    private Stage stage;

    public NewScreenButton(TubbyWars game, Texture texture, int x, int y, String screen){
        this.game = game;
        this.texture = texture;
    }

    public void createButton() {
        final Button playButton = new com.badlogic.gdx.scenes.scene2d.ui.Button(new TextureRegionDrawable(new TextureRegion(texture)), new TextureRegionDrawable(new TextureRegion(texture)));
        playButton.setTransform(true); //Automatisk satt til false. Setter den til true så vi kan skalere knappen ved klikk
        playButton.setSize(70, 70);
        playButton.setOrigin(50,50);
        //playButton.setPosition(x, y);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                System.out.println("Button pressed");
            }

            //Kjøres når knappen trykkes ned
            @Override
            public boolean touchDown(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                playButton.addAction(Actions.scaleTo(0.95f, 0.95f,0.1f)); //Minker størrelsen på knappen når den trykkes
                return super.touchDown(inputEvent, 100, 100,pointer,button);
            }

            //Kjører når knappen slippes opp
            public void touchUp(InputEvent inputEvent, float xpos, float ypos, int pointer, int button) {
                super.touchUp(inputEvent, 100, 100 ,pointer,button);
                playButton.addAction(Actions.scaleTo(1f, 1f,0.1f)); //Setter størrelsen på knappen tilbake til original størrelse
                //this.setScreen(new screen(this, engine));
            }
        });

        stage.addActor(playButton);
    }
}

