package proxydemo;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Override
    @Cacheable(name = "students")
    public List<StudentDTO> findStudents() {
        System.out.println("Calculating students...");

        return List.of(
                new StudentDTO("Pesho", 26),
                new StudentDTO("Anna", 24));
    }
}
