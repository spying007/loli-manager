package co.loli.service.impl;

import co.loli.manager.mapper.ContentcategoryMapper;
import co.loli.pojo.Contentcategory;
import co.loli.service.ContentcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentcategoryImpl extends BaseServiceImpl<Contentcategory> implements ContentcategoryService {

    @Autowired
    private ContentcategoryMapper contentcategoryMapper;


    @Override
    public void deleteContentcategoryById(Contentcategory contentcategory) {
        List<Object> deleteIds = new ArrayList<>();
        deleteIds.add(contentcategory.getId());
        findSubNote(deleteIds,contentcategory.getId());
        this.deleteByIds(deleteIds);
    }

    private void findSubNote(List<Object> deleteIds,Long id) {
        Contentcategory contentcategory = new Contentcategory();
        contentcategory.setParentid(id);
        //查询当前分类的子类目
        List<Contentcategory> select = this.contentcategoryMapper.select(contentcategory);
        for (Contentcategory mapper : select) {
            deleteIds.add(mapper.getId());
            findSubNote(deleteIds,mapper.getId());
        }
    }
}
