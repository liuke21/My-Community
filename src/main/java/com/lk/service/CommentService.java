package com.lk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lk.Mapper.CommentMapper;
import com.lk.Mapper.QuestionMapper;
import com.lk.Mapper.userMapper;
import com.lk.dto.CommentsDto;
import com.lk.dto.QUestionDto;
import com.lk.model.Comment;
import com.lk.model.Question;
import com.lk.model.User;
@Service
public class CommentService {
	
	@Autowired
	private QuestionMapper questionMapper;
	public void inComment_count(Question question,int id) {
		// TODO Auto-generated method stub
		//System.out.println(question.getComment_count());
		long comment_count=question.getComment_count();
		questionMapper.updateCommentCount(id,comment_count+1);
		//System.out.println(question.getComment_count());
	}
	
	@Autowired
	private userMapper userMapper;
	public List<CommentsDto> changeCommentDto(List<Comment> comments) {
		List<CommentsDto> commentsDto = new ArrayList<CommentsDto>();
		for(Comment comment:comments){
			User user=userMapper.selectUserById(comment.getCommentator_id());
			//System.out.println(user.getAvatar_url());
			if(user==null){
				return null;
			}
			CommentsDto comment1 = new CommentsDto();
			BeanUtils.copyProperties(comment, comment1);
			comment1.setAvatar_url(user.getAvatar_url());
			comment1.setCommentator(user.getName());
			commentsDto.add(comment1);
		}
		return commentsDto;
	}
	@Autowired
	private CommentMapper commentMapper;
	public void inSecComment_count(int comment_id) {
		// TODO Auto-generated method stub
		commentMapper.inSecCommentCount(comment_id);
	}
	

}
