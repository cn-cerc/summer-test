package cn.cerc.sample.forms;

import java.util.HashMap;
import java.util.Map;

public class ResultBean {
    private boolean result;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void add(String key, Object value) {
        data.put(key, value);
    }

}
