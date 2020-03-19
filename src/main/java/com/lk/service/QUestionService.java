package com.lk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lk.Mapper.QuestionMapper;
import com.lk.Mapper.userMapper;
import com.lk.dto.QUestionDto;
import com.lk.model.Question;
import com.lk.model.User;

@Service
public class QUestionService {
	@Autowired
	userMapper userMapper;
	public List<QUestionDto> changQuestion(List<Question> questions){
		List<QUestionDto> qUestionDtos = new ArrayList<QUestionDto>();
		for(Question question:questions){
			User user=userMapper.seUserByAutor(question.getAutor());
			//System.out.println(user.getAvatar_url());
			if(user==null){
				return null;
			}
			String avatar_url = user.getAvatar_url();
			QUestionDto qUestionDto = new QUestionDto();
			BeanUtils.copyProperties(question, qUestionDto);
			qUestionDto.setAvatar_url(avatar_url);
			qUestionDtos.add(qUestionDto);
		}
		return qUestionDtos;
	}
	
	@Autowired
	private QuestionMapper questionMapper;
	//增加阅读数
	public void inViewCount(Question question) {
		long viewCount = question.getView_count()+1;
		question.setView_count(viewCount);
		questionMapper.updateViewCount(viewCount,question.getId());
	}
	
	//增加问题喜欢次数
	public void inLikeCount(int id, Long like_count) {
		questionMapper.updateLikeCount(id, like_count);
	}
}
