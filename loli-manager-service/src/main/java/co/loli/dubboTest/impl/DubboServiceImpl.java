package co.loli.dubboTest.impl;

import co.loli.dubboTest.DubboService;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class DubboServiceImpl implements DubboService{

    @Override
    public String getDate() {
        return new Date().toString();
    }
}
