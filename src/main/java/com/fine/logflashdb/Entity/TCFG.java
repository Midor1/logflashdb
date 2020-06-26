package com.fine.logflashdb.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tcfg")
public class TCFG {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String TCFG_json;

    public TCFG() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTCFG_json() {
        return TCFG_json;
    }

    public void setTCFG_json(String TCFG_json) {
        this.TCFG_json = TCFG_json;
    }
}
