package com.ertc.taskmangwt.common;

public class StatusDto {
    Long id;
    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StatusDto() {
    }
}
