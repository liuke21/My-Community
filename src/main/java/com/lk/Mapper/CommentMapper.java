package com.lk.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lk.model.Comment;

@Mapper
public interface CommentMapper {
	
	 @Insert("insert into comment (question_id,commentator_id,content,gmt_create,gmt_motifid,type) values (#{question_id},#{commentator_id},#{content},#{gmt_create},#{gmt_motifid},#{type})")
	 void insert(Comment comment);
	 
	 @Insert("insert into comment (question_id,commentator_id,content,gmt_create,gmt_motifid,type,comment_id) values (#{question_id},#{commentator_id},#{content},#{gmt_create},#{gmt_motifid},#{type},#{comment_id})")
	 void insertsec(Comment comment);
	 
	 @Select("select * from comment where question_id=#{question_id} and type=1 order by gmt_create desc")
	 List<Comment> selectComment(@Param(value="question_id") int question_id);
	 
	 @Select("select * from comment where question_id=#{question_id} and type=2 order by gmt_create desc")
	 List<Comment> selectSecComments(@Param(value="question_id") int question_id);
	 
	 @Select("select * from comment where question_id=#{question_id} and comment_id=#{comment_id} order by gmt_create desc")
	 List<Comment> selectSecComment(@Param(value="question_id") int question_id,@Param(value="comment_id") int comment_id);
	 
	 @Update("update comment set coment_count=coment_count+1 where id=#{id}")
	 void inSecCommentCount(@Param(value="id") int id);
}