package cn.chzu.buildingmaterials.storegoods.service;


import cn.chzu.buildingmaterials.storegoods.dao.StoreMapper;
import cn.chzu.buildingmaterials.storegoods.model.Store;
import cn.chzu.buildingmaterials.storegoods.model.StoreVo;
import cn.chzu.conf.util.UUID;
import cn.chzu.conf.util.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreMapper storeMapper;

    @Value("${com.image.path}")
    private String pathURL;

    //定义file全局变量
    static String saveFile;
    //新增商品
    @Override
    public Store add(Store store) {
        String id = store.getId();
        String goodsName = store.getGoodsName();
        //商品不能为空
        if (store.getGoodsName() == null || store.getGoodsName().isEmpty()) {
            store.setMsg("商品不能为空！");
            System.out.println(store.getMsg());
            return store;

        }

        //根据商品名称查询数据库，看是否已有该商品
        // Store goodsName1 = storeMapper.findGoodsName(goodsName);
        Store goodsName1 = storeMapper.findGoodsName(goodsName);
        if (goodsName1 != null) {
            store.setMsg("该商品已存在，请重新输入！");

        } else {
            if (id == null || id.isEmpty()) {
                store.setId(UUID.getUUID());
            }
            if (saveFile == null) {
                store.setMsg("图片上传失败！");
                return store;
            }

            store.setStoreName("总店");
            //设置图片访问地址
            store.setImage(pathURL + saveFile);
            storeMapper.add(store);
            store.setMsg("成功新增商品！");
        }
        System.out.println(store.getMsg());
        return store;


    }

    //查询所有，分页展示
    @Override
    public List<Store> findAll(String storeName) {

        return storeMapper.findAll(storeName);
    }

    //商品名模糊查询
    @Override
    public List<Store> findByGoodsName(String goodsName) {

        return storeMapper.findByGoodsName(goodsName);
    }

    // 删除单个商品
    @Override
    public Store delete(String id) {

        Store store = new Store();
        int delete = storeMapper.delete(id);
        if (delete == 1) {
            store.setMsg("删除成功！");
        } else {
            store.setMsg("删除失败！");
        }

        return store;
    }

    //根据id修改
    @Override
    public Store updateById(Store store) {

        int i = storeMapper.updateById(store);
        if (i == 1) {
            store.setMsg("修改成功！");
        } else {
            store.setMsg("修改失败！");
        }

        return store;
    }

    //根据分类类型查询该类型所有信息
    @Override
    public List<StoreVo> findAllKind(String kind) {
        List<StoreVo> list = new ArrayList<>();
        List<Store> allKind = storeMapper.findAllKind(kind);
        for (Store str : allKind) {
            StoreVo storeVo = new StoreVo();
            storeVo.setId(str.getId());
            storeVo.setImg(str.getImage());
            storeVo.setGoodsName(str.getGoodsName());
            storeVo.setSalesPrice(str.getSalesPrice());
            storeVo.setStoreName(str.getStoreName());
            storeVo.setSalesNumber(str.getSalesNumber());
            list.add(storeVo);
        }
        return list;
    }

    //图片上传
    @Override
    public void imgFile(MultipartFile file) {

        String fileName = FileUtils.saveFile(file);
        saveFile=fileName;
        System.out.println(saveFile);
    }


}
