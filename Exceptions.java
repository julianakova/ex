/*
Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
Фамилия Имя Отчество датарождения номертелефона пол
Форматы данных:
фамилия, имя, отчество - строки
дата_рождения - строка формата dd.mm.yyyy
номер_телефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.
Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.
Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида
<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
Не забудьте закрыть соединение с файлом.
При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.
*/

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Exceptions {
    public static final int fieldsNumber = 6;
    public static void main(String[] args) {
        System.out.println("Введите следующие данные, разделенные пробелом: Фамилия Имя Отчество датарождения номертелефона пол:");
        Scanner scanner = new Scanner(System.in, "ibm866");
        String input = scanner.nextLine();
        scanner.close();

        String[] fields = input.split(" ");
        if (fields.length != fieldsNumber) {
            System.err.println("Неверное количество полей - " + fields.length + ", должно быть - 6!");
        }
        String lastName = fields[0];
        String firstName = fields[1];
        String middleName = fields[2];

        LocalDate birthDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            birthDate = LocalDate.parse(fields[3], formatter);
        } catch (DateTimeException e) {
            System.err.println("Неверный формат даты рождения!");
            return;
        }
        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(fields[4]);
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат номера телефона!");
            return;
        }
        String gender = fields[5];
        if (!"m".equals(gender) && !"f".equals(gender)) {
            System.err.println("Неправильно указан пол контакта, введите f или m!");
            return;
        }

        String fileName = "Exceptions/" + lastName + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(lastName + " " + firstName + " " + middleName + " " +
            birthDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + phoneNumber + " " + gender);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Ошибка заиси!");
        }
    }
}





/*
Задача 2
Напишите программу, которая вычисляет значение выражения intArray[8] / d, гдеintArray- массив целых чисел, а d - делитель.
Программа проверяет, имеется ли в массиве intArray элемент с индексом 8, и если нет, выводит сообщение о невозможности выполнения операции.
Также программа проверяет, равен ли делитель d нулю, и если да, выводит соответствующее сообщение.

На входе:


'1 2 3 4 5 6 7 8 9'
'1'
На выходе:


intArray[8] / d = 9 / 1 = 9.0
9.0
После запуска программы, если не переданы аргументы командной строки, то intArray будет {0, 1, 2, 3, 4, 5, 6, 7, 8, 9} и d будет равно 0. В этом случае результат выражения intArray[8] / d будет бесконечность (так как деление на 0).

Таким образом, программа выведет сообщение:

It's not possible to evaluate the expression - intArray[8] / d as d = 0.
NaN
В случае, если аргументы командной строки переданы, программа преобразует их в массив intArray и d соответственно. Затем вызывается метод expr, и результат выводится на экран, например:

intArray[8] / d = 8 / 3 = 2.6666666666666665
2.6666666666666665
Программа должна выдавать следующие ошибки:

Если длина массива меньше 9:
It's not possible to evaluate the expression - intArray[8] / d as there is no 8th element in the given array.

В этом случае, если массив имеет меньше 9 элементов, программа сообщает, что не удается вычислить выражение, так как в массиве нет 8-го элемента.

Если d равно 0:
It's not possible to evaluate the expression - intArray[8] / d as d = 0.

Если d равно 0, программа сообщает, что не удается вычислить выражение, так как деление на 0 невозможно.

Если условия не выполняются и программа успешно вычисляет результат, то выводится сообщение:
intArray[8] / d = {значение} / {значение} = {результат}

Где {значение} заменяется на соответствующие значения.

Примеры входных данных и соответствующих сообщений об ошибках:

Входные аргументы: 1 2 3 It's not possible to evaluate the expression - intArray[8] / d as there is no 8th element in the given array.

Входные аргументы: 1 2 3 4 5 6 7 8 9 0
It's not possible to evaluate the expression - intArray[8] / d as d = 0.

Входные аргументы: 1 2 3 4 5 6 7 8 9 10
intArray[8] / d = 9 / 10 = 0.9
*/

// public class Exceptions {
//     public static void main(String[] args) {
//         int[] intArray = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
//         double d = 3;
//         if (intArray.length - 1 < 8) {
//             System.out.println("It's not possible to evaluate the expression - intArray[8] / d as there is no 8th element in the given array.");
//             System.out.println("NaN");
//         }
//         else if (d == 0) {
//             System.out.println("It's not possible to evaluate the expression - intArray[8] / d as d = 0.");
//             System.out.println("NaN");
//         }
//         else {
//             double result = intArray[8] / d;
//             System.out.println("intArray[8] / d = " + intArray[8] + " / " + d + " = " +  result);
//             System.out.println(result);
//         }
//     }
// }

