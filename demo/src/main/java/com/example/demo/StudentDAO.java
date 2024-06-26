package com.example.demo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StudentDAO {


    @PersistenceContext(unitName = "my-pu")
    EntityManager entityManager;

    public List getAll (){
        return entityManager.createNamedQuery("Student.findAll", Student.class).getResultList();
    }

    public Student findById (Long id){
        return entityManager.find(Student.class, id);
    }

    public void update (Student student){
         entityManager.merge(student);
    }

    public void delete (Student student){

        if (!entityManager.contains(student)) {
            student = entityManager.merge(student);
        }

        entityManager.remove(student);
    }

    public void create (Student student){
        entityManager.persist(student);
    }


}
