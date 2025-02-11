package com.afs.employee;

import com.afs.employee.demo.Employee;
import com.afs.employee.demo.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;


@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    MockMvc client;

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void cleanRepository() {
        employeeRepository.clearAll();
    }
//    [
//        {
//            "id": 1,
//                "name": "Jason",
//                "age": 18,
//                "gender": "male",
//                "salary": 50000
//        }
//    ]
    @Test
    void should_get_all_employees_when_perform_get_given_employees() throws Exception {
        //given
        employeeRepository.create(new Employee(10, "Susan", 22, "Female", 10000));
        employeeRepository.create(new Employee(10, "Bob", 23, "Male", 9000));

        //when
        client.perform(MockMvcRequestBuilders.get("/employees"))
                // 1. assert response status
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 2. assert response date
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Susan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Bob"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name" , containsInAnyOrder("Bob", "Susan")) )
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(22))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gender").value("Female"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].salary").value(10000));



        //then
    } @Test
    void should_get_employees_by_id_when_perform_get_by_id_given_employees() throws Exception {
        //given
        Employee susan = employeeRepository.create(new Employee(10, "Susan", 22, "Female", 10000));
        employeeRepository.create(new Employee(10, "Bob", 23, "Male", 9000));

        //when
        client.perform(MockMvcRequestBuilders.get("/employees/{id}", susan.getId()))
                // 1. assert response status
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 2. assert response date
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Susan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(22))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("Female"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(10000));



        //then
    }


}
