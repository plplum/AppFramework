package com.example.framework.appframework.model;

import java.util.List;

/**
 * 公司：
 * 项目：
 * 简述：
 * 作者：Chenxp
 * 时间：2018/6/8
 * 版本：V1.0.0
 */
public class TestBean1 extends BaseBean{

    private String type;

    private String errorCode;

    private String elapsedTime;


    private List<List<TranslateResult>> translateResult;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public void setTranslateResult(List<List<TranslateResult>> translateResult) {
        this.translateResult = translateResult;
    }
    public List<List<TranslateResult>> getTranslateResult() {
        return translateResult;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    class TranslateResult {

        private String src;
        private String tgt;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getTgt() {
            return tgt;
        }

        public void setTgt(String tgt) {
            this.tgt = tgt;
        }
    }
}
