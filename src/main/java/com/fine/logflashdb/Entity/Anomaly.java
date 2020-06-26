package com.fine.logflashdb.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "anomaly_log")
public class Anomaly {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)")
    private int id;
    @Column(columnDefinition = "VARCHAR(100)")
    private String time;
    @JsonProperty("unix_time")
    @Column(columnDefinition = "VARCHAR(15)")
    private String unixtime;
    @Column(columnDefinition = "VARCHAR(20)")
    private String level;
    @Column(columnDefinition = "VARCHAR(500)")
    private String component;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(columnDefinition = "TEXT")
    private String template;
    @JsonProperty("param_list")
    @Column(columnDefinition = "TEXT")
    private String paramlist;
    @JsonProperty("event_id")
    @Column(columnDefinition = "VARCHAR(200)")
    private String eventid;
    @JsonProperty("anomaly_logs")
    @Column(columnDefinition = "TEXT")
    private String anomalylogs;
    @JsonProperty("anomaly_request")
    @Column(columnDefinition = "TEXT")
    private String anomalyrequest;
    @JsonProperty("anomaly_window")
    @Column(columnDefinition = "VARCHAR(200)")
    private String anomalywindow;
    @JsonProperty("anomaly_type")
    @Column(columnDefinition = "VARCHAR(10)")
    private String anomalytype;
    @JsonProperty("anomaly_templates")
    @Column(columnDefinition = "VARCHAR(500)")
    private String anomalytemplates;
    @Column(columnDefinition = "TEXT")
    private String logsequence_json;

    public Anomaly() {
    }

    public Anomaly(String time, String unixtime, String level, String component, String content, String template, String paramlist, String eventid, String anomalylogs, String anomalyrequest, String anomalywindow, String anomalytype, String anomalytemplates, String logsequence_json) {
        this.time = time;
        this.unixtime = unixtime;
        this.level = level;
        this.component = component;
        this.content = content;
        this.template = template;
        this.paramlist = paramlist;
        this.eventid = eventid;
        this.anomalylogs = anomalylogs;
        this.anomalyrequest = anomalyrequest;
        this.anomalywindow = anomalywindow;
        this.anomalytype = anomalytype;
        this.anomalytemplates = anomalytemplates;
        this.logsequence_json = logsequence_json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(String unixtime) {
        this.unixtime = unixtime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getParamlist() {
        return paramlist;
    }

    public void setParamlist(String paramlist) {
        this.paramlist = paramlist;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getAnomalylogs() {
        return anomalylogs;
    }

    public void setAnomalylogs(String anomalylogs) {
        this.anomalylogs = anomalylogs;
    }

    public String getAnomalyrequest() {
        return anomalyrequest;
    }

    public void setAnomalyrequest(String anomalyrequest) {
        this.anomalyrequest = anomalyrequest;
    }

    public String getAnomalywindow() {
        return anomalywindow;
    }

    public void setAnomalywindow(String anomalywindow) {
        this.anomalywindow = anomalywindow;
    }

    public String getAnomalytype() {
        return anomalytype;
    }

    public void setAnomalytype(String anomalytype) {
        this.anomalytype = anomalytype;
    }

    public String getAnomalytemplates() {
        return anomalytemplates;
    }

    public void setAnomalytemplates(String anomalytemplates) {
        this.anomalytemplates = anomalytemplates;
    }

    public String getLogsequence_json() {
        return logsequence_json;
    }

    public void setLogsequence_json(String logsequence_json) {
        this.logsequence_json = logsequence_json;
    }
}
