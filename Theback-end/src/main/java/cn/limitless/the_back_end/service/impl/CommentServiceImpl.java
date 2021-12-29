package cn.limitless.the_back_end.service.impl;

import cn.limitless.the_back_end.dao.CommentDao;
import cn.limitless.the_back_end.entity.Comment;
import cn.limitless.the_back_end.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/27
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Service
public class CommentServiceImpl implements CommentService {

	private final CommentDao commentDao;

	@Autowired
	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	/**
	 * 查询所有评价
	 *
	 * @return 订单实体类的集合
	 */
	@Override
	public List<Comment> queryComments() {
		return this.commentDao.selectComments();
	}

	/**
	 * 查询指定用户的评论
	 *
	 * @param id 用户id
	 * @return 订单实体类的集合
	 */
	@Override
	public List<Comment> queryCommentsByUserId(String id) {
		return this.commentDao.selectCommentByCustomerId(id);
	}

	/**
	 * 所有订单的分页查询
	 *
	 * @param pageNum  当前页数
	 * @param pageSize 页面最大条目数
	 * @return pgeInfo包装的订单实体类的集合
	 */
	@Override
	public PageInfo<Comment> spiltComments(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		final List<Comment> comments = this.commentDao.selectComments();
		return new PageInfo<>(comments);
	}

	/**
	 * 通过订单编号id 查询评论
	 *
	 * @param id 订单id
	 * @return 订单实体类
	 */
	@Override
	public Comment queryCommentByOrderId(String id) {
		return this.commentDao.selectCommentByOrderId(id);
	}

	/**
	 * 添加评论实体内容
	 *
	 * @param orderId 订单id
	 * @param userId  用户id
	 * @return 返回是否成功
	 */
	@Override
	public boolean addComment(String orderId, String userId, String content) {
		final Comment comment = new Comment();
		comment.setCommentOrderId(orderId);
		comment.setCommentUserId(userId);
		comment.setIsFinish(Boolean.TRUE);
		comment.setCommentId(userId + orderId);
		comment.setCommentContent(content);
		return this.commentDao.insertComment(comment) == 1;
	}

	/**
	 * 删除评论,不推荐使用，拓展功能待认定
	 *
	 * @param id 评论id
	 * @return 返回是否成功
	 */
	@Override
	@Deprecated
	public boolean deleteComment(String id) {
		return this.commentDao.deleteCommentById(id) == 1;
	}

	/**
	 * 更新评论内容
	 *
	 * @param orderId 订单id
	 * @param content 评论内容
	 * @return 返回是否成功
	 */
	@Override
	public boolean updateComment(String orderId, String content) {
		final Comment comment = this.commentDao.selectCommentByOrderId(orderId);
		comment.setCommentContent(content);
		comment.setIsFinish(Boolean.TRUE);
		return this.commentDao.updateComment(comment) == 1;
	}
}
