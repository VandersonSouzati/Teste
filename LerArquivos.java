
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LerArquivos extends JFrame {
	private JTextField cxCaminho, cxArquivo, cxDia, cxHora, cxMinuto, cxUnidades;
	private JLabel lbCaminho, lbArquivo, lbDia, lbHora, lbMinuto;
	private JButton btnVerificar, btnSair;
	private JTextArea areaTexto;
	private JScrollPane jScrollPane;
	private JProgressBar barraProgresso;
	private int totalUnidades = 22;
	private JPanel painel;
    private JFrame frame2 = new JFrame();
    private JDialog dialog = new JDialog();
    int verificador = 1;
	
    public LerArquivos() {
		inicializaComponentes();
		definirEventos();
	}

	/**************************************************************************************/
	public void inicializaComponentes() {

		setLayout(null);
		setBounds(100, 200, 450, 550);
                setResizable(false);
		setTitle("Verificar Arquivos");
		btnVerificar = new JButton("Verificar");
		areaTexto = new JTextArea(22, 40);
		barraProgresso = new JProgressBar();
		cxDia = new JTextField();
		cxHora = new JTextField();
		cxMinuto = new JTextField();
		cxCaminho = new JTextField();
		cxArquivo = new JTextField();
		lbDia = new JLabel("Dia: ");
		lbHora = new JLabel("Hora: ");
		lbMinuto = new JLabel("Minuto: ");
		lbCaminho = new JLabel("Caminho:");
		lbArquivo = new JLabel("Arquivo:");

		lbDia.setBounds(10, 10, 50, 20);
		lbHora.setBounds(10, 40, 50, 20);
		lbMinuto.setBounds(10, 70, 50, 20);
		lbArquivo.setBounds(10, 100, 50, 20);
		lbCaminho.setBounds(5, 130, 220, 20);

		cxDia.setBounds(70, 10, 50, 20);
		cxHora.setBounds(70, 40, 50, 20);
		cxMinuto.setBounds(70, 70, 50, 20);
		cxArquivo.setBounds(70, 100, 90, 20);
		cxCaminho.setBounds(70, 130, 200, 20);

		areaTexto.setBounds(5, 180, 420, 320);

		jScrollPane = new JScrollPane(areaTexto);
		jScrollPane.setBounds(5, 180, 420, 320);
		barraProgresso.setMinimum(0);
		barraProgresso.setMaximum(100);

		btnVerificar.setBounds(250, 10, 100, 30);
		barraProgresso.setBounds(5, 160, 420, 15);
		cxArquivo.setText("teste.txt");
		cxCaminho.setText("D:\\Temp\\serverUnl(Loja)teste");
		add(btnVerificar);
		// add(areaTexto);
		add(barraProgresso);
		add(lbDia);
		add(lbHora);
		add(lbMinuto);
		add(lbCaminho);
		add(lbArquivo);
		add(cxDia);
		add(cxHora);
		add(cxMinuto);
		add(cxArquivo);
		add(cxCaminho);
		add(jScrollPane);
	}

	public static void centralizarJanela(JFrame frame) {
		Dimension dimensionTotal = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimensionJanela = frame.getSize();
		frame.setLocation((dimensionTotal.width - dimensionJanela.width) / 2,
				(dimensionTotal.height - dimensionJanela.height) / 2);
	}

	/**************************************************************************************/

	/**************************************************************************************/
	public void definirEventos() {
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lerArquivos();
			}
		});
	}

	/**************************************************************************************/

	
	/*****************
	 * Retorna a Data
	 *********************************************************/

	public String pegaData(String caminho) {
		File file = new File(caminho);
		BasicFileAttributes fileAttributes;
		String dataFormatada = null;
		Date data = null;
		try {
			fileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			FileTime fileTime = fileAttributes.lastModifiedTime();

			data = new Date(fileTime.toMillis());

			String pattern = "dd-MM-yyyy HH:mm:ss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			dataFormatada = simpleDateFormat.format(new Date(fileTime.toMillis()));
		} catch (IOException erro) {
		}
		return dataFormatada;
	}

	/*************************************************************************************/

	/*************************************************************************************/
	public void lerArquivos() {
		String[] unidades = new String[totalUnidades + 1];
		String caminho = cxCaminho.getText().replace("\\", "/") + "/" + cxArquivo.getText();
		String dia, hora, minuto, men;

		areaTexto.setText("");
		mensagemAux();
		//dialog.setModal(true);
		
		mensagem();
		for (int c = 1; c <= totalUnidades; c++) {
			caminho = caminho.replace("(Loja)", String.valueOf(c));
			String dataFormatada = pegaData(caminho);
			men = (c < 10) ? "00" : "0";
			areaTexto.append("Unidade: " + men + c + "   " + dataFormatada + "\n");
			unidades[c] = dataFormatada;
			caminho = caminho.replace(String.valueOf(c), "(Loja)");
		    verificador++;
		}
		dialog.dispose();
       
       JOptionPane.showMessageDialog(null, "Arquivos Processados", "Resultado", JOptionPane.INFORMATION_MESSAGE);
	}

	/**************************************************************************************/

	
	
/**************************************************************************************/



/*****************************************/
    public void mensagemAux() {
	    mensagem();
	    dialog.dispose();  

    }
/******************************************/


    public void mensagem() {        

        dialog.setLocationRelativeTo(frame2.getContentPane());
        //dialog.setModal(true);
        Dimension dimension = new Dimension();
        dimension.setSize(400,90);
        dialog.setMinimumSize(dimension);
        JLabel label = new JLabel();
        label.setText("Processando Arquivos.....");
        label.setFont(new Font("Serif",Font.BOLD+Font.ITALIC, 35));
        label.setForeground(Color.blue);
        label.setBounds(10,200,200,200);
        dialog.setTitle("|------PROCESSANDO ARQUIVOS------|");
        dialog.add(label);
        dialog.repaint();
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);
        //dialog.setModal(false);

         
    }
/**************************************************************************************/

	
	public static void main(String[] args) {
		LerArquivos principal = new LerArquivos();
		principal.setVisible(true);
		principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centralizarJanela(principal);
	}

}
