package manage.enums;

import manage.DAO.CourseDao;
import manage.Util.JDBCUtil;

/**
 * @author kuangjunlin
 */
public enum Singleton {

    /**
     * 单例模式
     * restTemplate
     * gson
     */
    INSTANCE;
    private CourseDao courseDao;

    Singleton(){
        this.courseDao = new CourseDao(JDBCUtil.getConnection());
    }

    public CourseDao getCourseDao() {
        return courseDao;
    }
}
