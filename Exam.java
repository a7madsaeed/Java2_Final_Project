/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ahmad_120220184;

import java.util.ArrayList;

/**
 * @author HP
 */
public class Exam {

    private static int id;
    private int Eid;
    private String name;
    private int numberOfQuestions;
    private int minPassAverage;
    private int totalMarks;
    private boolean YesOrNo;
    private boolean Multiplechoice;
    private boolean Fillblank;
    private ArrayList<Question> questions;
    private Teacher t;
    private String tname;


    public Exam(String name, int numberOfQuestions, int minPassAverage, boolean YesOrNo,
                boolean Multiplechoice, boolean Fillblank, ArrayList<Question> y, Teacher t) {

        this.Eid = id++;
        this.name = name;
        this.numberOfQuestions = numberOfQuestions;
        this.minPassAverage = minPassAverage;
        this.t = t;
        this.tname = this.t.getName();
        this.YesOrNo = YesOrNo;
        this.Multiplechoice = Multiplechoice;
        this.Fillblank = Fillblank;

        ArrayList<Question> q = new ArrayList<>();
        int ynCount = 0, mcCount = 0, fbCount = 0;
        ArrayList<YesOrNo> ynQ = new ArrayList<>();
        ArrayList<Multiplechoice> mcQ = new ArrayList<>();
        ArrayList<Fillblank> fbQ = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            if (y.get(i) instanceof YesOrNo) {
                ynQ.add((YesOrNo) y.get(i));
                ynCount++;
            } else if (y.get(i) instanceof Multiplechoice) {
                mcQ.add((Multiplechoice) y.get(i));
                mcCount++;
            } else if (y.get(i) instanceof Fillblank) {
                fbQ.add((Fillblank) y.get(i));
                fbCount++;
            }
        }
        boolean valid = false;
        if (YesOrNo && Multiplechoice && Fillblank && (ynCount + mcCount + fbCount) >= numberOfQuestions) {
            valid = true;
        } else if (YesOrNo && Multiplechoice && (ynCount + mcCount) >= numberOfQuestions) {
            valid = true;
        } else if (YesOrNo && Fillblank && (ynCount + fbCount) >= numberOfQuestions) {
            valid = true;
        } else if (Multiplechoice && Fillblank && (mcCount + fbCount) >= numberOfQuestions) {
            valid = true;
        } else if (YesOrNo && (ynCount) >= numberOfQuestions) {
            valid = true;
        } else if (Multiplechoice && (mcCount) >= numberOfQuestions) {
            valid = true;
        } else if (Fillblank && (fbCount) >= numberOfQuestions) {
            valid = true;
        }

        int i = 0;
        while (q.size() < numberOfQuestions) {

            if (YesOrNo && q.size() < numberOfQuestions && valid) {
                q.add(ynQ.get(i));
            }
            if (Multiplechoice && q.size() < numberOfQuestions && valid) {
                q.add(mcQ.get(i));
            }
            if (Fillblank && q.size() < numberOfQuestions && valid) {
                q.add(fbQ.get(i));
            }
            i++;
        }
        int totalMarks = 0;
        for (int j = 0; j < q.size(); j++) {
            totalMarks += q.get(j).getMark();
        }

        this.totalMarks = totalMarks;
        this.questions = q;
//        ArrayList<Question> availableQuestions = new ArrayList<>(y);
//        Class<? extends Question> lastQuestionType = null;
//
//        boolean includeYesOrNo = true;
//        boolean includeMultipleChoice = true;
//        boolean includeFillBlank = true;
//
//        while (q.size() < numberOfQuestions && !availableQuestions.isEmpty()) {
//            int randomIndex = (int) (Math.random() * availableQuestions.size());
//            Question currentQuestion = availableQuestions.get(randomIndex);
//
//
//            if ((currentQuestion instanceof YesOrNo && includeYesOrNo)
//                    || (currentQuestion instanceof Multiplechoice && includeMultipleChoice)
//                    || (currentQuestion instanceof Fillblank && includeFillBlank)) {
//
//
//                if (lastQuestionType != null && lastQuestionType.equals(currentQuestion.getClass())) {
//                    continue;
//                }
//
//                q.add(currentQuestion);
//                lastQuestionType = currentQuestion.getClass();
//            }
//
//            availableQuestions.remove(randomIndex);
//            if (availableQuestions.isEmpty()) {
//                availableQuestions.addAll(y);
//                lastQuestionType = null;
//            }
//        }
//
//        int totalMarks = 0;
//        for (int i = 0; i < q.size(); i++) {
//            totalMarks += q.get(i).getMark();
//        }
//
//        this.totalMarks = totalMarks;
//        this.questions = q;
//        ----------------------------------------------------------------------------------------
//        ArrayList<Question> q = new ArrayList<>();
//        ArrayList<Question> availableQuestions = new ArrayList<>(y);
//        Class<? extends Question> lastQuestionType = null;
//
//        while (q.size() < numberOfQuestions && !availableQuestions.isEmpty()) {
//            int randomIndex = (int) (Math.random() * availableQuestions.size());
//            Question currentQuestion = availableQuestions.get(randomIndex);
//
//            if ((currentQuestion instanceof YesOrNo && YesOrNo)
//                    || (currentQuestion instanceof Multiplechoice && Multiplechoice)
//                    || (currentQuestion instanceof Fillblank && Fillblank)) {
//
//                // التحقق من أن نوع السؤال الحالي مختلف عن نوع السؤال السابق
//                if (lastQuestionType != null && lastQuestionType.equals(currentQuestion.getClass())) {
//                    continue;
//                }
//
//                q.add(currentQuestion);
//                lastQuestionType = currentQuestion.getClass();
//            }
//
//            availableQuestions.remove(randomIndex);
//            if (availableQuestions.isEmpty()) {
//                availableQuestions.addAll(y);
//                lastQuestionType = null;
//            }
//        }
//        for (int i = 0; i < q.size(); i++) {
//            this.totalMarks += q.get(i).getMark();
//        }
//        this.questions = q;

    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Exam.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getEid() {
        return Eid;
    }

    public void setEid(int Eid) {
        this.Eid = Eid;
    }

    public String getName() {
        return name;
    }

    public Teacher getT() {
        return t;
    }

    public void setT(Teacher t) {
        this.t = t;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getMinPassAverage() {
        return minPassAverage;
    }

    public void setMinPassAverage(int minPassAverage) {
        this.minPassAverage = minPassAverage;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public boolean isYesOrNo() {
        return YesOrNo;
    }

    public void setYesOrNo(boolean YesOrNo) {
        this.YesOrNo = YesOrNo;
    }

    public boolean isMultiplechoice() {
        return Multiplechoice;
    }

    public void setMultiplechoice(boolean Multiplechoice) {
        this.Multiplechoice = Multiplechoice;
    }

    public boolean isFillblank() {
        return Fillblank;
    }

    public void setFillblank(boolean Fillblank) {
        this.Fillblank = Fillblank;
    }

    public Question getQuestions(int i) {
        return (questions.get(i));
    }

    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

}
