package com.enlyee.smartgotchi;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Pet {
    private static final int SHOT_COST = 100;
    private String name;
    private int age;
    private int hunger;
    private int thirst;
    private int health;
    private int death;
    private int happy;
    private int ageTime;
    private int awayTime;
    private int randomEventPercentage;
    private String creature;
    private ArrayList<String> events;

    public String getName(){return name;}
    public int getAge(){return age;}
    public int getAgeTime(){return ageTime;}
    public int getHunger(){return hunger;}
    public int getThirst(){return thirst;}
    public int getHealth(){return health;}
    public int getDeath(){return death;}
    public int getHappy(){return happy;}
    public int getRandomEvent(){return randomEventPercentage;}
    public String getCreature(){return creature;}

    public Pet(){}

    public Pet(String newName, int newAge, int newHunger, int newThirst, int newHealth,
               int newDeath, int newHappy, int newAgeTime, int newAwayTime, int newRandomEventPercentage, String newCreature){
        name = newName;
        age = newAge;
        hunger = newHunger;
        thirst = newThirst;
        health = newHealth;
        death = newDeath;
        happy = newHappy;
        ageTime = newAgeTime;
        awayTime = newAwayTime;
        randomEventPercentage = newRandomEventPercentage;
        events = new ArrayList<>();
        creature = newCreature;

        //0
        events.add(name + " упал с лестницы " + "\nСчастье (-10) и Здоровье (-15)");
        //1
        events.add(name + " поел! <3 <3 <3 " + "\nСчастье (+15)");
        //2
        events.add(name + " получил 2 по физике " + "\nСчастье (-10)");
        //3
        events.add(name + " подрался с другим питомцем! " + "\nСчастье (-10) и Здоровье (-15)");
        //4
        events.add(name + " сильно спешил в школу и упал в грязь " + "\nСчастье (-10)");
        //5
        events.add(name + " съел червяка с земли " + "\nГолод (+2) и Здоровье (-2)");
        //6
        events.add(name + " нашел себе нового друга!! <3 <3 " + "\nСчастье (+20)");
        //7
        events.add(name + " рагромил всю твою квартиру и был наказан " + "\nСчастье (-15)");
        //8
        events.add(name + " Наелся всего того, что ты оставил на кухонном столе " + "\nЗдоровье (-15)");
        //9
        events.add(name + " потерялся на улице и был найден грязным и голодным " + "\nСчастье (-15) и Здоровье (-10)");
        //10
        events.add(name + " обидел своего друга " + "\nСчастье (-10)");
        //11
        events.add(name + " проспал очень важную встречу " + "\nСчастье (-10)");
        //12
        events.add(name + " споткнулся о лестницу( " + "\nЗдоровье (-2)");
        //13
        events.add(name + " забыл дорогу в школу (потом вспомнил, но всё равно грустно) " + "\nСчастье (-5)");
        //14
        events.add(name + " делал тортик и комнату заполнил прекраснейший запах " + "\nСчастье (+5)");
        //15
        events.add(name + " пытался выучить новый язык, но устал " + "\nСчастье (-5)");
        //16
        events.add(name + " учил новый язык и совсем не устал " + "\nСчастье (+10)");
        //17
        events.add(name + " взламывал Пентагон, но правительство взломало его..." + "\nСчастье (-20)");
        //18
        events.add(name + " получил от вас подарочек <3 <3 " + "\nСчастье (+10)");
        //19
        events.add(name + " на улице его виртуального мира прекрасная погода! " + "\nСчастье (+10)");
        //20
        events.add(name + " погода в его виртуальном мире просто ужаснейшая " + "\nСчастье (-10)");
        //21
        events.add(name + " сильно наелся и теперь у него болит живот" + "\nСчастье (-20) и Здоровье (-20)");

    }
    public void updateRandomEvent(){
        randomEventPercentage--;
        if(randomEventPercentage <= 0){
            randomEventPercentage = 0;
        }
    }


    public void updateHunger(int calories, boolean treat){
        if(hunger + calories > 100) {
            hunger = 100;
        }
        else if(hunger + calories <= 0){
            hunger = 0;
            updateHealth(calories);
        }
        else{
            if(treat){
                hunger += calories;
                updateHappy(5, false);
                updateHealth(-2);
            }
            else{
                hunger += calories;
            }
        }
    }

    public void updateThirst(int water){
        if(thirst + water > 100)
            thirst = 100;
        else if(thirst + water <= 0) {
            thirst = 0;
            updateHealth(water);
        }
        else
            thirst += water;
    }

    public void updateHealth(int heal){
        if(health + heal > 100)
            health = 100;
        else if(health + heal <= 0) {
            updateDeath(heal);
            health = 0;
        }
        else
            health += heal;
    }
    public void updateDeath(int major){
        major = abs(major);
        if(death + major > 100) {
            death = 100;
        }
        else if(death + major <= 0){
            death = 0;
        } else {
            death += major;
        }
    }

    public void updateHappy(int gameCost, boolean isShot){
        if(happy + gameCost > 100){
            happy = 100;
        } else if(happy + gameCost <= 0){
            if(!isShot)
                updateHealth(-(abs(happy + gameCost)));
            happy = 0;
        } else {
            happy += gameCost;
        }
    }

    public void giveShot(){
        updateHealth(SHOT_COST);
        updateHappy(-60, true);
    }

    public void updateAwayTime(int time){
        if((time - awayTime) / 900 > 0){
            int hoursAway = (time - awayTime) / 900;
            updateHunger(-hoursAway, false);
            updateThirst(-hoursAway);
            updateHappy(-hoursAway, false);
        }
        awayTime = time;
    }

    public void ageUp(int time){
        if(time - ageTime >= 86400){
            age += (time - ageTime) / 86400;
            ageTime = time;
        }
    }

    public boolean isDead(){
        return death >= 100;
    }

    public void play(){
        updateHappy(20, false);
    }

    public String randomEvent(){
        int eventNumber = (int) (Math.random() * events.size());
        if(eventNumber == 0){
            updateHappy(-10, false);
            updateHealth(-15);
        } else if(eventNumber == 1){
            updateHappy(15, false);
        } else if(eventNumber == 2){
            updateHappy(-10, false);
        } else if(eventNumber == 3){
            updateHappy(-10, false);
            updateHealth(-15);
        } else if(eventNumber == 4){
            updateHappy(-10, false);
        } else if(eventNumber == 5){
            updateHunger(2, false);
            updateHealth(-2);
        } else if(eventNumber == 6){
            updateHappy(20, false);
        } else if(eventNumber == 7){
            updateHappy(-15, false);
        } else if(eventNumber == 8){
            updateHealth(-15);
        } else if(eventNumber == 9){
            updateHappy(-15, false);
            updateHealth(-10);
        } else if(eventNumber == 10){
            updateHappy(-10, false);
        } else if(eventNumber == 12){
            updateHealth(-2);
        } else if(eventNumber == 13){
            updateHappy(-5, false);
        } else if(eventNumber == 14){
            updateHappy(5, false);
        } else if(eventNumber == 15){
            updateHappy(-5, false);
        } else if(eventNumber == 16){
            updateHappy(10, false);
        } else if(eventNumber == 17){
            updateHappy(-20, false);
        } else if(eventNumber == 18){
            updateHappy(10, false);
        } else if(eventNumber == 19){
            updateHappy(10, false);
        } else if(eventNumber == 20){
            updateHappy(20, false);
        } else if(eventNumber == 21){
            updateHappy(-20, false);
            updateHealth(-20);
        }
        return events.get(eventNumber);
    }
}
