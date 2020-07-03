/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deber6_avltree;

/**
 *
 * @author EAMT
 */
public interface iStudent extends Comparable<iStudent> {
    //gets
    public String getName();
    public String getLastName();
    public Integer getAge();
    public Integer getCode();
    public String getSemester();
    public Integer getCredits();
    
    //Set methods
    public void setName(String pName);
    public void setLastName(String pLastName);
    public void setAge(int pAge);
    public void setCode(int pCode);
    public void setSemester(String pSemester);
    public void setCredits(int pCredits);
  
}