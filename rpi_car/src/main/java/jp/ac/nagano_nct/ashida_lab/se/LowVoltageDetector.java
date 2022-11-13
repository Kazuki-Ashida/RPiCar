package jp.ac.nagano_nct.ashida_lab.se;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;

/**
 * 定電圧検知器
 * @author ashida
 */
public class LowVoltageDetector {

	/**
	 * 電圧が下がってきていることを伝えるLEDとつながっている端子番号
	 */
	private static final int _DROP_VOLTAGE_INDICATOR_PIN_NUM = 25;

	/**
	 * コンパレータとつながっている端子番号
	 */
	private static final int _COMPARATOR_PIN_NUM = 19;

	/**
	 * 電圧低下した時間が継続した時間[ms]．この時間より長く電圧が低下していたら通知する
	 */
	private static final long _DROP_VOLTAGE_TIME_THRESHOLD = 10000;

	/** 電圧低下を検知する端子を操作するためのインスタンス */
	private DigitalInput _lowVoltageDetectorPin;
	
	/** インディケータの端子 */
	private DigitalOutput _indicatorPin;
	
	/**
	 * シングルトンのインスタンス
	 */
	private static LowVoltageDetector _instance = new LowVoltageDetector(_DROP_VOLTAGE_INDICATOR_PIN_NUM, _COMPARATOR_PIN_NUM);

	/**
	 * 電圧が低下し始めた時間
	 */
	private long _startTime = Long.MAX_VALUE;

	/**
	 * 電圧が低下したことを伝える
	 */
	private Thread _checkVoltageThread = new Thread(){
		@Override	
		public void run ( ){
			long now_time = System.currentTimeMillis();
			if( now_time-_startTime >= _DROP_VOLTAGE_TIME_THRESHOLD){
				_indicatorPin.high();
				_listener.lowVoltageEvent(); 
			}
		}
	};

	/** リスナ */
	private LowVoltageDetectorListener _listener;

	/**
	 * コンストラクタ
	 * @param voltage_detector_pin_num 電圧検知用端子番号
	 * @param indicator_pin_num インディケータ端子番号
	 */
	private LowVoltageDetector(int voltage_detector_pin_num,
		int indicator_pin_num) {
		Context pi4j = Pi4J.newAutoContext();
		var config = DigitalInput.newConfigBuilder(pi4j)
                        .id("lowVoltageDetector")
                        .name("LowVoltageDetector")
                        .address(voltage_detector_pin_num)
                        .provider("pigpio-digital-input");
		_lowVoltageDetectorPin = pi4j.create(config);


		var config2 = DigitalOutput.newConfigBuilder(pi4j)
						.id("lowVoltageIndicator")
						.name("LowVoltageIndicator")
						.address(indicator_pin_num)
                        .shutdown(DigitalState.LOW)
                        .initial(DigitalState.LOW)
						.provider("pigpio-digital-output");
		_indicatorPin = pi4j.create(config2);

		_lowVoltageDetectorPin.addListener(event -> {
			/* 電圧が低下し始めたら */
			if(_lowVoltageDetectorPin.isLow()) {
				/* 現在の時間を記憶する */
				_startTime = System.currentTimeMillis();
			}
			else {
				/* 電圧が低下し始めた時間を初期化する */
				_startTime = Long.MAX_VALUE;
			}
		});
		/* 電圧低下を監視し始める */
		_checkVoltageThread.start();
	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static LowVoltageDetector getInstance() {
		return _instance;
	}

	/**
	 * リスナをセットする
	 * @param listener リスナ
	 */
	public void setListener(LowVoltageDetectorListener listener) {
		_listener = listener;
	}

}
