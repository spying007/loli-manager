package co.loli.service.impl;

import co.loli.manager.mapper.CategoryMapper;
import co.loli.pojo.Category;
import co.loli.service.CategoryService;
import co.loli.service.redis.utils.impl.RedisUtilsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.github.abel533.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    @Autowired
    private RedisUtilsImpl redisUtils;

    @Autowired
    private CategoryMapper categoryMapper;

    private static final String MANAGER_TREE_MENU = "MANAGER_TREE_MENU";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public List<Category> queryAll() {
        //判断redis缓存中是否有数据
        try {
            String jsonData = redisUtils.get(MANAGER_TREE_MENU);
            if (jsonData != null) {
                CollectionType collectionType = MAPPER.getTypeFactory().constructCollectionType(List.class, Category.class);
                return MAPPER.readValue(jsonData, collectionType);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            List<Category> categoryList = categoryMapper.select(null);
        try {
            redisUtils.expire(MANAGER_TREE_MENU, MAPPER.writeValueAsString(categoryList), 60 * 60 * 24 * 30);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}
