import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LessonReflection {


    public static void main(String[] args) {


        try {
            //Перебираем все классы и ищем нужные
            Files.find(Paths.get("./out"),
                    Integer.MAX_VALUE,
                    (filePath, fileAttr) -> {
                        String[] fileInfo = filePath.getFileName().toString().split("\\.");
                        return fileInfo.length > 1 && fileInfo[1].equalsIgnoreCase("class");
                    }).forEach(path -> {
                try {

                    String fileName = path.getFileName().toString().replaceAll("\\.class", "");
                    Class ch = URLClassLoader.newInstance(new URL[]{new File(path.getParent().toString()).toURL()}).loadClass(fileName);
                    for (Method m : ch.getDeclaredMethods()) {
                        if (m.getName() == "calculate" && m.getParameterCount() == 4 && m.getReturnType() == Integer.TYPE) {
                            int a = 3;
                            int b = 2;
                            int c = 6;
                            int d = 2;
                            m.setAccessible(true);
                            if ((int)m.invoke(ch, a, b, c, d) == (a * (b + (c / d)))) {
                                System.out.println("Верная функция " + m.getName());
                            } else {
                                System.out.println("Не верная функция " + m.getName());
                            }
                        }
                        if (m.getName() == "calculate" && m.getParameterCount() == 4 && m.getReturnType() == Float.TYPE) {
                            float a = 3.4f;
                            float b = 10.5f;
                            float c = 6f;
                            float d = 2f;
                            m.setAccessible(true);
                            if ((float)m.invoke(ch, a, b, c, d) == (a * (b + (c / d)))) {
                                System.out.println("Верная функция " + m.getName());
                            } else {
                                System.out.println("Не верная функция " + m.getName());
                            }
                        }
                        if (m.getName() == "checkTwoNumbers" && m.getParameterCount() == 2) {
                            int first = 3;
                            int second = 10;
                            m.setAccessible(true);
                            if ((boolean)m.invoke(ch, first, second) == true) {
                                System.out.println("Верная функция " + m.getName());
                            } else {
                                System.out.println("Не верная функция " + m.getName());
                            }
                        }

                        if (m.getName() == "isNegative" && m.getParameterCount() == 1) {
                            int variable = 3;
                            m.setAccessible(true);
                            if ((boolean)m.invoke(ch, variable) == false) {
                                System.out.println("Верная функция " + m.getName());
                            } else {
                                System.out.println("Не верная функция " + m.getName());
                            }
                        }


                        if (m.getName() == "isLeapYear" && m.getParameterCount() == 1) {
                            int year = 2024;
                            m.setAccessible(true);
                            if ((boolean)m.invoke(ch, year) == true) {
                                System.out.println("Верная функция " + m.getName());
                            } else {
                                System.out.println("Не верная функция " + m.getName());
                            }
                        }
                    }

                } catch (ClassNotFoundException | MalformedURLException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
