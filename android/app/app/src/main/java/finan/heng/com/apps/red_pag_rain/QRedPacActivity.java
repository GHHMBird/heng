package finan.heng.com.apps.red_pag_rain;
/*
 * Created by hhm on 2017/12/19.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;

public class QRedPacActivity extends BaseActivity implements View.OnClickListener {

    private RedPacketTest redRainView1;
    private TextView money;
    private int totalmoney = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyTheme();
        setContentView(R.layout.activity_qredpac);
        money = findViewById(R.id.money);
        redRainView1 = findViewById(R.id.red_packets_view1);
        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start) {
            startRedRain();
        } else if (v.getId() == R.id.stop) {
            stopRedRain();
        }
    }

    /**
     * 开始下红包雨
     */
    private void startRedRain() {
        redRainView1.startRain();
        redRainView1.setOnRedPacketClickListener(new RedPacketTest.OnRedPacketClickListener() {
            @Override
            public void onRedPacketClickListener(RedPacket redPacket) {
//                redRainView1.pauseRain();
//                ab.setCancelable(false);
//                ab.setTitle("红包提醒");
//                ab.setNegativeButton("继续抢红包", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        redRainView1.restartRain();
//                    }
//                });
//
                if (redPacket.isRealRed) {
//                    ab.setMessage("恭喜你，抢到了" + redPacket.money + "元！");
                    totalmoney += redPacket.money;
                    money.setText("中奖金额: " + totalmoney);
                }
                // else {
//                    ab.setMessage("很遗憾，下次继续努力！");
//                }
//                redRainView1.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        ab.show();
//                    }
//                });
            }
        });
    }

    /**
     * 停止下红包雨
     */
    private void stopRedRain() {
        totalmoney = 0;//金额清零
        redRainView1.stopRainNow();
    }

}
