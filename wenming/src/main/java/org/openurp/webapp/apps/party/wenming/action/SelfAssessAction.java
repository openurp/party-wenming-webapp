package org.openurp.webapp.apps.party.wenming.action;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.model.AssessItemGroup;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.SelfAssess;
import org.openurp.webapp.apps.party.wenming.model.SelfAssessItem;
import org.openurp.webapp.apps.party.wenming.service.AssessItemGroupService;

/**
 * 自评
 * 
 * @author chaostone
 */
public class SelfAssessAction extends WenMingAction {

  private AssessItemGroupService assessItemGroupService;

  public void setAssessItemGroupService(AssessItemGroupService assessItemGroupService) {
    this.assessItemGroupService = assessItemGroupService;
  }

  @Override
  protected String getEntityName() {
    return SelfAssess.class.getName();
  }

  @Override
  protected void indexSetting() {
    UrpUserBean user = getUrpUser();
    put("user", user);
    put("sessions", wenMingService.findSessions(user.getDepartment()));
    super.indexSetting();
  }

  @Override
  public String info() throws Exception {
    Integer sessionId = getInt("session.id");
    AssessSession assessSession = entityDao.get(AssessSession.class, sessionId);
    SelfAssess selfAssess = getSelfAssess(sessionId);
    put("selfAssess", selfAssess);
    put("assessSession", assessSession);

    UrpUserBean user = entityDao.get(UrpUserBean.class, getUserId());
    AssessSession session = wenMingService.getAssessSession();
    AssessSchema schema = wenMingService.getSchema(user.getDepartment());
    if (schema != null && session != null && session.equals(assessSession)) {
      if (selfAssess == null) {
        // 判断是否可添加自评
        put("addable", true);
      } else if (modifyable(selfAssess)) {
        // 判断是否可修改自评
        put("modifyable", true);
      }
    }
    return forward();
  }

  @Override
  public String search() {
    return super.search();
  }

  /**
   * 判断自评是否可修改
   * 
   * @param selfAssess
   * @return
   */
  private boolean modifyable(SelfAssess selfAssess) {
    if (selfAssess == null) { return false; }
    AssessState status = selfAssess.getState();
    if (AssessState.Draft.equals(status) || AssessState.DepartUnpassed.equals(status)
        || AssessState.SchoolUnpassed.equals(status)) { return true; }
    return false;
  }

  /**
   * 根据批次ID查询自评
   * 
   * @param session
   * @return
   */
  private SelfAssess getSelfAssess(Integer sessionId) {
    OqlBuilder<SelfAssess> query = OqlBuilder.from(SelfAssess.class, "o");
    query.where("session.id = :sessionId", sessionId);
    query.where("assessBy.id = :assessByid", getUserId());
    @SuppressWarnings("unchecked")
    List<SelfAssess> list = search(query);
    return list.isEmpty() ? null : list.get(0);
  }

  /**
   * 查询自己的自评
   */
  @SuppressWarnings("unchecked")
  @Override
  protected <T extends Entity<?>> OqlBuilder<T> getQueryBuilder() {
    OqlBuilder<SelfAssess> query = super.getQueryBuilder();
    query.where("assessBy.id = :assessById", getUserId());
    return (OqlBuilder<T>) query;
  }

  @Override
  public String edit() {
    AssessSession session = wenMingService.getAssessSession();
    if (session == null) { return redirect("list", "现在还不能自评"); }
    SelfAssess selfAssess = getSelfAssess(session.getId());
    if (selfAssess == null) {
      selfAssess = new SelfAssess();
      UrpUserBean user = entityDao.get(UrpUserBean.class, getUserId());
      AssessSchema schema = wenMingService.getSchema(user.getDepartment());
      selfAssess.setSchema(schema);
      List<AssessItemGroup> groups = assessItemGroupService.findBySchema(selfAssess.getSchema().getId());
      for (AssessItemGroup group : groups) {
        Collections.sort(group.getItems());
        for (AssessItem item : group.getItems()) {
          SelfAssessItem sai = new SelfAssessItem();
          sai.setItem(item);
          selfAssess.getItems().add(sai);
        }
      }
    }
    put("selfAssess", selfAssess);
    return forward();
  }

  @Override
  protected String saveAndForward(Entity<?> entity) {
    SelfAssess selfAssess = (SelfAssess) entity;
    selfAssess.setDepartment(getDepartment());
    selfAssess.setSession(wenMingService.getAssessSession());
    selfAssess.setSchema(wenMingService.getSchema(selfAssess.getDepartment()));
    selfAssess.setAssessAt(new Date());
    selfAssess.setAssessBy(getUrpUser());
    selfAssess.getItems().clear();
    String[] indexes = getAll("index", String.class);
    float totalScore = 0;
    for (String index : indexes) {
      SelfAssessItem sai = populate(SelfAssessItem.class, index);
      sai.setAssess(selfAssess);
      totalScore += sai.getScore();
      selfAssess.getItems().add(sai);
    }
    selfAssess.setTotalScore(totalScore);
    if (getBool("save")) {
      selfAssess.setState(AssessState.Draft);
    } else {
      selfAssess.setState(AssessState.Submit);
    }
    // return super.saveAndForward(entity);
    entityDao.saveOrUpdate(selfAssess);
    return redirect("info", "操作成功", "session.id=" + selfAssess.getSession().getId());
  }

}
