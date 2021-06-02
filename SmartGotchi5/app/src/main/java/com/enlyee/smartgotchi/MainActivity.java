package com.enlyee.smartgotchi;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.System.currentTimeMillis;

public class MainActivity extends AppCompatActivity {


    private Pet myPet;

    private int gameAttempts;
    private int random;
    private int SHORTduration;
    private int creatureChoice;
    private int resourceID;
    private int width;
    private int height;
    private int tapTracker;

    private TextView hungerIntText;
    private TextView waterIntText;
    private TextView ageIntText;
    private TextView happyIntText;
    private TextView healthIntText;
    private TextView deathIntText;
    private TextView nameYourNewPetText;
    private TextView deadPetText;
    private TextView guessANumberText;
    private TextView alreadyGuessedLettersText;
    private TextView hiddenWordText;
    private TextView guessALetterText;
    private TextView loadedPetNameText;

    private Button gameOneButton;
    private Button gameTwoButton;
    private Button createNewPetButton;
    private Button openOldPetButton;
    private Button submitButton;

    private EditText numberEditText;
    private EditText letterEditText;
    private EditText nameEditText;

    Character characterGuess;

    private CharSequence name;

    private String hiddenWordString;
    private String word;

    private boolean saveButtonWasOpened;
    private boolean soundORnot;
    private boolean loaded;

    private ImageView creatureImage;
    private ImageView waterImg;
    private ImageView foodImg;
    private ImageView treatImg;
    private ImageView heartImage;

    private ImageButton soundImageButton;

    private View view;

    private Context context;

    private MediaPlayer catpurrSound;
    private MediaPlayer drinkingSound;
    private MediaPlayer eatingSound;

    private LinearLayout linearLayout;

    private ArrayList<Character> guessedLettersArr;
    private ArrayList<Character> lettersHidden;
    private ArrayList<String> words;
    private ArrayList<String> creatureList;

    private AnimationDrawable waterAnimation;
    private AnimationDrawable foodAnimation;
    private AnimationDrawable treatAnimation;
    private AnimationDrawable heartAnimation;

    private ObjectAnimator creatureAnimator;

    Toast eventToast;
    Toast nopeToast;
    Toast correctToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPet = new Pet();

        Point size = new Point();
        Display display = getDisplay();
        display.getSize(size);

        width = size.x;
        height = size.y;
        SHORTduration = Toast.LENGTH_SHORT;

        hungerIntText = findViewById(R.id.hungerinttext);
        waterIntText = findViewById(R.id.waterinttext);
        ageIntText = findViewById(R.id.ageinttext);
        happyIntText = findViewById(R.id.happyinttext);
        healthIntText = findViewById(R.id.healthinttext);
        deathIntText = findViewById(R.id.deathinttext);
        nameYourNewPetText = findViewById(R.id.nameyournewpettext);
        deadPetText = findViewById(R.id.deadpettext);
        guessANumberText = findViewById(R.id.guessanumbertext);
        alreadyGuessedLettersText = findViewById(R.id.alreadyguessedletters);
        hiddenWordText = findViewById(R.id.hiddenword);
        guessALetterText = findViewById(R.id.guessaletter);
        loadedPetNameText = findViewById(R.id.loadedpetname);

        gameOneButton = findViewById(R.id.gameonebutton);
        gameTwoButton = findViewById(R.id.gametwobutton);
        createNewPetButton = findViewById(R.id.createnewpetbutton);
        openOldPetButton = findViewById(R.id.openoldpetbutton);
        submitButton = findViewById(R.id.submit);

        soundImageButton = findViewById(R.id.soundimagebutton);
        ImageButton savedCreaturesImageButton = findViewById(R.id.savedcreaturesimagebutton);
        ImageButton mealImageButton = findViewById(R.id.mealbutton);
        ImageButton treatImageButton = findViewById(R.id.treatbutton);
        ImageButton waterImageButton = findViewById(R.id.waterbutton);
        ImageButton playImageButton = findViewById(R.id.playbutton);
        ImageButton punishImageButton = findViewById(R.id.punishbutton);
        ImageButton shotImageButton = findViewById(R.id.shotbutton);

        nameEditText = findViewById(R.id.nameedittext);
        numberEditText = findViewById(R.id.numberedittext);
        letterEditText = findViewById(R.id.letteredittext);

