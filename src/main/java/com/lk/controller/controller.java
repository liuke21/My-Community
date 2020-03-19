package com.lk.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okhttp3.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lk.Mapper.QuestionMapper;
import com.lk.Mapper.userMapper;
import com.lk.dto.PaginationDto;
import com.lk.dto.QUestionDto;
import com.lk.dto.githubDto;
import com.lk.dto.user;
import com.lk.githubProvider.githubprovider;
import com.lk.model.Question;
import com.lk.model.User;
import com.lk.service.QUestionService;

@Controller
public class controller {
	@Autowired
	private githubprovider gp;
	@Autowired
	private userMapper usermapper;
	
	@Autowired
	QuestionMapper questionMapper;
	
	@Autowired
	QUestionService questionService;
	
	@SuppressWarnings("null")
	@RequestMapping("/")
	public String index(Model model
			,@RequestParam(name="page",defaultValue="1") Integer page
//			,@RequestParam(name="page",defaultValue="1") Integer page
			){
		//获取question列表,并进行分页
		Integer size=10;
		Integer offSet = size*(page-1);
		List<Question> questions=questionMapper.selectQuestion(offSet,size);
		if(questions==null&&questions.size()==0){
			return "error";
		}
		List<QUestionDto> questionDtos=questionService.changQuestion(questions);
		/*for(QUestionDto questionDto:questionDtos){
			System.out.println(questionDto.getId());
		}*/
		PaginationDto pagePaginationDto=new PaginationDto();
		pagePaginationDto.setqUestionDtos(questionDtos);
		Integer totalCount= (Integer) questionMapper.selectQuestionCount();
		pagePaginationDto.setPagination(totalCount, page, size);
		model.addAttribute("pagePaginationDto", pagePaginationDto);
		model.addAttribute("totalCount", totalCount);
		
		return "hello";
	}
	
	@Autowired
	private userMapper userMapper;
	
	@RequestMapping("/callback")
	public String callback(@RequestParam(name="code") String code,HttpServletRequest request,HttpServletResponse response){
		githubDto gd = new githubDto();
		gd.setClient_id("55638affe2d21bafbeee7b8708a705d1ea2985a52e64eb4958d625874af2919f");
		gd.setClient_secret("5695961ddf2a902d2fa83743ae2c31c2a3e41e851ed10bca759c94e25ca7c10f");
		gd.setCode(code);
		gd.setGrant_type("authorization_code");
		gd.setRedirect_uri("http://192.168.1.101:8080/callback");
		String accesstoken=gp.accessToken(gd);
		//System.out.println(accesstoken);
		user us = gp.getUser(accesstoken);
		if(us!=null){
			//System.out.println(us.getAvatar_url());
			User user=new User();
			user.setCount_id(us.getId());
			user.setName(us.getName());
			user.setAvatar_url(us.getAvatar_url());
			String token = UUID.randomUUID().toString();
			user.setToken(token);
			if(userMapper.seUserByAutor(user.getName())!=null){
				usermapper.addUser(token,user.getName());
				response.addCookie(new Cookie("token", token));
			}else{
				usermapper.insertUser(user);
				response.addCookie(new Cookie("token", token));
			}
			return "redirect:/";
		}
		return "redirect:/";
	}
	/**
	 * 退出登录
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		//清除session
		request.getSession().removeAttribute("user");
		//清除cookie
		Cookie cookie = new Cookie("token", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/";
	}
	
	/**
	 * 搜索问题
	 */
	@RequestMapping("/search")
	public String search(@RequestParam(name="searchQuestion") String searchQuestion,Model model,@RequestParam(name="page",defaultValue="1") Integer page){
		Integer size=5;
		Integer offSet = (page-1);
		List<Question> questions=questionMapper.selectQuestionBySearch(offSet,size,searchQuestion);
		List<QUestionDto> questionDtos=questionService.changQuestion(questions);
		PaginationDto pagePaginationDto=new PaginationDto();
		pagePaginationDto.setqUestionDtos(questionDtos);
		Integer totalCount= (Integer) questionMapper.selectQuestionCount();
		pagePaginationDto.setPagination(totalCount, page, size);
		model.addAttribute("pagePaginationDto", pagePaginationDto);
		model.addAttribute("totalCount", totalCount);
		
		return "hello";
	}
}
