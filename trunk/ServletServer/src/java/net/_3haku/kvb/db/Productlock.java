package net._3haku.kvb.db;



/**
 * Productlock generated by hbm2java
 */
public class Productlock  implements java.io.Serializable {


     private Integer id;
     private String pstring;
     private String uid;

    public Productlock() {
    }

    public Productlock(String pstring, String uid) {
       this.pstring = pstring;
       this.uid = uid;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPstring() {
        return this.pstring;
    }
    
    public void setPstring(String pstring) {
        this.pstring = pstring;
    }
    public String getUid() {
        return this.uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }




}

