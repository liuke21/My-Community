package com.lk.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;

import com.lk.model.Question;

@Mapper
public interface QuestionMapper {
	@Insert("insert into question (title,description,tag,autor,gmt_create,gmt_motified,view_count,like_count,comment_count) "
						+ "values(#{title},#{description},#{tag},#{autor},#{gmt_create},#{gmt_motified},#{view_count},#{like_count},#{comment_count})")
	void addQuestion(Question question);
	
	@Select("Select * from question order by gmt_motified desc limit #{offSet},#{size}")
	List<Question> selectQuestion(@Param(value="offSet") Integer offSet,@Param(value="size") Integer size);
	
	@Select("Select * from question where title like CONCAT('%', #{searchQuestion}, '%') limit #{offSet},#{size}")
	List<Question> selectQuestionBySearch(@Param(value="offSet") Integer offSet,
										  @Param(value="size") Integer size,
										  @Param(value="searchQuestion") String searchQuestion);

	
	@Select("Select count(1) from question")
	int selectQuestionCount();
	
	@Select("Select * from question where id=#{id}")
	Question selectQuestionById(@Param(value="id") int id);
	
	@Update("update question set view_count=#{viewCount} where id=#{id}")
	void updateViewCount(@Param(value="viewCount")long viewCount,@Param(value="id")int id);
	
	@Update("update question set comment_count=#{comment_count} where id=#{question_id}")
	void updateCommentCount(@Param(value="question_id") int question_id,@Param(value="comment_count") long comment_count);
	
	@Update("update question set like_count=#{like_count} where id=#{id}")
	void updateLikeCount(@Param(value="id") int id, @Param(value="like_count") Long like_count);
	
	@Update("update question set title=#{title},description=#{description},tag=#{tag},gmt_create=#{gmt_create},gmt_motified=#{gmt_motified} where id=#{id}")
	void editQuestion(Question question);
	
	
}