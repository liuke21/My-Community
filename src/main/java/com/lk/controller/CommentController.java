package com.lk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lk.Mapper.CommentMapper;
import com.lk.Mapper.QuestionMapper;
import com.lk.dto.CommentDto;
import com.lk.dto.CommentsDto;
import com.lk.dto.SecCommentDto;
import com.lk.model.Question;
import com.lk.model.User;
import com.lk.service.CommentService;

@Controller
@Transactional
public class CommentController {
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private CommentService commentService;
	@ResponseBody
	@RequestMapping(value="/comment",method=RequestMethod.POST)
	public Object comment(HttpServletRequest request,@RequestBody CommentDto commentDto){
		User user = (User) request.getSession().getAttribute("user");
		com.lk.model.Comment comment = new com.lk.model.Comment();
		comment.setCommentator_id(user.getId());
		comment.setContent(commentDto.getContent());
		comment.setQuestion_id(commentDto.getQuestion_id());
		comment.setGmt_create(System.currentTimeMillis());
		comment.setGmt_motifid(comment.getGmt_create());
		comment.setType(commentDto.getType());
		commentMapper.insert(comment);
		//增加评论数
		Question question=questionMapper.selectQuestionById(commentDto.getQuestion_id());
		commentService.inComment_count(question,commentDto.getQuestion_id());
		List<com.lk.model.Comment> comments=commentMapper.selectComment(commentDto.getQuestion_id());
		List<CommentsDto> commentsDto = commentService.changeCommentDto(comments);
		return commentsDto;
	}
	
	//添加子评论
	@ResponseBody
	@RequestMapping(value="/sec_comment",method=RequestMethod.POST)
	public Object sec_comment(HttpServletRequest request,@RequestBody SecCommentDto secCommentDto){
		System.out.println(secCommentDto.getContent());
		User user = (User) request.getSession().getAttribute("user");
		com.lk.model.Comment comment = new com.lk.model.Comment();
		comment.setCommentator_id(user.getId());
		comment.setContent(secCommentDto.getContent());
		comment.setQuestion_id(secCommentDto.getQuestion_id());
		comment.setGmt_create(System.currentTimeMillis());
		comment.setGmt_motifid(comment.getGmt_create());
		comment.setType(secCommentDto.getType());
		comment.setComment_id(secCommentDto.getComment_id());
		commentMapper.insertsec(comment);
		//增加评论数
		Question question=questionMapper.selectQuestionById(secCommentDto.getQuestion_id());
		commentService.inComment_count(question,secCommentDto.getQuestion_id());
		//增加评论的评论数
		commentService.inSecComment_count(secCommentDto.getComment_id());
		List<com.lk.model.Comment> comments=commentMapper.selectSecComment(secCommentDto.getQuestion_id(), secCommentDto.getComment_id());
		List<CommentsDto> seCommentsDto = commentService.changeCommentDto(comments);
		return seCommentsDto;
	}
}
