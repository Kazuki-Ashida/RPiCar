package jp.ac.nagano_nct.ashida_lab.se;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;

/**
 * クラクション
 * @author ashida
 */
public class Beeper {

	/**
	 * 端子番号
	 */
	private static final int _PIN_NUM = 10;

	/** 端子を操作するためのインスタンス */
	DigitalOutput _pin;
	
	/**
	 * シングルトンのインスタンス
	 */
	private static Beeper _instance = new Beeper(_PIN_NUM);

	/**
	 * コンストラクタ
	 */
	private Beeper(int pin_num) {
		Context pi4j = Pi4J.newAutoContext();
		var config = DigitalOutput.newConfigBuilder(pi4j)
                        .id("beeper")
                        .name("Beeper")
                        .address(pin_num)
                        .shutdown(DigitalState.LOW)
                        .initial(DigitalState.LOW)
                        .provider("pigpio-digital-output");
		_pin = pi4j.create(config);

	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static Beeper getInstance() {
		return _instance;
	}

	/**
	 * 音を鳴らし始める
	 */
	public void startBeep() {
		_pin.high();
	}

	/**
	 * 音を鳴らし終える
	 */
	public void stopBeep() {
		_pin.low();
	}

}
