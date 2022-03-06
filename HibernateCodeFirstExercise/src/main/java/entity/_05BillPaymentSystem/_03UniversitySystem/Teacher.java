package entity._05BillPaymentSystem._03UniversitySystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "teachers")
public class Teacher extends Person {


    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "salary_per_hour")
    private BigDecimal salaryPerHour;

    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;
}
