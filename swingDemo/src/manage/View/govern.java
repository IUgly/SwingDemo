package manage.View;

import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import manage.DAO.CourseDao;
import manage.Util.Constants;
import manage.Util.JDBCUtil;


public class govern extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private final int maxPageNum = 10;
	private JLabel currPageNumJLabel;
	private DefaultTableModel myTableModel;
	private JButton jButtonFirst, jButtonAdd, jButtonDelete;
	
	//当前页数
	public static int currPageNum = 1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					govern frame = new govern();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//JScrollPane 
	private JScrollPane scrollPanel = new JScrollPane();
	//表格表头   
	private static String[] tableHead = {"id","课程名","分数","排序号码"};
	public static JTable table;
	private JTextField condition;
	/**
	 * Create the frame.
	 */
	public govern() throws SQLException {
		setResizable(false);
		setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 22));
		setTitle("课程管理	（请在可以连接外网的网络环境下测试）");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(0, 0, 698, 196);
		contentPane.add(scrollPane);
		
		
		// init jTable
//		String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).list(currPageNum);
		CourseDao courseDao = new CourseDao(JDBCUtil.getConnection());
		String[][] result = courseDao.select(1);
		myTableModel = new DefaultTableModel(result, tableHead);
		table = new JTable(myTableModel);
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, cr);
		initJTable(table, result);
		scrollPane.setViewportView(table);
		
		condition = new JTextField();
		condition.setBounds(318, 265, 136, 27);
		condition.addKeyListener(new FindListener());
		contentPane.add(condition);
		condition.setColumns(10);
		
		JLabel label_1 = new JLabel("请输入要查找的课程名（模糊查询）:");
		label_1.setBounds(49, 266, 254, 24);
		contentPane.add(label_1);
		
		JButton chaxun = new JButton("查询");
		chaxun.setBounds(515, 264, 123, 29);
		chaxun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					find();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		chaxun.addKeyListener(new FindListener());
		contentPane.add(chaxun);
		
		JButton button = new JButton("查看所有（排序）");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPageNum = 1;
				//从数据库查询所有数据
				CourseDao courseDao =new CourseDao(JDBCUtil.getConnection());

				String[][] result = new String[0][];
				try {
					result = courseDao.select(0);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				//显示到table中
				initJTable(table, result);
				currPageNumJLabel.setText("第 " + currPageNum
						+ "/10 页");
			}
		});
		button.setBounds(525, 326, 113, 44);
		contentPane.add(button);
		
		jButtonAdd = new JButton("增加信息");
		jButtonAdd.setBounds(40, 326, 113, 44);
		jButtonAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "请将信息填写完整！",
						"注意", JOptionPane.WARNING_MESSAGE);
				// TODO Auto-generated method stub
				new AddView();
			}
		});
		contentPane.add(jButtonAdd);
		
		JButton btnNewButton_1 = new JButton("修改信息");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"请填写正确的课程id", "注意", JOptionPane.WARNING_MESSAGE);
				new UpdateView();
			}
		});
		btnNewButton_1.setBounds(353, 326, 123, 44);
		contentPane.add(btnNewButton_1);
		
		jButtonDelete = new JButton("删除信息");
		jButtonDelete.setBounds(180, 326, 123, 44);
		jButtonDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DeleteView(); 
			}
		});
		contentPane.add(jButtonDelete);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 197, 698, 27);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1,5));
		
		jButtonFirst = new JButton("首页");
		
		//首页按钮的点击事件
		jButtonFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				currPageNum = 1;
				String[][] result = new String[0][];
				try {
					result = new CourseDao(JDBCUtil.getConnection()).select(currPageNum);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				initJTable(table, result);
				currPageNumJLabel.setText("第 " + currPageNum
						+ "页");
			
			}
		});
		
		
		panel.add(jButtonFirst);
		
		JButton jButtonPre = new JButton("上一页");
		jButtonPre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				currPageNum--;
				if (currPageNum <= 0) {
					currPageNum = 1;
				}

				String[][] result = new String[0][];
				try {
					result = new CourseDao(JDBCUtil.getConnection()).select(currPageNum);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				initJTable(table, result);
				currPageNumJLabel.setText(Constants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum
						+ Constants.MAINVIEW_PAGENUM_JLABEL_YE);
			
			}
		});
		panel.add(jButtonPre);
		
		currPageNumJLabel = new JLabel("第 1/10 页");
		currPageNumJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(currPageNumJLabel);
		
		JButton jButtonNext = new JButton("下一页");
		jButtonNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				

				currPageNum++;
				if (currPageNum > maxPageNum) {
					currPageNum = maxPageNum;
				}
				String[][] result = new String[0][];
				try {
					result = new CourseDao(JDBCUtil.getConnection()).select(currPageNum);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				initJTable(table, result);
				currPageNumJLabel.setText(Constants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum
						+ Constants.MAINVIEW_PAGENUM_JLABEL_YE);
			
			}
		});
		panel.add(jButtonNext);
		
		JButton jButtonLast = new JButton("末页");
		jButtonLast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				currPageNum = maxPageNum;
				String[][] result = new String[0][];
				try {
					result = new CourseDao(JDBCUtil.getConnection()).select(currPageNum);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				initJTable(table, result);
				currPageNumJLabel.setText(Constants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum
						+ Constants.MAINVIEW_PAGENUM_JLABEL_YE);
			
			}
		});
		panel.add(jButtonLast);
	
		
	
		
	}
	
	
	//初始化表格
	public static  void initJTable(JTable jTable, String[][] result) {
		((DefaultTableModel) jTable.getModel()).setDataVector(result, tableHead);
		//为表格的每个列设置宽度，因为有的信息看起来长度不一样，为了显示完整必须都要设置宽度
		jTable.setRowHeight(20);
		TableColumn firsetColumn = jTable.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(100);
		firsetColumn.setMaxWidth(100);
		firsetColumn.setMinWidth(30);
		TableColumn secondColumn = jTable.getColumnModel().getColumn(1);
		secondColumn.setPreferredWidth(100);
		secondColumn.setMaxWidth(200);
		secondColumn.setMinWidth(60);
		TableColumn thirdColumn = jTable.getColumnModel().getColumn(2);
		thirdColumn.setPreferredWidth(100);
		thirdColumn.setMaxWidth(100);
		thirdColumn.setMinWidth(90);
		TableColumn fourthColumn = jTable.getColumnModel().getColumn(3);
		fourthColumn.setPreferredWidth(100);
		fourthColumn.setMaxWidth(100);
		fourthColumn.setMinWidth(30);
	}
	
	
	private class FindListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				try {
					find();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	
     private void find() throws SQLException {
		currPageNum = 0;
		String param = condition.getText();
		if ("".equals(param) || param == null) {
			JOptionPane.showMessageDialog(null, "请输入要查询的数据！",
					"提示消息", JOptionPane.WARNING_MESSAGE);
			initJTable(govern.table, null);
			currPageNumJLabel.setText(Constants.MAINVIEW_FIND_JLABEL);
			return;
		}
		String[][] result = new CourseDao(JDBCUtil.getConnection()).selectByName(param);
		//判断二维数组是否为空
		if(result==null||result.length==0||(result.length==1&&result[0].length==0)) {
			
			JOptionPane.showMessageDialog(null, "没有查询相关课程信息，请重新输入！", "提示消息", JOptionPane.WARNING_MESSAGE);
			condition.setText("");
		}else {
			JOptionPane.showMessageDialog(null, "查询成功！", "提示消息", JOptionPane.WARNING_MESSAGE);
			condition.setText("");
			initJTable(govern.table, result);
			currPageNumJLabel.setText(Constants.MAINVIEW_FIND_JLABEL);
		}
		
	}

}