        saveButtonWasOpened = false;
        soundORnot = false;
        loaded = false;

        waterImg = findViewById(R.id.waterbowl);
        waterImg.setBackgroundResource(R.drawable.water_animation);
        foodImg = findViewById(R.id.foodbowl);
        foodImg.setBackgroundResource(R.drawable.food_animation);
        treatImg = findViewById(R.id.bonetreat);
        treatImg.setBackgroundResource(R.drawable.treat_animation);
        heartImage = findViewById(R.id.heartimageview);
        heartImage.setBackgroundResource(R.drawable.heart_animation);
        heartImage.setVisibility(View.VISIBLE);
        creatureImage = findViewById(R.id.creatureimageview);

        context = getApplicationContext();

        catpurrSound = MediaPlayer.create(this, R.raw.purr);
        drinkingSound = MediaPlayer.create(this, R.raw.drinking);
        eatingSound = MediaPlayer.create(this, R.raw.eating);

        linearLayout = findViewById(R.id.linearlayoutlistofpets);

        creatureList = new ArrayList<>();
        creatureList.add("tamaameba"); creatureList.add("tamabomb"); creatureList.add("tamachip"); creatureList.add("tamaeyeturtle");
        creatureList.add("tamafish"); creatureList.add("tamaghost"); creatureList.add("tamagross"); creatureList.add("tamahive");
        creatureList.add("tamahumanface"); creatureList.add("tamapalm"); creatureList.add("tamapills"); creatureList.add("tamarobot");
        creatureList.add("tamarobotheadandspine"); creatureList.add("tamascarycat"); creatureList.add("tamascorpion"); creatureList.add("tamaship");
        creatureList.add("tamasideface"); creatureList.add("tamasierpinskitriangle"); creatureList.add("tamasnake"); creatureList.add("tamaspekter");
        creatureList.add("tamaspidereyeball"); creatureList.add("tamasquiggle"); creatureList.add("tamawtf");

        waterAnimation = (AnimationDrawable) waterImg.getBackground();
        foodAnimation = (AnimationDrawable) foodImg.getBackground();
        treatAnimation = (AnimationDrawable) treatImg.getBackground();
        heartAnimation = (AnimationDrawable) heartImage.getBackground();

        nopeToast = Toast.makeText(context, context.getString(R.string.nope), SHORTduration);
        correctToast = Toast.makeText(context, context.getString(R.string.correct), SHORTduration);

        display.getSize(size);

        settings();
        findViewById(R.id.mainall).setOnTouchListener(handleTouch);

        soundImageButton.setOnClickListener(v -> changeSound());

        creatureImage.setOnClickListener(v -> {
            heartImage.setX(creatureImage.getX());
            heartImage.setY(creatureImage.getY());
            myPet.updateHappy(2, false);
            heartAnimation.stop();
            heartAnimation.start();
            catpurrSound.start();
            updateGame();
        });

        savedCreaturesImageButton.setOnClickListener(v -> {
            turnOffEverything();
            linearLayout.removeAllViewsInLayout();
            linearLayout.setVisibility(View.GONE);
            if(!saveButtonWasOpened) {
                createNewPetButton.setVisibility(View.VISIBLE);
                openOldPetButton.setVisibility(View.VISIBLE);
                loadedPetNameText.setVisibility(View.GONE);
                deadPetText.setVisibility(View.GONE);
                nameEditText.setVisibility(View.GONE);
                submitButton.setVisibility(View.GONE);
                saveButtonWasOpened = true;
            } else {
                createNewPetButton.setVisibility(View.GONE);
                openOldPetButton.setVisibility(View.GONE);
                loadedPetNameText.setVisibility(View.GONE);
                deadPetText.setVisibility(View.GONE);
                nameEditText.setVisibility(View.GONE);
                submitButton.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                saveButtonWasOpened = false;
                linearLayout.setVisibility(View.GONE);
            }
            turnOffEverything();
        });

