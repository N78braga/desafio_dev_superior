package com.program;

import com.fileutil.Sale;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Desafio_01 {
    public static void main(String[] args)  {

        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();
        File file = new File(path);
        List<Sale> list = new ArrayList<>();

        try (BufferedReader buf = new BufferedReader(new FileReader(file))) {
            String fileLine = buf.readLine();
            while (fileLine != null) {
                String[] fields = fileLine.split(",");
                list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
                fileLine = buf.readLine();

            }
            System.out.println("Cinco primeiras vendas de 2016 de maior preço médio:");
            List<Sale> sale2016 = list.stream().filter(lista -> lista.getYear() == 2016).toList();

            List<Sale> topFiveSales2016 = sale2016.stream().sorted(Comparator.comparingDouble(Sale::averagePrice).reversed()).limit(5).toList();

            double totalSalesByLogan = list.stream().filter(sale -> sale.getSeller().equals("Logan")).filter(sale -> (sale.getMonth() == 1 || sale.getMonth() == 7)).mapToDouble(Sale::getTotal).sum();

            // Mostrando os resultados
            topFiveSales2016.forEach(System.out::println);
            System.out.println("\nTotal vendido pelo vendedor Logan nos meses 1 e 7 de qualquer ano: " + totalSalesByLogan);
        } catch (IOException e) {
            System.out.println("Error: " + file + " O sistema não pode encontrar o arquivo especificado");

        }


    }
}
