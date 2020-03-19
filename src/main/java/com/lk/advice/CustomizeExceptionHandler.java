package com.lk.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomizeExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	ModelAndView handle(HttpServletRequest request,
			Model model,Throwable ex){
		HttpStatus status = getStatus(request);
		System.out.println(status);
		model.addAttribute("message","服务器异常，请稍后再试");
		return new ModelAndView("error");
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode=(Integer) request.getAttribute("javax.error.status_code");
		if(statusCode == null){
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}
}
