package org.openjfx.view.menuView;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.openjfx.assetManager.Assets;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvButton;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends HBox {

    private final static int MENU_BTN_START_X = 750;
    private final static int MENU_BTN_START_Y = 50;

    private List<FiyuvButton> menuButtons;

    //Variables
    private FiyuvButton singlePlayerBtn;
    private FiyuvButton twoPlayersBtn;
    private FiyuvButton howToPlayBtn;
    private FiyuvButton settingsBtn;
    private FiyuvButton creditsBtn;
    private FiyuvButton scoresBtn;
    private FiyuvButton exitBtn;
    private FiyuvButton resumeBtn;
    private FiyuvButton autoSaveBtn;
    private  FiyuvButton bossSceneButton;
    private ImageView gameLogo;
    private VBox vbox;

    public MainMenu(){
        vbox = new VBox();
        gameLogo = new ImageView(Assets.getInstance().getMenuAssets().getGameLogo());
        gameLogo.setFitHeight(300);
        gameLogo.setFitWidth(400);
        menuButtons = new ArrayList<>();
        this.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(vbox, gameLogo);
        createButtons();
        this.setSpacing(100);
        this.getStyleClass().add("menu-root");
    }

    private void createButtons(){
        createResumeBtn();
        createAutoSave();
        createSinglePlayerBtn();
        createTwoPlayersBtn();
        createHowToPlayBtn();
        createSettingsBtn();
        createCreditsBtn();
        createScoresBtn();
        createExitBtn();

        createBossSceneButton();
    }

    private void addMenuButton(FiyuvButton btn){
        btn.setLayoutX(MENU_BTN_START_X);
        btn.setLayoutY(MENU_BTN_START_Y + menuButtons.size() * 100);
        menuButtons.add(btn);
        vbox.getChildren().add(btn);
    }

    private void createSinglePlayerBtn(){
        singlePlayerBtn = new FiyuvButton("Single Player");
        addMenuButton(singlePlayerBtn);
    }

    private void createTwoPlayersBtn(){
        twoPlayersBtn = new FiyuvButton("Two Player");
        addMenuButton(twoPlayersBtn);
    }

    private void createHowToPlayBtn(){
        howToPlayBtn = new FiyuvButton("How to Play");
        addMenuButton(howToPlayBtn);
    }

    private void createSettingsBtn(){
        settingsBtn = new FiyuvButton("Settings");
        addMenuButton(settingsBtn);
    }

    private void createCreditsBtn(){
        creditsBtn = new FiyuvButton("Credits");
        addMenuButton(creditsBtn);
    }

    private void createScoresBtn(){
        scoresBtn = new FiyuvButton("High Scores");
        addMenuButton(scoresBtn);
    }

    private void createExitBtn(){
        exitBtn = new FiyuvButton("Exit");
        addMenuButton(exitBtn);
    }

    private void createResumeBtn(){

        resumeBtn = new FiyuvButton("Resume");
        addMenuButton(resumeBtn);
    }

    private void createAutoSave(){
        autoSaveBtn = new FiyuvButton("AutoSave");
        addMenuButton(autoSaveBtn);
    }

    private void createBossSceneButton(){
        bossSceneButton = new FiyuvButton("Boss Scene Button");
        //addMenuButton(bossSceneButton);
    }

    public FiyuvButton getSinglePlayerBtn(){
        return singlePlayerBtn;
    }

    public FiyuvButton getTwoPlayersBtn(){
        return twoPlayersBtn;
    }

    public FiyuvButton getHowToPlayBtn(){
        return howToPlayBtn;
    }

    public FiyuvButton getSettingsBtn(){
        return settingsBtn;
    }

    public FiyuvButton getCreditsBtn(){
        return creditsBtn;
    }

    public FiyuvButton getScoresBtn(){return scoresBtn;}

    public FiyuvButton getExitBtn(){
        return exitBtn;
    }

    public FiyuvButton getResumeBtn(){return resumeBtn;}

    public FiyuvButton getBossSceneButton(){return bossSceneButton;}

    public FiyuvButton getAutoSaveBtn(){return autoSaveBtn;}
    
    public void enableResumeButton(){
            resumeBtn.setVisible(true);
    }

    public void disableResumeButton(){
            resumeBtn.setVisible(false);
    }

    public void enableAutoSaveBtn(){
        if(!vbox.getChildren().contains(autoSaveBtn))
            vbox.getChildren().add(1,autoSaveBtn);
    }

    public void disableAutoSaveBtn(){
        if(vbox.getChildren().contains(autoSaveBtn) && vbox.getChildren().get(1).equals(autoSaveBtn))
            vbox.getChildren().remove(1);
    }
}
