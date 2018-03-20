package co.loli.service;

import co.loli.pojo.Product;

public interface ProductService extends BaseService<Product> {
    void saveProductAndDesc(Product product,String editorValue);
}
