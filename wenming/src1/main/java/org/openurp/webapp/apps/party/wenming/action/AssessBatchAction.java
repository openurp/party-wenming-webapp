package org.openurp.webapp.apps.party.wenming.action;

import java.sql.Date;
import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.lang.Strings;
import org.openurp.webapp.apps.party.wenming.model.AssessBatch;

/**
 * 宣传部 考核批次维护
 */
public class AssessBatchAction extends WenMingAction {

	public String index() {

		return forward();
	}

	public String list() {
		Date beginTime = getDate("beginTime");
		Date endTime = getDate("endTime");
		OqlBuilder<AssessBatch> query = OqlBuilder.from(AssessBatch.class, "assessBatch");
		populateConditions(query);
		if (null != beginTime) {
			query.where("assessBatch.createTime>=:beginTime", beginTime);
		}
		if (null != endTime) {
			query.where("assessBatch.createTime<=:endTime", endTime);
		}
		this.addorder(query);
		List<AssessBatch> list = entityDao.search(query);
		put("assessBatchs", list);
		return forward();
	}

	public String edit() {
		Long assessBatchId = getLong("assessBatchId");
		AssessBatch assessBatch = null;
		if (null != assessBatchId) {
			assessBatch = entityDao.get(AssessBatch.class, assessBatchId);
		} else {
			assessBatch = new AssessBatch();
		}
		put("assessBatch", assessBatch);
		return forward();
	}

	public String save() {
		AssessBatch assessBatch = (AssessBatch) populateEntity(AssessBatch.class, "assessBatch");
		entityDao.saveOrUpdate(assessBatch);
		return redirect("list", "info.save.success");
	}

	public String logout() {
		Long[] assessBatchIds = Strings.splitToLong(get("assessBatchIds"));
		if (null != assessBatchIds) {
			for (int i = 0; i < assessBatchIds.length; i++) {
				AssessBatch assessBatch = (AssessBatch) entityDao.get(AssessBatch.class, assessBatchIds[i]);
				assessBatch.setIfEnable(Boolean.FALSE);
				entityDao.saveOrUpdate(assessBatch);
			}
		}
		return redirect("list", "注销成功");
	}

}
