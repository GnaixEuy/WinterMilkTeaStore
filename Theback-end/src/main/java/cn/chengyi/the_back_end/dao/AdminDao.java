package cn.chengyi.the_back_end.dao;

import cn.chengyi.the_back_end.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Mapper
public interface AdminDao {

   /**
    * 通过id查找持久层内的管理员数据
    * @param id 管理员id
    * @return Admin 返回一个管理员对象
    */
   Admin findAdminById(Integer id);

}
