package com.prjcadcliente.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.prjcadcliente.dominio.Cliente;
import com.prjcadcliente.persistencia.CRUDCliente;

public class GerenciarClientes extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtIdade;
	private JTable tbClientesCadastro;
	private Cliente cliente;
	private CRUDCliente crud;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarClientes frame = new GerenciarClientes();
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
	public GerenciarClientes() {
		//Vamos instanciar as classe Cliente e CRUD
		cliente = new Cliente();
		crud = new CRUDCliente();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 601);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Passar os dados do formulário para o objeto cliente
				cliente.setNome(txtNome.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setTelefone(txtTelefone.getText());
				cliente.setIdade(Integer.parseInt(txtIdade.getText()));
				
				String retorno = crud.cadastrar(cliente);
				JOptionPane.showMessageDialog(null, retorno);
				txtNome.setText("");
				txtEmail.setText("");
				txtTelefone.setText("");
				txtIdade.setText("");
			}
		});
		btnCadastrar.setBackground(Color.LIGHT_GRAY);
		btnCadastrar.setBounds(10, 248, 97, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBackground(Color.LIGHT_GRAY);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPesquisar.setBounds(117, 248, 97, 23);
		contentPane.add(btnPesquisar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog("Digite o ID do cliente Para Apagar");
				cliente.setId(Integer.parseInt(id));
				JOptionPane.showMessageDialog(null, crud.deletar(cliente));
			}
		});
		btnDeletar.setBackground(Color.LIGHT_GRAY);
		btnDeletar.setBounds(331, 248, 97, 23);
		contentPane.add(btnDeletar);
		
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog("Digite o ID do cliente");
				
					//Passar os dados do formulário para o objeto cliente
					cliente.setNome(txtNome.getText());
					cliente.setEmail(txtEmail.getText());
					cliente.setTelefone(txtTelefone.getText());
					cliente.setIdade(Integer.parseInt(txtIdade.getText()));
					cliente.setId(Integer.parseInt(id));
					
					String retorno = crud.atualizar(cliente);
					JOptionPane.showMessageDialog(null, retorno);
					txtNome.setText("");
					txtEmail.setText("");
					txtTelefone.setText("");
					txtIdade.setText("");
			}
		});
		btnAtualizar.setBackground(Color.LIGHT_GRAY);
		btnAtualizar.setBounds(224, 248, 97, 23);
		contentPane.add(btnAtualizar);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNome.setBounds(21, 11, 48, 14);
		contentPane.add(lblNome);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(21, 61, 48, 14);
		contentPane.add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(21, 117, 97, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblIdade = new JLabel("Idade");
		lblIdade.setBounds(21, 173, 48, 14);
		contentPane.add(lblIdade);
		
		txtNome = new JTextField();
		txtNome.setBounds(20, 30, 334, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(20, 86, 334, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(21, 142, 333, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		txtIdade = new JTextField();
		txtIdade.setBounds(20, 198, 334, 20);
		contentPane.add(txtIdade);
		txtIdade.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 299, 416, 262);
		contentPane.add(scrollPane);
		
		String[] colunas = {"Id","Nome","E-Mail","Telefone","Idade"};
		
		Object[][] dados = {
				{15,"Roberto","Roberto@gmail.com","13123123",15},
				{16,"Roberto","Roberto@gmail.com","13123123",15},
				{17,"Roberto","Roberto@gmail.com","13123123",15},
				{18,"Roberto","Roberto@gmail.com","13123123",15},
				{19,"Roberto","Roberto@gmail.com","13123123",15},
				{20,"Roberto","Roberto@gmail.com","13123123",15},
				{21,"Roberto","Roberto@gmail.com","13123123",15}
		};
		
		//Vamos construir o modelo de dados para exibir na tabela
		DefaultTableModel modelo = new DefaultTableModel(dados,colunas);
		tbClientesCadastro = new JTable(modelo);
		scrollPane.setViewportView(tbClientesCadastro);
	}
}
