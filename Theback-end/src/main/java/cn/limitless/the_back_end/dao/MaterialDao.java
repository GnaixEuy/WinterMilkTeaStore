package cn.limitless.the_back_end.dao;

import cn.limitless.the_back_end.entity.Material;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/22
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Mapper
public interface MaterialDao {

	/**
	 * 通过原料名字查询原料信息
	 *
	 * @param materialName 原料名字
	 * @return material 返回原料类
	 */
	Material selectMaterial(String materialName);

	/**
	 * 查询所有原料
	 *
	 * @return 返回material 原料对象集合
	 */
	List<Material> findAllMaterial();

	/**
	 * 模糊搜索原料
	 *
	 * @return 返回模糊的集合
	 */
	List<Material> likeMaterialName(String name);

	/**
	 * 增加原料
	 *
	 * @param material 原料对象
	 * @return 返回改变对行数
	 */
	Integer addMaterial(Material material);

	/**
	 * 删除原料
	 *
	 * @param materialName 原料对名字
	 * @return 返回删除的行数
	 */
	Integer deleteMaterial(String materialName);

	/**
	 * 更新原料信息
	 *
	 * @param material 改变后的原料
	 * @return 改变的原料数
	 */
	Integer updateMaterial(Material material);

}
