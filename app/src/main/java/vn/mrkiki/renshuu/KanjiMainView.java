package vn.mrkiki.renshuu;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;



/**
 * Created by linhnd on 2016/08/05.
 */
public class KanjiMainView extends ListActivity {
    private static Model[] mSamples;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_kanji_main);

        // Instantiate the list of samples.
        mSamples = new Model[]{
                new Model(R.string.title_character, CharacterActivity.class),
                new Model(R.string.title_chinese214, Chinese214.class),
                new Model(R.string.title_exercices, Exercices.class),
                new Model(R.string.title_quiz, Quiz.class),
                new Model(R.string.title_feedback, FeedBack.class),
                new Model(R.string.title_moreapp, MoreApp.class),
                new Model(R.string.title_moreapp, KanjiGoSetting.class),
        };

        setListAdapter(new ArrayAdapter<Model>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                mSamples));
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        // Launch the sample associated with this list position.
        startActivity(new Intent(KanjiMainView.this, mSamples[position].activityClass));
    }

    private class Model {
        private CharSequence title;
        private Class<? extends Activity> activityClass;

        public Model(int titleResId, Class<? extends Activity> activityClass) {
            this.activityClass = activityClass;
            this.title = getResources().getString(titleResId);
        }

        @Override
        public String toString() {
            return title.toString();
        }
    }


}
