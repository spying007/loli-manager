package co.loli.service;

import java.util.List;

public interface BaseService<T> {

    List<T> queryAll();

    T queryById(Long id);

    List<T> queryExample(T t);

    Integer queryByExampleCount(T t);

    List<T> queryByPage(Integer page, Integer rows);

    void saveSelective(T t);

    T queryOne(T t);

    void save(T t);

    void update(T t);

    void updateSelective(T t);

    void deleteById(Long id);

    void deleteByIds(List<Object> ids);

}
