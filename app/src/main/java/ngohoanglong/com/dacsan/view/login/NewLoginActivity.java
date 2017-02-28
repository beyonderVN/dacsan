package ngohoanglong.com.dacsan.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class NewLoginActivity extends AppCompatActivity {
    private static final String TAG = "NewLoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, LoginFragment.newInstance())
                .commit();
    }

    public static Intent getIntentNewTask(Context context) {
        Intent intent = new Intent(context, NewLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }
}
