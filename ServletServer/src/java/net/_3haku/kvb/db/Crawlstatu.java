package net._3haku.kvb.db;
// Generated 17-����-10 ���� 03:35 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Crawlstatu generated by hbm2java
 */
public class Crawlstatu  implements java.io.Serializable {


     private Integer id;
     private int uid;
     private String statu;
     private String kbhtml;
     private Date lstime;

    public Crawlstatu() {
    }

    public Crawlstatu(String statu, String kbhtml, Date lstime) {
       this.statu = statu;
       this.kbhtml = kbhtml;
       this.lstime = lstime;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getUid() {
        return this.uid;
    }
    
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getStatu() {
        return this.statu;
    }
    
    public void setStatu(String statu) {
        this.statu = statu;
    }
    public String getKbhtml() {
        return this.kbhtml;
    }
    
    public void setKbhtml(String kbhtml) {
        this.kbhtml = kbhtml;
    }
    public Date getLstime() {
        return this.lstime;
    }
    
    public void setLstime(Date lstime) {
        this.lstime = lstime;
    }




}


