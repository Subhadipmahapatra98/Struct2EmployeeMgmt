/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.DepartmentService;
import com.exavalu.services.EmployeeService;
import com.exavalu.services.LoginService;
import com.exavalu.services.RoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class Employee extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phone;
    private int status;
    private String gender ;
    private String age ;
    private String departmentName ;
    private String roleName ;
    private String basicSalary ;
    private String carAllowance;
    private String departmentId;
    private String roleId;

    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        map = (ApplicationMap) application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap = (SessionMap) session;
    }

    public String doLogin() throws Exception {
        String result = "FAILURE";

        boolean success = LoginService.getInstance().doLogin(email, password);

        if (success) {
            System.out.println("returning Success from doLogin method");
            result = "SUCCESS";
            ArrayList empList = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("Loggedin", this);
            sessionMap.put("empList", empList);
            
        } else {
            System.out.println("returning Failure from doLogin method");
        }

        return result;
    }
    
    
    
    
    public String doLogout(){
        
        String result="SUCCESS";
        sessionMap.clear();
        return result;
    }
    
    public String editEmployee(){
        
        String result = "FAILURE";
        ArrayList deptList = DepartmentService.getAllDepartments();
        Department dept = new Department();
        Role role = new Role();
        ArrayList roleList = RoleService.getAllRoles();
        
        // Now we need to retrieve this Employee from the DB and return to frontend
        Employee emp = EmployeeService.getInstance().getEmployee(this.employeeId);
        System.err.println("First Name"+emp.firstName);
        sessionMap.put("Emp", emp);
        sessionMap.put("DeptList", deptList);
        sessionMap.put("dept", dept);
        sessionMap.put("roleList", roleList);
        sessionMap.put("role", role);
        
        result="SUCCESS";
        return result;
        
    }
    
    public String searchEmployee(){
        String result="FAILURE";
        ArrayList empList  = EmployeeService.getInstance().SearchEmployees(firstName, lastName, gender, departmentName, roleName);
        
        if(!empList.isEmpty()){
            sessionMap.put("EmpList", empList);
            result="SUCCESS";
        }
        else{
            ArrayList deptList = DepartmentService.getAllDepartments();
            ArrayList roleList = RoleService.getAllRoles();
            Employee emp1 = new Employee();
            sessionMap.put("Emp", emp1);
            sessionMap.put("DeptList", deptList);
            sessionMap.put("RoleList", roleList);
        }
        return result;
    }
    
    public String addEmployee(){
        String res = "FAILURE";
        Employee emp = new Employee();
        emp.setFirstName(firstName);
        emp.setAddress(address);
        emp.setAge(age);
        emp.setDepartmentId(departmentId);
        emp.setCarAllowance(carAllowance);
        emp.setGender(gender);
        emp.setBasicSalary(basicSalary);
        emp.setRoleId(roleId);
        emp.setPhone(phone);
        emp.setLastName(lastName);
        
        boolean result = EmployeeService.AddEmployee(emp);
        if(result){
            ArrayList empList = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("empList", empList);
            String successMsg = "Employee is Successfully Added!";
            sessionMap.put("SuccessMsg",successMsg);
            System.out.println(successMsg);
            res="SUCCESS";
        }
        else{
            String errorMsg = "Data entry is wrong!! Please try again!";
            sessionMap.put("ErrorMsg",errorMsg);
            
        }
        return res;
    }
    public String saveEmployee(){
        String result = "FAILURE";
        Employee emp = new Employee();
        emp.setFirstName(firstName);
        emp.setAddress(address);
        emp.setAge(age);
        emp.setDepartmentId(departmentId);
        emp.setCarAllowance(carAllowance);
        emp.setGender(gender);
        emp.setBasicSalary(basicSalary);
        emp.setRoleId(roleId);
        emp.setPhone(phone);
        emp.setLastName(lastName);
        boolean res = EmployeeService.saveEmployee(emp,employeeId);
         if(res){
            ArrayList empList = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("empList", empList);
            result="SUCCESS";
        }
        else{
            sessionMap.put("Emp", emp);
        }
        return result;
    }
    
         

    /**
     * @return the employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    
    
    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return the basicSalary
     */
    public String getBasicSalary() {
        return basicSalary;
    }

    /**
     * @param basicSalary the basicSalary to set
     */
    public void setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
    }

    /**
     * @return the carAllowance
     */
    public String getCarAllowance() {
        return carAllowance;
    }

    /**
     * @param carAllowance the carAllowance to set
     */
    public void setCarAllowance(String carAllowance) {
        this.carAllowance = carAllowance;
    }

    /**
     * @return the departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
