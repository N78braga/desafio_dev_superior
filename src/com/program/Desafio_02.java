package com.program;

import com.fileutil.Sale;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Desafio_02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("Entre com o caminho do arquivo: ");
        String path = sc.nextLine();
        File file = new File(path);

        List<Sale> sale = new ArrayList<>();

        try (BufferedReader buf = new BufferedReader(new FileReader(file))) {
            String lineReade = buf.readLine();

            while (lineReade != null) {
                String[] line = lineReade.split(",");

                sale.add(new Sale(Integer.parseInt(line[0]), Integer.parseInt(line[1]), line[2],
                        Integer.parseInt(line[3]), Double.parseDouble(line[4])));
                lineReade = buf.readLine();
            }
            Map<String, Double> totalBySeller = sale.stream()
                    .collect(Collectors.groupingBy(Sale::getSeller,
                            Collectors.summingDouble(Sale::getTotal)));
            totalBySeller.forEach((seller, total) ->
                    System.out.printf(seller + " - R$ %.2f%n", total));


        } catch (IOException e) {
            System.out.println("Error: " + file + " O sistema não pode encontrar o arquivo especificado");
        }


    }
}