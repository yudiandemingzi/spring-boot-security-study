package com.jincou.entity;

/**
  * @Description: 用户对应角色信息
  *
  * @author xub
  * @date 2020/4/16 下午3:55
  */
public class Role {
    private Long id;
    private String name;



    public Role() {
    }

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

    public Role(Long id, String name) {

        this.id = id;
        this.name = name;
    }
}
