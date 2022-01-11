package cn.limitless.the_back_end.controller;

import cn.limitless.the_back_end.entity.Comment;
import cn.limitless.the_back_end.model.ObjectModel;
import cn.limitless.the_back_end.service.CommentService;
import cn.limitless.the_back_end.service.OrderService;
import cn.limitless.the_back_end.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/28
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@RestController
@RequestMapping(value = {"/comment"})
@Api(value = "评论模块接口")
@CrossOrigin(value = "*")
public class CommentAction {

	private static final int PAGE_SHOW_SIZE = 5;

	private final CommentService commentService;
	private final UserService userService;
	private final OrderService orderService;

	@Autowired
	public CommentAction(CommentService commentService, UserService userService, OrderService orderService) {
		this.commentService = commentService;
		this.userService = userService;
		this.orderService = orderService;
	}

	@RequestMapping(value = {"/all.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "获取所有评论内容", notes = "无需任何参数")
	public ObjectModel getAllComment() {
		final ObjectModel objectModel = new ObjectModel();
		final List<Comment> comments = this.commentService.queryComments();
		for (Comment comment : comments) {
			comment.setCommentUserId(this.userService.findUserById(comment.getCommentUserId()).getUserName());
			comment.setCommentId(this.orderService.queryOrderByOrderId(comment.getCommentOrderId()).getOrderCreateDateTime().toString());
		}
		if (comments.size() < 1) {
			objectModel.setRequestServiceStatus("failed");
		} else {
			objectModel.setObject(comments);
		}
		return objectModel;
	}


	@RequestMapping(value = {"/getCommentsByUserId.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "通过用户id获取该用户的所有评论接口", notes = "视工程进度是否改为token自动填充,请同时提供，改为token只需后端修改")
	public ObjectModel getCommentsByUserId(@ApiParam(value = "用户的id，视工程进度是否改为token自动填充") @RequestParam(name = "id") String userId) {
		final ObjectModel objectModel = new ObjectModel();
		if (userId == null || "".equals(userId)) {
			objectModel.setRequestServiceStatus("failed");
		} else {
			final List<Comment> comments = this.commentService.queryCommentsByUserId(userId);
			objectModel.setObject(comments);
		}
		return objectModel;
	}

	@RequestMapping(value = {"/getCommentByOrderId.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "通过订单id获取对应评论接口", notes = "请提供订单id")
	public ObjectModel getCommentsByOrderId(@ApiParam(value = "订单的id，视工程进度是否改为token自动填充") @RequestParam(name = "id") String orderId) {
		final ObjectModel objectModel = new ObjectModel();
		if (orderId == null || "".equals(orderId)) {
			objectModel.error();
		} else {
			final Comment comment = this.commentService.queryCommentByOrderId(orderId);
			objectModel.setObject(comment);
		}
		return objectModel;
	}

	@RequestMapping(value = {"/ajaxSpiltComments.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "所有评论分页接口", notes = "需要提供一个参数num，不提供则默认为1，条目为5")
	public ObjectModel spiltComments(@RequestParam(name = "num", defaultValue = "1") Integer pageNum) {
		final ObjectModel objectModel = new ObjectModel();
		final PageInfo<Comment> commentPageInfo = this.commentService.spiltComments(pageNum, CommentAction.PAGE_SHOW_SIZE);
		if (commentPageInfo == null) {
			objectModel.error();
		} else {
			objectModel.setObject(commentPageInfo);
		}
		return objectModel;
	}

	@RequestMapping(value = {"/frontDeskAjaxSpiltComments.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "所有评论分页接口", notes = "需要提供一个参数num，不提供则默认为1，条目为3")
	public ObjectModel spiltComments2(@RequestParam(name = "num", defaultValue = "1") Integer pageNum) {
		final ObjectModel objectModel = new ObjectModel();
		final PageInfo<Comment> commentPageInfo = this.commentService.spiltComments(pageNum, 3);
		final List<Comment> list = commentPageInfo.getList();
		Collections.reverse(list);
		commentPageInfo.setList(list);
		if (commentPageInfo.getList().size() < 1) {
			objectModel.error();
		} else {
			objectModel.setObject(commentPageInfo);
		}
		return objectModel;
	}

	@RequestMapping(value = {"/addComment.do"}, method = {RequestMethod.POST})
	@ApiOperation(value = "评论接口", notes = "需要传入userId，orderId，评论内容,userId视工程进度是否由token转化")
	public ObjectModel makeComment(String userId, String orderId, String content) {
		final ObjectModel objectModel = new ObjectModel();
		if (userId == null || "".equals(userId) || orderId == null || "".equals(orderId) || content == null || "".equals(content)) {
			objectModel.error();
		} else {
			final Comment comment = this.commentService.queryCommentByOrderId(orderId);
			if (comment != null) {
				objectModel.error();
				return objectModel;
			}
			final boolean b = this.commentService.addComment(orderId, userId, content);
			if (!b) {
				objectModel.error();
			}
		}
		return objectModel;
	}

	@RequestMapping(value = {"/delete.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "删除评论接口", notes = "传入评论id删除该评论")
	public ObjectModel deleteComment(@ApiParam(value = "评论的id", required = true) @RequestParam(name = "id") String commentId) {
		final ObjectModel objectModel = new ObjectModel();
		if (commentId == null || "".equals(commentId)) {
			objectModel.error();
		} else {
			final boolean b = this.commentService.deleteComment(commentId);
			if (!b) {
				objectModel.error();
			}
		}
		return objectModel;
	}

	@RequestMapping(value = {"/nums.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "获取总评论数")
	public ObjectModel commentNum() {
		final List<Comment> comments = this.commentService.queryComments();
		return new ObjectModel(comments.size());
	}


}
