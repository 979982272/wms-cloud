package cn.czcxy.xj.basicclient.development.service.impl;

import cn.czcxy.xj.basicclient.development.entity.DataBase;
import cn.czcxy.xj.basicclient.development.mapper.DataBaseMapper;
import cn.czcxy.xj.basicclient.development.mapper.DevelopmentMapper;
import cn.czcxy.xj.basicclient.development.service.IDataBaseService;
import cn.czcxy.xj.core.util.FreemakerUtil;
import cn.czcxy.xj.core.util.PropertiesUtil;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/24 0024.
 */
@Service
public class DataBaseServiceImpl implements IDataBaseService {
    @Resource
    private DataBaseMapper dataBaseMapper;

    @Resource
    private DevelopmentMapper developmentMapper;

    @Override
    public List<DataBase> findDataBase() {
        return dataBaseMapper.showTables();
    }


}
