package cn.limitless.the_back_end.controller;

import cn.limitless.the_back_end.entity.Material;
import cn.limitless.the_back_end.model.ObjectModel;
import cn.limitless.the_back_end.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>原料controller</p>
 *
 * @author GnaixEuy
 * @date 2021/12/24
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@RestController
@RequestMapping(value = {"/material"})
public class MaterialAction {

	private final MaterialService materialService;

	@Autowired
	public MaterialAction(MaterialService materialService) {
		this.materialService = materialService;
	}

	@RequestMapping(value = {"/materials.do"}, method = {RequestMethod.GET})
	public ObjectModel getMaterials() {
		final List<Material> allMaterial = this.materialService.findAllMaterial();
		final ObjectModel objectModel = new ObjectModel();
		if (allMaterial.size() == 0) {
			objectModel.setRequestServiceStatus("failed");
		} else {
			objectModel.setObject(allMaterial);
		}
		return objectModel;
	}

	public ObjectModel addMaterial(String materialName, Integer materialStock, Double materialPrice) {
		final boolean b = this.materialService.addMaterial(materialName, materialStock, materialPrice);
//		if ()
		return null;

	}


}
