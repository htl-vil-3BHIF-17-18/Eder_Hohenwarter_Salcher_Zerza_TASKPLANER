package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bll.Kategorie;
import bll.Task;

public class Taskdialog extends JPanel{
	private JLabel lblKategorie = null;
	private JLabel lblVon = null;
	private JLabel lblBis = null;
	private JLabel lblFach = null;
	private JLabel lblBeschreibung = null;
	private JButton btnAdd = null;
	//Test for Push please delete
	private JComboBox  cbKategorie= null;
	private JButton btnAendern=null;
	private Kategorie[] cbKategorienListe = {Kategorie.GLF,Kategorie.Mitarbeitskontrolle,Kategorie.Haus�bung,Kategorie.PLF,Kategorie.Schularbeit,Kategorie.Schul�bung};
	private JFormattedTextField txtVon = null;
	private JFormattedTextField txtBis = null;
	private JTextField txtFach = null;
	private JTextField txtBeschreibung = null;
	
	
	private ActionListener al=null;
	private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	
	private JFrame parent = null;
	private Task task = null;
	
	public Taskdialog(JFrame parent,Task task,ActionListener al) {
		this.al =al;
		this.parent = parent;
		this.task = task;
		initializeControls();
		
	}
	private void initializeControls() {
		this.setLayout(new GridLayout(6,2));
		this.setMinimumSize(new Dimension(300,300));
		this.setPreferredSize(new Dimension(300,300));
		lblKategorie = new JLabel("Kategorie :");
		lblVon = new JLabel("Von :");
		lblBis= new JLabel("Bis :");
		lblFach = new JLabel("Fach :");
		lblBeschreibung = new JLabel("Beschreibung :");
		btnAdd = new JButton("Add");
		btnAendern=new JButton("�ndern");
		
		btnAendern.addActionListener(al);
		btnAendern.setActionCommand("btnAendern");
		btnAendern.setEnabled(false);
		
		btnAdd.addActionListener(al);
		btnAdd.setActionCommand("btnAdd");
		
		
		cbKategorie = new JComboBox(cbKategorienListe);
		txtVon = new JFormattedTextField(dateFormat);
		txtBis= new JFormattedTextField(dateFormat);
		txtFach = new JTextField();
		txtBeschreibung = new JTextField();
			
		this.add(lblKategorie);
		this.add(cbKategorie);
		this.add(lblVon);
		this.add(txtVon);
		this.add(lblBis);
		this.add(txtBis);
		this.add(lblFach);
		this.add(txtFach);
		this.add(lblBeschreibung);
		this.add(txtBeschreibung);
		this.add(btnAdd);
		this.add(btnAendern);
		
	}
	public Task getEingabeTask() throws ParseException {
		//Liste zur Auswahl hinzugef�gt und Auswahl eingef�gt
		Task t=null;
		if(!this.txtBeschreibung.getText().trim().isEmpty() && !this.txtBis.getText().trim().isEmpty() && !this.txtFach.getText().trim().isEmpty() && !this.txtVon.getText().trim().isEmpty())
		{
			t=new Task((Kategorie) cbKategorie.getSelectedItem(),this.txtFach.getText(),this.txtBeschreibung.getText(),dateFormat.parse(this.txtVon.getText()),dateFormat.parse(this.txtBis.getText()),false);
		}
		return t;
	}
	public void setTask(Task selectedTask) {
		this.txtBeschreibung.setText(selectedTask.getBeschreibung());
		this.txtFach.setText(selectedTask.getFach());
		this.txtVon.setText(selectedTask.getVon().toLocaleString().split(" ")[0]);
		this.txtBis.setText(selectedTask.getBis().toLocaleString().split(" ")[0]);
		
		this.cbKategorie.setSelectedItem(selectedTask.getKategorie());
	}
	public void enableAendernButton(Boolean bool) {
		this.btnAendern.setEnabled(bool);
		this.btnAdd.setEnabled(!bool);
	}
	public void makeClear() {
		this.txtBeschreibung.setText("");
		this.txtBis.setText("");
		this.txtVon.setText("");
		this.txtFach.setText("");
		this.cbKategorie.setSelectedIndex(0);
		
	}
}
