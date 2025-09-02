package projetosjavabasico;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraFinanciamento {

    // Classe para cálculos financeiros
    static class CalculadoraFinanceira {

        // Cálculo de parcela pelo Sistema Price
        public static double calcularParcelaPrice(double valorFinanciado, double taxaJurosMensal, int numeroParcelas) {
            if (taxaJurosMensal == 0) {
                return valorFinanciado / numeroParcelas;
            }

            double taxaDecimal = taxaJurosMensal / 100;
            double fator = Math.pow(1 + taxaDecimal, numeroParcelas);
            return valorFinanciado * taxaDecimal * fator / (fator - 1);
        }

        // Cálculo de parcela pelo SAC
        public static List<Double> calcularParcelasSAC(double valorFinanciado, double taxaJurosMensal, int numeroParcelas) {
            List<Double> parcelas = new ArrayList<>();
            double amortizacao = valorFinanciado / numeroParcelas;
            double saldoDevedor = valorFinanciado;

            for (int i = 0; i < numeroParcelas; i++) {
                double juros = saldoDevedor * (taxaJurosMensal / 100);
                double parcela = amortizacao + juros;
                parcelas.add(parcela);
                saldoDevedor -= amortizacao;
            }

            return parcelas;
        }

        // Cálculo de juros compostos
        public static double calcularJurosCompostos(double principal, double taxa, int periodos) {
            return principal * Math.pow(1 + (taxa / 100), periodos) - principal;
        }

        // Correção monetária por inflação
        public static double corrigirValor(double valor, double inflacaoAcumulada) {
            return valor * (1 + inflacaoAcumulada / 100);
        }

        // Cálculo de taxa real (descontando inflação)
        public static double calcularTaxaReal(double taxaNominal, double inflacao) {
            return ((1 + taxaNominal / 100) / (1 + inflacao / 100) - 1) * 100;
        }
    }

    // Classe para dados de inflação
    static class InflacaoBrasil {
        // Dados históricos aproximados de inflação (IPCA) - valores mensais em %
        private static final double[] INFLACAO_MENSAL = {
                0.62, 0.83, 0.71, 0.61, 0.38, 0.26, 0.12, 0.23, 0.35, 0.59, 0.68, 0.56, // 2023
                0.41, 0.54, 0.71, 0.67, 0.47, 0.23, 0.01, -0.36, -0.29, 0.18, 0.41, 0.62  // 2022
        };

        public static double getInflacaoAcumulada(int meses) {
            if (meses <= 0 || meses > INFLACAO_MENSAL.length) {
                return 0;
            }

            double acumulado = 1;
            for (int i = 0; i < meses; i++) {
                acumulado *= (1 + INFLACAO_MENSAL[i] / 100);
            }

            return (acumulado - 1) * 100;
        }

        public static double getInflacaoMensal(int mes) {
            if (mes < 1 || mes > 12) return 0;
            return INFLACAO_MENSAL[mes - 1];
        }
    }

    // Método para calcular financiamento
    private static void calcularFinanciamento(Scanner scanner) {
        System.out.println("\n=== CÁLCULO DE FINANCIAMENTO ===");

        System.out.print("Valor financiado: R$ ");
        double valor = scanner.nextDouble();

        System.out.print("Taxa de juros mensal (%): ");
        double taxa = scanner.nextDouble();

        System.out.print("Número de parcelas: ");
        int parcelas = scanner.nextInt();

        System.out.print("Sistema (1-Price, 2-SAC): ");
        int sistema = scanner.nextInt();

        System.out.print("Corrigir pela inflação? (1-Sim, 2-Não): ");
        int corrigir = scanner.nextInt();

        double inflacaoAcumulada = 0;
        if (corrigir == 1) {
            inflacaoAcumulada = InflacaoBrasil.getInflacaoAcumulada(parcelas);
            System.out.printf("Inflação acumulada estimada: %.2f%%\n", inflacaoAcumulada);
        }

        System.out.println("\n=== RESULTADOS ===");
        System.out.printf("Valor financiado: R$ %.2f\n", valor);
        System.out.printf("Taxa de juros: %.2f%% ao mês\n", taxa);
        System.out.printf("Número de parcelas: %d\n", parcelas);

        if (sistema == 1) {
            double parcela = CalculadoraFinanceira.calcularParcelaPrice(valor, taxa, parcelas);
            double totalPago = parcela * parcelas;
            double totalJuros = totalPago - valor;

            System.out.printf("Valor da parcela: R$ %.2f\n", parcela);
            System.out.printf("Total pago: R$ %.2f\n", totalPago);
            System.out.printf("Total de juros: R$ %.2f\n", totalJuros);

            if (corrigir == 1) {
                double valorCorrigido = CalculadoraFinanceira.corrigirValor(valor, inflacaoAcumulada);
                System.out.printf("Valor corrigido pela inflação: R$ %.2f\n", valorCorrigido);
            }

        } else if (sistema == 2) {
            List<Double> parcelasSAC = CalculadoraFinanceira.calcularParcelasSAC(valor, taxa, parcelas);
            double totalPago = 0;

            System.out.println("\nTabela SAC:");
            System.out.println("Parcela\tValor");
            for (int i = 0; i < parcelasSAC.size(); i++) {
                System.out.printf("%d\tR$ %.2f\n", i + 1, parcelasSAC.get(i));
                totalPago += parcelasSAC.get(i);
            }

            System.out.printf("\nTotal pago: R$ %.2f\n", totalPago);
            System.out.printf("Total de juros: R$ %.2f\n", totalPago - valor);
        }
    }

    // Método para correção por inflação
    private static void corrigirInflacao(Scanner scanner) {
        System.out.println("\n=== CORREÇÃO POR INFLAÇÃO ===");

        System.out.print("Valor a ser corrigido: R$ ");
        double valor = scanner.nextDouble();

        System.out.print("Período em meses: ");
        int meses = scanner.nextInt();

        double inflacao = InflacaoBrasil.getInflacaoAcumulada(meses);
        double valorCorrigido = CalculadoraFinanceira.corrigirValor(valor, inflacao);

        System.out.println("\n=== RESULTADO ===");
        System.out.printf("Valor original: R$ %.2f\n", valor);
        System.out.printf("Inflação acumulada: %.2f%%\n", inflacao);
        System.out.printf("Valor corrigido: R$ %.2f\n", valorCorrigido);
        System.out.printf("Perda do poder de compra: R$ %.2f\n", valorCorrigido - valor);
    }

    // Método para comparar financiamentos
    private static void compararFinanciamentos(Scanner scanner) {
        System.out.println("\n=== COMPARAÇÃO DE FINANCIAMENTOS ===");

        System.out.print("Valor financiado: R$ ");
        double valor = scanner.nextDouble();

        System.out.print("Número de parcelas: ");
        int parcelas = scanner.nextInt();

        System.out.print("Taxa do 1º financiamento (%): ");
        double taxa1 = scanner.nextDouble();

        System.out.print("Taxa do 2º financiamento (%): ");
        double taxa2 = scanner.nextDouble();

        double parcela1 = CalculadoraFinanceira.calcularParcelaPrice(valor, taxa1, parcelas);
        double parcela2 = CalculadoraFinanceira.calcularParcelaPrice(valor, taxa2, parcelas);

        double total1 = parcela1 * parcelas;
        double total2 = parcela2 * parcelas;

        System.out.println("\n=== COMPARAÇÃO ===");
        System.out.printf("Financiamento 1 (%.2f%%):\n", taxa1);
        System.out.printf("  Parcela: R$ %.2f\n", parcela1);
        System.out.printf("  Total: R$ %.2f\n", total1);

        System.out.printf("Financiamento 2 (%.2f%%):\n", taxa2);
        System.out.printf("  Parcela: R$ %.2f\n", parcela2);
        System.out.printf("  Total: R$ %.2f\n", total2);

        System.out.printf("\nDiferença: R$ %.2f\n", Math.abs(total1 - total2));
        System.out.printf("Economia mensal: R$ %.2f\n", Math.abs(parcela1 - parcela2));
    }

    // Método principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== CALCULADORA DE FINANCIAMENTOS E INFLAÇÃO ===");
        System.out.println("1 - Calcular Financiamento");
        System.out.println("2 - Corrigir Valor pela Inflação");
        System.out.println("3 - Comparar Financiamentos");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                calcularFinanciamento(scanner);
                break;
            case 2:
                corrigirInflacao(scanner);
                break;
            case 3:
                compararFinanciamentos(scanner);
                break;
            default:
                System.out.println("Opção inválida!");
        }

        scanner.close();
    }
}