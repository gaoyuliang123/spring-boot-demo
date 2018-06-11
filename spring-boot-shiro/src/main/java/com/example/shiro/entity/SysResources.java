package com.example.shiro.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "sys_resources")
public class SysResources implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 资源名称
     */
    @Column(name = "res_name")
    private String resName;

    /**
     * 资源url
     */
    @Column(name = "res_url")
    private String resUrl;

    /**
     * 资源类型   1:菜单    2：按钮
     */
    @Column(name = "res_type")
    private Integer resType;

    /**
     * 父资源
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 排序
     */
    @Column(name = "res_sort")
    private Integer resSort;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取资源名称
     *
     * @return res_name - 资源名称
     */
    public String getResName() {
        return resName;
    }

    /**
     * 设置资源名称
     *
     * @param resName 资源名称
     */
    public void setResName(String resName) {
        this.resName = resName;
    }

    /**
     * 获取资源url
     *
     * @return res_url - 资源url
     */
    public String getResUrl() {
        return resUrl;
    }

    /**
     * 设置资源url
     *
     * @param resUrl 资源url
     */
    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    /**
     * 获取资源类型   1:菜单    2：按钮
     *
     * @return res_type - 资源类型   1:菜单    2：按钮
     */
    public Integer getResType() {
        return resType;
    }

    /**
     * 设置资源类型   1:菜单    2：按钮
     *
     * @param resType 资源类型   1:菜单    2：按钮
     */
    public void setResType(Integer resType) {
        this.resType = resType;
    }

    /**
     * 获取父资源
     *
     * @return parent_id - 父资源
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父资源
     *
     * @param parentId 父资源
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取排序
     *
     * @return res_sort - 排序
     */
    public Integer getResSort() {
        return resSort;
    }

    /**
     * 设置排序
     *
     * @param resSort 排序
     */
    public void setResSort(Integer resSort) {
        this.resSort = resSort;
    }
}