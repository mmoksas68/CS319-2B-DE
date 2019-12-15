package org.openjfx.controller.preBossSceneControllers;

import javafx.animation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import org.openjfx.controller.SoundController;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.preBossEntities.PreBossMap;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.utilization.*;
import org.openjfx.view.gameSceneView.preBossSceneView.RootPane;
import org.openjfx.view.gameSceneView.preBossSceneView.TopBar.radarView.RadarObject;

import java.util.ArrayList;

public class PreBossGameController {
    private RootPane rootPane;
    private Scene scene;
    private double width;
    private double height;
    private GameSituation gameSituation;
    private PreBossMapController preBossMapController;
    private SpacecraftController spacecraftController1;
    private SpacecraftController spacecraftController2;
    private boolean gameOn = true;
    private BooleanProperty gameOnChange = new SimpleBooleanProperty(false);
    private int scoreDecayTimer;
    private boolean isSinglePlayer = true;

    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
                 timerPulse();
        }
    };

    public PreBossGameController(Scene scene, double initWidth, double initHeight) {

        this.scene = scene;
        rootPane = new RootPane(initWidth, initHeight, isSinglePlayer);
        this.width = initWidth;
        this.height = initHeight;
        preBossMapController = new PreBossMapController(isSinglePlayer);
        spacecraftController1 = new SpacecraftController(preBossMapController.getPreBossMap().getSpacecraft1(), rootPane.getPreBossMapView1(), preBossMapController.getPreBossMap());

        if(!isSinglePlayer)
            spacecraftController2 = new SpacecraftController(preBossMapController.getPreBossMap().getSpacecraft2(), rootPane.getPreBossMapView2(), preBossMapController.getPreBossMap());

        scene.setRoot(rootPane);
        initGame();

    }


    public PreBossGameController(PreBossMap preBossMap, Scene scene, double initWidth, double initHeight) {
        this.scene = scene;
        preBossMapController = new PreBossMapController(preBossMap);
    }

    private void timerPulse(){
        refreshMap();
        spacecraftController1.checkInputs();
        if (!isSinglePlayer)
            spacecraftController2.checkInputs();
    }

    private void initGame(){
        /*
        if(!isSinglePlayer){
            scene.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case W:
                        System.out.println("up");
                        spacecraftController2.setUpKeyPressed(true);
                        break;
                    case S:
                        spacecraftController2.setDownKeyPressed(true);
                        break;
                    case A:
                        spacecraftController2.setLeftKeyPressed(true);
                        spacecraftController2.getSpacecraft().setDirectionLeft(true);
                        break;
                    case D:
                        spacecraftController2.setRightKeyPressed(true);
                        spacecraftController2.getSpacecraft().setDirectionLeft(false);
                        break;
                    case SPACE:
                        System.out.println("space");
                        spacecraftController2.setFireKeyPressed(true);
                        break;

                }
            });

            scene.setOnKeyReleased(e -> {
                switch (e.getCode()) {
                    case W:
                        spacecraftController2.setUpKeyPressed(false);
                        break;
                    case S:
                        spacecraftController2.setDownKeyPressed(false);
                        break;
                    case A:
                        spacecraftController2.setLeftKeyPressed(false);
                        break;
                    case D:
                        spacecraftController2.setRightKeyPressed(false);
                        break;
                    case  SPACE:
                        spacecraftController2.setFireKeyPressed(false);
                        break;
                }
            });
        }
    */


        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    spacecraftController2.setUpKeyPressed(true);
                    break;
                case S:
                    spacecraftController2.setDownKeyPressed(true);
                    break;
                case A:
                    spacecraftController2.setLeftKeyPressed(true);
                    spacecraftController2.getSpacecraft().setDirectionLeft(true);
                    break;
                case D:
                    spacecraftController2.setRightKeyPressed(true);
                    spacecraftController2.getSpacecraft().setDirectionLeft(false);
                    break;
                case SPACE:
                    spacecraftController2.setFireKeyPressed(true);
                    break;

                case UP:
                    spacecraftController1.setUpKeyPressed(true);
                    break;
                case DOWN:
                    spacecraftController1.setDownKeyPressed(true);
                    break;
                case LEFT:
                    spacecraftController1.setLeftKeyPressed(true);
                    spacecraftController1.getSpacecraft().setDirectionLeft(true);
                    break;
                case RIGHT:
                    spacecraftController1.setRightKeyPressed(true);
                    spacecraftController1.getSpacecraft().setDirectionLeft(false);
                    break;
                case ALT_GRAPH:
                    spacecraftController1.setFireKeyPressed(true);
                    break;

            }
        });

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case W:
                    spacecraftController2.setUpKeyPressed(false);
                    break;
                case S:
                    spacecraftController2.setDownKeyPressed(false);
                    break;
                case A:
                    spacecraftController2.setLeftKeyPressed(false);
                    break;
                case D:
                    spacecraftController2.setRightKeyPressed(false);
                    break;
                case  SPACE:
                    spacecraftController2.setFireKeyPressed(false);
                    break;
                case UP:
                    spacecraftController1.setUpKeyPressed(false);
                    break;
                case DOWN:
                    spacecraftController1.setDownKeyPressed(false);
                    break;
                case LEFT:
                    spacecraftController1.setLeftKeyPressed(false);
                    break;
                case RIGHT:
                    spacecraftController1.setRightKeyPressed(false);
                    break;
                case ALT_GRAPH:
                    spacecraftController1.setFireKeyPressed(false);
                    break;
                case ESCAPE:
                    gameOnChange.set(true);
                    break;
            }
        });


        animationTimer.start();

    }

    private void refreshMap() {

        preBossMapController.checkMapSituation();
        refreshAndReflectBuff();
        refreshAndReflectBullet();
        refreshAndReflectEnemy();
        refreshAndReflectMeteor();
        refreshAndReflectStations();
        refreshAndReflectSpacecraft(spacecraftController1.getSpacecraft());
        if(!isSinglePlayer)
            refreshAndReflectSpacecraft(spacecraftController2.getSpacecraft());
    }

    private void refreshAndReflectEnemy() {
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for (var enemy : preBossMapController.getPreBossMap().getEnemies().values()) {
            ModelToViewEnemy modelToViewEnemy = new ModelToViewEnemy(enemy);
            if (enemy.isDead()) {
                toBeDeleted.add(enemy.getID());
                spacecraftController1.getPreBossMapView().addExplodeAnimation(new ModelToView(enemy));
                if (!isSinglePlayer)
                    spacecraftController2.getPreBossMapView().addExplodeAnimation(new ModelToView(enemy));
            }

            spacecraftController1.getPreBossMapView().refreshEnemy(modelToViewEnemy);
            if (!isSinglePlayer)
                spacecraftController2.getPreBossMapView().refreshEnemy(modelToViewEnemy);
            rootPane.getTopBarView().getMiddleView().refresh(new RadarObject(enemy));
        }

        for (var it : toBeDeleted) {
            preBossMapController.getPreBossMap().deleteEnemy(it);
            SoundController.explosion();
        }
    }

    private void refreshAndReflectBullet() {
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for (var bullet : preBossMapController.getPreBossMap().getBullets().values()) {
            if (bullet.isDead()) {
                toBeDeleted.add(bullet.getID());
            }
            spacecraftController1.getPreBossMapView().refreshBullet(new ModelToViewBullet(bullet));
            if (!isSinglePlayer)
                spacecraftController2.getPreBossMapView().refreshBullet(new ModelToViewBullet(bullet));

        }
        for (var it : toBeDeleted) {
            preBossMapController.getPreBossMap().deleteBullet(it);
        }
    }


    private void refreshAndReflectSpacecraft(Spacecraft spacecraft){

        if(!isSinglePlayer){
            if(spacecraft.getID() == spacecraftController1.getSpacecraft().getID())
            {
                spacecraftController1.getPreBossMapView().refreshSpacecraftMain( new ModelToViewSpaceCraft(spacecraft));
                spacecraftController2.getPreBossMapView().refreshSpacecraftSecondary( new ModelToViewSpaceCraft(spacecraft));
            }


            if(spacecraft.getID() == spacecraftController2.getSpacecraft().getID())
            {
                spacecraftController2.getPreBossMapView().refreshSpacecraftMain( new ModelToViewSpaceCraft(spacecraft));
                spacecraftController1.getPreBossMapView().refreshSpacecraftSecondary( new ModelToViewSpaceCraft(spacecraft));
            }
        }else
            spacecraftController1.getPreBossMapView().refreshSpacecraftMain( new ModelToViewSpaceCraft(spacecraft));


        rootPane.getTopBarView().getMiddleView().refresh(new RadarObject(spacecraft));

    }

    private void refreshAndReflectStations(){
        ArrayList<Long> toBeDeleted = new ArrayList<>();

        for (var station : preBossMapController.getPreBossMap().getStations().values()) {
            if (station.isDead()) {
                toBeDeleted.add(station.getID());
                spacecraftController1.getPreBossMapView().addExplodeAnimation(new ModelToView(station));
                if (!isSinglePlayer)
                    spacecraftController2.getPreBossMapView().addExplodeAnimation(new ModelToView(station));
            }
            spacecraftController1.getPreBossMapView().refreshStations(new ModelToViewStation(station));
            if (!isSinglePlayer)
                spacecraftController1.getPreBossMapView().refreshStations(new ModelToViewStation(station));
            rootPane.getTopBarView().getMiddleView().refresh(new RadarObject(station));
        }

        for (var it : toBeDeleted) {
            preBossMapController.getPreBossMap().deleteStation(it);
            SoundController.explosion();
        }
    }

    private void refreshAndReflectBuff(){

    }

    private void refreshAndReflectMeteor(){

    }

    public void resume(){

    }

    public void pause(){

    }

    private void increaseScore(int score){

    }

    private void decreaseScore(int score){

    }

    public BorderPane getRootPane() {
        return rootPane;
    }

    public void setRootPane(RootPane rootPane) {
        this.rootPane = rootPane;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
      //  rootPane.setWidth(width);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
     //   rootPane.setHeight(height);
    }

    public GameSituation getGameSituation() {
        return gameSituation;
    }

    public void setGameSituation(GameSituation gameSituation) {
        this.gameSituation = gameSituation;
    }

    public PreBossMapController getPreBossMapController() {
        return preBossMapController;
    }

    public void setPreBossMapController(PreBossMapController preBossMapController) {
        this.preBossMapController = preBossMapController;
    }

    public SpacecraftController getSpacecraftController1() {
        return spacecraftController1;
    }

    public void setSpacecraftController1(SpacecraftController spacecraftController1) {
        this.spacecraftController1 = spacecraftController1;
    }

    public SpacecraftController getSpacecraftController2() {
        return spacecraftController2;
    }

    public void setSpacecraftController2(SpacecraftController spacecraftController2) {
        this.spacecraftController2 = spacecraftController2;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }

    public boolean isGameOnChange() {
        return gameOnChange.get();
    }

    public BooleanProperty gameOnChangeProperty() {
        return gameOnChange;
    }

    public void setGameOnChange(boolean gameOnChange) {
        this.gameOnChange.set(gameOnChange);
    }

    public int getScoreDecayTimer() {
        return scoreDecayTimer;
    }

    public void setScoreDecayTimer(int scoreDecayTimer) {
        this.scoreDecayTimer = scoreDecayTimer;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public void setAnimationTimer(AnimationTimer animationTimer) {
        this.animationTimer = animationTimer;
    }


}
