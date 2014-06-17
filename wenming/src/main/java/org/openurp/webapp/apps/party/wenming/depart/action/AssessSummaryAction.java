package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beangle.commons.bean.comparators.MultiPropertyComparator;
import org.beangle.commons.dao.query.builder.SqlBuilder;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItemGroup;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;

public class AssessSummaryAction extends WenMingAction {

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
    put("schemas", assessSession.getSchemas());
    put("assessSessions", assessSessions);
    put("assessSession", assessSession);
    return super.index();
  }

  @Override
  public String info() throws Exception {
    Integer sessionId = getInt("session.id");
    Integer schemaId = getInt("schema.id");
    AssessSession session = entityDao.get(AssessSession.class, sessionId);
    put("assessSession", session);
    AssessSchema assessSchema = entityDao.get(AssessSchema.class, schemaId);
    List<Department> departments = assessSchema.getDeparts();
    put("departments", departments);
    List<AssessItemGroup> groups = assessSchema.getGroups();
    List<AssessItem> assessItems = new ArrayList<AssessItem>();
    for (AssessItemGroup group : groups) {
      assessItems.addAll(group.getItems());
    }
    put("assessItems", assessItems);
    Collections.sort(assessItems, new MultiPropertyComparator("item.group.indexno,item.orderNumber"));

    Map<String, Map<String, Float>> selfAssessScore = findAssessAvgScore(departments, sessionId,
        "wm_self_assesses", "wm_self_assess_items");
    Map<String, Map<String, Float>> mutualAssessScore = findAssessAvgScore(departments, sessionId,
        "wm_mutual_assesses", "wm_mutual_assess_items");
    Map<String, Map<String, Float>> funcDepartAssessScore = findAssessAvgScore(departments, sessionId,
        "wm_func_depart_assesses", "wm_func_depart_assess_items");
    Map<String, Map<String, Float>> supervisorAssessScore = findAssessAvgScore(departments, sessionId,
        "wm_supervisor_assesses", "wm_supervisor_assess_items");
    Map<String, Map<String, Float>> itemTotalScoreMap = new HashMap<String, Map<String, Float>>();
    Map<String, Float> totalScoreMap = new HashMap<String, Float>();
    for (Department department : departments) {
      String departmentId = department.getId().toString();
      Float totalScore = 0F;
      for (AssessItem assessItem : assessItems) {
        Map<String, Float> tMap = itemTotalScoreMap.get(departmentId);
        String itemId = assessItem.getId().toString();
        Float itemTotalScore = 0F;
        itemTotalScore += getZeroScore(selfAssessScore.get(departmentId).get(itemId)) * 0.1F;
        itemTotalScore += getZeroScore(mutualAssessScore.get(departmentId).get(itemId)) * 0.9F;
        itemTotalScore += getZeroScore(supervisorAssessScore.get(departmentId).get(itemId)) * 0.9F;
        itemTotalScore += getZeroScore(funcDepartAssessScore.get(departmentId).get(itemId)) * 0.9F;
        if (tMap == null) {
          tMap = new HashMap<String, Float>();
          itemTotalScoreMap.put(departmentId, tMap);
        }
        totalScore+=itemTotalScore;
        totalScoreMap.put(departmentId, totalScore);
        tMap.put(itemId, itemTotalScore);
      }
    }
    put("totalScoreMap", totalScoreMap);
    put("itemTotalScoreMap", itemTotalScoreMap);
    put("selfAssessScore", selfAssessScore);
    put("mutualAssessScore", mutualAssessScore);
    put("funcDepartAssessScore", funcDepartAssessScore);
    put("supervisorAssessScore", supervisorAssessScore);

    return forward();
  }

  private Float getZeroScore(Float score) {
    if (score == null) { return 0F; }
    return score;
  }

  private Map<String, Map<String, Float>> findAssessAvgScore(List<Department> departments, Integer sessionId,
      String assessTableName, String assessItemTableName) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select a.department_id departmentId, ai.item_id itemId, avg(ai.score) score ");
    sql.append(" from ").append(assessTableName).append(" a join ").append(assessItemTableName)
        .append(" ai on a.id = ai.assess_id");
    sql.append(" where a.session_id = 1");
    sql.append(" group by a.department_id, ai.item_id");
    List<Object[]> list = (List<Object[]>) entityDao.search(SqlBuilder.sql(sql.toString()));
    Map<String, Map<String, Float>> map = new HashMap<String, Map<String, Float>>();
    for (Department department : departments) {
      Map<String, Float> smap = new HashMap<String, Float>();
      map.put(department.getId().toString(), smap);
    }
    for (Object[] oo : list) {
      Map<String, Float> smap = map.get(oo[0].toString());
      if(smap != null){
        smap.put(oo[1].toString(), Float.parseFloat(oo[2].toString()));
      }
    }
    return map;
  }
}
