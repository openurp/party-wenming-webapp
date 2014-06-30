package org.openurp.webapp.apps.party.wenming.depart.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.depart.model.AbstractAssessInfo;
import org.openurp.webapp.apps.party.wenming.depart.model.AbstractAssessItemInfo;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.depart.model.MutualAssess;
import org.openurp.webapp.apps.party.wenming.depart.model.MutualAssessItem;

public abstract class AssessAction<T extends AbstractAssessInfo, I extends AbstractAssessItemInfo> extends
    WenMingAction {

  @SuppressWarnings("unchecked")
  protected Class<T> getAssessClass() {
    Type a = this.getClass().getGenericSuperclass();
    ParameterizedType pt = (ParameterizedType) a;
    return (Class<T>) pt.getActualTypeArguments()[0];
  }

  @SuppressWarnings("unchecked")
  protected Class<I> getItemClass() {
    Type a = this.getClass().getGenericSuperclass();
    ParameterizedType pt = (ParameterizedType) a;
    return (Class<I>) pt.getActualTypeArguments()[1];
  }

  abstract List<AssessItem> findAssessItem(AssessSession assessSession, AssessSchema schema);

  @Override
  protected String getEntityName() {
    return getAssessClass().getName();
  }

  @Override
  public String index() throws Exception {
    List<AssessSession> assessSessions = wenMingService.findAssessSessions();
    Integer sessionId = getInt("session.id");
    AssessSession assessSession = null;
    if (sessionId != null) {
      assessSession = entityDao.get(AssessSession.class, sessionId);
    } else if (!assessSessions.isEmpty()) {
      assessSession = assessSessions.get(0);
    }
    indexSetting(assessSession);
    put("assessSessions", assessSessions);
    put("assessSession", assessSession);
    return super.index();
  }

  protected void indexSetting(AssessSession assessSession) {
    put("schemas", assessSession.getSchemas());
  }

  @Override
  public String edit() {
    Integer schemaId = getInt("schema.id");
    if (schemaId == null) {
      return null;
    }
    AssessSession nowAssessSession = wenMingService.getAssessSessionByAssessTime();
    AssessSession assessSession = entityDao.get(AssessSession.class, getInt("session.id"));
    if (nowAssessSession != null && nowAssessSession.equals(assessSession)) {
      AssessSchema schema = entityDao.get(AssessSchema.class, schemaId);
      List<AbstractAssessInfo> malist = findAssess(assessSession.getId(), schema.getId());
      if (malist.isEmpty() && editCreateAble()) {
        malist = new ArrayList<AbstractAssessInfo>();
        List<AssessItem> items = findAssessItem(assessSession, schema);
        for (Department d : schema.getDeparts()) {
          MutualAssess ma = new MutualAssess();
          ma.setSchema(schema);
          ma.setDepartment(d);
          for (AssessItem item : items) {
            MutualAssessItem mai = new MutualAssessItem();
            mai.setItem(item);
            ma.getItems().add(mai);
          }
          ma.setSchema(schema);
          ma.setState(AssessState.Draft);
          ma.setAssessAt(new Date());
          ma.setAssessDepartment(getDepartment());
          ma.setSession(assessSession);
          ma.setAssessBy(getUrpUser());
          malist.add(ma);
        }
      } else if (!malist.isEmpty() && !editable(malist.get(0).getState())) {
        return redirectInfo(malist.get(0));
      }
      editSetting(assessSession, schema, malist);
      put("malist", malist);
      put("schema", schema);
      return forward();
    }else{
      return redirectInfo(getInt("session.id"), schemaId);
    }
  }

  protected void editSetting(AssessSession assessSession, AssessSchema schema, List<AbstractAssessInfo> malist) {

  }

  protected String redirectInfo(AbstractAssessInfo assess) {
    return redirect("info", getBool("save") ? "保存成功" : "提交成功", "schema.id=" + assess.getSchema().getId()
        + "&session.id=" + assess.getSession().getId() + "&assessBy.id=" + assess.getAssessBy().getId());
  }

  protected String redirectInfo(Integer assessid, Integer schemaId) { 
    return redirect("info", getBool("save") ? "保存成功" : "提交成功", "schema.id=" + schemaId
      + "&session.id=" + assessid + "&assessBy.id=" + getUserId());
  }

  /**
   * 编辑的时候是否创建记录 当审核的时候不需要创建记录
   * 
   * @return
   */
  protected boolean editCreateAble() {
    return true;
  }

  protected List<AbstractAssessInfo> findAssess(Integer assessSessionId, Integer schemaId) {
    OqlBuilder<AbstractAssessInfo> query = getQueryBuilder(assessSessionId, schemaId);
    query.where("assessBy.id = :assessByid", getUserId());
    @SuppressWarnings("unchecked")
    List<AbstractAssessInfo> malist = search(query);
    return malist;
  }

  protected OqlBuilder<AbstractAssessInfo> getQueryBuilder(Integer assessSessionId, Integer schemaId) {
    OqlBuilder<AbstractAssessInfo> query = OqlBuilder.from(getEntityName(), "o");
    query.where("o.schema.id = :schemaid", schemaId);
    query.where("o.session.id = :sessionid", assessSessionId);
    query.orderBy("o.department.code");
    return query;
  }

  @Override
  public String save() throws Exception {
    @SuppressWarnings("unchecked")
    List<AbstractAssessInfo> malist = (List<AbstractAssessInfo>) getAllAssess(getAssessClass(), getItemClass());
    if (malist != null) {
      saveOrUpdate(malist);
    }
    saveAndForward(malist);
    return redirectSave(malist.get(0));
  }

  protected void saveAndForward(List<AbstractAssessInfo> malist) {

  }

  protected String redirectSave(AbstractAssessInfo assess) {
    return redirectInfo(assess);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected List<T> getAllAssess(Class<T> assessClass, Class<I> itemClass) {
    List<T> malist = null;
    Integer id = getInt(getShortName() + ".id");
    if (id != null) {
      T assess = (T) getEntity();
      assess.getItems().clear();
      String[] indexes = getAll("index", String.class);
      float totalScore = 0;
      List list = assess.getItems();
      for (String index : indexes) {
        I sai = populate(getItemClass(), index);
        sai.setAssess(assess);
        totalScore += sai.getScore();
        list.add(sai);
      }
      assess.setTotalScore(totalScore);
      setState(assess);
      malist = new ArrayList();
      malist.add(assess);
    } else {
      UrpUserBean assessBy = getUrpUser();
      malist = (List<T>) getAll();
      AssessSession session = wenMingService.getAssessSessionByAssessTime();
      Department assessDepartment = getDepartment();
      Date now = new Date();
      for (T assess : malist) {
        if (editable(assess.getState())) {
          if (assess.getId() == null) {
            assess.setAssessDepartment(assessDepartment);
            assess.setAssessBy(assessBy);
            assess.setSession(session);
          }
          assess.setAssessAt(now);
          List<I> items = (List<I>) getAll(itemClass, "item" + assess.getDepartment().getId());
          assess.getItems().clear();
          assess.getItems().addAll((List) items);
          float totalScore = 0;
          for (I item : items) {
            item.setAssess(assess);
            totalScore += item.getScore();
          }
          assess.setTotalScore(totalScore);
          setState(assess);
        } else {
          return null;
        }
      }
    }
    return malist;
  }

  protected void setState(T assess) {
    boolean save = getBool("save");
    if (save) {
      assess.setState(AssessState.Draft);
    } else {
      assess.setState(AssessState.Submit);
    }
  }

  @Override
  public String info() throws Exception {
    AssessSession session = wenMingService.getAssessSessionByAssessTime();
    List<AbstractAssessInfo> malist = findAssess(getInt("session.id"), getInt("schema.id"));
    put("malist", malist);
    if (malist != null && malist.size() > 0) {
      put("schema", malist.get(0).getSchema());
      put("ifSubmit", ifSubmit(malist.get(0).getState()));
    }
    if(session != null && malist.size() > 0 && editable(malist.get(0).getState())){
      put("editable", true);
    }
    return super.info();
  }  
  
  private boolean ifSubmit(AssessState state){
    return state == AssessState.Submit;
  }

}