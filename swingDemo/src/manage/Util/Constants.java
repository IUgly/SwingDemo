
package manage.Util;

/**
 * 模块说明： 常量
 * 
 */
public class Constants {
	// jdbc
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/manage";
	public static final String JDBC_USERNAME = "root";
	public static final String JDBC_PASSWORD = "kk123456";
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	// student field
	public static final String COURSE_NAME = "课程名";
	public static final String COURSE_NO = "课程id";
	public static final String COURSE_SCORE = "分数";


	// main view
	public static final String MAINVIEW_TITLE = "课程管理";
	public static final String MAINVIEW_PAGENUM_JLABEL_DI = "第 ";
	public static final String MAINVIEW_PAGENUM_JLABEL_YE = "/10 页";
	public static final String MAINVIEW_FIND_JLABEL = "查询结果";
	public static final String MAINVIEW_FIRST = "首页";
	public static final String MAINVIEW_LAST = "末页";
	public static final String MAINVIEW_PRE = "上一页";
	public static final String MAINVIEW_NEXT = "下一页";
	public static final String PARAM_FIND_CONDITION = "";
	public static final String PARAM_FIND = "查找";
	public static final String PARAM_ADD = "添加";
	public static final String PARAM_DELETE = "删除";
	public static final String PARAM_UPDATE = "更新";

	// add view
	public static final String ADDVIEW_TITLE = "添加课程信息";
	public static final String ADDVIEW_ADDBUTTON = "添加";
	public static final String EXITBUTTON = "退出";

	// delete view
	public static final String DELETEVIEW_TITLE = "删除课程信息";
	public static final String DELETEVIEW_DELETEBUTTON = "删除";

	// update view
	public static final String UPDATEVIEW_TITLE = "修改课程信息";
	public static final String UPDATEVIEW_UPDATEBUTTON = "更新";

}
