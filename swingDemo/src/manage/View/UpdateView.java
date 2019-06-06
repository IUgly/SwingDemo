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

import manage.Model.Course;
import manage.Util.Constants;
import manage.enums.Singleton;


/**
 * 模块说明： 更新课程信息
 * 
 */
public class UpdateView extends JFrame {

	private static final long serialVersionUID = 5292738820127102731L;
	private JPanel jPanelCenter, jPanelSouth;
	private JButton updateButton, exitButton;
	private JTextField name, sno,score;

	public UpdateView() {
		init();
	}

	private void init() {
		setTitle(Constants.UPDATEVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(9, 2));
		jPanelCenter.add(new JLabel(Constants.COURSE_NAME));
		name = new JTextField();
		jPanelCenter.add(name);
		jPanelCenter.add(new JLabel(Constants.COURSE_NO));
		sno = new JTextField();
		jPanelCenter.add(sno);
		jPanelCenter.add(new JLabel(Constants.COURSE_SCORE));
		score = new JTextField();
		jPanelCenter.add(score);
		score.addKeyListener(new Updateistener());
		jPanelCenter.add(score);
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		updateButton = new JButton(Constants.UPDATEVIEW_UPDATEBUTTON);
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if (check()) {
					Course course = new Course();
					buildStudent(course);

					try {
						Singleton.INSTANCE.getCourseDao().update(course);

						setEmpty();
						if (govern.currPageNum < 0 || govern.currPageNum > 10) {
							govern.currPageNum = 1;
						}
						String[][] result = Singleton.INSTANCE.getCourseDao().select(govern.currPageNum);
						govern.initJTable(govern.table, result);
						JOptionPane.showMessageDialog(null,
								"修改成功！", "提示消息", JOptionPane.WARNING_MESSAGE);
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		jPanelSouth.add(updateButton);
		exitButton = new JButton(Constants.EXITBUTTON);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jPanelSouth.add(exitButton);

		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(470, 200, 400, 270);
		setResizable(false);
		setVisible(true);
	}

	private boolean check() {
		boolean result = false;
		if ("".equals(name.getText()) || "".equals(sno.getText()) || "".equals(score.getText())) {
			JOptionPane.showMessageDialog(null, "请完整的输入要修改的课程信息！", "提示消息", JOptionPane.WARNING_MESSAGE);
			return result;
		} else {
			result = true;
		}
		return result;
	}

	private void buildStudent(Course course) {
		course.setId(Integer.parseInt(sno.getText()));
		course.setCourseName(name.getText());
		course.setScore(Double.parseDouble(score.getText()));
	}

	private class Updateistener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (check()) {
					Course course = new Course();
					buildStudent(course);
					try {
						Singleton.INSTANCE.getCourseDao().update(course);
						setEmpty();
						if (govern.currPageNum < 0 || govern.currPageNum > 10) {
							govern.currPageNum = 1;
						}
						String[][] result = Singleton.INSTANCE.getCourseDao().select(govern.currPageNum);
						govern.initJTable(govern.table, result);
						JOptionPane.showMessageDialog(null,
								"修改成功！", "提示消息", JOptionPane.WARNING_MESSAGE);
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "修改的课程信息不存在，请重新输入信息！",
							"提示消息", JOptionPane.WARNING_MESSAGE);
					setEmpty();
				}
			
			}
		}
	}
	
	private void setEmpty() {
		name.setText("");
		sno.setText(name.getText());
		score.setText(score.getText());
	}
}
