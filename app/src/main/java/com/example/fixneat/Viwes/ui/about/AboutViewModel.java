package com.example.fixneat.Viwes.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {

    private final MutableLiveData<String> aboutText;

    public AboutViewModel() {
        aboutText = new MutableLiveData<>();
        aboutText.setValue("About Fix Neat");
    }

    public LiveData<String> getText() {
        return aboutText;
    }


    private String buildAboutTxt() {


        String description1, description2, description3, description4;

        description1 = "[App Name] is a [describe what your app does in one sentence].";

        description2 = "We are dedicated to providing the best [insert app category, such as -productivity tool- or -entertainment platform-] experience to our users.";
        description3 = "With a focus on [insert a key benefit or value that your app provides, such as -ease of use- or -quality content-],";
        description4 = "[App Name] is designed to make [insert user goal, such as -getting things done- or -having fun-].";

        String features1, features2, features3;
        features1 = "Features:";
        features2 = "- [List key features of your app, such as -user-friendly interface-, -real-time updates-, etc.]";
        features3 = "- [Include any unique or innovative features that set your app apart from others in its category.]";

        String why1 , why2 , why3, why4 , why5;
        why1 = "Why Choose [App Name]?";
        why2 = "- [Outline the benefits of using your app, such as -save time-, -increase productivity-, etc.]";
        why3 = "- [Highlight what makes your app stand out from the competition, such as -unmatched accuracy- or -exclusive content-.]";
        why4 = "- [Explain why your app is the best choice for the user, such as compared to other similar apps,";
        why5 = "[App Name] offers [insert unique benefit]- or -our app is constantly updated to ensure the best experience for our users-.]";

        String team1, team2, team3;
        team1 = "Our Team";
        team2 = "- [Introduce your team, including the founder(s), key contributors, and any other relevant information.]";
        team3 = "- [Include any personal or inspiring stories about how the app came to be and why your team is passionate about it.]";

        String contact1, contact2, contact3, contact4;
        contact1 = "Contact Us";
        contact2 = "- [Include a way for users to get in touch with you, such as an email address, website, or social media account.]";
        contact3 = "- [Include a statement encouraging feedback and suggestions from your users,";
        contact4 = "such as -We value your thoughts and opinions, and always welcome feedback to help us improve [App Name]-.]";

        String thanks;
        thanks = "Thank you for choosing [App Name]!";

        return thanks;
    }
}