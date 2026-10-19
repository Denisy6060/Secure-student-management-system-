package com.example.studentmanagement.student;

import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    public Student create(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (studentRepository.existsByRegistrationNumber(student.getRegistrationNumber())) {
            throw new IllegalArgumentException("Registration number already exists");
        }
        return studentRepository.save(student);
    }

    public Student update(Long id, Student data) {
        Student student = findById(id);
        student.setFirstName(data.getFirstName());
        student.setLastName(data.getLastName());
        student.setEmail(data.getEmail());
        student.setRegistrationNumber(data.getRegistrationNumber());
        student.setCourse(data.getCourse());
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.delete(findById(id));
    }
}
