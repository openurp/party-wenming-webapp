package org.openurp.webapp.apps.party.wenming.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.model.AbstractAssessInfo;
import org.openurp.webapp.apps.party.wenming.model.AbstractAssessItemInfo;
import org.openurp.webapp.apps.party.wenming.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.MutualAssess;
import org.openurp.webapp.apps.party.wenming.model.MutualAssessItem;
import org.openurp.webapp.apps.party.wenming.model.SelfAssess;
import org.openurp.webapp.apps.party.wenming.model.SelfAssessItem;

public abstract class AssessAction <T extends AbstractAssessInfo, I extends AbstractAssessItemInfo>  extends WenMingAction {

  abstract protected Class<T> getAssessClass();

  abstract protected Class<I> getItemClass();

  abstract protected List<AssessItem> findAssessItem(AssessSession assessSession, AssessSchema schema);


  @Override
  protected String getEntityName() {
    return getAssessClass().getName();
  }

  @Override
  public String index() throws Exception {
    List<AssessSession> assessSessions = wenMingService.findSessions();
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
    AssessSchema schema = entityDao.get(AssessSchema.class, schemaId);
    AssessSession assessSession = wenMingService.getAssessSessionByAssessTime();
    List<AbstractAssessInfo> malist = findAssess(assessSession.getId(), schema.getId());
    List<AssessItem> items = findAssessItem(assessSession, schema);
    if (malist.isEmpty() && editCreateAble()) {
      malist = new ArrayList<AbstractAssessInfo>();
      for (Department d : schema.getDeparts()) {
        MutualAssess ma = new MutualAssess();
        ma.setSchema(schema);
        ma.setDepartment(d);
        for (AssessItem item : items) {
          MutualAssessItem mai = new MutualAssessItem();
          mai.setItem(item);
          ma.getItems().add(mai);
        }
        malist.add(ma);
      }
    } else if (!malist.isEmpty() && !editable(malist.get(0).getState())) {
      return redirectInfo(malist.get(0));
    }
    put("malist", malist);
    put("items", items);
    put("schema", schema);
    return forward();
  }

  private String redirectInfo(AbstractAssessInfo assess) {
    return redirect("info", getBool("save") ? "保存成功" : "提交成功", "schema.id=" + assess.getSchema().getId()
        + "&session.id=" + assess.getSession().getId() + "&assessBy.id=" + assess.getAssessBy().getId());
  }

  /**
   * 编辑的时候是否创建记录
   * 当审核的时候不需要创建记录
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
    return redirectInfo(malist.get(0));
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected List<T> getAllAssess(Class<T> assessClass,
      Class<I> itemClass) {
    List<T> malist = null;
    Integer id = getInt(getShortName() + ".id");
    if(id != null){
      T assess = (T) getEntity();
      assess.getItems().clear();
      String[] indexes = getAll("index", String.class);
      float totalScore = 0;
      List list = assess.getItems();
      for(String index : indexes){
        I sai = populate(getItemClass(), index);
        sai.setAssess(assess);
        totalScore += sai.getScore();
        list.add(sai);
      }
      assess.setTotalScore(totalScore);
      setState(assess);
      malist = new ArrayList();
      malist.add(assess);
    }else{
      UrpUserBean assessBy = getUrpUser();
      malist = (List<T>) getAll();
      AssessSession session = wenMingService.getAssessSessionByAssessTime();
      Department assessDepartment = getDepartment();
      for (T assess : malist) {
        if (editable(assess.getState())) {
          assess.setAssessDepartment(assessDepartment);
          assess.setAssessBy(assessBy);
          List<I> items = (List<I>) getAll(itemClass, "item" + assess.getDepartment().getId());
          
          assess.getItems().clear();
          assess.getItems().addAll((List) items);
          
          assess.setSession(session);
          assess.setAssessAt(new Date());
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
    List<AbstractAssessInfo> malist = findAssess(getInt("session.id"), getInt("schema.id"));
    put("malist", malist);
    if (editable(malist.get(0).getState())) {
      put("editable", true);
    }
    return super.info();
  }
}
