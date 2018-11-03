package com.lc.forum.system.builder;

import tk.mybatis.mapper.entity.Example;

/**
 * @author qiumin
 * @create 2018/11/3 20:38
 * @desc
 **/
public class MapperCriteriaBuilder {

    private Example example;

    private Example.Criteria criteria;

    public MapperCriteriaBuilder(Class<?> tClass){
        example =  new Example(tClass);
        criteria = example.createCriteria();
    }

    public static MapperCriteriaBuilder instances(Class<?> tClass){
        return new MapperCriteriaBuilder(tClass);
    }
    public Example.Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Example.Criteria criteria) {
        this.criteria = criteria;
    }

    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }

    /**
     *  构建 属性名=属性值条件
     * @param property
     * @param value
     */
    public void addEq(String property,Object value){
        criteria.andEqualTo(property,value);
    }

    /**
     * 构建属性名 >= 属性值
     * @param property
     * @param value
     */
    public void addGe(String property,Object value){
        criteria.andGreaterThanOrEqualTo(property,value);
    }

    /**
     * 构建属性名 > 属性值
     * @param property
     * @param value
     */
    public void addGt(String property,Object value){
        criteria.andGreaterThan(property,value);
    }

    /**
     * 构建 属性名 <= 属性值
     * @param property
     * @param value
     */
    public void addLe(String property,Object value){
        criteria.andLessThanOrEqualTo(property,value);
    }

    /**
     * 构建 属性名 < 属性值
     * @param property
     * @param value
     */
    public void addLt(String property,Object value){
        criteria.andLessThan(property,value);
    }

    /**
     * 构建 模糊查询
     * @param property
     * @param value
     */
    public void addLike(String property,String value){
        criteria.andLike(property,value);
    }

    /**
     * 构建模糊查询
     * @param property
     * @param value
     */
    public void addNotLike(String property,String value){
        criteria.andNotLike(property, value);
    }

    /**
     * 构建 in (?,?)
     * @param property
     * @param list
     */
    public void addIn(String property, Iterable<?> list){
        criteria.andIn(property,list);
    }

    /**
     *  构建 Not in (?,?)
     * @param property
     * @param list
     */
    public void addNotIn(String property,Iterable<?> list){
        criteria.andNotIn(property,list);
    }

    /**
     * 根据某属性 降序排序
     * @param property
     */
    public void addOrderDesc(String property){
        example.orderBy(property).desc();
    }

    /**
     * 根据某属性 降序排序
     * @param property
     */
    public void addOrderAsc(String property){
        example.orderBy(property).asc();
    }
}
