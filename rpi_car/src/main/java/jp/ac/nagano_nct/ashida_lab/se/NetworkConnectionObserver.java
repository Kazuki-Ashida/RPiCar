package jp.ac.nagano_nct.ashida_lab.se;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;

/**
 * ネットワーク接続監視器
 * @author ashida
 */
public class NetworkConnectionObserver {

	/**
	 * ネットワークに接続したことを伝えるLEDとつながっている端子番号
	 */
	private static final int _CONNECT_NETWORK_INDICATOR_PIN_NUM = 12;

	/**
	 * シングルトンのインスタンス
	 */
	private static NetworkConnectionObserver _instance = new NetworkConnectionObserver(_CONNECT_NETWORK_INDICATOR_PIN_NUM);

	/** 端子を操作するためのインスタンス */
	private DigitalOutput _pin;

	/**
	 * コンストラクタ
	 * @param pin_num 端子番号
	 */
	private NetworkConnectionObserver(int pin_num) {
		Context pi4j = Pi4J.newAutoContext();
		var config = DigitalOutput.newConfigBuilder(pi4j)
                        .id("networkConnection")
                        .name("NetworkConnection")
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
	public static NetworkConnectionObserver getInstance() {
		return _instance;
	}

}
