package com.lk.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lk.Mapper.QuestionMapper;
import com.lk.Mapper.userMapper;
import com.lk.model.Question;
import com.lk.model.User;
@Controller
public class publicController {
	
	@RequestMapping("/public")
	public String pub(){
		return "public";
	}
	
	@Autowired
	QuestionMapper questionMapper;
	
	@Autowired
	userMapper usermapper;
	
	@RequestMapping("/publicArticle")
	public String pubArticle(@RequestParam(name="title") String title,
							 @RequestParam(name="description") String description,
							 @RequestParam(name="tag") String tag,
							 @RequestParam(name="question-id") int question_id,
							 HttpServletRequest request){
		if(title==null||description==null||tag==null){	
			return "";
		}else {
			User user=(User)request.getSession().getAttribute("user");
			Question question=new Question();
			question.setAutor(user.getName());
			question.setTitle(title);
			question.setDescription(description);
			question.setTag(tag);
			question.setGmt_create(System.currentTimeMillis());
			question.setGmt_motified(question.getGmt_create());
			if(question_id==0){
				questionMapper.addQuestion(question);
			}else{
				question.setId(question_id);
				questionMapper.editQuestion(question);
			}
			
			return "redirect:/";
		}
		
	}
	//修改问题
	@RequestMapping("/public/{id}")
	 public String edit(@PathVariable(name="id") int id,Model model){
		 Question question=questionMapper.selectQuestionById(id);
		 model.addAttribute("question", question);
		 return "public";
	 }
}
