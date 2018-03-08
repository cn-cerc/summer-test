package cn.cerc.sample.services.check;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.sample.common.BaseConfig;

public class SvrCheck extends CustomService {

	public boolean findCheckh() {

		SqlQuery dsH = new SqlQuery(this);
		dsH.add("select * from %s", BaseConfig.Tranh);
		dsH.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsH.add("and DeptName_ is not null");
		dsH.open();
		getDataOut().appendDataSet(dsH);
		return true;
	}

	public boolean saveCheckh() {

		String tbNo = getDataIn().getHead().getString("tbNo");
		String tbDate = getDataIn().getHead().getString("tbDate");
		String deptName = getDataIn().getHead().getString("deptName");
		String appUser = getDataIn().getHead().getString("appUser");

		if (!tbNo.substring(0, 2).equals("AE")) {
			throw new RuntimeException("单号格式错误!");
		}

		SqlQuery dsH = new SqlQuery(this);
		dsH.add("select * from %s", BaseConfig.Tranh);
		dsH.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsH.add("and TBNo_ = '%s'", tbNo);
		dsH.open();
		if (dsH.eof()) {
			dsH.append();
			dsH.setField("CorpNo_", BaseConfig.CorpNo);
			dsH.setField("TB_", "AE");
			dsH.setField("TBNo_", tbNo);
			dsH.setField("TBDate_", tbDate);
			dsH.setField("DeptName_", deptName);
			dsH.setField("AppUser_", appUser);
			dsH.setField("AppDate_", TDateTime.Now());
			dsH.post();
		} else {
			throw new RuntimeException("单号已存在!");
		}
		return true;
	}

	public boolean findCheck() {

		String tbNo = getDataIn().getHead().getString("tbNo");
		SqlQuery dsH = new SqlQuery(this);
		dsH.add("select * from %s", BaseConfig.Tranh);
		dsH.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsH.add("and TBNo_ = '%s'", tbNo);
		dsH.open();
		getDataOut().getHead().copyValues(dsH.getCurrent());

		SqlQuery dsB = new SqlQuery(this);
		dsB.add("select * from %s", BaseConfig.Tranb);
		dsB.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsB.add("and TBNo_ = '%s'", tbNo);
		dsB.open();
		getDataOut().appendDataSet(dsB);

		return true;
	}
}
