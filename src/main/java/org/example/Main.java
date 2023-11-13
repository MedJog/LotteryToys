package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<Toy> lotteryToys = generateLotteryToys(20);
        System.out.println("Список всех игрушек для розыгрыша:");
        displayInfo(lotteryToys);
        ArrayList <Toy> lotteryPrizes = toyRaffle(20, lotteryToys);
        System.out.println();
        System.out.println("Список выпавших игрушек:");
        displayInfo(lotteryPrizes);
        String path = "toys.txt";
        getToy(path, lotteryPrizes);

    }
    static Random random = new Random();
    // создание игрушки
    static Toy generateToy(){
        Integer [] weight = new Integer[] {20, 40, 60};
        String [] names = new String[] {"Мишка", "Кукла", "Машина", "Самолёт", "Конструктор", "Настольная игра"};
        Toy toy = new Toy(1, names[random.nextInt(names.length)], weight[random.nextInt(weight.length)]);
        return toy;
    }
    // создание списка с игрушками для розыгрыша
    static ArrayList<Toy> generateLotteryToys(int count){
        ArrayList<Toy> toys= new ArrayList<>();
        for (int i = 0; i < count; i++){
            toys.add(generateToy());
        }
        return toys;
    }
    // розыгрыш игрушек
    static ArrayList<Toy> toyRaffle(int count, ArrayList<Toy> toys) {
        ArrayList<Toy> prizes = new ArrayList<>();
        // Toy prizeToy = null;
        while (count > 0) {
            Toy prizeToy = null;
            if (toys.isEmpty()) {
                System.out.println("Барабан пустой.");
                break;
            }
            System.out.println("*****************");
            System.out.println("Вращаем барабан!");
            int casus = random.nextInt(1, 101);
            //System.out.println("casus " + casus);
            for (Toy toy : toys) {
                if (casus <= 20 && toy.getWeight() == 20) {
                    prizes.add(toy);
                    prizeToy = toy;
                    System.out.println("Выпала игрушка: " + toy.getId() + " " + toy.getName());
                    toys.remove(toy);
                    break;
                } else if (casus > 20 && casus <= 40 && toy.getWeight() == 40) {
                    prizes.add(toy);
                    prizeToy = toy;
                    System.out.println("Выпала игрушка: " + toy.getId() + " " + toy.getName());
                    toys.remove(toy);
                    break;
                } else if (casus > 40 && toy.getWeight() == 60) {
                    prizes.add(toy);
                    prizeToy = toy;
                    System.out.println("Выпала игрушка: " + toy.getId() + " " + toy.getName());
                    toys.remove(toy);
                    break;
                }
            } if (prizeToy == null) System.out.println("Увы, пустышка!");
            count--;
        }
        System.out.println("*****************");
        System.out.println("Розыгрыш окончен.");
        return prizes;
    }
    // получение игрушки
    static void getToy (String path, ArrayList<Toy> prizes) throws Exception {
        int i = 0;
        int countToys = prizes.size();
        while (i < countToys){
            try {
                FileWriter writer = new FileWriter(path, true);
                writer.append(String.valueOf(prizes.get(0)));
                writer.flush();
                writer.close();
                prizes.remove(0);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            i++;
        }
        //System.out.println(prizes.size());
        System.out.println("Все игрушки выданы победителям!");
    }
    // вывод списка на дисплей
    static void displayInfo(ArrayList<Toy> toys){
        for (Toy toy : toys) {
            System.out.print(toy);
        }
    }

}