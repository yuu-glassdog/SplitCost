package com.example.splitcost;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	EditText etxtNum;		// 人数
	EditText etxtMoney;	// 金額
	TextView txtOneMoney;	// 計算結果
	TextView txtYen;		// "円"

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Viewをセット
		setContentView(R.layout.activity_main);
		// Viewの要素を参照
		Button btn1 = (Button)findViewById(R.id.button1);
		Button btn2 = (Button)findViewById(R.id.button2);
		txtOneMoney = (TextView)findViewById(R.id.txtOneMoney);
		txtYen = (TextView)findViewById(R.id.txtYen);
		etxtNum = (EditText)findViewById(R.id.etxtNum);
		etxtMoney = (EditText)findViewById(R.id.etxtMoney);

		// 計算ボタンの処理
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// フォーム入力を取得
				String strNum = etxtNum.getText().toString();
				String strMoney = etxtMoney.getText().toString();
				// フォームのどちらかが空の場合
				if ( strNum.equals("") || strMoney.equals("") ) {
					// アラートを表示
					AlertDialog.Builder dlg1;
					dlg1 = new AlertDialog.Builder(MainActivity.this);
					dlg1.setTitle("エラー");
					dlg1.setMessage("人数と金額 両方を入力してください.");
					dlg1.setPositiveButton("OK", null);
					dlg1.show();
					return;
				} else {
					// 整数変換
					int num = Integer.parseInt(strNum);
					int money = Integer.parseInt(strMoney);
					// 人数が0人の場合
					if (num == 0) {
						// アラートを表示
						AlertDialog.Builder dlg2;
						dlg2 = new AlertDialog.Builder(MainActivity.this);
						dlg2.setTitle("エラー");
						dlg2.setMessage("人数は1人以上にしてください.");
						dlg2.setPositiveButton("OK", null);
						dlg2.show();
					// 人数 > 金額の場合
					} else if (num > money) {
						// アラートを表示
						AlertDialog.Builder dlg3;
						dlg3 = new AlertDialog.Builder(MainActivity.this);
						dlg3.setTitle("エラー");
						dlg3.setMessage("金額は人数以上にしてください.");
						dlg3.setPositiveButton("OK", null);
						dlg3.show();
					// 正常な入力の場合
					} else {
						// 金額を計算して値をセット
						int result = money / num;
						txtOneMoney.setText(Integer.toString(result));
						// 計算結果を描画
						txtOneMoney.setVisibility(View.VISIBLE);
						txtYen.setVisibility(View.VISIBLE);
					}
				}
			}
		});

		// リセットボタンの処理
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// フォームと計算結果をリセット
				etxtNum.setText("");
				etxtMoney.setText("");
				txtOneMoney.setText(getResources().getText(R.string.result));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
