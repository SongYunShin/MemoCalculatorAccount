package simplecalmeacc;

import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class simplememo extends JPanel{
    
        private static final long serialVersionUID = 1L;
        private JButton jBtnAddRow = null;
        private JButton jBtnDelRow = null;
        private JTable table;
        private JScrollPane scrollPane;

        public simplememo() {
            setLayout(null);
            String colNames[] = {"날짜","내용"};
            DefaultTableModel model = new DefaultTableModel(colNames, 0);
            table = new JTable(model);
            table.addMouseListener(new JTableMouseListener());
            scrollPane = new JScrollPane(table);              // 테이블에 스크롤 생기게 하기
            scrollPane.setSize(500, 200);
            add(scrollPane);        
            initialize();    
        }

        private class JTableMouseListener implements MouseListener{
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                
                JTable jtable = (JTable)e.getSource();
                int row = jtable.getSelectedRow();
                int col = jtable.getSelectedColumn();
                DefaultTableModel model = (DefaultTableModel)table.getModel();
                
                System.out.println(model.getValueAt(row, 0));   // 눌려진 행의 부분에서 0번째 값을 출력 
                System.out.println(model.getValueAt(row, col));   // 눌려진 행과 열에 해당하는 선택된 데이터 하나 출력
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {    
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
            }
        }
        
        private void initialize() {
            // 테이블 새로 한줄 추가하는 부분
            jBtnAddRow = new JButton();
            jBtnAddRow.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jBtnAddRow();
                }
            });
            jBtnAddRow.setBounds(100,222,120, 25);
            jBtnAddRow.setText("추가");
            add(jBtnAddRow);
            
            // 선택된 테이블 한줄 삭제하는 부분
            jBtnDelRow = new JButton();
            jBtnDelRow.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    jBtnDelRow();
                }
            });
            jBtnDelRow.setBounds(new Rectangle(250, 222, 120, 25));
            jBtnDelRow.setText("삭제");
            add(jBtnDelRow);
        }

        void jBtnAddRow(){
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            model.addRow(new String[]{"",""});            // 새테이블의 초기값
        }

        void jBtnDelRow(){
            int row = table.getSelectedRow();
            if(row<0) return; // 선택이 안된 상태면 -1리턴
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            //System.out.println(model.getValueAt(row, 0));
            model.removeRow(row);
        }


    public static void main(String[] args) {
        
    	simplememo panel = new simplememo();
        JFrame win = new JFrame();
        win.add(panel);
        win.setSize(510,300);
        win.setVisible(true);
    }
}