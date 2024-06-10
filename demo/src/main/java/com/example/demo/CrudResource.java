package com.example.demo;



import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/crudApp")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CrudResource {

@Inject StudentDAO studentDAO;

    @GET
    @Path("/hello")
    public String getHello() {
        return "Hello World!";
    }


    @GET
    @Path("students/{id}")
    public Response getStudentById(@PathParam("id")Long id){


    Student student = studentDAO.findById(id);
    if (student != null ){
        StudentDTO studentDTO = StudentDTO.mapToDTO(student);
        return Response.ok(studentDTO).build();

    }
    return Response.status(Response.Status.NOT_FOUND).entity("Student with id " + id + " not found").build();
    }

    @POST
    @Path("students/add")
    public Response addStudent(StudentDTO studentDTO){


        if(studentDTO != null ){
            Student student = StudentDTO.mapToEntity(studentDTO);
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
            return Response.ok("Student was successfully deleted").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Student is null").build();
    }

    @PUT
    @Path("students/update/{id}")
    public Response updateStudent(@PathParam("id") Long id, StudentDTO studentDTO){

    Student updateStudent = studentDAO.findById(id);
    Student student = StudentDTO.mapToEntity(studentDTO);

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
