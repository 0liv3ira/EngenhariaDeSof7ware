/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.commons.mail.EmailException;
import system.EnviarEmail;
import system.Sistema;

//Esquema para deletar curriculo 
import model.bean.Fez;
import model.bean.Curso;
import model.bean.Emprego;
import model.bean.Pessoa;
import model.bean.TrabalhouEm;

import model.dao.CursoDao;
import model.dao.EmpregoDao;
import model.dao.FezDao;
import model.dao.PessoaDao;
import model.dao.TrabalhouDao;

/**
 *
 * @author Venom
 */
public class Resultado extends javax.swing.JFrame {
    
    public CursoDao o1;
    public EmpregoDao o2;
    public FezDao o3;
    public PessoaDao o4;
    public TrabalhouDao o5;

    
    public Sistema s;
    public Pessoa P;
    public Fez Fi;
    public ArrayList<Fez> F;
    public Curso Ci;
    public ArrayList<Curso>  C;
    public TrabalhouEm Ti;
    public ArrayList<TrabalhouEm> TE;
    public Emprego Ei;
    public ArrayList<Emprego> E;
    public byte[] foto;
    public ArrayList<String> resultados;
    public int qtdCURSOS , qtdEMPREGO;
    /**
     * Creates new form Cadastro
     */
    public Resultado(ArrayList<String> results) {
         
        s= new Sistema();
            o1 = new CursoDao();
    o2 = new EmpregoDao();
    o3 = new FezDao();
    o4 = new PessoaDao();
    o5 = new TrabalhouDao();
    
                initComponents();
        P = new Pessoa();
        // preenchendo os dados padrões do usuario
        resultados = results;
        jTextFieldcpf.setText(resultados.get(0));         P.setCpf(resultados.get(0));
        jTextFieldnome.setText(resultados.get(1));        P.setNome(resultados.get(1));
        jTextFieldsobrenome.setText(resultados.get(2));   P.setSobrenome(resultados.get(2));
        jTextFieldsexo.setText(resultados.get(3));        P.setSexo(resultados.get(3));
        jTextFielddata.setText(resultados.get(4));        P.setDataNascimento(resultados.get(4));
        jTextFieldemail.setText(resultados.get(5));       P.setEmail(resultados.get(5));
        jTextFieldtelefone.setText(resultados.get(6));    P.setTelefone(resultados.get(6));
        jTextFieldendereco.setText(resultados.get(7));    P.setEndereco(resultados.get(7));
        jTextFieldescola.setText(resultados.get(8));      P.setEscolaridade(resultados.get(8));
        jTextFieldingles.setText(resultados.get(9));      P.setNivelIngles(resultados.get(9));
        jTextArea2.setText(resultados.get(10));           P.setDescriçao(resultados.get(10));
        
        
        foto = s.BuscarFoto(resultados.get(1), resultados.get(2));
        //Prenchendo a foto 
        //JOptionPane.showMessageDialog(this, resultados.get(11));
        ImageIcon image = new ImageIcon(foto);
        jLabelFOTO.setIcon(new ImageIcon(image.getImage().getScaledInstance(jLabelFOTO.getWidth(), jLabelFOTO.getHeight(), Image.SCALE_DEFAULT)));
        
        // Separa o Arraylist , trabalho / cursos
        List<String> fazcursos = resultados.subList(12, (resultados.indexOf("&")) ); // +1 pra compensar  posição do &
        List<String> trabalhouEM = resultados.subList((resultados.indexOf("&")+1), resultados.size());
        
        C = new ArrayList<Curso>();
        Ci = new Curso();
        Fi = new Fez();
        F = new ArrayList<Fez>();
        
        qtdCURSOS =  fazcursos.size();
        
        int i =0 ;
        // Atribui os campos de texto com a quantidade de cursos
        if(i < fazcursos.size()) {
            Fi.setCpf(resultados.get(0)); 
            Ci.setNomeCurso(fazcursos.get(i)); Fi.setNomeCurso(fazcursos.get(i)); //Criando um objeto com os cursos caso deseja-se deletar
            jTextFieldcurso1.setText(fazcursos.get(i)); i++;    
            System.out.println("1");     Ci.setFornecedor(fazcursos.get(i));  Fi.setFornecedor(fazcursos.get(i));
            jTextFieldfornece1.setText(fazcursos.get(i));i++;   
            System.out.println("2");     Ci.setHoras(Integer.parseInt(fazcursos.get(i)));
            jTextFieldhoras1.setText(fazcursos.get(i));i++; 
            System.out.println("3");     Ci.setCertificado(fazcursos.get(i));
            jTextFieldcertificado1.setText(fazcursos.get(i)); i++;   
            System.out.println("4");  
            C.add(Ci);
            F.add(Fi);
        }
                if(i < fazcursos.size()) {
                    Fi.setCpf(resultados.get(0)); 
            jTextFieldcurso2.setText(fazcursos.get(i));             Ci.setNomeCurso(fazcursos.get(i));  Fi.setNomeCurso(fazcursos.get(i));   i++; 
            jTextFieldfornece2.setText(fazcursos.get(i));      Ci.setFornecedor(fazcursos.get(i));  Fi.setFornecedor(fazcursos.get(i));  i++; 
            jTextFieldhoras2.setText(fazcursos.get(i));         Ci.setHoras(Integer.parseInt(fazcursos.get(i)));                          i++; 
            jTextFieldcertificado2.setText(fazcursos.get(i));   Ci.setCertificado(fazcursos.get(i));                                       i++; 
        C.add(Ci);
        F.add(Fi);
                }
                        if(i < fazcursos.size()) {
                            Fi.setCpf(resultados.get(0)); 
            jTextFieldcurso3.setText(fazcursos.get(i));        Ci.setNomeCurso(fazcursos.get(i));   Fi.setNomeCurso(fazcursos.get(i));     i++; 
            jTextFieldfornece3.setText(fazcursos.get(i));      Ci.setFornecedor(fazcursos.get(i));    Fi.setFornecedor(fazcursos.get(i));   i++; 
            jTextFieldhoras3.setText(fazcursos.get(i));         Ci.setHoras(Integer.parseInt(fazcursos.get(i)));                             i++;  
            jTextFieldcertificado3.setText(fazcursos.get(i));   Ci.setCertificado(fazcursos.get(i));                                          i++; 
        C.add(Ci);
        F.add(Fi);
                        }
                        if(i < fazcursos.size()) { 
                            Fi.setCpf(resultados.get(0)); 
            jTextFieldcurso4.setText(fazcursos.get(i));        Ci.setNomeCurso(fazcursos.get(i));    Fi.setNomeCurso(fazcursos.get(i));    i++; 
            jTextFieldfornece4.setText(fazcursos.get(i));       Ci.setFornecedor(fazcursos.get(i));    Fi.setFornecedor(fazcursos.get(i));   i++; 
            jTextFieldhoras4.setText(fazcursos.get(i));         Ci.setHoras(Integer.parseInt(fazcursos.get(i)));                              i++; 
            jTextFieldcertificado4.setText(fazcursos.get(i));   Ci.setCertificado(fazcursos.get(i));                                          i++; 
        C.add(Ci);
        F.add(Fi);
                        }
                        
                        Ei = new Emprego();
                        E = new ArrayList<Emprego>();
                        Ti = new TrabalhouEm();
                        TE = new ArrayList<TrabalhouEm>();
                        
        qtdEMPREGO = trabalhouEM.size();
                        
                        i=0;  // Atribui os campos de texto com os empregos do candidato
                                if(i<trabalhouEM.size()){
                        Ti.setCpf(resultados.get(0));
                        Ti.setIdEmp(Integer.parseInt(trabalhouEM.get(i)));
                        Ei.setIdEmprego(Integer.parseInt(trabalhouEM.get(i)));
                        i++ ; // pra pular o ID 
                        jTextFieldempresa1.setText(trabalhouEM.get(i));  Ei.setNomeEmpresa(trabalhouEM.get(i)); i++;
                        jTextFieldcargo1.setText(trabalhouEM.get(i)); Ei.setCargo(trabalhouEM.get(i)); i++; 
                        jTextFieldtempo1.setText(trabalhouEM.get(i)); Ei.setTempo(trabalhouEM.get(i)); i++;  
                        TE.add(Ti); 
                        E.add(Ei);
                        }
                        
                                 if(i<trabalhouEM.size()){
                                     Ti.setCpf(resultados.get(0));
                        Ti.setIdEmp(Integer.parseInt(trabalhouEM.get(i))); Ei.setIdEmprego(Integer.parseInt(trabalhouEM.get(i)));
                        i++ ; // pra pular o ID 
                        jTextFieldempresa2.setText(trabalhouEM.get(i)); Ei.setNomeEmpresa(trabalhouEM.get(i));  i++;
                        jTextFieldcargo2.setText(trabalhouEM.get(i));   Ei.setCargo(trabalhouEM.get(i));         i++;
                        jTextFieldtempo2.setText(trabalhouEM.get(i));   Ei.setTempo(trabalhouEM.get(i));         i++;
                        TE.add(Ti); 
                        E.add(Ei);
                        }
                                 
                                 if(i<trabalhouEM.size()){
                                     Ti.setCpf(resultados.get(0));
                        Ti.setIdEmp(Integer.parseInt(trabalhouEM.get(i))); Ei.setIdEmprego(Integer.parseInt(trabalhouEM.get(i)));
                        i++ ; // pra pular o ID 
                        jTextFieldempresa3.setText(trabalhouEM.get(i));  Ei.setNomeEmpresa(trabalhouEM.get(i));  i++;
                        jTextFieldcargo3.setText(trabalhouEM.get(i));    Ei.setCargo(trabalhouEM.get(i));        i++;
                        jTextFieldtempo3.setText(trabalhouEM.get(i));    Ei.setTempo(trabalhouEM.get(i));       i++;
                        TE.add(Ti); 
                        E.add(Ei);
                        }
                                 
                                          if(i<trabalhouEM.size()){
                                              
                                              Ti.setCpf(resultados.get(0));
                        Ti.setIdEmp(Integer.parseInt(trabalhouEM.get(i))); Ei.setIdEmprego(Integer.parseInt(trabalhouEM.get(i)));
                        i++ ; // pra pular o ID 
                        jTextFieldempresa4.setText(trabalhouEM.get(i));   Ei.setNomeEmpresa(trabalhouEM.get(i));   i++;
                        jTextFieldcargo4.setText(trabalhouEM.get(i));     Ei.setCargo(trabalhouEM.get(i));         i++;
                        jTextFieldtempo4.setText(trabalhouEM.get(i));     Ei.setTempo(trabalhouEM.get(i));        i++;
                        TE.add(Ti); 
                        E.add(Ei);
                        }
    
    
            }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldnome = new javax.swing.JTextField();
        jTextFieldsobrenome = new javax.swing.JTextField();
        jTextFieldsexo = new javax.swing.JTextField();
        jTextFielddata = new javax.swing.JTextField();
        jTextFieldtelefone = new javax.swing.JTextField();
        jTextFieldemail = new javax.swing.JTextField();
        jTextFieldcpf = new javax.swing.JTextField();
        jTextFieldescola = new javax.swing.JTextField();
        jTextFieldingles = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jTextFieldendereco = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jTextFieldempresa1 = new javax.swing.JTextField();
        jTextFieldcargo1 = new javax.swing.JTextField();
        jTextFieldtempo1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jTextFieldempresa2 = new javax.swing.JTextField();
        jTextFieldcargo2 = new javax.swing.JTextField();
        jTextFieldtempo2 = new javax.swing.JTextField();
        jTextFieldtempo3 = new javax.swing.JTextField();
        jTextFieldcargo3 = new javax.swing.JTextField();
        jTextFieldempresa3 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextFieldtempo4 = new javax.swing.JTextField();
        jTextFieldcargo4 = new javax.swing.JTextField();
        jTextFieldempresa4 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldcurso1 = new javax.swing.JTextField();
        jTextFieldfornece1 = new javax.swing.JTextField();
        jTextFieldhoras1 = new javax.swing.JTextField();
        jTextFieldcertificado1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldcurso2 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldfornece2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldhoras2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldcertificado2 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldcurso3 = new javax.swing.JTextField();
        jTextFieldfornece3 = new javax.swing.JTextField();
        jTextFieldhoras3 = new javax.swing.JTextField();
        jTextFieldcertificado3 = new javax.swing.JTextField();
        jTextFieldcurso4 = new javax.swing.JTextField();
        jTextFieldfornece4 = new javax.swing.JTextField();
        jTextFieldhoras4 = new javax.swing.JTextField();
        jTextFieldcertificado4 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabelFOTO = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nome :");

