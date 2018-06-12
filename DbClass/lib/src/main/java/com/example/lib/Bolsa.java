package com.example.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by renanbenattidias on 01/04/18.
 */

public class Bolsa {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int numeroPropostas = Integer.parseInt(in.readLine());
        while (numeroPropostas != 0) {
            BinaryTree compra = new BinaryTree();
            BinaryTree venda = new BinaryTree();
            double lucro = 0;
            for (int i = 0; i < numeroPropostas; i++) {
                String[] vetSplit = in.readLine().split(" ");
                System.out.println(vetSplit[0] + " " + vetSplit[1]);
                double valor = Double.parseDouble(vetSplit[1]);
                Node no;
                if (vetSplit[0].equals("C")) {
                    if (venda.getSize() != 0) {
                        no = venda.getMin();
                        double menorVenda = no.getValue();
                        if (menorVenda <= valor) {
                            lucro += valor - menorVenda;
                            venda.remove(no);
                        } else {
                            compra.add(valor);
                        }
                    } else {
                        compra.add(valor);
                    }
                } else {
                    if (compra.getSize() != 0) {
                        no = compra.getMax();
                        double maiorCompra = no.getValue();
                        if (maiorCompra >= valor) {
                            lucro += maiorCompra - valor;
                            compra.remove(no);
                        } else {
                            venda.add(valor);
                        }
                    } else {
                        venda.add(valor);
                    }
                }
            }
            out.write(String.valueOf(lucro));
            out.write("\n");
            numeroPropostas = Integer.parseInt(in.readLine());
            System.out.println("Propostas: " + numeroPropostas);
        }
        out.flush();
        out.close();
    }
}
