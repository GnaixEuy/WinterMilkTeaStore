package cn.limitless.the_back_end.controller;

import cn.limitless.the_back_end.entity.Comment;
import cn.limitless.the_back_end.model.ObjectModel;
import cn.limitless.the_back_end.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
public class CommentAction {


	private final CommentService commentService;

	@Autowired
	public CommentAction(CommentService commentService) {
		this.commentService = commentService;
	}

	@RequestMapping(value = {"/all.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "获取所有评论内容", notes = "无需任何参数")
	public ObjectModel getAllComment() {
		final ObjectModel objectModel = new ObjectModel();
		final List<Comment> comments = this.commentService.queryComments();
		if (comments == null) {
			objectModel.setRequestServiceStatus("failed");
		} else {
			objectModel.setObject(comments);
		}
		return objectModel;
	}
}
