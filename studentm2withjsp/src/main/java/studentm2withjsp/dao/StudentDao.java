package studentm2withjsp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import studentm2withjsp.dto.Student;

public class StudentDao {
	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("vinod").createEntityManager();

	}

	public List<Student> getAllStudents() {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createQuery("select s from Student s");

		return query.getResultList();
	}

	public void saveStudent(Student student) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(student);
		entityTransaction.commit();

	}

	public void deleteStudent(int id) {
	
		EntityManager entityManager = getEntityManager();
		Student dbstudent = entityManager.find(Student.class, id);
		// no need to check bcz after present only we are clicking on it
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(dbstudent);
		entityTransaction.commit();
	}

	public Student findStudentById(int id) {
		EntityManager entityManager = getEntityManager();
		Student dbstudent = entityManager.find(Student.class, id);
		// no need to check bcz after present only we are clicking on it
		
		return dbstudent;
	}
	

	public void updateStudent(Student student) {
		EntityManager entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(student);
		entityTransaction.commit();
	}

}