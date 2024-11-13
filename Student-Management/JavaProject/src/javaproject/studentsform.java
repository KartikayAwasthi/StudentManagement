package javaproject;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class studentsform extends javax.swing.JFrame 
{
    public studentsform() 
    {
        initComponents();
        fillTable();
     //   MySqlConnection(); //for making database connection
    }
    
    public Connection MySqlConnection()
    {
        Connection conn=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","051242");
          //  JOptionPane.showMessageDialog(null,"Connection Successfull......");
            return conn;
            }
        catch(Exception e)
            {
            JOptionPane.showMessageDialog(null,"Connection failed!!!!!!!!");
            return null;
            }
        
    }
    String photopath="";
    public ImageIcon resetImageSize(String photopath,byte[] photo)
    {
        ImageIcon myPhoto=null;
        if(photopath!=null)
            {
            myPhoto=new ImageIcon(photopath);
            }
        else
            {
            myPhoto=new ImageIcon(photo);
            }
        Image img=myPhoto.getImage();
        Image img1=img.getScaledInstance(jLabel_photo.getWidth(),jLabel_photo.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon ph=new ImageIcon(img1);
        return ph;
        
    }

    //show or add data to Jtable
    public ArrayList<StudentBean> retrieveData(){
        ArrayList<StudentBean> al=null;
        al=new ArrayList<StudentBean>();
        try{
            Connection conn=MySqlConnection();
            String qry="select * from students";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(qry);
            StudentBean student;
            while(rs.next())
            {
                student= new StudentBean(rs.getInt(1),rs.getString("name"),Float.parseFloat(rs.getString(3)),rs.getString(4),rs.getBytes("photo"));
                
                al.add(student);
            }
            
            
            
        }catch(Exception e){
            System.out.println("Error in reteriveData method"+e);
        }
        
    return al;
    }
    
    //fill the table
    public void fillTable()
    { 
        ArrayList<StudentBean> al=retrieveData();
        DefaultTableModel model=(DefaultTableModel)jTable.getModel();
        model.setRowCount(0);
        Object[] row=new Object[4];
        for(int i=0;i<al.size();i++)
        {
            row[0]=al.get(i).getId();
            row[1]=al.get(i).getName();
            row[2]=al.get(i).getFees();
            row[3]=al.get(i).getDob();
            model.addRow(row);
        }
        
        
    }
        public void showItemToFields(int index){
        jTextField_id.setText(Integer.toString(retrieveData().get(index).getId()));
        jTextField_name.setText(retrieveData().get(index).getName());
        jTextField_fees.setText(Float.toString(retrieveData().get(index).getFees()));
        
        try{
            java.util.Date dob=null;
            dob=new SimpleDateFormat("dd-MM-yyyy").parse((String)retrieveData().get(index).getDob());
            jDateChooser_dob.setDate(dob);
        }catch(ParseException e){
            //Logger.getLogger(Student_Window.class.getName()).log(Level.SEVERE,null,e);
            System.out.println("Error in showItemToFields method...");
        }
        jLabel_photo.setIcon(resetImageSize(null, retrieveData().get(index).getPhoto()));  
    
        
        
        
            }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField_id = new javax.swing.JTextField();
        jTextField_name = new javax.swing.JTextField();
        jTextField_fees = new javax.swing.JTextField();
        jDateChooser_dob = new com.toedter.calendar.JDateChooser();
        jLabel_photo = new javax.swing.JLabel();
        jButton_new = new javax.swing.JButton();
        jButton_save = new javax.swing.JButton();
        jButton_update = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jButton_photo = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField_search = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Project on CRUD Operation:Student Management");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Student Information");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Student.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("id:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Student Name:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Fees:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Date of Birth:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Photo:");

        jTextField_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nameActionPerformed(evt);
            }
        });

        jDateChooser_dob.setDateFormatString("dd-MM-yyyy");

        jLabel_photo.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_photo.setOpaque(true);

        jButton_new.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_new.setForeground(new java.awt.Color(51, 51, 255));
        jButton_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check.png"))); // NOI18N
        jButton_new.setText("New");

        jButton_save.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_save.setForeground(new java.awt.Color(51, 51, 255));
        jButton_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_add.png"))); // NOI18N
        jButton_save.setText("Insert/Save");
        jButton_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_saveActionPerformed(evt);
            }
        });

        jButton_update.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_update.setForeground(new java.awt.Color(51, 51, 255));
        jButton_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sync.png"))); // NOI18N
        jButton_update.setText("Update");
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_updateActionPerformed(evt);
            }
        });

        jButton_delete.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_delete.setForeground(new java.awt.Color(51, 51, 255));
        jButton_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/erase.png"))); // NOI18N
        jButton_delete.setText("Delete");
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

        jButton_photo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton_photo.setForeground(new java.awt.Color(51, 51, 255));
        jButton_photo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/folder_red.png"))); // NOI18N
        jButton_photo.setText("Select Photo");
        jButton_photo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_photoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Search Student by Name:");

        jTextField_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_searchKeyReleased(evt);
            }
        });

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Student Name", "Fees", "Date of Birth"
            }
        ));
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(51, 51, 51)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(161, 161, 161)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel7))
                                                    .addGap(17, 17, 17))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jButton_photo)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(66, 66, 66)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_fees, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jDateChooser_dob, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel_photo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField_id, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(294, 294, 294)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jTextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton_save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton_new, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(71, 71, 71))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(42, 42, 42))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_new, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_save, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_fees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDateChooser_dob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton_photo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel_photo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nameActionPerformed
    //for selecting the photo
    private void jButton_photoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_photoActionPerformed
          JFileChooser chooser=new JFileChooser();
          chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
          FileNameExtensionFilter fnef=new FileNameExtensionFilter("*.image","jpg","png","jpeg");
          chooser.addChoosableFileFilter(fnef);
          int ans=chooser.showSaveDialog(null);
          if(ans==JFileChooser.APPROVE_OPTION)
          {
              File selectedPhoto=chooser.getSelectedFile();
              String path=selectedPhoto.getAbsolutePath();
              jLabel_photo.setIcon(resetImageSize(path,null));
              this.photopath=path;
          }
    }//GEN-LAST:event_jButton_photoActionPerformed
    //for save and insert the database record to database
    private void jButton_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_saveActionPerformed
        if((jTextField_id.getText()!=null || jTextField_name!=null || jTextField_fees!=null
                || jDateChooser_dob!=null)&& photopath!=null){
            try{
                Connection conn=MySqlConnection();
                String qry="insert into students(id,name,fees,dob,photo) values (?,?,?,?,?)";
                PreparedStatement ps=conn.prepareStatement(qry);
                ps.setInt(1,Integer.parseInt(jTextField_id.getText()));
                ps.setString(2,jTextField_name.getText());
                ps.setFloat(3,Float.parseFloat(jTextField_fees.getText()));
                
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                String dob1=sdf.format(jDateChooser_dob.getDate());
                ps.setString(4,dob1);
                
                
                InputStream is=new FileInputStream(new File(photopath));
                ps.setBlob(5,is);
                int res=ps.executeUpdate();
                fillTable();
                if(res>=1)
                {
                    JOptionPane.showMessageDialog(null, res+"number of student inserted in database....");
                }
                else
                 {
                      JOptionPane.showMessageDialog(null, res+"student insertion failed!!!!");
                }
                }
            catch(Exception e)
            {
                  JOptionPane.showMessageDialog(null,e);
            }
            jTextField_id.setText("");
            jTextField_name.setText("");
            jTextField_fees.setText("");
            
               
        }
        else
        {
            JOptionPane.showMessageDialog(null, "All Fields are compulsory");
        }
        
            
     
    }//GEN-LAST:event_jButton_saveActionPerformed
    //for updating the students data
    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
         if(jTextField_id!=null || jTextField_name!=null || jTextField_fees !=null
                || jDateChooser_dob!=null){
             String qry=null;
             PreparedStatement ps=null;
             Connection conn=MySqlConnection();
             
             if(photopath==null)
             {
            try{
                qry="update students set name=?,fees=?,dob=? where id=?";
                ps=conn.prepareStatement("");
                
                ps.setString(1,jTextField_name.getText());
                ps.setFloat(2,Float.parseFloat(jTextField_fees.getText()));
                
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                String dob1=sdf.format(jDateChooser_dob.getDate());
                ps.setString(3,dob1);
                //  InputStream is=new FileInputStream(new File(photopath));
              //  ps.setBlob(5,is);
              
              ps.setInt(4,Integer.parseInt(jTextField_id.getText()));
                int res=ps.executeUpdate();
                fillTable();
                if(res>=1)
                {
                    JOptionPane.showMessageDialog(null,"Student Updated Successfully");
                }
                else
                 {
                      JOptionPane.showMessageDialog(null,"Student not updated!!!!");
                }
                }
            catch(Exception e)
            {
                  JOptionPane.showMessageDialog(null,e);
            }
            jTextField_id.setText("");
            jTextField_name.setText("");
            jTextField_fees.setText("");
            
               
        }
        else
            {//if update with photo
            
            try{
                InputStream is=new FileInputStream(new File(photopath));
                qry="update students set name=?,fees=?,dob=?,photo=? where id=?";
                ps=conn.prepareStatement(qry);
                
                ps.setString(1,jTextField_name.getText());
                ps.setFloat(2,Float.parseFloat(jTextField_fees.getText()));
                
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                String dob1=sdf.format(jDateChooser_dob.getDate());
                ps.setString(3,dob1);
                
                ps.setBlob(4, is);
                
              
              ps.setInt(5,Integer.parseInt(jTextField_id.getText()));
                int res=ps.executeUpdate();
                fillTable();
                if(res>=1)
                {
                    JOptionPane.showMessageDialog(null,"Student Updated Successfully");
                }
                else
                 {
                      JOptionPane.showMessageDialog(null,"Student not updated!!!!");
                }
                }
            catch(Exception e)
            {
                  JOptionPane.showMessageDialog(null,e);
            }
            jTextField_id.setText("");
            jTextField_name.setText("");
            jTextField_fees.setText("");
            
               
        
            } 
        
         } else{
             JOptionPane.showMessageDialog(null, "All fields are mandatory");
         }
    }//GEN-LAST:event_jButton_updateActionPerformed
    //for deleting the students
    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        if(jTextField_id.getText().equals(""))
        {
           JOptionPane.showMessageDialog(null,"Please enter Student_Id");
        }
        else{
            
            
             try{
                String qry="delete from students where id=?";
                Connection conn=MySqlConnection();
                PreparedStatement ps=conn.prepareStatement(qry);
                ps.setInt(1,Integer.parseInt(jTextField_id.getText().toString()));
                int res=ps.executeUpdate();
                fillTable();
                if(res>1){
                    JOptionPane.showMessageDialog(null,"Student deletion failed!!!!!");
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"Student deleted Successfully.....");
                }
                
                
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        int ind=jTable.getSelectedRow();
        showItemToFields(ind);
    }//GEN-LAST:event_jTableMouseClicked

    private void jTextField_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_searchKeyReleased
        ArrayList<StudentBean> al=null;
        al=new ArrayList<StudentBean>();
        String val=jTextField_search.getText().toString();
         try{
            Connection conn=MySqlConnection();
            String qry="select * from students where name like '%"+val+"%'";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(qry);
            StudentBean student;
            while(rs.next())
            {
                student= new StudentBean(rs.getInt(1),rs.getString("name"),Float.parseFloat(rs.getString(3)),rs.getString(4),rs.getBytes("photo"));
                
                al.add(student);
            }
            DefaultTableModel model=(DefaultTableModel)jTable.getModel();
        model.setRowCount(0);
        Object[] row=new Object[4];
        for(int i=0;i<al.size();i++)
        {
            row[0]=al.get(i).getId();
            row[1]=al.get(i).getName();
            row[2]=al.get(i).getFees();
            row[3]=al.get(i).getDob();
            model.addRow(row);
        }
        
            
            
            
        }catch(Exception e){
            System.out.println("Error in reteriveData method"+e);
        }
        
    }//GEN-LAST:event_jTextField_searchKeyReleased

    
    public static void main(String []args)//1:16:00 in projct video
    {
        
       java.awt.EventQueue.invokeLater(new Runnable()
       {
            public void run() {
                new studentsform().setVisible(true);
            }
        });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_new;
    private javax.swing.JButton jButton_photo;
    private javax.swing.JButton jButton_save;
    private javax.swing.JButton jButton_update;
    private com.toedter.calendar.JDateChooser jDateChooser_dob;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_photo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextField_fees;
    private javax.swing.JTextField jTextField_id;
    private javax.swing.JTextField jTextField_name;
    private javax.swing.JTextField jTextField_search;
    // End of variables declaration//GEN-END:variables

    
    
}
