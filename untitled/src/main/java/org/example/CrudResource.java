package org.example;



import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("crudApp")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CrudResource {

@Inject StudentDAO studentDAO;


    @GET
    @Path("students/{id}")
    public Response getStudentById(@PathParam("id")Long id){


    Student student = studentDAO.findById(id);
    if (student != null ){
        return Response.ok(student).build();

    }
    return Response.status(Response.Status.NOT_FOUND).entity("Student with id " + id + " not found").build();
    }

    @POST
    @Path("students/add")
    public Response addStudent(Student student){
        if(student != null ){
            studentDAO.create(student);
            return Response.ok("Student was successfully added").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Student is null").build();
    }

    @DELETE
    @Path("students/delete/{id}")
    public Response deleteStudent(@PathParam("id")long id){
        Student student = studentDAO.findById(id);

        if(student != null){
            studentDAO.delete(student);
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Student is null").build();
    }

    @PUT
    @Path("students/update/{id}")
    public Response updateStudent(@PathParam("id") Long id, Student student){
    Student updateStudent = studentDAO.findById(id);


    if(updateStudent != null) {
        updateStudent.setAge(student.getAge());
        updateStudent.setEmail(student.getEmail());
        updateStudent.setFieldOfStudy(student.getFieldOfStudy());
        updateStudent.setName(student.getName());

        studentDAO.update(updateStudent);
        return Response.ok("Student was successfully updated").build();
    }
        return Response.status(Response.Status.BAD_REQUEST).entity("Student is null").build();

    }


    
    }
