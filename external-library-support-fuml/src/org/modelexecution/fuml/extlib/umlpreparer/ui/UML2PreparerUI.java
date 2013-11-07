package org.modelexecution.fuml.extlib.umlpreparer.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.modelexecution.fuml.extlib.umlpreparer.UML2Preparer;


public class UML2PreparerUI extends JFrame {

	private static final long serialVersionUID = -964439577220945806L;

	private JPanel contentPane;
	private JTextField textFieldLibraryJar;
	private JButton btnSelectLibraryJar;
	private JButton btnAddDependentJars;
	private JButton btnRemoveSelectedJars;
	private JButton btnPrepareUmlClass;
	
	private JList listDependentJarFiles;
	
	private JPanel panelExternalLibrarySettings;
	private JPanel panelControlArea;
	
	private File fileLibraryJar;
	private DefaultListModel<File> modelDependentJarFiles;
	private File fileUmlActivityModel;
	private File fileUmlInputClassModel;
	private File fileUmlOutputClassModel;
	private File CURRENT_MODELS_DIRECTORY = new File("samples/models");
	private File CURRENT_LIBRARYS_DIRECTORY = new File("samples/extlibs");
	
	private ActionListener actionListener;
	private JTextField textFieldInputModel;
	private JTextField textFieldOutputModel;

	private JLabel lblDependentJarFiles = new JLabel("Dependent JAR(s):");

	private JLabel lblLibraryJarPath = new JLabel("Library JAR:");

	private JButton btnQuit = new JButton("Quit");

	private JPanel panelSpecifyInputOutputModel = new JPanel();

	private JLabel labelInputModel = new JLabel("Input UML Class Model:");

	private JButton btnSelectInput = new JButton("Select Input UML Class Model");

	private JLabel labelOutputModel = new JLabel("Output UML Class Model:");

	private JButton btnSelectOutput = new JButton("Select Output UML Class Model");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UML2PreparerUI frame = new UML2PreparerUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public UML2PreparerUI() {
		actionListener = createActionListener();
		
		setTitle("UML2 Preparer Tool for Integration of External Libraries into Foundational UML Prototype\n");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 968, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panelExternalLibrarySettings = new JPanel();
		panelExternalLibrarySettings.setBounds(22, 23, 268, 337);
		panelExternalLibrarySettings.setBorder(BorderFactory.createTitledBorder("External Library Settings"));
		
		panelControlArea = new JPanel();
		panelControlArea.setBounds(318, 245, 623, 115);
		panelControlArea.setBorder(BorderFactory.createTitledBorder("Control Area"));
		panelControlArea.setLayout(null);
		panelExternalLibrarySettings.setLayout(null);
		
		textFieldLibraryJar = new JTextField();
		textFieldLibraryJar.setEditable(false);
		textFieldLibraryJar.setBounds(27, 59, 206, 28);
		panelExternalLibrarySettings.add(textFieldLibraryJar);
		textFieldLibraryJar.setToolTipText("path to external library JAR");
		textFieldLibraryJar.setColumns(10);
		
		btnSelectLibraryJar = new JButton("Select library JAR");
		btnSelectLibraryJar.setName("testName");
		btnSelectLibraryJar.setBounds(27, 93, 206, 29);
		btnSelectLibraryJar.addActionListener(actionListener);
		panelExternalLibrarySettings.add(btnSelectLibraryJar);
		
		lblDependentJarFiles.setBounds(27, 148, 128, 16);
		panelExternalLibrarySettings.add(lblDependentJarFiles);
		
		listDependentJarFiles = new JList<DefaultListModel<File>>();
		modelDependentJarFiles = new DefaultListModel<File>();
		listDependentJarFiles.setModel(modelDependentJarFiles);
		listDependentJarFiles.setBounds(27, 176, 206, 62);
		panelExternalLibrarySettings.add(listDependentJarFiles);
		
		btnAddDependentJars = new JButton("Add dependent JAR(s)");
		btnAddDependentJars.setBounds(27, 244, 206, 29);
		btnAddDependentJars.addActionListener(actionListener);
		panelExternalLibrarySettings.add(btnAddDependentJars);
		
		btnRemoveSelectedJars = new JButton("Remove selected JAR(s)");
		btnRemoveSelectedJars.setBounds(27, 279, 206, 29);
		btnRemoveSelectedJars.addActionListener(actionListener);
		panelExternalLibrarySettings.add(btnRemoveSelectedJars);
		
		lblLibraryJarPath.setBounds(27, 36, 128, 16);
		panelExternalLibrarySettings.add(lblLibraryJarPath);
		contentPane.setLayout(null);
		contentPane.add(panelExternalLibrarySettings);
		contentPane.add(panelControlArea);
		
		btnPrepareUmlClass = new JButton("Prepare UML Class Model");
		btnPrepareUmlClass.setBounds(18, 35, 296, 57);
		btnPrepareUmlClass.addActionListener(actionListener);
		panelControlArea.add(btnPrepareUmlClass);
		
		btnQuit.setBounds(326, 35, 269, 57);
		btnQuit.addActionListener(actionListener);
		panelControlArea.add(btnQuit);
		
		panelSpecifyInputOutputModel.setLayout(null);
		panelSpecifyInputOutputModel.setBorder(BorderFactory.createTitledBorder("Input / Output UML Class Model"));
		panelSpecifyInputOutputModel.setBounds(318, 23, 623, 210);
		contentPane.add(panelSpecifyInputOutputModel);
		
		labelInputModel.setBounds(19, 25, 179, 16);
		panelSpecifyInputOutputModel.add(labelInputModel);
		
		btnSelectInput.setBounds(19, 74, 583, 29);
		btnSelectInput.addActionListener(actionListener);
		panelSpecifyInputOutputModel.add(btnSelectInput);
		
		textFieldInputModel = new JTextField();
		textFieldInputModel.setEditable(false);
		textFieldInputModel.setColumns(10);
		textFieldInputModel.setBounds(19, 43, 583, 28);
		panelSpecifyInputOutputModel.add(textFieldInputModel);
		
		labelOutputModel.setBounds(19, 115, 179, 16);
		panelSpecifyInputOutputModel.add(labelOutputModel);
		
		textFieldOutputModel = new JTextField();
		textFieldOutputModel.setEditable(false);
		textFieldOutputModel.setColumns(10);
		textFieldOutputModel.setBounds(19, 133, 583, 28);
		panelSpecifyInputOutputModel.add(textFieldOutputModel);
		
		btnSelectOutput.setBounds(19, 164, 583, 29);
		btnSelectOutput.addActionListener(actionListener);
		panelSpecifyInputOutputModel.add(btnSelectOutput);
		
	}

