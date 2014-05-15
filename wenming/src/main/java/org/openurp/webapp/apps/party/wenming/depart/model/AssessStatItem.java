package org.openurp.webapp.apps.party.wenming.depart.model;

import org.beangle.commons.entity.pojo.LongIdObject;

/**
 * 测评指标得分
 * 
 * @author chaostone
 */
public class AssessStatItem extends LongIdObject {

  private static final long serialVersionUID = 197924504191476337L;

  private AssessStat stat;

  private float selfScore;

  private float mutualScore;

  private float supervisorScore;

  private float score;

  public AssessStat getStat() {
    return stat;
  }

  public void setStat(AssessStat stat) {
    this.stat = stat;
  }

  public float getSelfScore() {
    return selfScore;
  }

  public void setSelfScore(float selfScore) {
    this.selfScore = selfScore;
  }

  public float getMutualScore() {
    return mutualScore;
  }

  public void setMutualScore(float mutualScore) {
    this.mutualScore = mutualScore;
  }

  public float getSupervisorScore() {
    return supervisorScore;
  }

  public void setSupervisorScore(float supervisorScore) {
    this.supervisorScore = supervisorScore;
  }

  public float getScore() {
    return score;
  }

  public void setScore(float score) {
    this.score = score;
  }

}