        createNewPetButton.setOnClickListener(v -> {
            turnOffEverything();
            linearLayout.removeAllViewsInLayout();
            deadPetText.setVisibility(View.GONE);
            nameEditText.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.VISIBLE);
            nameYourNewPetText.setVisibility(View.VISIBLE);
            createNewPetButton.setVisibility(View.GONE);
            openOldPetButton.setVisibility(View.GONE);
            saveButtonWasOpened = false;

            submitButton.setOnClickListener(v1 -> {
                view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                createNewPetButton.setVisibility(View.GONE);
                openOldPetButton.setVisibility(View.GONE);
                submitButton.setVisibility(View.GONE);
                nameEditText.setVisibility(View.GONE);
                nameEditText.getText().toString();

                creatureChoice = abs((int) (Math.random() * creatureList.size()) - 1);
                resourceID = getResources().getIdentifier(creatureList.get(creatureChoice), "drawable", getPackageName());

                creatureImage.setBackgroundResource(resourceID);
                creatureImage.setVisibility(View.VISIBLE);


                myPet = new Pet(nameEditText.getText().toString().toLowerCase().replace(" ", "_"), 0, 50, 50, 100,
                        0, 50, (int)(currentTimeMillis() / 1000), (int)(currentTimeMillis() / 1000), 75, creatureList.get(creatureChoice));

                String petInfo = nameEditText.getText().toString().toLowerCase().replace(" ", "_") + " 0 50 50 100 0 50 " + (int) (currentTimeMillis() / 1000) + " " +
                        (int) (currentTimeMillis() / 1000) + " 75 " + creatureList.get(creatureChoice);
                loaded = true;

                FileOutputStream outputStream;
                try{
                    outputStream = openFileOutput(nameEditText.getText().toString().toLowerCase().replace(" ", "_") + ".pet", Context.MODE_PRIVATE);
                    outputStream.write(petInfo.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                updateGame();

                loadedPetNameText.setText(myPet.getName().replace("_", " "));
                loadedPetNameText.setVisibility(View.VISIBLE);
                view = getCurrentFocus();
                nameYourNewPetText.setVisibility(View.GONE);
            });
        });

        openOldPetButton.setOnClickListener(v -> {
            saveButtonWasOpened = false;
            deadPetText.setVisibility(View.GONE);
            createNewPetButton.setVisibility(View.GONE);
            openOldPetButton.setVisibility(View.GONE);
            loadedPetNameText.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
            turnOffEverything();
            choosePet();
        });

        mealImageButton.setOnClickListener(v -> {
            if(loaded) {
                event();
                myPet.updateHunger(15, false);
                myPet.ageUp((int) (currentTimeMillis() / 1000));
                foodAnimation.stop();
                foodAnimation.start();
                animateCreature(foodImg.getX(), foodImg.getY());
                eatingSound.start();
                turnOffEverything();
                updateGame();
            }
        });

        treatImageButton.setOnClickListener(v -> {
            if(loaded) {
                event();
                treatAnimation.stop();
                treatAnimation.start();
                animateCreature(treatImg.getX(), treatImg.getY());
                eatingSound.start();
                myPet.updateHunger(30, true);
                myPet.updateHealth(-5);
                myPet.updateHappy(10, false);
                turnOffEverything();
                updateGame();
            }
        });

        waterImageButton.setOnClickListener(v -> {
            if(loaded) {
                myPet.updateThirst(15);
                myPet.ageUp((int) (currentTimeMillis() / 1000));
                waterAnimation.stop();
                waterAnimation.start();
                animateCreature(waterImg.getX(), waterImg.getY());
                drinkingSound.start();
                turnOffEverything();
                updateGame();
                event();
            }
        });

        playImageButton.setOnClickListener(v -> {
            if (loaded) {
                event();
                gameOneButton.setVisibility(View.VISIBLE);
                gameTwoButton.setVisibility(View.VISIBLE);

                gameOneButton.setOnClickListener(v14 -> {

                    gameAttempts = 3;
                    guessANumberText.setVisibility(View.VISIBLE);
                    guessANumberText.setText(R.string.guess_a_number);
                    numberEditText.setVisibility(View.VISIBLE);
                    submitButton.setVisibility(View.VISIBLE);

                    random = (int) (Math.random() * 5) + 1;
                    gameOneButton.setVisibility(View.GONE);
                    gameTwoButton.setVisibility(View.GONE);
                    submitButton.setOnClickListener(v12 -> {
                        if(!numberEditText.getText().toString().isEmpty()) {
                            int guessInt = Integer.parseInt(numberEditText.getText().toString());
                            if (guessInt == random) {
                                myPet.play();
                                gameAttempts = 0;
                                correctToast.show();
                                numberEditText.getText().clear();
                            } else {
                                gameAttempts--;
                                numberEditText.getText().clear();
                                nopeToast.show();
                            }
                            if (gameAttempts == 0 || gameAttempts == 3) {
                                view = getCurrentFocus();
                                if (view != null) {
                                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                }
                                guessANumberText.setVisibility(View.GONE);
                                numberEditText.setVisibility(View.GONE);
                                submitButton.setVisibility(View.GONE);
                                updateGame();
                            }
                        }
                    });
                });
                gameTwoButton.setOnClickListener(v13 -> {
                    alreadyGuessedLettersText.setText("");
                    gameOneButton.setVisibility(View.GONE);
                    gameTwoButton.setVisibility(View.GONE);

                    words = new ArrayList<>();
                    words.add("apple"); words.add("butter"); words.add("cat"); words.add("dog"); words.add("elephant"); words.add("future");
                    words.add("ghost"); words.add("history"); words.add("icing"); words.add("jump"); words.add("kill"); words.add("little");
                    words.add("moth"); words.add("naughty"); words.add("octopus"); words.add("peanut"); words.add("quit"); words.add("race");
                    words.add("simple"); words.add("terrible"); words.add("unbeatable"); words.add("very"); words.add("wild");
                    words.add("yoda"); words.add("zap"); words.add("alligator"); words.add("ant"); words.add("bear"); words.add("bee");
                    words.add("bird"); words.add("camel"); words.add("cheetah"); words.add("chicken"); words.add("chimpanzee"); words.add("cow");
                    words.add("crocodile"); words.add("deer"); words.add("dolphin"); words.add("duck"); words.add("eagle"); words.add("fish");
                    words.add("fly"); words.add("fox"); words.add("frog"); words.add("giraffe"); words.add("goat"); words.add("goldfish");
                    words.add("hamster"); words.add("hippopotamus"); words.add("horse"); words.add("kangaroo"); words.add("kitten"); words.add("lion");
                    words.add("lobster"); words.add("monkey"); words.add("owl"); words.add("panda"); words.add("pig"); words.add("puppy");
                    words.add("rabbit"); words.add("rat"); words.add("scorpion"); words.add("seal"); words.add("shark"); words.add("sheep");
                    words.add("snail"); words.add("snake"); words.add("spider"); words.add("squirrel"); words.add("tiger"); words.add("turtle");
                    words.add("wolf"); words.add("zebra"); words.add("banana"); words.add("cherry"); words.add("grapefruit"); words.add("grapes");
                    words.add("lemon"); words.add("lime"); words.add("melon"); words.add("orange"); words.add("peach"); words.add("pear");
                    words.add("persimmon"); words.add("pineapple"); words.add("plum"); words.add("strawberry"); words.add("tangerine"); words.add("watermelon");
                    words.add("beach"); words.add("desert"); words.add("forest"); words.add("hill"); words.add("mountain"); words.add("ocean");
                    words.add("pond"); words.add("river"); words.add("lake"); words.add("sea"); words.add("valley"); words.add("stream");
                    words.add("waterfall"); words.add("woods"); words.add("asparagus"); words.add("beans"); words.add("broccoli"); words.add("cabbage");
                    words.add("carrot"); words.add("celery"); words.add("corn"); words.add("cucumber"); words.add("eggplant"); words.add("lettuce");
                    words.add("onion"); words.add("peas"); words.add("potato"); words.add("pumpkin"); words.add("radish"); words.add("spinach");
                    words.add("tomato"); words.add("turnip"); words.add("clear"); words.add("cold"); words.add("cloudy"); words.add("cool");
                    words.add("foggy"); words.add("hot"); words.add("humid"); words.add("rainy"); words.add("snowy"); words.add("stormy");
                    words.add("sunny"); words.add("warm"); words.add("windy "); words.add("drill"); words.add("hammer"); words.add("knife");
                    words.add("plane"); words.add("pliers"); words.add("scissors"); words.add("screwdriver"); words.add("vise"); words.add("wrench");
                    words.add("blackboard"); words.add("book");
                    words.add("bookcase"); words.add("calendar"); words.add("chair"); words.add("chalk"); words.add("clock"); words.add("computer");
                    words.add("desk"); words.add("dictionary"); words.add("eraser"); words.add("notebook"); words.add("pencil"); words.add("textbook");
                    words.add("measuring"); words.add("microwave"); words.add("mixing"); words.add("bowl"); words.add("broil"); words.add("paper");
                    words.add("towels"); words.add("poach"); words.add("potholder"); words.add("roast"); words.add("rolling"); words.add("scramble");
                    words.add("simmer"); words.add("knife"); words.add("spoon"); words.add("spatula"); words.add("steam"); words.add("strainer");

                    lettersHidden = new ArrayList<>();

                    guessedLettersArr = new ArrayList<>();

                    random = abs((int) (Math.random() * words.size()) - 1);
                    word = words.get(random);

                    for (int i = 0; i < 3; i++) {
                        int rando = abs((int) (Math.random() * word.length()) - 1);
                        Character randomLetter = word.charAt(rando);
                        if (!lettersHidden.contains(randomLetter)) {
                            lettersHidden.add(randomLetter);
                        }
                    }

                    submitButton.setVisibility(View.VISIBLE);
                    letterEditText.setVisibility(View.VISIBLE);
                    guessALetterText.setVisibility(View.VISIBLE);
                    guessALetterText.setText(getString(R.string.guess_a_letter));

                    hiddenWordString = wordChange(word, lettersHidden);

                    alreadyGuessedLettersText.setVisibility(View.VISIBLE);
                    hiddenWordText.setVisibility(View.VISIBLE);
                    hiddenWordText.setText(hiddenWordString);
                    gameAttempts = 6;

                    submitButton.setOnClickListener(v131 -> {
                        if (letterEditText.getText().length() > 0)
                            characterGuess = letterEditText.getText().charAt(0);
                        else
                            characterGuess = ' ';

                        letterEditText.getText().clear();
                        if (lettersHidden.contains(characterGuess)) {
                            lettersHidden.remove(characterGuess);
                            hiddenWordString = wordChange(word, lettersHidden);
                            hiddenWordText.setText(hiddenWordString);
                            correctToast.show();
                        } else {
                            guessedLettersArr.add(characterGuess);
                            gameAttempts--;
                            nopeToast.show();
                        }

                        alreadyGuessedLettersText.setText(wrongGuesses(guessedLettersArr));

                        if (lettersHidden.isEmpty()) {
                            view = getCurrentFocus();
                            if (view != null) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            }
                            lettersHidden.removeAll(lettersHidden);
                            guessedLettersArr.removeAll(guessedLettersArr);
                            myPet.play();
                            correctToast.show();
                            alreadyGuessedLettersText.setVisibility(View.GONE);
                            guessALetterText.setVisibility(View.GONE);
                            letterEditText.setVisibility(View.GONE);
                            submitButton.setVisibility(View.GONE);
                            hiddenWordText.setVisibility(View.GONE);
                            updateGame();
                            guessedLettersArr.clear();
                        } else {
                            lettersHidden.size();
                            if (gameAttempts == 0) {
                                view = getCurrentFocus();
                                if (view != null) {
                                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                }
                                alreadyGuessedLettersText.setVisibility(View.GONE);
                                lettersHidden.removeAll(lettersHidden);
                                guessedLettersArr.removeAll(guessedLettersArr);
                                nopeToast.show();
                                guessALetterText.setVisibility(View.GONE);
                                letterEditText.setVisibility(View.GONE);
                                submitButton.setVisibility(View.GONE);
                                hiddenWordText.setVisibility(View.GONE);
                                updateGame();
                                guessedLettersArr.clear();
                            }
                        }
                    });
                });
                updateGame();
                animateCreature();
            }
        });

