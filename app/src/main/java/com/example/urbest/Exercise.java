package com.example.urbest;

import java.util.ArrayList;

public class Exercise {
    private ArrayList<Integer> images = new ArrayList<Integer>(){
        {
            add(R.drawable.cardio);
            add(R.drawable.abs);
            add(R.drawable.stretch);
            add(R.drawable.leg);
            add(R.drawable.upper);
        }
    };
    public ArrayList<Integer> getImages() {
        return images;
    }
    private ArrayList<String> headers = new ArrayList<String>(){
        {
            add("10 Mins Cardio");
            add("5 Mins Abs Workout");
            add("10 Mins Stretching");
            add("5 Mins Leg Workout");
            add("7 Mins Upper Body Workout");
        }
    };
    public ArrayList<String> getHeaders() {
        return headers;
    }
    private ArrayList<String> steps = new ArrayList<String>(){
        {
            add("Rest 15 seconds between the steps !\n\n" +
                    "45 seconds Jumping Jacks\n\n" +
                    "45 seconds Mountain Climbers\n\n" +
                    "45 seconds Box Jumps\n\n" +
                    "45 seconds Plank Hip Dip\n\n" +
                    "45 seconds Lateral Lunge\n\n" +
                    "45 seconds Forearm Plank\n\n" +
                    "45 seconds Squat Thrust\n\n" +
                    "45 seconds High Knees\n\n" +
                    "45 seconds Standing Side Bend\n\n" +
                    "45 seconds Reverse Lunge\n\n");
            add("Rest 15 seconds between the steps !\n\n" +
                    "45 seconds Plank Up\n\n" +
                    "45 seconds V-Up\n\n" +
                    "45 seconds Mountain Climber Twist\n\n"+
                    "45 seconds Plank With T Rotation\n\n" +
                    "45 seconds Core Roll Up\n\n");
            add("Rest 15 seconds between the steps !\n\n" +
                    "45 seconds Shoulder Rolls\n\n" +
                    "45 seconds Shoulder and Chest Stretch\n\n" +
                    "45 seconds T-Stretch\n\n"+
                    "45 seconds Kneeling Stretch\n\n" +
                    "45 seconds Lower Back\n\n" +
                    "45 seconds Roll Over\n\n" +
                    "45 seconds Forward Fold\n\n" +
                    "45 seconds Hamstrings\n\n"+
                    "45 seconds Pelvic Tilts\n\n" +
                    "45 seconds Breathing\n\n");
            add("Rest 15 seconds between the steps !\n\n" +
                    "45 seconds Squat\n\n" +
                    "45 seconds Sumo Squat\n\n" +
                    "45 seconds Lunge\n\n"+
                    "45 seconds Donkey Kick\n\n" +
                    "45 seconds Glute Bridge\n\n");
            add("Rest 15 seconds between the steps !\n\n" +
                    "45 seconds Alternating Punches\n\n" +
                    "45 seconds Side and Lateral Arm Raises\n\n" +
                    "45 seconds Shoulder Press\n\n"+
                    "45 seconds Press Ups\n\n" +
                    "45 seconds Floor Tricep Dips\n\n"+
                    "45 seconds Inchworms\n\n" +
                    "45 seconds Plank Raise\n\n");
        }
    };
    public ArrayList<String> getSteps() {
        return steps;
    }
}
