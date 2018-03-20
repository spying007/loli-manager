package co.loli.service.impl;

import co.loli.manager.mapper.ProductMapper;
import co.loli.manager.mapper.ProductdescMapper;
import co.loli.pojo.Product;
import co.loli.pojo.Productdesc;
import co.loli.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductdescMapper productdescMapper;

    @Override
    public void saveProductAndDesc(Product product, String editorValue) {
//        同一事务中
        this.productMapper.insertSelective(product);

        Productdesc productdesc = new Productdesc();
        productdesc.setId(product.getId());
        productdesc.setProductdesc(editorValue);

        this.productdescMapper.insertSelective(productdesc);
    }
}
