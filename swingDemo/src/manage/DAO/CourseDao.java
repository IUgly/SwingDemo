package manage.DAO;

import manage.Model.Course;
import manage.sortUtils.MyCollection;
import manage.sortUtils.MyCollections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author kuangjunlin
 */
public class CourseDao {
    /**
     * 声明 连接对象，sql任务执行接口对象，结果集合对象
     */
    Connection con;
    Statement statement;
    ResultSet rs;

    //字段编号9,表示表格里面的列数
    private final int fieldNum = 9;
    //每页展示的个数
    private final int showNum = 6;

    public Connection getCon() {
        return con;
    }
    public Statement getStatement() {
        return statement;
    }
    public ResultSet getRs() {
        return rs;
    }


    public CourseDao(Connection con) {
        this.con = con;
        try {
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[][] selectByName(String param) throws SQLException {
        String sql = "select * from course where course_name like \'%" + param + "%\'";

        System.out.println(sql);
        rs = statement.executeQuery(sql);
        List<Course> courseList = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("course_name");
            int score = rs.getInt("score");

            Course course = new Course.Builder(name).score(score).id(id).build();
            courseList.add(course);
        }
        return buildResult(courseList);
    }


    public void insert(Course course) throws SQLException {
        String sql = "insert into course(course_name, score) values(\'"+course.getCourseName()+"\',"+
                course.getScore() + ")";
        statement.addBatch(sql);
        statement.execute(sql);
    }

    public void update(Course course) throws SQLException {
        String sql = "update course set course_name = \'"+ course.getCourseName() + "\',"
                + "score = " + course.getScore()
                + " Where id = " + course.getId();
        statement.addBatch(sql);
        statement.execute(sql);
    }

    public void delete(Course course) throws SQLException {
        String sql = "delete from course where id = " + course.getId()
                +" And course_name = \'" + course.getCourseName() + "\'";
        System.out.println(sql);
        statement.addBatch(sql);
        statement.execute(sql);
    }

    public String[][] select(int page) throws SQLException {
        String sql = "select id, course_name, score from course ";
        if (page > 0){
            int start = (page - 1 ) * 6;
            int end = page * 6;
            sql +=  "limit "+ start + "," + end;
        }
        rs = statement.executeQuery(sql);
        List<Course> courseList = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("course_name");
            int score = rs.getInt("score");

            Course course = new Course.Builder(name).score(score).id(id).build();
            courseList.add(course);
        }
        return buildResult(courseList);
    }

    /**
     * 将list中记录添加到二维数组中，
     * @param courseList 查询到的所有课程
     * @return  组装成二维数组
     */
    private String[][] buildResult(List<Course> courseList) {
        MyCollections.sort(courseList);
        String[][] result = new String[courseList.size()][4];
        for (int k = 0; k < courseList.size(); k++) {
            Course course = courseList.get(k);
            String idStr = String.valueOf(course.getId());
            result[k][0] = String.valueOf(idStr);
            result[k][1] = course.getCourseName();
            result[k][2] = String.valueOf(course.getScore());
            result[k][3] = String.valueOf(k+1);
        }
        return result;
    }
}
