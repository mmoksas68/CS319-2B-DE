package org.openjfx.fileManager;

import org.openjfx.model.menuEntities.GameSaveObj;
import org.openjfx.model.menuEntities.PassedLevelInfo;
import org.openjfx.model.menuEntities.Settings;
import org.openjfx.model.preBossEntities.PreBossMap;

import java.io.*;

public class FileController {
    private boolean isGameSaveExist;
    private FileOutputStream fos;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public FileController(){

    }

    //##################################################################################################//

    public void saveGame(){
        GameSaveObj gameSaveObj = GameSaveObj.getInstance();
        try {
            fos = new FileOutputStream(new File("gameData/gameSave.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.writeObject(gameSaveObj.getPreBossMap());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadGame(){
        try {
            fis = new FileInputStream(new File("gameData/gameSave.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            GameSaveObj gameSaveObj = GameSaveObj.getInstance();
            gameSaveObj.setPreBossMap((PreBossMap) ois.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveHighScore(){

    }

    public void loadHighScore(){

    }

    public void saveKeys() {
        Settings settings = Settings.getInstance();
        try {
            fos = new FileOutputStream(new File("gameData/setting.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.writeObject(settings);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadKeys(){
        try {
            fis = new FileInputStream(new File("gameData/setting.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Settings.setInstance((Settings) ois.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void savePassedLevelInfo(){
        PassedLevelInfo passedLevelInfo = PassedLevelInfo.getInstance();
        try {
            fos = new FileOutputStream(new File("gameData/levelInfo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.writeObject(passedLevelInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPassedLevelInfo(){
        try {
            fis = new FileInputStream(new File("gameData/levelInfo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PassedLevelInfo.setInstance((PassedLevelInfo) ois.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //##################################################################################################//

    //##################################################################################################//

    public File getFile(String address){
        return new File(address);
    }


    //##################################################################################################//

    //##################################################################################################//

    public boolean getIsGameSaveExist(){
        return isGameSaveExist;
    }

    public void setIsGameSaveExist(boolean isGameSaveExist){
        this.isGameSaveExist = isGameSaveExist;
    }

    //##################################################################################################//

    public static FileInputStream getFileStream( String url) throws FileNotFoundException {
        return new FileInputStream(url);
    }
}