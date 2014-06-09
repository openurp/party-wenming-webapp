package org.openurp.webapp.apps.party.wenming.model;

import org.beangle.commons.entity.pojo.NumberIdTimeObject;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.Supervisor;

/**
 * 测评投票
 * 
 * @author chaostone
 */
public abstract class AbstractWenmingVote extends NumberIdTimeObject<Long> {

  private static final long serialVersionUID = 4303109503469167899L;

  /** 投票批次 */
  private WenmingSession session;

  /** 投票人 */
  private WenmingProjectVoter voter;

  /** 赞成票 */
  private boolean ayes;

  private boolean submit;

  public WenmingSession getSession() {
    return session;
  }

  public void setSession(WenmingSession session) {
    this.session = session;
  }

  public WenmingProjectVoter getVoter() {
    return voter;
  }

  public void setVoter(WenmingProjectVoter voter) {
    this.voter = voter;
  }

  public boolean isAyes() {
    return ayes;
  }

  public void setAyes(boolean ayes) {
    this.ayes = ayes;
  }

  public boolean isSubmit() {
    return submit;
  }

  public void setSubmit(boolean submit) {
    this.submit = submit;
  }

  public abstract void setGoodObject(AbstractWenmingObject obj);

    

}
