package jp.ac.nagano_nct.ashida_lab.se;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;

/**
 * ロータリエンコーダ
 * @author ashida
 */
public class RotaryEncoder {

	/**
	 * 回転数
	 */
	private long _rotationNum;

	/** 端子を操作するためのインスタンス */
	private DigitalInput _pin;

	/** コンストラクタ
	 * @param pin_num ピン番号
	 */
	protected RotaryEncoder(int pin_num){
		Context pi4j = Pi4J.newAutoContext();
		var config = DigitalInput.newConfigBuilder(pi4j)
                        .id("rotaryEncoder")
                        .name("RotaryEncoder")
                        .address(pin_num)
                        .provider("pigpio-digital-input");
		_pin = pi4j.create(config);
		_pin.addListener(DigitalStateChangeListener->{
			if(_pin.isHigh()){
				_rotationNum++;
			}
		});
	}
	/**
	 * 回転数を得る
	 * @return 回転数
	 */
	public long getRotationNum() {
		return _rotationNum;
	}

	/**
	 * 回転数をクリアする
	 */
	public void clear() {
		_rotationNum = 0;
	}

}
