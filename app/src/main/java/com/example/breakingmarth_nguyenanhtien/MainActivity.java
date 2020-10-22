package com.example.breakingmarth_nguyenanhtien;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.w3c.dom.Text;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class MainActivity extends AppCompatActivity {

    int diem=0;
    boolean ketQua;
    Button btnTrue , btnFalse ,btnNewGame;
    TextView tvMainView, tvPoint , tvResultMath;
    TheadSimple t1 = new TheadSimple();
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    intView();
    gamestart();
    }
    //=======================
    private void intView(){
    btnFalse = findViewById(R.id.buttonFalse);
    btnTrue = findViewById(R.id.buttonTrue);
    btnNewGame = findViewById(R.id.buttonTrue);
    tvMainView = findViewById(R.id.textMain);
    tvPoint = findViewById(R.id.textPoint);
    tvMainView = findViewById(R.id.textMain);
    tvResultMath = findViewById(R.id.textResultMath);
    }
    private void gamestart(){

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ketQua = random.nextBoolean();
                playGame(ketQua);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } diem = diem -1;
                ketQua =random.nextBoolean();
                playGame(ketQua);
            }
        });

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dừng đếm giờ
                t1.destroy();
                // bắt đầu lấy điểm và tạo lượt mới
                if(ketQua == true ) diem   = diem +1;
                else diem = diem -1;
                tvPoint.setText("Điểm : "+ diem  );
                ketQua =random.nextBoolean();
                playGame(ketQua);
            }
        });
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dừng đếm giờ
                t1.destroy();
                // bắt đầu lấy điểm và tạo lượt mới
                if(ketQua == false ) diem   = diem +1;
                else diem = diem -1;
                tvPoint.setText("Điểm : "+ diem  );
                ketQua =random.nextBoolean();
                playGame(ketQua);
            }
        });
    }
    private void playGame(boolean ketQua  ){

        String chuoipheptinh = "df";
        int ketquaHienThi =0 ;
        int numberPhepTinh = random.nextInt(4);
        int number1 ,number2,numberTam;
        int saiso =0;
        // 0=+ , 1 = -, 2 = * , 3 = /
        switch ( numberPhepTinh) {
            case 0 :
                number1 = random.nextInt(40)+50;
                number2 = random.nextInt(90- number1);
                if (ketQua= false) saiso =random.nextInt(10)+1;
                chuoipheptinh = number2 + " + " + number1;
                if (random.nextInt(1)==0)ketquaHienThi = number1 +number2 +saiso;
                else ketquaHienThi = number1 +number2 - saiso;
                break;
            case 1 :
                number1 = random.nextInt(100);
                number2 = random.nextInt(100- number1);
                if (ketQua= false)saiso =random.nextInt(20)+1;
                chuoipheptinh = number1 + " - " + number2;
                if (random.nextInt(1)==0)ketquaHienThi = number1 - number2 +saiso;
                else ketquaHienThi = number1 -number2 - saiso;
                break;
            case 2 :
                number1 = random.nextInt(100);
                number2 = random.nextInt(10);
                numberTam = number1/number2;
                number1 = numberTam;
                if (ketQua= false)saiso =random.nextInt(20)+1;
                chuoipheptinh = number2 + " * " + number1;
                if (random.nextInt(1)==0)ketquaHienThi = number2 * number1 +saiso;
                else ketquaHienThi = number1 * number2 - saiso;
                break;
            case 3 :
                number1 = random.nextInt(80)+10;
                numberTam= random.nextInt(10);
                number2 = number1/numberTam ;
                number1 = number2* numberTam;
                if (ketQua= false)saiso =random.nextInt(20)+1;
                chuoipheptinh = number2 + " / " + number1;
                if (random.nextInt(1)==0)ketquaHienThi = number1 / number2 +saiso;
                else ketquaHienThi = number1 / number2 - saiso;
                break;
        }
        tvMainView.setText(chuoipheptinh);
        tvResultMath.setText( " = " + ketquaHienThi );
        t1.start();
    }

    class TheadSimple extends Thread {
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            diem =diem -1;
            tvPoint.setText("Điểm : "+ diem  );
            ketQua =random.nextBoolean();
            gamestart();

        }}

}
