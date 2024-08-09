import proxydemo.CacheableInvocationHandler;
import proxydemo.StudentService;
import proxydemo.StudentServiceImpl;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        StudentService studentService = studentService();

        studentService.findStudents();
        studentService.findStudents();
        studentService.findStudents().forEach(System.out::println);

    }

    private static StudentService studentService() {
        return (StudentService) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[] { StudentService.class },
                new CacheableInvocationHandler(new StudentServiceImpl()));
    }
}
