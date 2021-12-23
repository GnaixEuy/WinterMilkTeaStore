package cn.chengyi.the_back_end.dao;

import cn.chengyi.the_back_end.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductDaoTest {

	@Autowired
	private ProductDao productDao;

	@Test
	void selectProductById() {
		Integer id = 1;
		final Product product = this.productDao.selectProductById(id);
		System.out.println("product.getProductId() = " + product.getProductId());
		System.out.println("product.getProductName() = " + product.getProductName());
		System.out.println("product.getProductType() = " + product.getProductType());
		System.out.println("product.getProductPrice() = " + product.getProductPrice());
		System.out.println("product.getProductImageId() = " + product.getProductImageId());
		System.out.println("product.getProductMaterialList() = " + product.getProductMaterialList());

	}

	@Test
	void selectProductByName() {
		String name = "橙汁";
		final Product product = this.productDao.selectProductByName(name);
		System.out.println("product.getProductId() = " + product.getProductId());
		System.out.println("product.getProductName() = " + product.getProductName());
		System.out.println("product.getProductType() = " + product.getProductType());
		System.out.println("product.getProductPrice() = " + product.getProductPrice());
		System.out.println("product.getProductImageId() = " + product.getProductImageId());
		System.out.println("product.getProductMaterialList() = " + product.getProductMaterialList());
	}

	@Test
	void vagueSelectProductByName() {

	}

	@Test
	void findAllProduct() {
		final List<Product> allProduct = this.productDao.findAllProduct(null);
		for (Product product : allProduct) {
			System.out.println("product.getProductId() = " + product.getProductId());
			System.out.println("product.getProductName() = " + product.getProductName());
			System.out.println("product.getProductType() = " + product.getProductType());
			System.out.println("product.getProductPrice() = " + product.getProductPrice());
			System.out.println("product.getProductImageId() = " + product.getProductImageId());
			System.out.println("product.getProductMaterialList() = " + product.getProductMaterialList());
		}
	}

	@Test
	void selectProductByType() {
		String type = "奶茶";
		final Product product = (Product) this.productDao.selectProductByType(type);
		System.out.println("product.getProductId() = " + product.getProductId());
		System.out.println("product.getProductName() = " + product.getProductName());
		System.out.println("product.getProductType() = " + product.getProductType());
		System.out.println("product.getProductPrice() = " + product.getProductPrice());
		System.out.println("product.getProductImageId() = " + product.getProductImageId());
		System.out.println("product.getProductMaterialList() = " + product.getProductMaterialList());
	}

	@Test
	void addProduct() {
		String name = "冬乳奶茶";
		String type = "奶茶";
		Double price = 18.0;
		final ArrayList<String> strings = new ArrayList<>();
		strings.add("水");
		strings.add("糖");
		strings.add("奶茶粉");
		final Product product = new Product(name, type, price, "iiiiii", strings);
		final int integer = this.productDao.addProduct(product);
		if (integer == 1) {
			System.out.println("成功");
		} else {
			System.out.println("失败");
		}
	}

	@Test
	void updateProduct() {
		String name = "冬乳奶茶";
		Product product = this.productDao.selectProductByName(name);
		System.out.println("product.getProductPrice() = " + product.getProductPrice());
		product.setProductPrice(11.0);
		final ArrayList<String> strings = new ArrayList<>();
		strings.add("几把");
		product.setProductMaterialList(strings);
		final int integer = this.productDao.updateProduct(product);
		if (integer == 1) {
			product = this.productDao.selectProductByName(name);
			System.out.println("product.getProductPrice() = " + product.getProductPrice());
		} else {
			System.out.println("失败");
		}
	}

	@Test
	void deleteProduct() {
		String name = "冬乳奶茶";
		final Product product = this.productDao.selectProductByName(name);
		final int integer = this.productDao.deleteProduct(product.getProductId());
		if (integer == 1) {
			System.out.println("成功");
		} else {
			System.out.println("失败");
		}
	}


}
