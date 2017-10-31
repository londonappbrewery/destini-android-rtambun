package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    private TextView mTextView;
    private Button mButtonTop;
    private Button mButtonBottom;
    private int mStoryIndex;

    private StoryFlow[] mStoryFlows = {
            new StoryFlow(R.string.T1_Story, R.string.T1_Ans1, R.string.T1_Ans2, 2, 1),
            new StoryFlow(R.string.T2_Story, R.string.T2_Ans1, R.string.T2_Ans2, 2, 3),
            new StoryFlow(R.string.T3_Story, R.string.T3_Ans1, R.string.T3_Ans2, 5, 4),
            new StoryFlow(R.string.T4_End, StoryFlow.NO_BUTTON, StoryFlow.NO_BUTTON, StoryFlow.END_STORY, StoryFlow.END_STORY),
            new StoryFlow(R.string.T5_End, StoryFlow.NO_BUTTON, StoryFlow.NO_BUTTON, StoryFlow.END_STORY, StoryFlow.END_STORY),
            new StoryFlow(R.string.T6_End, StoryFlow.NO_BUTTON, StoryFlow.NO_BUTTON, StoryFlow.END_STORY, StoryFlow.END_STORY),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        mTextView = (TextView) findViewById(R.id.storyTextView);
        mButtonTop = (Button) findViewById(R.id.buttonTop);
        mButtonBottom = (Button) findViewById(R.id.buttonBottom);

        // Initialize start of story
        if (savedInstanceState == null) {
            mStoryIndex = 0;
        } else {
            mStoryIndex = savedInstanceState.getInt("StoryIndex");
            // If end of story is reached then restart from beginning
            if (mStoryFlows[mStoryIndex].getNextIndexBottom() == StoryFlow.END_STORY &&
                    mStoryFlows[mStoryIndex].getNextIndexTop() == StoryFlow.END_STORY) {
                mStoryIndex = 0;
            }
        }
        updateTextAndButton();

        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        mButtonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStoryIndex = mStoryFlows[mStoryIndex].getNextIndexTop();
                updateTextAndButton();
            }
        });

        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        mButtonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStoryIndex = mStoryFlows[mStoryIndex].getNextIndexBottom();
                updateTextAndButton();
            }
        });
    }

    private void updateTextAndButton() {
        StoryFlow currentStory = mStoryFlows[mStoryIndex];
        mTextView.setText(currentStory.getStoryTextID());
        if (currentStory.getButtonTopID() != StoryFlow.NO_BUTTON) {
            mButtonTop.setText(currentStory.getButtonTopID());
        } else {
            mButtonTop.setVisibility(View.INVISIBLE);
        }
        if (currentStory.getButtonBottomID() != StoryFlow.NO_BUTTON) {
            mButtonBottom.setText(currentStory.getButtonBottomID());
        } else {
            mButtonBottom.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("StoryIndex", mStoryIndex);
    }
}
