package jp.ac.nagano_nct.ashida_lab.se;
import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalStateChangeEvent;
import com.pi4j.io.gpio.digital.DigitalStateChangeListener;

/**
 * フォトリフレクタ
 * @author ashida
 */
public class PhotoReflector {

	/** 端子を操作するためのインスタンス */
	private DigitalInput _pin;

	/** コンストラクタ
	 * @param pin_num ピン番号
	 */
	protected PhotoReflector(int pin_num){
		Context pi4j = Pi4J.newAutoContext();
		var config = DigitalInput.newConfigBuilder(pi4j)
                        .id("photoReflector")
                        .name("PhotoReflector")
                        .address(pin_num)
                        .provider("pigpio-digital-input");
		_pin = pi4j.create(config);
	}
	/**
	 * 黒色を検知したか
	 * @return 黒色を検知したら真，してなければ偽を返す
	 */
	public boolean isBlack() {
		return _pin.isLow();
	}

	public void setListener(PhotoReflectorListener listener){
		_pin.addListener(digitalStateChangeEvent -> {
			listener.handlePhotoReflectorStateChangeEvent(_pin.isHigh());
		});
	}
}
