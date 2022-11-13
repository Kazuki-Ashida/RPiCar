package jp.ac.nagano_nct.ashida_lab.se;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;

/**
 * ライト
 * @author ashida
 */
public class Light {

	/** 端子を操作するためのインスタンス */
	DigitalOutput _pin;

	/*　コンストラクタ
	 * @param pin_num ピン番号
	 */
	protected Light(int pin_num){
		Context pi4j = Pi4J.newAutoContext();
		var config = DigitalOutput.newConfigBuilder(pi4j)
                        .id("light")
                        .name("LED Flasher")
                        .address(pin_num)
                        .shutdown(DigitalState.LOW)
                        .initial(DigitalState.LOW)
                        .provider("pigpio-digital-output");
		_pin = pi4j.create(config);
	}
	/**
	 * 点灯する
	 */
	public void turnOn() {
		_pin.high();				
	}

	/**
	 * 消灯する
	 */
	public void turnOff() {
		_pin.low();
	}
}
