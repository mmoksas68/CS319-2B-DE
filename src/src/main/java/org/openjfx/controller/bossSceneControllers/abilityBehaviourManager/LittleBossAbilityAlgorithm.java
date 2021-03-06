package org.openjfx.controller.bossSceneControllers.abilityBehaviourManager;

import org.openjfx.model.bossEntities.BossAbility.LittleBoss;
import org.openjfx.model.bossEntities.BossAbility.SpecialAbility;
import org.openjfx.model.bossEntities.BossMap;

public class LittleBossAbilityAlgorithm implements AbilityBehaviourAlgorithm {
    SpecialAbility specialAbility;
   public LittleBossAbilityAlgorithm (SpecialAbility specialAbility) {
       this.specialAbility = specialAbility;
   }

    public void moveSpecialAbility() {
       double MAX_WIDTH = BossMap.MAP_WIDTH;
       double MAX_HEIGHT = BossMap.MAP_HEIGHT;
            LittleBoss littleBoss = (LittleBoss) specialAbility;
            littleBoss.moveWithVelocityVector( littleBoss.getVelocity(), littleBoss.getxDir(), littleBoss.getyDir());
            if( littleBoss.getHitNumber() == 4) {
                littleBoss.setDead( true);
            }
            else if ( littleBoss.getLocation().getPositionX() + littleBoss.getHitBoxWidth() >= MAX_WIDTH || littleBoss.getLocation().getPositionX() < 0 ) {
                littleBoss.setHitNumber( littleBoss.getHitNumber() + 1);
                littleBoss.setxDir( - littleBoss.getxDir());
                littleBoss.setyDir( littleBoss.getyDir());
            }
            else if( littleBoss.getLocation().getPositionY() + littleBoss.getHitBoxHeight() >= MAX_HEIGHT || littleBoss.getLocation().getPositionY() < 0  ){
                littleBoss.setHitNumber( littleBoss.getHitNumber() + 1);
                littleBoss.setxDir( littleBoss.getxDir());
                littleBoss.setyDir( - littleBoss.getyDir());
            }

            if ( littleBoss.getHealthPoint() <= 0)
                littleBoss.setDead( true);

    }
}


