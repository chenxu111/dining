package com.nbtlxx.dining.pojo;

public class UserInfo {
    private Integer id;

    private String name;

    private Integer departmentId;

    private Integer status;

    public UserInfo(Integer id, String name, Integer departmentId, Integer status) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.status = status;
    }

    public UserInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}