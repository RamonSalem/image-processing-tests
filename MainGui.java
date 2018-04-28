import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.Button;
import javax.swing.JList;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import java.awt.Panel;
import javax.swing.JTextPane;
import java.awt.TextField;

public class MainGui {

	private JFrame frame;
	
	/*Image selector*/
	public static BufferedImage chooseImage() throws java.io.IOException{
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        BufferedImage choosenImage = null;
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            BufferedImage myPicture = ImageIO.read(chooser.getSelectedFile());
            return myPicture;
        }
        else{
            return choosenImage;
        }
    }
	/*Adds a image to a panel passed in the first param... The image is added to a JLabel*/
	public static void setImageOnFrame(JPanel panel, BufferedImage image) {
		JLabel picLabel = new JLabel(new ImageIcon(image));
		panel.add(picLabel);
		panel.revalidate();
		panel.repaint();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGui() {
		Image imgObj = new Image();
		initialize(imgObj);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Image imgObj) {
		
		/*General panel*/
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		/*Original image panel*/
		JPanel imagePanel = new JPanel();
		/*Panel of the results images*/
		JPanel imageResultPanel = new JPanel();
		/*Main frame*/
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*Menu bar with the options*/
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnNewMenu);
		/*Button used to load images from disk*/
		JMenuItem mntmAbrirImagem = new JMenuItem("Abrir imagem");
		mntmAbrirImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//imgObj.show();
					BufferedImage newImage = MainGui.chooseImage();
					imgObj.setImage(newImage);
					MainGui.setImageOnFrame(imagePanel, newImage);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			
			}
		});
		
		mnNewMenu.add(mntmAbrirImagem);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnNewMenu.add(mntmSair);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		panel.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnShowRed = new JButton("Banda R");
		btnShowRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(imgObj.getImage());
				MainGui.setImageOnFrame(imageResultPanel, imgObj.getBandaRed(imgObj.getImage()));
			}
		});
		topPanel.add(btnShowRed);
		
		JButton btnShowGreen = new JButton("Banda G");
		btnShowGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(imgObj.getImage());
				MainGui.setImageOnFrame(imageResultPanel, imgObj.getBandaGreen(imgObj.getImage()));
			}
		});
		topPanel.add(btnShowGreen);
		
		JButton btnShowBlue = new JButton("Banda B");
		btnShowBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(imgObj.getImage());
				MainGui.setImageOnFrame(imageResultPanel, imgObj.getBandaBlue(imgObj.getImage()));
				
			}
		});
		topPanel.add(btnShowBlue);
		
		JButton btnGrayScale = new JButton("Escala de cinza");
		btnGrayScale.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
				System.out.println(imgObj.getImage());
				MainGui.setImageOnFrame(imageResultPanel, imgObj.toGrayScale(imgObj.getImage()));
				
			}
		});
		topPanel.add(btnGrayScale);
		
		JButton btmNegativeRgb = new JButton("Negativo RGB");
		
		btmNegativeRgb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					System.out.println(imgObj.getImage());
					MainGui.setImageOnFrame(imageResultPanel, imgObj.negativeRgb(imgObj.getImage()));
					
				}
			});
		
		topPanel.add(btmNegativeRgb);
		
		JButton btnNegativeY = new JButton("Negativo Y");
		
		btnNegativeY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					System.out.println(imgObj.getImage());
					MainGui.setImageOnFrame(imageResultPanel, imgObj.negativeYuv(imgObj.getImage()));
					
				}
			});
		topPanel.add(btnNegativeY);
		
		Panel downPanel = new Panel();
		panel.add(downPanel, BorderLayout.SOUTH);
		
		JButton btnAdditiveControl = new JButton("Aplicar");
		btnAdditiveControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		TextField aditiveControl = new TextField();
		aditiveControl.setText("Controle aditivo");
		downPanel.add(aditiveControl);
		downPanel.add(btnAdditiveControl);
		
		JTextPane textPane = new JTextPane();
		downPanel.add(textPane);
		
		TextField textField = new TextField();
		textField.setText("Controle multiplicativo");
		downPanel.add(textField);
		
		JButton btnMultiplicativeControl = new JButton("Aplicar");
		downPanel.add(btnMultiplicativeControl);
		
		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane, BorderLayout.CENTER);
		
		//JPanel imagePanel = new JPanel();
		splitPane.setLeftComponent(imagePanel);
		
		//JPanel imageResultPanel = new JPanel();
		splitPane.setRightComponent(imageResultPanel);
		imageResultPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblImagem = new JLabel("");
		lblImagem.setToolTipText("Resultado");
		lblImagem.setIcon(null);
		imageResultPanel.add(lblImagem);
	}

}
