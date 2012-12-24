package visual;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar.Separator;

import control.Service;
import entity.Book;
import entity.SearchResult;
import entity.SimpleBook;

/**
 * This class displays the main frame of the program and deals with user inputs.
 * @author looya
 */
public class Douban extends javax.swing.JFrame implements Runnable{
	
	public Douban()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("DOUBAN API");
		setSize(946, 649);
		this.setResizable(false);
		init();
		setVisible(true);
	}

    /**
     * initialize variables, set layout and position of the main panels
     */
    private void init() {
    	panel_message = new JPanel();
    	
    	//initialize variable for search panel
		panel_search = new JPanel();
		textfield_search = new javax.swing.JTextField(20);
		button_search = new JButton("Search");
		radio_title = new JRadioButton("Title");
		radio_isbn = new JRadioButton("ISBN");
		radio_tag = new JRadioButton("Tag");
		radio_id = new JRadioButton("ID");
		ButtonGroup group_type = new ButtonGroup();
		group_type.add(radio_title);
		group_type.add(radio_isbn);
		group_type.add(radio_tag);
		group_type.add(radio_id);
		radio_title.setSelected(true);
		textfield_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
		});
		
		//initialize variable for detail panel
        panel_details = new JPanel();
        spane_detailwrap = new JScrollPane(panel_details);
        //spane_detailwrap.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        int defaultTextFieldWidth = 15;
    	textfield_title = new JTextField(defaultTextFieldWidth);
    	textfield_rating = new JTextField(defaultTextFieldWidth);
    	textfield_subtitle = new JTextField(defaultTextFieldWidth);
    	textfield_alt = new JTextField(defaultTextFieldWidth);
    	textfield_author = new JTextField(defaultTextFieldWidth);
    	textfield_translator = new JTextField(defaultTextFieldWidth);
    	textfield_publisher = new JTextField(defaultTextFieldWidth);
    	textfield_pubdate = new JTextField(defaultTextFieldWidth);
    	textfield_price = new JTextField(defaultTextFieldWidth);
    	textfield_pages = new JTextField(defaultTextFieldWidth);
    	textfield_binding = new JTextField(defaultTextFieldWidth);
    	textfield_isbn = new JTextField(defaultTextFieldWidth);
    	textarea_summary = new JTextArea();
    	textarea_summary.setLineWrap(true);
    	textarea_authorintro = new JTextArea();
    	textarea_authorintro.setLineWrap(true);
    	textfield_tags = new JTextArea();
    	textfield_tags.setLineWrap(true);
    	
    	textfield_title.setEditable(false);
    	textfield_rating.setEditable(false);
    	textfield_subtitle.setEditable(false);
    	textfield_alt.setEditable(false);
    	textfield_author.setEditable(false);
    	textfield_translator.setEditable(false);
    	textfield_publisher.setEditable(false);
    	textfield_pubdate.setEditable(false);
    	textfield_price.setEditable(false);
    	textfield_pages.setEditable(false);
    	textfield_binding.setEditable(false);
    	textfield_isbn.setEditable(false);
    	textarea_summary.setEditable(false);
    	textarea_authorintro.setEditable(false);
    	textfield_tags.setEditable(false);
    	
    	//initialize variable for bookwarp panel
