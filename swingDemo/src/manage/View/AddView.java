
package manage.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import manage.DAO.CourseDao;
import manage.Model.Course;
import manage.Util.Constants;

import manage.Util.JDBCUtil;
import javax.swing.SwingConstants;



/**
 * 模块说明： 添加课程
 * 
 */
public class AddView extends JFrame {

	private static final long serialVersionUID = -1984182788841566838L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton addButton, exitButton;
	private JTextField courseName, score;

	public AddView() {
		setResizable(false);
		init();
	}

	private void init() {
		setTitle(Constants.ADDVIEW_TITLE);

		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(9, 2));
		JLabel label = new JLabel("课程名:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		jPanelCenter.add(label);
		courseName = new JTextField();
		jPanelCenter.add(courseName);
		JLabel label_2 = new JLabel("分数:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		jPanelCenter.add(label_2);
		score = new JTextField();

		score.addKeyListener(new AddListener());
		jPanelCenter.add(score);

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		addButton = new JButton("添加");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (check()) {
					Course course = new Course();
					buildStudent(course);

					CourseDao courseDao = new CourseDao(JDBCUtil.getConnection());
					try {
						courseDao.insert(course);
						setEmpty();
						if (govern.currPageNum < 0 || govern.currPageNum > 10) {
							govern.currPageNum = 1;
						}
						String[][] result = new CourseDao(JDBCUtil.getConnection()).select(govern.currPageNum);
						govern.initJTable(govern.table, result);
						JOptionPane.showMessageDialog(null, "添加成功", "提示消息", JOptionPane.WARNING_MESSAGE);
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		jPanelSouth.add(addButton);
		exitButton = new JButton(Constants.EXITBUTTON);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jPanelSouth.add(exitButton);

		getContentPane().add(jPanelCenter, BorderLayout.CENTER);
		getContentPane().add(jPanelSouth, BorderLayout.SOUTH);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(470, 200, 400, 270);
		setVisible(true);
	}
	
	private class AddListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (check()) {
					Course course = new Course();
					buildStudent(course);

					CourseDao courseDao = new CourseDao(JDBCUtil.getConnection());
					boolean isSuccess = false;
					try {
						courseDao.insert(course);
						setEmpty();
						if (govern.currPageNum < 0 || govern.currPageNum > 10) {
							govern.currPageNum = 1;
						}
						String[][] result = new CourseDao(JDBCUtil.getConnection()).select(govern.currPageNum);
						govern.initJTable(govern.table, result);
						JOptionPane.showMessageDialog(null, "添加成功", "提示消息", JOptionPane.WARNING_MESSAGE);
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	private boolean check() {
		boolean result = false;
		if ("".equals(courseName.getText()) || "".equals(score.getText())) {
			JOptionPane.showMessageDialog(null, "请把信息填写完整！", "提示消息", JOptionPane.WARNING_MESSAGE);
			return result;
		} else {
			result = true;
		}
		return result;
	}

	private void buildStudent(Course course) {
		course.setScore(Double.parseDouble(score.getText()));
		course.setCourseName(courseName.getText());
//		course.setId(Integer.parseInt(no.getText()));
	}

	private void setEmpty() {
		courseName.setText("");
//		no.setText("");
		score.setText("");
	}
}