/*
Идеальное решение:
import java.util.Arrays;

class Expr {

    public static double expr(int[] intArray, int d) {
        if (intArray.length < 9) {
            System.out.println("It's not possible to evaluate the expression - intArray[8] / d as there is no 8th element in the given array.");
            return Double.NaN;
        } else if (d == 0) {
            System.out.println("It's not possible to evaluate the expression - intArray[8] / d as d = 0.");
            return Double.NaN;
        } else {
            double catchedRes1 = (double) intArray[8] / d;
            System.out.println("intArray[8] / d = " + intArray[8] + " / " + d + " = " + catchedRes1);
            return catchedRes1;
        }
    }
}

public class Printer {
    public static void main(String[] args) {
        int[] intArray;
        int d;

        if (args.length == 0) {
            intArray = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
            d = 0; // По умолчанию используем 0, если аргумент не передан
        } else {
            intArray = Arrays.stream(args[0].split(" ")).mapToInt(Integer::parseInt).toArray();
            d = Integer.parseInt(args[1]); // Можно использовать значение по умолчанию или передать его как аргумент.
        }

        double result = Expr.expr(intArray, d);
        System.out.println(result);
    }
}
*/

/*
Задача 3
Деление
Напишите программу для выполнения арифметической операции деления двух целых чисел a и b.
При этом программа должна проверить, что делитель b не равен нулю, и выполнить деление только в этом случае.
Если b равен нулю, программа должна вернуть результат равный нулю.
После выполнения операции деления, программа также должна вывести сумму чисел a и b с помощью метода printSum.
Если аргументы не переданы через командную строку, используйте значения по умолчанию.
На входе:
'12'
'5'
На выходе:
17
2.4

Моё решение:
class Expr {

    public static double expr(int a, int b) {
 // Введите свое решение ниже
      printSum(a, b);
      if (b != 0) {
        double result = a / (double) b;
        return result;
      }
      else {
        int result = 0;
        return result;
      }
}

    public static void printSum(int a, int b) {
 // Введите свое решение ниже
      System.out.println(a + b);
    }
}

// Не удаляйте этот класс - он нужен для вывода результатов на экран и проверки

public class Printer {
    public static void main(String[] args) {
        int a;
        int b;

        if (args.length == 0) {
            a = 90;
            b = 3; // Default values if no arguments are provided
        } else {
            a = Integer.parseInt(args[0]);
            b = Integer.parseInt(args[1]);
        } 

        double result = Expr.expr(a, b);
        System.out.println(result);
    }
}

Идеальное решение:
class Expr {

    public static double expr(int a, int b) {
        double result = 0.0; // Initialize the result
        if (b != 0) {
            result = (double) a / b; // Perform the division and store the result
        }
        printSum(a, b);
        return result; // Return the result
    }

    public static void printSum(int a, int b) {
        System.out.println(a + b);
    }
}

public class Printer {
    public static void main(String[] args) {
        int a;
        int b;

        if (args.length == 0) {
            a = 90;
            b = 3; // Default values if no arguments are provided
        } else {
            a = Integer.parseInt(args[0]);
            b = Integer.parseInt(args[1]);
        } 

        double result = Expr.expr(a, b);
        System.out.println(result);
    }
}
*/

/*
Задача 4
Cимвол `a`
Напишите программу, которая принимает символ a в качестве аргумента и выполняет следующую проверку:
если символ a равен пробелу '', программа должна выбрасывать исключение с сообщением
"Empty string has been input.".
В противном случае программа должна возвращать сообщение
"Your input was - [символ]", где [символ] заменяется на введенный символ a.
На входе:
'0'
На выходе:
Result: Your input was - 0
*/

// public class Exceptions {
//     public static void main(String[] args) throws Exception {
//         char a;
//         a = 'f';

//         if (a == ' ') {
//             throw new Exception("Empty string has been input.");
//         } else {
//             System.out.println("Your input was - " + a);
//         }
//     }
// }

/*
Идеальное решение:
class Expr {
    public static String expr(char a) throws Exception {

        if (a == ' ') {
            throw new Exception("Empty string has been input.");
        } else {
            return "Your input was - " + a;
        }
    }
}

public class Printer {
    public static void main(String[] args) {
        char a;

        if (args.length == 0) {
            a = 'J'; // Значение по умолчанию, если аргументы не были предоставлены
        } else {
            a = args[0].charAt(0); // Преобразуйте первый аргумент командной строки в символ
        }

        try {
            String result = Expr.expr(a);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
*/