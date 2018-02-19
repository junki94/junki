package com.dokgo.junkiproj.Data;

/**
 * Created by JawsGeun on 2018-01-21.
 */

public class ListData {
    private String name;
    private String addr;
    private String id;

    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = "이름 : "+name;
    }

    public void setAddr(String addr) {
        this.addr = "주소 : "+addr;
    }

    public void setId(String id) {
        this.id = id;
    }

}
