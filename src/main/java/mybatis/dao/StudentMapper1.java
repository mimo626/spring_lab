package mybatis.dao;

import com.example.springlab.domain.StudentDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper1 {
//    전체 학생 리스트를 읽어서 리턴하는 메서드 - listAll()
    @Select("select * from student")
    public List<StudentDTO> listAll();
//    점수가 높은 순으로 학생 리스트를 읽어서 리턴하는 메서드 - listOrderByScoreDesc()
    @Select("select * from student order by score desc")
    public List<StudentDTO> listOrderByScoreDesc();
//    점수가 70 이상인 학생 리스트를 읽어서 리턴하는 메서드 - listByScoreGreaterEqualThan70()
    @Select("select * from student where score >= 70")
    public List<StudentDTO> listByScoreGreaterEqualThan70();
//    이름에 ‘짱’이 들어간 학생 리스트를 읽어서 리턴하는 메서드 - listByContainName()
    @Select("select * from student where name like '%짱%'")
    public List<StudentDTO> listByContainName();
//    학생의 이름을 입력받고 이 학생의 점수를 리턴하는 메서드 - getScore(String name)
    @Select("select score from student where name = #{name}")
    public int getScore(String name);
//    전체 학생 점수의 평균을 리턴하는 메서드 - getScoreAvg()
    @Select("select avg(score) from student")
    public int getScoreAvg();
//    학생 정보를 입력 메서드 - insertStudent(StudentDTO dto)
    @Insert("insert into student values (#{name}, #{score})")
    public int insert(StudentDTO studentDTO);
//    학생 정보를 삭제 메서드 - delete(String name)
    @Delete("delete from student where name = #{name}")
    public int delete(String name);
}