        jLabel2.setText("Sobrenome :");

        jLabel3.setText("Sexo :");

        jLabel4.setText("Data de nascimento :");

        jLabel5.setText("Tefelone :");

        jLabel6.setText("Email :");

        jLabel7.setText("CPF :");

        jLabel8.setText("Escolaridade :");

        jLabel9.setText("Nível de fluência em inglês :");

        jLabel11.setText("Sobre o candidato :");

        jTextFieldnome.setEditable(false);

        jTextFieldsobrenome.setEditable(false);
        jTextFieldsobrenome.setText("                            ");

        jTextFieldsexo.setEditable(false);
        jTextFieldsexo.setText("                         ");

        jTextFielddata.setEditable(false);
        jTextFielddata.setText("                               ");

        jTextFieldtelefone.setEditable(false);
        jTextFieldtelefone.setText("                                       ");

        jTextFieldemail.setEditable(false);

        jTextFieldcpf.setEditable(false);

        jTextFieldescola.setEditable(false);

        jTextFieldingles.setEditable(false);

        jLabel41.setText("Endereco :");

        jTextFieldendereco.setEditable(false);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldemail, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel11)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldnome, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel2))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jTextFielddata)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldtelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(16, 16, 16)
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldescola, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jTextFieldsobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldsexo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel9)))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldingles, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldcpf)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldendereco, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 63, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldsobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldsexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldingles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jTextFielddata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldtelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldescola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldcpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jTextFieldendereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Dados", jPanel2);

        jLabel28.setText("Empresa :");

        jLabel29.setText("Cargo :");

        jLabel30.setText("Tempo :");

        jTextFieldempresa1.setEditable(false);
        jTextFieldempresa1.setText("          ");
        jTextFieldempresa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldempresa1ActionPerformed(evt);
            }
        });

        jTextFieldcargo1.setEditable(false);

        jTextFieldtempo1.setEditable(false);
        jTextFieldtempo1.setText("                ");

        jLabel31.setText("Empresa :");

        jLabel32.setText("Cargo :");

        jLabel33.setText("Tempo :");

        jLabel34.setText("Empresa :");

        jLabel35.setText("Cargo :");

        jLabel36.setText("Tempo :");

        jTextFieldempresa2.setEditable(false);

        jTextFieldcargo2.setEditable(false);
        jTextFieldcargo2.setText("                    ");

        jTextFieldtempo2.setEditable(false);
        jTextFieldtempo2.setText("                ");

        jTextFieldtempo3.setEditable(false);
        jTextFieldtempo3.setText("                ");

        jTextFieldcargo3.setEditable(false);
        jTextFieldcargo3.setText("                    ");

        jTextFieldempresa3.setEditable(false);

        jLabel37.setText("Empresa :");

        jLabel38.setText("Cargo :");

        jLabel39.setText("Tempo :");

        jTextFieldtempo4.setEditable(false);
        jTextFieldtempo4.setText("                ");

        jTextFieldcargo4.setEditable(false);
        jTextFieldcargo4.setText("     ");

        jTextFieldempresa4.setEditable(false);
        jTextFieldempresa4.setText("                                                      ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldempresa3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldempresa1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(jTextFieldempresa2)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldempresa4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldcargo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldtempo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldcargo2, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldtempo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldcargo4))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldcargo3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel39)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldtempo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldtempo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(40, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jTextFieldempresa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldcargo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldtempo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jTextFieldempresa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldcargo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldtempo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(jTextFieldtempo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldcargo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldempresa3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jTextFieldtempo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldcargo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldempresa4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(133, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Experiência Profissional", jPanel4);

        jLabel12.setText("Nome do Curso :");

        jLabel13.setText("Fornecedor :");

        jLabel14.setText("Horas :");

        jLabel15.setText("Certificado :");

        jTextFieldcurso1.setEditable(false);
        jTextFieldcurso1.setText("                                ");

        jTextFieldfornece1.setEditable(false);
        jTextFieldfornece1.setText("                             ");

        jTextFieldhoras1.setEditable(false);
        jTextFieldhoras1.setText("            ");

        jTextFieldcertificado1.setEditable(false);
        jTextFieldcertificado1.setText("           ");

        jLabel16.setText("Nome do Curso :");

        jTextFieldcurso2.setEditable(false);
        jTextFieldcurso2.setText("                                ");

        jLabel17.setText("Fornecedor :");

        jTextFieldfornece2.setEditable(false);
        jTextFieldfornece2.setText("                             ");

        jLabel18.setText("Horas :");

        jTextFieldhoras2.setEditable(false);
        jTextFieldhoras2.setText("            ");

        jLabel19.setText("Certificado :");

        jTextFieldcertificado2.setEditable(false);
        jTextFieldcertificado2.setText("           ");

        jLabel20.setText("Nome do Curso :");

        jLabel21.setText("Fornecedor :");

        jLabel22.setText("Horas :");

        jLabel23.setText("Certificado :");

        jLabel24.setText("Nome do Curso :");

        jLabel25.setText("Fornecedor :");

        jLabel26.setText("Horas :");

        jLabel27.setText("Certificado :");

        jTextFieldcurso3.setEditable(false);
        jTextFieldcurso3.setText("                                             ");

        jTextFieldfornece3.setEditable(false);
        jTextFieldfornece3.setText("                             ");

        jTextFieldhoras3.setEditable(false);

        jTextFieldcertificado3.setEditable(false);
        jTextFieldcertificado3.setText("           ");

        jTextFieldcurso4.setEditable(false);

        jTextFieldfornece4.setEditable(false);

        jTextFieldhoras4.setEditable(false);

        jTextFieldcertificado4.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldcurso4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldcurso3, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldcurso2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldfornece2, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(jTextFieldfornece3)
                            .addComponent(jTextFieldfornece4)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldcurso1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldfornece1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldhoras2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldcertificado2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel26)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldhoras4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel22)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldhoras3)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel23)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldcertificado3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel27)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldcertificado4)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldhoras1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldcertificado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 32, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldcurso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldfornece1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldhoras1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldcertificado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldcertificado2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jTextFieldhoras2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jTextFieldfornece2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jTextFieldcurso2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22)
                            .addComponent(jLabel21)
                            .addComponent(jTextFieldfornece3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldhoras3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldcertificado3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(159, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jTextFieldcurso3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldcurso4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jTextFieldfornece4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(jTextFieldhoras4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)
                            .addComponent(jTextFieldcertificado4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(132, 132, 132))))
        );

        jTabbedPane2.addTab("Cursos", jPanel3);

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel42.setText("FOTO DO CANDIDATO(A)");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jLabelFOTO, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(636, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelFOTO, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel42)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Foto", jPanel1);

        jTabbedPane1.addTab("Informações Pessoais", jTabbedPane2);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Currículo");

        jButton1.setText("Clique para enviar Email para o candidato");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Deletar Currículo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(71, 71, 71)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldempresa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldempresa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldempresa1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        String destinatario = jTextFieldemail.getText() , remetente = "roo7" ;
        String Mensagem = "Você foi selecionado para a vaga de emprego , favor comparecer ao nosso estabelecimento para uma entrevista .";
        String Assunto = "Vaga de Emprego" , Senha="roo7";
                
        
        EnviarEmail e1 = new EnviarEmail();
        try { 
            e1.Envio(destinatario, remetente, Mensagem, Assunto, Senha);
            JOptionPane.showMessageDialog(this, "Email Enviado");
        } catch (EmailException ex) {
            Logger.getLogger(Resultado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String senha = JOptionPane.showInputDialog("Digite a senha do gerente para deletar");
        System.out.println("SENHA : " + senha);
        if(senha.equals("admin")){
            int i = 0;
            while(i < qtdCURSOS){ //Deletando Cursos
            o3.delete(F.get(i));
            i++;
            }
            i=0;
            while(i < qtdCURSOS){ //Deletando Cursos
            o1.delete(C.get(i));
            i++;
            }
            i=0;
            
            while(i < qtdEMPREGO){
                o5.delete(TE.get(i));
                i++;
            }
            i=0;
            while(i < qtdEMPREGO){ 
                o2.delete(E.get(i));
                i++;
            } 
            o4.delete(P); // Deletando pessoa

        }
        else JOptionPane.showMessageDialog(null,"Senha Incorreta , entre em contato com o Gerente"); 
                 
        this.dispose();
    /*public CursoDao o1;
    public EmpregoDao o2;
    public FezDao o3;
    public PessoaDao o4;
    public TrabalhouDao o5;*/
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelFOTO;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextFieldcargo1;
    private javax.swing.JTextField jTextFieldcargo2;
    private javax.swing.JTextField jTextFieldcargo3;
    private javax.swing.JTextField jTextFieldcargo4;
    private javax.swing.JTextField jTextFieldcertificado1;
    private javax.swing.JTextField jTextFieldcertificado2;
    private javax.swing.JTextField jTextFieldcertificado3;
    private javax.swing.JTextField jTextFieldcertificado4;
    private javax.swing.JTextField jTextFieldcpf;
    private javax.swing.JTextField jTextFieldcurso1;
    private javax.swing.JTextField jTextFieldcurso2;
    private javax.swing.JTextField jTextFieldcurso3;
    private javax.swing.JTextField jTextFieldcurso4;
    private javax.swing.JTextField jTextFielddata;
    private javax.swing.JTextField jTextFieldemail;
    private javax.swing.JTextField jTextFieldempresa1;
    private javax.swing.JTextField jTextFieldempresa2;
    private javax.swing.JTextField jTextFieldempresa3;
    private javax.swing.JTextField jTextFieldempresa4;
    private javax.swing.JTextField jTextFieldendereco;
    private javax.swing.JTextField jTextFieldescola;
    private javax.swing.JTextField jTextFieldfornece1;
    private javax.swing.JTextField jTextFieldfornece2;
    private javax.swing.JTextField jTextFieldfornece3;
    private javax.swing.JTextField jTextFieldfornece4;
    private javax.swing.JTextField jTextFieldhoras1;
    private javax.swing.JTextField jTextFieldhoras2;
    private javax.swing.JTextField jTextFieldhoras3;
    private javax.swing.JTextField jTextFieldhoras4;
    private javax.swing.JTextField jTextFieldingles;
    private javax.swing.JTextField jTextFieldnome;
    private javax.swing.JTextField jTextFieldsexo;
    private javax.swing.JTextField jTextFieldsobrenome;
    private javax.swing.JTextField jTextFieldtelefone;
    private javax.swing.JTextField jTextFieldtempo1;
    private javax.swing.JTextField jTextFieldtempo2;
    private javax.swing.JTextField jTextFieldtempo3;
    private javax.swing.JTextField jTextFieldtempo4;
    // End of variables declaration//GEN-END:variables
}
