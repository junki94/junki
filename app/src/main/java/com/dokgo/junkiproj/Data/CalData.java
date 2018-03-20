package com.dokgo.junkiproj.Data;

/**
 * Created by user on 2018-02-09.
 */

public class CalData {
    private String name;
    private String addr;
    private String memo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }

    public String getMemo() { return memo;}

    public void setName(String name) {
        this.name = "이름 : "+name;
    }

    public void setAddr(String addr) {

        this.addr = "주소 : "+addr;
    }

    public void setMemo(String memo) { this.memo = "메모 : "+memo;}
}
