package jinhe.lt.xml.jdom;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 通过JDOM来操作XML以实现对数据的管理
 */
public class BookGui {
	public static void main(String[] args) {
		BookFrame frame = new BookFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/**
 * BookFrame是一个GUI程序
 */
@SuppressWarnings("unchecked")
class BookFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 400;

	public static final int HEIGHT = 400;

	public static final String TITLE = "Book Library";

	private int currentIndex = -1;// 当前的索引

	/**
	 * 以下是GUI的一些组件
	 */
	private Container contentPane;

	private JComboBox combo;

	private JTextField id;

	private JTextField title;

	private JTextField length;

	private JTextArea authors;

	private JButton saveButton;

	private JButton updateButton;

	private JButton createButton;

	private JButton deleteButton;

	private JLabel message = new JLabel("");

	private JPanel panelcombo, paneldetails;

	// 创建一个业务对象
	private BookManager manager = new BookManagerUseJDOM();

	private boolean selectionEnabled = true;

	/**
	 * 构造方法，用于初始化界面
	 */
	public BookFrame() {
		setTitle(TITLE);
		setSize(WIDTH, HEIGHT);
		contentPane = getContentPane();

		// Create a combobox with all Book titles
		// =====================================
		panelcombo = new JPanel();
		contentPane.add(panelcombo, BorderLayout.NORTH);
		panelcombo.setBorder(BorderFactory.createEtchedBorder());

		Box topbox = Box.createVerticalBox();
		panelcombo.add(topbox);
		JLabel prompt = new JLabel("Select a Book from the list below:");
		prompt.setAlignmentX(Component.LEFT_ALIGNMENT);
		topbox.add(prompt);

		Vector items = getBookList();
		combo = new JComboBox(items);
		combo.setMaximumRowCount(6);
		combo.setAlignmentX(Component.LEFT_ALIGNMENT);
		topbox.add(combo);

		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (!selectionEnabled)
					return;
				message.setText("");
				// Get item selected by user
				int i = combo.getSelectedIndex();
				System.out.println("Index selected:" + i);
				if (i != -1) {
					getBookDetail(i);
				}
			}
		});

		topbox.add(Box.createVerticalStrut(10));

		// Create the detail fields
		// ========================
		paneldetails = new JPanel();
		contentPane.add(paneldetails, BorderLayout.CENTER);
		paneldetails.setBorder(BorderFactory.createEtchedBorder());

		Box box = Box.createVerticalBox();
		paneldetails.add(box);

		box.add(Box.createVerticalStrut(6));

		Box box1 = Box.createHorizontalBox();
		box1.setAlignmentX(Component.LEFT_ALIGNMENT);

		box1.add(new JLabel("Id: "));
		id = new JTextField("", 2);
		box1.add(id);
		box.add(box1);

		Box box2 = Box.createHorizontalBox();
		box2.setAlignmentX(Component.LEFT_ALIGNMENT);

		box2.add(new JLabel("Title: "));
		title = new JTextField("", 30);
		box2.add(title);
		box.add(box2);

		Box box3 = Box.createHorizontalBox();
		box3.setAlignmentX(Component.LEFT_ALIGNMENT);

		box3.add(new JLabel("Length: "));
		length = new JTextField("", 3);
		box3.add(length);
		box.add(box3);

		Box box4 = Box.createHorizontalBox();
		box4.setAlignmentX(Component.LEFT_ALIGNMENT);

		box4.add(new JLabel("authors: "));
		authors = new JTextArea("", 3, 30);
		authors.setBorder(BorderFactory.createEtchedBorder());
		box4.add(authors);
		box.add(box4);

		// Create the buttons
		// ==================
		Box box5 = Box.createHorizontalBox();
		box5.setAlignmentX(Component.LEFT_ALIGNMENT);
		box.add(Box.createVerticalStrut(10));
		box.add(box5);

		// "Update" button
		// ===============
		updateButton = new JButton("Update");
		box5.add(updateButton);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				message.setText("");
				if (currentIndex != -1)
					updateBook();
				else
					message.setText("Select a Book first");
			}
		});

		// "Create" button
		// ===============
		createButton = new JButton("Create");
		box5.add(createButton);
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				message.setText("");
				createBook();
			}
		});

		// "Delete" button
		// ===============
		deleteButton = new JButton("Delete");
		box5.add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				message.setText("");
				if (currentIndex != -1)
					deleteBook();
				else
					message.setText("Select a Book first");
			}
		});

		// "Save" button
		// =============
		saveButton = new JButton("Save");
		saveButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		box5.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				message.setText("");
				save();
			}
		});

		// Add the error message area
		// ==========================
		message.setAlignmentX(Component.LEFT_ALIGNMENT);
		box.add(message);

	}

	/**
	 * Get the list of Books
	 */
	public Vector getBookList() {
		// new
		Vector v = manager.getTitles();
		if (manager.error()) {
			showError();
			return new Vector();
		} else {
			return v;
		}
	}

	/**
	 * Save the list of Books
	 */
	public void save() {
		manager.save();
		if (manager.error()) {
			showError();
		}
	}

	/**
	 * Get details for one Book
	 */
	public void getBookDetail(int i) {
		Book Book = manager.getDetails(i);
		if (manager.error()) {
			showError();
			return;
		} else {
			id.setText(Book.getId());
			title.setText(Book.getTitle());
			length.setText(Book.getLength());
			authors.setText(StringUtil.list2String(Book.getAuthors()));
			currentIndex = i;
		}
	}

	/**
	 * Delete a Book
	 */
	public void deleteBook() {
		manager.delete();
		manager.setIndex(currentIndex);
		if (manager.error()) {
			showError();
		} else {
			id.setText("");
			title.setText("");
			length.setText("");
			authors.setText("");
			selectionEnabled = false;
			combo.removeItemAt(currentIndex);
			// set to first element if list not empty
			if (combo.getItemCount() > 0)
				combo.setSelectedIndex(0);
			selectionEnabled = true;
			currentIndex = -1;
		}
	}

	/**
	 * Update a Book
	 */
	public void updateBook() {
		Book book = new Book();
		book.setId(id.getText());
		book.setTitle(title.getText());
		book.setLength(length.getText());
		book.setAuthors(StringUtil.string2List(authors.getText()));
		manager.setIndex(currentIndex);
		manager.update(book);
		if (!manager.error()) {
			selectionEnabled = false;
			combo.insertItemAt(title.getText(), currentIndex);
			combo.removeItemAt(currentIndex + 1);
			selectionEnabled = true;
		} else {
			showError();
		}
	}

	/**
	 * Create a Book
	 */
	public void createBook() {
		Book book = new Book();
		book.setId(id.getText());
		book.setTitle(title.getText());
		book.setLength(length.getText());
		book.setAuthors(StringUtil.string2List(authors.getText()));
		manager.create(book);
		if (!manager.error()) {
			selectionEnabled = false;
			combo.addItem(title.getText());
			combo.setSelectedIndex(combo.getItemCount() - 1);
			currentIndex = combo.getItemCount() - 1;
			selectionEnabled = true;
		} else {
			showError();
		}
	}

	/**
	 * Display error messages
	 */
	public void showError() {
		String msg = manager.getErrorText();
		message.setText(msg);
		System.out.println(msg);
	}
}
