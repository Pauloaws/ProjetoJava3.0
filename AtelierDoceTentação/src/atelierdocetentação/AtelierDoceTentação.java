/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atelierdocetentação;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class AtelierDoceTentação extends JFrame {
    private List<Produto> produtos;
    private DefaultTableModel model;
    private JTable table;
    private JTextField campoId;
    private JTextField campoNome;
    private JTextField campoPreco;

    public AtelierDoceTentação() {
        produtos = new ArrayList<>();

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Preço");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        JLabel labelId = new JLabel("ID:");
        campoId = new JTextField(10);

        JLabel labelNome = new JLabel("Nome:");
        campoNome = new JTextField(20);

        JLabel labelPreco = new JLabel("Preço:");
        campoPreco = new JTextField(10);

        JButton botaoCriar = new JButton("Criar");
        botaoCriar.addActionListener((ActionEvent e) -> {
            int id = Integer.parseInt(campoId.getText());
            String nome = campoNome.getText();
            double preco = Double.parseDouble(campoPreco.getText());
            
            Produto produto = new Produto(id, nome, preco);
            produtos.add(produto);
            
            model.addRow(new Object[]{id, nome, preco});
            
            JOptionPane.showMessageDialog(AtelierDoceTentação.this, "Produto criado com sucesso!");
        });

        JButton botaoConsultar = new JButton("Consultar");
        botaoConsultar.addActionListener((ActionEvent e) -> {
            int id = Integer.parseInt(campoId.getText());
            Produto produtoEncontrado = null;
            
            for (Produto produto : produtos) {
                if (produto.getId() == id) {
                    produtoEncontrado = produto;
                    break;
                }
            }
            
            if (produtoEncontrado != null) {
                campoNome.setText(produtoEncontrado.getNome());
                campoPreco.setText(String.valueOf(produtoEncontrado.getPreco()));
            } else {
                JOptionPane.showMessageDialog(AtelierDoceTentação.this, "Produto não encontrado!");
            }
        });

        JButton botaoAtualizar = new JButton("Atualizar");
        botaoAtualizar.addActionListener((ActionEvent e) -> {
            int id = Integer.parseInt(campoId.getText());
            String nome = campoNome.getText();
            double preco = Double.parseDouble(campoPreco.getText());
            
            boolean produtoEncontrado = false;
            
            for (int i = 0; i < produtos.size(); i++) {
                Produto produto = produtos.get(i);
                if (produto.getId() == id) {
                    produto.setNome(nome);
                    produto.setPreco(preco);
                    produtoEncontrado = true;
                    model.setValueAt(nome, i, 1);
                    model.setValueAt(preco, i, 2);
                    break;
                }
            }
            
            if (produtoEncontrado) {
                JOptionPane.showMessageDialog(AtelierDoceTentação.this, "Produto atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(AtelierDoceTentação.this, "Produto não encontrado!");
            }
        });

        JButton botaoDeletar = new JButton("Deletar");
        botaoDeletar.addActionListener((ActionEvent e) -> {
            int id = Integer.parseInt(campoId.getText());
            int index = -1;
            
            for (int i = 0; i < produtos.size(); i++) {
                Produto produto = produtos.get(i);
                if (produto.getId() == id) {
                    index = i;
                    break;
                }
            }
            
            if (index != -1) {
                produtos.remove(index);
                model.removeRow(index);
                campoId.setText("");
                campoNome.setText("");
                campoPreco.setText("");
                JOptionPane.showMessageDialog(AtelierDoceTentação.this, "Produto deletado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(AtelierDoceTentação.this, "Produto não encontrado!");
            }
        });

        JPanel panelBotoes = new JPanel();
        panelBotoes.add(botaoCriar);
        panelBotoes.add(botaoConsultar);
        panelBotoes.add(botaoAtualizar);
        panelBotoes.add(botaoDeletar);

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(3, 2));
        panelCampos.add(labelId);
        panelCampos.add(campoId);
        panelCampos.add(labelNome);
        panelCampos.add(campoNome);
        panelCampos.add(labelPreco);
        panelCampos.add(campoPreco);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.NORTH);
        getContentPane().add(panelCampos, BorderLayout.CENTER);
        getContentPane().add(panelBotoes, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AtelierDoceTentação::new);
    }
}