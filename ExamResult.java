/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ahmad_120220184;

import java.util.ArrayList;

/**
 * @author HP
 */
public class ExamResult {

    private Exam e;
    private Student s;
    private int mark;
    private ArrayList<String> studentAnswers;
    private String sname;
    private String ename;
    private int uniId;
    private int totalMark;

    public ExamResult(Exam e, Student s, int mark, ArrayList<String> studentAnswers) {
        this.e = e;
        this.s = s;
        this.sname = s.getName();
        this.uniId = s.getUniversityId();
        this.totalMark = e.getTotalMarks();
        this.ename = e.getName();
        this.mark = mark;
        this.studentAnswers = studentAnswers;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getUniId() {
        return uniId;
    }

    public void setUniId(int uniId) {
        this.uniId = uniId;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    public ExamResult(Exam e, Student s, int mark) {
        this.e = e;
        this.s = s;
        this.sname = s.getName();
        this.uniId = s.getUniversityId();
        this.totalMark = e.getTotalMarks();
        this.ename = e.getName();
        this.mark = mark;
    }

    public String getStudentAnswers(int i) {
        return studentAnswers.get(i);
    }

    public void setStudentAnswers(ArrayList<String> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }

    public Exam getE() {
        return e;
    }

    public void setE(Exam e) {
        this.e = e;
    }

    public Student getS() {
        return s;
    }

    public void setS(Student s) {
        this.s = s;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

}

