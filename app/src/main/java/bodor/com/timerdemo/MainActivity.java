package bodor.com.timerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import static bodor.com.timerdemo.R.id.checkbox;
import static bodor.com.timerdemo.R.id.start;
import static bodor.com.timerdemo.R.id.time;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBox;
    private Button strat;
    private Button cancle;
    private TextView textView;

    private Timer timer;
    private MyTimerTask myTimerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = (CheckBox) findViewById(checkbox);
        strat = (Button) findViewById(R.id.start);
        cancle = (Button) findViewById(R.id.cancle);
        textView = (TextView) findViewById(R.id.timer);


        strat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (timer!=null)
                        timer.cancel();

                        myTimerTask = new MyTimerTask();
                        timer = new Timer();
                        if (checkBox.isChecked()) {
                            textView.setVisibility(View.VISIBLE);
                            timer.schedule(myTimerTask, 0, 1000); //1 second
                        } else {

                            Toast.makeText(getApplicationContext(), "Please Cheak SingleBox First", Toast.LENGTH_SHORT);
                        }

            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer!=null){
                    timer.cancel();
                    timer = null;
                    textView.setText("");
                    textView.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
           final String strData = simpleDateFormat.format(calendar.getTime());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(strData);
                }
            });

        }
    }
}
