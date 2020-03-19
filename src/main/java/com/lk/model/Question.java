package com.lk.model;

public class Question {
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
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", description="
				+ description + ", tag=" + tag + ", autor=" + autor
				+ ", gmt_create=" + gmt_create + ", gmt_motified="
				+ gmt_motified + ", view_count=" + view_count + ", like_count="
				+ like_count + ", comment_count=" + comment_count + "]";
	}
}
