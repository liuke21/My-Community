package com.lk.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.lk.Mapper.QuestionMapper;
import com.lk.model.Question;

public class QUestionDto {
	int id;
	String title;
	String description;
	String tag;
	String autor;
	long gmt_create;
	long gmt_motified;
	long view_count;
	long like_count;
	long comment_count;
	String avatar_url;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public long getGmt_create() {
		return gmt_create;
	}
	public void setGmt_create(long gmt_create) {
		this.gmt_create = gmt_create;
	}
	public long getGmt_motified() {
		return gmt_motified;
	}
	public void setGmt_motified(long gmt_motified) {
		this.gmt_motified = gmt_motified;
	}
	public long getView_count() {
		return view_count;
	}
	public void setView_count(long view_count) {
		this.view_count = view_count;
	}
	public long getLike_count() {
		return like_count;
	}
	public void setLike_count(long like_count) {
		this.like_count = like_count;
	}
	public long getComment_count() {
		return comment_count;
	}
	public void setComment_count(long comment_count) {
		this.comment_count = comment_count;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	
}