//    	tabpane_bookwarp = new JTabbedPane();
//    	tabpane_bookwarp.addTab("Details", spane_detailwrap);
    	
    	//initialize variable for search result panel
    	panel_searchResult = new JPanel();
    	panel_booklist = new JPanel();
    	spane_booklistwrap = new JScrollPane(panel_booklist);
    	//panel_searchResult.add(panel_srTop, BorderLayout.NORTH);
    	//panel_searchResult.add(spane_booklistwrap);
    	panel_srBut = new JPanel();
    	but_pre = new JButton("Preview");
    	but_pre.setEnabled(false);
    	but_next = new JButton("Next");
    	but_next.setEnabled(false);
		but_pre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changViewActionPerformed(evt);
            }
		});
		but_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changViewActionPerformed(evt);
            }
		});
    	panel_srBut.add(but_pre);
    	panel_srBut.add(but_next);
    	buttons_viewdetail = new ArrayList();
    	
    	//initialize variable for wrap panel
    	panel_wrap = new JPanel();
    	panel_wrap.setLayout(null);
    	//panel_wrap.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(160, 160, 160), 3, true));

        panel_wrap.add(spane_booklistwrap);
        panel_wrap.add(spane_detailwrap);
        panel_wrap.add(panel_srBut);

        spane_booklistwrap.setBorder(javax.swing.BorderFactory.createTitledBorder("Result"));
        spane_detailwrap.setBorder(javax.swing.BorderFactory.createTitledBorder("Detais"));
		setBoundz();
    	
		button_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
		});
    	
		panel_search.add(new JLabel("SEARCH DOUBAN"));
		panel_search.add(textfield_search);
		panel_search.add(radio_title);
		panel_search.add(radio_tag);
		panel_search.add(radio_isbn);
		panel_search.add(radio_id);
		panel_search.add(button_search);
		
		add(panel_search, BorderLayout.NORTH);
		add(panel_wrap);
		add(panel_message, BorderLayout.SOUTH);
		showmsg("Loading Demo...",false);
		//recommandation();
    }
    
    /**
     * Shows a message on the South region of the frame
     * @param msg the message to be displayed
     * @param err true if the message is an error message
     */
    public void showmsg(String msg, boolean err)
    {
    	JLabel message = new JLabel(msg);
    	message.setFont(new java.awt.Font("Tahoma", 1, 11));
    	
    	if(err==true)
    	{
    		message.setForeground(new java.awt.Color(204, 0, 0));
    	}
    	
    	panel_message.removeAll();
    	panel_message.add(message);
    	panel_message.revalidate();
    }
    
    /**
     * set location of the main panels
     */
    public void setBoundz()
    {
    	int northBlank = 40;
    	int validLength = this.getHeight()-northBlank-60;
    	int detailWidth = this.getWidth()-36-200;
    	
        spane_booklistwrap.setBounds(10,2,200,validLength-40);
        spane_detailwrap.setBounds(220,2,detailWidth,validLength);
        panel_srBut.setBounds(10, validLength-40, 200, 40);
    }
    
    /**
     * actions when the search button is clicked or hit enter on the text field
     * @param evt the event that triggered this function
     */
    private void searchActionPerformed(java.awt.event.ActionEvent evt) {
    	showmsg("Searching...", false);
    	

    	
    	if(radio_id.isSelected())
    	{	
	        int id;
	        id = Integer.parseInt(textfield_search.getText());
	        Service service = new Service();
	        Book book = service.getBookByID(id);
	        message = service.errMsg;
	        showdetails(book);
    	}
    	
    	else if(radio_title.isSelected())
    	{
    		key = textfield_search.getText();
    		if(!key.matches("[\\w\\.\\-\\ ]+"))
    		{
    			showmsg("Sorry, search key should only contains English letters and numbers.", true);
    			return;
    		}
    		keyOrTag = true;
    		Service service = new Service();
    		sr = service.searchBookByKeyOrByTag(key, 0, true);
    		message = service.errMsg;
    		showSearchResult();
    	}
    	
    	else if(radio_isbn.isSelected())
    	{	
	        String isbn;
	        isbn = textfield_search.getText();
	        Service service = new Service();
	        Book book = service.getBookByISBN(isbn);
	        message = service.errMsg;
	        showdetails(book);
    	}
    	
    	else if(radio_tag.isSelected())
    	{      	
    		key = textfield_search.getText();
    		if(!key.matches("[\\w\\.\\-\\ ]+"))
    		{
    			showmsg("Sorry, search key should only contains English letters and numbers.", true);
    			return;
    		}
    		keyOrTag = false;
    		Service service = new Service();
    		sr = service.searchBookByKeyOrByTag(key, 0, false);
    		message = service.errMsg;
    		showSearchResult();
    	}
    	
    	else
    	{
    		showmsg("Please specify the type of your search", true);
    	}

    }
    
    /**
     * put a book's detailed description on the panel
     * @param book the book that to be displayed
     */
    public void showdetails(Book book)
    {
        if(book == null)
        {
        	showmsg(message, true);
        	return;
        }
        
    	panel_details.removeAll();
    	panel_details.revalidate();
    	panel_details.setSize(0, 0);
    	spane_detailwrap.revalidate();
    	
        textfield_title.setText(book.getTitle());
        textfield_rating.setText(Double.toString(book.getRating().getAverage())+"  ("+book.getRating().getNumRaters()+" votes)");
        textfield_subtitle.setText(book.getSubtitle());
        textfield_alt.setText(book.getAlt_title());
        textfield_author.setText(book.getAuthorString());
        textfield_translator.setText(book.getTranslatorString());
        textfield_publisher.setText(book.getPublisher());
        textfield_pubdate.setText(book.getPubdate());
        textfield_price.setText(book.getPrice());
        textfield_pages.setText(book.getPages());
        textfield_binding.setText(book.getBinding());
        textfield_isbn.setText(book.getIsbn10()+" / "+book.getIsbn13());
        textarea_summary.setText(book.getSummary());
        textarea_authorintro.setText(book.getAuthor_intro());
        textfield_tags.setText(book.getTagsString());
        
        panel_details.setLayout(new GridBagLayout());
        GridBagConstraints gbdc=new GridBagConstraints();
        //adding image
        gbdc.fill = GridBagConstraints.NONE;
        gbdc.gridx = 0;
        gbdc.gridy = 0;
        gbdc.gridwidth = 4;
        gbdc.ipadx = 658;

        String picAddr = book.getImage_url();
		Image pic = null;
		try {
			pic = ImageIO.read(new URL(picAddr));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		int h = pic.getHeight(null);
        gbdc.ipady = h;
        TagAnime ta = new TagAnime( book.getTags(), pic);
        panel_details.add(ta,gbdc);

        
        gbdc.fill = GridBagConstraints.HORIZONTAL;
        gbdc.ipadx = 0;
        gbdc.ipady = 0;
        //adding col1
        gbdc.weightx = 1;
        gbdc.gridx = 0;
        gbdc.gridy = 1;
        gbdc.gridwidth = 1;
        panel_details.add(new JLabel("Title"),gbdc);
        
        gbdc.gridy = 2;
        panel_details.add(new JLabel("Subtitle"),gbdc);
        
        gbdc.gridy = 3;
        panel_details.add(new JLabel("Author"),gbdc);
        
        gbdc.gridy = 4;
        panel_details.add(new JLabel("Publisher"),gbdc);
        
        gbdc.gridy = 5;
        panel_details.add(new JLabel("Price"),gbdc);
        
        gbdc.gridy = 6;
        panel_details.add(new JLabel("Binding"),gbdc);
        
        gbdc.gridy = 7;
        panel_details.add(new JLabel("Summary"),gbdc);
        
        gbdc.gridy = 8;
        panel_details.add(new JLabel("Author Introduction"),gbdc);
        
        gbdc.gridy = 9;
        panel_details.add(new JLabel("Tags"),gbdc);
        
        //adding col2
        gbdc.weightx = 3;
        gbdc.gridx = 1;
        
        gbdc.gridy = 1;
        panel_details.add(textfield_title,gbdc);
        
        gbdc.gridy = 2;
        panel_details.add(textfield_subtitle,gbdc);
        
        gbdc.gridy = 3;
        panel_details.add(textfield_author,gbdc);
        
        gbdc.gridy = 4;
        panel_details.add(textfield_publisher,gbdc);
        
        gbdc.gridy = 5;
        panel_details.add(textfield_price,gbdc);
        
        gbdc.gridy = 6;
        panel_details.add(textfield_binding,gbdc);
        
        gbdc.gridwidth = 3;
        //gbdc.ipady = 40;
        gbdc.gridy = 7;
        panel_details.add(textarea_summary,gbdc);
        
        gbdc.gridy = 8;
        panel_details.add(textarea_authorintro,gbdc);
        
        gbdc.gridy = 9;
        gbdc.ipady = 0;
        panel_details.add(textfield_tags,gbdc);
        
        //adding col3
        gbdc.gridwidth = 1;
        gbdc.weightx = 1;
        gbdc.gridx = 2;
        
        gbdc.gridy = 1;
        panel_details.add(new JLabel("Rating"),gbdc);
        
        gbdc.gridy = 2;
        panel_details.add(new JLabel("Alt"),gbdc);
        
        gbdc.gridy = 3;
        panel_details.add(new JLabel("Translator"),gbdc);
        
        gbdc.gridy = 4;
        panel_details.add(new JLabel("Publish Date"),gbdc);
        
        gbdc.gridy = 5;
        panel_details.add(new JLabel("Pages"),gbdc);
        
        gbdc.gridy = 6;
        panel_details.add(new JLabel("ISBN"),gbdc);

        //adding col4
        gbdc.weightx = 3;
        gbdc.gridx = 3;
        
        gbdc.gridy = 1;
        panel_details.add(textfield_rating,gbdc);
        
        gbdc.gridy = 2;
        panel_details.add(textfield_alt,gbdc);
        
        gbdc.gridy = 3;
        panel_details.add(textfield_translator,gbdc);
        
        gbdc.gridy = 4;
        panel_details.add(textfield_pubdate,gbdc);
        
        gbdc.gridy = 5;
        panel_details.add(textfield_pages,gbdc);
        
        gbdc.gridy = 6;
        panel_details.add(textfield_isbn,gbdc);
        
        spane_detailwrap.revalidate();
        //move scroll bar to top TBC
        
    	showmsg("Displaying book " + book.getId(), false);
    	
        Thread thread = new Thread(ta);
        thread.start();
    }
    
    /**
     * displays the search result stored in sr
     */
    public void showSearchResult()
    {
        if(sr == null)
        {
        	showmsg(message, true);
        	return;
        }
        
    	buttons_viewdetail.clear();
    	
    	//refreshing display
    	panel_booklist.removeAll();
    	panel_booklist.setSize(0, 0);
    	panel_booklist.revalidate();
    	spane_booklistwrap.revalidate();
    	
    	//adding components and setting layout
    	panel_booklist.setLayout(new GridBagLayout());
        GridBagConstraints gbdc=new GridBagConstraints();
        
        List<SimpleBook> booklist = sr.getBooks();
        for(int i = 0; i<booklist.size(); i++)
        {
        	SimpleBook book = booklist.get(i);
        	
        	gbdc.fill = GridBagConstraints.NONE;
        	gbdc.anchor = GridBagConstraints.CENTER;
        	gbdc.gridy = i*4;
        	panel_booklist.add(new JLabel("<html><img src='" + book.getImage_url() + "' /><html>"),gbdc);
        	
        	gbdc.gridy = i*4+1;
        	panel_booklist.add(new JLabel(book.getTitle()),gbdc);
        	
        	gbdc.gridy = i*4+2;
        	JButton button = new JButton("View Details");
        	panel_booklist.add(button, gbdc);
        	button.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    viewActionPerformed(evt);
                }
    		});
        	buttons_viewdetail.add(button);
        	
        	if(i<(booklist.size()-1))
        	{
	        	gbdc.gridy = i*4+3;
	        	gbdc.fill = GridBagConstraints.HORIZONTAL;
	        	gbdc.insets = new Insets(3,0,5,0);
	        	gbdc.anchor = GridBagConstraints.CENTER;
	        	JSeparator sep = new JSeparator();
	        	sep.setOrientation(Separator.HORIZONTAL);
	        	panel_booklist.add(sep, gbdc);
        	}
        }
        
        spane_booklistwrap.revalidate();
    	setBut();
    	
        //show message
        String message = "Search Complete. Total: "+sr.getTotal() + 
        ". Displaying: " + (sr.getStart()+1) + " to " + (sr.getStart()+sr.getCount());
        showmsg(message, false);
    }
    
    /**
     * set the buttons preview and next to be enables or disables according to sr
     */
    private void setBut()
    {
    	int beg = sr.getStart();
    	
    	if(beg>0)
    	{
    		but_pre.setEnabled(true);
    	}
    	else
    	{
    		but_pre.setEnabled(false);
    	}
    	
    	if(sr.getTotal()-sr.getCount()-beg>0)
    	{
    		but_next.setEnabled(true);
    	}
    	else
    	{
    		but_next.setEnabled(false);
    	}
	}

    /**
     * refresh search result when pre or next is clicked
     * @param evt the event triggered this function
     */
	protected void changViewActionPerformed(ActionEvent evt) {
		int offset = 0;
		Service s = new Service();
		
		if(evt.getSource()==but_pre)
		{
			offset = sr.getStart()-s.searchCount;
			sr = s.searchBookByKeyOrByTag(key, offset, keyOrTag);
			message = s.errMsg;
			showSearchResult();
		}
		else if(evt.getSource()==but_next)
		{
			offset = sr.getStart()+s.searchCount;
			sr = s.searchBookByKeyOrByTag(key, offset, keyOrTag);
			message = s.errMsg;
			showSearchResult();
		}
	}

	/**
	 * refresh book detail description when choosing to view details in search result
	 * @param evt the event triggered this function
	 */
	private void viewActionPerformed(java.awt.event.ActionEvent evt) {
    	for(int i = 0; i<buttons_viewdetail.size(); i++)
    	{
    		if(buttons_viewdetail.get(i)==evt.getSource())
    		{
    			int id = sr.getBooks().get(i).getId();
    	        Service service = new Service();
    	        Book book = service.getBookByID(id);
    	        message = service.errMsg;
    	        showdetails(book);
    		}
    	}
    }
	
	public void run()
	{
		Service se = new Service();
		Book bk = se.getBookByID(1386127);
		//showdetails(bk);
		
		
		List<SimpleBook> sb = new ArrayList();
		
		SimpleBook sb0 = new SimpleBook();
		sb0.setId(1386127);
		sb0.setTitle("Moby Dick");
		sb0.setImage_url("http://img3.douban.com/spic/s4736341.jpg");
		sb.add(sb0);
		
		SimpleBook sb1 = new SimpleBook();
		sb1.setId(10443265);
		sb1.setTitle("把恐龙做成大餐");
		sb1.setImage_url("http://img3.douban.com/spic/s11230955.jpg");
		sb.add(sb1);
		
		SimpleBook sb2 = new SimpleBook();
		sb2.setId(5348097);
		sb2.setTitle("这个天国不太平");
		sb2.setImage_url("http://img5.douban.com/spic/s4538389.jpg");
		sb.add(sb2);
		
		SimpleBook sb3 = new SimpleBook();
		sb3.setId(6025373);
		sb3.setTitle("风起陇西");
		sb3.setImage_url("http://img5.douban.com/spic/s4671909.jpg");
		sb.add(sb3);
		
		SimpleBook sb4 = new SimpleBook();
		sb4.setId(3013532);
		sb4.setTitle("兰波彩图集");
		sb4.setImage_url("http://img3.douban.com/spic/s2970633.jpg");
		sb.add(sb4);
		
		SimpleBook sb5 = new SimpleBook();
		sb5.setId(6857344);
		sb5.setTitle("夜莺与玫瑰");
		sb5.setImage_url("http://img5.douban.com/spic/s6980489.jpg");
		sb.add(sb5);
		
		SimpleBook sb6 = new SimpleBook();
		sb6.setId(1858576);
		sb6.setTitle("一九八四");
		sb6.setImage_url("http://img3.douban.com/spic/s1790263.jpg");
		sb.add(sb6);
		
		SimpleBook sb7 = new SimpleBook();
		sb7.setId(3815126);
		sb7.setTitle("瓦尔登湖");
		sb7.setImage_url("http://img3.douban.com/spic/s3861585.jpg");
		sb.add(sb7);
		
		SimpleBook sb8 = new SimpleBook();
		sb8.setId(1705914);
		sb8.setTitle("三国演义");
		sb8.setImage_url("http://img3.douban.com/spic/s5643167.jpg");
		sb.add(sb8);
		
		SimpleBook sb9 = new SimpleBook();
		sb9.setId(1216556);
		sb9.setTitle("查拉斯图拉如是说");
		sb9.setImage_url("http://img5.douban.com/spic/s1158579.jpg");
		sb.add(sb9);
		
		sr = new SearchResult();
		sr.setBooks(sb);
		showSearchResult();
		
		showdetails(bk);
		
		showmsg("Ready. Demo loaded.", false);
	}

    //Variable declaration
	private boolean keyOrTag = true;
	private String key;
    
    private JPanel panel_wrap;
    
    private JPanel panel_search;
    private JTextField textfield_search;
    private JButton button_search;
    private JRadioButton radio_title;
	private JRadioButton radio_isbn;
	private JRadioButton radio_tag;
	private JRadioButton radio_id;
	
	private JTabbedPane tabpane_bookwarp;
	
	private JPanel panel_details;
	private JScrollPane spane_detailwrap;
	private JTextField textfield_title;
	private JTextField textfield_rating;
	private JTextField textfield_subtitle;
	private JTextField textfield_alt;
	private JTextField textfield_author;
	private JTextField textfield_translator;
	private JTextField textfield_publisher;
	private JTextField textfield_pubdate;
	private JTextField textfield_price;
	private JTextField textfield_pages;
	private JTextField textfield_binding;
	private JTextField textfield_isbn;
	private JTextArea textarea_summary;
	private JTextArea textarea_authorintro;
	private JTextArea textfield_tags;
	
	private JPanel panel_searchResult;
	private JPanel panel_booklist;
	private JPanel panel_srBut;
	private JScrollPane spane_booklistwrap;
	private List<JButton> buttons_viewdetail;
	private JButton but_pre;
	private JButton but_next;
	
	private JPanel panel_message;
	private SearchResult sr;
	private String message;
	
}
