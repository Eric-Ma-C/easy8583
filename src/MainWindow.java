import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class MainWindow extends JFrame {
	
	private static String packet_data = "";
	 //declare PrintStream and JTextArea
    private static PrintStream ps = null;
    private JTextArea result_jta,packet_jta;  //constructor
    private JPanel panel_packet,panel_result;

    
    int WIN_HEIGHT,WIN_WIDTH;
    MenuBar menubar;  
    Menu menu;  
    MenuItem item1, item2;  
//    public StreamToTextArea() {}
 
    MainWindow(String s)  
    {  

//        setSize( 310, 180 );

        
        setTitle(s);  
        Toolkit tool = getToolkit();  
        Dimension dim = tool.getScreenSize(); 
        WIN_HEIGHT = dim.height/8*7;
        WIN_WIDTH = dim.width/3;
        setBounds(20, 20, WIN_WIDTH, WIN_HEIGHT);  
        menubar = new MenuBar();  
        menu = new Menu("文件");  
        item1 = new MenuItem("打开");  
        item2 = new MenuItem("保存");  
        menu.add(item1);  
        menu.add(item2);  
        menubar.add(menu);  
        setMenuBar(menubar);  
        
        getContentPane().setLayout(new BorderLayout());
        
//        packet_jta.setBounds(1, 1, 1, 1);
        
        packet_jta = new JTextArea(WIN_HEIGHT/30,WIN_WIDTH/40);
        result_jta = new JTextArea(WIN_HEIGHT/20,WIN_WIDTH/18);
        packet_jta.setLineWrap(true);        //激活自动换行功能 
        result_jta.setLineWrap(true);        //激活自动换行功能 
        
        panel_packet = new JPanel();
        panel_result = new JPanel();
        panel_packet.setLayout(new GridLayout(2, 0, 0, 0));
        JScrollPane scrollPane = new JScrollPane(packet_jta);
        panel_packet.add(scrollPane);
//        panel_packet.setPreferredSize(new Dimension(120, 200));
        panel_result.add(new JScrollPane(result_jta));
        
        getContentPane().add("West",panel_packet);
        
        JButton button = new JButton("解析");
        panel_packet.add(button);
        button.setBounds(1200, 120, 200, 300);
        getContentPane().add("East",panel_result);
        
        //this is the trick: overload the println(String)
        //method of the PrintStream
        //and redirect anything sent to this to the text box
        ps = new PrintStream(System.out) {
        	public void println(String x) {
        		result_jta.append(x + "\n");
        	}
        };
        
        setVisible(true);   
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	result_jta.setText("");
                packet_data = packet_jta.getText();
                Packet8583 packet8583 = new Packet8583(packet_data);
        		packet8583.showField();
            }
        });
        this.addWindowListener
        (
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
    }  
    
    public PrintStream getPs() {
        return ps;
    }
}
