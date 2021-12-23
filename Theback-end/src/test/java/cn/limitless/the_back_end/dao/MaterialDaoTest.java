package cn.limitless.the_back_end.dao;

import cn.limitless.the_back_end.entity.Material;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/22
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@SpringBootTest
class MaterialDaoTest {

	@Autowired
	private MaterialDao materialDao;

	@Test
	void selectMaterial() {
		String name = "test1";
		final Material material = this.materialDao.selectMaterial(name);
		System.out.println("material.getMaterialName() = " + material.getMaterialName());
		System.out.println("material.getMaterialStock() = " + material.getMaterialStock());
		System.out.println("material.getMaterialPrice() = " + material.getMaterialPrice());
	}

	@Test
	void findAllMaterial() {
		final List<Material> allMaterial = this.materialDao.findAllMaterial();
		for (Material material : allMaterial) {
			System.out.println("material.getMaterialName() = " + material.getMaterialName());
			System.out.println("material.getMaterialStock() = " + material.getMaterialStock());
			System.out.println("material.getMaterialPrice() = " + material.getMaterialPrice());
			System.out.println("----------------------");
		}
	}

	@Test
	void addMaterial() {
		String name = "test2";
		Integer stock = 14;
		Double price = 697.3;
		final Material material = new Material(name, stock, price);
		final Integer integer = this.materialDao.addMaterial(material);
		if (integer == 1) {
			System.out.println("成功");
		} else {
			System.out.println("失败");
		}
	}

	@Test
	void deleteMaterial() {
		String name = "test2";
		final Integer integer = this.materialDao.deleteMaterial(name);
		if (integer == 1) {
			System.out.println("成功");
		} else {
			System.out.println("失败");
		}
	}

	@Test
	void updateMaterial() {
		String name = "test1";
		Material material = this.materialDao.selectMaterial(name);
		System.out.println("material.getMaterialPrice() = " + material.getMaterialPrice());
		material.setMaterialPrice(111.0);
		final Integer integer = this.materialDao.updateMaterial(material);
		if (integer == 1) {
			material = this.materialDao.selectMaterial(name);
			System.out.println("material.getMaterialPrice() = " + material.getMaterialPrice());
		} else {
			System.out.println("失败");
		}
	}
}