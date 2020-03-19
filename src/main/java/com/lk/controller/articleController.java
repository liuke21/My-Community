package com.lk.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lk.Mapper.CommentMapper;
import com.lk.Mapper.QuestionMapper;
import com.lk.dto.CommentsDto;
import com.lk.dto.QUestionDto;
import com.lk.model.Comment;
import com.lk.model.Question;
import com.lk.service.CommentService;
import com.lk.service.QUestionService;

@Controller
public class articleController {
	
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private QUestionService questionService;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private CommentService commentService;
	@RequestMapping("/artile")
	public String article(@RequestParam(name="id") int id,Model model){
		Question question = questionMapper.selectQuestionById(id);
		//增加阅读数
		questionService.inViewCount(question);
		model.addAttribute("question", question);
		//相关问题
		List<Question> questions = questionMapper.selectQuestionBySearch(0, 5, (String)question.getTitle().subSequence(question.getTitle().length()-2, question.getTitle().length()));
		
		List<Question> questions2=new ArrayList<Question>();
		questions2.add(question);
		List<QUestionDto> questionDtos=questionService.changQuestion(questions2);
		model.addAttribute("questionDtos", questionDtos.get(0));
		model.addAttribute("questions",questions);
		
		//一级评论
		List<com.lk.model.Comment> comments=commentMapper.selectComment(id);
		//二级评论
		List<com.lk.model.Comment> Seccomments =commentMapper.selectSecComments(id);
		List<CommentsDto> commentsDto = commentService.changeCommentDto(comments);
		List<CommentsDto> SeccommentsDto = commentService.changeCommentDto(Seccomments);
		model.addAttribute("commentsDto",commentsDto);
		model.addAttribute("SeccommentsDto",SeccommentsDto);
		return "artile";
	}
	
	@RequestMapping("/artile/add_likeCount")
	public String addLike_count(@RequestParam(name="id") int id,@RequestParam(name="like_count") Long like_count,Model model){
		questionService.inLikeCount(id,like_count+1);
		return "redirect:/";
	}
}
