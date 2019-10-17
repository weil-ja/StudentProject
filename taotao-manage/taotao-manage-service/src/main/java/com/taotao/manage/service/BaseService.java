package com.taotao.manage.service;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manage.pojo.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class BaseService<T extends BasePojo> {

    @Autowired
    private Mapper<T> mapper;

//    public abstract Mapper<T> getMapper();

    /**
     * 根据主键查询数据
     *
     * @param id
     * @return
     */
    public T queryById(Long id){
        return this.mapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    public List<T> queryAll(){
        return this.mapper.select(null);
    }

    /**
     * 查询一条数据
     *
     * @param record
     * @return
     */
    public T queryOne(T record){
        return this.mapper.selectOne(record);
    }

    /**
     * 查询多条数据
     *
     * @param record
     * @return
     */
    public List<T> queryListByWhere(T record){
        return this.mapper.select(record);
    }

    /**
     * 查分页信息
     *
     * @param record
     * @param page
     * @param rows
     * @return
     */
    public PageInfo<T> queryPageListByWhere(T record,Integer page,Integer rows){
        PageHelper.startPage(page,rows);
        List<T> list=this.mapper.select(record);
        return new PageInfo<T>(list);
    }

    /**
     * 添加数据
     *
     * @param t
     * @return
     */
    public Integer save(T t){
        //设置创建时间和更新时间
        t.setCreated(new Date());
        t.setUpdated(t.getCreated());
        return this.mapper.insert(t);
    }

    /**
     * 选择不为null的数据作为插入数据
     *
     * @param t
     * @return
     */
    public Integer saveSelective(T t){
        t.setCreated(new Date());
        t.setUpdated(t.getCreated());
        return this.mapper.insertSelective(t);
    }

    /**
     * 根据id更新
     *
     * @param t
     * @return
     */
    public Integer update(T t){
        t.setUpdated(new Date());
        return this.mapper.updateByPrimaryKey(t);
    }

    /**
     *选择不为null的数据作为更新数据
     *
     * @param t
     * @return
     */
    public Integer updateSelective(T t){
        t.setUpdated(new Date());
        t.setCreated(null);
        return this.mapper.updateByPrimaryKeySelective(t);
    }

    /**
     * 根据id删除一条数据
     *
     * @param id
     * @return
     */
    public Integer deleteById(Long id){
        return this.mapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除多条数据
     *
     * @param ids
     * @param clazz
     * @param property
     * @return
     */
    public Integer deleteByIds(List<Object> ids,Class<T> clazz,String property){
        Example example=new Example(clazz);
        example.createCriteria().andIn(property,ids);
        return this.mapper.deleteByExample(example);
    }

    /**
     * 根据条件删除数据
     *
     * @param record
     * @return
     */
    public Integer deleteByWhere(T record){
        return this.mapper.delete(record);
    }
}