	private ActionListener createActionListener() {
		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource().equals(btnSelectLibraryJar)) {
					
					final JFileChooser fc = new JFileChooser();
					fc.setCurrentDirectory(CURRENT_LIBRARYS_DIRECTORY);
					fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
					int returnVal = fc.showOpenDialog(contentPane);
					fileLibraryJar = fc.getSelectedFile();
					textFieldLibraryJar.setText(fileLibraryJar.getAbsolutePath());
					
				} else if (e.getSource().equals(btnAddDependentJars)) {
					
					final JFileChooser fc = new JFileChooser();
					fc.setCurrentDirectory(CURRENT_LIBRARYS_DIRECTORY);
					fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
					fc.setMultiSelectionEnabled(true);
					int returnVal = fc.showOpenDialog(contentPane);
					final File[] fileDependentJars = fc.getSelectedFiles();
					for (File file : fileDependentJars) {
						if (!modelDependentJarFiles.contains(file)) {
							modelDependentJarFiles.addElement(file);
						}
					}
					
				} else if (e.getSource().equals(btnRemoveSelectedJars)) {
					
					final List<File> selectedFilesToRemoveList = listDependentJarFiles.getSelectedValuesList();
					for (File file : selectedFilesToRemoveList) {
						if (modelDependentJarFiles.contains(file)) {
							modelDependentJarFiles.removeElement(file);
						}
					}
					
				} else if (e.getSource().equals(btnSelectInput)) {
					
					final JFileChooser fc = new JFileChooser();
					fc.setCurrentDirectory(CURRENT_MODELS_DIRECTORY);
					fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
					int returnVal = fc.showOpenDialog(contentPane);
					fileUmlInputClassModel = fc.getSelectedFile();
					textFieldInputModel.setText(fileUmlInputClassModel.getAbsolutePath());
					
				} else if (e.getSource().equals(btnSelectOutput)) {
					
					final JFileChooser fc = new JFileChooser();
					fc.setCurrentDirectory(CURRENT_MODELS_DIRECTORY);
					fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
					int returnVal = fc.showSaveDialog(contentPane);
					fileUmlOutputClassModel = fc.getSelectedFile();
					textFieldOutputModel.setText(fileUmlOutputClassModel.getAbsolutePath());
					
				} else if (e.getSource().equals(btnPrepareUmlClass)) {
					
					prepareUmlClassModel();
					
				} else if (e.getSource().equals(btnQuit)) {
					
					System.exit(0);
					
				}
				
			}

		};
		
		return actionListener;
	}

	private void prepareUmlClassModel() {
		
		UML2Preparer preparer = new UML2Preparer();
		
		preparer.load(fileUmlInputClassModel.getAbsolutePath());
		
		final String[] allJarFiles = new String[1+modelDependentJarFiles.size()];
		allJarFiles[0] = fileLibraryJar.getAbsolutePath();
		for (int i=0; i<modelDependentJarFiles.size(); i++) {
			allJarFiles[i+1] = modelDependentJarFiles.get(i).getAbsolutePath();
		}
		
		preparer.convert(allJarFiles);
		
		preparer.save(fileUmlOutputClassModel.getAbsolutePath());
		
	}// prepareUmlClassModel
	
}// UML2PreparerUI
