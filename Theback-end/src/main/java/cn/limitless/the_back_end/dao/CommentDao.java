package cn.limitless.the_back_end.dao;

import cn.limitless.the_back_end.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>comment接口</p>
 *
 * @author GnaixEuy
 * @date 2021/12/27
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Mapper
public interface CommentDao {

	/**
	 * 通过id 查询订单评价
	 *
	 * @param id 订单id
	 * @return 返回一个订单评价的实体类
	 */
	Comment selectCommentById(String id);

	/**
	 * 通过订单编号查询评论
	 *
	 * @param id 订单编号
	 * @return 评论实体类
	 */
	Comment selectCommentByOrderId(String id);

	/**
	 * 通过用户id 查询历史评价
	 *
	 * @param id 用户的id
	 * @return 评价实体类的集合
	 */
	List<Comment> selectCommentByCustomerId(String id);

	/**
	 * 查询所有订单评价
	 *
	 * @return 返回一个订单评价的实体类的集合
	 */
	List<Comment> selectComments();

	/**
	 * 创建订单评价
	 *
	 * @param comment 订单评价实体类
	 * @return 返回改变的行数
	 */
	int insertComment(Comment comment);

	/**
	 * 通过订单评价的id 进行删除
	 *
	 * @param id 要删除的id
	 * @return 返回改变的行数
	 */
	int deleteCommentById(String id);

	/**
	 * 更新评价状态
	 *
	 * @param comment 评价实体类
	 * @return 返回改变的行数
	 */
	int updateComment(Comment comment);


}
