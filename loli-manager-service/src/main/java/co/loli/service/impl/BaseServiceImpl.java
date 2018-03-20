package co.loli.service.impl;

import co.loli.service.BaseService;
import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private Mapper<T> mapper;

    private Class<T> clazz;

    public BaseServiceImpl() {
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }
    @Override
    public List<T> queryAll() {
        List<T> list = this.mapper.select(null);
        return list;
    }
    @Override
    public T queryById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> queryExample(T t) {
        List<T> list = this.mapper.select(t);
        return list;
    }

    @Override
    public Integer queryByExampleCount(T t) {
        int count = this.mapper.selectCount(t);
        return count;
    }

    @Override
    public List<T> queryByPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<T> list = this.mapper.select(null);
        return list;
    }

    @Override
    public T queryOne(T t) {
        T one = this.mapper.selectOne(t);
        return one;
    }

    @Override
    public void save(T t) {
        this.mapper.insert(t);

    }

    @Override
    public void saveSelective(T t) {
        this.mapper.insertSelective(t);

    }

    @Override
    public void update(T t) {
        this.mapper.updateByPrimaryKey(t);

    }

    @Override
    public void updateSelective(T t) {
        this.mapper.updateByPrimaryKeySelective(t);

    }

    @Override
    public void deleteById(Long id) {
        this.mapper.deleteByPrimaryKey(id);

    }

    @Override
    public void deleteByIds(List<Object> ids) {
        Example example = new Example(clazz);
        example.createCriteria().andIn("id", ids);
        this.mapper.deleteByExample(example);
    }
}
