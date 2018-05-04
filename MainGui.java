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
import java.util.ArrayList;
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

	private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	
	private static ArrayList<BufferedImage> imagesResults = new ArrayList<BufferedImage>();
	
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
	
	/*Save file*/
	public static void saveImage(Image imageOps) throws java.io.IOException{
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(null);
        
        if(returnVal == JFileChooser.APPROVE_OPTION && imagesResults.size()>=1) {
            
            imageOps.saveImageToDisk(
            		imagesResults.get(imagesResults.size()-1),
            		chooser.getSelectedFile().getName(),
            		chooser.getCurrentDirectory().toString()
            		);
        }
        else{
        	System.out.println("Not Ok");
        }
    }
	
	/*Adds a image to a panel passed in the first param... The image is added to a JLabel*/
	public static void setImageOnFrame(JPanel panel, BufferedImage image) {
		
		if(panel.getName().equals("imageResultPanel")) {
			imagesResults.add(image);
		}
		
		JLabel picLabel = new JLabel(new ImageIcon(image));
		panel.add(picLabel);
		panel.revalidate();
		panel.repaint();
	}
	
	/*Clear all images of a panel*/
	
	public static void clearPanel(JPanel panel) {
		panel.removeAll();
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
		imagePanel.setName("imagePanel");
		/*Panel of the results images*/
		JPanel imageResultPanel = new JPanel();
		imageResultPanel.setName("imageResultPanel");
		/*Main frame*/
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*Menu bar with the options*/
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		/*Down panel*/
		Panel downPanel = new Panel();
		
		/*Text buttons*/
		TextField aditiveControlText = new TextField();
		aditiveControlText.setText("Controle aditivo");
		
		TextField multiplicativeControlText = new TextField();
		multiplicativeControlText.setText("Controle multiplicativo");
		
		JMenu mnNewMenu = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnNewMenu);
		/*Button used to load images from disk*/
		JMenuItem mntmAbrirImagem = new JMenuItem("Abrir imagem");
		mntmAbrirImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//imgObj.show();
					BufferedImage newImage = MainGui.chooseImage();
					images.add(newImage);
					imgObj.setImage(newImage);
					MainGui.setImageOnFrame(imagePanel, newImage);

					System.out.println(images.size());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			
			}
		});
		
		mnNewMenu.add(mntmAbrirImagem);
		/*Menu item to click and clear panel*/
		JMenuItem jMenuItemClearAll = new JMenuItem("Limpar");
		jMenuItemClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGui.clearPanel(imagePanel);
				images.clear();
				imagesResults.clear();
			}
		});
		mnNewMenu.add(jMenuItemClearAll);
		
		JMenuItem mntmSaveImage = new JMenuItem("Salvar imagem");
		mntmSaveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MainGui.saveImage(imgObj);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mntmSaveImage);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnNewMenu.add(mntmSair);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		/*Top panel configs and basic actions*/ 
		JPanel topPanel = new JPanel();
		panel.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		/*Button that shows red scheme*/
		JButton btnShowRed = new JButton("Banda R");
		btnShowRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(imgObj.getImage());
				MainGui.setImageOnFrame(imageResultPanel, imgObj.getBandaRed(imgObj.getImage()));
			}
		});
		topPanel.add(btnShowRed);
		
		/*Button that shows green scheme*/
		JButton btnShowGreen = new JButton("Banda G");
		btnShowGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(imgObj.getImage());
				MainGui.setImageOnFrame(imageResultPanel, imgObj.getBandaGreen(imgObj.getImage()));
			}
		});
		topPanel.add(btnShowGreen);
		/*Button that shows blue scheme*/
		JButton btnShowBlue = new JButton("Banda B");
		btnShowBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(imgObj.getImage());
				MainGui.setImageOnFrame(imageResultPanel, imgObj.getBandaBlue(imgObj.getImage()));
				
			}
		});
		topPanel.add(btnShowBlue);

		/*Button that shows gray scale*/
		JButton btnGrayScale = new JButton("Escala de cinza");
		btnGrayScale.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
				System.out.println(imgObj.getImage());
				MainGui.setImageOnFrame(imageResultPanel, imgObj.toGrayScale(imgObj.getImage()));
				
			}
		});
		topPanel.add(btnGrayScale);

		/*Button that shows negative rgb*/
		JButton btmNegativeRgb = new JButton("Negativo RGB");
		
		btmNegativeRgb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					System.out.println(imgObj.getImage());
					MainGui.setImageOnFrame(imageResultPanel, imgObj.negativeRgb(imgObj.getImage()));
					
				}
			});
		
		topPanel.add(btmNegativeRgb);

		/*Button that shows Y*/
		
		JButton btnNegativeY = new JButton("Negativo Y");
		
		btnNegativeY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					System.out.println(imgObj.getImage());
					MainGui.setImageOnFrame(imageResultPanel, imgObj.negativeYuv(imgObj.getImage()));
					
				}
			});
		topPanel.add(btnNegativeY);
		
		JButton btnClearRightSpot = new JButton("Limpar");
		btnClearRightSpot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGui.clearPanel(imageResultPanel);
			}
		});
		topPanel.add(btnClearRightSpot);
		
		
		panel.add(downPanel, BorderLayout.SOUTH);
		
		/**Button, in down panel, used to additive control */
		JButton btnAdditiveControl = new JButton("Aplicar");
		btnAdditiveControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//int valueToAdd = Integer.parseInt(aditiveControlText.getText()); 
					MainGui.setImageOnFrame(imageResultPanel, imgObj.aditiveControl(imgObj.getImage(),
							Integer.parseInt(aditiveControlText.getText())
					));
				}catch(NumberFormatException exception) {
					System.out.println(exception);
				}
			}
		});
		
		downPanel.add(aditiveControlText);
		downPanel.add(btnAdditiveControl);
		
		JTextPane textPane = new JTextPane();
		downPanel.add(textPane);
		
		downPanel.add(multiplicativeControlText);

		/**Button, in down panel, used to multiplicative control */
		JButton btnMultiplicativeControl = new JButton("Aplicar");
		btnMultiplicativeControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//int valueToAdd = Integer.parseInt(aditiveControlText.getText()); 
					MainGui.setImageOnFrame(imageResultPanel, imgObj.multiplicativeControl(imgObj.getImage(),
							Integer.parseInt(multiplicativeControlText.getText())
							));
				}catch(NumberFormatException exception) {
					System.out.println(exception);
				}
			}
		});
		downPanel.add(btnMultiplicativeControl);
		
		JButton btnSuperposition = new JButton("Sobreposi\u00E7\u00E3o");
		btnSuperposition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(images.size() >= 2) {
					
					MainGui.setImageOnFrame(imageResultPanel,
							imgObj.overlapImages(images.get(images.size()-2) , images.get(images.size()-1)));
				}
				System.out.println("Not enough images");
			}
		});
		downPanel.add(btnSuperposition);
		
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
