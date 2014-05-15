package org.openurp.webapp.apps.party.wenming.depart.model;

import java.util.Date;
import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.entity.pojo.LongIdObject;
import org.openurp.kernel.base.unit.model.Department;

/**
 * 测评统计
 * 
 * @author chaostone
 */
public class AssessStat extends LongIdObject {

  private static final long serialVersionUID = -1162136301267001456L;

  private Department department;

  private AssessSession session;

  private AssessSchema schema;

  private float selfScore;

  private float mutualScore;

  private float supervisorScore;

  private float score;

  private Date statAt;

  private List<AssessStatItem> items = CollectUtils.newArrayList();

  public AssessSession getSession() {
    return session;
  }

  public void setSession(AssessSession session) {
    this.session = session;
  }

  public AssessSchema getSchema() {
    return schema;
  }

  public void setSchema(AssessSchema schema) {
    this.schema = schema;
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

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Date getStatAt() {
    return statAt;
  }

  public void setStatAt(Date statAt) {
    this.statAt = statAt;
  }

  public List<AssessStatItem> getItems() {
    return items;
  }

  public void setItems(List<AssessStatItem> items) {
    this.items = items;
  }

}
