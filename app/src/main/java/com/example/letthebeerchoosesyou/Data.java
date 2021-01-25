package com.example.letthebeerchoosesyou;

import java.io.Serializable;

public class Data implements Serializable {

    public int id;
    public Object created_at;
    public Object updated_at;
    public String category;
    public String styleFamily;
    public String style;
    public int srmFrom;
    public int srmTo;
    public float abvFrom;
    public float abvTo;
    public int ibuFrom;
    public int ibuTo;
    public String imgsrc;

    public String getCategory(){
        return this.category;
    }

    public String getStyleFamily() {
        return this.styleFamily;
    }

    public String getStyle() {
        return this.style;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setStyle(String style){
        this.style = style;
    }

    public void setStyleFamily(String styleFamily){
        this.styleFamily = styleFamily;
    }


    @Override
    public String toString() {
        return   category + ' '
                 + styleFamily + ' '
                 + style + ' ' ;
    }
    
}
