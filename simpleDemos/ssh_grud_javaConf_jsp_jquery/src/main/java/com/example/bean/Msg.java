package com.example.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * created by dfk
 * 2022/4/25
 */
public class Msg {
    //状态码 100-成功 200-失败
    private int code;
    //提示信息
    private String msg;
    //用户返回浏览器的数据
    private Map<String, Object> extend = new HashMap<>();

    public static Msg success() {
        Msg msg = new Msg();
        msg.setCode(100);
        msg.setMsg("处理成功！");
        return msg;
    }
    public static Msg fail() {
        Msg msg = new Msg();
        msg.setCode(200);
        msg.setMsg("处理失败！");
        return msg;
    }
    public Msg add(String key, Object object) {
        this.extend.put(key, object);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Msg setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
