package manage;

import manage.pojo.Course;
import utils.MyCollections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author kuangjunlin
 */
public class Main {
    public static void main(String[] args) {
        Course course1 = new Course
                .Builder("计算机组织与机构")
                .score(90.5)
                .build();

        Course course2 = new Course("数据结构", 70.5);
        Course course3 = new Course("操作系统", 80.5);

        List<Course> courseList = Arrays.asList(course1, course2, course3);
        System.out.println("before:"+courseList.toString());
//        Collections.sort(courseList);
        MyCollections.sort(courseList);
        System.out.println("after:"+courseList.toString());
    }
}
