package com.lk.dto;

public class CommentsDto {
	private int id;
	private int question_id;
	private int commentator_id;
	private String content;
	private long gmt_create;
	private long gmt_motifid;
	private long like_count;
	private long coment_count;
	private int type;
	private String avatar_url;
	private String commentator;
	private int comment_id;
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public int getCommentator_id() {
		return commentator_id;
	}
	public void setCommentator_id(int commentator_id) {
		this.commentator_id = commentator_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getGmt_create() {
		return gmt_create;
	}
	public void setGmt_create(long gmt_create) {
		this.gmt_create = gmt_create;
	}
	public long getGmt_motifid() {
		return gmt_motifid;
	}
	public void setGmt_motifid(long gmt_motifid) {
		this.gmt_motifid = gmt_motifid;
	}
	public long getLike_count() {
		return like_count;
	}
	public void setLike_count(long like_count) {
		this.like_count = like_count;
	}
	public long getComent_count() {
		return coment_count;
	}
	public void setComent_count(long coment_count) {
		this.coment_count = coment_count;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	public String getCommentator() {
		return commentator;
	}
	public void setCommentator(String commentator) {
		this.commentator = commentator;
	}
	
}
