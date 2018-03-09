package cn.cerc.sample.services.inAndOut;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.mysql.BuildQuery;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.sample.common.BaseConfig;

public class SvrInAndOut extends CustomService {

	public boolean search() {

		Record head = getDataIn().getHead();
		BuildQuery f = new BuildQuery(this);
		f.byField("h.CorpNo_", BaseConfig.CorpNo);
		if (head.hasValue("tbNo")) {
			f.byField("h.TBNo_", head.getString("tbNo"));
		}
		if (head.hasValue("productName")) {
			f.byLink(new String[] { "b.Code_,b.Desc_,b.Spec_,b.Unit_" }, head.getString("productName"));
		}
		if (head.hasValue("startTime") && head.hasValue("endTime")) {

			f.byBetween("h.TBDate_", head.getDate("startTime"), head.getDate("endTime"));
		}
		if (!head.hasValue("startTime") && head.hasValue("endTime")) {
			f.byParam(String.format("h.TBDate_ <= '%s'", head.getDate("endTime")));
		}
		if (head.hasValue("startTime") && !head.hasValue("endTime")) {
			f.byParam(String.format("h.TBDate_ >= '%s'", head.getDate("startTime")));
		}
		if (head.hasValue("object")) {
			f.byLink(new String[] { "h.SupName_", "h.CusName_", "h.DeptName_" }, head.getString("object"));
		}
		f.add("select * from %s h inner join %s b on h.CorpNo_ = b.CorpNo_ and b.TBNo_= h.TBNo_", BaseConfig.Tranh,
				BaseConfig.Tranb);
		f.setOrderText("order by h.TBDate_ desc");
		SqlQuery ds = f.open();
		getDataOut().appendDataSet(ds);

		double totalInNum = 0;
		double totalOutNum = 0;
		double totalAENum = 0;
		getDataIn().first();
		while (getDataOut().fetch()) {
			String tb = getDataOut().getString("TB_");
			if ("AB".equals(tb)) {
				getDataOut().setField("InNum_", getDataOut().getDouble("Num_"));
				getDataOut().setField("ObjName_", getDataOut().getString("SupName_"));
			} else if ("BC".equals(tb)) {
				getDataOut().setField("OutNum_", getDataOut().getDouble("Num_"));
				getDataOut().setField("ObjName_", getDataOut().getString("CusName_"));
			} else if ("AE".equals(tb)) {
				getDataOut().setField("AENum_", getDataOut().getDouble("Num_"));
				getDataOut().setField("ObjName_", getDataOut().getString("DeptName_"));
			}
			totalInNum += getDataOut().getDouble("InNum_");
			totalOutNum += getDataOut().getDouble("OutNum_");
			totalAENum += getDataOut().getDouble("AENum_");
		}

		getDataOut().last();
		getDataOut().append();
		getDataOut().setField("Unit_", "合计");
		getDataOut().setField("InNum_", totalInNum);
		getDataOut().setField("OutNum_", totalOutNum);
		getDataOut().setField("AENum_", totalAENum);

		return true;
	}
}
