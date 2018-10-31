package cn.cerc.sample.es;

import java.io.Serializable;

import com.google.gson.Gson;

import cn.cerc.jdb.core.Record;

public class PartItem implements Serializable {
    private static final long serialVersionUID = -9211650696622013472L;
    private String corpNo;
    private String code;
    private String desc;
    private String spec;

    public String getCorpNo() {
        return corpNo;
    }

    public void setCorpNo(String corpNo) {
        this.corpNo = corpNo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setRecord(Record item) {
        this.desc = item.getString("Desc_");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public static void main(String[] args) {
        System.out.println(new PartItem());
    }

}
