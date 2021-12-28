package cn.limitless.the_back_end.controller;

import cn.limitless.the_back_end.entity.Material;
import cn.limitless.the_back_end.model.ObjectModel;
import cn.limitless.the_back_end.service.MaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "原料接口")
@CrossOrigin(value = "*")
public class MaterialAction {

	private final MaterialService materialService;

	@Autowired
	public MaterialAction(MaterialService materialService) {
		this.materialService = materialService;
	}

	@RequestMapping(value = {"/materials.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "获取所有原料信息", notes = "不需要任何参数")
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

	@RequestMapping(value = {"/add.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "添加原料接口", notes = "原料名重复时，添加失败")
	public ObjectModel addMaterial(@ApiParam("原料名") @RequestParam(name = "name") String materialName,
	                               @ApiParam("原料库存") @RequestParam(name = "stock") Integer materialStock,
	                               @ApiParam("原料价格") @RequestParam(name = "price") Double materialPrice) {
		final boolean b = this.materialService.addMaterial(materialName, materialStock, materialPrice);
		final ObjectModel objectModel = new ObjectModel();
		if (!b) {
			objectModel.setRequestServiceStatus("failed");
		}
		return objectModel;
	}

	@RequestMapping(value = "/delete.do", method = {RequestMethod.DELETE})
	@ApiOperation(value = "原料删除接口")
	public ObjectModel deleteMaterial(@ApiParam(value = "要删除的原料名字") String name) {
		final boolean b = this.materialService.deleteMaterial(name);
		final ObjectModel objectModel = new ObjectModel();
		if (!b) {
			objectModel.setRequestServiceStatus("failed");
		}
		return objectModel;
	}

	@RequestMapping(value = {"/update.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "更新原料接口")
	public ObjectModel updateMaterial(Material material) {
		return null;
	}


}
