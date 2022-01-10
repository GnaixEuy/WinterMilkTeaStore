package cn.limitless.the_back_end.service.impl;

import cn.limitless.the_back_end.dao.MaterialDao;
import cn.limitless.the_back_end.entity.Material;
import cn.limitless.the_back_end.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/24
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Service
public class MaterialServiceImpl implements MaterialService {


	private final MaterialDao materialDao;

	@Autowired
	public MaterialServiceImpl(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}

	/**
	 * 获取所有原料
	 *
	 * @return 返回所有原料的list
	 */
	@Override
	public List<Material> findAllMaterial() {
		return this.materialDao.findAllMaterial();
	}

	/**
	 * 通过名字查找原料信息
	 *
	 * @param name 原料信息
	 * @return 返回原料实体对象
	 */
	@Override
	public Material findMaterialByName(String name) {
		return this.materialDao.selectMaterial(name);
	}

	/**
	 * 模糊查询原料
	 *
	 * @param likeName 模糊字段
	 * @return 返回原料实体集合
	 */
	@Override
	public List<Material> quickFindMaterialByName(String likeName) {
		return this.materialDao.likeMaterialName(likeName);
	}

	/**
	 * 增加原料
	 *
	 * @param name  原料名字
	 * @param stock 原料库存
	 * @param price 原料价格
	 * @return 返回是否成功
	 */
	@Override
	public boolean addMaterial(String name, Integer stock, Double price) {
		final Material material = new Material(name, stock, price);
		return this.materialDao.addMaterial(material) == 1;
	}

	/**
	 * 通过名字删除原料
	 *
	 * @param name 原料名字
	 * @return 返回是否成功
	 */
	@Override
	public boolean deleteMaterial(String name) {
		return this.materialDao.deleteMaterial(name) == 1;
	}

	/**
	 * 更新原料信息
	 *
	 * @param name  要更新的原料名字
	 * @param stock 新的库存
	 * @param price 新的价格
	 * @return 返回是否成功
	 */
	@Override
	public boolean updateMaterial(String name, Integer stock, Double price) {
		final Material material = this.materialDao.selectMaterial(name);
//		material.setMaterialPrice(price);
		material.setMaterialStock(stock);
		return this.materialDao.updateMaterial(material) == 1;
	}
}