        punishImageButton.setOnClickListener(v -> {
            if(loaded) {
                myPet.updateHappy(-10, false);
                myPet.updateRandomEvent();
                turnOffEverything();
                updateGame();
                animateCreature();
                event();
            }
        });

        shotImageButton.setOnClickListener(v -> {
            if(loaded){
                myPet.giveShot();
                turnOffEverything();
                updateGame();
                animateCreature();
                event();
            }
        });
    }

    @Override
     public void onPause() {
        super.onPause();
        save();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        int timeKeeper = (int) (currentTimeMillis() / 1000);
        myPet.updateAwayTime(timeKeeper);
        save();
        updateText();
    }

    private void updateText(){
        ageIntText.setText(String.valueOf(myPet.getAge()));
        hungerIntText.setText(String.valueOf(myPet.getHunger()));
        waterIntText.setText(String.valueOf(myPet.getThirst()));
        happyIntText.setText(String.valueOf(myPet.getHappy()));
        healthIntText.setText(String.valueOf(myPet.getHealth()));
        deathIntText.setText(String.valueOf(myPet.getDeath()));
    }

    private void event(){
        int percentage = myPet.getRandomEvent();
        int randomEv = (int) (Math.random() * 100);
        if(randomEv <= percentage){
            String eventText = myPet.randomEvent();
            eventToast = Toast.makeText(context, eventText, SHORTduration);
            eventToast.show();
        }
    }

    private void updateGame(){
        myPet.ageUp((int)(currentTimeMillis() / 1000));
        handlingDeath();
        save();
    }

    private void save(){
        updateText();
        String petInfo = myPet.getName() + " " + myPet.getAge() + " " + myPet.getHunger() + " " +
                myPet.getThirst() + " " + myPet.getHealth() + " " + myPet.getDeath() + " " +
                myPet.getHealth() + " " + myPet.getAgeTime() + " " +
                (int) (currentTimeMillis() / 1000) + " " + myPet.getRandomEvent() + " " + myPet.getCreature();
        FileOutputStream outputStream;
        try{
            outputStream = openFileOutput(myPet.getName() + ".pet", Context.MODE_PRIVATE);
            outputStream.write(petInfo.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handlingDeath(){
        if(myPet.isDead()){
            deadPetText.setVisibility(View.VISIBLE);
            openOldPetButton.setVisibility(View.VISIBLE);
            createNewPetButton.setVisibility(View.VISIBLE);
        }
    }
    private String wordChange(String s, ArrayList<Character> a){
        String hidden = s;
        for(int i = 0; i < a.size(); i++){
            hidden = hidden.replace(a.get(i), '_');
        }
        return hidden;
    }

    private String wrongGuesses(ArrayList<Character> a){
        StringBuilder guesses = new StringBuilder();
        for(int i = 0; i < a.size(); i++){
            guesses.append(a.get(i));
        }
        return guesses.toString();
    }

    private void animateCreature(){
        int randomX = 300 + (int) (Math.random() * (300 - (width - 300)) + 1);
        int randomY = 300 + (int) (Math.random() * (300 - (height - 300)) + 1);
        creatureAnimator = ObjectAnimator.ofFloat(creatureImage, "translationX", randomX);
        creatureAnimator.setDuration(2000);
        creatureAnimator.start();
        creatureAnimator = ObjectAnimator.ofFloat(creatureImage, "translationY", randomY);
        creatureAnimator.setDuration(2000);
        creatureAnimator.start();
    }

    private void animateCreature(float newX, float newY){
        creatureAnimator = ObjectAnimator.ofFloat(creatureImage, "translationX", newX - 50);
        creatureAnimator.setDuration(2000);
        creatureAnimator.start();
        creatureAnimator = ObjectAnimator.ofFloat(creatureImage, "translationY", newY - 50);
        creatureAnimator.setDuration(2000);
        creatureAnimator.start();
    }

    private void choosePet(){
        File path = getFilesDir();
        File[] files = path.listFiles();
        linearLayout.removeAllViewsInLayout();
        for (File file : files) {
            int lengthOfSplit = file.getName().split("\\.").length;
            if (lengthOfSplit > 1 && file.getName().split("\\.")[1].equals("pet") && !file.getName().split("\\.")[0].equals("null")) {
                name = file.getName().split("\\.")[0];
                final Button newButt = new Button(this);
                newButt.setBackgroundResource(R.drawable.petlistimage);
                newButt.setText(name);
                newButt.setVisibility(linearLayout.getVisibility());
                newButt.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                final ImageButton deleteButt = new ImageButton(this);
                deleteButt.setVisibility(linearLayout.getVisibility());
                deleteButt.setBackgroundResource(R.drawable.deletebutton);
                deleteButt.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                newButt.setOnClickListener(v -> {
                    linearLayout.setVisibility(View.GONE);
                    try {
                        view = getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        String[] words = new String[11];
                        FileInputStream petFile = openFileInput(newButt.getText().toString() + ".pet");
                        BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(petFile)));
                        String line;
                        while ((line = br.readLine()) != null) {
                            words = line.split(" ");
                        }
                        br.close();
                        myPet = new Pet(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]),
                                Integer.parseInt(words[4]), Integer.parseInt(words[5]), Integer.parseInt(words[6]),
                                Integer.parseInt(words[7]), Integer.parseInt(words[8]), Integer.parseInt(words[9]), words[10]);
                        myPet.updateAwayTime((int) (currentTimeMillis() / 1000));
                        resourceID = getResources().getIdentifier(words[10], "drawable", getPackageName());

                        creatureImage.setBackgroundResource(resourceID);
                        creatureImage.setVisibility(View.VISIBLE);

                        updateGame();
                        save();
                        loaded = true;
                        newButt.setVisibility(View.GONE);
                        deleteButt.setVisibility(View.GONE);
                        openOldPetButton.setVisibility(View.GONE);
                        createNewPetButton.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.GONE);
                        linearLayout.removeAllViewsInLayout();
                        loadedPetNameText.setText(myPet.getName().replace("_", " "));
                        loadedPetNameText.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                deleteButt.setOnClickListener(v -> {
                    File dir = getFilesDir();
                    try {
                        File file1 = new File(dir, name + ".pet");
                        if (file1.exists()) {
                            file1.delete();
                            deleteButt.setVisibility(View.GONE);
                            newButt.setVisibility(View.GONE);
                            linearLayout.setVisibility(View.GONE);
                            linearLayout.removeView(deleteButt);
                            linearLayout.removeView(newButt);
                            linearLayout.removeAllViewsInLayout();
                            choosePet();
                        }
                    } catch (Exception ignored) {

                    }
                });

                if (linearLayout != null) {
                    linearLayout.addView(newButt);
                    linearLayout.addView(deleteButt);
                }
            }
        }
    }

    private final View.OnTouchListener handleTouch = (v, event) -> {

        float xC = event.getX();
        float yC = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                myPet.updateHappy(1, false);
                animateCreature(xC, yC);
                if (tapTracker<5){
                    tapTracker++;
                }
                else {
                    tapTracker = 0;
                    event();
                }
                break;
        }
        updateGame();
        return true;
    };

    private void settings(){

        File path = getFilesDir();
        File[] files = path.listFiles();
        for (File file : files) {
            if (file.getName().equals("settings")) {

                try {
                    FileInputStream settingsFile = openFileInput("settings");
                    BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(settingsFile)));
                    String line;
                    while ((line = br.readLine()) != null) {
                        soundORnot = Boolean.parseBoolean(line);
                    }
                    br.close();
                } catch (Exception ignored) {

                }

            } else {
                FileOutputStream outputStream;
                try {
                    outputStream = openFileOutput("settings", Context.MODE_PRIVATE);
                    outputStream.write("true".getBytes());
                    outputStream.close();
                    soundORnot = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        changeSound();
    }

    private void changeSound(){
        if(soundORnot) {
            catpurrSound.setVolume(1.0f,1.0f);
            drinkingSound.setVolume(0.4f,0.4f);
            eatingSound.setVolume(0.4f,0.4f);
            soundImageButton.setBackgroundResource(R.drawable.musicbutton);
            FileOutputStream outputStream;
            try{
                outputStream = openFileOutput("settings", Context.MODE_PRIVATE);
                outputStream.write("true".getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            soundORnot = false;
        } else {
            catpurrSound.setVolume(0.0f,0.0f);
            drinkingSound.setVolume(0.0f,0.0f);
            eatingSound.setVolume(0.0f,0.0f);
            soundImageButton.setBackgroundResource(R.drawable.nomusicbutton);
            FileOutputStream outputStream;
            try{
                outputStream = openFileOutput("settings", Context.MODE_PRIVATE);
                outputStream.write("false".getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            soundORnot = true;
        }
    }

    private void turnOffEverything(){
        gameOneButton.setVisibility(View.GONE);
        gameTwoButton.setVisibility(View.GONE);
        nameYourNewPetText.setVisibility(View.GONE);
        nameEditText.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
        nameYourNewPetText.setVisibility(View.GONE);
        alreadyGuessedLettersText.setVisibility(View.GONE);
        guessALetterText.setVisibility(View.GONE);
        letterEditText.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
        hiddenWordText.setVisibility(View.GONE);
        guessANumberText.setVisibility(View.GONE);
        numberEditText.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
    }

}