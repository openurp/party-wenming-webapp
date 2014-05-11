package org.openurp.webapp.apps.party.wenming.action;

import java.util.Date;

import org.beangle.commons.entity.Entity;
import org.openurp.webapp.apps.party.wenming.model.AssessBonusType;

/**
 * 测评加分类型
 * 
 * @author xinzhou
 **/

public class AssessBonusTypeAction extends WenMingAction {
  @Override
  protected String getEntityName() {
    return AssessBonusType.class.getName();
  }

  @Override
  protected String saveAndForward(Entity<?> entity) {
    AssessBonusType bonusType = (AssessBonusType) entity;
    if (bonusType.getCreatedAt() == null) {
      bonusType.setCreatedAt(new Date());
    }
    bonusType.setEffectiveAt(new Date());
    return super.saveAndForward(entity);
  }
  
 

}
