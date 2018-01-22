import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    ///
    // Будет переспрашивать, пока не получит int число на вводе
    private static Integer getInt(int a, int b) {
        // Результат, флаг выхода из цикла, сканер
        int res = 0;
        Boolean fExit = false;

        // Пока флаг выхода не включен
        while (fExit == false) {
            // Создаём объект сканнер
            Scanner scan = new Scanner(System.in);

            // Есть ли Int?
            if (scan.hasNextInt()) {
                // Да, считываем
                res = scan.nextInt();
                if ((res >= a) && (res <=b)) {
                    // Заканчиваем ввод
                    fExit = true;
                } else {
                    // Нету Int
                    System.out.println("Error: число должно быть " + a + " <= Число <= " + b);
                    System.out.println("Введите число заново");
                }
            } else {
                // Нету Int
                System.out.println("Error: число Int не найдено");
                System.out.println("Введите число заново");
            }
        }
        return res;
    }

    public static void main(String[] args) {
        boolean flagExit = false;
        // Рабочая матрица
        ArrayList<ArrayList<Double>> Matr = null;
        System.out.println("Здравствуйте!");
        while (flagExit == false) {
            System.out.println("Выберете пункт меню:");
            System.out.println("1.Метод Гаусса");
            System.out.println("2.Вычисление определителя");
            System.out.println("3.Быстрое возведение в степень");
            System.out.println("4.Выход");

            int vybor = getInt(1,4);

            if (vybor == 4) {
                flagExit = true;
                return;
            }

            System.out.println("Выберете тип ввода:");
            System.out.println("1.Рандом");
            System.out.println("2.Ручной");

            int tipVvoda = getInt(1,2);

            switch (vybor) {
                case 1: {
                    // Ввод для Гаусса
                    if (tipVvoda == 1) {
                        System.out.println("Введите высоту от 1 до 50");
                        int height = getInt(1,50);
                        int width = height + 1;
                        Matr = generateMatr(width,height);
                    } else {
                        Matr = inputMatr(true);
                    }
                    Gauss1.outputMatr(Matr);

                    // Берём функцию из библиотеки
                    Gauss1 objGauss = new Gauss1();
                    // Выполняем метод
                    Gauss1.metGauss(Matr);
                    break;
                }
                case 2: {
                    // Ввод для определителя
                    if (tipVvoda == 1) {
                        System.out.println("Введите высоту от 1 до 50");
                        int height = getInt(1,50);
                        int width = height;
                        Matr = generateMatr(width,height);
                    } else {
                        Matr = inputMatr(false);
                    }
                    Gauss1.outputMatr(Matr);

                    // Берём функцию из библиотеки
                    Op1 objOp = new Op1();
                    // Выполняем метод
                    double res = Op1.op(Matr);
                    System.out.println("Определитель равен " + res);
                    break;
                }
                case 3: {
                    // Ввод для степени
                    if (tipVvoda == 1) {
                        System.out.println("Введите высоту от 1 до 50");
                        int height = getInt(1,50);
                        int width = height;
                        Matr = generateMatr(width,height);
                    } else {
                        Matr = inputMatr(false);
                    }
                    System.out.println("Введите степень от 1 до 50");
                    int k = getInt(1,50);

                    Gauss1.outputMatr(Matr);

                    // Берём функцию из библиотеки
                    Stepen1 objStepen = new Stepen1();
                    // Выполняем метод
                    Matr = objStepen.stepenMatr(Matr, k);
                    System.out.println("Результат");
                    Gauss1.outputMatr(Matr);
                    break;
                }
            }

        }
    }

    ///
    // ГЕНЕРАЦИЯ РАНДОМНОЙ МАТРИЦЫ С ЗАДАНЫМ РАЗМЕРОМ
    public static ArrayList<ArrayList<Double>> generateMatr(int width, int height) {
        // Создаём объект двумерной матрицы
        ArrayList<ArrayList<Double>> newM = new ArrayList<>();
        // Создаём объект рандомизатор
        Random vr = new Random();
        // Автозаполнение матрицы
        for (int j = 0; j < height; j++) {
            newM.add(new ArrayList<Double>());
            for (int i = 0; i < width; i++) {
                newM.get(j).add(vr.nextDouble());
            }
        }
        // Возвращаем объект заполненой матрицы
        return newM;
    }

    ///
    // Ручной ввод матрицы
    private static ArrayList<ArrayList<Double>> inputMatr(boolean gauss) {
        // Определим размер матрицы
        int height = 0;
        int width = 0;
        if (gauss) {
            System.out.println("Введите высоту от 1 до 4:");
            height = getInt(1,4);
            width = height + 1;
        } else {
            System.out.println("Введите высоту от 1 до 6:");
            height = getInt(1,6);
            width = height;
        }

        // Создаём объект двумерной матрицы
        ArrayList<ArrayList<Double>> newM = new ArrayList<>();
        // Автозаполнение матрицы
        for (int j = 0; j < height; j++) {
            newM.add(new ArrayList<Double>());
            for (int i = 0; i < width; i++) {
                System.out.print("Строка " + j + " Элемент " + i + ":");
                newM.get(j).add(getDouble());
            }
        }
        // Возвращаем объект заполненой матрицы
        return newM;
    }

    ///
    // Будет переспрашивать, пока не получит double число на вводе
    private static Double getDouble() {
        // Результат, флаг выхода из цикла, сканер
        double res = 0;
        Boolean fExit = false;

        // Пока флаг выхода не включен
        while (fExit == false) {
            // Создаём объект сканнер
            Scanner scan = new Scanner(System.in);

            // Есть ли double?
            if (scan.hasNextDouble()) {
                // Да, считываем
                res = scan.nextDouble();
                // И заканчиваем ввод
                fExit = true;
            } else {
                // Нету Double
                System.out.println("Error: число Double не найдено");
                System.out.println("Введите число заново");
            }
        }
        return res;
    }
}
