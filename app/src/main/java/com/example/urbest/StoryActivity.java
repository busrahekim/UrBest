package com.example.urbest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.urbest.R.drawable.ic_baseline_star_24;

public class StoryActivity extends AppCompatActivity {

    DatabaseReference favDB;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String userID;
    private CoordinatorLayout co;
    private TextView textView;
    FloatingActionButton floatingActionButton;
    boolean clicked = false;
    private String story = "I’m 45 years-old, I have a wonderful wife, two adult children who make me proud every day, and a daughter in-law who I love like she’s my own child. My life is, by every objective measurement, very very good. That’s because I live with Depression and Anxiety, the tag team champions of the World Wrestling With Mental Illness Federation. And I’m not ashamed to stand here, in front of six hundred people in this room, and millions more online, and proudly say that I live with mental illness, and that’s okay.\n" +
            "\n" +
            "I say \"with\" because even though my mental illness tries its best, it doesn’t control me, it doesn’t define me, and I refuse to be stigmatized by it. My name is Wil Wheaton, and I have Chronic Depression. I suffered because though we in America have done a lot to help people who live with mental illness, we have not done nearly enough to make it okay for our fellow travelers on the wonky brain express to reach out and accept that help. I’m here today to talk with you about working to end the stigma and prejudice that surrounds mental illness in America, and as part of that, I want to share my story with you.\n" +
            "\n" +
            "Night after night, I’d wake up in absolute terror, and night after night, I’d drag my blankets off my bed, to go to sleep on the floor in my sister’s bedroom, because I was so afraid to be alone. When I was around twelve or thirteen, my anxiety began to express itself in all sorts of delightful ways. When I was thirteen, I was in an internationally-beloved film called Stand by Me, and I was famous. Like, really famous, like, can’t-go-to-the-mall-with-my-friends-without-getting-mobbed famous, and that meant that all of my actions were scrutinized by my parents, my peers, my fans, and the press.\n" +
            "\n" +
            "The directors and producers complained to my parents that I was being difficult to work with. When I couldn’t remember my lines, because I was so anxious about things I can’t even remember now, directors would accuse me of being unprofessional and unprepared. And that’s when my anxiety turned into depression. I struggled to reconcile the facts of my life with the reality of my existence.\n" +
            "\n" +
            "This prejudice existed in my family in spite of the ample incidence of mental illness that ran rampant through my DNA, featuring successful and unsuccessful suicide attempts by my relations, more than one case of bipolar disorder, clinical depression everywhere, and, because of self-medication, so much alcoholism, it was actually notable when someone didn’t have a drinking problem. They lived in a world where mental illness was equated with weakness, and shame, and as a result, I suffered until I was in my thirties. I just didn’t know what questions to ask, and the adults I was close to didn’t know what answers to give. I clearly remember being twenty-two, living in my own house, waking up from a panic attack that was so terrifying just writing about it for this talk gave me so much anxiety I almost cut this section from my speech.\n" +
            "\n" +
            "It was the middle of the night, and I drove across town, to my parents’ house, to sleep on the floor of my sister’s bedroom again, because at least that’s where I felt safe. She knew that many of my blood relatives had mental illness, but she couldn’t or wouldn’t connect the dots. \"You’re just realizing that the world is a scary place,\" she said. She really was doing the best that she could for me, but stigma and the shame is inspires are powerful things.\n" +
            "\n" +
            "One of the primary reasons I speak out about my mental illness, is so that I can make the difference in someone’s life that I wish had been made in mine when I was young, because not only did I have no idea what Depression even was until I was in my twenties, once I was pretty sure that I had it, I suffered with it for another fifteen years, because I was ashamed, I was embarrassed, and I was afraid. I missed out on a lot of things, during what are supposed to be the best years of my life, because I was paralyzed by What If-ing anxiety. I wanted to go do things with my friends, but my anxiety always found a way to stop me.\n" +
            "\n" +
            "Except it didn’t have to be that way, and it took me having a full blown panic attack and a complete meltdown at Los Angeles International Airport for my wife to suggest to me that I get help. Like I said, I had suspected for years that I was clinically depressed, but I was afraid to admit it, until the most important person in my life told me without shame or judgment that she could see that I was suffering. It’s just an illness. I mean, it’s right there in the name \"Mental ILLNESS\" so it shouldn’t have been the revelation that it was, but when the part of our bodies that is responsible for how we perceive the world and ourselves is the same part of our body that is sick, it can be difficult to find objectivity or perspective.\n" +
            "\n" +
            "At that moment, I realized that I had lived my life in a room that was so loud, all I could do every day was deal with how loud it was. Ten years of suffering and feeling weak and worthless and afraid all the time, because of the stigma that surrounds mental illness. Thank God that my wife saw that I was hurting, and thank God she didn’t believe the lie that Depression is weakness, or something to be ashamed of. I started talking in public about my mental illness in 2012, and ever since then, people reach out to me online every day, and they ask me about living with depression and anxiety.\n" +
            "\n" +
            "Right now, there is a child somewhere who has the same panic attacks I had, and their parents aren’t getting them help, because they believe it reflects poorly on their parenting to have a child with mental illness.\n" +
            "\n" +
            "Here’s one of the things I tell them\n" +
            "\n" +
            "One of the many delightful things about having Depression and Anxiety is occasionally and unexpectedly feeling like the whole goddamn world is a heavy lead blanket, like that thing they put on your chest at the dentist when you get x-rays, and it’s been dropped around your entire existence without your consent. Emotionally, it covers me completely, separating me from my motivation, my focus, and everything that brings me joy in my life. Depression is beating up on us already, and we don’t need to help it out. No person anywhere, especially here in the richest country in the world, should live in the shadows or suffer alone, because they can’t afford treatment.\n" +
            "\n" +
            "We have all the money in the world for weapons and corporate tax cuts, so I know that we can afford to prioritize not just health care in general, but mental health care, specifically. There are parents who have learned that mental illness is no different than physical illness, and they’re helping their children get better. There are adults who, like me, were terrified that antidepressant medication would make them a different person, and they’re hearing the birds sing for the first time, because they have finally found their way out of the dark room.\n" +
            "\n" +
            "I spent the first thirty years of my life trapped in that dark, loud room, and I know how hopeless and suffocating it feels to be in there, so I do everything I can to help others find their way out. I do that by telling my story, so that my privilege and success does more than enrich my own life. We can start by demanding that our elected officials fully fund mental health programs. Finally, we who live with mental illness need to talk about it, because our friends and neighbors know us and trust us. We need to share our experiences, so someone who is suffering the way I was won’t feel weird or broken or ashamed or afraid to seek treatment. So that parents don’t feel like they have failed or somehow screwed up when they see symptoms in their kids.\n" +
            "\n" +
            "People who reach out to get help for their mental illness are brave.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        textView = findViewById(R.id.storyContent);
        floatingActionButton = findViewById(R.id.fab);
        co = findViewById(R.id.c);
        textView.setText(story);

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        favDB = FirebaseDatabase.getInstance().getReference().child("favList").child(userID);

    }

    public void fabClicked(View view) {
        if(clicked == false){
            floatingActionButton.setImageResource(ic_baseline_star_24);
            insertFavData();
            Snackbar snackbar = Snackbar.make(co, "The story added your favorites.", Snackbar.LENGTH_LONG);
            snackbar.setAction("OKEY", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();
            clicked = true;
        }
        else{
            floatingActionButton.setImageResource(R.drawable.ic_baseline_star_border_24);
            removeFavData();
            Snackbar snackbar = Snackbar.make(co, "The story removed from your favorites.", Snackbar.LENGTH_LONG);
            snackbar.setAction("OKEY", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();
            clicked = false;
        }
    }

    private void removeFavData() {
        favDB.child("favList").child(userID).removeValue();
    }

    private void insertFavData() {
        String headData = "Wil Wheaton";
        int imgData = R.drawable.will;
        UserFavorites u = new UserFavorites(imgData,headData);

        favDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getChildrenCount() == 0){
                    Log.d("count",snapshot.getChildrenCount()+"");
                    favDB.push().setValue(u);
                    return;
                }
                else{
                    boolean checkIfExist = checkIfExist(headData,snapshot);
                    if(checkIfExist == false){
                        favDB.push().setValue(u);
                        Log.d("not exist","db has not the favorite");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Something went wrong!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkIfExist(String a, DataSnapshot snapshot) {

        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
            UserFavorites userFavorites = dataSnapshot.getValue(UserFavorites.class);
            if (a.equals(userFavorites.getFavoriteName())){
                Log.d("exist","db has the favorite");
                return true;
            }
        }
        return false;
    }

}


