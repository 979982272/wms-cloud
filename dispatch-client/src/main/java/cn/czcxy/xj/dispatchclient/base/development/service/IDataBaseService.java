package cn.czcxy.xj.dispatchclient.base.development.service;

/**
 * @Auther: wwh
 * @Date: 2018/6/30 0030 23:02
 * @Description:
 */
public interface IDataBaseService {
    /**
     * 创建实体类文件
     * @param ids
     * @param pack
     */
    void createEntityCode(String[] ids, String[] pack)throws Exception;
}
