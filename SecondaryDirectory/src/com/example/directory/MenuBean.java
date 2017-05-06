package com.example.directory;

import java.util.List;

/**
 *
 */
public class MenuBean {
    String menuName;
    List<String> name;

    public MenuBean(String menuName, List<String> name) {
        this.menuName = menuName;
        this.name = name;
    }


    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MenuBean{" +
                "menuName='" + menuName + '\'' +
                ", name=" + name +
                '}';
    }
}
