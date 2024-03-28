/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tbs.companyclient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author tom
 */
public class CompanyRESTfulClient {
  private static String BASE_URI = "http://localhost:8080/CompanyRESTfulService/resources/company";
  private static Client client = ClientBuilder.newClient();
  private static Gson gson = new Gson();

  // create a department

  public static void createDepartment(Department department) {
    // Invoke POST request to create a new department
    Response response = client
            .target(BASE_URI)
            .path("departments")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(department, MediaType.APPLICATION_JSON));
    // Check if request was successful
    if (response.getStatus() != ErrorCodes.CREATED) System.err.println("Failed to create new department. Status code: " + response.getStatus());
  }
 
  // read all departments
    
  public static List<Department> readAllDepartments() {
    String response = client
            .target(BASE_URI)
            .path("departments")
            .request(MediaType.APPLICATION_JSON)
            .get(String.class);
    List<Department> departments = convertJsonToDepartmentList(response);
    return departments;
  }

  // bulk update departments
  
  public static void bulkUpdateDepartments(List<Department> departments) {
    // Invoke PUT request to bulk update departments
    Response response = client
            .target(BASE_URI)
            .path("departments")
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(departments, MediaType.APPLICATION_JSON));
    // Check if request was successful
    if (response.getStatus() != ErrorCodes.OK) System.err.println("Failed to update departments. Status code: " + response.getStatus());
  }
  
  // delete all departments
  
  public static void deleteAllDepartments() {
    Response response = client
            .target(BASE_URI)
            .path("departments")
            .request(MediaType.APPLICATION_JSON)
            .delete();
    int statusCode = response.getStatus();
    if (statusCode != ErrorCodes.NO_CONTENT) System.out.println("Failed to delete department. Status code: " + statusCode);
  }
  
  // return department with ID
  
  public static Department readDepartment(int id) {
    Department department = client
            .target(BASE_URI)
            .path("departments/" + id)
            .request(MediaType.APPLICATION_JSON)
            .get(Department.class);
    return department;
  }
  
  // update department with ID
  
  public static void updateDepartment(Department department) {
    // Invoke PUT request to update a department
    Response response = client
            .target(BASE_URI)
            .path("departments/" + department.id)
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(department, MediaType.APPLICATION_JSON));
    // Check if request was successful
    if (response.getStatus() != ErrorCodes.OK) System.err.println("Failed to update departments. Status code: " + response.getStatus());
  }
  
  // ID delete department with ID
   
  public static void deleteDepartment(int id) {
    Response response = client
            .target(BASE_URI)
            .path("departments/" + id)
            .request(MediaType.APPLICATION_JSON)
            .delete();
    int statusCode = response.getStatus();
    if (statusCode != ErrorCodes.NO_CONTENT) System.out.println("Failed to delete department. Status code: " + statusCode);
  }
  
  // create an employee

  public static void createEmployee(Employee employee) {
    // Invoke POST request to create a new employee
    Response response = client
            .target(BASE_URI)
            .path("employees")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(employee, MediaType.APPLICATION_JSON));
    // Check if request was successful
    if (response.getStatus() != ErrorCodes.CREATED) System.err.println("Failed to create new employee. Status code: " + response.getStatus());
  }

  // return all employees
  
  public static List<Employee> readAllEmployees() {
    String response = client
            .target(BASE_URI)
            .path("employees")
            .request(MediaType.APPLICATION_JSON)
            .get(String.class);
    List<Employee> employees = convertJsonToEmployeeList(response);
    return employees;
  }

  // bulk update employees
    
  public static void bulkUpdateEmployees(List<Employee> employees) {
    // Invoke PUT request to update employees
    Response response = client
            .target(BASE_URI)
            .path("employees")
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(employees, MediaType.APPLICATION_JSON));
    // Check if request was successful
    if (response.getStatus() != ErrorCodes.OK) System.err.println("Failed to update employees. Status code: " + response.getStatus());
  }

  // delete all employees
  
  public static void deleteAllEmployees() {
    Response response = client
            .target(BASE_URI)
            .path("employees")
            .request(MediaType.APPLICATION_JSON)
            .delete();
    int statusCode = response.getStatus();
    if (statusCode != ErrorCodes.NO_CONTENT) System.out.println("Failed to delete employees. Status code: " + statusCode);
  }

  // return employee with ID

  public static Employee readEmployee(int id) {
    Employee employee = client
            .target(BASE_URI)
            .path("employees/" + String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .get(Employee.class);
    return employee;
  }

  // update employee with ID
  
  public static int updateEmployee(Employee employee) {
    Response response = client
            .target(BASE_URI)
            .path("employees/" + employee.id)
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(employee, MediaType.APPLICATION_JSON));
    return response.getStatus();
  }
  
  // delete employee with ID
    
  public static void deleteEmployee(int id) {
    Response response = client
            .target(BASE_URI)
            .path("employees/" + id)
            .request(MediaType.APPLICATION_JSON)
            .delete();
    int statusCode = response.getStatus();
    if (statusCode != ErrorCodes.NO_CONTENT) System.out.println("Failed to delete employees. Status code: " + statusCode);
  }

  // return employees from department with ID
    
  public static List<Employee> readEmployeesFromDepartment(int departmentId) {
    String response = client
            .target(BASE_URI)
            .path("departments/" + departmentId + "/employees")
            .request(MediaType.APPLICATION_JSON)
            .get(String.class);
    List<Employee> employees = convertJsonToEmployeeList(response);
    return employees;
  }

  // delete employees from department with ID

  public static void deleteAllEmployeesFromDepartment(int departmentId) {
    // Invoke DELETE request to delete employees
    Response response = client
            .target(BASE_URI)
            .path("departments/" + departmentId + "/employees")
            .request(MediaType.APPLICATION_JSON)
            .delete();
    int statusCode = response.getStatus();
    if (statusCode != ErrorCodes.NO_CONTENT) System.out.println("Failed to delete employees. Status code: " + statusCode);
  }
  
  private static List<Department> convertJsonToDepartmentList(String jsonString) {
    Type departmentListType = new TypeToken<List<Department>>() {}.getType();
    return gson.fromJson(jsonString, departmentListType);
  }
  
  private static List<Employee> convertJsonToEmployeeList(String jsonString) {
    Type employeeListType = new TypeToken<List<Employee>>() {}.getType();
    return gson.fromJson(jsonString, employeeListType);
  }
  
  public static void main(String[] args) {
    List<Department> departments = CompanyRESTfulClient.readAllDepartments();
    List<Employee> employees = CompanyRESTfulClient.readAllEmployees();
    System.out.println(departments);
    System.out.println(employees);
  }
}
