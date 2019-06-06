
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
import manage.enums.Singleton;

import javax.swing.SwingConstants;


/**
 * 模块说明： 删除课程
 * 
 */
public class DeleteView extends JFrame {

	private static final long serialVersionUID = -7668153283910203959L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton deleteButton, exitButton;
	private JTextField name, no;

	public DeleteView() {
		init();
	}

	private void init() {
		setTitle(Constants.DELETEVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(3, 2));
		JLabel label = new JLabel("课程名:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		jPanelCenter.add(label);
		name = new JTextField();
		name.addKeyListener( new DeletListener());
		jPanelCenter.add(name);
		JLabel label_1 = new JLabel("课程id:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		jPanelCenter.add(label_1);
		no = new JTextField();
		no.addKeyListener( new DeletListener());
		jPanelCenter.add(no);

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		deleteButton = new JButton(Constants.DELETEVIEW_DELETEBUTTON);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					Course course = new Course();
					buildStudent(course);

					try {
						new CourseDao(JDBCUtil.getConnection()).delete(course);
						setEmpty();

						String[][] result = Singleton.INSTANCE.getCourseDao().select(1);
						govern.initJTable(govern.table, result);
						JOptionPane.showMessageDialog(null, "删除成功！", "提示消息", JOptionPane.WARNING_MESSAGE);
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		jPanelSouth.add(deleteButton);
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
		setBounds(470, 250, 448, 190);
		setVisible(true);
	}
	
	private class DeletListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {

				if (check()) {
					Course course = new Course();
					buildStudent(course);
					try {
						Singleton.INSTANCE.getCourseDao().delete(course);

						setEmpty();
						if (govern.currPageNum < 0 || govern.currPageNum > 10) {
							govern.currPageNum = 1;
						}
						String[][] result = Singleton.INSTANCE.getCourseDao().select(govern.currPageNum);
						govern.initJTable(govern.table, result);
						JOptionPane.showMessageDialog(null, "删除成功！", "提示消息", JOptionPane.WARNING_MESSAGE);
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
		if ("".equals(name.getText()) && "".equals(no.getText())) {
			JOptionPane.showMessageDialog(null, "请至少输入一个数据！", "提示消息", JOptionPane.WARNING_MESSAGE);
			return result;
		} else {
			result = true;
		}
		return result;
	}

	private void buildStudent(Course course) {
		course.setCourseName(name.getText());
		course.setId(Integer.parseInt(no.getText()));
	}

	private void setEmpty() {
		name.setText("");
		no.setText("");
	}

}
