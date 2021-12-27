package cn.limitless.the_back_end.service;

import cn.limitless.the_back_end.entity.Comment;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/27
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
public interface CommentService {

	/**
	 * 查询所有评价
	 *
	 * @return 订单实体类的集合
	 */
	List<Comment> queryComments();

	/**
	 * 查询指定用户的评论
	 *
	 * @param id 用户id
	 * @return 订单实体类的集合
	 */
	List<Comment> queryCommentsByUserId(String id);

	/**
	 * 所有订单的分页查询
	 *
	 * @param pageNum  当前页数
	 * @param pageSize 页面最大条目数
	 * @return pgeInfo包装的订单实体类的集合
	 */
	PageInfo<Comment> spiltComments(Integer pageNum, Integer pageSize);

	/**
	 * 通过订单编号id 查询评论
	 *
	 * @param id 订单id
	 * @return 订单实体类
	 */
	Comment queryCommentByOrderId(String id);

	/**
	 * 添加评论实体内容
	 *
	 * @param orderId 订单id
	 * @param userId  用户id
	 * @return 返回是否成功
	 */
	boolean addComment(String orderId, String userId);

	/**
	 * 删除评论,不推荐使用，拓展功能待认定
	 *
	 * @param id 评论id
	 * @return 返回是否成功
	 */
	@Deprecated
	boolean deleteComment(String id);

	/**
	 * 更新评论内容
	 *
	 * @param orderId 订单id
	 * @param content 评论内容
	 * @return 返回是否成功
	 */
	boolean updateComment(String orderId, String content);


}
