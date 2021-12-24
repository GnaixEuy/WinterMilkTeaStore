package cn.limitless.the_back_end.service;

import cn.limitless.the_back_end.entity.Material;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/24
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
public interface MaterialService {

	/**
	 * 获取所有原料
	 *
	 * @return 返回所有原料的list
	 */
	List<Material> findAllMaterial();

	/**
	 * 通过名字查找原料信息
	 *
	 * @param name 原料信息
	 * @return 返回原料实体对象
	 */
	Material findMaterialByName(String name);

	/**
	 * 增加原料
	 *
	 * @param name  原料名字
	 * @param stock 原料库存
	 * @param price 原料价格
	 * @return 返回是否成功
	 */
	boolean addMaterial(String name, Integer stock, Double price);

	/**
	 * 通过名字删除原料
	 *
	 * @param name 原料名字
	 * @return 返回是否成功
	 */
	boolean deleteMaterial(String name);

	/**
	 * 更新原料信息
	 *
	 * @param name  要更新的原料名字
	 * @param stock 新的库存
	 * @param price 新的价格
	 * @return 返回是否成功
	 */
	boolean updateMaterial(String name, Integer stock, Double price);

}
